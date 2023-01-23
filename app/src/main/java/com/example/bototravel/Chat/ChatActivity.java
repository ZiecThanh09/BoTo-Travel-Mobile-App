package com.example.bototravel.Chat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.bototravel.Friend.Friend;
import com.example.bototravel.Friend.FriendActivity;
import com.example.bototravel.Listeners.ConversionListener;
import com.example.bototravel.SignIn.SignInActivity;
import com.example.bototravel.Utilities.Constants;
import com.example.bototravel.Utilities.PreferenceManager;
import com.example.bototravel.databinding.MessageBinding;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ChatActivity extends BaseActivity implements ConversionListener {

    private MessageBinding binding;
    private PreferenceManager preferenceManager;
    private List<Chat> conversations;
    private ChatRecentConversationsAdapter conversationsAdapter;
    private FirebaseFirestore database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MessageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager = new PreferenceManager(getApplicationContext());
        init();
        loadUserDetails();
        getToken();
        setListeners();
        listenConversations();
    }

    private void init() {
        conversations = new ArrayList<>();
        conversationsAdapter = new ChatRecentConversationsAdapter(conversations, this);
        binding.rcvConversations.setAdapter(conversationsAdapter);
        database = FirebaseFirestore.getInstance();
    }

    private void setListeners() {
        binding.imgBtnLogo.setOnClickListener(v -> signOut());
        binding.btnUsers.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), FriendActivity.class));
        });
    }

    private void loadUserDetails() {
        byte[] bytes = Base64.decode(
                preferenceManager.getString(Constants.KEY_IMAGE), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        binding.imgAvt.setImageBitmap(bitmap);
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void listenConversations() {
        database.collection(Constants.KEY_COLLECTION_CONVERSATIONS)
                .whereEqualTo(Constants.KEY_SENDER_ID, preferenceManager.getString(Constants.KEY_USER_ID))
                .addSnapshotListener(eventListener);
        database.collection(Constants.KEY_COLLECTION_CONVERSATIONS)
                .whereEqualTo(Constants.KEY_RECEIVER_ID, preferenceManager.getString(Constants.KEY_USER_ID))
                .addSnapshotListener(eventListener);
    }

    @SuppressLint("NotifyDataSetChanged")
    private final EventListener<QuerySnapshot> eventListener = (value, error) -> {
        if (error != null) {
            return;
        }
        if (value != null) {
            for (DocumentChange documentChange : value.getDocumentChanges()) {
                if (documentChange.getType() == DocumentChange.Type.ADDED) {
                    String senderId = documentChange.getDocument().getString(Constants.KEY_SENDER_ID);
                    String receiverId = documentChange.getDocument().getString(Constants.KEY_RECEIVER_ID);
                    Chat chat = new Chat();
                    chat.senderId = senderId;
                    chat.receiverId = receiverId;
                    if (preferenceManager.getString(Constants.KEY_USER_ID).equals(senderId)) {
                        chat.conversionImage = documentChange.getDocument().getString(Constants.KEY_RECEIVER_IMAGE);
                        chat.conversionName = documentChange.getDocument().getString(Constants.KEY_RECEIVER_NAME);
                        chat.conversionId = documentChange.getDocument().getString(Constants.KEY_RECEIVER_ID);
                    } else {
                        chat.conversionImage = documentChange.getDocument().getString(Constants.KEY_SENDER_IMAGE);
                        chat.conversionName = documentChange.getDocument().getString(Constants.KEY_SENDER_NAME);
                        chat.conversionId = documentChange.getDocument().getString(Constants.KEY_SENDER_ID);
                    }
                    chat.message = documentChange.getDocument().getString(Constants.KEY_LAST_MESSAGE);
                    chat.dateObject = documentChange.getDocument().getDate(Constants.KEY_TIMESTAMP);
                    conversations.add(chat);
                } else if (documentChange.getType() == DocumentChange.Type.MODIFIED) {
                    for (int i = 0; i < conversations.size(); i++) {
                        String senderId = documentChange.getDocument().getString(Constants.KEY_SENDER_ID);
                        String receiverId = documentChange.getDocument().getString(Constants.KEY_RECEIVER_ID);
                        if (conversations.get(i).senderId.equals(senderId) && conversations.get(i).receiverId.equals(receiverId)) {
                            conversations.get(i).message = documentChange.getDocument().getString(Constants.KEY_LAST_MESSAGE);
                            conversations.get(i).dateObject = documentChange.getDocument().getDate(Constants.KEY_TIMESTAMP);
                            break;
                        }
                    }
                }
            }
            Collections.sort(conversations, (obj1, obj2) -> obj2.dateObject.compareTo(obj1.dateObject));
            conversationsAdapter.notifyDataSetChanged();
            binding.rcvConversations.smoothScrollToPosition(0);
            binding.rcvConversations.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.GONE);
        }
    };

    private void getToken() {
        FirebaseMessaging.getInstance().getToken().addOnSuccessListener(this::updateToken);
    }

    private void updateToken(String token) {
        preferenceManager.putString(Constants.KEY_FCM_TOKEN, token);
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        DocumentReference documentReference =
                database.collection(Constants.KEY_COLLECTION_USERS).document(
                        preferenceManager.getString(Constants.KEY_USER_ID)
                );
        documentReference.update(Constants.KEY_FCM_TOKEN, token)
                .addOnFailureListener(e -> showToast("Unable to update token"));
    }

    private void signOut() {
        showToast("Signing out...");
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        DocumentReference documentReference =
                database.collection(Constants.KEY_COLLECTION_USERS).document(
                        preferenceManager.getString(Constants.KEY_USER_ID)
                );
        HashMap<String, Object> updates = new HashMap<>();
        updates.put(Constants.KEY_FCM_TOKEN, FieldValue.delete());
        documentReference.update(updates)
                .addOnSuccessListener(unused -> {
                    preferenceManager.clear();
                    startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                    finish();
                })
                .addOnFailureListener(e -> showToast("Unable to sign out"));
    }

    @Override
    public void onConversionClicked(Friend friend) {
        Intent intent = new Intent(getApplicationContext(), ChatDetailedActivity.class);
        intent.putExtra(Constants.KEY_FRIEND, friend);
        startActivity(intent);
    }
}
