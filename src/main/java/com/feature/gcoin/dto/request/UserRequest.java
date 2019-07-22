package com.feature.gcoin.dto.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("totalCoin")
	private Long totalCoin;
	
	@JsonProperty("addressReceive")
	private String addressReceive;

	public Long getTotalCoin() {
		return totalCoin == null ? 0L : totalCoin;
	}

	public void setTotalCoin(Long totalCoin) {
		this.totalCoin = totalCoin;
	}

	public String getAddressReceive() {
		return addressReceive;
	}

	public void setAddressReceive(String addressReceive) {
		this.addressReceive = addressReceive;
	}
}
