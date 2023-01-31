package ru.netology;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;
import ru.netology.screens.AppiumMainScreen;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class AppiumTest {

    private AndroidDriver driver;

    @BeforeAll
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "Some name");
        desiredCapabilities.setCapability("appium:app", "C:\\Users\\Dima\\IdeaProjects\\mqa\\mqa-homeworks\\2.2 UI Automator\\sample\\app\\build\\outputs\\apk\\debug\\app-debug.apk");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void inputSpace() {
        AppiumMainScreen main = new AppiumMainScreen(driver);
        String textBefore = main.textBefore.getText();
        main.input.sendKeys(" ");
        main.buttonChange.click();
        String textAfter = main.textBefore.getText();
        assertEquals(textBefore, textAfter);
    }

    @Test
    public void inputNothing() {
        AppiumMainScreen main = new AppiumMainScreen(driver);
        String textBefore = main.textBefore.getText();
        main.buttonChange.click();
        String textAfter = main.textBefore.getText();
        assertEquals(textBefore, textAfter);
    }

    @Test
    public void newActivity() {
        AppiumMainScreen main = new AppiumMainScreen(driver);
        main.input.sendKeys("Hallo");
        main.buttonActivity.click();
        String textAfter = main.activityText.getText();
        assertEquals("Hallo", textAfter);
    }

    @AfterAll
    public void tearDown() {
        driver.quit();
    }
}