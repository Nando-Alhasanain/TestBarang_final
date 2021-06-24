package com.example.testbarang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testbarang.Barang;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TemanEdit extends AppCompatActivity {
    TextView tv_key;
    EditText ed_Nama, ed_kode;
    Button btnEdit;
    DatabaseReference databaseReference;
    String code,name,kode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teman_edit);

        tv_key = findViewById(R.id.tv_key);
        ed_Nama = findViewById(R.id.edNama);
        ed_kode = findViewById(R.id.edKode);
        btnEdit = findViewById(R.id.btEdit);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        Bundle bundle = this.getIntent().getExtras();
        code = bundle.getString("Kunci1");
        name = bundle.getString("Kunci2");
        kode = bundle.getString("Kunci3");

        tv_key.setText(code);
        ed_Nama.setText(name);
        ed_kode.setText(kode);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editBarang(new Barang(ed_kode.getText().toString(),
                        ed_Nama.getText().toString()));
            }
        });
    }
    public void editBarang(Barang barang){
        databaseReference.child("Barang")
                .child(code).setValue(barang).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(TemanEdit.this,"Data berhasil diupdate",Toast.LENGTH_LONG).show();
            }
        });
    }
}