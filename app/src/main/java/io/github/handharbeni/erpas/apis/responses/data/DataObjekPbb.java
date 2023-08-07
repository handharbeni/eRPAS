package io.github.handharbeni.erpas.apis.responses.data;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataObjekPbb implements Serializable, Parcelable {
	@SerializedName("JALAN_OP")
	@Expose
	private String jalanOp;
	@SerializedName("RW_OP")
	@Expose
	private String rwOp;
	@SerializedName("RT_OP")
	@Expose
	private String rtOp;
	@SerializedName("NM_WP")
	@Expose
	private String nmWp;
	@SerializedName("NM_KELURAHAN")
	@Expose
	private String nmKelurahan;
	@SerializedName("NM_KECAMATAN")
	@Expose
	private String nmKecamatan;
	public final static Creator<DataObjekPbb> CREATOR = new Creator<DataObjekPbb>() {


		@SuppressWarnings({
				"unchecked"
		})
		public DataObjekPbb createFromParcel(android.os.Parcel in) {
			return new DataObjekPbb(in);
		}

		public DataObjekPbb[] newArray(int size) {
			return (new DataObjekPbb[size]);
		}

	};

	protected DataObjekPbb(android.os.Parcel in) {
		this.jalanOp = ((String) in.readValue((String.class.getClassLoader())));
		this.rwOp = ((String) in.readValue((String.class.getClassLoader())));
		this.rtOp = ((String) in.readValue((String.class.getClassLoader())));
		this.nmWp = ((String) in.readValue((String.class.getClassLoader())));
		this.nmKelurahan = ((String) in.readValue((String.class.getClassLoader())));
		this.nmKecamatan = ((String) in.readValue((String.class.getClassLoader())));
	}

	public DataObjekPbb() {
	}

	public String getJalanOp() {
		return jalanOp;
	}

	public void setJalanOp(String jalanOp) {
		this.jalanOp = jalanOp;
	}

	public String getRwOp() {
		return rwOp;
	}

	public void setRwOp(String rwOp) {
		this.rwOp = rwOp;
	}

	public String getRtOp() {
		return rtOp;
	}

	public void setRtOp(String rtOp) {
		this.rtOp = rtOp;
	}

	public String getNmWp() {
		return nmWp;
	}

	public void setNmWp(String nmWp) {
		this.nmWp = nmWp;
	}

	public String getNmKelurahan() {
		return nmKelurahan;
	}

	public void setNmKelurahan(String nmKelurahan) {
		this.nmKelurahan = nmKelurahan;
	}

	public String getNmKecamatan() {
		return nmKecamatan;
	}

	public void setNmKecamatan(String nmKecamatan) {
		this.nmKecamatan = nmKecamatan;
	}

	public void writeToParcel(android.os.Parcel dest, int flags) {
		dest.writeValue(jalanOp);
		dest.writeValue(rwOp);
		dest.writeValue(rtOp);
		dest.writeValue(nmWp);
		dest.writeValue(nmKelurahan);
		dest.writeValue(nmKecamatan);
	}

	public int describeContents() {
		return 0;
	}

}
