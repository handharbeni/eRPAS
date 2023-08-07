package io.github.handharbeni.erpas.apis.responses.WP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataTagihan implements Serializable {

	@SerializedName("kd_rekening")
	@Expose
	private String kdRekening;
	@SerializedName("bln_retribusi")
	@Expose
	private String blnRetribusi;
	@SerializedName("thn_retribusi")
	@Expose
	private String thnRetribusi;
	@SerializedName("status_ketetapan")
	@Expose
	private String statusKetetapan;
	@SerializedName("status_bayar")
	@Expose
	private String statusBayar;
	@SerializedName("status_lunas")
	@Expose
	private String statusLunas;
	@SerializedName("total_retribusi")
	@Expose
	private String totalRetribusi;

	public String getKdRekening() {
		return kdRekening;
	}

	public void setKdRekening(String kdRekening) {
		this.kdRekening = kdRekening;
	}

	public String getBlnRetribusi() {
		return blnRetribusi;
	}

	public void setBlnRetribusi(String blnRetribusi) {
		this.blnRetribusi = blnRetribusi;
	}

	public String getThnRetribusi() {
		return thnRetribusi;
	}

	public void setThnRetribusi(String thnRetribusi) {
		this.thnRetribusi = thnRetribusi;
	}

	public String getStatusKetetapan() {
		return statusKetetapan;
	}

	public void setStatusKetetapan(String statusKetetapan) {
		this.statusKetetapan = statusKetetapan;
	}

	public String getStatusBayar() {
		return statusBayar;
	}

	public void setStatusBayar(String statusBayar) {
		this.statusBayar = statusBayar;
	}

	public String getStatusLunas() {
		return statusLunas;
	}

	public void setStatusLunas(String statusLunas) {
		this.statusLunas = statusLunas;
	}

	public String getTotalRetribusi() {
		return totalRetribusi;
	}

	public void setTotalRetribusi(String totalRetribusi) {
		this.totalRetribusi = totalRetribusi;
	}

}