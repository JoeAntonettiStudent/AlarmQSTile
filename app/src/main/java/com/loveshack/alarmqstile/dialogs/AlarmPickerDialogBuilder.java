package com.loveshack.alarmqstile.dialogs;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.provider.AlarmClock;
import android.provider.Settings;
import android.service.quicksettings.Tile;
import android.widget.TimePicker;

/**
 * Created by joe on 8/2/16.
 */
public class AlarmPickerDialogBuilder implements IAlarmDialogBuilder {
    @Override
    public Dialog makeDialog(final Context context, final Tile tile) {
        return new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                Intent alarmIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
                alarmIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                alarmIntent.putExtra(AlarmClock.EXTRA_MESSAGE, "Some message!");
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.MINUTE, 1);
                alarmIntent.putExtra(AlarmClock.EXTRA_HOUR, selectedHour);
                alarmIntent.putExtra(AlarmClock.EXTRA_MINUTES, selectedMinute);
                alarmIntent.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
                context.startActivity(alarmIntent);
                tile.setLabel(Settings.System.getString(context.getContentResolver(), Settings.System.NEXT_ALARM_FORMATTED));
                tile.updateTile();
            }
        }, 0, 0, false);
    }
}
