package utilities;

import org.openqa.selenium.Keys;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.CommonPageElements;

public class BaseClass {
    CommonPageElements elements = new CommonPageElements();
    @BeforeMethod
    public void preconditions(){
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        elements.searchBox.sendKeys(ConfigurationReader.getProperty("product") + Keys.ENTER);
        ReusableMethods.clickWithJS(elements.appleCheckBox);
    }
    @AfterMethod
    public void tearDown(){
        Driver.quitDriver();
    }

}
