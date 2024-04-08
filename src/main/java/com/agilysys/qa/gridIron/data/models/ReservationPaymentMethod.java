package com.agilysys.qa.gridIron.data.models;

public class ReservationPaymentMethod {
	
	boolean isThirdParty;
	boolean doNotDiscloseRates;
	boolean isPrimaryMethod;
	boolean associateToDefaultFolio;
	boolean saveToGuestProfile;
	boolean isGuestDefaultDefault;
	String directBillAccountName;
	String swipeData;
	String paymentMethod;
	String paymentMethodDisplayName;
	
	public boolean isThirdParty() {
		return isThirdParty;
	}
	public void setThirdParty(boolean isThirdParty) {
		this.isThirdParty = isThirdParty;
	}
	public boolean isDoNotDiscloseRates() {
		return doNotDiscloseRates;
	}
	public void setDoNotDiscloseRates(boolean doNotDiscloseRates) {
		this.doNotDiscloseRates = doNotDiscloseRates;
	}
	public boolean isPrimaryMethod() {
		return isPrimaryMethod;
	}
	public void setPrimaryMethod(boolean isPrimaryMethod) {
		this.isPrimaryMethod = isPrimaryMethod;
	}
	public boolean isAssociateToDefaultFolio() {
		return associateToDefaultFolio;
	}
	public void setAssociateToDefaultFolio(boolean associateToDefaultFolio) {
		this.associateToDefaultFolio = associateToDefaultFolio;
	}
	public boolean isSaveToGuestProfile() {
		return saveToGuestProfile;
	}
	public void setSaveToGuestProfile(boolean saveToGuestProfile) {
		this.saveToGuestProfile = saveToGuestProfile;
	}
	public boolean isGuestDefault() {
		return isGuestDefaultDefault;
	}
	public void setGuestDefault(boolean isGuestDefaultDefault) {
		this.isGuestDefaultDefault = isGuestDefaultDefault;
	}
	public String getDirectBillAccountName() {
		return directBillAccountName;
	}
	public void setDirectBillAccountName(String directBillAccountName) {
		this.directBillAccountName = directBillAccountName;
	}
	public String getSwipeData() {
		return swipeData;
	}
	public void setSwipeData(String swipeData) {
		this.swipeData = swipeData;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getPaymentMethodDisplayName() {
		return paymentMethodDisplayName;
	}
	public void setPaymentMethodDisplayName(String paymentMethodDisplayName) {
		this.paymentMethodDisplayName = paymentMethodDisplayName;
	}
}
