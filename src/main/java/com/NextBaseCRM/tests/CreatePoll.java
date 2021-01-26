package com.NextBaseCRM.tests;

import com.NextBaseCRM.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CreatePoll {

    WebDriver driver;

    @BeforeMethod
    public void setupMethod() throws InterruptedException {
        driver = WebDriverFactory.getDriver("chrome");
        driver.get("http://login2.nextbasecrm.com");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        WebElement logIn = driver.findElement(By.xpath("//input[@name='USER_LOGIN']"));
        logIn.sendKeys("hr17@cybertekschool.com");
        WebElement password = driver.findElement(By.xpath("//input[@name='USER_PASSWORD']"));
        password.sendKeys("UserUser");
        WebElement loginButton = driver.findElement(By.xpath("//input[@class='login-btn']"));
        loginButton.click();
        WebElement PollButton = driver.findElement(By.xpath("//span[@id='feed-add-post-form-tab-vote']"));
        PollButton.click();
        Assert.assertTrue(PollButton.isDisplayed(), "Poll title message window is not displayed");
        Thread.sleep(2000);
    }


    @Test
    public void test_PollTitle() throws InterruptedException {

        WebElement frame = driver.findElement(By.xpath("//iframe[@class='bx-editor-iframe']"));
        driver.switchTo().frame(frame);
        WebElement TitleInbox = driver.findElement(By.xpath("//body[@contenteditable='true']"));
        TitleInbox.sendKeys("Title Message");
        driver.switchTo().defaultContent();
        Thread.sleep(2000);

        WebElement questionInbox = driver.findElement(By.xpath("//input[@id='question_0']"));
        questionInbox.sendKeys("Question1 ");
        Thread.sleep(2000);

        WebElement answerInbox1 = driver.findElement(By.xpath("//input[@id='answer_0__0_']"));
        answerInbox1.sendKeys("Answer1");
        WebElement answerInbox2 = driver.findElement(By.xpath("//input[@id='answer_0__1_']"));
        answerInbox2.sendKeys("Answer2");
        Thread.sleep(2000);

        WebElement sendButton = driver.findElement(By.xpath("//button[@id='blog-submit-button-save']"));
        sendButton.click();

        WebElement voteButton= driver.findElement(By.xpath("//button[@class='ui-btn ui-btn-lg ui-btn-primary']"));
        if (voteButton.isDisplayed()){
            System.out.println("Poll is created successfully");
        }else {
            System.out.println("You already added your post");

        }
    }

    @Test
    public void test_AllowMultipleChoices() {

        WebElement checkbox = driver.findElement(By.xpath("//input[@id='multi_0']"));
        checkbox.click();
    }

    @Test
    public void test_AddQuestion() {

        WebElement addQuestion= driver.findElement(By.xpath("//a[@class='vote-new-question-link addq']"));
        addQuestion.click();
        WebElement questionInbox = driver.findElement(By.xpath("//input[@id='question_1']"));
        WebElement answerInbox1 = driver.findElement(By.xpath("//input[@id='answer_1__0_']"));
        WebElement answerInbox2 = driver.findElement(By.xpath("//input[@id='answer_1__1_']"));

        Assert.assertTrue(questionInbox.isDisplayed(),"Question input box is not displayed!");
        Assert.assertTrue(answerInbox1.isDisplayed(),"First answer input box is not displayed!");
        Assert.assertTrue(answerInbox2.isDisplayed(),"Second answer input box is not displayed!");

    }

    @Test
    public void test_CancelButton() {

        WebElement cancelButton= driver.findElement(By.xpath("//button[@id='blog-submit-button-cancel']"));
        cancelButton.click();
        WebElement questionInbox = driver.findElement(By.xpath("//input[@id='question_0']"));
        Assert.assertFalse(questionInbox.isDisplayed(),"Can not return to \"Activity Stream\" window!");

    }

    @AfterMethod
    public void tearDownMethod() {

    }

}

