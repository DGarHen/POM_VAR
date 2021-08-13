package prueba.test.controller;

import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;
import prueba.test.actions.BusinessActor;
import prueba.test.page.PageLogin;
import prueba.test.page.PageMain;

public class MainController {

    public void init(){
        BusinessActor.getInstance().initApplication();
        BusinessActor.getInstance().openUrl();
    }


    public void validateMainMessage(String msg) {
        PageMain page = new PageMain();
        PageFactory.initElements(BusinessActor.getInstance().getDriver(), page);
        Assert.assertEquals("El mensaje obtenido no es el esperado", msg, page.getMainMessage());
    }

    public void end() {
        BusinessActor.getInstance().endApplication();
    }

    public void login(String user, String pass, String name) {
        PageMain page = new PageMain();
        PageFactory.initElements(BusinessActor.getInstance().getDriver(), page);
        page.clickSignIn();
        PageLogin pagelogin = new PageLogin();
        PageFactory.initElements(BusinessActor.getInstance().getDriver(), pagelogin);
        pagelogin.insertUserName(user);
        pagelogin.insertPassword(pass);
        pagelogin.clickSignIn();
        pagelogin.clickMyStoreIcon();
        Assert.assertEquals("el usuario no se logeo o se muestra el nombre equivocado", name, page.getLogedUser());
    }

    public void clickOnDressMenu() {
        PageMain page = new PageMain();
        PageFactory.initElements(BusinessActor.getInstance().getDriver(), page);
        page.clickDressMenu();
    }

    public void logout() {
        PageMain page = new PageMain();
        PageFactory.initElements(BusinessActor.getInstance().getDriver(), page);
        page.clickLogOutButton();
    }
}
