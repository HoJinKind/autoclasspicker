import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasicUtils {

    public String classSearchURL = "https://myportal.sutd.edu.sg/psp/EPPRD/EMPLOYEE/HRMS/c/SA_LEARNER_SERVICES.CLASS_SEARCH.GBL?FolderPath=PORTAL_ROOT_OBJECT.CO_EMPLOYEE_SELF_SERVICE.HCCC_SS_CATALOG.HC_CLASS_SEARCH&IsFolder=false&IgnoreParamTempl=FolderPath%2cIsFolder";
    public String addClassURL = "https://myportal.sutd.edu.sg/psp/EPPRD/EMPLOYEE/HRMS/c/SA_LEARNER_SERVICES.SSR_SSENRL_CART.GBL?FolderPath=PORTAL_ROOT_OBJECT.CO_EMPLOYEE_SELF_SERVICE.HCCC_ENROLLMENT.HC_SSR_SSENRL_CART_GBL&IsFolder=false&IgnoreParamTempl=FolderPath%2cIsFolder";
    public String commonFrameID = "ptifrmtgtframe";

    public void login(WebDriver driver, String username, String password) {
        WebElement usernameElement = driver.findElement(By.id("userid"));
        usernameElement.sendKeys(username.toString());
        WebElement passwordElement = driver.findElement(By.id("pwd"));
        passwordElement.sendKeys(password.toString());
        driver.findElement(By.name("Submit")).click();
    }

    public Boolean checkClassesReleased(WebDriver driver) {
        navigateIframe(commonFrameID, driver);
        if (!driver.findElements(By.id("DERIVED_REGFRM1_SS_MESSAGE_LONG")).isEmpty()) {
            WebElement checker = driver.findElement(By.id("DERIVED_REGFRM1_SS_MESSAGE_LONG"));
            if (!checker.getText().equals("You do not have access to enrollment at this time.")) {


                System.out.println(checker.getText());
                System.out.println(checker.getText() != "You do not have access to enrollment at this time.");
                return false;
            } else {
                driver.navigate().refresh();
                System.out.println("refresh");
                return true;

            }
        }
        return true;
    }

    public void selectClassSearchDummyPage_stepOne(WebDriver driver) {
        //TODO click radio button of term, and continue button
        waitElement(driver, "DERIVED_REGFRM1_SSR_PB_SRCH");
        driver.findElement(By.id("DERIVED_REGFRM1_SSR_PB_SRCH")).click();
    }

    public void selectSearchCriteriaPage_stepTwo(WebDriver driver, String session) {
        navigateIframe(commonFrameID, driver);
        //Select termDropDown = new Select(driver.findElement(By.id("CLASS_SRCH_WRK2_STRM$35$")));
        //termDropDown.selectByVisibleText("Acad Year 2019 Sep");
        //select subject
        waitElement(driver, "SSR_CLSRCH_WRK_SUBJECT_SRCH$0");
        waitElement(driver, "SSR_CLSRCH_WRK_SSR_EXACT_MATCH1$1");
        waitElement(driver, "SSR_CLSRCH_WRK_CATALOG_NBR$1");
        waitElement(driver, "CLASS_SRCH_WRK2_SSR_PB_CLASS_SRCH");

        Select subjectDropDown = new Select(driver.findElement(By.id("SSR_CLSRCH_WRK_SUBJECT_SRCH$0")));
        subjectDropDown.selectByVisibleText(session.substring(0, 2));
        Select courseDropDown = new Select(driver.findElement(By.id("SSR_CLSRCH_WRK_SSR_EXACT_MATCH1$1")));
        courseDropDown.selectByVisibleText("contains");
        WebElement courseNumber = driver.findElement(By.id("SSR_CLSRCH_WRK_CATALOG_NBR$1"));
        courseNumber.sendKeys(session.substring(2, 6));

        driver.findElement(By.name("CLASS_SRCH_WRK2_SSR_PB_CLASS_SRCH")).click();
    }


    public Boolean selectClassTimingPage_stepThree(WebDriver driver) {
        //TODO need to click the right timing  SSR_PB_SELECT$0, SSR_PB_SELECT$1, depending on class

        if (!driver.findElements(By.id("win0divSSR_PB_SELECT$0")).isEmpty()) {
            navigateIframe(commonFrameID, driver);
            driver.findElement(By.id("win0divSSR_PB_SELECT$0")).click();
            return true;
        } else return false;

    }

    public void firstConfirmationPage_stepFour(WebDriver driver) {
        //TODO click next
        waitElement(driver, "win0divDERIVED_CLS_DTL_NEXT_PB$280$");
        navigateIframe(commonFrameID, driver);
        driver.findElement(By.id("win0divDERIVED_CLS_DTL_NEXT_PB$280$")).click();

    }

    public void secondConfirmationPage_stepFive(WebDriver driver) {
        //TODO click proceed to step 2 of 3 DERIVED_REGFRM1_LINK_ADD_ENRL$82$
        waitElement(driver, "win0divDERIVED_REGFRM1_LINK_ADD_ENRL$82$");
        navigateIframe(commonFrameID, driver);
        driver.findElement(By.id("win0divDERIVED_REGFRM1_LINK_ADD_ENRL$82$")).click();

    }

    public void finishEnrollingClassPage(WebDriver driver) {
        waitElement(driver, "win0divDERIVED_REGFRM1_SSR_PB_SUBMIT");
        navigateIframe(commonFrameID, driver);
        driver.findElement(By.id("win0divDERIVED_REGFRM1_SSR_PB_SUBMIT")).click();
    }

    public void navigateIframe(String iframe, WebDriver driver) {
        driver.switchTo().defaultContent();
        if (!driver.findElements(By.id(iframe)).isEmpty()) {
            driver.switchTo().frame(iframe);
        }
    }

    public void waitElement(WebDriver driver, String element) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element)));
    }
}
