package io.github.handharbeni.erpas.apis.responses.WP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PaymentStatus implements Serializable {
	@SerializedName("status")
	@Expose
	private String status;
	@SerializedName("npwrd")
	@Expose
	private String npwrd;
	@SerializedName("kode_billing")
	@Expose
	private String kodeBilling;
	@SerializedName("amount")
	@Expose
	private String amount;
	@SerializedName("status_bayar")
	@Expose
	private String statusBayar;
	@SerializedName("create_date")
	@Expose
	private String createDate;
	@SerializedName("expired_date")
	@Expose
	private String expiredDate;
	@SerializedName("transaction_date")
	@Expose
	private Object transactionDate;
	@SerializedName("barcode")
	@Expose
	private String barcode;
	@SerializedName("invoice_id")
	@Expose
	private String invoiceId;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNpwrd() {
		return npwrd;
	}

	public void setNpwrd(String npwrd) {
		this.npwrd = npwrd;
	}

	public String getKodeBilling() {
		return kodeBilling;
	}

	public void setKodeBilling(String kodeBilling) {
		this.kodeBilling = kodeBilling;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getStatusBayar() {
		return statusBayar;
	}

	public void setStatusBayar(String statusBayar) {
		this.statusBayar = statusBayar;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
	}

	public Object getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Object transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
}
