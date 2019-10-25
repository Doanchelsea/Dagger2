package com.example.dagger2_api_login.api;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.example.dagger2_api_login.R;
import com.example.dagger2_api_login.contract.AppConstants;
import com.example.dagger2_api_login.data.eventbus.CanEvent;
import com.example.dagger2_api_login.data.eventbus.NewEvent;
import com.example.dagger2_api_login.ui.main.activity.MainActivity;
import com.example.dagger2_api_login.untils.StringUtils;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

/**
 * doannd
 * <p>
 * handle receive data from firebase cloud messaging
 * <p>_</p>
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Map<String, String> data = remoteMessage.getData();
        Logger.d("data: %s", data);
        if (data == null) {
            return;
        }

        Logger.d("data: %s", data);
        String tripId = data.get(AppConstants.KEY_FIREBASE_SERVICE_TRIP_ID);
        String code = data.get(AppConstants.KEY_FIREBASE_SERVICE_NOTIFICATION_CODE);
        String content = data.get(AppConstants.KEY_FIREBASE_SERVICE_NOTIFICATION_CONTENT);
        Logger.d("tripId: %s", tripId);
        Logger.d("code: %s", code);
        Logger.d("content: %s", content);

        if (StringUtils.isEmpty(tripId)) {
            return;
        }

        if (StringUtils.isEmpty(code)) {
            return;
        }

        if (StringUtils.isEmpty(content)) {
            return;
        }

        if (code.equals(AppConstants.COMMON_TRIP_CODE_KO_TIM_THAY_CHUYEN_NOTIFICATION)) {

        } else if (code.equals(AppConstants.COMMON_TRIP_CODE_NHAN_CHUYEN_NOTIFICATION)) {
            EventBus.getDefault().post(new NewEvent(tripId));
            showNotification(content);
        }else if (code.equals(AppConstants.COMMON_TRIP_CODE_HUY_CHUYEN_NOTIFICATION)){
            EventBus.getDefault().post(new CanEvent());
            showNotification(content);
        }

    }

    private void showNotification(String message) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        String channelId = getString(R.string.project_id);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.ic_sao_vang)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_sao_vang))
                        .setContentTitle(getString(R.string.app_name))
                        .setContentText(message)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setPriority(NotificationManager.IMPORTANCE_HIGH);
//                        .addAction(new NotificationCompat.Action(
//                                android.R.drawable.sym_call_missed,
//                                "Cancel",
//                                PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)))
//                        .addAction(new NotificationCompat.Action(
//                                android.R.drawable.sym_call_outgoing,
//                                "OK",
//                                PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)));

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);

            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0, notificationBuilder.build());
    }

}
