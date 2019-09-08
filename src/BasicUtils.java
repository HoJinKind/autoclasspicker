import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasicUtils {
    public void login(WebDriver driver){
        WebElement username = driver.findElement(By.id("userid"));
        username.sendKeys("1002846");
        WebElement password = driver.findElement(By.id("pwd"));
        password.sendKeys("Sutd1234");
        driver.findElement(By.name("Submit")).click();
    }

    public Boolean checkClassesReleased(WebDriver driver){
        driver.switchTo().frame("ptifrmtgtframe");
        WebElement checker = driver.findElement(By.id("DERIVED_REGFRM1_SS_MESSAGE_LONG"));
        if (!checker.getText().equals("You do not have access to enrollment at this time.")){


            System.out.println(checker.getText());
            System.out.println(checker.getText() != "You do not have access to enrollment at this time.");
            return false;
        }
        else{
            driver.navigate().refresh();
            System.out.println("refresh");
            return true;

        }
    }

}
