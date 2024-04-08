package com.agilysys.qa.gridIron.utils.listeners;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.internal.ConstructorOrMethod;


public class Profiling implements IInvokedMethodListener {

	protected SimpleReport report = new SimpleReport();

	public static boolean onFailedTest = true;
	public static boolean onSucceededTest = true;

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		if (onFailedTest || onSucceededTest) {
			if (isClassAnnotatedWithReport(method)) {
				report.start();
			}
		}
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		if (testResult.isSuccess() && onSucceededTest || !testResult.isSuccess() && onFailedTest) {
			if (isClassAnnotatedWithReport(method)) {
				if (null != testResult.getName())
					report.finish(testResult.getName() +" - " + testResult.getTestClass(), method);
			}
		}
		report.clean();
	}

	private boolean isClassAnnotatedWithReport(IInvokedMethod method) {
		ConstructorOrMethod consOrMethod = method.getTestMethod().getConstructorOrMethod();
	//	Report annotation = consOrMethod.getDeclaringClass().getAnnotation(Report.class);
		return true;// annotation != null;
	}
}
