package com.lambdatest.ltOptions_w3c;

// 1. Updated imports
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class android_ltOptions_w3c {
    String username = System.getenv("LT_USERNAME") == null ? "YOUR_USERNAME" : System.getenv("LT_USERNAME");
    String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "YOUR_ACCESS_KEY" : System.getenv("LT_ACCESS_KEY");
    public String app_id = System.getenv("LT_APP_ID") == null ? "lt://APP10160622431766424164986229" : System.getenv("LT_APP_ID");
    public String grid_url = System.getenv("LT_GRID_URL") == null ? "mobile-hub.lambdatest.com" : System.getenv("LT_GRID_URL");
    public String status = "passed";

    // 2. Changed RemoteWebDriver to AndroidDriver
    public static AndroidDriver driver = null;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("w3c", true);
        ltOptions.put("console", true);
        ltOptions.put("network", false);
        ltOptions.put("visual", true);
        ltOptions.put("autoGrantPermissions", true);
        ltOptions.put("platformName", "android");
        ltOptions.put("deviceName", "Pixel.*");
        ltOptions.put("platformVersion", "12");
        ltOptions.put("app", app_id);
        ltOptions.put("deviceOrientation", "PORTRAIT");
        ltOptions.put("build", "JUNIT_lt:options_w3c");
        ltOptions.put("name", "android_lt:options_w3c");
        ltOptions.put("isRealMobile", true);
        capabilities.setCapability("lt:options", ltOptions);

        // 3. Initialize using AndroidDriver
        driver = new AndroidDriver(new URL("https://" + username + ":" + accessKey + "@" + grid_url + "/wd/hub"), capabilities);
    }

    @Test
    public void testSimple() throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            // 4. Using AppiumBy.id instead of MobileBy
            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("color"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("geoLocation"))).click();

            Thread.sleep(5000);

            // This will now work because AndroidDriver knows how to handle "back"
            driver.navigate().back();

            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("Text"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("notification"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("toast"))).click();

            wait.until(ExpectedConditions.elementToBeClickable(By.id("webview"))).click();
            Thread.sleep(5000);

            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("url"))).sendKeys("https://www.lambdatest.com/");
            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("find"))).click();

            Thread.sleep(5000);
            driver.navigate().back();

            status = "passed";
        } catch (Exception e) {
            System.out.println("Error found: " + e.getMessage());
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