package com.example.bototravel.Friend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bototravel.Chat.ChatDetailedActivity;
import com.example.bototravel.Listeners.FriendListener;
import com.example.bototravel.Utilities.Constants;
import com.example.bototravel.Utilities.PreferenceManager;
import com.example.bototravel.databinding.FriendBinding;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class FriendActivity extends AppCompatActivity implements FriendListener {

    private FriendBinding binding;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FriendBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager = new PreferenceManager(getApplicationContext());
        setListeners();
        getFriends();
    }

    private void setListeners() {
        binding.imgBack.setOnClickListener(v -> onBackPressed());
    }

    private void getFriends() {
        loading(true);
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        database.collection(Constants.KEY_COLLECTION_USERS).get()
                .addOnCompleteListener(task -> {
                    loading(false);
                    String currentFriendId = preferenceManager.getString(Constants.KEY_USER_ID);
                    if (task.isSuccessful() && task.getResult() != null) {
                        List<Friend> friends = new ArrayList<>();
                        for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                            if (currentFriendId.equals(queryDocumentSnapshot.getId())) {
                                continue;
                            }
                            Friend friend = new Friend();
                            friend.name = queryDocumentSnapshot.getString(Constants.KEY_NAME);
                            friend.email = queryDocumentSnapshot.getString(Constants.KEY_EMAIL);
                            friend.image = queryDocumentSnapshot.getString(Constants.KEY_IMAGE);
                            friend.token = queryDocumentSnapshot.getString(Constants.KEY_FCM_TOKEN);
                            friend.id = queryDocumentSnapshot.getId();
                            friends.add(friend);
                        }
                        if (friends.size() > 0) {
                            FriendAdapter friendAdapter = new FriendAdapter(friends, this);
                            binding.rcvFriends.setAdapter(friendAdapter);
                            binding.rcvFriends.setVisibility(View.VISIBLE);
                        } else {
                            showErrorMessage();
                        }
                    } else {
                        showErrorMessage();
                    }
                });
    }

    private void showErrorMessage() {
        binding.tvErrorMessage.setText(String.format("%s", "No user available"));
        binding.tvErrorMessage.setVisibility(View.VISIBLE);
    }

    private void loading(Boolean isLoading) {
        if (isLoading) {
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onFriendClicked(Friend friend) {
        Intent intent = new Intent(getApplicationContext(), ChatDetailedActivity.class);
        intent.putExtra(Constants.KEY_FRIEND, friend);
        startActivity(intent);
        finish();
    }
}