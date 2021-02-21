package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static pages.PageForTests.invitationCreationPage;

public class ProfileDetailsPage extends Page{

    @Step("Click 'Logout' button")
    public ProfileDetailsPage clickLogoutButton(){
        $("a[href*=logout]").click();

        return this;
    }
    @Step("Click 'Monitoring settings' button")
    public ProfileDetailsPage clickMonitoringSettingButton(){
        $("a[href*=monitoring-settings]").click();
        return this;
    }

    @Step("Click 'Allow pauses monitoring' button")
    public Boolean isCheckedAllowPausesMonitoring(){
        return $("[id=pauseMonitoringPeriods]").isSelected();
    }

    @Step("Click 'Send invitation by email' button")
    public InvitationCreationPage sendInvitationByEmail(){
        $(By.xpath(".//span[contains(text(),'Add employee')]")).shouldBe(visible).click();
        $("a[href*=invite]").shouldBe(visible).click();
        return invitationCreationPage;
    }

    @Step("Get toast message")
    public String getToastMessage(){
        return $("[class=toast-message]").should(visible).getText();
    }
}
