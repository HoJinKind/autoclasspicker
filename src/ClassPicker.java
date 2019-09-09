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
        String[] sessions = {"02.139","50.042"};
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        Thread.sleep(1000);

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://myportal.sutd.edu.sg");

        Thread.sleep(1000);
        try {
            BasicUtils utils = new BasicUtils();
            utils.login(driver,  username, password);
            //for loop here
            for(int i=0;i<sessions.length;i++){
                driver.navigate().to(utils.addClassURL);
                Thread.sleep(1000);
                Boolean classAvailable = false;
                while (!classAvailable) {
                    classAvailable = utils.checkClassesReleased(driver);
                    Thread.sleep(1000);
                }
                System.out.println("found");
                    utils.selectClassSearchDummyPage_stepOne(driver);
                    Thread.sleep(1000);
                    utils.selectSearchCriteriaPage_stepThree(driver, sessions[i]);
                    Thread.sleep(1000);
                    utils.selectClassTimingPage_step_four(driver);
                    Thread.sleep(1000);
                    utils.firstConfirmationPage_stepFive(driver);
                    Thread.sleep(1000);
                    utils.secondConfirmationPage_stepSeven(driver);
                    Thread.sleep(1000);
                    //warning: this will enroll you into class, only use at production phase, not development
                    //utils.finishEnrollingClassPage(driver);
                    Thread.sleep(5000);
            }
        } catch (Exception e) {
            System.out.println(e);

        }
        driver.quit();
    }
}