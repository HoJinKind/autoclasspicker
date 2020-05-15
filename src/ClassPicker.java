import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.List;

//things to improve: crash if class not found
//select timing of class, currently, always select first one
public class ClassPicker {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
        WebDriver driver = new ChromeDriver(options);
//        driver.get("https://linkedin.com/in/fadhli-bafagih-72b286131");
//        driver.get("https://twitter.com/realDonaldTrump");
        driver.get(args[0]);
        try {
            String text1 = driver.findElement(By.xpath("//*[@class='main']")).getAttribute("innerHTML");
            System.out.println("authwall" + text1);
            if (text1.contains("form class=\"login-form\"")){
                return;
            }
        } catch(Exception e) {
        }
        try{
            String text = driver.findElement(By.xpath("//*[@class='app__content']/h1")).getAttribute("innerHTML");
            System.out.println("this is text" + text);

        } catch ( Exception e) {
            System.out.println("managed to get thru");

            List<WebElement> companyLs = driver.findElements(By.xpath("//*[@class='result-card__subtitle " +
                    "experience-item__subtitle']/a"));
            List<WebElement> roleLs = driver.findElements(By.xpath("//*[@class='result-card__contents experience-item__contents']/h3"));

            List<WebElement> detailsLs = driver.findElements(By.xpath("//*[@class='experience-item__description " +
                    "experience-item__meta-item']/*[@class='show-more-less-text show-more-less-description']/" +
                    "*[@class='show-more-less-text__text--less']"));
            List<WebElement> durationLs =  driver.findElements(By.xpath("//*[@class='experience-item__duration " +
                    "experience-item__meta-item']/span"));

            List<WebElement> countryLs = driver.findElements(By.xpath("//*[@class='experience-item__location " +
                    "experience-item__meta-item']"));
            WebElement profilePic = driver.findElement(By.xpath("//*[@class='artdeco-entity-image " +
                    "artdeco-entity-image--profile artdeco-entity-image--circle-8 top-card-layout__entity-image lazy-loaded']"));
            String dpSrc = profilePic.getAttribute("src");
            System.out.println("dp:" + dpSrc);

            WebElement connectionsElement = driver.findElement(By.xpath("//*[@class='top-card__subline-item " +
                    "top-card__subline-item--bullet']"));

            String connections =  connectionsElement.getAttribute("innerHTML");
            System.out.println("Connections:" + connections);
            WebElement summaryElement = driver.findElement(By.xpath("//*[@class='summary pp-section']/p"));
            String summary = summaryElement.getAttribute("innerHTML");

            System.out.println("Summary:" + summary);
            for (int i = 0; i < companyLs.size(); i++)  {
                String companyI =  companyLs.get(i).getAttribute("innerHTML");
                String roleI =  roleLs.get(i).getAttribute("innerHTML");
                String detailsI = detailsLs.get(i).getAttribute("innerHTML");
                String durationI = durationLs.get(i).getAttribute("innerHTML");
                String countryI = countryLs.get(i).getAttribute("innerHTML");
                System.out.println("this is role:" + roleI + '\n' + "company:" + companyI + '\n' + detailsI  + '\n' + durationI + '\n' + countryI);
            }

//            String textTest = driver.findElement(By.xpath("//*[@class='experience-item__description " +
//                    "experience-item__meta-item']/*[@class='show-more-less-text show-more-less-description']/" +
//                    "*[@class='show-more-less-text__text--more']")).getAttribute("innerHTML");
//            System.out.println(textTest);
        }
        Thread.sleep(1000000);
//        try {
//            BasicUtils utils = new BasicUtils();
//            utils.login(driver,  username, password);
//            //for loop here
//            for(int i=0;i<sessions.length;i++){
//                driver.navigate().to(utils.addClassURL);
//                Boolean classAvailable = false;
//                while (!classAvailable) {
//                    classAvailable = utils.checkClassesReleased(driver);
//                    Thread.sleep(700);
//                }
//                System.out.println("found");
//                utils.selectClassSearchDummyPage_stepOne(driver);
//                utils.selectSearchCriteriaPage_stepTwo(driver, sessions[i][0]);
//                Thread.sleep(700);
//                Boolean timingAvailable= utils.selectClassTimingPage_stepThree(driver, sessions[i][1]);
//                if(timingAvailable){
//                    utils.firstConfirmationPage_stepFour(driver);
//                    utils.secondConfirmationPage_stepFive(driver);
//                    //warning: this will enroll you into class, only use at production phase, not development
//                    //utils.finishEnrollingClassPage(driver);
//
//                    System.out.println(String.format("Successfully applied for class: %s",sessions[i][0]));
//                    Thread.sleep(7000);
//                }
//                else{
//                    System.out.println(String.format("Could not apply class: %s at  timing %s" ,sessions[i][0], sessions[i][1]));
//                }
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//
//        }sounds dangerous
        driver.quit();
    }
}
