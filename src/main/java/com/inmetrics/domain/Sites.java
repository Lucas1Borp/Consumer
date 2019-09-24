package com.inmetrics.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "sites")
public class Sites {

	@Id
	private String id;
	private String name;
	private String address;

	public Sites(String name, String address) {
		this.name = name;
		this.address = address;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Sites [id=" + id + ", name=" + name + ", address=" + address + "]";
	}

}
