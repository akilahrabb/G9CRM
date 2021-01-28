package com.NextBaseCRM.tests.Akilah;

import com.NextBaseCRM.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

public class LogWorkHoursWithTimeReporting {

    WebDriver driver;
    WebElement enterLogin;
    WebElement enterPassword;
    WebElement loginButton;
    WebElement timeManager;
    WebElement clockInButton;
    WebElement clockOutButton;
    WebElement breakButton;
    WebElement userBlock;
    WebElement logout;
    WebElement enterTask;

    @BeforeMethod
    public void setupMethod () {
        driver = WebDriverFactory.getDriver("chrome");
        driver.get("https://login2.nextbasecrm.com/login");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        enterLogin = driver.findElement(By.xpath("//input[@name='USER_LOGIN']"));
        enterLogin.sendKeys("hr18@cybertekschool.com");
        enterPassword = driver.findElement(By.xpath("//input[@name='USER_PASSWORD']"));
        enterPassword.sendKeys("UserUser");
        loginButton = driver.findElement(By.xpath("//input[@class='login-btn']"));
        loginButton.click();
    }

    @AfterMethod
    public void tearDownMethod () {
    userBlock = driver.findElement(By.xpath("//div[@onclick='showUserMenu()']"));
    userBlock.click();
    logout = driver.findElement(By.xpath("//span[contains(text(), 'Log out')]"));
    logout.click();

    driver.close();
    }

    @Test
    public void ClockIn () {
    timeManager = driver.findElement(By.xpath("//span[@id='timeman-status']"));
    timeManager.click();

    clockInButton = driver.findElement(By.xpath("//span[@class='webform-small-button tm-popup-main-button webform-small-button-accept']"));
    clockInButton.click();

    clockOutButton = driver.findElement(By.xpath("//span[@class='webform-small-button tm-popup-main-button webform-small-button-decline']"));
        if (clockOutButton.isDisplayed()) {
            System.out.println("Employee is clocked in. Verification PASS!");
        } else {
            System.out.println("FAIL!");
        }
    }

    @Test
    public void ClockOut () {
    timeManager = driver.findElement(By.xpath("//span[@id='timeman-status']"));
    timeManager.click();

    clockOutButton = driver.findElement(By.xpath("//span[@class='webform-small-button tm-popup-main-button webform-small-button-decline']"));
    clockOutButton.click();

    timeManager = driver.findElement(By.xpath("//span[@id='timeman-status']"));
    timeManager.click();
    breakButton = driver.findElement(By.xpath("//span[@class='webform-small-button tm-webform-button-pause']"));
        if (!breakButton.isDisplayed()) {
            System.out.println("BREAK button is no longer displayed. Verification PASS!");
        } else {
            System.out.println("FAIL!");
        }
    }

    @Test
    public void editTasks(){
        timeManager = driver.findElement(By.xpath("//span[@id='timeman-status']"));
        timeManager.click();

        WebElement enterTask = driver.findElement(By.xpath("//input[@class='tm-popup-task-form-textbox']"));
        enterTask.click();
        enterTask.sendKeys("New task entered");
        driver.findElement(By.xpath("//span[@class='tm-popup-task-form-submit']")).click();
        WebElement expectedResult = driver.findElement(By.xpath("//*[text()='New task entered']"));

        if(expectedResult.isDisplayed()){
            System.out.println("New task displayed. Test editTasks PASSED.");
        }else{
            System.out.println("New task not displayed. Test editTasks FAILED.");
        }
    }

    @Test
    public void addEvents( ){
        timeManager = driver.findElement(By.xpath("//span[@id='timeman-status']"));
        timeManager.click();

        driver.findElement(By.xpath("//input[@class='tm-popup-event-start-time-textbox_am_pm']")).click();
        driver.findElement(By.xpath("//div[@class='bxc-controls-cont']//td[2]")).click();
        driver.findElement(By.xpath("//span[@class='popup-window-button popup-window-button-create']")).click();

        driver.findElement(By.xpath("//input[@class='tm-popup-event-end-time-textbox_am_pm']")).click();
        driver.findElement(By.xpath("//div[@class='bxc-controls-cont']//td[2]")).click();
        driver.findElement(By.xpath("//span[@class='popup-window-button popup-window-button-create']")).click();

        WebElement enterEvent = driver.findElement(By.xpath("//input[@class='tm-popup-event-form-textbox_am_pm']"));
        enterEvent.click();
        enterEvent.sendKeys("New event entered");
        driver.findElement(By.xpath("//span[@class='tm-popup-event-form-submit']")).click();

        WebElement expectedResult = driver.findElement(By.xpath("//*[text()='New event entered']"));

        if(expectedResult.isDisplayed()){
            System.out.println("New event displayed. Test addEvents PASSED.");
        }else{
            System.out.println("New event not displayed. Test addEvents FAILED.");
        }
    }



}
