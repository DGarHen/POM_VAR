package prueba.test.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import prueba.test.actions.BusinessActor;

public class PageMain {
    BusinessActor actor = BusinessActor.getInstance();

    @FindBy(className = "header_user_info")
    private WebElement singin;

    @FindBy(xpath = "//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li/a[@class='sf-with-ul'][@title='Dresses']")
    private WebElement btndressmenu;

    @FindBy(className = "account")
    private WebElement lblaccountname;

    @FindBy(className = "logout")
    private WebElement btnlogout;


    public String getMainMessage() {return actor.getText(singin);}

    public void clickSignIn() {actor.click(singin);}

    public String getLogedUser() {return actor.getText(lblaccountname);}

    public void clickDressMenu() {actor.click(btndressmenu);
    }

    public void clickLogOutButton() {
        actor.click(btnlogout);
    }

}
