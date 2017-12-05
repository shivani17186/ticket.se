package ticketSEGroup.page;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ticketSEGroup.page.util.UtilPage;
import ticketSEGroup.waitPage.ExplicitPage;

public class PassengerInputPage extends UtilPage{
	WebDriver driver;
	ExplicitPage explicitPage;
	By selectFlightButton=By.xpath(".//*[@id='selectAirButton1']");
	By numberOfTraveller=By.xpath("//input[starts-with(@id,'traveller') and contains(@id,'firstname') ]");

	By countOfRadioButtonForAdult=By.xpath(".//*[@id='travellerForm']/div/fieldset[2]/div[3]/div/div/div[1]/label/div");
	By countOfRadioButtonForKids=By.xpath(".//*[@id='travellerForm']/div/fieldset[2]/div[4]/div/div/div[1]/label/div");
	By travellerFormEmail=By.xpath(".//*[@id='travellerFormEmail']");
	By travellerFormMobileNo=By.xpath("//input[@id='travellerFormMobilePhone']");
	By travellerTermsAndCondition=By.xpath("//input[@id='travellerAcceptTermsAndConditions']/following::div[1]");
	By continueBooking=By.xpath("//button[@id='submitTravellerForm']");

	public PassengerInputPage(WebDriver driver) throws IOException {
		super(driver);
		this.driver=driver;
		explicitPage=new ExplicitPage(driver);
		// TODO Auto-generated constructor stub
	}
	public boolean enterAllPassengersFirstAndLastNameAsOnPassport(){
		int count=countElements(numberOfTraveller);
		System.out.println("COUNT="+count);
		if(count>0){
			for(int i=0;i<count;i++){
				String text=figureToWords(i+1);
				System.out.println("TEXT="+text);
				enterText(By.xpath(".//*[@id='traveller"+i+"firstname']"),"first"+text);
				enterText(By.xpath(".//*[@id='traveller"+i+"lastname']"),"last"+text);
			}
			return true;
		}
		else
			return false;
	}
	public void enterBirthdaysForKids(){
		int countRadioAdult=countElements(countOfRadioButtonForAdult);
		int countNoOfKids=countElementsIfPresent(countOfRadioButtonForKids);
		System.out.println("No Of Kids for inputing bday="+countNoOfKids);
		if(countNoOfKids>0){
			for(int i=countRadioAdult;i<(countNoOfKids+countRadioAdult);i++){
				System.out.println("DATE="+"//input[@id='traveller"+i+"birthDate']");
				String text=getTextAfterCheckingVisibility(By.xpath(".//*[@id='travellerForm']/div["+(i+1)+"]/fieldset[1]/div[1]/div/div"));
				System.out.println("IS INFANT="+text);
				
				if(!(text.contains("Barn") ||text.contains("Vuxen")))
					{
					System.out.println("INFANT IT IS"+text+" No="+i);
					enterText(By.xpath("//input[@id='traveller"+i+"birthDate']"),"20170911");
					}
				else
				enterText(By.xpath("//input[@id='traveller"+i+"birthDate']"),"20120911");

			}
		}
	}
	public boolean clickRadioButton(){
		int countRadioAdult=countElements(countOfRadioButtonForAdult);
		System.out.println("No Of Adults="+countRadioAdult);
		for(int i=1;i<=countRadioAdult;i++){
			System.out.println(".//*[@id='travellerForm']/div["+i+"]/fieldset[2]/div[3]/div/div/div[1]/label/div");
			if(i==countRadioAdult)
				clickAftergettingVisible(By.xpath(".//*[@id='travellerForm']/div["+i+"]/fieldset[2]/div[3]/div/div/div[2]/label/div"));
			else
			clickAftergettingVisible(By.xpath(".//*[@id='travellerForm']/div["+i+"]/fieldset[2]/div[3]/div/div/div[1]/label/div"));
			
			System.out.println("CLICKED RADIO BUTTON");
		}
		int countRadioKid=countElementsIfPresent(countOfRadioButtonForKids);
		System.out.println("No Of Kids="+countRadioKid);
		if(countRadioKid>0){
			for(int i=(countRadioAdult+1);i<=(countRadioKid+countRadioAdult);i++){
				System.out.println("XPATH FOR THE RADIO BUTTON OF KIDS"+".//*[@id='travellerForm']/div["+i+"]/fieldset[2]/div[4]/div/div/div[1]/label/div");
				clickAftergettingVisible(By.xpath(".//*[@id='travellerForm']/div["+i+"]/fieldset[2]/div[4]/div/div/div[1]/label/div"));
			}
		return true;
		}
		else{
			System.out.println("NO Kids presnet in this journey");
			return false;
		}
	}
	public void enterEmail(String email){
		enterText(travellerFormEmail,email);
	}

	public void enterMobileNumber(String mobileNo){
		enterText(travellerFormMobileNo,mobileNo);
	}
	public void clickTermsAndCondition(){
		clickAftergettingVisible(travellerTermsAndCondition);
	}
	public void clickContinueBooking(){
		clickAftergettingVisible(continueBooking);
	}

	public String figureToWords(int no){
		HashMap<String,String> map=new HashMap();
		map.put("1","ONE");
		map.put("2","TWO");
		map.put("3","THREE");
		map.put("4","FOUR");
		map.put("5","FIVE");

		return map.get(String.valueOf(no));

	}

}
