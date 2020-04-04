package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText txtEnterName,txtEnterPhone;
    Button btnSave;

    ListView lvSave;
    ArrayList<Member> dsMb=new ArrayList<Member>();
    DialListAdapter dialListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyLuuDanhBa();
            }
        });
    }

    private void xuLyLuuDanhBa() {
        Member member= new Member(txtEnterName.getText().toString(),txtEnterPhone.getText().toString(),false);
        dsMb.add(member);
        dialListAdapter.notifyDataSetChanged();

    }

    private void addControls() {
        txtEnterName=findViewById(R.id.txtEnterName);
        txtEnterPhone=findViewById(R.id.txtEnterPhone);
        btnSave=findViewById(R.id.btnSave);


        lvSave=findViewById(R.id.lvSave);
        dialListAdapter= new DialListAdapter(MainActivity.this,
                R.layout.items,dsMb);
        lvSave.setAdapter(dialListAdapter);



    }
}
