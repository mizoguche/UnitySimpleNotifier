package info.mizoguche.simplenotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TimeAlarm extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        String title = intent.getStringExtra(SimpleNotificationService.EXTRA_KEY_TITLE);
        String body  = intent.getStringExtra(SimpleNotificationService.EXTRA_KEY_BODY);

        SimpleNotificationService.notify(title, body);
    }
}
