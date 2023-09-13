package io.github.handharbeni.erpas.apis.responses.WP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResponseLogin implements Serializable {
	@SerializedName("status")
	@Expose
	private String status;
	@SerializedName("username")
	@Expose
	private String username;
	@SerializedName("fullname")
	@Expose
	private String fullname;
	@SerializedName("email")
	@Expose
	private String email;
	@SerializedName("id")
	@Expose
	private String id;
	@SerializedName("created_at")
	@Expose
	private String createdAt;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

}
