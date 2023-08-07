package io.github.handharbeni.erpas.apis.responses.data;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataBphtb implements Serializable, Parcelable {
	@SerializedName("NOP")
	@Expose
	private String nop;
	@SerializedName("LUAS_TANAH")
	@Expose
	private String luasTanah;
	@SerializedName("LUAS_BNG")
	@Expose
	private String luasBng;
	@SerializedName("NM_PENJUAL")
	@Expose
	private String nmPenjual;
	@SerializedName("NM_PEMBELI")
	@Expose
	private String nmPembeli;
	@SerializedName("KD_BOOKING")
	@Expose
	private String kdBooking;
	@SerializedName("BPHTB_YG_HARUS_DIBAYAR")
	@Expose
	private String bphtbYgHarusDibayar;
	@SerializedName("STATUS_BOOKING")
	@Expose
	private String statusBooking;
	@SerializedName("STATUS_VALIDASI")
	@Expose
	private String statusValidasi;
	@SerializedName("STATUS_BAYAR")
	@Expose
	private String statusBayar;
	@SerializedName("STATUS_EXPIRED")
	@Expose
	private String statusExpired;
	@SerializedName("TGL_TRANSAKSI")
	@Expose
	private String tglTransaksi;
	@SerializedName("SSEGMEN7")
	@Expose
	private String ssegmen7;
	@SerializedName("STAMBAHANCATATAN")
	@Expose
	private String stambahancatatan;
	public final static Creator<DataBphtb> CREATOR = new Creator<DataBphtb>() {
		@SuppressWarnings({
				"unchecked"
		})
		public DataBphtb createFromParcel(android.os.Parcel in) {
			return new DataBphtb(in);
		}

		public DataBphtb[] newArray(int size) {
			return (new DataBphtb[size]);
		}

	};

	protected DataBphtb(android.os.Parcel in) {
		this.nop = ((String) in.readValue((String.class.getClassLoader())));
		this.luasTanah = ((String) in.readValue((String.class.getClassLoader())));
		this.luasBng = ((String) in.readValue((String.class.getClassLoader())));
		this.nmPenjual = ((String) in.readValue((String.class.getClassLoader())));
		this.nmPembeli = ((String) in.readValue((String.class.getClassLoader())));
		this.kdBooking = ((String) in.readValue((String.class.getClassLoader())));
		this.bphtbYgHarusDibayar = ((String) in.readValue((String.class.getClassLoader())));
		this.statusBooking = ((String) in.readValue((String.class.getClassLoader())));
		this.statusValidasi = ((String) in.readValue((String.class.getClassLoader())));
		this.statusBayar = ((String) in.readValue((String.class.getClassLoader())));
		this.statusExpired = ((String) in.readValue((String.class.getClassLoader())));
		this.tglTransaksi = ((String) in.readValue((String.class.getClassLoader())));
		this.ssegmen7 = ((String) in.readValue((String.class.getClassLoader())));
		this.stambahancatatan = ((String) in.readValue((String.class.getClassLoader())));
	}

	public DataBphtb() {
	}

	public String getNop() {
		return nop;
	}

	public void setNop(String nop) {
		this.nop = nop;
	}

	public String getLuasTanah() {
		return luasTanah;
	}

	public void setLuasTanah(String luasTanah) {
		this.luasTanah = luasTanah;
	}

	public String getLuasBng() {
		return luasBng;
	}

	public void setLuasBng(String luasBng) {
		this.luasBng = luasBng;
	}

	public String getNmPenjual() {
		return nmPenjual;
	}

	public void setNmPenjual(String nmPenjual) {
		this.nmPenjual = nmPenjual;
	}

	public String getNmPembeli() {
		return nmPembeli;
	}

	public void setNmPembeli(String nmPembeli) {
		this.nmPembeli = nmPembeli;
	}

	public String getKdBooking() {
		return kdBooking;
	}

	public void setKdBooking(String kdBooking) {
		this.kdBooking = kdBooking;
	}

	public String getBphtbYgHarusDibayar() {
		return bphtbYgHarusDibayar;
	}

	public void setBphtbYgHarusDibayar(String bphtbYgHarusDibayar) {
		this.bphtbYgHarusDibayar = bphtbYgHarusDibayar;
	}

	public String getStatusBooking() {
		return statusBooking;
	}

	public void setStatusBooking(String statusBooking) {
		this.statusBooking = statusBooking;
	}

	public String getStatusValidasi() {
		return statusValidasi;
	}

	public void setStatusValidasi(String statusValidasi) {
		this.statusValidasi = statusValidasi;
	}

	public String getStatusBayar() {
		return statusBayar;
	}

	public void setStatusBayar(String statusBayar) {
		this.statusBayar = statusBayar;
	}

	public String getStatusExpired() {
		return statusExpired;
	}

	public void setStatusExpired(String statusExpired) {
		this.statusExpired = statusExpired;
	}

	public String getTglTransaksi() {
		return tglTransaksi;
	}

	public void setTglTransaksi(String tglTransaksi) {
		this.tglTransaksi = tglTransaksi;
	}

	public String getSsegmen7() {
		return ssegmen7;
	}

	public void setSsegmen7(String ssegmen7) {
		this.ssegmen7 = ssegmen7;
	}

	public String getStambahancatatan() {
		return stambahancatatan;
	}

	public void setStambahancatatan(String stambahancatatan) {
		this.stambahancatatan = stambahancatatan;
	}

	public void writeToParcel(android.os.Parcel dest, int flags) {
		dest.writeValue(nop);
		dest.writeValue(luasTanah);
		dest.writeValue(luasBng);
		dest.writeValue(nmPenjual);
		dest.writeValue(nmPembeli);
		dest.writeValue(kdBooking);
		dest.writeValue(bphtbYgHarusDibayar);
		dest.writeValue(statusBooking);
		dest.writeValue(statusValidasi);
		dest.writeValue(statusBayar);
		dest.writeValue(statusExpired);
		dest.writeValue(tglTransaksi);
		dest.writeValue(ssegmen7);
		dest.writeValue(stambahancatatan);
	}

	public int describeContents() {
		return 0;
	}
}
