package com.example.seleniumdemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;

public class example implements WebDriverListener {

    // Implementing the listener methods
    @Override
    public void beforeClick(WebElement element) {
        System.out.println("Before clicking on: " + element);
    }

    @Override
    public void afterClick(WebElement element) {
        System.out.println("After clicking on: " + element);
    }

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\ARTemp\\automation\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        try {
            // Create the base WebDriver
            WebDriver baseDriver = new ChromeDriver();

            // Create an instance of the listener (Example class)
            WebDriverListener listener = new example();

            // Attach the listener to EventFiringDecorator
            WebDriver decoratedDriver = new EventFiringDecorator<>(listener).decorate(baseDriver);

            // Navigate to the URL
            decoratedDriver.navigate().to("https://www.google.com/");

            // Find the element and click on it
            WebElement ele = decoratedDriver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[3]/center/input[1]"));
            ele.click();  // The listener methods will be triggered

            // Close the driver
            decoratedDriver.quit();

        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
    }
}
