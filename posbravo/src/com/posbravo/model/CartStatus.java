package com.posbravo.model;

// Generated Jun 10, 2014 6:20:56 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * CartStatus generated by hbm2java
 */
public class CartStatus implements java.io.Serializable {

	private Integer cartStatusId;
	private String status;
	private int dateLastModified;
	private Set carts = new HashSet(0);

	public CartStatus() {
	}

	public CartStatus(int dateLastModified) {
		this.dateLastModified = dateLastModified;
	}

	public CartStatus(String status, int dateLastModified, Set carts) {
		this.status = status;
		this.dateLastModified = dateLastModified;
		this.carts = carts;
	}

	public Integer getCartStatusId() {
		return this.cartStatusId;
	}

	public void setCartStatusId(Integer cartStatusId) {
		this.cartStatusId = cartStatusId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getDateLastModified() {
		return this.dateLastModified;
	}

	public void setDateLastModified(int dateLastModified) {
		this.dateLastModified = dateLastModified;
	}

	public Set getCarts() {
		return this.carts;
	}

	public void setCarts(Set carts) {
		this.carts = carts;
	}

}
