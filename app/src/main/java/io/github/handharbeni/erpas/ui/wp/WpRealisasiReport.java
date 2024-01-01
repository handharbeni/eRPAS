package io.github.handharbeni.erpas.ui.wp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

import io.github.handharbeni.erpas.apis.responses.WP.DataRealisasi;
import io.github.handharbeni.erpas.apis.responses.WP.LaporanRealisasi;
import io.github.handharbeni.erpas.apis.responses.WP.ListResponseSkrd;
import io.github.handharbeni.erpas.apis.responses.WP.PaymentStatus;
import io.github.handharbeni.erpas.apis.responses.WP.ResponseWp;
import io.github.handharbeni.erpas.cores.BaseFragment;
import io.github.handharbeni.erpas.databinding.FragmentRealisasiReportBinding;
import io.github.handharbeni.erpas.utils.UtilDate;

public class WpRealisasiReport extends BaseFragment implements WpModelView.WpCallback {
	FragmentRealisasiReportBinding binding;
	View view;

	WpModelView wpModelView;
	List<Entry> listData = new ArrayList<>();

	@Nullable
	@Override
	public View onCreateView(
			@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState
	) {
		wpModelView = new ViewModelProvider(this).get(WpModelView.class);
		wpModelView.setupClient(requireContext(), this);

		binding = FragmentRealisasiReportBinding.inflate(inflater, container, false);
		view = binding.getRoot();

		return view;
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		wpModelView.realisasiReport();
	}

	@Override
	public void onLoad() {
		showLoading();
	}

	@Override
	public void onSuccess(ResponseWp responseWp) {
		doneLoading();
	}

	@Override
	public void onPaymentSuccess(PaymentStatus paymentStatus) {
		doneLoading();
	}

	@Override
	public void onSuccessTutup() {
		doneLoading();
	}

	@Override
	public void onSuccessChangePassword() {

	}

	@Override
	public void onSkrdSuccess(ListResponseSkrd listResponseSkrd) {
		doneLoading();
	}

	@Override
	public void onRealisasiSuccess(LaporanRealisasi laporanRealisasi) {
		doneLoading();
		setupChart(laporanRealisasi);
	}

	@Override
	public void onSuccessQris(String qris) {
		doneLoading();
	}

	@Override
	public void onFailed(String message) {
		doneLoading();
	}

	public void setupDataChart(LaporanRealisasi laporanRealisasi) {
		listData.clear();

		int i = 0;

		for (DataRealisasi dataRealisasi : laporanRealisasi.getDataRealisasi()) {
			long lDate = UtilDate.dateToLong(dataRealisasi.getBulan()+"/"+dataRealisasi.getTahun(), "MM/yyyy");
			Entry entry = new Entry();
			entry.setX(i);
			entry.setY(Float.parseFloat(dataRealisasi.getTotalBayar()));
			listData.add(entry);
			i++;
		}

	}

	public LineDataSet setupDataSet(LaporanRealisasi laporanRealisasi) {
		setupDataChart(laporanRealisasi);
		LineDataSet lineDataSet = new LineDataSet(listData, "Realisasi");
		lineDataSet.setLineWidth(5f);
		lineDataSet.setDrawCircles(true);
		lineDataSet.setDrawCircleHole(true);
		lineDataSet.setDrawFilled(true);
		lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
		lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
		lineDataSet.setValueTextSize(12f);
		return lineDataSet;
	}

	public void setupChart(LaporanRealisasi laporanRealisasi) {
		List<String> listM = listMonth(laporanRealisasi);

		List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
		dataSets.add(setupDataSet(laporanRealisasi));

		LineData lineData = new LineData(dataSets);
		binding.lcRealisasi.setData(lineData);
		binding.lcRealisasi.getAxisRight().setEnabled(false);
		binding.lcRealisasi.getAxisLeft().setEnabled(false);
		binding.lcRealisasi.setDrawGridBackground(false);
		binding.lcRealisasi.setDrawBorders(false);
		binding.lcRealisasi.setExtraOffsets(30f, 0f, 30f, 0f);
		binding.lcRealisasi.getDescription().setEnabled(false);
		binding.lcRealisasi.invalidate();

		ValueFormatter valueFormatter = new ValueFormatter() {
			@Override
			public String getAxisLabel(float value, AxisBase axis) {
				return listM.get((int) value);
			}
		};
		XAxis xAxis = binding.lcRealisasi.getXAxis();
		xAxis.setGranularity(1f);
		xAxis.setValueFormatter(valueFormatter);
	}

	public List<String> listMonth(LaporanRealisasi laporanRealisasi) {
		List<String> listM = new ArrayList<>();
		for (DataRealisasi dataRealisasi : laporanRealisasi.getDataRealisasi()) {
			listM.add(dataRealisasi.getBulan()+"/"+dataRealisasi.getTahun());
		}
		return listM;
	}
}
