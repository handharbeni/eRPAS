package io.github.handharbeni.erpas.apis.responses.WP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class LaporanRealisasi implements Serializable {
	@SerializedName("status")
	@Expose
	private String status;
	@SerializedName("data_realisasi")
	@Expose
	private List<DataRealisasi> dataRealisasi;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<DataRealisasi> getDataRealisasi() {
		return dataRealisasi;
	}

	public void setDataRealisasi(List<DataRealisasi> dataRealisasi) {
		this.dataRealisasi = dataRealisasi;
	}
}
