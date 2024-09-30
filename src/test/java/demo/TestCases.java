package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;
import java.util.logging.Level;

import demo.utils.ExcelDataProvider;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases extends ExcelDataProvider{ // Lets us read the data
        ChromeDriver driver;

        /*
         * TODO: Write your tests here with testng @Test annotation.
         * Follow `testCase01` `testCase02`... format or what is provided in
         * instructions
         */

        /*
         * Do not change the provided methods unless necessary, they will help in
         * automation and assessment
         */
        @BeforeTest
        public void startBrowser() {
                System.setProperty("java.util.logging.config.file", "logging.properties");

                // NOT NEEDED FOR SELENIUM MANAGER
                // WebDriverManager.chromedriver().timeout(30).setup();

                ChromeOptions options = new ChromeOptions();
                LoggingPreferences logs = new LoggingPreferences();

                logs.enable(LogType.BROWSER, Level.ALL);
                logs.enable(LogType.DRIVER, Level.ALL);
                options.setCapability("goog:loggingPrefs", logs);
                options.addArguments("--remote-allow-origins=*");

                System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

                driver = new ChromeDriver(options);

                driver.manage().window().maximize();
        }

@Test
public void testCase01() throws Exception{
        driver.get("https://www.youtube.com/");
        WebDriverWait mywait=new WebDriverWait(driver,Duration.ofSeconds(6));
       mywait.until(ExpectedConditions.urlMatches("https://www.youtube.com/"));
 
        Thread.sleep(4000);
        String curr_url=driver.getCurrentUrl();
        Assert.assertEquals(curr_url,"https://www.youtube.com/");
        driver.findElement(By.xpath("//div[@id='start']//button[@id='button']//div")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[normalize-space()='About']")).click();
        Thread.sleep(2000);
        String txt=driver.getCurrentUrl();
        if(txt.contains("about")){
        String text1=driver.findElement(By.xpath("//h1[@class='lb-font-display-1 lb-font-weight-700 lb-font-color-text-primary lb-font--no-crop']")).getText();
        String text2=driver.findElement(By.xpath("(//p[@class='lb-font-display-3 lb-font-color-text-primary'])[1]")).getText();
System.out.println(text1+text2);}
}

@Test
public void testCase02() throws Exception{
driver.navigate().back();
WebDriverWait mywait=new WebDriverWait(driver,Duration.ofSeconds(6));
mywait.until(ExpectedConditions.urlMatches("https://www.youtube.com/"));

driver.findElement(By.xpath("//div[@id='start']//button[@id='button']//div")).click();
Thread.sleep(1000);
driver.findElement(By.xpath("//yt-formatted-string[normalize-space()='Movies']")).click();
Thread.sleep(1000);
driver.findElement(By.xpath("(//div[@ class='yt-spec-touch-feedback-shape__fill'])[5]")).click();
Thread.sleep(1000);
List<WebElement> wb1=driver.findElements(By.xpath("//p[contains(text(),'A')]"));
boolean result_cat=false;
for(WebElement wb:wb1){
        String txt=wb.getText();
        if(txt.contains("A")){
                result_cat=true;     
        }else{
                result_cat=false;       
        }
        
}

Assert.assertEquals(result_cat,true);
Thread.sleep(1000);
boolean movie_cat=false;

List<WebElement> WB4=driver.findElements(By.xpath("//span[@class='grid-movie-renderer-metadata style-scope ytd-grid-movie-renderer']"));
for(WebElement wb5:WB4){
        String txt=driver.findElement(By.xpath("//span[@class='grid-movie-renderer-metadata style-scope ytd-grid-movie-renderer']")).getText();
        if((txt.contains("Comedy")) || (txt.contains("Animation"))||(txt.contains("Drama"))){
                movie_cat=true;}   
        else{movie_cat=false;}
}

Assert.assertEquals(movie_cat,true);

}

@Test
public void testCase03() throws Exception{
driver.findElement(By.xpath("//div[@id='start']//button[@id='button']//div")).click();
Thread.sleep(1000);
driver.findElement(By.xpath("//yt-formatted-string [normalize-space()='Music']")).click();
Thread.sleep(3000);
List<WebElement>wb7=driver.findElements(By.xpath("//p [@id='video-count-text' ]"));
int count=wb7.size();
Assert.assertTrue(count <= 50);}



@Test
public void testCase04() throws Exception{
       driver.findElement(By.xpath("//ytd-topbar-logo-renderer[@id='logo']//div[@class='style-scope ytd-topbar-logo-renderer']//div")).click();
       Thread.sleep(4000);
   driver.findElement(By.xpath("//div[@id='start']//button[@id='button']//div")).click();
Thread.sleep(1000);
driver.findElement(By.xpath("//yt-formatted-string[normalize-space()='News']")).click();
Thread.sleep(3000);
List<WebElement> wb10=driver.findElements(By.xpath("//span[@class='style-scope yt-formatted-string']")); 
Thread.sleep(2000);
String temp_change="";
int sum=0;
for(WebElement wb11:wb10){
    System.out.println(wb11.getText());  }  
    List<WebElement> wb15=driver.findElements(By.xpath("//span[@id='vote-count-middle']")); 
      for(WebElement wb16:wb15){
        temp_change= wb16.getText();
        temp_change.trim();
        if(temp_change.equalsIgnoreCase("")){
                continue;
        }else{
        int temp=Integer.parseInt(temp_change);
       
        sum=sum+temp;}
       
      }
      System.out.printf("Count value is %s",sum);}

     @Test( dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
      public void testCase05(String to_be_searched) throws Exception{
        int count=0;
        driver.findElement(By.xpath("//ytd-topbar-logo-renderer[@id='logo']//div[@class='style-scope ytd-topbar-logo-renderer']//div")).click();
       Thread.sleep(4000);
       WebElement wb1= driver.findElement(By.xpath("//input[@id='search']"));
       wb1.clear();
       wb1.sendKeys(to_be_searched);
       Thread.sleep(1000);
     driver.switchTo().activeElement().sendKeys(Keys.ENTER);
     Thread.sleep(4000);
     List<WebElement> wb21=driver.findElements(By.xpath("//span[@class='yt-core-attributed-string yt-core-attributed-string--white-space-pre-wrap' and contains(normalize-space(),'views') ]"));
     Thread.sleep(2000);
     for(WebElement wb22:wb21){
      String temp=wb22.getText();
      if(temp.contains("K")){
        String val=temp.replaceAll("[^0-9.]", "").trim();
        float num=(int)(Float.parseFloat(val));
        num=num*1000;
        count=(int)(count+num);
        if(count>=100000000){
                System.out.println("Count value="+count);
                break;
        }
      }else if(temp.contains("M")){
        String val=temp.replaceAll("[^0-9.]", "").trim();
        float num=(int)(Float.parseFloat(val));
        num=num*1000;
        count=(int)(count+num);
        if(count>=100000000){
                System.out.println("Count value="+count);
                break;
        }
      }

      
     }

       Thread.sleep(5000);
       System.out.println("Execution done");
      }




        @AfterTest
        public void endTest() {
                 driver.close();
                driver.quit();

        }
}