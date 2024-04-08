package com.agilysys.qa.gridIron.utils;

import java.math.BigDecimal;

import org.joda.time.LocalDate;



public class HelperMethods {

	public static String formatAmount(BigDecimal amount){
		if(amount == null){
			return "$";
		}

		if(amount.intValue()<0){
			return "-$"+amount.setScale(2, BigDecimal.ROUND_HALF_UP).abs();
		}

		return "$"+amount.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public static String CommaRemoveInAmount(String amount)
	{
		return amount.replace(",","");
	}

	public static String formatAmount(String amount){
		if(amount == null){
			return "$";
		}
		return "$"+ new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public static String convertDateToString(LocalDate date){
		if(date == null){
			return null;
		}
		return date.toString("MM/dd/yyy");
	}

	public static BigDecimal convertToBigDecimal(String amount){
		if(amount == null){
			return null;
		}
		amount = amount.replace("$", "");
		amount = amount.trim();
		System.out.println("***** "+amount);
		return new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
}
