package com.marsad.BoToTravel.fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.marsad.catchy.R;

public class Error extends AppCompatActivity {
    private EditText error;
    private Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_error);
        error = findViewById(R.id.editTextTextPersonName);
        send = findViewById(R.id.send_error);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                String url="Báo lỗi/Chi tiết" ;
                DatabaseReference myRef = database.getReference(url);

                myRef.setValue(error.getText().toString());
            }
        });

    }
}