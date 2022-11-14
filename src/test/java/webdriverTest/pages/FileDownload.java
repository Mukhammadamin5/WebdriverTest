package webdriverTest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FileDownload {
    public FileDownload(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "some-file.txt")
    public WebElement download;
}
