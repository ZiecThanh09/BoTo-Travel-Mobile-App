package com.example.boto_travel_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class send_report extends AppCompatActivity {
    EditText content,error;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_report);
      error=findViewById(R.id.error);
      content=findViewById(R.id.subject);
      send=findViewById(R.id.send);
      send.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              String mail= "hoanglongblbp8@gmail.com";
              String[] mails= mail.split(",");
              Intent intent=new Intent(Intent.ACTION_SEND);
              intent.putExtra(Intent.EXTRA_EMAIL,mails);
              intent.putExtra(Intent.EXTRA_SUBJECT,error.getText().toString());
              intent.putExtra(Intent.EXTRA_TEXT,content.getText().toString());
              intent.setType("message/rfc822");
              startActivity(intent);
          }
      });
    }
}