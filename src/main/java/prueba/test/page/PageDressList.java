package prueba.test.page;

import org.openqa.selenium.WebElement;
import prueba.test.actions.BusinessActor;

public class PageDressList {
    BusinessActor actor = BusinessActor.getInstance();

    public void lookForDressUsingScroll(String dressname) {
        WebElement dress = actor.findElementByXpath("//a[contains(@title,'"+dressname+"')]");
        actor.moveToElement(dress);
    }

    public void seeMoreDetailsOfTheProduct(String dressname) {
        WebElement dressimg = actor.findElementByXpath("//a[contains(@title,'"+dressname+"')]/ancestor::div[@class='product-image-container']");
        actor.click(dressimg);
    }

    public void swithToProductIframe() {
        int iframeid=0;
        WebElement iframe=actor.findElementByXpath("//iframe[@Class='fancybox-iframe']");
        actor.switchToIframe(iframeid, iframe);
    }

    public String getPriceFromProductoIframe() {
        WebElement lblprice=actor.findElementByXpath("//span[@id='our_price_display']");
        return actor.getText(lblprice).trim().replace("$","");
    }

    public String getDiscountPriceFromProductIfram() {
        WebElement lblprice=actor.findElementByXpath("//span[@id='old_price_display']");
        return actor.getText(lblprice).trim().replace("$","");
    }

    public void insertAmount(String count) {
        WebElement txtamount=actor.findElementByXpath("//input[@id='quantity_wanted']");
        actor.sendKeys(txtamount,count);
    }

    public void selectSize(String size) {
        actor.selectFromDropDownText("//select[@id='group_1']",size);
    }

    public void selectColor(String color) {
        WebElement btncolor = actor.findElementByXpath("//a[contains(@title,'"+color+"')]");
        actor.click(btncolor);
    }

    public void clicAddToCart() {
        WebElement btnaddtocart = actor.findElementByXpath("//button[@type='submit']");
        actor.click(btnaddtocart);
        actor.switchToMainIframe();
    }

    public void clickProceedToCheckOutButton() {
        WebElement btnproceed = actor.findElementByXpath("//a[@class='btn btn-default button button-medium']");
        actor.click(btnproceed);
    }

    public int previewAmount() {
        WebElement lblcartquantity = actor.findElementByXpath("//span[@id='layer_cart_product_quantity']");
        return Integer.parseInt(actor.getText(lblcartquantity));
    }

    public String previewColor() {
        WebElement lblcartcolor = actor.findElementByXpath("//span[@id='layer_cart_product_attributes']");
        return actor.getText(lblcartcolor).split(",")[0].trim();
    }

    public String previewSize() {
        WebElement lblcartsize = actor.findElementByXpath("//span[@id='layer_cart_product_attributes']");
        return actor.getText(lblcartsize).split(",")[1].trim();
    }

    public Double previewPrice() {
        WebElement lblcartprice = actor.findElementByXpath("//span[@id='layer_cart_product_price']");
        return Double.parseDouble(actor.getText(lblcartprice).replace("$","").trim());
    }

    public String previewProductName() {
        WebElement lblcartproductname = actor.findElementByXpath("//span[@id='layer_cart_product_title']");
        return actor.getText(lblcartproductname);
    }
}
