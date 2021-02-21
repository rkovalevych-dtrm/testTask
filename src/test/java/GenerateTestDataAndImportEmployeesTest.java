import model.Employee;
import model.Login;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.RandStrings;

import java.io.*;
import java.util.*;

import static model.NotificationTexts.*;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.CSVUtils.insertDataIntoCSVFile;
import static utils.XLSUtils.getTemplateHeaders;
import static utils.XLSUtils.insertDataIntoXSLFile;

public class GenerateTestDataAndImportEmployeesTest extends BaseTest {
    private Employee employee1,employee2,employee3,employee4,
    employee5,employee6,employee7,employee8,employee9,employee10;

    @BeforeClass
    void preconditions() throws Exception {
        employee1 = generateLine();
        employee2 = generateLine();
        employee3 = generateLine();
        employee4 = generateLine();
        employee5 = generateLine();
        employee6 = generateLine();
        employee7 = generateLine();
        employee8 = generateLine();
        employee9 = generateLine();
        employee10 = generateLine();
    }

    @Test(description = "Generate test data")
    void generateTestData() throws Exception {
        File fileXSL = new File("C:\\testTask\\src\\test\\resources\\fileToUpload\\importEmployee.xls");
        File fileCSV = new File("C:\\testTask\\src\\test\\resources\\fileToUpload\\importEmployee.csv");

        Map<String, Integer> templateHeaders = getTemplateHeaders(fileXSL);
        insertDataIntoXSLFile(mappedModel(), templateHeaders,1, fileXSL); // xsl file
        insertDataIntoCSVFile(mappedModel(), fileCSV);  // csv file
    }

    @Test(description = "Import Employee Test")
    void importEmployeeTest(){
        File fileCSV = new File("C:\\testTask\\src\\test\\resources\\fileToUpload\\importEmployee.csv");
        insertDataIntoCSVFile(mappedModel(), fileCSV);  // csv file

        Login login = Login.builder()
                .mail("rkovalevych@yaware.com")
                .password("123456")
                .build();

        loginPage.open();
        loginPage.fill(login);
        loginPage.clickLoginButton();
        profileDetailsPage.sendInvitationByEmail();
        invitationCreationPage.clickImportEmployeesButton();
        invitationCreationPage.chooseUploadFile(fileCSV);
        invitationCreationPage.clickImportButton();
        invitationCreationPage.clickImportButton();
        assertThat(profileDetailsPage.getToastMessage()).contains(SUCCESS);

        invitationCreationPage.clickCopyDownloadLinkButton();
        invitationCreationPage.chooseMacOSLink();
        assertThat(profileDetailsPage.getToastMessage()).contains(COPIED);

        invitationCreationPage.clickDeleteInviteButton();
        invitationCreationPage.clickRemoveConfirmButton();
        assertThat(profileDetailsPage.getToastMessage()).contains(SUCCESS);

        invitationCreationPage.clickSendInviteButton();
        assertThat(profileDetailsPage.getToastMessage()).contains(USER_HAS_BEEN_INVITED_SUCCESSFULLY);
    }

    private Set<Map<String, Object>> mappedModel() {
        Set<Map<String, Object>> mappedModel = new HashSet<>();
        mappedModel.add(employee1.getMapFromModel());
        mappedModel.add(employee2.getMapFromModel());
        mappedModel.add(employee3.getMapFromModel());
        mappedModel.add(employee4.getMapFromModel());
        mappedModel.add(employee5.getMapFromModel());
        mappedModel.add(employee6.getMapFromModel());
        mappedModel.add(employee7.getMapFromModel());
        mappedModel.add(employee8.getMapFromModel());
        mappedModel.add(employee9.getMapFromModel());
        mappedModel.add(employee10.getMapFromModel());

        return mappedModel;
    }

    private Employee generateLine() {
        Employee employee = Employee.builder()
                .firstname(RandStrings.appendRandom("firstname", 15))
                .lastname(RandStrings.appendRandom("lastname", 15))
                .email(RandStrings.appendRandom("qa.time", 10) + "@yaware.com")
                .group_name(RandStrings.appendRandom("groupname", 15))
                .build();

        return employee;
    }
}
