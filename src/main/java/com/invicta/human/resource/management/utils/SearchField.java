package com.invicta.human.resource.management.utils;

public class SearchField {
	public static boolean isNullOrEmpty(String field) {
		if (field != null && !field.trim().isEmpty())
			return false;
		return true;
	}

	public static boolean isNull(Long field) {
		if (field != null)
			return false;
		return true;
	}

	public static boolean isNull(Double field) {
		if (field != null)
			return false;
		return true;
	}

	public static boolean isNull(Boolean field) {
		if (field != null)
			return false;
		return true;
	}
}