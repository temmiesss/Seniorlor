package com.digital_nomads.talent_lms.drivers;

import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import org.openqa.selenium.WebDriver;

public class Driver {

    public Driver() {

    }

    public static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            switch (ConfigReader.getProperty("browserType").toLowerCase().trim()) {
                case "chrome":
                    driver = ChromeWebDriver.loadChromeDriver();
                    break;
                case "firefox":
                    driver = FireFoxWebDriver.loadFirefoxDriver();
                    break;
                case "edge":
                    driver = EdgeWebDriver.loadEdgeDriver();
                    break;
//                case "safari":
//                    driver = SafariWebDriver.loadSafariDriver();
//                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser type: " + ConfigReader.getProperty("browserType"));
            }
        }
        return driver;
    }

    public static void closeDriver() {
        try {
            if (driver!=null){
                driver.close();
                driver.quit();
                driver=null;
            }
        } catch (Exception e) {
            System.out.println("Error while closing driver");
        }
    }
}
