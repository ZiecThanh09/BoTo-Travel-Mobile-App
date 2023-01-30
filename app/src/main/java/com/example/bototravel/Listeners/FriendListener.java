package com.example.bototravel.Listeners;

import com.example.bototravel.Friend.Friend;

public interface FriendListener {
    void onFriendClicked(Friend friend);
    void initiateVideoMeeting(Friend friend);
    void initiateAudioMeeting(Friend friend);

}
