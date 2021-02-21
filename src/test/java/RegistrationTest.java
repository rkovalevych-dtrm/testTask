import model.Login;
import model.Register;
import org.testng.annotations.Test;
import utils.RandStrings;

import static model.NotificationTexts.WELCOME_TEXT;
import static org.assertj.core.api.Assertions.assertThat;

public class RegistrationTest extends BaseTest {

    @Test(description = "Registration a new user and check settings")
    void registrationTest() {
        Register register = Register.builder()
                .firstName(RandStrings.appendRandom("firstName", 20))
                .lastName(RandStrings.appendRandom("lastName", 20))
                .mail(RandStrings.appendRandom("qa.time", 10) + "@yaware.com")
                .password(RandStrings.getRandomInts(8))
                .phone("+380" + RandStrings.getRandomInts(9))
                .build();

        loginPage.open();
        registrationCreationPage.putEnglishLanguage();
        registrationCreationPage.clickCreateAccountButton();
        registrationCreationPage.fill(register);
        registrationCreationPage.removeElementFromDom();
        registrationCreationPage.checkTermsOfService();
        registrationCreationPage.checkSubscribeFollowUps();
        registrationCreationPage.clickCreateButton();
        assertThat(configurationPage.getWelcomeMessage()).contains(WELCOME_TEXT);

        configurationPage.clickAcceptCookieLink();
        configurationPage.clickStartButton();
        configurationPage.clickNextButton();
        configurationPage.clickActivationWebCameraButton();
        configurationPage.clickNextButton();
        configurationPage.clickNextButton();
        configurationPage.clickNextButton();
        profileDetailsPage.clickLogoutButton();

        Login login = Login.builder()
                .mail(register.getMail())
                .password(register.getPassword())
                .build();

        loginPage.open();
        loginPage.fill(login);
        loginPage.clickLoginButton();
        profileDetailsPage.clickMonitoringSettingButton();
        assertThat(profileDetailsPage.isCheckedAllowPausesMonitoring()).isTrue();
    }
}
