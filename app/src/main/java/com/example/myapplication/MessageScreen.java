package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.MemoryFile;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MessageScreen extends AppCompatActivity {
    EditText txtDetail;
    Button btnSendMes;
    ArrayList<Member> choosens=new ArrayList<>();
    boolean test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_screen);

        addControls();
        addEvents();

    }

    private void addEvents() {
        btnSendMes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyNhanTin();
            }
        });
    }

    private void xuLyNhanTin() {
        checkAndRequestPermission();
        final SmsManager sms = SmsManager.getDefault();
        Intent smgSent = new Intent("ACTION_SMG_SENT");

        final PendingIntent pendingMsgSent = PendingIntent.getBroadcast(this, 0, smgSent, 0);


        registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                int result = getResultCode();
                String msg = "Send OK";
                if (result != Activity.RESULT_OK) {
                    msg = "Send failed";
                }
                Toast.makeText(MessageScreen.this, msg, Toast.LENGTH_SHORT).show();

            }
        }, new IntentFilter("ACTION_MSG_SENT"));
        if(test==false)
            Toast.makeText(MessageScreen.this, "Ben kia deo co gi", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MessageScreen.this, "Ben kia  co day ben nay sai", Toast.LENGTH_SHORT).show();
//        if(choosens.isEmpty())
//            Toast.makeText(MessageScreen.this, "Bennay deo co gi", Toast.LENGTH_SHORT).show();
//        else {
//            for (Member mb : choosens) {
//                sms.sendTextMessage(mb.getPhone().toString(), null, txtDetail.getText().toString(), pendingMsgSent, null);
//            }
//        }



        //finish();
    }

    private void checkAndRequestPermission() {
        String[] permissions= new String[]{
                Manifest.permission.CALL_PHONE,
                Manifest.permission.SEND_SMS,
                Manifest.permission.READ_SMS
        };
        ArrayList<String> listPermissionsNeeded= new ArrayList<String>();
        for(String permission: permissions)
        {
            if(ContextCompat.checkSelfPermission(this,permission)!= PackageManager.PERMISSION_GRANTED)
            {
                listPermissionsNeeded.add(permission);
            }
        }
        if(!listPermissionsNeeded.isEmpty())
        {
            ActivityCompat.requestPermissions(this,listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),1);
        }
    }

    private void addControls() {
        txtDetail=findViewById(R.id.txtDetail);
        btnSendMes=findViewById(R.id.btnSendMes);
        Bundle bundle=new Bundle();

        Intent intent=getIntent();
        bundle=intent.getBundleExtra("bundle");
        choosens=(ArrayList<Member>) bundle.getSerializable("choosens");
        test=bundle.getBoolean("test");

    }
}
