package com.example.lr_29.notificationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {

        //Obtener el id y el mensaje desde el intent.
        int notificationId=intent.getIntExtra("notidicationId",0);
        String message=intent.getStringExtra("todo");

        //Cuando la notificacion es presionada, llama MainActivity.
        Intent mainIntent=new Intent(context, MainActivity.class);
        PendingIntent contentIntent= PendingIntent.getActivity(context,0,mainIntent,0);

        NotificationManager myNotificationManager=
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        //Preparar la notificacion.

        Notification.Builder builder=new Notification.Builder(context);
        builder.setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Reporte Venta!")
                .setContentText(message)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentIntent(contentIntent);

        // Notificacion
        myNotificationManager.notify(notificationId,builder.build());


















    }
}
































