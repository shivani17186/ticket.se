package ticketSEGroup.waitPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class ExplicitPage {
	WebDriver driver;
	ExplicitPage(){

	}
	public ExplicitPage(WebDriver driver){
		this.driver=driver;
	}

	WebElement element;

	public void clickAfterWaitingForVisibility(By xpath){


		WebDriverWait wait = new WebDriverWait(driver, 10);

		wait.until(ExpectedConditions.elementToBeClickable(xpath)).click();
	}
	public WebElement waitTillVisibility(By xpath){


		WebDriverWait wait = new WebDriverWait(driver, 10);

		//wait.until(ExpectedConditions.elementToBeClickable(xpath));

		return wait.until(ExpectedConditions.elementToBeClickable(xpath));
	}
}
