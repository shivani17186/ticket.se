package ticketSEGroup.page.util;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ticketSEGroup.waitPage.ExplicitPage;

public class UtilPage {

	WebDriver driver;
	ExplicitPage explicitPage;
	public UtilPage(WebDriver driver){
		this.driver=driver;
		explicitPage=new ExplicitPage(driver);
	}
	
	public int countElements(By element){
		explicitPage.waitTillVisibility(element);
		List li=driver.findElements(element);
		
		
		return li.size();
	}
	
	public int 	countElementsIfPresent(By element){
		List li=driver.findElements(element);
		return li.size();
	}
	public void enterText(By by,String text){

		explicitPage.waitTillVisibility(by).sendKeys(text);
	  //  driver.findElement(element).sendKeys(text);
		
	}
	public String getTextAfterCheckingVisibility(By by){

		return explicitPage.waitTillVisibility(by).getText();
	  //  driver.findElement(element).sendKeys(text);
		
	}
	public void clickAftergettingVisible(By element){

		explicitPage.waitTillVisibility(element).click();
	  //  driver.findElement(element).sendKeys(text);
		
	}
}
