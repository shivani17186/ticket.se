package ticketSEGroup.ticketSEArtifact;



import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ticketSEGroup.page.FinalFlightBookingPage;
import ticketSEGroup.page.FlightSearchPage;
import ticketSEGroup.page.FlightSearchResultPage;
import ticketSEGroup.page.PassengerInputPage;
import ticketSEGroup.ticketSEArtifact.DriverBase;
/*import page.BookingPage;
import page.LoginPage;
 */
public class FlightBookingTest extends DriverBase{
	FlightSearchPage flightSearchPage=new FlightSearchPage(driver);
	FlightSearchResultPage flightSearchResultPage=new FlightSearchResultPage(driver);
	PassengerInputPage passengerInputPage=new PassengerInputPage(driver);
	FinalFlightBookingPage finalFlightBookingPage=new FinalFlightBookingPage(driver);

	public FlightBookingTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}


	@Test
	public void testValidationNotReturnDate() throws InterruptedException, ParseException{

		//Enter source and Destination
		flightSearchPage.enterSource();
		flightSearchPage.enterDestination();

		//Enter travel start date
		flightSearchPage.chooseTravelDate();
		flightSearchPage.acceptDateInDDMMYYYY("18","01","2018");
		flightSearchPage.clickTravelDate();

		//Destination date is missing

		flightSearchPage.clickSearchFlightButton();

		Assert.assertTrue(flightSearchPage.verifyErrorMessageForNotEnteringDestination("Ogiltigt datum"));
	}
	@Test
	public void testValidationDestinationMissing() throws InterruptedException, ParseException{

		//Enter source and Destination
		flightSearchPage.enterSource();
		//	flightSearchPage.enterDestination();

		//Enter travel start date
		flightSearchPage.chooseTravelDate();
		flightSearchPage.acceptDateInDDMMYYYY("18","01","2018");
		flightSearchPage.clickTravelDate();

		//Enter return date
		flightSearchPage.chooseTravelReturnDate();
		flightSearchPage.acceptDateInDDMMYYYY("18","02","2018");
		flightSearchPage.clickTravelDate();

		flightSearchPage.clickSearchFlightButton();

		Assert.assertTrue(flightSearchPage.verifyErrorMessageForNotEnteringDestination("Ange destination"));
	}


	@Test
	public void testValidateBookTwoWayFlightAndReturnDateShouldBeGreater() throws InterruptedException, ParseException{

		//Enter source and Destination
		flightSearchPage.enterSource();
		flightSearchPage.enterDestination();

		//Enter travel start date
		flightSearchPage.chooseTravelDate();
		flightSearchPage.acceptDateInDDMMYYYY("18","01","2018");
		flightSearchPage.clickTravelDate();

		//Enter return date
		flightSearchPage.chooseTravelReturnDate();
		flightSearchPage.acceptDateInDDMMYYYY("18","02","2018");
		flightSearchPage.clickTravelDate();
		boolean flag=veryfyReturnDateGreaterThanSource("18","01","2018","18","02","2018");
		flightSearchPage.clickSearchFlightButton();
    if(flag==false)
    	Assert.assertTrue(flightSearchPage.verifyErrorMessageForNotEnteringDestination("Ogiltigt datum"));

	}

	public boolean veryfyReturnDateGreaterThanSource(String dd1,String mm1,String yyyy1,String dd2,String mm2,String yyyy2) throws ParseException{
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	     //   Date date1 = sdf.parse("2009-12-31");
	        Date date1 = sdf.parse(yyyy1+"-"+mm1+"-"+dd1);
	        Date date2 = sdf.parse(yyyy2+"-"+mm2+"-"+dd2);
	        if (date1.compareTo(date2) > 0) {
	            System.out.println("Date1 is after Date2");
	            return false;
	        }
	        else
	        	return true;
	     //   Date date2 = sdf.parse("2010-01-31");
	}


	@Test
	public void testBookOneWayFlightAnytime() throws InterruptedException, ParseException{
		// Opting for one way flight booking
		flightSearchPage.clickOneWay(); 

		//Enter source and Destination
		flightSearchPage.enterSource();
		flightSearchPage.enterDestination();

		//Enter Travel Start date
		flightSearchPage.chooseTravelDate();
		flightSearchPage.acceptDateInDDMMYYYY("18","01","2018");
		flightSearchPage.clickTravelDate();
		flightSearchPage.clickSearchFlightButton();

		//Clicking confirm flight which is selected by default
		flightSearchResultPage.clickSelectFlightButton();

		//Entering passenger details
		passengerInputPage.enterAllPassengersFirstAndLastNameAsOnPassport();
		boolean kidsPresent=passengerInputPage.clickRadioButton();
		if(kidsPresent)
			passengerInputPage.enterBirthdaysForKids();

		passengerInputPage.enterEmail("shivani.gupta@tothenew.com");
		passengerInputPage.enterMobileNumber("0708835399");
		passengerInputPage.clickTermsAndCondition();
		//Clicking confirm booking
		passengerInputPage.clickContinueBooking();
		Assert.assertTrue(finalFlightBookingPage.bookingConfirmationMessageDisplayed(),"Booking not confirmed yet");

	}
	@Test
	public void testBookTwoWayFlight() throws InterruptedException, ParseException{

		//Enter source and Destination
		flightSearchPage.enterSource();
		flightSearchPage.enterDestination();

		//Enter travel start date
		flightSearchPage.chooseTravelDate();
		flightSearchPage.acceptDateInDDMMYYYY("18","01","2018");
		flightSearchPage.clickTravelDate();

		//Enter return date
		flightSearchPage.chooseTravelReturnDate();
		flightSearchPage.acceptDateInDDMMYYYY("18","02","2018");
		flightSearchPage.clickTravelDate();

		flightSearchPage.clickSearchFlightButton();

		flightSearchResultPage.clickSelectFlightButton();

		//continue without booking hotel
		flightSearchResultPage.ifHotelPresent();

		//Enter passenger detail 
		// FirstName and LastName
		passengerInputPage.enterAllPassengersFirstAndLastNameAsOnPassport();
		//Click Gender radio button
		passengerInputPage.clickRadioButton();
		//Enter Birthday if kids are on journey
		passengerInputPage.enterBirthdaysForKids();
		//Enter One Passenger's email address
		passengerInputPage.enterEmail("shivani.gupta@tothenew.com");
		//Enter Mobile number
		passengerInputPage.enterMobileNumber("0708835399");
		//Click "Terms and Condition checkbox"
		passengerInputPage.clickTermsAndCondition();
		//Click "Continue Booking"
		passengerInputPage.clickContinueBooking();
		passengerInputPage.clickContinueBooking();
		Assert.assertTrue(finalFlightBookingPage.bookingConfirmationMessageDisplayed(),"Booking not confirmed yet");

	}

	@Test
	public void testBookOneWayFlightInGivenTimeRange() throws InterruptedException, ParseException{
		// Opting for one way flight booking
		flightSearchPage.clickOneWay(); 
		//Choose morning flights
		flightSearchPage.clickTimeDropDown("Dag (10-16)");
		//Enter source and Destination
		flightSearchPage.enterSource();

		flightSearchPage.enterDestination();

		//Enter Travel Start date
		flightSearchPage.chooseTravelDate();

		flightSearchPage.acceptDateInDDMMYYYY("18","01","2018");

		flightSearchPage.clickTravelDate();


		flightSearchPage.clickSearchFlightButton();

		//Clicking confirm flight which is selected by default


		Assert.assertTrue(flightSearchResultPage.validateResultedFlightTiming("Dag (10-16)"),"Flight is not appearing in time range");
		Thread.sleep(2000);
		flightSearchResultPage.clickSelectFlightButton();
		Thread.sleep(3000);
		//Entering passenger details
		Assert.assertTrue(passengerInputPage.enterAllPassengersFirstAndLastNameAsOnPassport(),"Passenger form is missing");

		boolean kidsPresent=passengerInputPage.clickRadioButton();
		if(kidsPresent)
			passengerInputPage.enterBirthdaysForKids();

		passengerInputPage.enterEmail("shivani.gupta@tothenew.com");
		passengerInputPage.enterMobileNumber("0708835399");
		passengerInputPage.clickTermsAndCondition();
		//Clicking confirm booking
		passengerInputPage.clickContinueBooking();
		Assert.assertTrue(finalFlightBookingPage.bookingConfirmationMessageDisplayed(),"Booking not confirmed yet");
	}
	@Test
	public void testBookOneWayFlightFor1Male1Female1KidAnd1Infant() throws InterruptedException, ParseException{
		// Opting for one way flight booking
		flightSearchPage.clickOneWay(); 

		//Enter source and Destination
		flightSearchPage.enterSource();
		flightSearchPage.enterDestination();

		//Enter Travel Start date
		flightSearchPage.chooseTravelDate();
		flightSearchPage.acceptDateInDDMMYYYY("18","01","2018");
		flightSearchPage.clickTravelDate();
		String no="2";
		flightSearchPage.chooseNumberOfAdults(no);
		flightSearchPage.chooseNumberOfKids(no);
		flightSearchPage.chooseKidsAges(2);
		Thread.sleep(2000);
		flightSearchPage.clickSearchFlightButton();

		//Clicking confirm flight which is selected by default
		flightSearchResultPage.clickSelectFlightButton();

		//Entering passenger details
		passengerInputPage.enterAllPassengersFirstAndLastNameAsOnPassport();
		passengerInputPage.clickRadioButton();
		passengerInputPage.enterBirthdaysForKids();
		Thread.sleep(2000);
		passengerInputPage.enterEmail("shivani.gupta@tothenew.com");
		passengerInputPage.enterMobileNumber("0708835399");

		passengerInputPage.clickTermsAndCondition();
		Thread.sleep(2000);
		//Clicking confirm booking
		passengerInputPage.clickContinueBooking();
		Assert.assertTrue(finalFlightBookingPage.bookingConfirmationMessageDisplayed(),"Booking not confirmed yet");

	}
}