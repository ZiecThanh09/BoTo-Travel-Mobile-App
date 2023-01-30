package com.example.bototravel.Chat.Meeting;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bototravel.Friend.Friend;
import com.example.bototravel.R;
import com.example.bototravel.Utilities.Constants;


public class OutgoingInvitationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meeting_outgoing_invitation);

        ImageView imgMeetingType = findViewById(R.id.imgMeetingType);
        String meetingType = getIntent().getStringExtra("type");

        if (meetingType != null) {
            if (meetingType.equals("video")) {
                imgMeetingType.setImageResource(R.drawable.img_videocall);
            }
        }

        ImageView imgAvatar = findViewById(R.id.img_avt);
        TextView tvUserName = findViewById(R.id.tv_userName);
        TextView tvEmail = findViewById(R.id.tv_email);

        Friend friend = (Friend) getIntent().getSerializableExtra(Constants.KEY_FRIEND);
        if (friend != null) {
            imgAvatar.setImageBitmap(getFriendUserImage(friend.image));
            tvUserName.setText(String.format("%s", friend.name));
            tvEmail.setText(friend.email);
        }

        ImageButton imgBtnStopInvitation = findViewById(R.id.imgBtn_reject);
        imgBtnStopInvitation.setOnClickListener(v -> onBackPressed());
    }

    private Bitmap getFriendUserImage(String encodedImage) {
        if (encodedImage != null) {
            byte[] bytes = Base64.decode(encodedImage, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        } else {
            return null;
        }
    }
}