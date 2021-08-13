package prueba.test.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import prueba.test.actions.BusinessActor;

public class PageLogin {

    BusinessActor actor = BusinessActor.getInstance();

    @FindBy(id = "email")
    private WebElement txtemail;
    @FindBy(id = "passwd")
    private WebElement txtpassword;
    @FindBy(id = "SubmitLogin")
    private WebElement btnsignin;
    @FindBy(xpath = "//a[@title='My Store']")
    private WebElement iconStore;

    public void insertUserName(String user) {
        actor.sendKeys(txtemail,user);
    }

    public void insertPassword(String pass) {
        actor.sendKeys(txtpassword,pass);
    }

    public void clickSignIn() {actor.click(btnsignin);}

    public void clickMyStoreIcon(){actor.click(iconStore);}
}
