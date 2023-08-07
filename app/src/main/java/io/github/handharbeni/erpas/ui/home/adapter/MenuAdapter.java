package io.github.handharbeni.erpas.ui.home.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.HashMap;

import io.github.handharbeni.erpas.databinding.FragmentHomeMenuItemBinding;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder>{
	Context context;
	HashMap<String, Drawable> listMenu;
	MenuCallback menuCallback;

	public MenuAdapter(
			Context context, HashMap<String, Drawable> listMenu, MenuCallback menuCallback
	) {
		this.context = context;
		this.listMenu = listMenu;
		this.menuCallback = menuCallback;
	}

	@NonNull
	@Override
	public MenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		FragmentHomeMenuItemBinding binding = FragmentHomeMenuItemBinding.inflate(LayoutInflater.from(parent.getContext()));
		CardView.LayoutParams lp =
				new CardView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		lp.setMargins(10, 10, 10, 10);
		binding.getRoot().setLayoutParams(lp);

		return new ViewHolder(binding);
	}

	@Override
	public void onBindViewHolder(@NonNull MenuAdapter.ViewHolder holder, int position) {
		String title = (String) listMenu.keySet().toArray()[position];
		Drawable image = listMenu.get(title);

		holder.bindData(title, image);
		holder.itemView.setOnClickListener(view -> {
			menuCallback.onItemClick(title);
		});
	}

	@Override
	public int getItemCount() {
		return listMenu.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		FragmentHomeMenuItemBinding binding;
		public ViewHolder(FragmentHomeMenuItemBinding itemView) {
			super(itemView.getRoot());
			binding = itemView;
		}

		public void bindData(String title, Drawable image) {
			binding.itemTitle.setText(title);
			Glide.with(context).load(image).into(binding.itemImage);
		}
	}

	public void updateData(HashMap<String, Drawable> listMenu) {
		this.listMenu.clear();
		this.listMenu = listMenu;
		notifyDataSetChanged();
	}

	public interface MenuCallback{
		void onItemClick(String menu);
	}
}
