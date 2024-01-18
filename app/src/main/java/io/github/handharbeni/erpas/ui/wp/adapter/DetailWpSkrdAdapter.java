package io.github.handharbeni.erpas.ui.wp.adapter;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.github.handharbeni.erpas.apis.responses.WP.DataSkrd;
import io.github.handharbeni.erpas.databinding.FragmentSkrdItemBinding;

public class DetailWpSkrdAdapter extends RecyclerView.Adapter<DetailWpSkrdAdapter.ViewHolder>{
	private Context context;
	private List<DataSkrd> listSkrd;
	private List<DataSkrd> tempListSkrd;
	private SkrdCallback skrdCallback;

	public DetailWpSkrdAdapter(
			Context context, List<DataSkrd> listSkrd, SkrdCallback skrdCallback
	) {
		this.context = context;
		this.listSkrd = listSkrd;
		this.tempListSkrd = listSkrd;
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
		holder.binding.btnQris.setOnClickListener(v -> skrdCallback.onQrisClick(dataSkrd));
		holder.binding.btnPrint.setOnClickListener(v -> skrdCallback.onPrintClick(dataSkrd));
		holder.binding.btnCheckPayment.setOnClickListener(v -> skrdCallback.onPaymentCheck(dataSkrd));
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

	public void filterStatus(String status) {
//		List<DataSkrd> dataFilter = new ArrayList<>();
//		if (status.equalsIgnoreCase("-1")) {
//			// semua
//			this.listSkrd = this.tempListSkrd;
//		} else if (status.equalsIgnoreCase("0")) {
//			this.listSkrd = this.tempListSkrd;
//			for (DataSkrd dataSkrd : this.listSkrd) {
//				if (dataSkrd.getStatusLunas().equalsIgnoreCase("0")) {
//					dataFilter.add(dataSkrd);
//				}
//			}
//			this.listSkrd = dataFilter;
//		} else if (status.equalsIgnoreCase("1")) {
//			this.listSkrd = this.tempListSkrd;
//			for (DataSkrd dataSkrd : this.listSkrd) {
//				if (dataSkrd.getStatusLunas().equalsIgnoreCase("1")) {
//					dataFilter.add(dataSkrd);
//				}
//			}
//			this.listSkrd = dataFilter;
//		}
//
//		notifyDataSetChanged();
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

			binding.itemNamaNpwrd.setText(String.format("Nama Npwrd: %s", dataSkrd.getWpWrNama()));
			binding.itemNpwrd.setText(String.format("Npwrd: %s", dataSkrd.getNpwrd()));

			String colorRegex = "\\[(.*?)\\]";
			String valueRegex = "\\{(.*?)\\}";
			final String pattern = "<font color='[red]'>{This is a paragraph.}</font>";

			String colorKetetapan = pattern.replaceAll(colorRegex, dataSkrd.getStatusKetetapan().equalsIgnoreCase("0")?"#FF0000":"#008000");
			String valueKetetapan = colorKetetapan.replaceAll(valueRegex, dataSkrd.getStatusKetetapan().equalsIgnoreCase("0")?"Belum Tetap":"Tetap");
			String rawKetetapan = "Status Ketetapan: "+valueKetetapan;

			String colorStatusBayar = pattern.replaceAll(colorRegex, dataSkrd.getStatusBayar().equalsIgnoreCase("0")?"#FF0000":"#008000");
			String valueStatusBayar = colorStatusBayar.replaceAll(valueRegex, dataSkrd.getStatusBayar().equalsIgnoreCase("0")?"Belum Bayar":"Sudah Bayar");
			String rawStatusBayar = "Status Bayar: "+valueStatusBayar;

			String colorStatusLunas = pattern.replaceAll(colorRegex, dataSkrd.getStatusLunas().equalsIgnoreCase("0")?"#FF0000":"#008000");
			String valueStatusLunas = colorStatusLunas.replaceAll(valueRegex, dataSkrd.getStatusLunas().equalsIgnoreCase("0")?"Belum Lunas":"Lunas");
			String rawStatusLunas = "Status Lunas: "+valueStatusLunas;

			binding.itemStatusKetetapan.setText(Html.fromHtml(rawKetetapan, Html.FROM_HTML_MODE_LEGACY));
			binding.itemStatusBayar.setText(Html.fromHtml(rawStatusBayar, Html.FROM_HTML_MODE_COMPACT));
			binding.itemStatusLunas.setText(Html.fromHtml(rawStatusLunas, Html.FROM_HTML_MODE_COMPACT));

			binding.itemTotalBayar.setText(String.format("Total Bayar: %s", dataSkrd.getTotalRetribusi()));
			if (dataSkrd.getStatusBayar().equalsIgnoreCase("0")) {
				binding.btnPrint.setVisibility(View.GONE);
				binding.btnQris.setVisibility(View.VISIBLE);
			} else {
				binding.btnPrint.setVisibility(View.VISIBLE);
				binding.btnQris.setVisibility(View.GONE);
			}
		}
	}

	public interface SkrdCallback{
		void onItemClick(DataSkrd dataSkrd);
		void onQrisClick(DataSkrd dataSkrd);
		void onPrintClick(DataSkrd dataSkrd);
		void onPaymentCheck(DataSkrd dataSkrd);
	}
}
