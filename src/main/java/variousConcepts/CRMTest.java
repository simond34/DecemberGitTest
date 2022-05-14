package variousConcepts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class CRMTest {
	
	WebDriver driver;

	By USERNAME_LOCATOR = By.xpath("//input[@id='username']");
	By PASSWORD_LOCATOR = By.xpath("//*[@id='password']");
	By SIGNIN_BUTTON_LOCATOR = By.xpath("/html/body/div/div/div/form/div[3]/button");
	By DASHBOARD_HEADER_LOCATOR = By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div/h2");
	By ORDER_MENU_LOCATOR = By.xpath("//span[contains(text(), 'Orders')]");
	By ADD_ORDER_MENU_LOCATOR = By.xpath("//*[@id=\"side-menu\"]/li[7]/ul/li[2]/a");
	By PRODUCT_SERVICE_LOCATOR = By.xpath("//*[@id=\"select2-pid-container\"]");
	By PRODUCT_SELECTION_LOCATOR = By.xpath("//li[contains(text(), 'Laptop')]");
	By CUSTOMER_LOCATOR = By.xpath("//span[@id='select2-cid-container']");
	By CUSTOMER_SELECTION_LOCATOR = By.xpath("//li[contains(text(), 'Adam1 - Adam1@TechFios.com')]");
	By STATUS_LOCATOR = By.xpath("//select[@id='status']");
	By STATUS_SELECTION_LOCATOR = By.xpath("//select[@id='status']/child::option[2]");
	By PRICE_LOCATOR = By.xpath("//input[@id='price']");
	By BILLING_CYCLE_LOCATOR = By.xpath("//select[@id='billing_cycle']");
	By BILLING_CYCLE_SELECTION_LOCATOR = By.xpath("//select[@id='billing_cycle']/child::option[2]");
	By ORDERS_HEADER_LOCATOR = By.xpath("//h2[contains(text(), 'Orders')]");
	By ORDER_SUBMIT_LOCATOR = By.xpath("//button[@id='submit']");
	By ORDER1_HEADER_LOCATOR = By.xpath("//h3[contains(text(),'Order # 1')]");
	By LIST_ALL_ORDERS_LOCATOR = By.xpath("//a[contains(text(),'List All Orders')]");
	
	
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
		
		driver.findElement(USERNAME_LOCATOR).sendKeys("demo@techfios.com");
		driver.findElement(PASSWORD_LOCATOR).sendKeys("abc123");
		driver.findElement(SIGNIN_BUTTON_LOCATOR).click();
		
		String dashboardHeaderText = driver.findElement(DASHBOARD_HEADER_LOCATOR).getText();
		System.out.println(dashboardHeaderText);
		Assert.assertEquals(dashboardHeaderText, "Dashboard", "Wrong Page!!");
		
		driver.findElement(ORDER_MENU_LOCATOR).click();
		driver.findElement(ADD_ORDER_MENU_LOCATOR).click();
		
		String orderHeaderText = driver.findElement(ORDERS_HEADER_LOCATOR).getText();
		System.out.println(orderHeaderText);
		Assert.assertEquals(orderHeaderText, "Orders", "Wrong Page!!");
		
		driver.findElement(PRODUCT_SERVICE_LOCATOR).click();
		driver.findElement(PRODUCT_SELECTION_LOCATOR).click();
		driver.findElement(CUSTOMER_LOCATOR).click();
		driver.findElement(CUSTOMER_SELECTION_LOCATOR).click();
		driver.findElement(STATUS_LOCATOR).click();
		driver.findElement(STATUS_SELECTION_LOCATOR).click();
		driver.findElement(PRICE_LOCATOR).sendKeys("25.00");
		driver.findElement(BILLING_CYCLE_LOCATOR).click();
		driver.findElement(BILLING_CYCLE_SELECTION_LOCATOR).click();
		driver.findElement(ORDER_SUBMIT_LOCATOR).click();
		
		String order1HeaderText = driver.findElement(ORDER1_HEADER_LOCATOR).getText();
		System.out.println(order1HeaderText);
		Assert.assertEquals(order1HeaderText, "Orders # 1", "Wrong Page!!");
		
		driver.findElement(LIST_ALL_ORDERS_LOCATOR).click();
		
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
