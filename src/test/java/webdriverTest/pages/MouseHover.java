package webdriverTest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MouseHover {
    public MouseHover(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//img)[2]")
    public WebElement image1;

    @FindBy(xpath = "(//img)[3]")
    public WebElement image2;

    @FindBy(xpath = "(//img)[4]")
    public WebElement image3;

    @FindBy(xpath = "//h5[text()='name: user1']")
    public WebElement user1;

    @FindBy(xpath = "//h5[text()='name: user2']")
    public WebElement user2;

    @FindBy(xpath = "//h5[text()='name: user3']")
    public WebElement user3;
}
