package info.mizoguche.simplenotification;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.unity3d.player.UnityPlayer;

public class SimpleNotificationService {
    public static final String EXTRA_KEY_TITLE = "title";
    public static final String EXTRA_KEY_BODY = "body";

    public static void notify(String title, String body) {
        Activity activity = UnityPlayer.currentActivity;
        Context context = activity.getApplicationContext();
        int id =(int)(Math.random() * 10000.0f) + 1;
        Intent intent2 = new Intent();
        intent2.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent2,
                PendingIntent.FLAG_UPDATE_CURRENT);

        final PackageManager pm=context.getPackageManager();
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = pm.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return;
        }
        final int appIconResId = applicationInfo.icon;
        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), appIconResId);

        // NotificationBuilderを作成
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentIntent(contentIntent);
        builder.setTicker(title);
        builder.setSmallIcon(appIconResId);
        builder.setContentTitle(title);
        builder.setContentText(body);
        builder.setLargeIcon(largeIcon);
        builder.setWhen(System.currentTimeMillis());
        builder.setDefaults(Notification.DEFAULT_SOUND
                | Notification.DEFAULT_VIBRATE
                | Notification.DEFAULT_LIGHTS);
        builder.setAutoCancel(true);

        NotificationManager manager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(id, builder.build());
    }

    public static void scheduleNotification(long time, String title, String body){
        Context context = UnityPlayer.currentActivity.getApplicationContext();
        int id = (int) System.currentTimeMillis();

        Intent intent = new Intent(context, TimeAlarm.class);
        intent.putExtra(EXTRA_KEY_TITLE, title);
        intent.putExtra(EXTRA_KEY_BODY, body);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);
    }

    public static void cancelAllNotifications() {
        Context context = UnityPlayer.currentActivity.getApplicationContext();
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancelAll();
    }
}
