package ticketSEGroup.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ticketSEGroup.page.util.UtilPage;
import ticketSEGroup.waitPage.ExplicitPage;

public class FinalFlightBookingPage extends UtilPage{
	WebDriver driver;
	ExplicitPage explicitPage;
	By message=By.xpath("//*[@id='main']/div/div/div[1]/div[1]/div[2]/h2");
	public FinalFlightBookingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		explicitPage=new ExplicitPage(driver);
		// TODO Auto-generated constructor stub
	}
	public boolean bookingConfirmationMessageDisplayed(){
		String text=getTextAfterCheckingVisibility(message);
		System.out.println("text="+text);
		if(text.contains("ordernummer"))
			return true;
		else 
			return false;

	}

}
