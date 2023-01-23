package com.example.bototravel.Chat;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bototravel.databinding.MessageReceiveDetailedItemBinding;
import com.example.bototravel.databinding.MessageSendDetailedItemBinding;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final List<Chat> chats;
    private Bitmap receiverProfileImage;
    private final String senderId;

    public static final int VIEW_TYPE_SENT = 1;
    public static final int VIEW_TYPE_RECEIVED = 2;

    public void setReceiverProfileImage(Bitmap bitmap) {
        receiverProfileImage = bitmap;
    }

    public ChatAdapter(List<Chat> chats, Bitmap receiverProfileImage, String senderId) {
        this.chats = chats;
        this.receiverProfileImage = receiverProfileImage;
        this.senderId = senderId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_SENT) {
            return new SentMessageViewHolder(
                    MessageSendDetailedItemBinding.inflate(
                            LayoutInflater.from(parent.getContext()), parent, false));
        } else {
            return new ReceivedMessageViewHolder(
                    MessageReceiveDetailedItemBinding.inflate(
                            LayoutInflater.from(parent.getContext()), parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_SENT) {
            ((SentMessageViewHolder) holder).setData(chats.get(position));
        } else {
            ((ReceivedMessageViewHolder) holder).setData(chats.get(position), receiverProfileImage);
        }
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (chats.get(position).senderId.equals(senderId)) {
            return VIEW_TYPE_SENT;
        } else {
            return VIEW_TYPE_RECEIVED;
        }
    }

    static class SentMessageViewHolder extends RecyclerView.ViewHolder {

        private final MessageSendDetailedItemBinding binding;

        SentMessageViewHolder(MessageSendDetailedItemBinding messageSendDetailedItemBinding) {
            super(messageSendDetailedItemBinding.getRoot());
            binding = messageSendDetailedItemBinding;
        }

        void setData(Chat chat) {
            binding.tvTextMessage.setText(chat.message);
            binding.tvDateTime.setText(chat.dateTime);
        }
    }

    static class ReceivedMessageViewHolder extends RecyclerView.ViewHolder {
        private final MessageReceiveDetailedItemBinding binding;

        ReceivedMessageViewHolder(MessageReceiveDetailedItemBinding messageReceiveDetailedItemBinding) {
            super(messageReceiveDetailedItemBinding.getRoot());
            binding = messageReceiveDetailedItemBinding;
        }

        void setData(Chat chat, Bitmap receiverProfileImage) {
            binding.tvTextMessage.setText(chat.message);
            binding.tvDateTime.setText(chat.dateTime);
            if (receiverProfileImage != null) {
                binding.imgAvt.setImageBitmap(receiverProfileImage);
            }
        }
    }
}
