package com.lambdatest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class iosWeb {

    String username = System.getenv("LT_USERNAME") == null ? "LT_USERNAME" : System.getenv("LT_USERNAME"); //Enter the Username here
    String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "LT_ACCESS_KEY" : System.getenv("LT_ACCESS_KEY"); //Enter the Access key here
    public String grid_url = System.getenv("LT_GRID_URL") == null ? "mobile-hub.lambdatest.com" : System.getenv("LT_GRID_URL");
    public String status = "passed";

    public static RemoteWebDriver driver = null;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("build", "JUNIT Native Web automation");
        capabilities.setCapability("name", "Java JUnit iOS");
        capabilities.setCapability("platformName", "ios");
        capabilities.setCapability("deviceName", "iPhone .*");
        capabilities.setCapability("isRealMobile", true);
        capabilities.setCapability("platformVersion", "15");
        capabilities.setCapability("console", true);
        capabilities.setCapability("network", false);
        capabilities.setCapability("visual", true);

        driver = new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + "@" + grid_url + "/wd/hub"),
                capabilities);
    }

    @Test
    public void testSimple() throws Exception {
        try {
            driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
            driver.get("https://mfml.in/api/getInfo");
            driver.getWindowHandles().forEach(handle -> System.out.println(handle));
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.elementToBeClickable(By.id("resolution"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("location"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("details"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("timezone"))).click();

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
