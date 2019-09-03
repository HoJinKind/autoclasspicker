import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

public class ClassPicker{

    public static void main(String[] args) throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
            Thread.sleep(5000);

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://myportal.sutd.edu.sg");

        Thread.sleep(1000);

        boolean present;
        try {
            WebElement username = driver.findElement(By.id("userid"));
            username.sendKeys("1002846");
            WebElement password = driver.findElement(By.id("pwd"));
            password.sendKeys("Sutd1234");
            driver.findElement(By.name("Submit")).click();
            driver.navigate().to("https://myportal.sutd.edu.sg/psp/EPPRD/EMPLOYEE/HRMS/c/SA_LEARNER_SERVICES.SSR_SSENRL_CART.GBL?FolderPath=PORTAL_ROOT_OBJECT.CO_EMPLOYEE_SELF_SERVICE.HCCC_ENROLLMENT.HC_SSR_SSENRL_CART_GBL&IsFolder=false&IgnoreParamTempl=FolderPath%2cIsFolder");
            Thread.sleep(1000);
            Boolean refresh = true;
            while(refresh){
                driver.switchTo().frame("ptifrmtgtframe");
                WebElement checker = driver.findElement(By.id("DERIVED_REGFRM1_SS_MESSAGE_LONG"));
                if (!checker.getText().equals("You do not have access to enrollment at this time.")){

                    refresh = false;
                    System.out.println(checker.getText());
                    System.out.println(checker.getText() != "You do not have access to enrollment at this time.");
                }
                else{
                    driver.navigate().refresh();
                    System.out.println("refresh");
                    Thread.sleep(3000);

                }
            }

            present = true;
        } catch (Exception e) {
            present = false;

            System.out.println(e);

        }
        driver.quit();
    }
}