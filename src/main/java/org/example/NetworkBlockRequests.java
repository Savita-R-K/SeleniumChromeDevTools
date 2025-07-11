package org.example;

import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.Immutable;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v138.network.Network;

import java.util.Optional;

import static java.lang.System.currentTimeMillis;

public class NetworkBlockRequests {
    //all the images on the page should be blocked
    public static void main(String[] args) {
        ChromeDriver driver=new ChromeDriver();
        DevTools devTools=driver.getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty()));

        long startTime= System.currentTimeMillis();
        devTools.send(Network.setBlockedURLs(ImmutableList.of("*.jpg","*.css")));
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.linkText("Browse Products")).click();
        driver.findElement(By.linkText("Selenium")).click();
        driver.findElement(By.cssSelector(".add-to-cart")).click();
        System.out.println(driver.findElement(By.cssSelector("p")).getText());
        long endTime= System.currentTimeMillis();

        System.out.println(endTime-startTime);

        //with blocked jpg - time taken for execution-1318
        //without blocked jpg - time taken for execution-2684



    }
}
