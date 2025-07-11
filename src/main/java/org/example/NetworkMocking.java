package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v138.fetch.Fetch;

import java.util.Optional;

public class NetworkMocking {
    //you should see only one book
    public static void main(String[] args) throws InterruptedException {
        ChromeDriver driver=new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Fetch.enable(Optional.empty(),Optional.empty()));
        devTools.addListener(Fetch.requestPaused(),requestPaused -> {
            if(requestPaused.getRequest().getUrl().contains("shetty")){
                String mockedUrl=requestPaused.getRequest().getUrl().replace("=shetty","=BadGuy");
                System.out.println(mockedUrl);
                devTools.send(Fetch.continueRequest(requestPaused.getRequestId(),Optional.of(mockedUrl),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty()));
            }else{
                devTools.send(Fetch.continueRequest(requestPaused.getRequestId(),Optional.of(requestPaused.getRequest().getUrl()),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty()));
            }
        });
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.cssSelector("a[routerlink*='library']")).click();
        Thread.sleep(3000);
        System.out.println(driver.findElement(By.cssSelector("p")).getText());
    }
}
