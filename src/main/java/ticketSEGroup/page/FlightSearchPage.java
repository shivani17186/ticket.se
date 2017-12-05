package ticketSEGroup.page;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import ticketSEGroup.page.util.UtilPage;
import ticketSEGroup.waitPage.ExplicitPage;

//import ticketSEGroup.ticketSEArtifact.DriverBase;

public class FlightSearchPage extends UtilPage{// extends DriverBase  {
	WebDriver driver;
	ExplicitPage explicitPage;
	By source=By.xpath(".//input[@id='airOutStartLocation']");///ancestor::div[1]");
	By destination=By.xpath(".//input[@id='airHomeStartLocation']");

	By firstSourceOption=By.xpath("//ul[@id='ui-id-1']/li[1]/a");
	By secondSourceOption=By.xpath("//ul[@id='ui-id-2']/li[2]/a");
	
	By oneWayRadioButton=By.xpath(".//*[@id='airSearchFormWrapper']/form/fieldset/div[1]/label[2]/div");
	
	By travelDate=By.xpath("//input[@id='airDatepicker1']");
	By travelReturnDate=By.xpath("//input[@id='airDatepicker2']");
	
	By selectMonthAndYear=By.xpath("//select[@class='ui-datepicker-month']");
	By selectTime=By.xpath("//select[@id='airStartTime']");
	By noOfAdults=By.xpath(".//select[@id='airNumberOfAdults']");
	By noOfKids=By.xpath(".//select[@id='numberOfChildrenInfants']");
	By searchFlightButton=By.xpath("//button[@id='airSearchFormSubmitButton']");
	By errorXpath=By.xpath("//p[@class='validate-error-text error-text']");
	String monthYear;
	String date;
	
	public FlightSearchPage(WebDriver driver) throws IOException {
		super(driver);
		this.driver=driver;
		explicitPage=new ExplicitPage(driver);
		// TODO Auto-generated constructor stub
	}
	public void clickOneWay(){
		driver.findElement(oneWayRadioButton).click();
	}
	public void enterSource(){
		driver.findElement(source).click();
		driver.findElement(source).sendKeys("Australien");
		explicitPage.clickAfterWaitingForVisibility(firstSourceOption);
	}

	public void enterDestination(){
		driver.findElement(destination).click();
		driver.findElement(destination).sendKeys("Australien");
		explicitPage.clickAfterWaitingForVisibility(secondSourceOption);
	}
	
	public void chooseTravelDate(){
		driver.findElement(travelDate).click();
		
	}
	public void chooseTravelReturnDate(){
		driver.findElement(travelReturnDate).click();
	
}
	public void acceptDateInDDMMYYYY(String dd,String mm,String yyyy){
		String mon=mapMonth(mm);
		monthYear=mon+" "+yyyy;
		date=dd;
	}
	public void clickTravelDate(){
		chooseTravelMonthAndYear();
		clickDate();
	}
	
	public void 	chooseNumberOfAdults(String no) throws InterruptedException{
		Thread.sleep(2000);
		String chooseAdults=no+" vuxna";
		Select dropdown= new Select(driver.findElement(noOfAdults));
		dropdown.selectByVisibleText(chooseAdults);
	}
	public void chooseNumberOfKids(String no){
		String chooseKids=no+" barn";
		Select dropdown= new Select(driver.findElement(noOfKids));
		dropdown.selectByVisibleText(chooseKids);
	}
	public void chooseKidsAges(int no){
		for(int i=0;i<(no-1);i++){
			Select dropdown= new Select(driver.findElement(By.xpath(".//select[@id='airChildrenAges_"+i+"']")));			
			dropdown.selectByVisibleText("5");
		}
	}
	
	public void clickTimeDropDown(String timeText){
		Select dropdown= new Select(driver.findElement(selectTime));
		dropdown.selectByVisibleText(timeText);
	}
	public void chooseTravelMonthAndYear(){
		Select dropdown= new Select(driver.findElement(selectMonthAndYear));
		dropdown.selectByVisibleText(monthYear);
	}
	public void clickDate(){
		driver.findElement(By.xpath("//a[text()='"+date+"']")).click();
	}
	
	public void clickSearchFlightButton(){
		clickAftergettingVisible(searchFlightButton);
	
	}
	
	public boolean verifyErrorMessageForNotEnteringDestination(String message){
		
		String text=getTextAfterCheckingVisibility(errorXpath);
		if(text.equals(message))
			return true;
		else
			return false;
	}
	
	String  mapMonth(String mm){
		HashMap<String,String> map=new HashMap();
		map.put("01","jan");
		map.put("02","feb");
		map.put("03","mar");
		map.put("04","apr");
		map.put("05","may");
		map.put("06","jun");
		map.put("07","jul");
		map.put("08","aug");
		map.put("09","sep");
		map.put("10","oct");
		map.put("11","nov");
		map.put("12","dec");
		
		return map.get(mm);
	}
}
