package com.agilysys.qa.gridIron.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import com.agilysys.qa.constants.EndPoints;

/*
 * *Author - Harish Baskaran - 2018
 */
public class Endpoints {

    private static String basePMSUrl;
    private static String baseUrlPlatform;
    private static String UserName;
    private static String Password;
    private static String Environment;
    private static String JiraId;
    private static int retries;
    protected static Properties prop = new Properties();
    static InputStream input = null;

    private static String tenantId;
    private static String propertyId;
    private static String tenantCode;

    private static int nodes;

    // JIRA
    public static String jiraUrl;
    public static String jiraAuthorization;
    public static String jiraUsername;
    public static String jiraPassword;
    public static String jiraProjectId;
    public static String jiraVersionId;
    public static String jiraCycleId;
    public static JSONObject jiraExecutions;

    public static JiraZephyrClient jiraClient;

    public static String getBasePMSUrl() {
        basePMSUrl = System.getProperty("url.base");
        baseUrlPlatform = System.getProperty("platformurl.base");

        String env = System.getProperty("env");
        if (null == basePMSUrl | null == baseUrlPlatform) {
            try {
                input = new FileInputStream("src\\main\\resources\\" + env + ".properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            try {
                prop.load(input);
            } catch (IOException e) {
                e.printStackTrace();
            }

            basePMSUrl = prop.getProperty("urlBase");
            baseUrlPlatform = prop.getProperty("platformurlBase");
        }

        UserName = prop.getProperty("stayUsername");
        Password = prop.getProperty("stayPassword");

        setEnvironmentUrl(basePMSUrl, baseUrlPlatform);

        return basePMSUrl;
    }

    public static String getUserName() {
        return UserName;
    }

    public static String getTenantId() {
        String env = System.getProperty("env");

        try {
            input = new FileInputStream("src\\main\\resources\\" + env + ".properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        tenantId = prop.getProperty("ProdTenantId");

        return tenantId;
    }

    public static int getNodesCount() {
        String env = System.getProperty("env");

        try {
            nodes = Integer.parseInt(System.getProperty("nodes"));
        } catch (Exception e1) {
            try {
                input = new FileInputStream("src\\main\\resources\\" + env + ".properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            try {
                prop.load(input);
            } catch (IOException e) {
                e.printStackTrace();
            }

            nodes = Integer.parseInt(prop.getProperty("nodes"));
        }
        return nodes;
    }

    public static String getTenantCode() {
        String env = System.getProperty("env");

        try {
            input = new FileInputStream("src\\main\\resources\\" + env + ".properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        tenantCode = prop.getProperty("ProdTenantCode");

        return tenantCode;
    }

    public static String getPropertyId() {
        String env = System.getProperty("env");

        try {
            input = new FileInputStream("src\\main\\resources\\" + env + ".properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        propertyId = prop.getProperty("ProdPropertyId");

        return propertyId;
    }

    public static int getRetries() {
        String env = System.getProperty("env");

        try {
            input = new FileInputStream("src\\main\\resources\\" + env + ".properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        retries = Integer.parseInt(prop.getProperty("retries"));

        return retries;
    }

    public static String getPassword() {
        return Password;
    }

    public static String getEnvironment() {
        String env = System.getProperty("env");

        try {
            input = new FileInputStream("src\\main\\resources\\" + env + ".properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Environment = prop.getProperty("Environment");

        return Environment;
    }

    public static String getJiraId() {
        String env = System.getProperty("env");

        try {
            input = new FileInputStream("src\\main\\resources\\" + env + ".properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JiraId = prop.getProperty("JiraId");

        return JiraId;
    }

    public static String getBaseUrlPlatform() {
        String env = System.getProperty("env");

        basePMSUrl = System.getProperty("url.base");
        baseUrlPlatform = System.getProperty("platformurl.base");

        if (null == basePMSUrl | null == baseUrlPlatform) {
            try {
                input = new FileInputStream("src\\main\\resources\\" + env + ".properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            try {
                prop.load(input);
            } catch (IOException e) {
                e.printStackTrace();
            }

            basePMSUrl = prop.getProperty("urlBase");
            baseUrlPlatform = prop.getProperty("platformurlBase");
        }
        setEnvironmentUrl(basePMSUrl, baseUrlPlatform);

        return baseUrlPlatform;
    }

    public static void setEnvironmentUrl(String pmsUrl, String platformURL) {

        if (pmsUrl.charAt(pmsUrl.length() - 1) != '/') {
            pmsUrl = pmsUrl + "/";
        }

        if (platformURL.charAt(platformURL.length() - 1) != '/') {
            platformURL = platformURL + "/";
        }

        basePMSUrl = pmsUrl;
        baseUrlPlatform = platformURL;

        EndPoints.setEnvironmentUrl(pmsUrl, platformURL);
    }

    public static void getJiraDetails() throws JSONException {
        String env = System.getProperty("env");

        try {
            input = new FileInputStream("src\\main\\resources\\" + env + ".properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        jiraUrl = prop.getProperty("jira.url", "");
        jiraAuthorization = prop.getProperty("jira.authorization", "");
        jiraUsername = prop.getProperty("jira.username", "");
        jiraPassword = prop.getProperty("jira.password", "");
        jiraProjectId = prop.getProperty("jira.projectid", "");
        jiraVersionId = prop.getProperty("jira.versionid", "");

        jiraCycleId = System.getProperty("jira.cycleid");

        if (null == jiraCycleId || jiraCycleId.isEmpty() || " " == jiraCycleId)
            jiraCycleId = prop.getProperty("jira.cycleid", "");

        if (!jiraAuthorization.isEmpty()) {
            jiraClient = new JiraZephyrClient(jiraUrl, jiraAuthorization);
        } else {
            jiraClient = new JiraZephyrClient(jiraUrl, jiraUsername, jiraPassword);
        }

        if (null != jiraClient) {
            jiraExecutions = jiraClient.getTestCycleExecution(jiraCycleId);
        }
    }

}
