package HotelPage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HotelPageTestCase extends Parameters {

	@BeforeTest
	public void setup() {
		driver.get(url);
		driver.manage().window().maximize();
		
		WebElement popupMsg = driver.findElement(By.cssSelector(".sc-iBmynh.izXFRL"));

		if (popupMsg.isDisplayed()) {
			WebElement buttonSAR = driver.findElement(
					By.cssSelector("button[class='sc-jTzLTM hQpNle cta__button cta__saudi btn btn-primary']"));
			buttonSAR.click();
		}
	}

	@Test(priority = 1)
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

	@Test(priority = 2)
	public void checkLowPriceFilter() throws InterruptedException {
		Thread.sleep(5000);
		WebElement lowpriceTab = driver
				.findElement(By.xpath("//button[@data-testid='HotelSearchResult__sort__LOWEST_PRICE']"));
		lowpriceTab.click();
		
		
	}
}
