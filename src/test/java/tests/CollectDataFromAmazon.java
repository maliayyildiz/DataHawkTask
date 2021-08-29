package tests;

import org.testng.annotations.Test;
import pages.CommonPageElements;
import utilities.BaseClass;
import utilities.ReusableMethods;

public class CollectDataFromAmazon extends BaseClass {
    CommonPageElements elements = new CommonPageElements();
    @Test
    public void collectDataFromAmazonAndStoreDB(){
        for (int j = 1; j <100 ; j++) {
            elements.getAllProductOnPage(elements.allProductsOnPage);
            if (ReusableMethods.isElementPresent(elements.nextButton)) {
                ReusableMethods.clickWithJS(elements.nextButton);
                ReusableMethods.waitForPageToLoad(2);
            } else {
                break;
            }
        }
    }

}
