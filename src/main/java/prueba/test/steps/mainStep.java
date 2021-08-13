package prueba.test.steps;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import prueba.test.controller.MainController;

public class mainStep {
    MainController control = new MainController();

    @After
    public void TearDownTest(){control.end();}

    @Given("ingreso al portal my store")
    public void ingresoAlPortalMyStore() {
        control.init();
    }

    @When("observo la pantalla")
    public void observoLaPantalla() {

    }

    @Then("debo encontrar el mensaje {string}")
    public void deboEncontrarElMensaje(String msg) {
        control.validateMainMessage(msg);
    }

    @Given("im in the main menu as a guest user")
    public void imInMainMenuAsAGuestUser() {
        control.init();
        control.validateMainMessage("Sign in");
    }

    @And("i login as {string} with password {string} and name {string}")
    public void iLoginAsWithPassword(String user, String pass, String name) {
        control.login(user, pass, name);
    }

    @And("i click on dresses menu")
    public void iClickOnDressesMenu() {
        control.clickOnDressMenu();
    }

    @And("logout")
    public void logout() {
        control.logout();
    }
}
