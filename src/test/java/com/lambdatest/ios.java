package com.lambdatest;

// Use Appium-specific imports
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class ios {
    String username = System.getenv("LT_USERNAME") == null ? "YOUR_USERNAME" : System.getenv("LT_USERNAME");
    String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "YOUR_ACCESS_KEY" : System.getenv("LT_ACCESS_KEY");
    public String grid_url = "mobile-hub.lambdatest.com";
    public String status = "passed";

    // FIX 1: Use IOSDriver instead of RemoteWebDriver
    public static IOSDriver driver = null;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();

        ltOptions.put("build", "JUNIT Native App automation");
        ltOptions.put("name", "Java JUnit iOS");
        ltOptions.put("platformName", "ios");
        ltOptions.put("deviceName", "iPhone.*");
        ltOptions.put("isRealMobile", true);
        ltOptions.put("platformVersion", "18");
        ltOptions.put("app", "lt://APP1016018631760361477812757");
        ltOptions.put("network", false);
        ltOptions.put("visual", true);
        ltOptions.put("w3c", true); // Ensure W3C is enabled

        capabilities.setCapability("lt:options", ltOptions);

        // FIX 2: Initialize as IOSDriver
        driver = new IOSDriver(new URL("https://" + username + ":" + accessKey + "@" + grid_url + "/wd/hub"),
                capabilities);
    }

    @Test
    public void testSimple() throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            // FIX 3: Use AppiumBy instead of MobileBy
            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("color"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("geoLocation"))).click();

            Thread.sleep(2000);

            // FIX 4: iOS navigate().back() is often not supported.
            // If this fails, you must click the 'Back' button element in your UI instead.
            try {
                driver.navigate().back();
            } catch (Exception e) {
                System.out.println("Standard back not supported, skipping or use element click.");
            }

            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("Text"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("notification"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("toast"))).click();

            // Navigate to Browser/WebView
            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Browser"))).click();
            Thread.sleep(5000);

            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("url"))).sendKeys("https://www.lambdatest.com/");
            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("find"))).click();

            Thread.sleep(5000);
            // navigate back to app
            driver.navigate().back();

            status = "passed";
        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
            status = "failed";
        }
    }

    @After
    public void tearDown() throws Exception {
        if (driver != null) {
            // FIX 5: Use the correct LambdaTest status hook for Appium 2.x
            driver.executeScript("lambda-status=" + status);
            driver.quit();
        }
    }
}