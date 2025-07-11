package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

import java.util.HashMap;
import java.util.Map;

public class GeoLocation {
    public static void main(String[] args) throws InterruptedException {
        //35 128
        ChromeDriver driver=new ChromeDriver();
        DevTools devTools=driver.getDevTools();
        devTools.createSession();
        Map<String, Object> coordinates=new HashMap<>();
        coordinates.put("latitude",35);
        coordinates.put("longitude",128);
        coordinates.put("accuracy",1);
        driver.executeCdpCommand("Emulation.setGeolocationOverride",coordinates);
        driver.get("http://google.com");
        driver.findElement(By.name("q")).sendKeys("Netflix", Keys.ENTER);
        Thread.sleep(40000);
        driver.findElements(By.cssSelector(".LC20lb")).get(0).click();
        String title=driver.findElement(By.cssSelector("h1[class=' default-ltr-cache-y1eo5m euy28770']")).getText();
        System.out.println(title);

    }
}
