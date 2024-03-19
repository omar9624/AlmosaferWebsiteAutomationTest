package HomePage;

import java.time.LocalDate;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HomePageTestCases extends Parameters {

	@BeforeTest
	public void mySetup() {
		driver.get(url);
		driver.manage().window().maximize();

		WebElement popupMsg = driver.findElement(By.cssSelector(".sc-iBmynh.izXFRL"));

		if (popupMsg.isDisplayed()) {
			WebElement buttonSAR = driver.findElement(
					By.cssSelector("button[class='sc-jTzLTM hQpNle cta__button cta__saudi btn btn-primary']"));
			buttonSAR.click();
		}
	}

	@Test(priority = 1 , description = "Check Default Language - EN -")
	public void defaultEnglishLanguageTest() {
		String actualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
		myAssert.assertEquals(actualLanguage, expectedLanguage);
	}

	@Test(priority = 1 , description = "Check Default Currency - SAR -")
	public void defaultCurrencySARTest() {
		String actualCurrency = driver.findElement(By.xpath("//button[@data-testid=\'Header__CurrencySelector\']"))
				.getText();
		myAssert.assertEquals(actualCurrency, expectedCurrency);
	}

	@Test(priority = 1 , )
	public void checkContactNumber() {
		String actualContact = driver.findElement(By.xpath("//strong[normalize-space()='+966554400000']")).getText();
		myAssert.assertEquals(actualContact, expectedContact);
	}

	@Test(priority = 1 , description = "Check if Qitaf Logo Is Displayed or Not")
	public void checkQitafLogo() throws InterruptedException {
		boolean actualLogo = driver.findElement(By.xpath("//div[@class='sc-fihHvN eYrDjb']//*[name()='svg']"))
				.isDisplayed();
		myAssert.assertEquals(actualLogo, expectedLogo);
	}

	@Test(priority = 1)
	public void checkHotelTabIsNotSelected() throws InterruptedException {
		String actualHotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"))
				.getAttribute("aria-selected");
		myAssert.assertEquals(actualHotelTab, expectedHotelTab);
	}

	@Test(priority = 1 , description = "Check if Departure Date is Tomorrow And Return Date is After Tomorrow")
	public void checkFlightsDate() throws InterruptedException {

		LocalDate today = LocalDate.now();
		int tomorrow = today.plusDays(1).getDayOfMonth();
		int afterTomorrow = today.plusDays(2).getDayOfMonth();

		int actualFlightDepartureDate = Integer.parseInt(driver
				.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-kqlzXE blwiEW'] span[class='sc-cPuPxo LiroG']"))
				.getText());
		int actualFlightReturnDate = Integer.parseInt(driver
				.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-OxbzP edzUwL'] span[class='sc-cPuPxo LiroG']"))
				.getText());

		System.out.println("Act Departure : " + String.valueOf(actualFlightDepartureDate) + " " + "Act Return : "
				+ String.valueOf(actualFlightReturnDate));
		System.out.println(
				"Exp Departure : " + String.valueOf(tomorrow) + " " + "Exp Return : " + String.valueOf(afterTomorrow));

		myAssert.assertEquals(actualFlightDepartureDate, tomorrow);
		myAssert.assertEquals(actualFlightReturnDate, afterTomorrow);
	}

	@Test(priority = 2)
	public void randomlyChooseLanguages() throws InterruptedException {
		WebElement languageButton = driver.findElement(By.xpath("//a[@data-testid='Header__LanguageSwitch']"));
		int randomLanguage = random.nextInt(2);
		if (randomLanguage == 1) {
			languageButton.click();
			Thread.sleep(2000);
			String actualLangauge = driver.findElement(By.tagName("html")).getAttribute("lang");
			myAssert.assertEquals(actualLangauge, "ar");
		} else {
			String actualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
			myAssert.assertEquals(actualLanguage, "en");
		}
	}

	@Test(priority = 3 , description = "Test Search Procces And Show Search Result With Assertion")
	public void searchHotelProcces() throws InterruptedException {
		WebElement hotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		hotelTab.click();
		
		String currentLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");

		WebElement locationInput = driver.findElement(By.xpath("//input[@data-testid='AutoCompleteInput']"));

		if (currentLanguage.equals("en")) {
	
			int randomLanguage = random.nextInt(3);

			locationInput.sendKeys(cityInENLanguage[randomLanguage]);
			location = cityInENLanguage[randomLanguage];

		} else {

			int randomLanguage = random.nextInt(2);

			locationInput.sendKeys(cityInARLanguage[randomLanguage]);
			location = cityInARLanguage[randomLanguage];

		}

		Thread.sleep(2000);
		WebElement autocompleteListContainer = driver
				.findElement(By.xpath("//ul[@data-testid='AutoCompleteResultsList']"));
		List<WebElement> autocompleteList = autocompleteListContainer.findElements(By.tagName("li"));
		autocompleteList.get(1).click();

		WebElement selectRoomContainer = driver
				.findElement(By.xpath("//select[@data-testid='HotelSearchBox__ReservationSelect_Select']"));
		Select roomAndAdultSelect = new Select(selectRoomContainer);
		int randomRoom = random.nextInt(2);
		roomAndAdultSelect.selectByIndex(randomRoom);

		WebElement searchButton = driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
		Thread.sleep(2000);
		searchButton.click();

		String actualSearchresult = driver.findElement(By.xpath("//input[@data-testid='AutoCompleteInput']"))
				.getAttribute("value");

		
		myAssert.assertEquals(actualSearchresult, location);
		
	}

}
