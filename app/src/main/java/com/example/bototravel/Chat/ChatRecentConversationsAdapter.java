package com.example.bototravel.Chat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bototravel.Friend.Friend;
import com.example.bototravel.Listeners.ConversionListener;
import com.example.bototravel.databinding.MessageItemBinding;

import java.util.List;

public class ChatRecentConversationsAdapter extends RecyclerView.Adapter<ChatRecentConversationsAdapter.ConversionViewHolder> {
    private final List<Chat> chats;
    private final ConversionListener conversionListener;

    public ChatRecentConversationsAdapter(List<Chat> chats, ConversionListener conversionListener) {
        this.chats = chats;
        this.conversionListener = conversionListener;
    }

    @NonNull
    @Override
    public ConversionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ConversionViewHolder(
                MessageItemBinding.inflate(
                        LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ConversionViewHolder holder, int position) {
        holder.setData(chats.get(position));
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    class ConversionViewHolder extends RecyclerView.ViewHolder {
        MessageItemBinding binding;

        ConversionViewHolder(MessageItemBinding messageItemBinding) {
            super(messageItemBinding.getRoot());
            binding = messageItemBinding;
        }

        void setData(Chat chat) {
            binding.imgAvt.setImageBitmap(getConversionImage(chat.conversionImage));
            binding.tvUsername.setText(chat.conversionName);
            binding.tvRecentMessage.setText(chat.message);
            binding.getRoot().setOnClickListener(v -> {
                Friend friend = new Friend();
                friend.id = chat.conversionId;
                friend.name = chat.conversionName;
                friend.image = chat.conversionImage;
                friend.email = chat.conversionEmail;
                conversionListener.onConversionClicked(friend);
            });
        }
    }

    private Bitmap getConversionImage(String encodedImage) {
        if (encodedImage != null) {
            byte[] bytes = Base64.decode(encodedImage, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        } else {
            return null;
        }
    }
}
