package AlmosaferWeb;

import static org.testng.Assert.assertEquals;


import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class MyTestCases extends Parameter {

	@BeforeTest
	public void mySetup() {

		driver.get(Url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		WebElement popUPScreen = driver.findElement(By.className("sc-iBmynh"));

		if (popUPScreen.isDisplayed()) {

			WebElement SARBUTTON = driver.findElement(By.className("cta__saudi"));
			SARBUTTON.click();
		}

	}

	@Test(priority = 1, enabled = false)
	public void CheckTheDeafultLanguageIsEnglish() {

		String ExpectedLanguage = "EN";
		String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang").toUpperCase();
		assertEquals(ActualLanguage, ExpectedLanguage);
	}

	@Test(priority = 2, enabled = false)
	public void CheckTheDeafultCurrencyIsSAR() {

		String ExpectedCurrency = "SAR";
		String ActualCurrency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"))
				.getText();
		assertEquals(ActualCurrency, ExpectedCurrency);
	}

	@Test(priority = 3, enabled = false)
	public void CheckContactNumber() {

		String ExpectedContactNumber = "+966554400000";
		String ActualContactNumber = driver.findElement(By.tagName("strong")).getText();
		assertEquals(ActualContactNumber, ExpectedContactNumber);

	}

	@Test(priority = 4, enabled = false)
	public void CheckQitafLogo() {

		WebElement theFooter = driver.findElement(By.tagName("footer"));
		assertEquals(theFooter.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ekulBa.eYboXF")).isDisplayed(), true);
	}

	@Test(priority = 5, enabled = false)
	public void CheckHotelTapIsNotSelectedByDeafult() {

		assertEquals(driver.findElement(By.id("uncontrolled-tab-example-tab-hotels")).getAttribute("aria-selected"),
				"false");
		System.out.println();
	}

	@Test(priority = 6, enabled = false)
	public void CheckDepartureDateAndReturnDate() {

		LocalDate Today = LocalDate.now();

		int expectedDepartureDate = Today.plusDays(1).getDayOfMonth();
		int expectedReturnDate = Today.plusDays(2).getDayOfMonth();

		int ActualDeparturedate = Integer.parseInt(driver
				.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-kqlzXE blwiEW'] span[class='sc-cPuPxo LiroG']"))
				.getText());

		int ActualReturn = Integer.parseInt(driver
				.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-OxbzP edzUwL'] span[class='sc-cPuPxo LiroG']"))
				.getText());

		assertEquals(ActualReturn, expectedReturnDate);
		assertEquals(ActualDeparturedate, expectedDepartureDate);

	}

	@Test(priority = 7)
	public void RandomMethodToChangeTheLanguage() {

		Random rand = new Random();
		int RandomIndexForTheWebSite = rand.nextInt(Websites.length);

		driver.get(Websites[RandomIndexForTheWebSite]);

		if (driver.getCurrentUrl().contains("ar")) {

			String Expectedlang = "ar";
			String Actuallang = driver.findElement(By.tagName("html")).getAttribute("lang");

			assertEquals(Actuallang, Expectedlang);
		}

		else {

			String Expectedlang = "en";
			String Actuallang = driver.findElement(By.tagName("html")).getAttribute("lang");

			assertEquals(Actuallang, Expectedlang);
		}

	}

	@Test(priority = 8)
	public void SwitchToHotelTap() {
		WebElement HotelTap = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		HotelTap.click();

		if (driver.getCurrentUrl().contains("ar")) {

			WebElement SearchCityInput = driver
					.findElement(By.cssSelector("input[placeholder='البحث عن فنادق أو وجهات']"));
			SearchCityInput.sendKeys(CitiesInArabic[RandomArabicCities]);

		} else {

			WebElement SearchCityInput = driver
					.findElement(By.cssSelector("input[placeholder='Search for hotels or places']"));
			SearchCityInput.sendKeys(CitiesInEnglish[RandomEnglishCities]);
		}
		WebElement theList = driver.findElement(By.className("UzzIN"));
		System.out.println(theList.findElements(By.tagName("li")).size());
		theList.findElements(By.tagName("li")).get(1).click();
		
		// hard assert/comment
		
		// if this test case failed i will not go to the following test cases/comment
//Assert.assertEquals(false,true);
	}

	@Test(priority = 9)
	public void randomSelectTheVisitorNumber() {

		WebElement visitor = driver.findElement(By.tagName("select"));
		Select selector = new Select(visitor);

		if (driver.getCurrentUrl().contains("ar"))

		{
			selector.selectByVisibleText("1 غرفة، 1 بالغ، 0 أطفال");

		} else {
			selector.selectByVisibleText("1 Room, 1 Adult, 0 Children");
		}

		driver.findElement(By.className("sc-1vkdpp9-6")).click();
	}

	@Test(priority = 10)
	public void makeSurePageIsFullyLoaded() throws InterruptedException {
		Thread.sleep(30000);

		String searchResult = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/section/span")).getText();

		if (driver.getCurrentUrl().contains("ar")) {

			boolean Actualresult = searchResult.contains("وجدنا");
			assertEquals(Actualresult, true);
		}

		else {

			boolean Actualresult = searchResult.contains("found");
			assertEquals(Actualresult, true);

		}

	}
	@Test(priority = 11)
	public void sortTheItemsBasedOnThEPrice() {
		
	WebElement LowestPriceButton =	driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[2]/section[1]/div/button[2]"));
	LowestPriceButton.click();
	
	WebElement HotelsContainer = driver.findElement(By.cssSelector(".sc-htpNat.KtFsv.col-9"));
	List<WebElement> thePricesList = HotelsContainer.findElements(By.className("Price__Value"));
	
	System.out.println(thePricesList.size()+" this is the total prices found");
	
	String LowestPriceOnTheList = thePricesList.get(0).getText();
	int LowestPriceOnTheListAsNumber = Integer.parseInt(LowestPriceOnTheList);
	
	String highestPriceOnTheList = thePricesList.get(thePricesList.size()-1).getText();
	int highestPriceOnTheListAsNumber = Integer.parseInt(highestPriceOnTheList);
	
	System.out.println("this is the minimum vale "+LowestPriceOnTheList);
	System.out.println("this is the maximum vale "+highestPriceOnTheList);
	
	assertEquals(highestPriceOnTheListAsNumber>LowestPriceOnTheListAsNumber, true);
	}
	
	@AfterTest
	public void myPostTest() {
	}

}
