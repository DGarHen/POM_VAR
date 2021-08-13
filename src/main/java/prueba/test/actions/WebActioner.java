package prueba.test.actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import prueba.test.util.ManagementPropertiesFiles;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class WebActioner {

    static final int ELEMENT_WAIT_TIME=5;
    static final int PAGE_WAIT_TIME=60;
    private WebDriver driver;

    public void initApplication(){
        WebDriver driver = null;
        String enviroment = ManagementPropertiesFiles.getFieldProperties("enviroment");

        try{
            driver=getDriverBrowser(enviroment);
            driver.manage().timeouts().implicitlyWait(ELEMENT_WAIT_TIME,  TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(PAGE_WAIT_TIME, TimeUnit.SECONDS);
        }catch (Exception e){
            System.out.println(e);
        }
        this.setDriver(driver);
    }

    public void openUrl() {
        String URL = ManagementPropertiesFiles.getFieldProperties("URL");
        try {
            driver.get(URL);
            System.out.println("page open");
        } catch (Exception e) {
            System.out.println("error to open page");
        }
    }

    private void setDriver(WebDriver driver){this.driver=driver;}

    public WebDriver getDriver(){return driver;}

    public WebDriver getDriverBrowser(String enviroment) throws Exception {
        WebDriver driver;
        ChromeOptions options;
        HashMap<String, Object> chromePrefs;
        switch(enviroment) {
            case "chrome incognito":
                WebDriverManager.chromedriver().setup();
                String[] arglist = new String[]{"--start-maximized", "--incognito"};
                options = new ChromeOptions();
                options.addArguments(arglist);
                chromePrefs = new HashMap<String, Object>();
                options.setExperimentalOption("prefs", chromePrefs);
                options.addArguments("--test-type");
                driver = new ChromeDriver(options);
                driver.manage().deleteAllCookies();
                break;
            case "chrome":
                WebDriverManager.chromedriver().setup();
                String[] arglist1 = new String[]{"--start-maximized"};
                options = new ChromeOptions();
                options.addArguments(arglist1);
                chromePrefs = new HashMap<String, Object>();
                options.setExperimentalOption("prefs", chromePrefs);
                options.addArguments("--test-type");
                driver = new ChromeDriver(options);
                break;
            default:
                throw new Exception("Error al iniciar el driver del navegador: " + enviroment);
        }

        return driver;
    }

    public String getText(WebElement object){
        try{
            waitUntilPresents(object);
            return object.getText();
        }catch (NullPointerException e){
            Assert.fail("El elemento no fue encontrado error nullPointer");
            return null;
        }catch (Exception e){
            Assert.fail("El elemento:"+object+" genero un error al intentar obtener su contenido");
            return null;
        }
    }

    public void sendKeys(WebElement object, String text) {
        try{
            waitUntilPresents(object);
            object.clear();
            object.sendKeys(text);
        }catch (NullPointerException e){
            Assert.fail("El elemento no fue encontrado error nullPointer");
        }catch (Exception e){
            Assert.fail("El elemento:"+object+" genero un error al intentar escribir en el");
        }
    }

    public void click(WebElement object) {
        try{
            waitUntilPresents(object);
            object.click();
        }catch (NullPointerException e){
            Assert.fail("El elemento no fue encontrado error nullPointer");
        }catch (Exception e){
            Assert.fail("El elemento:"+object+" genero un error al intentar hacer click");
        }
    }

    public void moveToElement(WebElement object) {
        try{
            Actions actions = new Actions(driver);
            actions.moveToElement(object).perform();
        }catch (NullPointerException e){
            Assert.fail("El elemento no fue encontrado error nullPointer");
        }catch (Exception e){
            Assert.fail("El elemento:"+object+" genero un error al intentar poner el mouse sobre el");
        }
    }

    private boolean waitUntilPresents(WebElement object) {
        try{
            WebDriverWait w = new WebDriverWait(driver,ELEMENT_WAIT_TIME);
            w.until(ExpectedConditions.visibilityOf(object));
            return true;
        }
        catch (NullPointerException e){
            Assert.fail("El elemento no fue encontrado error nullPointer");
            return false;
        }catch (Exception e){
            return false;
        }
    }

    public void endApplication() {
        System.out.println("Escenario Terminado");
        driver.quit();
    }

    public boolean switchToIframe(int iframeid, WebElement iframe) {
        try{
            waitUntilPresents(iframe);
            driver.switchTo().frame(iframeid);
            return true;
        }catch (NullPointerException e){
            Assert.fail("El iframe no fue encontrado error nullPointer");
            return false;
        }catch (Exception e){
            return false;
        }
    }

    public boolean switchToMainIframe(){
        try{
            driver.switchTo().defaultContent();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public WebElement findElementByXpath(String xpath){
        try {
            return driver.findElement(By.xpath(xpath));
        }catch (Exception e){
            Assert.fail("Elemento para el xpath: "+xpath+" no encontrado");
            return null;
        }
    }

    public void selectFromDropDownText(String xpath, String option) {
        Select selector = new Select(findElementByXpath(xpath));
        selector.selectByVisibleText(option);
    }

    public String getAttribute(WebElement object, String atribute) {
        waitUntilPresents(object);
        return object.getAttribute(atribute);
    }
}
