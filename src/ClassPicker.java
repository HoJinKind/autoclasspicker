import java.io.IOException;
import java.lang.reflect.Array;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

//things to improve: crash if class not found
//select timing of class, currently, always select first one
public class ClassPicker {

    public static void main(String[] args) throws IOException, InterruptedException {
        String username = "1002846";
        String password = "Sutd1234";
        //note, sort your mods, by most impt, if classes run out, it will crash
        String[] sessions = {"02.141","50.042"};
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://myportal.sutd.edu.sg");
        try {
            BasicUtils utils = new BasicUtils();
            utils.login(driver,  username, password);
            //for loop here
            for(int i=0;i<sessions.length;i++){
                driver.navigate().to(utils.addClassURL);
                Boolean classAvailable = false;
                while (!classAvailable) {
                    classAvailable = utils.checkClassesReleased(driver);
                    Thread.sleep(700);
                }
                System.out.println("found");
                utils.selectClassSearchDummyPage_stepOne(driver);
                utils.selectSearchCriteriaPage_stepTwo(driver, sessions[i]);
                Thread.sleep(700);
                Boolean timingAvailable= utils.selectClassTimingPage_stepThree(driver);
                if(timingAvailable){
                    utils.firstConfirmationPage_stepFour(driver);
                    utils.secondConfirmationPage_stepFive(driver);
                    //warning: this will enroll you into class, only use at production phase, not development
                    //utils.finishEnrollingClassPage(driver);

                    System.out.println(String.format("Successfully applied for class: %s",sessions[i]));
                    Thread.sleep(7000);
                }
                else{
                    System.out.println(String.format("Could not apply class: %s",sessions[i]));
                }
            }
        } catch (Exception e) {
            System.out.println(e);

        }
        driver.quit();
    }
}