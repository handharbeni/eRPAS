package io.github.handharbeni.erpas.apis.responses.data;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataStatusPayment implements Serializable, Parcelable {
	@SerializedName("COUNT")
	@Expose
	private String count;
	public final static Creator<DataStatusPayment> CREATOR = new Creator<DataStatusPayment>() {


		@SuppressWarnings({
				"unchecked"
		})
		public DataStatusPayment createFromParcel(android.os.Parcel in) {
			return new DataStatusPayment(in);
		}

		public DataStatusPayment[] newArray(int size) {
			return (new DataStatusPayment[size]);
		}

	}
			;
	private final static long serialVersionUID = -8027101437820017197L;

	protected DataStatusPayment(android.os.Parcel in) {
		this.count = ((String) in.readValue((String.class.getClassLoader())));
	}

	public DataStatusPayment() {
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public void writeToParcel(android.os.Parcel dest, int flags) {
		dest.writeValue(count);
	}

	public int describeContents() {
		return 0;
	}
}
