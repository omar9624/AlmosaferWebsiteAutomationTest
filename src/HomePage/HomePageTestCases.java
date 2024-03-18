package HomePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HomePageTestCases extends Parameters {

	@BeforeTest
	public void mySetup() {
		driver.get(url);
		driver.manage().window().maximize();

		WebElement popupMsg = driver.findElement(By.cssSelector(".sc-iBmynh.izXFRL"));

		if (popupMsg.isDisplayed()) {
			WebElement buttonSAR = driver.findElement(By.cssSelector("button[class='sc-jTzLTM hQpNle cta__button cta__saudi btn btn-primary']"));
			buttonSAR.click();
		}
	}

	@Test
	public void defaultEnglishLanguageTest() {
		String actualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
		myAssert.assertEquals(actualLanguage, expectedLanguage);
	}
	
	@Test
	public void defaultCurrencySARTest() {
		String actualCurrency = driver.findElement(By.xpath("//button[@data-testid=\'Header__CurrencySelector\']")).getText();
		myAssert.assertEquals(actualCurrency, expectedCurrency);
	}
	
	@Test
	public void checkContactNumber() {
		String actualContact = driver.findElement(By.xpath("//strong[normalize-space()='+966554400000']")).getText();
		myAssert.assertEquals(actualContact, expectedContact);
	}
	
	@Test
	public void checkQitafLogo() throws InterruptedException {
		boolean actualLogo = driver.findElement(By.xpath("//div[@class='sc-fihHvN eYrDjb']//*[name()='svg']")).isDisplayed();
		myAssert.assertEquals(actualLogo, expectedLogo);
	}
	
	@Test
	public void checkHotelTabIsNotSelected() throws InterruptedException {
		String actualHotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels")).getAttribute("aria-selected");
		myAssert.assertEquals(actualHotelTab, expectedHotelTab);
	}
	
	@Test
	public void checkFlightsDate() throws InterruptedException {
		int actualFlightDepartureDate = Integer.parseInt(driver.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-kqlzXE blwiEW'] span[class='sc-cPuPxo LiroG']")).getText());
		int actualFlightReturnDate = Integer.parseInt(driver.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-OxbzP edzUwL'] span[class='sc-cPuPxo LiroG']")).getText());
		
		System.out.println("Departure : " + String.valueOf(actualFlightDepartureDate) + " " + "Return : " + String.valueOf(actualFlightReturnDate));
		System.out.println("Exp Departure : " + String.valueOf(expectedFlightDepartureDate) + " " + "Exp Return : " + String.valueOf(expectedFlightReturnDate));
		
		myAssert.assertEquals(actualFlightDepartureDate, expectedFlightDepartureDate);
		myAssert.assertEquals(actualFlightReturnDate, expectedFlightReturnDate);
	}
	
	
	
	
	
}
