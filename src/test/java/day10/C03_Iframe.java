package day10;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class C03_Iframe {

    //   ● https://the-internet.herokuapp.com/iframe adresine gidin.
    //   ● Bir metod olusturun: iframeTest
    //        ○ “An IFrame containing….” textinin erisilebilir oldugunu test edin ve  konsolda    yazdirin.
    //        ○ Text Box’a “Merhaba Dunya!” yazin.
    //        ○ TextBox’in altinda bulunan “Elemental Selenium” linkini textinin gorunur oldugunu
    //        dogrulayin ve  konsolda yazdirin.

WebDriver driver;
    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://the-internet.herokuapp.com/iframe");
    }

    @Test
    public void iframeTest() {
        //  ○ “An IFrame containing….” textinin erisilebilir oldugunu test edin ve  konsolda    yazdirin.
        WebElement text = driver.findElement(By.xpath("//h3[.='An iFrame containing the TinyMCE WYSIWYG Editor']"));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(text.isEnabled(),"The text disabled");

        //        ○ Text Box’a “Merhaba Dunya!” yazin.
        WebElement iframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iframe);
        WebElement textBox= driver.findElement(By.tagName("p"));
        textBox.clear();
        textBox.sendKeys("Hello World!");

        driver.switchTo().defaultContent();
        //        ○ TextBox’in altinda bulunan “Elemental Selenium” linkini textinin gorunur oldugunu
        //        dogrulayin ve  konsolda yazdirin.
        WebElement elemental = driver.findElement(By.xpath("//a[text()='Elemental Selenium']"));
        softAssert.assertTrue(elemental.isEnabled());

        softAssert.assertAll();



    }

    @AfterClass
    public void teardown() {
    driver.close();
    }

}
