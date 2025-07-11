package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CDPCommand {
    public static void main(String[] args) {
        ChromeDriver driver=new ChromeDriver();
        DevTools devTools=driver.getDevTools();
        devTools.createSession();
        Map<String, Object> deviceMetrics=new HashMap<>();
        deviceMetrics.put("width",800);
        deviceMetrics.put("height",1000);
        deviceMetrics.put("deviceScaleFactor",50);
        deviceMetrics.put("mobile",true);
        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride",deviceMetrics);
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.cssSelector("span[class='navbar-toggler-icon']")).click();
        WebElement libraryEle=driver.findElement(By.xpath("//a[@routerlink='/library']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click()",libraryEle);
    }
}
