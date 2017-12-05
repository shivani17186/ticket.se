package ticketSEGroup.ticketSEArtifact;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverBase {
	public WebDriver driver;
	String browserversion="46";
	public static File directory = new File(".");

	public DriverBase() throws IOException{
		System.out.println("I am able to reach here");
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("dom.disable_beforeunload",true);
		DesiredCapabilities dc=DesiredCapabilities.firefox();
		dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
		System.out.println("PATH="+"F:\\DoTrips_Backend_Automation"+ File.separator
				+ "lib" + File.separator+"//MozillaVersions//MozillaFirefox"+browserversion+"//firefox.exe");
		driver = new FirefoxDriver(new FirefoxBinary(new File("F:\\DoTrips_Backend_Automation"+ File.separator
				+ "lib" + File.separator+"//MozillaVersions//MozillaFirefox"+browserversion+"//firefox.exe")), profile,dc);
		openURL("https://www.ticket.se/flyg/");
	}

	public void pressTab(){
		Actions act=new Actions(driver);
		act.sendKeys(Keys.TAB);//act.keyDown(Keys.ESCAPE).keyUp(Keys.ESCAPE).build().perform();
	}
	public void pressEscape(){
		Actions act=new Actions(driver);
		act.sendKeys(Keys.ESCAPE);//act.keyDown(Keys.ESCAPE).keyUp(Keys.ESCAPE).build().perform();
	}

	public void openURL(String url){
		driver.get(url);
	}
	public void closeBrowser(){
		driver.quit();
	}
}
