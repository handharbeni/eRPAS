package io.github.handharbeni.erpas.apis.responses.WP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ResponseWp implements Serializable {

	@SerializedName("status")
	@Expose
	private String status;
	@SerializedName("data")
	@Expose
	private String data;
	@SerializedName("data_wp")
	@Expose
	private DataWp dataWp;
	@SerializedName("data_tagihan")
	@Expose
	private List<DataTagihan> dataTagihan;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public DataWp getDataWp() {
		return dataWp;
	}

	public void setDataWp(DataWp dataWp) {
		this.dataWp = dataWp;
	}

	public List<DataTagihan> getDataTagihan() {
		return dataTagihan;
	}

	public void setDataTagihan(List<DataTagihan> dataTagihan) {
		this.dataTagihan = dataTagihan;
	}

	@Override
	public String toString() {
		return "ResponseWp{" +
				"status='" + status + '\'' +
				", data='" + data + '\'' +
				", dataWp=" + dataWp +
				", dataTagihan=" + dataTagihan +
				'}';
	}
}