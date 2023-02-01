package com.example.bototravel.Chat.Meeting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bototravel.Friend.Friend;
import com.example.bototravel.Network.ApiClient;
import com.example.bototravel.Network.ApiService;
import com.example.bototravel.R;
import com.example.bototravel.Utilities.Constants;
import com.google.firebase.firestore.FirebaseFirestore;
import com.makeramen.roundedimageview.RoundedImageView;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IncomingInvitationActivity extends AppCompatActivity {
    private String meetingType = null;
    private Friend receiverFriend;
    private FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meeting_incoming_invitation);
        database = FirebaseFirestore.getInstance();

        ImageView imgMeetingType = findViewById(R.id.imgMeetingType);
        meetingType = getIntent().getStringExtra(Constants.REMOTE_MSG_MEETING_TYPE);

        if (meetingType != null) {
            if (meetingType.equals("video")) {
                imgMeetingType.setImageResource(R.drawable.img_videocall);
            } else {
               imgMeetingType.setImageResource(R.drawable.img_phone);
            }
        }

        ImageView imgAvatar = findViewById(R.id.img_avt);
        TextView tvUserName = findViewById(R.id.tv_userName);
        TextView tvEmail = findViewById(R.id.tv_email);

        receiverFriend = (Friend) getIntent().getSerializableExtra(Constants.KEY_FRIEND);
        getReceiverAvatar(imgAvatar);

        tvUserName.setText(receiverFriend.name);
        tvEmail.setText(receiverFriend.email);

        ImageButton imgAcceptInvitation = findViewById(R.id.imgBtn_accept);
        imgAcceptInvitation.setOnClickListener(v -> sendInvitationResponse(
                Constants.REMOTE_MSG_INVITATION_ACCEPTED,
                getIntent().getStringExtra(Constants.REMOTE_MSG_INVITER_TOKEN)
        ));

        ImageButton imgRejectInvitation = findViewById(R.id.imgBtn_reject);
        imgRejectInvitation.setOnClickListener(v -> sendInvitationResponse(
                Constants.REMOTE_MSG_INVITATION_REJECTED,
                getIntent().getStringExtra(Constants.REMOTE_MSG_INVITER_TOKEN)
        ));
    }

    private void getReceiverAvatar(ImageView imgAvatar) {
        database.collection(Constants.KEY_COLLECTION_USERS).document(receiverFriend.id)
                .addSnapshotListener(IncomingInvitationActivity.this, ((value, error) -> {
                    if (error != null) {
                        return;
                    }
                    if (value != null) {
                        if (receiverFriend.image == null) {
                            receiverFriend.image = value.getString(Constants.KEY_IMAGE);
                            imgAvatar.setImageBitmap(getFriendUserImage(receiverFriend.image));
                        }
                    }
                }));
    }

    private Bitmap getFriendUserImage(String encodedImage) {
        if (encodedImage != null) {
            byte[] bytes = Base64.decode(encodedImage, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        } else {
            return null;
        }
    }

    private void sendInvitationResponse(String type, String receiverToken) {
        try {
            JSONArray tokens = new JSONArray();
            tokens.put(receiverToken);

            JSONObject body = new JSONObject();
            JSONObject data = new JSONObject();

            data.put(Constants.REMOTE_MSG_TYPE, Constants.REMOTE_MSG_INVITATION_RESPONSE);
            data.put(Constants.REMOTE_MSG_INVITATION_RESPONSE, type);

            body.put(Constants.REMOTE_MSG_DATA, data);
            body.put(Constants.REMOTE_MSG_REGISTRATION_IDS, tokens);

            sendRemoteMessages(body.toString(), type);
        } catch (Exception exception) {
            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void sendRemoteMessages(String remoteMessageBody, String type) {
        ApiClient.getClient().create(ApiService.class).sendMessage(
                Constants.getRemoteMsgHeaders(), remoteMessageBody
        ).enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if (response.isSuccessful()) {
                    if (type.equals(Constants.REMOTE_MSG_INVITATION_ACCEPTED)) {
                        try {
                            URL serverURL = new URL("https://meet.jit.si");
                            JitsiMeetConferenceOptions.Builder builder = new JitsiMeetConferenceOptions.Builder();
                            builder.setServerURL(serverURL);
                            builder.setWelcomePageEnabled(false);
                            builder.setRoom(getIntent().getStringExtra(Constants.REMOTE_MSG_MEETING_ROOM));
                            if (meetingType.equals("audio")) {
                                builder.setVideoMuted(true);
                            }
                            JitsiMeetActivity.launch(IncomingInvitationActivity.this, builder.build());
                            finish();
                        } catch (Exception exception) {
                            Toast.makeText(
                                    IncomingInvitationActivity.this,
                                    exception.getMessage(),
                                    Toast.LENGTH_SHORT
                            ).show();
                            finish();
                        }
                    } else {
                        Toast.makeText(
                                IncomingInvitationActivity.this,
                                "Invitation Rejected",
                                Toast.LENGTH_SHORT
                        ).show();
                        finish();
                    }
                } else {
                    Toast.makeText(
                            IncomingInvitationActivity.this,
                            response.message(),
                            Toast.LENGTH_SHORT
                    ).show();
                    finish();
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Toast.makeText(IncomingInvitationActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private final BroadcastReceiver invitationResponseReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String type = intent.getStringExtra(Constants.REMOTE_MSG_INVITATION_RESPONSE);
            if (type != null) {
                if (type.equals(Constants.REMOTE_MSG_INVITATION_CANCELLED)) {
                    Toast.makeText(context, "Invitation Cancelled", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(
                invitationResponseReceiver,
                new IntentFilter(Constants.REMOTE_MSG_INVITATION_RESPONSE)
        );
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(
                invitationResponseReceiver
        );
    }
}