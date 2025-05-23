import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;
import utilMethods.Utils;

import java.io.File;
import java.time.Duration;
import java.util.Objects;

public class IlCarroXpathTest {
    WebDriver driver;
    String email = "test_mkii@gmail.com";
    String password = "@Password123";
    String location = "tel aviv";
    String make = "Toyota";
    String model = "Corolla";
    String year = "2020";
    String seats = "5";
    String carClass = "SUV";
    String carRegistration = Utils.randomRegNum();
    String price = "250";
    String aboutText = "This is my test car for ilcarro project, its brand new and in a great condition";


    @BeforeSuite
    public void driverSetup() {
        driver = new ChromeDriver();
        driver.get("https://ilcarro.web.app/");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void iLCarroXpathLogin() {
        WebElement navBtnLogin = driver.findElement(By.xpath("//a[text()=' Log in ']"));
        navBtnLogin.click();
        Utils.pause(2);
        Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("login"));

        WebElement emailInput = driver.findElement(By.xpath("//*[@type='email']"));
        emailInput.sendKeys(email);
        WebElement passwordInput = driver.findElement(By.xpath("//input[@type='password']"));
        passwordInput.sendKeys(password);
        WebElement yallaBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        yallaBtn.click();
        Utils.pause(5);

        WebElement loginMessage = driver.findElement(By.xpath("//mat-dialog-container/app-error"));
        Assert.assertTrue(loginMessage.isDisplayed());
        Assert.assertTrue(loginMessage.getText().contains("Logged in success"));
        Utils.pause(2);
        WebElement closeBtn = driver.findElement(By.xpath("//button[text()='Ok']"));
        closeBtn.click();
        Utils.pause(2);
    }

    @Test
    public void iLCarroletTheCarWork() {
        WebElement navBtnLTCW = driver.findElement(By.xpath("//a[@id='1']"));
        navBtnLTCW.click();
        Utils.pause(2);

        WebElement inputLocation = driver.findElement(By.xpath("//input[@id='pickUpPlace']"));
        inputLocation.sendKeys(location);
        Utils.pause(2);
        inputLocation.sendKeys(Keys.ARROW_DOWN);
        inputLocation.sendKeys(Keys.ENTER);

        WebElement inputManufacture = driver.findElement(By.xpath("//input[@id='make']"));
        inputManufacture.sendKeys(make);
        WebElement inputModel = driver.findElement(By.xpath("//input[@id='model']"));
        inputModel.sendKeys(model);
        WebElement inputYear = driver.findElement(By.xpath("//input[@id='year']"));
        inputYear.sendKeys(year);
        WebElement selectFuel = driver.findElement(By.xpath("//select[@id='fuel']"));
        selectFuel.click();
        Select select = new Select(selectFuel);
        select.selectByIndex(2);
        selectFuel.click();
        WebElement inputSeats = driver.findElement(By.xpath("//input[@id='seats']"));
        inputSeats.sendKeys(seats);
        WebElement inputCarClass = driver.findElement(By.xpath("//input[@id='class']"));
        inputCarClass.sendKeys(carClass);
        WebElement inputCarRegistration = driver.findElement(By.xpath("//input[@id='serialNumber']"));
        inputCarRegistration.sendKeys(carRegistration);
        WebElement inputPrice = driver.findElement(By.xpath("//input[@id='price']"));
        inputPrice.sendKeys(price);
        WebElement inputAbout = driver.findElement(By.xpath("//textarea[@id='about']"));
        inputAbout.sendKeys(aboutText);
        WebElement uploadPhoto = driver.findElement(By.xpath("//input[@type='file']"));
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("MyCar.jpg")).getFile());
        uploadPhoto.sendKeys(file.getAbsolutePath());
        Utils.pause(6);
        WebElement submitBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        submitBtn.click();
        Utils.pause(2);
        WebElement showCarBtn = driver.findElement(By.xpath("//button[text()='Show car']"));
        showCarBtn.click();
        Utils.pause(5);

    }

    @AfterSuite
    public void closeDriver() {
        driver.quit();
    }


}
