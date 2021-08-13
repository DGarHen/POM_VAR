package prueba.test.controller;
import com.sun.jna.platform.win32.OaIdl;
import cucumber.api.java.an.E;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;
import prueba.test.actions.BusinessActor;
import prueba.test.page.PageCheckout;
import prueba.test.page.PageDressList;

import java.util.Map;

public class BuyController {

    public void lookForADress(String dressname) {
        PageDressList pagelist= new PageDressList();
        PageFactory.initElements(BusinessActor.getInstance().getDriver(), pagelist);
        pagelist.lookForDressUsingScroll(dressname);
        pagelist.seeMoreDetailsOfTheProduct(dressname);
        BusinessActor.getInstance().setProductName(dressname);
    }

    public void settingDressShopping(String count, String color, String size) {
        PageDressList pageList= new PageDressList();
        PageFactory.initElements(BusinessActor.getInstance().getDriver(), pageList);
        pageList.swithToProductIframe();
        pageList.insertAmount(count);
        pageList.selectSize(size);
        pageList.selectColor(color);
        BusinessActor.setPrice(pageList.getPriceFromProductoIframe());
        BusinessActor.setRealPrice(pageList.getDiscountPriceFromProductIfram());
        BusinessActor.setAmount(count);
        BusinessActor.setColor(color);
        BusinessActor.setSize(size);

    }

    public void addToCartOnIframe() {
        PageDressList pageList= new PageDressList();
        PageFactory.initElements(BusinessActor.getInstance().getDriver(), pageList);
        pageList.clicAddToCart();
    }

    public void proceedToCheckOut(){
        PageDressList pageList= new PageDressList();
        PageFactory.initElements(BusinessActor.getInstance().getDriver(), pageList);
        Assert.assertEquals("valores de cantidad no esperados", BusinessActor.getInstance().getAmount(), pageList.previewAmount());
        Assert.assertEquals("valores de color no esperados", BusinessActor.getInstance().getColor(), pageList.previewColor());
        Assert.assertEquals("valores de talla no esperados", BusinessActor.getInstance().getSize(), pageList.previewSize());
        Double actualprice = calculateTotalPrice(BusinessActor.getInstance().getAmount(), BusinessActor.getInstance().getPrice());
        Assert.assertEquals("valores de precio no esperados", actualprice, pageList.previewPrice());
        Assert.assertEquals("valores nombre producto no esperados", BusinessActor.getInstance().getProductName(), pageList.previewProductName());
        pageList.clickProceedToCheckOutButton();
    }

    private Double calculateTotalPrice(int amount, Float price) {
        float input = amount * price;
        return Math.round(input * 100.0) / 100.0;
    }

    public void validateCartInformation(String ship, String tax) {
        PageCheckout page= new PageCheckout();
        PageFactory.initElements(BusinessActor.getInstance().getDriver(), page);
        BusinessActor.setShippingPrice(ship);
        BusinessActor.setTaxValue(tax);
        Assert.assertEquals("valores nombre producto no esperados", BusinessActor.getInstance().getProductName(), page.previewCheckoutProductName());
        Assert.assertEquals("valores de cantidad no esperados", BusinessActor.getInstance().getAmount(), page.previewCheckoutAmount());
        Assert.assertEquals("valores de color no esperados", BusinessActor.getInstance().getColor(), page.previewCheckoutColor());
        Assert.assertEquals("valores de talla no esperados", BusinessActor.getInstance().getSize(), page.previewCheckoutSize());
        Double actualprice;
        try{
            actualprice = calculateTotalPrice(BusinessActor.getInstance().getAmount(), BusinessActor.getInstance().getPrice());
        }catch (Exception e){
            actualprice=null;
            Assert.fail("los valores para cantidad o precio que debia recordar el actor no se encuentran o no son validos");
        }
        Assert.assertEquals("valores de precio unitario no esperados", BusinessActor.getInstance().getPrice(), page.previewCheckoutUnitPrice());
        Assert.assertEquals("valores de precio total no esperados", actualprice, page.previewCheckoutTotalPrice());
        Assert.assertEquals("valores de precio total en columna no esperados", actualprice, page.previewCheckoutTotalPriceColum());
        Double pricewithshipping;
        Double totalwithtax;
        try {
            pricewithshipping=calculateTotalwithShipping(Double.parseDouble(ship),actualprice);
            totalwithtax=calculateTotalWithTaxes(Double.parseDouble(tax),pricewithshipping);
        }catch (Exception e){
            pricewithshipping=null;
            totalwithtax=null;
            Assert.fail("Los datos enviados por parametros en el feature no son validos");
        }
        Assert.assertEquals("valores de precio envio no esperados", Double.parseDouble(ship), page.previewCheckoutShippingPrice(),0.1d);
        Assert.assertEquals("valores de precio impuestos no esperados", Double.parseDouble(tax), page.previewCheckoutTaxPrice(),0.1d);
        Assert.assertEquals("valores de precio total con envio no esperados", pricewithshipping, page.previewCheckoutTotalWithShippingPrice());
        Assert.assertEquals("valores de precio total con impuestos no esperados", totalwithtax, page.previewCheckoutTotalWithTaxPrice());
    }

    private Double calculateTotalWithTaxes(double totalwithtax, Double pricewithshipping) {
        double input = totalwithtax + pricewithshipping;
        return Math.round(input * 100.0) / 100.0;
    }

    private Double calculateTotalwithShipping(Double shipping, Double actualprice) {
        double input = shipping + actualprice;
        return Math.round(input * 100.0) / 100.0;
    }

