package org.example;

import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.URI;
import java.util.function.Predicate;

public class BasicAuthentication {
    //opens the browser without any authentication
    public static void main(String[] args) {
        ChromeDriver driver=new ChromeDriver();
        Predicate<URI> predicateURI=URI->URI.getHost().contains("httpbin.org");
        ((HasAuthentication)driver).register(predicateURI, UsernameAndPassword.of("foo","bar"));
        driver.get("http://httpbin.org/basic-auth/foo/bar");
    }
}
