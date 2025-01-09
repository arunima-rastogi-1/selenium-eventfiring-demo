package com.example.seleniumdemo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class LongScreenshotExample {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\ARTemp\\automation\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        try {

            driver.get("https://www.google.com/search?q=apple&sca_esv=aca0fd80300fec32&sxsrf=ADLYWIL0AwkUPQab6KYB8IZkCW5vJ5GAVA%3A1735580647962&source=hp&ei=59tyZ5GmOLyYseMP3OCg-Aw&iflsig=AL9hbdgAAAAAZ3Lp91BJRB17vbD7viY3EP1zFuvEZoUq&ved=0ahUKEwjRtf7fhdCKAxU8TGwGHVwwCM8Q4dUDCBk&uact=5&oq=apple&gs_lp=Egdnd3Mtd2l6IgVhcHBsZTIEECMYJzILEC4YgAQYkQIYigUyCxAAGIAEGJECGIoFMg4QLhiABBixAxjRAxjHATIIEAAYgAQYsQMyCxAuGIAEGLEDGOUEMgsQLhiABBixAxjlBDIIEAAYgAQYsQMyBRAAGIAEMgUQABiABEjMBFAAWMIDcAB4AJABAJgBkQGgAZwEqgEDMC40uAEDyAEA-AEBmAIEoAKrBMICChAjGIAEGCcYigXCAhEQLhiABBiRAhjRAxjHARiKBcICDhAAGIAEGLEDGIMBGIoFwgIREC4YgAQYsQMY0QMYgwEYxwHCAgsQABiABBixAxiDAcICCBAuGIAEGOUEwgILEAAYgAQYsQMYigWYAwCSBwMwLjSgB_Uy&sclient=gws-wiz");


            Screenshot screenshot = new AShot()
                    .shootingStrategy(ShootingStrategies.viewportPasting(1000))
                    .takeScreenshot(driver);


            ImageIO.write(screenshot.getImage(), "PNG", new File("C:\\ARTemp\\cucumber\\fullPageScreenshot.png"));

            System.out.println("Full-page screenshot saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            
            driver.quit();
        }
    }
}
