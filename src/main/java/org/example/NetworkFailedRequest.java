package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v138.fetch.Fetch;
import org.openqa.selenium.devtools.v138.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v138.network.model.ErrorReason;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class NetworkFailedRequest {
    //you should not see any books in library page
    public static void main(String[] args) {
        ChromeDriver driver=new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        Optional<List<RequestPattern>> patternList= Optional.of(Arrays.asList(new RequestPattern(Optional.of("*GetBook*"),Optional.empty(),Optional.empty())));
        devTools.send(Fetch.enable(patternList,Optional.empty()));
        devTools.addListener(Fetch.requestPaused(),requestPaused -> {
            devTools.send(Fetch.failRequest(requestPaused.getRequestId(), ErrorReason.FAILED));
        });

        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.cssSelector("a[routerlink*='library']")).click();
    }
}
