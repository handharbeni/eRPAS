package io.github.handharbeni.erpas.ui.wp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.github.handharbeni.erpas.apis.responses.WP.DataSkrd;
import io.github.handharbeni.erpas.databinding.FragmentSkrdItemBinding;

public class DetailWpSkrdAdapter extends RecyclerView.Adapter<DetailWpSkrdAdapter.ViewHolder>{
	private Context context;
	private List<DataSkrd> listSkrd;
	private SkrdCallback skrdCallback;

	public DetailWpSkrdAdapter(
			Context context, List<DataSkrd> listSkrd, SkrdCallback skrdCallback
	) {
		this.context = context;
		this.listSkrd = listSkrd;
		this.skrdCallback = skrdCallback;
	}

	@NonNull
	@Override
	public DetailWpSkrdAdapter.ViewHolder onCreateViewHolder(
			@NonNull ViewGroup parent, int viewType
	) {
		FragmentSkrdItemBinding binding = FragmentSkrdItemBinding.inflate(LayoutInflater.from(parent.getContext()));
		CardView.LayoutParams lp =
				new CardView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		lp.setMargins(10, 10, 10, 10);
		binding.getRoot().setLayoutParams(lp);

		return new ViewHolder(binding);
	}

	@Override
	public void onBindViewHolder(@NonNull DetailWpSkrdAdapter.ViewHolder holder, int position) {
		DataSkrd dataSkrd = listSkrd.get(position);
		holder.bind(dataSkrd);

		holder.itemView.setOnClickListener(v -> skrdCallback.onItemClick(dataSkrd));
	}

	@Override
	public int getItemCount() {
		return listSkrd.size();
	}

	public void updateData(List<DataSkrd> listSkrd){
		this.listSkrd.clear();
		this.listSkrd = listSkrd;
		notifyDataSetChanged();;
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		FragmentSkrdItemBinding binding;
		public ViewHolder(@NonNull FragmentSkrdItemBinding itemView) {
			super(itemView.getRoot());

			binding = itemView;
		}

		void bind(DataSkrd dataSkrd) {
			binding.itemKdRekening.setText(String.format("Kode Rekening: %s", dataSkrd.getKdRekening()));
			binding.itemBulanTahun.setText(String.format("%s/%s", dataSkrd.getBlnRetribusi(), dataSkrd.getThnRetribusi()));
			binding.itemStatusKetetapan.setText(String.format(
					"Status Ketetapan: %s",
					dataSkrd.getStatusKetetapan()
					        .equalsIgnoreCase("0") ?
							"Belum Tetap" : "Tetap"
			));
			binding.itemStatusBayar.setText(String.format(
					"Status Bayar: %s",
					dataSkrd.getStatusBayar()
					        .equalsIgnoreCase("0") ?
							"Belum Bayar" : "Sudah Bayar"
			));
			binding.itemStatusLunas.setText(String.format(
					"Status Lunas: %s",
					dataSkrd.getStatusLunas()
					        .equalsIgnoreCase("0") ?
							"Belum Lunas" : "Lunas"
			));
			binding.itemTotalBayar.setText(String.format("Total Bayar: %s", dataSkrd.getTotalRetribusi()));
		}
	}

	public interface SkrdCallback{
		void onItemClick(DataSkrd dataSkrd);
	}
}
