package io.github.handharbeni.erpas.apis.responses.WP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataStatusPayment implements Serializable {
	@Expose
	private String status;
	@SerializedName("message")
	@Expose
	private String message;
	@SerializedName("data")
	@Expose
	private DataPayment data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public DataPayment getData() {
		return data;
	}

	public void setData(DataPayment data) {
		this.data = data;
	}
	public static class DataPayment implements Serializable{
		@SerializedName("type")
		@Expose
		private String type;
		@SerializedName("merchantName")
		@Expose
		private String merchantName;
		@SerializedName("transactionDate")
		@Expose
		private String transactionDate;
		@SerializedName("transactionStatus")
		@Expose
		private String transactionStatus;
		@SerializedName("transactionAmount")
		@Expose
		private String transactionAmount;
		@SerializedName("transactionReference")
		@Expose
		private String transactionReference;
		@SerializedName("paymentReference")
		@Expose
		private String paymentReference;
		@SerializedName("merchantBalance")
		@Expose
		private String merchantBalance;
		@SerializedName("merchantMsisdn")
		@Expose
		private String merchantMsisdn;
		@SerializedName("merchantEmail")
		@Expose
		private String merchantEmail;
		@SerializedName("merchantMpan")
		@Expose
		private String merchantMpan;
		@SerializedName("rrn")
		@Expose
		private String rrn;
		@SerializedName("customerName")
		@Expose
		private String customerName;
		@SerializedName("invoiceNumber")
		@Expose
		private String invoiceNumber;
		@SerializedName("isPaid")
		@Expose
		private Boolean isPaid;
		@SerializedName("recordStatus")
		@Expose
		private String recordStatus;
		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getMerchantName() {
			return merchantName;
		}

		public void setMerchantName(String merchantName) {
			this.merchantName = merchantName;
		}

		public String getTransactionDate() {
			return transactionDate;
		}

		public void setTransactionDate(String transactionDate) {
			this.transactionDate = transactionDate;
		}

		public String getTransactionStatus() {
			return transactionStatus;
		}

		public void setTransactionStatus(String transactionStatus) {
			this.transactionStatus = transactionStatus;
		}

		public String getTransactionAmount() {
			return transactionAmount;
		}

		public void setTransactionAmount(String transactionAmount) {
			this.transactionAmount = transactionAmount;
		}

		public String getTransactionReference() {
			return transactionReference;
		}

		public void setTransactionReference(String transactionReference) {
			this.transactionReference = transactionReference;
		}

		public String getPaymentReference() {
			return paymentReference;
		}

		public void setPaymentReference(String paymentReference) {
			this.paymentReference = paymentReference;
		}

		public String getMerchantBalance() {
			return merchantBalance;
		}

		public void setMerchantBalance(String merchantBalance) {
			this.merchantBalance = merchantBalance;
		}

		public String getMerchantMsisdn() {
			return merchantMsisdn;
		}

		public void setMerchantMsisdn(String merchantMsisdn) {
			this.merchantMsisdn = merchantMsisdn;
		}

		public String getMerchantEmail() {
			return merchantEmail;
		}

		public void setMerchantEmail(String merchantEmail) {
			this.merchantEmail = merchantEmail;
		}

		public String getMerchantMpan() {
			return merchantMpan;
		}

		public void setMerchantMpan(String merchantMpan) {
			this.merchantMpan = merchantMpan;
		}

		public String getRrn() {
			return rrn;
		}

		public void setRrn(String rrn) {
			this.rrn = rrn;
		}

		public String getCustomerName() {
			return customerName;
		}

		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}

		public String getInvoiceNumber() {
			return invoiceNumber;
		}

		public void setInvoiceNumber(String invoiceNumber) {
			this.invoiceNumber = invoiceNumber;
		}

		public Boolean getIsPaid() {
			return isPaid;
		}

		public void setIsPaid(Boolean isPaid) {
			this.isPaid = isPaid;
		}

		public String getRecordStatus() {
			return recordStatus;
		}

		public void setRecordStatus(String recordStatus) {
			this.recordStatus = recordStatus;
		}
	}
}
