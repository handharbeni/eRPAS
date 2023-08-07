package io.github.handharbeni.erpas.ui.home;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;

import io.github.handharbeni.erpas.R;

public class HomeViewModel extends ViewModel {
	private Context context;
	private final MutableLiveData<HashMap<String, Drawable>> listMenu;

	public HomeViewModel() {
		listMenu = new MutableLiveData<>();
	}

	public void setupData(Context context) {
		this.context = context;

		HashMap<String, Drawable> item = new HashMap<>();
		item.put("Pembayaran", AppCompatResources.getDrawable(context, R.drawable.ic_payment));
		item.put("Data Pasar", AppCompatResources.getDrawable(context, R.drawable.ic_market));
		item.put("SKRD", AppCompatResources.getDrawable(context, R.drawable.ic_skrd));
		item.put("Realisasi", AppCompatResources.getDrawable(context, R.drawable.ic_chart));

		listMenu.setValue(item);
	}

	public LiveData<HashMap<String, Drawable>> getMenu() {
		return listMenu;
	}
}