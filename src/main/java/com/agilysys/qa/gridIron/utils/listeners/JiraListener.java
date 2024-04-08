package com.agilysys.qa.gridIron.utils.listeners;

import com.agilysys.qa.gridIron.utils.Endpoints;
import com.agilysys.qa.gridIron.utils.JiraZephyrClient;
import com.agilysys.qa.gridIron.utils.annotations.TestCase;
import lombok.SneakyThrows;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class JiraListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        // TODO Auto-generated method stub

    }

    @SneakyThrows
    @Override
    public void onTestSuccess(ITestResult result) {
        // TODO Auto-generated method stub
        updateResult(result, "1");
    }

    @SneakyThrows
    @Override
    public void onTestFailure(ITestResult result) {
        // TODO Auto-generated method stub
        updateResult(result, "2");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStart(ITestContext context) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onFinish(ITestContext context) {
        // TODO Auto-generated method stub

    }

    public void updateResult(ITestResult result, String status) throws JSONException {
        TestCase testCaseAnnotation = result.getMethod().getConstructorOrMethod().getMethod()
                .getAnnotation(TestCase.class);

        String jiraKey = null;
        String[] jiraKeys = null;
        try {
            jiraKey = testCaseAnnotation.id();
        } catch (Exception e) {
        }

        try {
            jiraKeys = testCaseAnnotation.ids();
        } catch (Exception e) {
        }

        if (testCaseAnnotation != null && !jiraKey.isEmpty()) {

            if (jiraKey.isEmpty() || jiraKey == null) {
                return;
            }
            JiraZephyrClient zephyrClient = Endpoints.jiraClient;
            int executionId = zephyrClient.getTestExecutionIdByNameFromCycleExecution(jiraKey,
                    Endpoints.jiraExecutions);

            if (executionId == 0) {
                JSONObject testcase = zephyrClient.getIssue(jiraKey);
                if (testcase != null && testcase.has("id")) {
                    String testcaseId = testcase.getString("id");
                    JSONObject addTestResponse = zephyrClient.addTestToCycle(testcaseId, Endpoints.jiraProjectId,
                            Endpoints.jiraVersionId, Endpoints.jiraCycleId);
                    try {
                        if (addTestResponse != null) {
                            executionId = addTestResponse.getJSONObject(addTestResponse.keys().next().toString()).getInt("id");
                        }
                    } catch (Exception e) {

                    }
                }

            }

            if (executionId != 0) {
                zephyrClient.updateExecutionStatus(executionId, status);
            }
        } else if (testCaseAnnotation != null && jiraKeys != null) {

            for (int i = 0; i < jiraKeys.length; i++) {
                JiraZephyrClient zephyrClient = Endpoints.jiraClient;
                int executionId = zephyrClient.getTestExecutionIdByNameFromCycleExecution(jiraKeys[i].toString(),
                        Endpoints.jiraExecutions);
                if (executionId == 0) {
                    JSONObject testcase = zephyrClient.getIssue(jiraKeys[i].toString());

                    if (testcase != null && testcase.has("id")) {
                        String testcaseId = testcase.getString("id");
                        JSONObject addTestResponse = zephyrClient.addTestToCycle(testcaseId, Endpoints.jiraProjectId,
                                Endpoints.jiraVersionId, Endpoints.jiraCycleId);
                        try {
                            if (addTestResponse != null) {
                                executionId = addTestResponse.getJSONObject(addTestResponse.keys().next().toString()).getInt("id");
                            }
                        } catch (Exception e) {

                        }
                    }

                }

                if (executionId != 0) {
                    zephyrClient.updateExecutionStatus(executionId, status);
                }
            }
        }
    }
}