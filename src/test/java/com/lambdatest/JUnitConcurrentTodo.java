package com.lambdatest;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
 
@RunWith(Parallelized.class)
public class JUnitConcurrentTodo
{
    String username = System.getenv("LT_USERNAME") == null ? "Your LT Username" : System.getenv("LT_USERNAME");
    String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "Your LT AccessKey" : System.getenv("LT_ACCESS_KEY");
    public String gridURL = "@beta-hub.lambdatest.com/wd/hub";
 
     public String platformName;
     public String deviceName;
     public String platformVersion;
     public RemoteWebDriver driver = null;
     public String status = "passed";
    
     @Parameterized.Parameters
     public static LinkedList<String[]> getEnvironments() throws Exception
     {
        LinkedList<String[]> env = new LinkedList<String[]>();
        env.add(new String[]{"Android", "Galaxy S21", "12"});
        env.add(new String[]{"Android","OnePlus 6T","9"});
        env.add(new String[]{"ios","iPhone 12","15.0" });

         return env;
    }
   
    public JUnitConcurrentTodo(String platformName, String deviceName, String platformVersion)
     {
        this.platformName = platformName;
        this.deviceName = deviceName;
        this.platformVersion = platformVersion;
     }
    @Before
    public void setUp() throws Exception
    {
       DesiredCapabilities capabilities = new DesiredCapabilities();


        capabilities.setCapability("build", "JUNIT Native App Parallel");

        capabilities.setCapability("name", deviceName+"/"+platformVersion);

        capabilities.setCapability("platformName", platformName);

        capabilities.setCapability("deviceName", deviceName);

        capabilities.setCapability("platformVersion",platformVersion); // If this cap isn't specified, it will just get the any available one

        capabilities.setCapability("isRealMobile", true);

        if(platformName=="Android")
        {
            capabilities.setCapability("app", "lt://APP100201841648497755622971");//APK file
        }
        else if (platformName=="ios")
        {
            capabilities.setCapability("app", "lt://APP100201841648669217490986");//IPA file
        }

        capabilities.setCapability("deviceOrientation", "PORTRAIT");


        // capabilities.setCapability("network", true); // To enable network logs
        // capabilities.setCapability("visual", true); // To enable step by step screenshot
        // capabilities.setCapability("video", true); // To enable video recording
        // capabilities.setCapability("console", true); // To capture console logs
        try
        {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + gridURL), capabilities);
        }
        catch (MalformedURLException e)
        {
            System.out.println("Invalid grid URL");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void testParallel() throws Exception
    {
       try
       {
           WebDriverWait color = new WebDriverWait(driver, 30);
           color.until(ExpectedConditions.elementToBeClickable(MobileBy.id("color")));
           driver.findElementById("color").click();


           WebDriverWait geo =  new WebDriverWait(driver, 30);
           geo.until(ExpectedConditions.elementToBeClickable(MobileBy.id("geoLocation")));
           driver.findElementById("geoLocation").click();
           Thread.sleep(5000);
           driver.navigate().back();


           WebDriverWait text = new WebDriverWait(driver, 30);
           text.until(ExpectedConditions.elementToBeClickable(MobileBy.id("Text")));
           driver.findElementById("Text").click();


           WebDriverWait nf =  new WebDriverWait(driver, 30);
           nf.until(ExpectedConditions.elementToBeClickable(MobileBy.id("notification")));
           driver.findElementById("notification").click();


           WebDriverWait toast = new WebDriverWait(driver, 30);
           toast.until(ExpectedConditions.elementToBeClickable(MobileBy.id("toast")));
           driver.findElementById("toast").click();



           WebDriverWait browser = new WebDriverWait(driver, 30);
           browser.until(ExpectedConditions.elementToBeClickable(By.id("webview")));
           driver.findElementById("webview").click();
           Thread.sleep(10000);


           WebDriverWait el7 =  new WebDriverWait(driver, 30);
           el7.until(ExpectedConditions.elementToBeClickable(MobileBy.id("url")));
           driver.findElementById("url").sendKeys("https://www.lambdatest.com/");


           WebDriverWait el8 =  new WebDriverWait(driver, 30);
           el8.until(ExpectedConditions.elementToBeClickable(MobileBy.id("find")));
           driver.findElementById("find").click();
           Thread.sleep(5000);
           driver.navigate().back();

           status="passed";
       } catch (Exception e)
       {
           System.out.println(e.getMessage());
           status="failed";
       }
    }  
    @After
    public void tearDown() throws Exception
    {
       if (driver != null)
       {
            driver.executeScript("lambda-status=" + status);
            driver.quit();
       }
    }
}
