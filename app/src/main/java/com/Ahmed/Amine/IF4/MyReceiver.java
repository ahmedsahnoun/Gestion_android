package com.Ahmed.Amine.IF4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyReceiver extends BroadcastReceiver {

    static String dateRecente = "";

    @Override
    public void onReceive(Context context, Intent intent) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());

        if(intent.hasExtra("dateRecente"))
            dateRecente = intent.getExtras().getString("dateRecente");

        if(dateRecente.compareTo(date) > 0)
            Toast.makeText(context,"date non valide: "+dateRecente,Toast.LENGTH_LONG).show();
    }
}