    public void validateProductInStock() {
        PageCheckout page= new PageCheckout();
        PageFactory.initElements(BusinessActor.getInstance().getDriver(), page);
        Assert.assertEquals("valores de precio total con impuestos no esperados", "In stock", page.previewCheckoutInStock());
    }

    public void proceedToCheckOut2() {
        PageCheckout page= new PageCheckout();
        PageFactory.initElements(BusinessActor.getInstance().getDriver(), page);
        page.clickProceedCheckoutOnSummary();
    }

    public void selectDeliveryFromAddresStep(String delivery) {
        PageCheckout page= new PageCheckout();
        PageFactory.initElements(BusinessActor.getInstance().getDriver(), page);
        page.selectDelivery(delivery);
    }

    public void validateDeliveryInformation(Map<String, String> addressinfo) {
        PageCheckout page= new PageCheckout();
        PageFactory.initElements(BusinessActor.getInstance().getDriver(), page);
        for (Map.Entry<String, String> field : addressinfo.entrySet()) {
            Assert.assertEquals("El campo: "+field.getKey()+" tiene información no esperada",field.getValue(),page.getAddressInfo(field));
        }
    }

    public void proceedToCheckOut3() {
        PageCheckout page= new PageCheckout();
        PageFactory.initElements(BusinessActor.getInstance().getDriver(), page);
        page.clickProceedCheckoutOnAddress();
    }

    public void termOfService() {
        PageCheckout page= new PageCheckout();
        PageFactory.initElements(BusinessActor.getInstance().getDriver(), page);
        page.clickTermOfServiceChecker();
    }

    public void proceedToCheckOut4() {
        PageCheckout page= new PageCheckout();
        PageFactory.initElements(BusinessActor.getInstance().getDriver(), page);
        page.clickProceedCheckoutOnShipping();
    }

    public void validateValuesOfMyBuyOnThePaymentStep() {
        PageCheckout page= new PageCheckout();
        PageFactory.initElements(BusinessActor.getInstance().getDriver(), page);
        Assert.assertEquals("valores nombre producto no esperados", BusinessActor.getInstance().getProductName(), page.paymentCheckoutProductName());
        Assert.assertEquals("valores de cantidad no esperados", BusinessActor.getInstance().getAmount(), page.paymentCheckoutAmount());
        Assert.assertEquals("valores de color no esperados", BusinessActor.getInstance().getColor(), page.paymentCheckoutColor());
        Assert.assertEquals("valores de talla no esperados", BusinessActor.getInstance().getSize(), page.paymentCheckoutSize());
        Double actualprice = calculateTotalPrice(BusinessActor.getInstance().getAmount(), BusinessActor.getInstance().getPrice());
        Assert.assertEquals("valores de precio unitario no esperados", BusinessActor.getInstance().getPrice(), page.paymentCheckoutUnitPrice());
        Assert.assertEquals("valores de precio total no esperados", actualprice, page.paymentCheckoutTotalPrice());
        Assert.assertEquals("valores de precio total en columna no esperados", actualprice, page.paymentCheckoutTotalPriceColum());
        Assert.assertEquals("valores de precio envio no esperados", BusinessActor.getInstance().getShippingPrice(), page.paymentCheckoutShippingPrice(),0.1f);
        //Assert.assertEquals("valores de precio impuestos no esperados",BusinessActor.getInstance().getTaxValue(), page.paymentCheckoutTaxPrice(),0.1f);
        Double pricewithshipping=calculateTotalwithShipping(BusinessActor.getInstance().getShippingPrice().doubleValue(),actualprice);
        Assert.assertEquals("valores de precio total con envio no esperados", pricewithshipping, page.paymentCheckoutTotalWithShippingPrice());
        //Assert.assertEquals("valores de precio total con impuestos no esperados", totalwithtax, page.paymentCheckoutTotalWithTaxPrice());
    }

    public void selectPaymentMethodBankWire() {
        PageCheckout page= new PageCheckout();
        PageFactory.initElements(BusinessActor.getInstance().getDriver(), page);
        page.clickPaymentMethodBankWire();
    }

    public void validateConfirmationTotalPrice() {
        PageCheckout page= new PageCheckout();
        PageFactory.initElements(BusinessActor.getInstance().getDriver(), page);
        Double actualprice = calculateTotalPrice(BusinessActor.getInstance().getAmount(), BusinessActor.getInstance().getPrice());
        Double pricewithshipping=calculateTotalwithShipping(BusinessActor.getInstance().getShippingPrice().doubleValue(),actualprice);
        Double totalwithtax=calculateTotalWithTaxes(BusinessActor.getInstance().getTaxValue().doubleValue(),pricewithshipping);
        Assert.assertEquals("El valor total no es igual al esperado",totalwithtax,page.getConfirmationTotalPayment());
    }

    public void validateOrderConfirmed() {
        PageCheckout page= new PageCheckout();
        PageFactory.initElements(BusinessActor.getInstance().getDriver(), page);
        Assert.assertEquals("la pagina de confirmación no muestra el mensaje correcto","Your order on My Store is complete.",page.getConfirmedMessage());
        Double actualprice = calculateTotalPrice(BusinessActor.getInstance().getAmount(), BusinessActor.getInstance().getPrice());
        Double pricewithshipping=calculateTotalwithShipping(BusinessActor.getInstance().getShippingPrice().doubleValue(),actualprice);
        Double totalwithtax=calculateTotalWithTaxes(BusinessActor.getInstance().getTaxValue().doubleValue(),pricewithshipping);
        Assert.assertEquals("El valor total no es igual al esperado",totalwithtax,page.getConfirmedTotalPayment());
    }
}
