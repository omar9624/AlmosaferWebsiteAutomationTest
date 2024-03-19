package HomePage;



import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.Assertion;

public class Parameters {
	WebDriver driver = new ChromeDriver();
	
	Assertion myAssert = new Assertion();
	
	Random random = new Random();
	
	String url = "https://www.almosafer.com/en";
	String expectedLanguage = "en";
	String expectedCurrency = "SAR";
	String expectedContact = "+966554400000";
	boolean expectedLogo = true;
	String expectedHotelTab = "false";
	
	

	
}
