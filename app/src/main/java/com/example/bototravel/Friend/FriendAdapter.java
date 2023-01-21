package com.example.bototravel.Friend;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bototravel.Listeners.FriendListener;
import com.example.bototravel.databinding.FriendItemBinding;

import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendViewHolder>{

    private final List<Friend> friends;
    private final FriendListener friendListener;

    public FriendAdapter(List<Friend> friends, FriendListener friendListener) {
        this.friends = friends;
        this.friendListener = friendListener;
    }

    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            FriendItemBinding friendItemBinding = FriendItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new FriendViewHolder(friendItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendViewHolder holder, int position) {
        holder.setFriendUserData(friends.get(position));
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    class FriendViewHolder extends RecyclerView.ViewHolder {
        FriendItemBinding binding;

        FriendViewHolder(FriendItemBinding friendItemBinding) {
            super(friendItemBinding.getRoot());
            binding = friendItemBinding;
        }

        void setFriendUserData(Friend friend) {
            binding.tvUsername.setText(friend.name);
            binding.tvEmail.setText(friend.email);
            binding.imgAvt.setImageBitmap(getFriendUserImage(friend.image));
            binding.getRoot().setOnClickListener(v -> friendListener.onFriendClicked(friend));
        }
    }

    private Bitmap getFriendUserImage(String encodedImage) {
        byte[] bytes = Base64.decode(encodedImage, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
