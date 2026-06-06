
	package genericbaseutility;


	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.edge.EdgeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.BeforeSuite;
	import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.databaseutility.DataBaseUtility;
	import com.comcast.crm.genericfileutility.ExcelUtility;
	import com.comcast.crm.genericfileutility.FileUtility;
	import com.comcast.crm.genericwebdriverutility.JavaUtility;
	import com.comcast.crm.objectrepositoryutility.Home_page;
	import com.comcast.crm.objectrepositoryutility.Login_page;

	public class Base2 {
		public JavaUtility jlib=new JavaUtility();
		public ExcelUtility elib=new ExcelUtility();
		public DataBaseUtility dblib=new DataBaseUtility();
	  public FileUtility flib=new FileUtility();
	  public WebDriver driver=null;
public static WebDriver sdriver=null;
public ExtentSparkReporter spark;
public ExtentReports report;
		@BeforeSuite
		public void configBs() {
			System.out.println("===connect to db==report config==");
			dblib.getDbconnection();
			//spark report config
			ExtentSparkReporter spark=new ExtentSparkReporter("./advancereport/report.html");
			spark.config().setDocumentTitle("crm test suite results");
			spark.config().setReportName("crm report");
			spark.config().setTheme(Theme.DARK);
			//add env information &create test
			 report=new ExtentReports();
			report.attachReporter(spark);
			report.setSystemInfo("BROWSER","CHROME-100");
			report.setSystemInfo("OS","WINDOWS-10");
			
		}
		@BeforeClass
		public void configbc() throws Throwable {
			System.out.println("====launch browser=====");
			String BROWSER=flib.getDataFromPropertiesFile("browser");
			if (BROWSER.equals("chrome")) {
				driver = new ChromeDriver();
			} else if (BROWSER.equals("firefox")) {
				driver = new FirefoxDriver();

			} else if (BROWSER.equals("edge")) {
				driver = new EdgeDriver();
			} else {
				driver = new ChromeDriver();
			}
			sdriver=driver;
		}
		@BeforeMethod
		public void configBM() throws Throwable {
			System.out.println("====LOGIN====");
			Login_page lp=new Login_page(driver);
		String	URL=flib.getDataFromPropertiesFile("url");
		String	USERNAME=flib.getDataFromPropertiesFile("username");
		String PASSWORD=flib.getDataFromPropertiesFile("password");
		Login_page lp1=new Login_page(driver);
		lp1.loginToapp(URL, USERNAME, PASSWORD);
			
		}
		@AfterMethod
		public void configam() {
			System.out.println("=====logout====");
			Home_page hp=new Home_page(driver);
			hp.navigatetologout();
		}
		
		@AfterClass
		public void configac() {
			System.out.println("=======close the browser===");
			driver.quit();
		}
	}



