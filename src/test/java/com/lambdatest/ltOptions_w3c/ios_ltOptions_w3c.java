package com.lambdatest.ltOptions_w3c;

import io.appium.java_client.MobileBy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.HashMap;

public class ios_ltOptions_w3c {
    String username = System.getenv("LT_USERNAME") == null ? "LT_USERNAME" : System.getenv("LT_USERNAME"); //Enter the Username here
    String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "LT_ACCESS_KEY" : System.getenv("LT_ACCESS_KEY"); //Enter the Access key here
    public String app_id = System.getenv("LT_APP_ID") == null ? "lt://proverbial-ios" : System.getenv("LT_APP_ID");      //Enter your LambdaTest App ID at the place of lt://proverbial-android
    public String grid_url = System.getenv("LT_GRID_URL") == null ? "mobile-hub.lambdatest.com" : System.getenv("LT_GRID_URL");
    public String status = "passed";

    public static RemoteWebDriver driver = null;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("w3c", true);
        ltOptions.put("console", true);
        ltOptions.put("network", true);
        ltOptions.put("visual", true);
        ltOptions.put("enableCustomTranslation", true);
        ltOptions.put("platformName", "ios");
        ltOptions.put("deviceName", ".*");
        ltOptions.put("platformVersion", "15");
        ltOptions.put("app", app_id);
        ltOptions.put("deviceOrientation", "PORTRAIT");
        ltOptions.put("build", "JUNIT_lt:options_w3c");
        ltOptions.put("name", "ios_lt:options_w3c");
        ltOptions.put("isRealMobile", true);
        capabilities.setCapability("lt:options", ltOptions);

        driver = new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + "@" + grid_url + "/wd/hub"), capabilities);
    }

    @Test
    public void testSimple() throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.elementToBeClickable(MobileBy.id("color"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(MobileBy.id("geoLocation"))).click();
            Thread.sleep(5000);
            driver.navigate().back();
            wait.until(ExpectedConditions.elementToBeClickable(MobileBy.id("Text"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(MobileBy.id("notification"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(MobileBy.id("toast"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("Browser"))).click();
            Thread.sleep(10000);
            wait.until(ExpectedConditions.elementToBeClickable(MobileBy.id("url"))).sendKeys("https://www.lambdatest.com/");
            wait.until(ExpectedConditions.elementToBeClickable(MobileBy.id("find"))).click();
            Thread.sleep(5000);
            driver.navigate().back();

            status = "passed";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            status = "failed";
        }
    }

    @After
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.executeScript("lambda-status=" + status);
            driver.quit();
        }
    }
}

