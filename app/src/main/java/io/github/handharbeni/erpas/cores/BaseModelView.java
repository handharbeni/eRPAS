package io.github.handharbeni.erpas.cores;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import io.github.handharbeni.erpas.utils.UtilDb;

public class BaseModelView extends ViewModel {
	protected UtilDb utilDb;

	public UtilDb getDb(Context context) {
		return utilDb = new UtilDb(context);
	}

	void showLog(String TAG, String message){
		Log.d(TAG, "showLog: "+message);
	}
}
