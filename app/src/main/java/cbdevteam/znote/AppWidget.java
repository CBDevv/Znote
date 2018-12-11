package cbdevteam.znote;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.Random;

public class AppWidget extends AppWidgetProvider {
    // String array to show text at textview on refresh
    private static final String widgetText = "";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {

            String finalWidgetText = widgetText;

            // Create an Intent to launch MainActivity
            Intent intent = new Intent(context, TransparentActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,
                    0, intent, 0);

            // Create an Intent to Refresh MyWidgetProvider
            Intent intent1 = new Intent(context, AppWidget.class);
            intent1.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent1.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
            PendingIntent pendingIntent1 = PendingIntent.getBroadcast(context,
                    0, intent1, 0);

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.new_widget2);

            //set the text from the transparent activity to the widget textView ** NOT WORKING **
            remoteViews.setTextViewText(R.id.fullscreen_content, finalWidgetText);

            remoteViews.setOnClickPendingIntent(R.id.edit_widget_text, pendingIntent1);

            //set the pending intent to open Transparent activity edit text
            remoteViews.setOnClickPendingIntent(R.id.edit_widget_text, pendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);

        }
    }
}
