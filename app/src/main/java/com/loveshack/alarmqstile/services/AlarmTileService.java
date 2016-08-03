package com.loveshack.alarmqstile.services;

import android.provider.Settings;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

import com.loveshack.alarmqstile.dialogs.AlarmPickerDialogBuilder;
import com.loveshack.alarmqstile.dialogs.IAlarmDialogBuilder;

/**
 * Created by joe on 8/2/16.
 */
public class AlarmTileService extends TileService {

    private IAlarmDialogBuilder alarmDialogBuilder;

    @Override
    public void onStartListening(){
        super.onStartListening();
        String nextAlarm = Settings.System.getString(getContentResolver(),
                Settings.System.NEXT_ALARM_FORMATTED);
        if(nextAlarm.isEmpty()){
            getQsTile().setLabel("No Alarm Set");
            getQsTile().setState(Tile.STATE_INACTIVE);
        }else{
            getQsTile().setLabel(nextAlarm);
            getQsTile().setState(Tile.STATE_ACTIVE);
        }
        getQsTile().updateTile();
    }

    @Override
    public void onClick(){
        super.onClick();
        alarmDialogBuilder = new AlarmPickerDialogBuilder();
        showDialog(alarmDialogBuilder.makeDialog(this, getQsTile()));
    }
}
