package edu.mum.ea.domain;

import javax.persistence.Embeddable;

import org.springframework.util.StringUtils;

@Embeddable
public class Address {

	private String city;
	private String state;
	private String country;
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
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		if (!StringUtils.isEmpty(city)) {
			sb.append(city);
		}

		if (!StringUtils.isEmpty(state)) {
			sb.append(", ").append(state);
		}

		if (!StringUtils.isEmpty(country)) {
			sb.append(", ").append(country);
		}
		
		if (!StringUtils.isEmpty(zipcode)) {
			sb.append(", ").append(zipcode);
		}
		
		return sb.toString();
	}
}