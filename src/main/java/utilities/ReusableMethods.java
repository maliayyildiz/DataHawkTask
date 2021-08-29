package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReusableMethods {
    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
    }

    public static boolean isElementPresent(WebElement element) {
        boolean present;
        try {
            present = (element.isDisplayed() || (!element.isDisplayed()));
        } catch (NoSuchElementException e) {
            present = false;
        }
        return present;
    }
    public static String reformatReview(String text){
            text =text.replaceAll("[^0-9]", "");
            return text;
    }

    public static String reformatPrice(String text){
       text = text.substring(0,text.length()-3)+"."+text.substring(text.length()-2);
       return text;
    }

    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeOutInSeconds);
            wait.until(expectation);
        } catch (Exception error) {
           // error.printStackTrace();
        }
    }
}
