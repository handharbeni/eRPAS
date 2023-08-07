package io.github.handharbeni.erpas.apis.responses.WP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataSkrd implements Serializable {
	@SerializedName("id_skrd")
	@Expose
	private String idSkrd;
	@SerializedName("thn_retribusi")
	@Expose
	private String thnRetribusi;
	@SerializedName("bln_retribusi")
	@Expose
	private String blnRetribusi;
	@SerializedName("npwrd")
	@Expose
	private String npwrd;
	@SerializedName("wp_wr_nama")
	@Expose
	private String wpWrNama;
	@SerializedName("wp_wr_alamat")
	@Expose
	private String wpWrAlamat;
	@SerializedName("wp_wr_lurah")
	@Expose
	private String wpWrLurah;
	@SerializedName("wp_wr_camat")
	@Expose
	private String wpWrCamat;
	@SerializedName("wp_wr_kabupaten")
	@Expose
	private String wpWrKabupaten;
	@SerializedName("kd_rekening")
	@Expose
	private String kdRekening;
	@SerializedName("nm_rekening")
	@Expose
	private String nmRekening;
	@SerializedName("tgl_skrd")
	@Expose
	private String tglSkrd;
	@SerializedName("total_retribusi")
	@Expose
	private String totalRetribusi;
	@SerializedName("status_ketetapan")
	@Expose
	private String statusKetetapan;
	@SerializedName("status_bayar")
	@Expose
	private String statusBayar;
	@SerializedName("status_lunas")
	@Expose
	private String statusLunas;

	public String getIdSkrd() {
		return idSkrd;
	}

	public void setIdSkrd(String idSkrd) {
		this.idSkrd = idSkrd;
	}

	public String getThnRetribusi() {
		return thnRetribusi;
	}

	public void setThnRetribusi(String thnRetribusi) {
		this.thnRetribusi = thnRetribusi;
	}

	public String getBlnRetribusi() {
		return blnRetribusi;
	}

	public void setBlnRetribusi(String blnRetribusi) {
		this.blnRetribusi = blnRetribusi;
	}

	public String getNpwrd() {
		return npwrd;
	}

	public void setNpwrd(String npwrd) {
		this.npwrd = npwrd;
	}

	public String getWpWrNama() {
		return wpWrNama;
	}

	public void setWpWrNama(String wpWrNama) {
		this.wpWrNama = wpWrNama;
	}

	public String getWpWrAlamat() {
		return wpWrAlamat;
	}

	public void setWpWrAlamat(String wpWrAlamat) {
		this.wpWrAlamat = wpWrAlamat;
	}

	public String getWpWrLurah() {
		return wpWrLurah;
	}

	public void setWpWrLurah(String wpWrLurah) {
		this.wpWrLurah = wpWrLurah;
	}

	public String getWpWrCamat() {
		return wpWrCamat;
	}

	public void setWpWrCamat(String wpWrCamat) {
		this.wpWrCamat = wpWrCamat;
	}

	public String getWpWrKabupaten() {
		return wpWrKabupaten;
	}

	public void setWpWrKabupaten(String wpWrKabupaten) {
		this.wpWrKabupaten = wpWrKabupaten;
	}

	public String getKdRekening() {
		return kdRekening;
	}

	public void setKdRekening(String kdRekening) {
		this.kdRekening = kdRekening;
	}

	public String getNmRekening() {
		return nmRekening;
	}

	public void setNmRekening(String nmRekening) {
		this.nmRekening = nmRekening;
	}

	public String getTglSkrd() {
		return tglSkrd;
	}

	public void setTglSkrd(String tglSkrd) {
		this.tglSkrd = tglSkrd;
	}

	public String getTotalRetribusi() {
		return totalRetribusi;
	}

	public void setTotalRetribusi(String totalRetribusi) {
		this.totalRetribusi = totalRetribusi;
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
}
