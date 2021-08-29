package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.DBUtils;
import utilities.Driver;
import utilities.ReusableMethods;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonPageElements {
    public CommonPageElements() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//a[contains(text(),'Next')]")
    public WebElement nextButton;
    @FindBy(id = "twotabsearchtextbox")
    public WebElement searchBox;
    @FindBy(xpath = "(//div[@class='a-checkbox a-checkbox-fancy s-navigation-checkbox aok-float-left'])[1]")
    public WebElement appleCheckBox;
    @FindBy(xpath = "//*[@id='search']//h2/parent::div/parent::div")
    public List<WebElement> allProductsOnPage;
    @FindBy(xpath = "//div//h2")
    public List<WebElement> titles;
    @FindBy(xpath = "//span[@id='productTitle']")
    public WebElement actualProductTitle;
    @FindBy(xpath = "//span[@id='acrCustomerReviewText']")
    public WebElement actualProductReview;
    @FindBy(xpath = "//span[@id='priceblock_ourprice']")
    public WebElement actualProductPrice;


    //==========
    int id = 0;

    public void getAllProductOnPage(List<WebElement> products) {
        List<WebElement> allProducts = products;

        for (int i = 0; i < allProducts.size() - 1; i++) {
            Map<String, Object> oneProduct = new HashMap<>();
            List<WebElement> productInfos = allProducts.get(i).findElements(By.xpath("./child::*"));

            oneProduct.put("Title", productInfos.get(0).getText());
            String review = ReusableMethods.reformatReview(productInfos.get(1).getText());
            oneProduct.put("Review", review);
            if (productInfos.size() > 2 && !(productInfos.get(2).getText().contains("Currently unavailable."))) {
                String reformatPrice = ReusableMethods.reformatPrice(productInfos.get(2).getText());
                oneProduct.put("Price",reformatPrice);

            } else {
                oneProduct.put("Price", "0");
            }
            id++;
            DBUtils.Database_write(id, oneProduct.get("Title").toString(), oneProduct.get("Price").toString(), oneProduct.get("Review").toString(), LocalDateTime.now());
        }
    }

    public void getAllItemsAndSelectSpecificOne(List<WebElement> products, String expectedTitle) {
        List<WebElement> allProducts = products;

        for (int i = 0; i < allProducts.size(); i++) {
            for (WebElement title:titles) {
                if (title.getText().equals(expectedTitle)) {
                  //  System.out.println(title.getText());
                    title.click();
                    break;
                }
            }
            if (ReusableMethods.isElementPresent(nextButton)) {
                ReusableMethods.clickWithJS(nextButton);
                ReusableMethods.waitForPageToLoad(2);
            } else {
                break;
            }
        }
    }
}
