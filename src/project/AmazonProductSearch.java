package project;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonProductSearch {

	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.amazon.in");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);

		WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
		searchBox.sendKeys("Samsung");
		WebElement searchBtn = driver.findElement(By.id("nav-search-submit-button"));
		searchBtn.click();
		
		List<WebElement> searchResultNames = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//h2/a/span"));
		List<WebElement> searchResultPrices = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//span[@class='a-price']//span[@class='a-price-whole']"));
		
		for(int i = 0; i < searchResultNames.size(); i++)
		{
			String itemName = searchResultNames.get(i).getText();
			String price = searchResultPrices.get(i).getText();
			System.out.println("Item name: " + itemName + " Price: " + price);
		}
		
		WebElement firstItem = driver.findElement(By.xpath("//div[@data-component-type='s-search-result']//h2/a"));
		firstItem.click();
	}

}
