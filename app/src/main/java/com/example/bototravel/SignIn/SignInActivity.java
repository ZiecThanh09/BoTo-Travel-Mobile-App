package com.example.bototravel.SignIn;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bototravel.Feed.FeedActivity;
import com.example.bototravel.R;
import com.example.bototravel.Utilities.Constants;
import com.example.bototravel.Utilities.PreferenceManager;
import com.example.bototravel.databinding.SignInBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignInActivity extends AppCompatActivity {

    private SignInBinding binding;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SignInBinding.inflate(getLayoutInflater());
        preferenceManager = new com.example.bototravel.Utilities.PreferenceManager(
                getApplicationContext());

        // Nhớ mật khẩu
        if (preferenceManager.getBoolean(Constants.KEY_IS_SIGNED_IN)) {
            Intent intent = new Intent(getApplicationContext(), FeedActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.anim_move_in_right, R.anim.anim_move_out_left);
            finish();
        }

        setContentView(binding.getRoot());
        setListeners();
    }

    private void setListeners() {
        binding.btnSignIn.setOnClickListener(v -> {
            if (isValidSignInDetails()) {
                signIn();
            }
        });
    }

    private void signIn() {
        loading(true);
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        database.collection(Constants.KEY_COLLECTION_USERS)
                .whereEqualTo(Constants.KEY_EMAIL, binding.email.getText().toString())
                .whereEqualTo(Constants.KEY_PASSWORD, binding.password.getText().toString())
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null
                            && task.getResult().getDocuments().size() > 0) {
                        DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                        preferenceManager.putBoolean(Constants.KEY_IS_SIGNED_IN, true);
                        preferenceManager.putString(Constants.KEY_USER_ID,
                                documentSnapshot.getId());
                        preferenceManager.putString(Constants.KEY_NAME,
                                documentSnapshot.getString(Constants.KEY_NAME));
                        preferenceManager.putString(Constants.KEY_EMAIL,
                                documentSnapshot.getString(Constants.KEY_EMAIL));
                        preferenceManager.putString(Constants.KEY_IMAGE,
                                documentSnapshot.getString(Constants.KEY_IMAGE));
                        Intent intent = new Intent(getApplicationContext(), FeedActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        overridePendingTransition(R.anim.anim_move_in_right,
                                R.anim.anim_move_out_left);
                    } else {
                        loading(false);
                        showToast("Unable to sign in");
                    }
                });
    }

    private void loading(Boolean isLoading) {
        if (isLoading) {
            binding.btnSignIn.setVisibility(View.INVISIBLE);
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.btnSignIn.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.INVISIBLE);
        }
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private Boolean isValidSignInDetails() {
        if (binding.email.getText().toString().trim().isEmpty()) {
            showToast("Enter email");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.email.getText().toString()).matches()) {
            showToast("Enter valid email");
            return false;
        } else if (binding.password.getText().toString().trim().isEmpty()) {
            showToast("Enter password");
            return false;
        } else return true;
    }
}
