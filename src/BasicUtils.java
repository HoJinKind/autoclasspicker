import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BasicUtils {

    public String classSearchURL = "https://myportal.sutd.edu.sg/psp/EPPRD/EMPLOYEE/HRMS/c/SA_LEARNER_SERVICES.CLASS_SEARCH.GBL?FolderPath=PORTAL_ROOT_OBJECT.CO_EMPLOYEE_SELF_SERVICE.HCCC_SS_CATALOG.HC_CLASS_SEARCH&IsFolder=false&IgnoreParamTempl=FolderPath%2cIsFolder";
    public String addClassURL = "https://myportal.sutd.edu.sg/psp/EPPRD/EMPLOYEE/HRMS/c/SA_LEARNER_SERVICES.SSR_SSENRL_CART.GBL?FolderPath=PORTAL_ROOT_OBJECT.CO_EMPLOYEE_SELF_SERVICE.HCCC_ENROLLMENT.HC_SSR_SSENRL_CART_GBL&IsFolder=false&IgnoreParamTempl=FolderPath%2cIsFolder";
    public  String commonFrameID = "ptifrmtgtframe";

    public void login(WebDriver driver, String username, String password){
        WebElement usernameElement = driver.findElement(By.id("userid"));
        usernameElement.sendKeys(username.toString());
        WebElement passwordElement = driver.findElement(By.id("pwd"));
        passwordElement.sendKeys(password.toString());
        driver.findElement(By.name("Submit")).click();
    }


    public Boolean checkClassesReleased(WebDriver driver){
        navigateIframe(commonFrameID, driver);
        if(!driver.findElements(By.id("DERIVED_REGFRM1_SS_MESSAGE_LONG")).isEmpty()){
            WebElement checker = driver.findElement(By.id("DERIVED_REGFRM1_SS_MESSAGE_LONG"));
            if (!checker.getText().equals("You do not have access to enrollment at this time.")){


                System.out.println(checker.getText());
                System.out.println(checker.getText() != "You do not have access to enrollment at this time.");
                return false;
            }
            else {
                driver.navigate().refresh();
                System.out.println("refresh");
                return true;

            }
        }
        return true;
    }

    public void selectSearchCriteriaPage_stepThree(WebDriver driver, String session){
        navigateIframe(commonFrameID, driver);
        //Select termDropDown = new Select(driver.findElement(By.id("CLASS_SRCH_WRK2_STRM$35$")));
        //termDropDown.selectByVisibleText("Acad Year 2019 Sep");
        //select subject
        Select subjectDropDown = new Select(driver.findElement(By.id("SSR_CLSRCH_WRK_SUBJECT_SRCH$0")));
        subjectDropDown.selectByVisibleText(session.substring(0,2));
        Select courseDropDown = new Select(driver.findElement(By.id("SSR_CLSRCH_WRK_SSR_EXACT_MATCH1$1")));
        courseDropDown.selectByVisibleText("contains");
        WebElement password = driver.findElement(By.id("SSR_CLSRCH_WRK_CATALOG_NBR$1"));
        password.sendKeys(session.substring(2,6));

        driver.findElement(By.name("CLASS_SRCH_WRK2_SSR_PB_CLASS_SRCH")).click();
    }
    public void navigateIframe(String iframe, WebDriver driver){
        driver.switchTo().defaultContent();
        if(!driver.findElements(By.id(iframe)).isEmpty()){
            driver.switchTo().frame(iframe);
        }
    }

    public void selectClassSearchDummyPage_stepOne(WebDriver driver){
        //TODO click radio button of term, and continue button
        driver.findElement(By.id("DERIVED_REGFRM1_SSR_PB_SRCH")).click();
    }

    public void selectClassTimingPage_step_four(WebDriver driver){
        //TODO need to click the right timing  SSR_PB_SELECT$0, SSR_PB_SELECT$1, depending on class
        navigateIframe(commonFrameID, driver);
        driver.findElement(By.id("win0divSSR_PB_SELECT$0")).click();
    }

    public void firstConfirmationPage_stepFive(WebDriver driver){
        //TODO click next
        navigateIframe(commonFrameID, driver);
        driver.findElement(By.id("win0divDERIVED_CLS_DTL_NEXT_PB$280$")).click();

    }

    public void secondConfirmationPage_stepSeven(WebDriver driver){
        //TODO click proceed to step 2 of 3 DERIVED_REGFRM1_LINK_ADD_ENRL$82$
        navigateIframe(commonFrameID, driver);
        driver.findElement(By.id("win0divDERIVED_REGFRM1_LINK_ADD_ENRL$82$")).click();
    }

    public void finishEnrollingClassPage(WebDriver driver){
        navigateIframe(commonFrameID, driver);
        driver.findElement(By.id("win0divDERIVED_REGFRM1_SSR_PB_SUBMIT")).click();
    }
    //after this we can add multiple classes, or we can immediately finish enrolling
    // for now we will just finish enrolling   win0divDERIVED_REGFRM1_SSR_PB_SUBMIT

}
