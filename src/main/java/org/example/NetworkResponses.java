package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v138.network.Network;
import org.openqa.selenium.devtools.v138.network.model.Request;
import org.openqa.selenium.devtools.v138.network.model.Response;

import java.util.Optional;

public class NetworkResponses {
    public static void main(String[] args) {
        ChromeDriver driver=new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty()));
        devTools.addListener(Network.requestWillBeSent(), request->{
            Request req=request.getRequest();
            System.out.println(req.getUrl());
//            req.getHeaders();
        });
        devTools.addListener(Network.responseReceived(), response->{
            Response res= response.getResponse();
//            System.out.println(res.getUrl());
            System.out.println(res.getStatus());
        });
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.cssSelector("a[routerlink*='library']")).click();
    }
}
