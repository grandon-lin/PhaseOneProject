package project;

import java.util.List;
import java.util.Set;
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
		
		String parentWin = driver.getWindowHandle();
		
		List<WebElement> searchResultNames = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//h2/a/span"));
		List<WebElement> searchResultPrices = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//span[@class='a-price']//span[@class='a-price-whole']"));
		
		for(int i = 0; i < searchResultNames.size(); i++)
		{
			String itemName = searchResultNames.get(i).getText();
			String price = searchResultPrices.get(i).getText();
			System.out.println("Item name: " + itemName + " Price: " + price);
		}
		
		searchResultNames.get(0).click();
		String firstItemName = searchResultNames.get(0).getText();		
		
		Set<String> allWindows = driver.getWindowHandles();
		
		for(String win : allWindows)
		{			
			if(!win.equals(parentWin))
			{
				driver.switchTo().window(win);
			}
		}
		
		String productName = driver.findElement(By.id("productTitle")).getText();
		if(firstItemName.equals(productName))
		{
			System.out.println("It's the same product");
		}
		else
		{
			System.out.println("It's not the same product");
		}
		
		driver.quit();
	}

}
