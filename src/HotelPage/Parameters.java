package HotelPage;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.Assertion;

public class Parameters {
	WebDriver driver = new ChromeDriver();
	
	String url = "https://www.almosafer.com/en";
	
	Random random = new Random();
	
	Assertion myAssert = new Assertion();
	
	String[] cityInENLanguage = { "Dubai", "Jeddah", "Riyadh" };
	String[] cityInARLanguage = { "دبي", "جدة" };
	String location = "";
}
