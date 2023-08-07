package io.github.handharbeni.erpas.ui.wp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.github.handharbeni.erpas.apis.responses.WP.DataTagihan;
import io.github.handharbeni.erpas.databinding.FragmentDetailWpItemBinding;

public class DetailWpAdapter extends RecyclerView.Adapter<DetailWpAdapter.ViewHolder> {
	private Context context;
	private List<DataTagihan> listTagihan;
	private DetailWpCallback detailWpCallback;

	public DetailWpAdapter(
			Context context, List<DataTagihan> listTagihan, DetailWpCallback detailWpCallback
	) {
		this.context = context;
		this.listTagihan = listTagihan;
		this.detailWpCallback = detailWpCallback;
	}

	@NonNull
	@Override
	public DetailWpAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

		FragmentDetailWpItemBinding
				binding = FragmentDetailWpItemBinding.inflate(LayoutInflater.from(parent.getContext()));
		CardView.LayoutParams lp =
				new CardView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		lp.setMargins(10, 10, 10, 10);
		binding.getRoot().setLayoutParams(lp);

		return new DetailWpAdapter.ViewHolder(binding);
	}

	@Override
	public void onBindViewHolder(@NonNull DetailWpAdapter.ViewHolder holder, int position) {
		DataTagihan dataTagihan = listTagihan.get(position);
		holder.bindData(dataTagihan);

		holder.binding.btnBayarCash.setOnClickListener(v -> detailWpCallback.onCashClick(dataTagihan));
		holder.binding.btnBayarQris.setOnClickListener(v -> detailWpCallback.onQrisClick(dataTagihan));
		holder.itemView.setOnClickListener(v -> detailWpCallback.onItemClick(dataTagihan));
	}

	@Override
	public int getItemCount() {
		return listTagihan.size();
	}

	public void updateData(List<DataTagihan> listTagihan){
		this.listTagihan.clear();
		this.listTagihan = listTagihan;
		notifyDataSetChanged();;
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		FragmentDetailWpItemBinding binding;
		public ViewHolder(FragmentDetailWpItemBinding itemView) {
			super(itemView.getRoot());
			binding = itemView;
		}

		void bindData(DataTagihan dataTagihan){
			binding.itemKdRekening.setText(dataTagihan.getKdRekening());
			binding.itemBulanTahun.setText(dataTagihan.getBlnRetribusi()+"/"+dataTagihan.getThnRetribusi());
			binding.itemStatusKetetapan.setText(dataTagihan.getStatusKetetapan());
			binding.itemStatusBayar.setText(dataTagihan.getStatusBayar());
			binding.itemStatusLunas.setText(dataTagihan.getStatusLunas());
			binding.itemTotalBayar.setText(dataTagihan.getTotalRetribusi());

			if (dataTagihan.getStatusBayar().toLowerCase().contains("belum") || dataTagihan.getStatusLunas().toLowerCase().contains("belum")) {
				binding.llButton.setVisibility(View.VISIBLE);
			} else {
				binding.llButton.setVisibility(View.GONE);
			}
		}
	}

	public interface DetailWpCallback {
		void onCashClick(DataTagihan dataTagihan);
		void onQrisClick(DataTagihan dataTagihan);
		void onItemClick(DataTagihan dataTagihan);
	}
}
