package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DialListAdapter extends ArrayAdapter<Member> {
    @NonNull
    Activity context;
    int resource;
    @NonNull
    List<Member> objects;
    ArrayList<Member> choosens=new ArrayList<>();

    public DialListAdapter(@NonNull Activity context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource, null);
        TextView txtName = row.findViewById(R.id.txtName);
        final TextView txtPhone = row.findViewById(R.id.txtPhone);
        ImageButton btnCall = row.findViewById(R.id.btnCall);
        ImageButton btnSend = row.findViewById(R.id.btnSend);
        ImageButton btnDelete = row.findViewById(R.id.btnDelete);
        CheckBox ckChoose = row.findViewById(R.id.ckChoose);
       //  ArrayList<Member> choosens=new ArrayList<>();


        final Member member = this.objects.get(position);
        txtName.setText(member.getName());
        txtPhone.setText(member.getPhone());

        if(ckChoose.isChecked())
        {
            member.setChecked(true);
            choosens.add(member);
        }




        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objects.remove(position);
                notifyDataSetChanged();
            }
        });



        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("tel:" + txtPhone.getText().toString());
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(uri);
                checkAndRequestPermissions();

                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }

                context.startActivity(intent);

            }


        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               xuLyNhaTin();

            }
        });
        return row;




    }

    private void xuLyNhaTin() {
        Intent sendSMG=new Intent(context,MessageScreen.class);
        Bundle bundle=new Bundle();
        boolean test=true;
        if(choosens.isEmpty())
            test=false; 
        bundle.putSerializable("choosens",(Serializable) choosens);
        bundle.putBoolean("test",test);
        sendSMG.putExtra("bundle",bundle);
        this.context.startActivity(sendSMG);
    }


    private void checkAndRequestPermissions() {
        String[] permissions= new String[]{
                Manifest.permission.CALL_PHONE,
                Manifest.permission.SEND_SMS
        };
        ArrayList<String> listPermissionsNeeded= new ArrayList<String>();
        for(String permission: permissions)
        {
            if(ContextCompat.checkSelfPermission(this.context,permission)!= PackageManager.PERMISSION_GRANTED)
            {
                    listPermissionsNeeded.add(permission);
            }
        }
        if(!listPermissionsNeeded.isEmpty())
        {
            ActivityCompat.requestPermissions(this.context,listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),1);
        }
    }


}
