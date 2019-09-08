import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

public class ClassPicker {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        Thread.sleep(5000);

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://myportal.sutd.edu.sg");

        Thread.sleep(1000);
        try {
            BasicUtils utils = new BasicUtils();
            utils.login(driver);
            driver.navigate().to("https://myportal.sutd.edu.sg/psp/EPPRD/EMPLOYEE/HRMS/c/SA_LEARNER_SERVICES.SSR_SSENRL_CART.GBL?FolderPath=PORTAL_ROOT_OBJECT.CO_EMPLOYEE_SELF_SERVICE.HCCC_ENROLLMENT.HC_SSR_SSENRL_CART_GBL&IsFolder=false&IgnoreParamTempl=FolderPath%2cIsFolder");
            Thread.sleep(1000);
            Boolean refresh = true;
            while (refresh) {
                refresh = utils.checkClassesReleased(driver);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println(e);

        }
        driver.quit();
    }
}