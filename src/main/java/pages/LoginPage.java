package pages;

import io.qameta.allure.Step;
import model.Login;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class LoginPage extends Page {

    @Step("Fill login  with test data: {login}")
    public LoginPage fill(Login login){
        $("[id=email]").setValue(login.getMail());
        $("[id=password]").setValue(login.getPassword());

        return this;
    }

    @Step("Click 'Login' button")
    public LoginPage clickLoginButton(){
        $("[id=login-submit]").click();
        sleep(1000);
        return this;
    }
}
