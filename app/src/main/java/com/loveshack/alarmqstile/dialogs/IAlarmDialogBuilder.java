package com.loveshack.alarmqstile.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.service.quicksettings.Tile;

/**
 * Created by joe on 8/2/16.
 */
public interface IAlarmDialogBuilder {
    Dialog makeDialog(Context context, Tile tile);
}
