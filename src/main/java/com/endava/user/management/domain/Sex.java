package com.endava.user.management.domain;

public enum Sex {
	MALE, FEMALE;
	
	public String toString() {
		String name = this.name();
		return name.charAt(0) + name.substring(1).toLowerCase();
	}
}
