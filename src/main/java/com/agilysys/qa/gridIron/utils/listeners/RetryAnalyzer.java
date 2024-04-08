package com.agilysys.qa.gridIron.utils.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.agilysys.qa.gridIron.wrappers.StayUIWrappers;

public class RetryAnalyzer implements IRetryAnalyzer{

	int counter = 0;
    int retryLimit = 0;
	@Override
	public boolean retry(ITestResult result) {
		result.setStatus(ITestResult.FAILURE);
		//retry for three iterations		
		if(counter < retryLimit)
		{
			StayUIWrappers.wrapperAfterMethod(result);
			counter++;            
            return true;
        }
		
		return false;
	}

}
