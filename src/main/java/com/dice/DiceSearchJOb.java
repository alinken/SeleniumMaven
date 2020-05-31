package com.dice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceSearchJOb {
	public static void main(String[] args) {
		
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		//fullscreen
		driver.manage().window().fullscreen();
		
		//set universal wait time in case web page is slow
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		String url = "https://www.usajobs.gov";
		driver.get(url);
		
		
		String actualTitle = driver.getTitle();
		String expectedTitle = "JobSearch for Technology Professionals | Dice,com";
		
		if (actualTitle.equals(expectedTitle)) {
			System.out.println("Step PASS.Dice homepage successfully loaded");
		}else {
			System.out.println("Step FAIL.Dice homepage did not load sucessfully loaded");
			throw new RuntimeException ("Step FAIl.Dice homepage did not load successfully");
		}
		
		String keyword = "java developer";
		driver.findElement(By.name("k")).sendKeys(keyword);;
		
		String location = "22102";
		driver.findElement(By.id("nav-location")).clear();
		driver.findElement(By.id("nav-location")).sendKeys(location);
		
		driver.findElement(By.name("Search")).click();
		
		String count = driver.findElement(By.id("posiCountId")).getText();
		System.out.println(count);
		//ensure count is more than0;
		int countResult = Integer.parseInt(count.replace(",", ""));
		
		if (countResult >0) {
			System.out.println("Step Pass: Keyword : " + keyword + " search returned " + countResult + "results in " + location);
		}else {
			System.out.println("Step Fail: Keyword : " + keyword + " search returned " + countResult + "results in " + location);
			
		}
		
		
		driver.close();
		
		System.out.println("TEst completed -" + LocalDateTime.now());
	}

}
