package com.lambdatest;

import io.appium.java_client.MobileBy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class androidAppAutomation {
    String username = System.getenv("LT_USERNAME") == null ? "LT_USERNAME" : System.getenv("LT_USERNAME"); //Enter the Username here
    String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "LT_ACCESS_KEY" : System.getenv("LT_ACCESS_KEY"); //Enter the Access key here
    public String app_id = System.getenv("LT_APP_ID") == null ? "lt://proverbial-android" : System.getenv("LT_APP_ID");      //Enter your LambdaTest App ID at the place of lt://proverbial-android
    public String grid_url = System.getenv("LT_GRID_URL") == null ? "mobile-hub.lambdatest.com" : System.getenv("LT_GRID_URL");
    public String status = "passed";

    public static RemoteWebDriver driver = null;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("build", "JUNIT Native App automation");
        capabilities.setCapability("name", "Java JUnit Android");
        capabilities.setCapability("platformName", "android");
        capabilities.setCapability("deviceName", "Pixel.*"); //Enter the name of the device here
        capabilities.setCapability("isRealMobile", true);
        capabilities.setCapability("platformVersion", "12");
        capabilities.setCapability("app", app_id); //Enter the App ID here
        capabilities.setCapability("deviceOrientation", "PORTRAIT");
        capabilities.setCapability("network", false);
        capabilities.setCapability("visual", true);

        driver = new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + "@" + grid_url + "/wd/hub"),
                capabilities);
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
            wait.until(ExpectedConditions.elementToBeClickable(By.id("webview"))).click();
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

