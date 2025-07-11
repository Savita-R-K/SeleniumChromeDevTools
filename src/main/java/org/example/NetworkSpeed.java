package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v138.network.model.ConnectionType;
import org.openqa.selenium.devtools.v138.network.Network;

import java.util.Optional;


public class NetworkSpeed {
    //making the testcase to pass which is failing due to slow network, high traffic conditions
    //making the browser to delay as per the response for 2-3 sec
    public static void main(String[] args) {
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));
        long startTime = System.currentTimeMillis();


        devTools.send(Network.emulateNetworkConditions(false, 1000, 20000, 10000, Optional.of(ConnectionType.ETHERNET), Optional.empty(), Optional.empty(), Optional.empty()));

        //or
//        making the internet to disconnect and logging the error caused
//        Error internet disconnected
//        devTools.send(Network.emulateNetworkConditions(true, 1000, 20000, 10000, Optional.of(ConnectionType.ETHERNET), Optional.empty(), Optional.empty(), Optional.empty()));
//        devTools.addListener(Network.loadingFailed(),loadingFailed -> {
//            System.out.println(loadingFailed.getErrorText());
//            System.out.println(loadingFailed.getTimestamp());
//        });


        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.cssSelector("a[routerlink*='library']")).click();
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);

        //latency:11164 -making the browser to load with a latency of 1000ms
        //without network latency:1004
    }
}
