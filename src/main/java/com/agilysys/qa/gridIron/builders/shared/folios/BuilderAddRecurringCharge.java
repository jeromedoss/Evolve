package com.agilysys.qa.gridIron.builders.shared.folios;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

import com.agilysys.qa.gridIron.constants.pagefactory.shared.folios.PFAddRecurringChargeModal;
import com.agilysys.qa.gridIron.data.models.RecurringCharge;

public class BuilderAddRecurringCharge {
	
	List<RecurringCharge> recurringCharges = new ArrayList<RecurringCharge>();
	String reason = null;
	LocalDate postDate = null;
	boolean checkAvailability = false;
	String overrideUsername = null;
	String overridePassword = null;
	
	public BuilderAddRecurringCharge(List<RecurringCharge> recurringCharges){
		this.recurringCharges = recurringCharges;
	}
	
	public BuilderAddRecurringCharge(RecurringCharge recurringCharge){
		this.recurringCharges.add(recurringCharge);
	}
	
	public BuilderAddRecurringCharge addRecurringCharge(RecurringCharge recurringCharge){
		this.recurringCharges.add(recurringCharge);
		return this;
	}
	
	public String getReason() {
		return reason;
	}

	public BuilderAddRecurringCharge setReason(String reason) {
		this.reason = reason;
		return this;
	}

	public LocalDate getPostDate() {
		return postDate;
	}

	public BuilderAddRecurringCharge setPostDate(LocalDate postDate) {
		this.postDate = postDate;
		return this;
	}

	
	public boolean isCheckAvailability() {
		return checkAvailability;
	}

	public BuilderAddRecurringCharge setCheckAvailability(boolean checkAvailability) {
		this.checkAvailability = checkAvailability;
		return this;
	}

	
	public BuilderAddRecurringCharge setOverrideUserCredentials(String overrideUsername, String overridePassword) {
		this.overrideUsername = overrideUsername;
		this.overridePassword = overridePassword;
		return this;
	}	

	public void build(){
		//Add items to cart
		recurringCharges.forEach((recurringCharge) ->{
			PFAddRecurringChargeModal.typeItemChargeAmount(recurringCharge.getItemName(), recurringCharge.getChargeAmount());
			PFAddRecurringChargeModal.typeItemQuantity(recurringCharge.getItemName(), recurringCharge.getQuantity());
			PFAddRecurringChargeModal.clickItemAdd(recurringCharge.getItemName());
		});				
		PFAddRecurringChargeModal.moveFromItemSelectScreen();
		
		//Set frequency if necessary
		boolean isRecurringCharge = false;
		
		for(RecurringCharge recurringCharge : recurringCharges){
			if(recurringCharge.getFrequencyType() != null){				
				isRecurringCharge = true;
				PFAddRecurringChargeModal.clickSelectedItem(recurringCharge.getItemName());
				PFAddRecurringChargeModal.setFrequencyType(recurringCharge.getFrequencyType());
				PFAddRecurringChargeModal.typeNoOfNights(recurringCharge.getEveryNFrequency());
			}
		}
		
		if(isRecurringCharge){			
			if(checkAvailability){
				PFAddRecurringChargeModal.clickCheckAvailability();	
			}
			PFAddRecurringChargeModal.moveFromFrequencySetup();
		}
		
		PFAddRecurringChargeModal.typeReason(reason);
		PFAddRecurringChargeModal.clickAddCharge();
	}
}