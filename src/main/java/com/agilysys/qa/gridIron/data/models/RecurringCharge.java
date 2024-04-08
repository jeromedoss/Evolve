package com.agilysys.qa.gridIron.data.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

import com.agilysys.common.model.FrequencyType;
import com.agilysys.pms.account.model.InventoryItem;
import com.agilysys.pms.account.model.TransactionItem;

public class RecurringCharge {
	String itemName = null;
	BigDecimal chargeAmount = null;
	int quantity;	
	
	LocalDate startDate = null;
	LocalDate endDate = null;
	FrequencyType frequencyType = null;
	List<String> days = new ArrayList<String>();
	String everyNoOfNights = null;
	
	public RecurringCharge(String itemName, BigDecimal chargeAmount, int quantity){
		this.itemName=itemName;
		this.chargeAmount = chargeAmount;
		this.quantity = quantity;
	}
	
	
	public String getItemName() {
		return itemName;
	}

	public BigDecimal getChargeAmount() {
		return chargeAmount;
	}

	public int getQuantity() {
		return quantity;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public FrequencyType getFrequencyType() {
		return frequencyType;
	}

	public List<String> getDays() {
		return days;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public void setEveryNFrequency(String everyNoOfNights){
		this.frequencyType = FrequencyType.EVERY_N;
		this.everyNoOfNights = everyNoOfNights;
	}
	
	public String getEveryNFrequency(){
		return this.everyNoOfNights;
	}
	
	public void setFirstNightOnlyFrequency(){
		this.frequencyType = FrequencyType.FIRST;
	}

	public void setDays(List<String> days) {
		this.days = days;
	}
}
