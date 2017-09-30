package com.endava.user.management.domain;

public class Address {

	private String country;
	private String state;
	private String city;
	private String zipCode;

	private Address(Builder builder) {
		this.country = builder.country;
		this.state = builder.state;
		this.city = builder.city;
		this.zipCode = builder.zipCode;
	}
	
	private Address() {
	}

	public String getCountry() {
		return country;
	}

	public String getState() {
		return state;
	}

	public String getCity() {
		return city;
	}

	public String getZipCode() {
		return zipCode;
	}

	@Override
	public String toString() {
		return "Address [country=" + country + ", state=" + state + ", city=" + city + ", zipCode=" + zipCode + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public static final class Builder {
		private String country;
		private String state;
		private String city;
		private String zipCode;

		private Builder() {
		}

		public Builder setCountry(String country) {
			this.country = country;
			return this;
		}

		public Builder setState(String state) {
			this.state = state;
			return this;
		}

		public Builder setCity(String city) {
			this.city = city;
			return this;
		}

		public Builder setZipCode(String zipCode) {
			this.zipCode = zipCode;
			return this;
		}

		public Address build() {
			return new Address(this);
		}
	}
}
