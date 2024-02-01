package AlmosaferWeb;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Parameter {

	WebDriver driver = new ChromeDriver();
	String Url = "https://global.almosafer.com/en";
	Random randForCity = new Random();

	String[] Websites = { "https://global.almosafer.com/en", "https://global.almosafer.com/ar" };

	String[] CitiesInEnglish = {"Dubai", "Jeddah", "Riyadh"};
	int RandomEnglishCities = randForCity.nextInt(CitiesInEnglish.length);

	String[] CitiesInArabic = {"دبي", "جدة"};
	int RandomArabicCities = randForCity.nextInt(CitiesInArabic.length);
}
