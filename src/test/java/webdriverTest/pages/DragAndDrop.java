package webdriverTest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DragAndDrop {
    public DragAndDrop(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "column-a")
    public WebElement columA;

    @FindBy(id = "column-b")
    public WebElement columB;
}
