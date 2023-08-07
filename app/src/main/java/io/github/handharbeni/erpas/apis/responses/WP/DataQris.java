package io.github.handharbeni.erpas.apis.responses.WP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataQris {
	@SerializedName("status")
	@Expose
	private String status;

	@SerializedName("qris")
	@Expose
	private String qris;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getQris() {
		return qris;
	}

	public void setQris(String qris) {
		this.qris = qris;
	}
}
