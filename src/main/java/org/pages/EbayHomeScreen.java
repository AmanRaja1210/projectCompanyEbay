package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EbayHomeScreen {

    private final WebDriver driver;
    private final WebDriverWait wait;
    public String storedItemText;

    // Constructor
    public EbayHomeScreen(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait
    }

        //Locators
        private final By txtBox_search=By.xpath("//input[@type='text']");
        private final By btn_search=By.xpath("//input[@type='submit']");
        private final By link_firstItem=By.xpath("(//span[@role='heading' and @aria-level='3'])[3]");
        private final By btn_addToCart=By.xpath("//a[@id='atcBtn_btn_1']");
        private final By text_addToCart=By.xpath("//a[@data-test-id='cart-item-link']");
        private final By icon_addToCart=By.xpath("//i[@id='gh-cart-n']");

    public void text_SearchBox(String text) {
        WebElement textBox = wait.until(ExpectedConditions.visibilityOfElementLocated(txtBox_search));
        textBox.clear();
        textBox.sendKeys(text);
    }

    public  void btnSearch(){
    WebElement btnSearch= wait.until(ExpectedConditions.visibilityOfElementLocated(btn_search));
    btnSearch.click();
    }

    public  void firstItem()
    {
        WebElement firstItem= wait.until(ExpectedConditions.visibilityOfElementLocated(link_firstItem));
        storedItemText = firstItem.getText();
        System.out.println("Stored Item Text: " + storedItemText);
        firstItem.click();

    }

    public  void btnAddToCart()
    {
        WebElement btnAddToCart= wait.until(ExpectedConditions.visibilityOfElementLocated(btn_addToCart));
        btnAddToCart.click();
    }

    public  String txtAddToCart()
    {
        WebElement textAddCart= wait.until(ExpectedConditions.visibilityOfElementLocated(text_addToCart));
        return textAddCart.getText();
    }
    public  String iconAddToCart()
    {
        WebElement iconQuantityAddToCart= wait.until(ExpectedConditions.visibilityOfElementLocated(icon_addToCart));
       return iconQuantityAddToCart.getText();
    }






}
