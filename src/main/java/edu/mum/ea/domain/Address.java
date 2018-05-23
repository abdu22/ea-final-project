package edu.mum.ea.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Embeddable
public class Address {
	
	@NotEmpty(message= "{NotEmpty}")
	private String city;
	@Size(min=2, max=2, message = "Size.state")
	private String state;
	@NotEmpty(message= "{NotEmpty}")
	private String country;

	@Pattern(regexp="^\\d{5}(-\\d{4})?$", message= "{Pattern.zipcode}")
	private String zipcode;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
}