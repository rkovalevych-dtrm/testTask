package pages;

import io.qameta.allure.Step;
import model.Register;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static java.lang.String.format;

public class RegistrationCreationPage extends Page{

    private By firstName = By.cssSelector("[id=firstname]");
    private By lastName = By.cssSelector("[id=lastname]");
    private By mail = By.cssSelector("[id=registerEmail]");
    private By password = By.cssSelector("[id=pwd1]");
    private By phone = By.cssSelector("[id=phone]");

    @Step("Fill registration  with test data: {register}")
    public RegistrationCreationPage fill(Register register){
        $(firstName).setValue(register.getFirstName());
        $(lastName).setValue(register.getLastName());
        $(mail).setValue(register.getMail());
        $(password).setValue(register.getPassword());
        $(phone).setValue(register.getPhone());

        return this;
    }

    @Step("Click 'Create account' button")
    public RegistrationCreationPage clickCreateAccountButton(){
        $("a[href*=register]").click();
        return this;
    }

    @Step("Click 'Create' button")
    public RegistrationCreationPage clickCreateButton(){
        $("[id=register-account-submit]").click();
        return this;
    }

    @Step("Check subscribe me to the follow-ups and the newsletter")
    public RegistrationCreationPage checkSubscribeFollowUps(){
        $("[for=subscribeFollowups]").click();
        return this;
    }

    @Step("Check I agree with Terms Of Service and User Agreement")
    public RegistrationCreationPage checkTermsOfService(){
        $("[for=termsOfService]").click();
        return this;
    }

    @Step("Remove element with id {id} from DOM")
    public void removeElementFromDom() {
        executeJavaScript(format("document.querySelector('a[href*=\"https://timetracker.yaware.com.ua/terms-of-service/\"]')" +
                ".remove()"));
    }

    @Step("Put english language")
    public RegistrationCreationPage putEnglishLanguage(){
        $("a[data-lang=en_US]").click();
        return this;
    }
}
