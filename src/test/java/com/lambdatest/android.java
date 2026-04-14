package com.lambdatest;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class android {
    String username = System.getenv("LT_USERNAME") == null ? "YOUR_USERNAME" : System.getenv("LT_USERNAME");
    String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "YOUR_ACCESS_KEY" : System.getenv("LT_ACCESS_KEY");
    public String status = "passed";

    // Fix 1: Use AndroidDriver instead of RemoteWebDriver
    public static AndroidDriver driver = null;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Fix 2: Use lt:options for W3C compliance
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("w3c", true);
        ltOptions.put("platformName", "android");
        ltOptions.put("deviceName", "Pixel.*");
        ltOptions.put("platformVersion", "14");
        ltOptions.put("isRealMobile", true);
        ltOptions.put("app", "lt://APP1016055391769006462699400");
        ltOptions.put("build", "JUNIT Native App automation");
        ltOptions.put("name", "Java JUnit Android");
        ltOptions.put("visual", true);
        ltOptions.put("console", true);
        ltOptions.put("autoGrantPermissions", true);

        capabilities.setCapability("lt:options", ltOptions);

        driver = new AndroidDriver(
                new URL("https://" + username + ":" + accessKey + "@mobile-hub.lambdatest.com/wd/hub"),
                capabilities);
    }

    @Test
    public void testSimple() throws Exception {
        try {
            // Fix 3: Use Duration for WebDriverWait in Selenium 4
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            // Fix 4: Use AppiumBy instead of MobileBy
            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("color"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("geoLocation"))).click();

            Thread.sleep(2000);

            // Fix 5: Proper Mobile Back Navigation
            driver.navigate().back();

            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("Text"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("notification"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("toast"))).click();

            // Interaction with WebView
            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("webview"))).click();

            WebElement urlField = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("url")));
            urlField.sendKeys("https://www.lambdatest.com/");

            driver.findElement(AppiumBy.id("find")).click();

            Thread.sleep(2000);
            driver.navigate().back();

            status = "passed";
        } catch (Exception e) {
            System.out.println("Test Error: " + e.getMessage());
            status = "failed";
            throw e;
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