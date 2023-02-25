package com.example.bototravel;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Messenger;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnxemds;
    private Button btndat;
    private TextView tvdiemdi;
    private ListView listview;
    private List<data> Listdata;
    //private CustomAdapter_dstour customAdapter_dstour;
    private Adapter adapter;
    private DatabaseReference mDatabase;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private String Didi;
    private String Dide;
    private String Ngdi;
    private String Ngve;
    private String Gi;
    private TextView Diemdi;
    private TextView Diemden;
    private TextView Ngaydi;
    private TextView Ngayve;
    private TextView Gia;
    private Button btnDangtour;
    private EditText nhapdiemdi;
    private EditText nhapdiemden;
    private EditText nhapngaydi;
    private EditText nhapngayve;
    private EditText nhapgia;
    private int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.listview);

        Listdata = new ArrayList<>();
        Listdata.add(new data("Ngày đi : a","Ngày về : b","Điểm đi : c","Giá : e","Điểm đến : d"));

        adapter = new Adapter(this,R.layout.dong_danhsachtour ,Listdata);
        listview.setAdapter(adapter);

        Diemdi =(TextView) findViewById(R.id.diemdi);
        Diemden = (TextView) findViewById(R.id.diemden);
        Ngaydi = (TextView) findViewById(R.id.ngaydi);
        Ngayve = (TextView) findViewById(R.id.ngayve);
        Gia = (TextView) findViewById(R.id.gia);
        i=2;

        nhapdiemdi =(EditText) findViewById(R.id.nhapdiemdi);
        nhapdiemden =(EditText) findViewById(R.id.nhapdiemden);
        nhapngaydi =(EditText) findViewById(R.id.nhapngaydi);
        nhapngayve =(EditText) findViewById(R.id.nhapdiemden);
        nhapgia=(EditText) findViewById(R.id.nhapgia);

        btnDangtour=(Button) findViewById(R.id.btndangtour);
        btnDangtour.setOnClickListener(this);

        btndat =(Button) findViewById(R.id.btndat);
        btndat.setOnClickListener(this);

        btnxemds = (Button) findViewById(R.id.btnxemds);
        btnxemds.setOnClickListener(this);



    }


    @Override
    public void onClick(View v){
        if(v== btnxemds){
            onClickReadData();
        }
        if(v== btndat){
            writedata("DuongQuocHai","Khachhang");
        }
        if(v== btnDangtour){
            i++;
            dangtour(i);

        }

    }

    public void dangtour(int i) {

        String n = String.valueOf(i);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("Tours/"+n).child("Diemdi").setValue(nhapdiemdi.getText().toString());
        mDatabase.child("Tours/"+n).child("Diemden").setValue(nhapdiemden.getText().toString());
        mDatabase.child("Tours/"+n).child("Ngaydi").setValue(nhapngaydi.getText().toString());
        mDatabase.child("Tours/"+n).child("Ngayve").setValue(nhapngayve.getText().toString());
        mDatabase.child("Tours/"+n).child("Gia").setValue(nhapgia.getText().toString());

    }

    public void writedata(String diemdi,String Noio) {

      mDatabase = FirebaseDatabase.getInstance().getReference();

      mDatabase.child("Tours/1").child(Noio).setValue(diemdi);
    }
    public void onClickReadData(){
        btnxemds.setText("Xemds");

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        databaseReference.child("Tours/1/Diemdi").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
              String diemdi = snapshot.getValue(String.class);
              Didi = diemdi;
                Diemdi.setText(Didi);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("", "loadPost:onCancelled", error.toException());
            }
        });
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        databaseReference.child("Tours/1/Diemden").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String diemden = snapshot.getValue(String.class);
                Dide = diemden;
                Diemden.setText(Dide);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("", "loadPost:onCancelled", error.toException());
            }
        });
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        databaseReference.child("Tours/1/Ngaydi").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String ngaydi = snapshot.getValue(String.class);
                Ngdi = ngaydi;
                Ngaydi.setText(Ngdi);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("", "loadPost:onCancelled", error.toException());
            }
        });
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        databaseReference.child("Tours/1/Ngayve").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String ngayve = snapshot.getValue(String.class);
                Ngve = ngayve;
                Ngayve.setText(Ngve);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("", "loadPost:onCancelled", error.toException());
            }
        });
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        databaseReference.child("Tours/1/Gia").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String gia = snapshot.getValue(String.class);
                Gi= gia;
                Gia.setText(Gi);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("", "loadPost:onCancelled", error.toException());
            }
        });
        Listdata = new ArrayList<>();
        Listdata.add(new data("Giá :"+Gi,"Điểm đi:"+Didi,"Điểm đến:"+Dide,"Ngày đi:"+Ngdi,"Ngày về :"+ Ngve));

    }



}