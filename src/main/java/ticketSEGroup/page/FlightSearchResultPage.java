package ticketSEGroup.page;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ticketSEGroup.page.util.UtilPage;
import ticketSEGroup.waitPage.ExplicitPage;

public class FlightSearchResultPage extends UtilPage {
	WebDriver driver;
	ExplicitPage explicitPage;
	By selectFlightButton=By.xpath(".//*[@id='selectAirButton1']");
	By countFlightSections=By.xpath(".//*[@id='productResultWrapper']/div");
	By continueWithoutHotel=By.xpath(".//*[@id='hotelCrossellingProceedButton']");
	public FlightSearchResultPage(WebDriver driver) throws IOException {
		super(driver);
		this.driver=driver;
		explicitPage=new ExplicitPage(driver);
		// TODO Auto-generated constructor stub
	}

	public void clickSelectFlightButton(){
		explicitPage.clickAfterWaitingForVisibility(selectFlightButton);
		//driver.findElement(selectFlightButton).click();

	}
	public void ifHotelPresent(){
		int count=countElementsIfPresent(continueWithoutHotel);
		if(count>0)
		{
			explicitPage.clickAfterWaitingForVisibility(continueWithoutHotel);
		}
	}

	public boolean validateResultedFlightTiming(String time) throws InterruptedException{
		Thread.sleep(10000);
		int count=countElements(countFlightSections);
		System.out.println("FLIGHT CATEGORIES="+count);
		boolean flag=true;
		System.out.println("TIME SET RANGE="+time);
		for(int i=1;i<=count;i++){
			String fetchTime=getTextAfterCheckingVisibility(By.xpath(" .//*[@id='productResultWrapper']/div["+i+"]/div/form/fieldset/div[2]/table/tbody/tr[2]/td[1]/ul[1]/li[1]/div[1]/div[1]"));
			System.out.println("TIME         i ="+fetchTime+"  "+i);
			Thread.sleep(500);
			String ti[]=time.split(" ");
	
			String finalFromTime=ti[1].substring(1,3);
		
			String finalToTime=ti[1].substring(4,6);
		

			String expTime[]=fetchTime.split(":");
			String actualTime=expTime[0];

			System.out.println("EXP FROM TIME="+actualTime);


			if(!(Integer.parseInt(actualTime)>=Integer.parseInt(finalFromTime) && Integer.parseInt(actualTime)<=Integer.parseInt(finalToTime)))
			{
				System.out.println("Failed Case");
				System.out.println("EXP FROM TIME="+actualTime);
				System.out.println("FINAL From TIME TO CHECK RANGE="+finalFromTime);
				System.out.println("FINAL To   TIME TO CHECK RANGE="+finalToTime);

				flag=false;
			break;
			}

		}/*for*/
return flag;		
	}
}
