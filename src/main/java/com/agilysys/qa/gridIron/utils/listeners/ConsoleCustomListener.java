package com.agilysys.qa.gridIron.utils.listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/*
 * *Author - Harish Baskaran - 2018
 */
public class ConsoleCustomListener implements ISuiteListener, ITestListener {

	public void onStart(ITestContext testContext) {

	}

	public void onStart(ISuite suite) {

	}

	public void onFinish(ISuite suite) {

	}

	public void onTestStart(ITestResult result) {
		try {

			System.out.println(
					"******************************************************************************************************************");
			System.out.println("===========Test: " + result.getTestClass().getName() + " : "
					+ result.getMethod().getMethodName() + " :" + " Test Started");
			System.out.println(
					"******************************************************************************************************************");
		} catch (Exception e) {

		}

	}

	public void beforeConfiguration(ITestResult tr) {

		System.out.println(
				"******************************************************************************************************************");
		System.out.println("===========CLASS: " + tr.getTestClass().getName() + " : Configuration : "
				+ tr.getMethod().getMethodName() + " started ===========");

	}

	public void onConfigurationSuccess(ITestResult itr) {
		try {

			System.out.println("===========CLASS: " + itr.getTestClass().getName() + " : Configuration : "
					+ itr.getMethod().getMethodName() + " completed ===========");
			System.out.println(
					"******************************************************************************************************************");

		} catch (Exception e) {

		}
	}

	public void onTestSkipped(ITestResult tr) {

	}

	public void onConfigurationFailure(ITestResult result) {

		System.out.println(result.getMethod().getMethodName() + " : ConfigurationFailed");
	}

	public void onTestFailure(ITestResult result) {

		try {
			System.out.println(
					"******************************************************************************************************************");
			System.out.println("**TEST: " + result.getTestClass().getName() + "." + result.getMethod().getMethodName()
					+ " :" + " :( Test Failed");
			System.out.println(
					"******************************************************************************************************************");

		} catch (Exception e) {

		}

	}

	public void onTestSuccess(ITestResult result) {

		try {
			System.out.println(
					"******************************************************************************************************************");
			System.out.println("**TEST: " + result.getTestClass().getName() + "." + result.getMethod().getMethodName()
					+ " :" + " :) Test Success");
			System.out.println(
					"******************************************************************************************************************");

		} catch (Exception e) {

		}

	}

	public void onFinish(ITestContext testContext) {
		try {
			System.out.println(
					"******************************************************************************************************************");
			System.out
					.println("**CLASS: " + testContext.getAllTestMethods()[0].getTestClass().getName() + " Completed");
			System.out.println(
					"******************************************************************************************************************");

		} catch (Exception e) {

		}
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

}
