package io.github.handharbeni.erpas.apis.responses.WP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ListResponseSkrd implements Serializable {
	@SerializedName("status")
	@Expose
	private String status;
	@SerializedName("data_skrd")
	@Expose
	private List<DataSkrd> dataSkrd;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<DataSkrd> getDataSkrd() {
		return dataSkrd;
	}

	public void setDataSkrd(List<DataSkrd> dataSkrd) {
		this.dataSkrd = dataSkrd;
	}
}
