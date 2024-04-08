package com.agilysys.qa.gridIron.utils.listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class RuntimeListener extends TestListenerAdapter implements ISuiteListener, ITestListener {

	static int ConfigPassCount = 0;
	static int ConfigFailCount = 0;
	static int TestPassCount = 0;
	static int TestFailCount = 0;
	static int TestSkipCount = 0;

	private final String resourcePath = System.getProperty("user.dir") + "/status.csv";

	@Override
	public void onStart(ISuite suite) {

		startLine();
	}

	@Override
	public void onFinish(ISuite suite) {
		Singleton.getInstance().close();
	}

	public void onConfigurationSuccess(ITestResult result) {

		String[] Line = new String[] { result.getTestClass().getName() + "." + result.getMethod().getMethodName(),
				"CONFIG PASS", Integer.toString(++ConfigPassCount), Integer.toString(ConfigFailCount),
				Integer.toString(TestPassCount), Integer.toString(TestFailCount), Integer.toString(TestSkipCount) };

		Singleton.getInstance().writeLine(Line, true, resourcePath);
		Singleton.getInstance().writeResults(Line);
	}

	public void onTestSkipped(ITestResult result) {

		String[] Line = new String[] { result.getTestClass().getName() + "." + result.getMethod().getMethodName(),
				"TEST SKIPPED", Integer.toString(ConfigPassCount), Integer.toString(ConfigFailCount),
				Integer.toString(TestPassCount), Integer.toString(TestFailCount), Integer.toString(++TestSkipCount) };

		Singleton.getInstance().writeLine(Line, true, resourcePath);
		Singleton.getInstance().writeResults(Line);
	}

	public void onConfigurationFailure(ITestResult result) {

		String[] Line = new String[] { result.getTestClass().getName() + "." + result.getMethod().getMethodName(),
				"CONFIG FAIL", Integer.toString(ConfigPassCount), Integer.toString(++ConfigFailCount),
				Integer.toString(TestPassCount), Integer.toString(TestFailCount), Integer.toString(TestSkipCount) };

		Singleton.getInstance().writeLine(Line, true, resourcePath);
		Singleton.getInstance().writeResults(Line);

	}

	public void onTestFailure(ITestResult result) {

		String[] Line = new String[] { result.getTestClass().getName() + "." + result.getMethod().getMethodName(),
				"TEST FAILED", Integer.toString(ConfigPassCount), Integer.toString(ConfigFailCount),
				Integer.toString(TestPassCount), Integer.toString(++TestFailCount), Integer.toString(TestSkipCount) };

		Singleton.getInstance().writeLine(Line, true, resourcePath);
		Singleton.getInstance().writeResults(Line);

	}

	public void onTestSuccess(ITestResult result) {

		String[] Line = new String[] { result.getTestClass().getName() + "." + result.getMethod().getMethodName(),
				"TEST PASSED", Integer.toString(ConfigPassCount), Integer.toString(ConfigFailCount),
				Integer.toString(++TestPassCount), Integer.toString(TestFailCount), Integer.toString(TestSkipCount) };

		Singleton.getInstance().writeLine(Line, true, resourcePath);
		Singleton.getInstance().writeResults(Line);

	}

	public void startLine() {
		String[] startLine = new String[] { "Test Name", "Status", "Config Pass Count", "Config Fail Count",
				"Test Pass Count", "Test Fail Count", "Test Skip Count" };

		Singleton.getInstance().writeLine(startLine, false, resourcePath);
		Singleton.getInstance().writeResults(startLine);
	}

}

class Singleton {
	private static final Singleton inst = new Singleton();

	// Create blank workbook
	XSSFWorkbook workbook = new XSSFWorkbook();

	// Create a blank sheet
	XSSFSheet spreadsheet = workbook.createSheet(" Status ");

	// Create row object
	XSSFRow row;
	static int rowid = 0;

	BufferedWriter buf = null;
	FileOutputStream out = null;

	private Singleton() {
		super();
	}

	public synchronized void writeLine(Object[] objectArr, boolean append, String fullPath) {

		try {
			FileWriter fileOut = new FileWriter(fullPath, append);
			buf = new BufferedWriter(fileOut);

			for (Object obj : objectArr) {
				buf.write((String) obj + ",");
			}
			buf.write("\n");
			buf.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public synchronized void writeResults(Object[] objectArr) {

		row = spreadsheet.createRow(rowid++);
		int cellid = 0;

		for (Object obj : objectArr) {
			Cell cell = row.createCell(cellid++);
			cell.setCellValue((String) obj);
		}

		// Write the workbook in file system

		try {
			out = new FileOutputStream(new File(System.getProperty("user.dir") + "/RunTimeStatus.xlsx"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Singleton getInstance() {
		return inst;
	}

	public synchronized void close() {
		try {
			buf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Writesheet.xlsx written successfully");
	}
}
