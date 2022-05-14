package variousConcepts;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomeWork {
	WebDriver driver;

	By USERNAME_LOCATOR = By.xpath("//input[@id='username']");
	By PASSWORD_LOCATOR = By.xpath("//*[@id='password']");
	By SIGNIN_BUTTON_LOCATOR = By.xpath("/html/body/div/div/div/form/div[3]/button");
	By DASHBOARD_HEADER_LOCATOR = By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div/h2");
	By CUSTOMER_DROPDOWN_MENU = By.xpath("//span[text()='Customers']");
	By ADD_CUSTOMER_LINK = By.xpath("//*[@id='side-menu']/li[3]/ul/li[1]/a");
	By CONTACTS_HEADER_LOCATOR = By.xpath("//h2[contains(text(), 'Contacts')]");
	By FULLNAME = By.xpath("//input[@id='account']");
	By COMPANY_DROPDOWN = By.xpath("//select[@id='cid']");
	By EMAIL = By.xpath("//input[@id='email']");
	By PHONE_NUMBER = By.xpath("//input[@id='phone']");
	By ADDRESS = By.xpath("//input[@id='address']");
	By CITY = By.xpath("//input[@id='city']");
	By STATE_REGION = By.xpath("//input[@id='state']");
	By ZIP = By.xpath("//input[@id='zip']");
	By COUNTRY = By.xpath("//select[@id = 'country']");
	By SAVE_BUTTON = By.xpath("//button[@id='submit']");
	By LIST_CUSTOMER_LINK = By.xpath("//a[contains(text(), 'List Customers')]");
	By LIST_CUSTOMER_SEARCH_BOX = By.xpath("//input[@id='foo_filter']");
	By SEARCH_RESULT_NAME_BOX = By
			.xpath("//*[@id=\"page-wrapper\"]/div[3]/div[1]/div/div/div[2]/table/tbody/tr[1]/td[3]");

	@BeforeMethod
	public void init() {
		System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://techfios.com/billing/?ng=login");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void loginTest() {
		Random rn = new Random();
		int x = rn.nextInt(999);

		driver.findElement(USERNAME_LOCATOR).sendKeys("demo@techfios.com");
		driver.findElement(PASSWORD_LOCATOR).sendKeys("abc123");
		driver.findElement(SIGNIN_BUTTON_LOCATOR).click();

		String dashboardHeaderText = driver.findElement(DASHBOARD_HEADER_LOCATOR).getText();
		System.out.println(dashboardHeaderText);
		Assert.assertEquals(dashboardHeaderText, "Dashboard", "Wrong Page!!");

		driver.findElement(CUSTOMER_DROPDOWN_MENU).click();
		driver.findElement(ADD_CUSTOMER_LINK).click();

		String ContactsHeaderText = driver.findElement(CONTACTS_HEADER_LOCATOR).getText();
		System.out.println(ContactsHeaderText);
		Assert.assertEquals(ContactsHeaderText, "Contacts", "Wrong Page!!");

		String fullName = "SeleniumDec" + x;
		driver.findElement(FULLNAME).sendKeys("SeleniumDec" + x);

		selectFromDropDown(COMPANY_DROPDOWN, "Techfios");

		driver.findElement(EMAIL).sendKeys(x + "demo@techfios.com");
		driver.findElement(PHONE_NUMBER).sendKeys(x + "5555555");
		driver.findElement(ADDRESS).sendKeys(x + " Beltline Rd");
		driver.findElement(CITY).sendKeys("Irving");
		driver.findElement(STATE_REGION).sendKeys("Texas");
		driver.findElement(ZIP).sendKeys("76" + x);
		
		selectFromDropDown(COUNTRY, "United Kingdom");		
		
		driver.findElement(SAVE_BUTTON).click();

		waitForElement(driver, 5, LIST_CUSTOMER_LINK);

		driver.findElement(LIST_CUSTOMER_LINK).click();
		driver.findElement(LIST_CUSTOMER_SEARCH_BOX).sendKeys(fullName);

		String verifyContact = driver.findElement(SEARCH_RESULT_NAME_BOX).getText();
		System.out.println(verifyContact);
		Assert.assertEquals(verifyContact, fullName, "Wrong Page!!");

	}

	private int numberGenerator(int i) {
		Random rn = new Random();
		int x = rn.nextInt(i);
		return x;
	}

	private void selectFromDropDown(By locator, String visibleText) {
		Select sel = new Select(driver.findElement(locator));
		sel.selectByVisibleText(visibleText);

	}

	private void waitForElement(WebDriver driver, int timeInSeconds, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

//	@AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
