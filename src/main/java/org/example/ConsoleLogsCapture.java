package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

public class ConsoleLogsCapture {
    //when the error are due to wrong inputs or the other
    //Used to known whether it is a bug-real error or some other error

    public static void main(String[] args) {
        ChromeDriver driver=new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.linkText("Browse Products")).click();
        driver.findElement(By.linkText("Selenium")).click();
        driver.findElement(By.cssSelector(".add-to-cart")).click();
        driver.findElement(By.linkText("Cart")).click();
        driver.findElement(By.id("exampleInputEmail1")).clear();
        //css error thrown--->Parameter is not a number
        driver.findElement(By.id("exampleInputEmail1")).sendKeys("2");

        for (LogEntry logEntry : driver.manage().logs().get(LogType.BROWSER)) {
            System.out.println(logEntry.getMessage());
        }


    }

}
