package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class InvitationCreationPage extends Page{

    @Step("Click 'Import Employees' button")
    public void clickImportEmployeesButton(){
        $(By.xpath(".//span[contains(text(),'Import Employees')]")).click();
    }

    @Step("Click 'Import' button")
    public void clickImportButton(){
        $("[id=yaware-modal-button-0]").click();
        sleep(1000);
    }

    @Step("Click 'Upload file' button")
    public void chooseUploadFile(File file){
       $("[id=file]").uploadFile(file);
    }

    @Step("Click 'Copy download link' button")
    public void clickCopyDownloadLinkButton(){
        $("[id^=copy-link-invite-grid]").shouldBe(visible).click(); // should be modified according to ag grid by line
    }

    @Step("Choose 'Msc OS' link")
    public void chooseMacOSLink(){
        $("[id=macos-download-link]").click();
        sleep(500);
    }

    @Step("Click 'Delete Invite' button")
    public void clickDeleteInviteButton(){
        $("[id^=hidden-invite-grid]").shouldBe(visible).click(); // should be modified according to ag grid by line
    }

    @Step("Click 'Remove confirm' button")
    public void clickRemoveConfirmButton(){
        $("[id=\"yaware-modal-button-0\"]").click();
    }

    @Step("Click 'Send Invite' button")
    public void clickSendInviteButton(){
        $("[id^=send-invite-grid-]").shouldBe(visible).click(); // should be modified according to ag grid by line
    }
}
