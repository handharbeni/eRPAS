package io.github.handharbeni.erpas.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.github.handharbeni.erpas.cores.BaseModelView;

public class NotificationsViewModel extends BaseModelView {

	private final MutableLiveData<String> mText;

	public NotificationsViewModel() {
		mText = new MutableLiveData<>();
		mText.setValue("This is notifications fragment");
	}

	public LiveData<String> getText() {
		return mText;
	}
}