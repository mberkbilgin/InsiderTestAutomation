package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
    @FindBy(xpath = "(//a[@id='mega-menu-1'])[1]")
    private WebElement platformButton;

    @FindBy(xpath = "(//a[@id='mega-menu-1'])[2]")
    private WebElement industriesButton;

    @FindBy(xpath = "(//a[@id='mega-menu-1'])[3]")
    private WebElement useCasesButton;

    @FindBy(xpath = "(//a[@id='mega-menu-1'])[4]")
    private WebElement resourcesButton;

    @FindBy(xpath = "(//a[@id='mega-menu-1'])[5]")
    private WebElement blogButton;

    @FindBy(xpath = "(//a[@id='mega-menu-1'])[6]")
    private WebElement moreButton;

    @FindBy(xpath = "//span[.='Product Demo Hub']")
    private WebElement productDemoHubButton;

    @FindBy(xpath = "//h5[.='Careers']")
    private WebElement careersButton;

    @FindBy(xpath = "(//h1[@data-animate='fade-in'])[1]")
    private WebElement homePageMessage;
    @FindBy(xpath = "//a[@id='wt-cli-accept-all-btn']")
    private WebElement cookies;

    public void clickMoreButton(){
        moreButton.click();
    }
    public void clickCareersButton() {
        careersButton.click();
    }

    public String getMessage() {
        return homePageMessage.getText();

    }
    public void acceptCookies() {
        cookies.click();
    }
}
