package io.github.handharbeni.erpas.apis.responses.WP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralResponse {
	@SerializedName("status")
	@Expose
	private String status;

	@SerializedName("message")
	@Expose
	private String expose;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExpose() {
		return expose;
	}

	public void setExpose(String expose) {
		this.expose = expose;
	}
}
