package HomePage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.Assertion;

public class Parameters {
	WebDriver driver = new ChromeDriver();
	
	Assertion myAssert = new Assertion();
	
	String url = "https://www.almosafer.com/en";
	String expectedLanguage = "en";
	String expectedCurrency = "SAR";
	String expectedContact = "+966554400000";
	boolean expectedLogo = true;
	String expectedHotelTab = "false";
	
	DateFormat dateFormat = new SimpleDateFormat("dd"); 
	Date date = new Date();
	String dayOfDate = dateFormat.format(date);
	int expectedFlightDepartureDate = Integer.parseInt(dayOfDate) + 1;
	int expectedFlightReturnDate = Integer.parseInt(dayOfDate) + 2;
	
}
