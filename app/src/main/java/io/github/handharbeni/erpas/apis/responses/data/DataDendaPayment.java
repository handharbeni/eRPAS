package io.github.handharbeni.erpas.apis.responses.data;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataDendaPayment implements Serializable, Parcelable {
	@SerializedName("TOTAL_BAYAR")
	@Expose
	private Object totalBayar;
	@SerializedName("TOTAL_DENDA")
	@Expose
	private Object totalDenda;
	public final static Creator<DataDendaPayment> CREATOR = new Creator<DataDendaPayment>() {


		@SuppressWarnings({
				"unchecked"
		})
		public DataDendaPayment createFromParcel(android.os.Parcel in) {
			return new DataDendaPayment(in);
		}

		public DataDendaPayment[] newArray(int size) {
			return (new DataDendaPayment[size]);
		}

	};

	protected DataDendaPayment(android.os.Parcel in) {
		this.totalBayar = ((Object) in.readValue((Object.class.getClassLoader())));
		this.totalDenda = ((Object) in.readValue((Object.class.getClassLoader())));
	}

	public DataDendaPayment() {
	}

	public Object getTotalBayar() {
		return totalBayar;
	}

	public void setTotalBayar(Object totalBayar) {
		this.totalBayar = totalBayar;
	}

	public Object getTotalDenda() {
		return totalDenda;
	}

	public void setTotalDenda(Object totalDenda) {
		this.totalDenda = totalDenda;
	}

	public void writeToParcel(android.os.Parcel dest, int flags) {
		dest.writeValue(totalBayar);
		dest.writeValue(totalDenda);
	}

	public int describeContents() {
		return 0;
	}
}
