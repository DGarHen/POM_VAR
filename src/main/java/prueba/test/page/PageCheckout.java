package prueba.test.page;

import org.openqa.selenium.WebElement;
import prueba.test.actions.BusinessActor;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

public class PageCheckout {
    BusinessActor actor = BusinessActor.getInstance();

    @FindBy(id = "cart_title")
    private WebElement lblcarttitle;

    @FindBy(xpath = "//td[@class='cart_description']/p/a")
    private WebElement lblproductname;

    @FindBy(xpath = "//td[@class='cart_description']/small/a")
    private WebElement lblcolorandsize;

    @FindBy(xpath = "//span[@class='price']/span[1]")
    private WebElement lblunitprice;

    @FindBy(xpath = "//td[@class='cart_quantity text-center']/input[@type='hidden']")
    private WebElement lblquantity;

    @FindBy(xpath = "//td[@class='cart_total']/span")
    private WebElement lbltotalprice;

    @FindBy(xpath = "//td[@class='cart_avail']/span")
    private WebElement lblinstock;

    @FindBy(xpath = "//a[@class='button btn btn-default standard-checkout button-medium']")
    private WebElement btnproceed;

    @FindBy(id = "total_product")
    private WebElement lbltotalpricecolum;

    @FindBy(id = "total_shipping")
    private WebElement lblshippingcolum;

    @FindBy(id = "total_price_without_tax")
    private WebElement lbltotalwithshipping;

    @FindBy(id = "total_tax")
    private WebElement lbltax;

    @FindBy(id = "total_price_container")
    private WebElement lbltotalwithtax;

    @FindBy(xpath = "//button[@class='button btn btn-default button-medium']")
    private WebElement btnproceedaddress;

    @FindBy(xpath = "//div[@class='checker']")
    private WebElement checktermofservice;

    @FindBy(xpath = "//button[@class='button btn btn-default standard-checkout button-medium']")
    private WebElement btnproceedshipping;

    @FindBy(xpath ="//td[@class='cart_quantity text-center']/span")
    private WebElement lblpaymentquantity;

    @FindBy(className = "bankwire")
    private WebElement btnbackwiremethod;

    @FindBy(id = "amount")
    private WebElement lblconfirmationtotalpricewithtaxandshipping;

    @FindBy(xpath = "//div[@class='box']/span[@class='price']")
    private WebElement lblorderconfirmedprice;

    @FindBy(xpath = "//div[@class='box']/p[@class='cheque-indent']")
    private WebElement lblorderconfirmedmessage;

    public String previewCheckoutProductName() {
        return actor.getText(lblproductname).trim();
    }

    public int previewCheckoutAmount() {
        return Integer.parseInt(actor.getAttribute(lblquantity,"value"));
    }

    public String previewCheckoutColor() {
        return actor.getText(lblcolorandsize).split(",")[0].split(":")[1].trim();

    }

    public String previewCheckoutSize() {
        return actor.getText(lblcolorandsize).split(",")[1].split(":")[1].trim();
    }

    public Float previewCheckoutUnitPrice() {
        return Float.parseFloat(actor.getText(lblunitprice).replace("$","").trim());
    }

    public Double previewCheckoutTotalPrice() {
        return Double.parseDouble(actor.getText(lbltotalprice).replace("$","").trim());
    }

    public Double previewCheckoutTotalPriceColum() {
        return Double.parseDouble(actor.getText(lbltotalpricecolum).replace("$","").trim());
    }

    public float previewCheckoutShippingPrice() {
        return Float.parseFloat(actor.getText(lblshippingcolum).replace("$","").trim());
    }

    public Double previewCheckoutTotalWithShippingPrice() {
        return Double.parseDouble(actor.getText(lbltotalwithshipping).replace("$","").trim());
    }

    public float previewCheckoutTaxPrice() {
        return Float.parseFloat(actor.getText(lbltax).replace("$","").trim());
    }

    public Double previewCheckoutTotalWithTaxPrice() {
        return Double.parseDouble(actor.getText(lbltotalwithtax).replace("$","").trim());
    }

    public String previewCheckoutInStock() {
        return actor.getText(lblinstock);
    }

    public void clickProceedCheckoutOnSummary() {
        actor.click(btnproceed);
    }

    public void selectDelivery(String delivery){
        actor.selectFromDropDownText("//select[@id='id_address_delivery']", delivery);
    }

    public String getAddressInfo(Map.Entry<String, String> field) {
        return actor.getText(actor.findElementByXpath("//*[@id='address_delivery']/li[@class='"+field.getKey()+"']"));
    }

    public void clickProceedCheckoutOnAddress() {
        actor.moveToElement(btnproceedaddress);
        actor.click(btnproceedaddress);
    }

    public void clickTermOfServiceChecker() {
        actor.click(checktermofservice);
    }

    public void clickProceedCheckoutOnShipping(){
        actor.moveToElement(btnproceedshipping);
        actor.click(btnproceedshipping);
    }

    public String paymentCheckoutProductName() {
        return actor.getText(lblproductname);
    }

    public int paymentCheckoutAmount() {
        return Integer.parseInt(actor.getText(lblpaymentquantity));
    }

    public String paymentCheckoutColor() {
        return actor.getText(lblcolorandsize).split(",")[0].split(":")[1].trim();
    }

    public String paymentCheckoutSize() {
        return actor.getText(lblcolorandsize).split(",")[1].split(":")[1].trim();
    }

    public Float paymentCheckoutUnitPrice() {
        return Float.parseFloat(actor.getText(lblunitprice).replace("$","").trim());
    }

    public Double paymentCheckoutTotalPrice() {
        return Double.parseDouble(actor.getText(lbltotalprice).replace("$","").trim());
    }


    public Double paymentCheckoutTotalPriceColum() {
        return Double.parseDouble(actor.getText(lbltotalpricecolum).replace("$","").trim());
    }

    public Float paymentCheckoutShippingPrice() {
        return Float.parseFloat(actor.getText(lblshippingcolum).replace("$","").trim());
    }

    public Double paymentCheckoutTotalWithShippingPrice() {
        return Double.parseDouble(actor.getText(lbltotalwithtax).replace("$","").trim());
    }

    public void clickPaymentMethodBankWire() {
        actor.moveToElement(btnbackwiremethod);
        actor.click(btnbackwiremethod);
    }

    public Double getConfirmationTotalPayment(){
        return Double.parseDouble(actor.getText(lblconfirmationtotalpricewithtaxandshipping).replace("$","").trim());
    }

    public Double getConfirmedTotalPayment(){
        return Double.parseDouble(actor.getText(lblorderconfirmedprice).replace("$","").trim());
    }

    public String getConfirmedMessage(){
        return actor.getText(lblorderconfirmedmessage).trim();
    }
}
