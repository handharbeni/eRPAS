package io.github.handharbeni.erpas.apis.responses.WP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataRealisasi implements Serializable {
	@SerializedName("tahun")
	@Expose
	private String tahun;
	@SerializedName("bulan")
	@Expose
	private String bulan;
	@SerializedName("total_bayar")
	@Expose
	private String totalBayar;

	public String getTahun() {
		return tahun;
	}

	public void setTahun(String tahun) {
		this.tahun = tahun;
	}

	public String getBulan() {
		return bulan;
	}

	public void setBulan(String bulan) {
		this.bulan = bulan;
	}

	public String getTotalBayar() {
		return totalBayar;
	}

	public void setTotalBayar(String totalBayar) {
		this.totalBayar = totalBayar;
	}
}
