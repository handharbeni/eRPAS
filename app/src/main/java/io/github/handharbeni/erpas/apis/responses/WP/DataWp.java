package io.github.handharbeni.erpas.apis.responses.WP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataWp implements Serializable {

	@SerializedName("npwrd")
	@Expose
	private String npwrd;
	@SerializedName("nm_wp_wr")
	@Expose
	private String nmWpWr;
	@SerializedName("alamat_wp_wr")
	@Expose
	private String alamatWpWr;
	@SerializedName("kelurahan")
	@Expose
	private String kelurahan;
	@SerializedName("kecamatan")
	@Expose
	private String kecamatan;
	@SerializedName("kota")
	@Expose
	private String kota;
	@SerializedName("nominal_pengenaan")
	@Expose
	private String nominal;

	public String getNpwrd() {
		return npwrd;
	}

	public void setNpwrd(String npwrd) {
		this.npwrd = npwrd;
	}

	public String getNmWpWr() {
		return nmWpWr;
	}

	public void setNmWpWr(String nmWpWr) {
		this.nmWpWr = nmWpWr;
	}

	public String getAlamatWpWr() {
		return alamatWpWr;
	}

	public void setAlamatWpWr(String alamatWpWr) {
		this.alamatWpWr = alamatWpWr;
	}

	public String getKelurahan() {
		return kelurahan;
	}

	public void setKelurahan(String kelurahan) {
		this.kelurahan = kelurahan;
	}

	public String getKecamatan() {
		return kecamatan;
	}

	public void setKecamatan(String kecamatan) {
		this.kecamatan = kecamatan;
	}

	public String getKota() {
		return kota;
	}

	public void setKota(String kota) {
		this.kota = kota;
	}

	public String getNominal() {
		return nominal;
	}

	public void setNominal(String nominal) {
		this.nominal = nominal;
	}
}