package io.github.handharbeni.erpas.cores;

import android.util.Log;

import androidx.lifecycle.ViewModel;

public class BaseModelView extends ViewModel {

	void showLog(String TAG, String message){
		Log.d(TAG, "showLog: "+message);
	}
}
