package prueba.test.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import prueba.test.controller.BuyController;
import java.util.Map;

public class BuyStep {
    BuyController control = new BuyController();

    @And("i want to see details of the {string}")
    public void iWantToSeeDetailsOfThe(String dressname) {
        control.lookForADress(dressname);
    }

    @And("i want to buy {string} dress, color {string} and Size {string}")
    public void iWantToBuyDressColorAndSize(String count, String color, String size) {
        control.settingDressShopping(count,color,size);
    }

    @And("i try to add to cart")
    public void iTryToAddToCart() {
        control.addToCartOnIframe();
    }

    @And("i click on Proceed to Checkout button")
    public void iClickOnProceedToCheckoutButton() {
        control.proceedToCheckOut();
    }

    @When("i verify all data on screen is the same as i saw on product page with shipping {string} and tax {string}")
    public void iVerifyAllDataOnScreenIsTheSameAsISawOnProductPageWithShippingAndTax(String ship, String tax) {
        control.validateCartInformation(ship,tax);
    }

    @And("my product is avaiblable")
    public void myProductIsAvaiblable() {
        control.validateProductInStock();
    }

    @And("i click on the Proceed to Checkout button")
    public void iClickOnTheProceedToCheckoutButton() {
        control.proceedToCheckOut2();
    }

    @And("i choose my delivery address {string}")
    public void iChooseMyDeliveryAddress(String delivery) {
        control.selectDeliveryFromAddresStep(delivery);
    }

    @And("my address information is correct")
    public void myAddressInformationIsCorrect(Map<String,String> datatable) {
        control.validateDeliveryInformation(datatable);
    }

    @And("i click on the Proceed to Checkout button on Address screen")
    public void iClickOnTheProceedToCheckoutButtonOnAddressScreen() {
        control.proceedToCheckOut3();
    }

    @And("i click the agree to the terms of service button")
    public void iClickTheAgreeToTheTermsOfServiceButton() {
        control.termOfService();
    }

    @And("i click on the Proceed to Checkout button on Shipping screen")
    public void iClickOnTheProceedToCheckoutButtonOnShippingScreen() {
        control.proceedToCheckOut4();
    }

    @And("i verify all values of my buy to be the same as the summary screen")
    public void iVerifyAllValuesOfMyBuyToBeTheSameAsTheSummaryScreen() {
        control.validateValuesOfMyBuyOnThePaymentStep();
    }

    @And("choose the pay by bank-wire option")
    public void chooseThePayByBankWireOption() {
        control.selectPaymentMethodBankWire();
    }

    @And("i should see the correct total price")
    public void iShouldSeeTheCorrectTotalPrice() {
        control.validateConfirmationTotalPrice();
    }

    @Then("i should see my order confirmation")
    public void iShouldSeeMyOrderConfirmation() {
        control.validateOrderConfirmed();
    }

    @And("i click the I confirm my order button")
    public void iClickTheIConfirmMyOrderButton() {
        control.proceedToCheckOut3();
    }
}
