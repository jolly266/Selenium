package stepsDefinations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class stepDefination {
	WebDriver driver;
	String Expected ="";
	String Actual ="";
	//Git Test
	@Before
	public void setup() throws Throwable, Throwable {
	Properties conpro = new Properties();
	conpro.load(new FileInputStream("./OrangeHRM.properties"));
	String brw = conpro.getProperty("Browser");
	if(brw.equalsIgnoreCase("chrome"))
	{
		driver= new ChromeDriver();
		driver.manage().window().maximize();
	}
	else if(brw.equalsIgnoreCase("firefox"))
	{
		driver= new FirefoxDriver();
		driver.manage().window().maximize();
	}
	else if(brw.equalsIgnoreCase("edge"))
	{
		driver= new EdgeDriver();
		driver.manage().window().maximize();
	}
	}
	
	@Given("^Launch url \"([^\"]*)\" in a browser$")
	public void launchUrl(String Url) throws Throwable {
	    driver.get(Url);
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@When("^Enter user as \"([^\"]*)\" in username$")
	public void enterUserName(String username) throws Throwable {
	   driver.findElement(By.name("txtUsername")).sendKeys(username);
	}

	@When("^Enter pass as\"([^\"]*)\" in password$")
	public void enterPasword(String password) throws Throwable {
		driver.findElement(By.name("txtPassword")).sendKeys(password);
	}

	@When("^Click login button$")
	public void clickLoginBtn() throws Throwable {
	    driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
	    Thread.sleep(2000);
	}

	@When("^click pim$")
	public void click_pim() throws Throwable {
		  driver.findElement(By.xpath("//b[normalize-space()='PIM']")).click();
	}

	@When("^Click Add button$")
	public void click_Add_button() throws Throwable {
		  driver.findElement(By.xpath("//input[@id='btnAdd']")).click();
		  Thread.sleep(2000);
	}

	@When("^Enter fname \"([^\"]*)\" in firstname textbox$")
	public void enterFirstName(String FirstName) throws Throwable {
		driver.findElement(By.name("firstName")).sendKeys(FirstName);
	}

	@When("^Enter mname \"([^\"]*)\" in mname textbox$")
	public void enterMiddleName(String MiddleName) throws Throwable {
		driver.findElement(By.name("middleName")).sendKeys(MiddleName);
	}

	@When("^Enter lname \"([^\"]*)\" in last name textbox$")
	public void enterLastName(String LastName) throws Throwable {
		driver.findElement(By.name("lastName")).sendKeys(LastName);
	    
	}

	@When("^Capture Eid BeforeAdding Employee$")
	public void capture_Eid_BeforeAdding_Employee() throws Throwable {
	    Expected = driver.findElement(By.name("employeeId")).getAttribute("value");
	}

	@When("^Click save button$")
	public void click_save_button() throws Throwable {
		  driver.findElement(By.xpath("//input[@id='btnSave']")).click();
	}

	@When("^capture Eid After Adding employee$")
	public void capture_Eid_After_Adding_employee() throws Throwable {
		  Actual = driver.findElement(By.name("personal[txtEmployeeId]")).getAttribute("value");
	}

	@Then("^Valid empoyee id before and after$")
	public void valid_empoyee_id_before_and_after() throws Throwable {
	    if(Expected.equals(Actual))
	    {
	    	System.out.println("Add Employee success::"+Expected+"   "+Actual);
	    }
	    else
	    {
	    	System.out.println("Add Employee Fail::"+Expected+"   "+Actual);
	    }
	}

	@Then("^Close browser$")
	public void close_browser() throws Throwable {
	    driver.quit();
	}
	
}
