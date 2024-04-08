package com.agilysys.qa.gridIron.utils.listeners;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestNG;

import com.agilysys.qa.gridIron.utils.Endpoints;

/*
 * *Author - Harish Baskaran - 2018
 */
public class RetryCustomListener implements ISuiteListener, ITestListener {

	static String retryFileName = null;
	static boolean testflag = false;
	static int count = 0;
	static int retries = 1;

	TestNG testng = new TestNG();

	List<String> testFilesList = new ArrayList<String>();
	Set<ITestResult> passedTestCaseResults = new HashSet<>();
	Set<ITestResult> skippedTestCaseResults = new HashSet<>();	
	Set<ITestResult> failedTestCaseResults = new HashSet<>();
	public void createfile() {
		
		File file = new File(retryFileName);
	}

	public void onStart(ISuite suite) {

	}

	public void onFinish(ISuite suite) {

		Iterator<ITestResult> iteratorFailedTestCases = failedTestCaseResults.iterator();
		while(iteratorFailedTestCases.hasNext()) {
			ITestResult failedTest = iteratorFailedTestCases.next();
			if(checkResultPresent(passedTestCaseResults, failedTest)){
				iteratorFailedTestCases.remove();
				continue;
        	}
			
			if (getDuplicateObjectCount(failedTestCaseResults, failedTest) > 1)
				iteratorFailedTestCases.remove();
		}

		Iterator<ITestResult> iteratorSkippedTestCases = skippedTestCaseResults.iterator();
		while(iteratorSkippedTestCases.hasNext()) {
			ITestResult skippedTest = iteratorSkippedTestCases.next();
			if(checkResultPresent(failedTestCaseResults, skippedTest)){
				iteratorSkippedTestCases.remove();
				continue;
        	}
        	
        	if(checkResultPresent(passedTestCaseResults, skippedTest)){
        		iteratorSkippedTestCases.remove();
        		continue;
        	}
        	
        	if (getDuplicateObjectCount(skippedTestCaseResults, skippedTest) > 1)
        		iteratorSkippedTestCases.remove();
		}
		
        //Retry rename file if exists
		String path = System.getProperty("user.dir") + "\\src\\test\\resources\\";
		retryFileName = path + "testngRetry" + ".xml";
		
		File retryFile = new File(retryFileName);
		
		if(retryFile.exists()){
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
			Date date = new Date();

			String backupFileName = path + "testngRetry" + formatter.format(date) + ".xml";
			File backupFile = new File(backupFileName);
			retryFile.renameTo(backupFile);
		}
		
        //Return if there is nothing to retry
		if(skippedTestCaseResults.size() == 0 && failedTestCaseResults.size() ==0){
			return;
		}		
		
		initiateXML(suite);
		failedTestCaseResults.forEach((result)->{
			includeFailedTestCase(result);
		});
		
		skippedTestCaseResults.forEach((result)->{
			includeSkippedTestCase(result);
		});
		completeXmlAndInitiateRetry();
	}

	public void onTestSkipped(ITestResult result) {
		skippedTestCaseResults.add(result);
	}

	public void onConfigurationFailure(ITestResult result) {

	}

	public void onTestFailure(ITestResult result) {
		failedTestCaseResults.add(result);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestSuccess(ITestResult result) {
		passedTestCaseResults.add(result);
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
	
	public void initiateXML(ISuite suite) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
		Date date = new Date();

		createfile();
		
		List<String> listeners =suite.getXmlSuite().getListeners();

		String startTemplate = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
				+ "<!DOCTYPE suite SYSTEM \"http://testng.org/testng-1.0.dtd\">\n"
				+ "<suite name=\"Suite\" configfailurepolicy=\"continue\" parallel=\"classes\"\n"
				+ "    thread-count=\"10\">\n" 
				+ "    <listeners>\n";
		

		for(String listener : listeners){
			startTemplate = startTemplate + "		<listener class-name=\""+listener+"\" />\n";
		}
		
				
				startTemplate = startTemplate + "    </listeners>\n" 
				+ "	<parameter name=\"remote\" value=\""+suite.getXmlSuite().getParameter("remote")+"\" />\n"
				+ "	<parameter name=\"remoteUrl\" value=\""+suite.getXmlSuite().getParameter("remoteUrl")+"\" />\n"
				+ "	<parameter name=\"headless\" value=\""+suite.getXmlSuite().getParameter("headless")+"\" />\n" 
				+ "    <test name=\"Retry\">\n"
				+ "        <classes>\n";

		writeLine(startTemplate, false);

	}
	
	public void completeXmlAndInitiateRetry() {

		String endTemplate = "        </classes>\n" + "    </test>\n" + "</suite>\n";

		writeLine(endTemplate, true);
		
	}
	
	public void includeSkippedTestCase(ITestResult result) {

		String template = "            <class name=\"" + result.getTestClass().getName() + "\">\n"
				+ "				<methods>\n" + "					<include name=\""
				+ result.getMethod().getMethodName() + "\" />\n" + "				</methods>\n"
				+ "			</class>\n";

		writeLine(template, true);

		testflag = true;
	}
	
	public void includeFailedTestCase(ITestResult result) {

		String template = "            <class name=\"" + result.getTestClass().getName() + "\">\n"
				+ "				<methods>\n" + "					<include name=\""
				+ result.getMethod().getMethodName() + "\" />\n" + "				</methods>\n"
				+ "			</class>\n";

		writeLine(template, true);

		testflag = true;
	}
	
	public void writeLine(String sLine, boolean append) {

		try {
			FileWriter fileOut = new FileWriter(retryFileName, append);
			BufferedWriter buf = new BufferedWriter(fileOut);

			buf.write(sLine + "\r\n");
			buf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkResultPresent(Set<ITestResult> testResults, ITestResult compareTestResult){
		return testResults.stream().filter( result -> result.getName().contentEquals(compareTestResult.getName()) && result.getTestClass().getName().contentEquals(compareTestResult.getTestClass().getName())).findAny().isPresent();
	}
	
	public int getDuplicateObjectCount(Set<ITestResult> testResults, ITestResult compareTestResult){
		return testResults.stream().filter( result -> result.getName().contentEquals(compareTestResult.getName()) && result.getTestClass().getName().contentEquals(compareTestResult.getTestClass().getName())).collect(Collectors.toList()).size();
	}
}