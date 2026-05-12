# JUnit With Appium — TestMu AI (Formerly LambdaTest)
![pw](https://img.shields.io/badge/Junit5-25A162?style=for-the-badge&logo=junit5&logoColor=white)

<p align="center">
<img height="500" src="https://user-images.githubusercontent.com/95698164/171858482-4986e439-2a31-4947-91e7-93fa9eae7652.png">
</p>

<p align="center">
  <a href="https://www.testmuai.com/blog/?utm_source=github&utm_medium=repo&utm_campaign=LT-appium-java-junit" target="_bank">Blog</a>
  &nbsp; &#8901; &nbsp;
  <a href="https://www.testmuai.com/support/docs/?utm_source=github&utm_medium=repo&utm_campaign=LT-appium-java-junit" target="_bank">Docs</a>
  &nbsp; &#8901; &nbsp;
  <a href="https://www.testmuai.com/learning-hub/?utm_source=github&utm_medium=repo&utm_campaign=LT-appium-java-junit" target="_bank">Learning Hub</a>
  &nbsp; &#8901; &nbsp;
  <a href="https://www.testmuai.com/newsletter/?utm_source=github&utm_medium=repo&utm_campaign=LT-appium-java-junit" target="_bank">Newsletter</a>
  &nbsp; &#8901; &nbsp;
  <a href="https://www.testmuai.com/certifications/?utm_source=github&utm_medium=repo&utm_campaign=LT-appium-java-junit" target="_bank">Certifications</a>
  &nbsp; &#8901; &nbsp;
  <a href="https://www.youtube.com/@TestMuAI" target="_bank">YouTube</a>
</p>
&emsp;
&emsp;
&emsp;

_JUnit is a popular unit testing framework for Java, and was an important part of test-driven development. It belongs to a family of unit testing frameworks known as xUnit. Perform [JUnit test on TestMu AI's online cloud](https://www.testmuai.com/appium-mobile-testing?utm_source=github&utm_medium=repo&utm_campaign=LT-appium-java-junit)._

_Learn the basics of [Appium testing on the TestMu AI platform](https://www.testmuai.com/support/docs/getting-started-with-appium-testing/?utm_source=github&utm_medium=repo&utm_campaign=LT-appium-java-junit)._

[<img height="53" width="200" src="https://user-images.githubusercontent.com/70570645/171866795-52c11b49-0728-4229-b073-4b704209ddde.png">](https://accounts.lambdatest.com/register?utm_source=github&utm_medium=repo&utm_campaign=LT-appium-java-junit)

## Table of Contents

- [Pre-requisites](#pre-requisites)
- [Run Your First Test](#run-your-first-test)
- [Executing The Test](#executing-the-test)

## Pre-requisites

Before you can start performing App automation testing with Appium, you would need to follow these steps:

- Make sure you have Appium’s [Java client library](https://github.com/appium/java-client) installed.

### Clone The Sample Project

Clone the TestMu AI’s :link: [LT-appium-java-junit](https://github.com/LambdaTest/LT-appium-java-junit) repository and navigate to the code directory as shown below:

```bash
git clone https://github.com/LambdaTest/LT-appium-java-junit
cd LT-appium-java-junit
```

### Setting Up Your Authentication

Make sure you have your TestMu AI credentials with you to run test automation scripts on LambdaTest. To obtain your access credentials, [purchase a plan](https://billing.lambdatest.com/billing/plans?utm_source=github&utm_medium=repo&utm_campaign=LT-appium-java-junit) or access the [Automation Dashboard](https://appautomation.lambdatest.com/?utm_source=github&utm_medium=repo&utm_campaign=LT-appium-java-junit).

Set TestMu AI `Username` and `Access Key` in environment variables.

**For Linux/macOS:**

```bash
export LT_USERNAME="YOUR_LAMBDATEST_USERNAME" \
export LT_ACCESS_KEY="YOUR_LAMBDATEST_ACCESS_KEY"
```

**For Windows:**

```bash
set LT_USERNAME="YOUR_LAMBDATEST_USERNAME" `
set LT_ACCESS_KEY="YOUR_LAMBDATEST_ACCESS_KEY"
```

### Upload Your Application

Upload your **_iOS_** application (.ipa file) or **_android_** application (.apk file) to the TestMu AI servers using our **REST API**. You need to provide your **Username** and **AccessKey** in the format `Username:AccessKey` in the **cURL** command for authentication. Make sure to add the path of the **appFile** in the cURL request. Here is an example cURL request to upload your app using our REST API:

**Using App File:**

**For Linux/macOS:**

```bash
curl -u "YOUR_LAMBDATEST_USERNAME:YOUR_LAMBDATEST_ACCESS_KEY" \
--location --request POST 'https://manual-api.lambdatest.com/app/upload/realDevice' \
--form 'name="Android_App"' \
--form 'appFile=@"/Users/macuser/Downloads/proverbial_android.apk"'
```

**For Windows:**

```bash
curl -u "YOUR_LAMBDATEST_USERNAME:YOUR_LAMBDATEST_ACCESS_KEY" -X POST "https://manual-api.lambdatest.com/app/upload/realDevice" -F "appFile=@"/Users/macuser/Downloads/proverbial_android.apk""
```

**Using App URL:**

**For Linux/macOS:**

```bash
curl -u "YOUR_LAMBDATEST_USERNAME:YOUR_LAMBDATEST_ACCESS_KEY" \
--location --request POST 'https://manual-api.lambdatest.com/app/upload/realDevice' \
--form 'name="Android_App"' \
--form 'url="https://prod-mobile-artefacts.lambdatest.com/assets/docs/proverbial_android.apk"'
```

**For Windows:**

```bash
curl -u "YOUR_LAMBDATEST_USERNAME:YOUR_LAMBDATEST_ACCESS_KEY" -X POST "https://manual-api.lambdatest.com/app/upload/realDevice" -d "{"url":"https://prod-mobile-artefacts.lambdatest.com/assets/docs/proverbial_android.apk","name":"sample.apk"}"
```

**Tip:**

- If you do not have any **.apk** or **.ipa** file, you can run your sample tests on TestMu AI by using our sample :link: [Android app](https://prod-mobile-artefacts.lambdatest.com/assets/docs/proverbial_android.apk) or sample :link: [iOS app](https://prod-mobile-artefacts.lambdatest.com/assets/docs/proverbial_ios.ipa).
- Response of above cURL will be a **JSON** object containing the `App URL` of the format - <lt://APP123456789123456789> and will be used in the next step.

## Run Your First Test

Once you are done with the above-mentioned steps, you can initiate your first JUnit test on LambdaTest.

**Test Scenario**: Check out [android.java](https://github.com/LambdaTest/LT-appium-java-junit/blob/master/src/test/java/com/lambdatest/android.java) file to view the sample test script for android and [ios.java](https://github.com/LambdaTest/LT-appium-java-junit/blob/master/src/test/java/com/lambdatest/ios.java) for iOS.

### Configuring Your Test Capabilities

You can update your custom capabilities in test scripts. In this sample project, we are passing platform name, platform version, device name and app url (generated earlier) along with other capabilities like build name and test name via capabilities object. The capabilities object in the sample code are defined as:

<Tabs className="docs__val">
<TabItem value="android-config" label="Android" default>

```java
DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("build", "JUNIT Native App automation");
        capabilities.setCapability("name", "Java JUnit Android Pixel 6");
        capabilities.setCapability("platformName", "android");
        capabilities.setCapability("deviceName", "Pixel 6"); //Enter the name of the device here
        capabilities.setCapability("isRealMobile", true);
        capabilities.setCapability("platformVersion","12");
        capabilities.setCapability("app","YOUR_APP_URL"); //Enter the App ID here
        capabilities.setCapability("deviceOrientation", "PORTRAIT");
        capabilities.setCapability("console",true);
        capabilities.setCapability("network",true);
        capabilities.setCapability("visual",true);
```

</TabItem>

<TabItem value="ios-config" label="iOS" default>

```java
DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("build", "JUNIT Native App automation");
        capabilities.setCapability("name", "Java JUnit iOS iPhone 12");
        capabilities.setCapability("platformName", "ios");
        capabilities.setCapability("deviceName", "iPhone 12");
        capabilities.setCapability("isRealMobile", true);
        capabilities.setCapability("platformVersion","15");
        capabilities.setCapability("app","YOUR_APP_URL"); //Enter the APP_ID here
        capabilities.setCapability("deviceOrientation", "PORTRAIT");
        capabilities.setCapability("console",true);
        capabilities.setCapability("network",true);
        capabilities.setCapability("visual",true);
```

</TabItem>

</Tabs>

**Info Note:**

- You must add the generated **APP_URL** to the `"app"` capability in the config file.
- You can generate capabilities for your test requirements with the help of our inbuilt **[Capabilities Generator tool](https://www.testmuai.com/capabilities-generator/beta/index.html?utm_source=github&utm_medium=repo&utm_campaign=LT-appium-java-junit)**. A more Detailed Capability Guide is available [here](https://www.testmuai.com/support/docs/desired-capabilities-in-appium/?utm_source=github&utm_medium=repo&utm_campaign=LT-appium-java-junit).

## Executing The Test

Execute the following commands to install the required dependencies:

```bash
mvn clean
```

The tests can be executed in the terminal using the following command:

<Tabs className="docs__val">
<TabItem value="android-exec" label="Android" default>

```bash
mvn test -P android
```

</TabItem>

<TabItem value="ios-exec" label="iOS" default>

```bash
mvn test -P ios
```

</TabItem>
</Tabs>

**Info:** Your test results would be displayed on the test console (or command-line interface if you are using terminal/cmd) and on the :link: [TestMu AI App Automation Dashboard](https://appautomation.lambdatest.com/build?utm_source=github&utm_medium=repo&utm_campaign=LT-appium-java-junit).

## Additional Links

- [Advanced Configuration for Capabilities](https://www.testmuai.com/support/docs/desired-capabilities-in-appium/?utm_source=github&utm_medium=repo&utm_campaign=LT-appium-java-junit)
- [How to test locally hosted apps](https://www.testmuai.com/support/docs/testing-locally-hosted-pages/?utm_source=github&utm_medium=repo&utm_campaign=LT-appium-java-junit)
- [How to integrate TestMu AI with CI/CD](https://www.testmuai.com/support/docs/integrations-with-ci-cd-tools/?utm_source=github&utm_medium=repo&utm_campaign=LT-appium-java-junit)

## Documentation & Resources :books:

Visit the following links to learn more about TestMu AI's features, setup and tutorials around test automation, mobile app testing, responsive testing, and manual testing.

- [TestMu AI Documentation](https://www.testmuai.com/support/docs/?utm_source=github&utm_medium=repo&utm_campaign=LT-appium-java-junit)
- [TestMu AI Blog](https://www.testmuai.com/blog/?utm_source=github&utm_medium=repo&utm_campaign=LT-appium-java-junit)
- [TestMu AI Learning Hub](https://www.testmuai.com/learning-hub/?utm_source=github&utm_medium=repo&utm_campaign=LT-appium-java-junit)

## TestMu AI Community :busts_in_silhouette:

The [TestMu AI Community](https://community.testmuai.com/?utm_source=github&utm_medium=repo&utm_campaign=LT-appium-java-junit) allows people to interact with tech enthusiasts. Connect, ask questions, and learn from tech-savvy people. Discuss best practises in web development, testing, and DevOps with professionals from across the globe 🌎

## What's New At TestMu AI ❓

To stay updated with the latest features and product add-ons, visit [Changelog](https://changelog.lambdatest.com/)

## 🚀 [LambdaTest is Now TestMu AI](https://www.testmuai.com/lambdatest-is-now-testmuai/)

👋 Welcome to TestMu AI, the next evolution of LambdaTest. As of January 2026, LambdaTest has officially rebranded to TestMu AI. We have evolved from a cross-browser testing cloud into a unified, AI-native quality engineering platform designed for the modern DevOps era.

Whether you have been part of the LambdaTest community for years or are just discovering TestMu AI, our mission remains the same: to help you ship faster with high-scale test execution, autonomous testing, and deep quality analytics.

**🔄 Our Rebrand Journey**

We chose the name TestMu AI to reflect our shift towards intelligent, autonomous testing. While our identity has changed, our core technology and commitment to the testing community stay the same.

**✨ Specialties**

- 🤖 AI-Native Test Execution (Formerly LambdaTest)
- ⚡ Autonomous Test Automation
- 🌐 Cross-Browser & Mobile Testing
- 📊 Unified Quality Intelligence

👉 Find [LambdaTest's New Home](https://www.testmuai.com/).