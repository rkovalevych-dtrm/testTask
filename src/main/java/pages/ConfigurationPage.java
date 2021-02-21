package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class ConfigurationPage extends Page{

    @Step("Get welcome message")
    public String getWelcomeMessage(){
        sleep(1000);
        return $("[class=welcome-step__text]").should(visible).getText();
    }

    @Step("Click 'Accept' button")
    public ConfigurationPage clickAcceptCookieLink(){
        $("[class=yaware-cookie-notice-link]").click();
        return this;
    }

    @Step("Click 'Start' button")
    public ConfigurationPage clickStartButton(){
        $("[title=Start]").click();
        return this;
    }

    @Step("Click 'Next' button")
    public ConfigurationPage clickNextButton(){
        $("[title=Next]").click();
        return this;
    }

    @Step("Click 'Activation WebCamera' button")
    public ConfigurationPage clickActivationWebCameraButton(){
        $(By.xpath(".//span[contains(text(),'Activate webcam snapshots')]")).click();
        return this;
    }
}
