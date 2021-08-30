package tests;


import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CommonPageElements;
import utilities.BaseClass;
import utilities.DBUtils;
import utilities.Driver;
import utilities.ReusableMethods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidateCollectedData extends BaseClass {
    CommonPageElements elements = new CommonPageElements();

    @Test
    public void getSpecificProductAndValidate() {
        List<Map<String, Object>> expectedProduct = DBUtils.Database_getSpecificData();
        elements.getAllItemsAndSelectSpecificOne(elements.allProductsOnPage, expectedProduct.get(expectedProduct.size() - 1).get("title").toString());
        new WebDriverWait(Driver.getDriver(), 3).until(ExpectedConditions.visibilityOf(elements.actualProductTitle));
        String actualReview = ReusableMethods.reformatReview(elements.actualProductReview.getText());
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(elements.actualProductTitle.getText(), expectedProduct.get(expectedProduct.size() - 1).get("title").toString());
        softAssert.assertEquals(actualReview, expectedProduct.get(expectedProduct.size() - 1).get("review").toString());
        softAssert.assertEquals(elements.actualProductPrice.getText(), ReusableMethods.reformatPrice(expectedProduct.get(expectedProduct.size() - 1).get("price").toString()));
        softAssert.assertAll();
        System.out.println("Selected product is most reviewed and available on Amazon.");
        System.out.println("Title: " + elements.actualProductTitle.getText());
        System.out.println("Review: " + actualReview);
        System.out.println("Price: " + elements.actualProductPrice.getText());
    }
}
