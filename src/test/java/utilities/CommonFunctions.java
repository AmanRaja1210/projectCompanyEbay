package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class CommonFunctions {

    private WebDriver driver;
    private  WebDriverWait wait;

    public CommonFunctions(WebDriver driver)
    {
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void switchToSingleNewTab()
    {
        String originalWindow= driver.getWindowHandle();
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        Set<String> windowsandles= driver.getWindowHandles();
        for(String windowHandle:windowsandles)
        {
            if(!windowHandle.equals(originalWindow))
            {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

}
