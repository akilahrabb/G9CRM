package com.NextBaseCRM.tests.AutomationTesting_LoginFunction;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Arrays;

public class AutomatingTestingFunction {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        //new ChromeDriver(); //opens up the actual browser
        //new ChromeDriver();
//===============================================================================

        //DOING STEP 1 OF OUR TEST EXECUTION
        driver.get("http://login2.nextbasecrm.com");
        String expectedTitle = "Authorization";
        String actualTitle = driver.getTitle();
        if(expectedTitle.equals(actualTitle)){
            System.out.println("Title verification has PASSED. STEP 1 COMPLETE!");
            System.out.println("actualTitle = " + actualTitle);
            System.out.println("expectedTitle = " + expectedTitle);
        }else{
            System.out.println("actualTitle = " + actualTitle);
            System.out.println("expectedTitle = " + expectedTitle);
        }

//===============================================================================
        /*DOING STEP 2 OF OUR TEST EXECUTION

DATA SET:
        POSITIVE USERNAMES:
helpdesk17@cybertekschool.com
helpdesk18@cybertekschool.com
hr17@cybertekschool.com
hr18@cybertekschool.com
marketing17@cybertekschool.com
marketing18@cybertekschool.com

NEGATIVE USERNAMES:
"helpdesk@cybertekschool.com",
"hr@cybertekschool.com",
"marketingg16cybertekschool.com",

         */
        ArrayList<String> positiveDataSet = new ArrayList<String>(Arrays.asList(
                "helpdesk17@cybertekschool.com",
                "helpdesk18@cybertekschool.com",
                "hr17@cybertekschool.com",
                "hr18@cybertekschool.com",
                "marketing17@cybertekschool.com",
                "marketing18@cybertekschool.com",

                //negative data
                "helpdesk@cybertekschool.com",
                "hr@cybertekschool.com",
                "marketingg16cybertekschool.com" )) ;

        String password = "UserUser";


        for(String each: positiveDataSet){
            //String each = positiveDataSet[i]; for regular For loopdiLoop
            driver.findElement(By.name("USER_LOGIN")).sendKeys(each); //username
            driver.findElement(By.name("USER_PASSWORD")).sendKeys(password);//password

            driver.findElement(By.className("login-btn")).click();//click login button
            Thread.sleep(2000);
            String expectedURL = "https://login2.nextbasecrm.com/stream/";
            String actualURL = driver.getCurrentUrl();
            if(expectedURL.equals(actualURL)){
                System.out.println("URL HAS PASSED! For username: " + each);
            }else{
                System.out.println("FAILED! For username: "+ each);
                System.out.println("actualURL = " + actualURL);
                System.out.println("expectedURL = " + expectedURL);
            }


            Thread.sleep(2000);
            driver.findElement(By.id("user-name")).click();//getting to the logout link
            Thread.sleep(2000);
            driver.findElement(By.linkText("Log out")).click();//the actual log out link
            Thread.sleep(1000);//sleep for smoother run

            driver.findElement(By.name("USER_LOGIN")).clear(); //username


        }

    }
}
