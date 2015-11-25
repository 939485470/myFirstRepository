package Selenium.Test2.SeleniumTest2;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class F2{
  private WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeMethod
  public void setUp() throws Exception {
	System.setProperty(
				"webdriver.chrome.driver",
				"C:/Program Files (x86)/Google/Chrome/Application/chromedriver.exe");
	driver = new ChromeDriver();
    baseUrl = "http://piao.ctrip.com/";
    driver.manage().window().maximize();
    /*隐式延迟：设置一个最大超时时间，如果element在最大时间内出现，则停止等待，否则抛出异常，在行为上与显示等待是一样的。
     * 不同的地方在于显式等待需要自己去管理超时对象WebDriverWait，而隐式等待则将等待任务交给web driver去做。在设置一次之后，
     * 相同的driver的所有的find element操作都会遵循这个延迟设置。
    */
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testUntitled2() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.id("mainInput")).click();
    driver.findElement(By.id("mainInput")).clear();
    driver.findElement(By.id("mainInput")).sendKeys("上海");
    //让页面等2s
    Thread.sleep(2000);
    driver.findElement(By.linkText("搜 索")).click();
    Thread.sleep(2000);
    String expectedTitle = "上海景点门票,上海景点门票价格,上海景点门票预订【携程门票】";
    String actualTitle = driver.getTitle();
    System.out.println(actualTitle);
    AssertJUnit.assertEquals(actualTitle,expectedTitle); 
  }

  @AfterMethod
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      Assert.fail(verificationErrorString);
    }
  }
}
