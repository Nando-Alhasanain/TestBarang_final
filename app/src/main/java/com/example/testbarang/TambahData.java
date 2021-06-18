package com.example.testbarang;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.testbarang.Barang;


public class TambahData extends AppCompatActivity {
    private DatabaseReference database;
    private Button btSubmit;
    private EditText etKode;
    private EditText etNama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);


        etKode = findViewById(R.id.editNo);
        etNama = findViewById(R.id.editNama);
        btSubmit = findViewById(R.id.btnOk);

        database = FirebaseDatabase.getInstance().getReference();

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( !(etKode.getText().toString().isEmpty()) && !(etNama.getText().toString().isEmpty()) )

                    submitBrg(new Barang(etKode.getText().toString(),etNama.getText().toString()));

                else
                    Toast.makeText(getApplicationContext(),"data tidak boleh kosong", Toast.LENGTH_LONG).show();

                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etKode.getWindowToken(),0);
            }
        });


    }
    private void submitBrg(Barang brg) {
        database.child("Barang").push().setValue(brg).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                etKode.setText("");
                etNama.setText("");
                Toast.makeText(getApplicationContext(),"data berhasih ditambahkan", Toast.LENGTH_LONG).show();

            }
        });
    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity,TambahData.class);
    }

}