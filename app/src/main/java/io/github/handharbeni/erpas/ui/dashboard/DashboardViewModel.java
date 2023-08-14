package io.github.handharbeni.erpas.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.github.handharbeni.erpas.cores.BaseModelView;

public class DashboardViewModel extends BaseModelView {

	private final MutableLiveData<String> mText;

	public DashboardViewModel() {
		mText = new MutableLiveData<>();
		mText.setValue("This is dashboard fragment");
	}

	public LiveData<String> getText() {
		return mText;
	}
}