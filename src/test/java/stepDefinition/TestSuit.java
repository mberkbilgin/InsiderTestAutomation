package stepDefinition;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.BrowserUtils;
import utilities.ConfigurationReader;
import utilities.Driver;

import java.io.IOException;

@Slf4j
public class TestSuit extends BaseStep {
    // Initialize SoftAssert object
    SoftAssert softAssert = new SoftAssert();

    WebDriver driver;

    @Test
    public void insiderWebPageTest() throws IOException {

        // Launch the browser and navigate to the Insider home page
        // Check if the Insider Home Page is successfully opened
        String message = "One platform for individualized, cross-channel customer experiences";
        String urlHomePage = ConfigurationReader.getProperty("url");
        String title = "#1 Leader in Individualized, Cross-Channel CX — Insider";

        softAssert.assertEquals(pages.getHOME_PAGE().getMessage(), message);
        softAssert.assertEquals(Driver.getDriver().getCurrentUrl(), urlHomePage);
        softAssert.assertEquals(Driver.getDriver().getTitle(), title);

        // Click on the "More" menu in the navigation bar and select "Careers"
        pages.getHOME_PAGE().clickMoreButton();
        pages.getHOME_PAGE().clickCareersButton();

        // Check if the Careers page is successfully opened
        String urlCareersPage = "https://useinsider.com/careers/";
        String careerPageTitle = "Insider Careers";

        softAssert.assertEquals(Driver.getDriver().getCurrentUrl(), urlCareersPage);
        softAssert.assertEquals(Driver.getDriver().getTitle(), careerPageTitle);

        // Scroll down to view the Teams Block
        BrowserUtils.scrollDown("2200");
        BrowserUtils.wait(2);

        // Check if the Teams Block is displayed and validate the text
        softAssert.assertTrue(pages.getCAREER_PAGE().teamsBlockIsDisplayed());
        softAssert.assertEquals(pages.getCAREER_PAGE().getCustomerSuccessText(), "Customer Success");
        softAssert.assertEquals(pages.getCAREER_PAGE().getSalesMessageText(), "Sales");
        softAssert.assertEquals(pages.getCAREER_PAGE().getProductEngineeringText(), "Product & Engineering");

        // Scroll down to view the Locations Block
        BrowserUtils.scrollDown("800");
        BrowserUtils.wait(2);

        // Check if the Location Block is displayed and validate the text
        softAssert.assertEquals(pages.getCAREER_PAGE().getOurLocationText(), "Our Locations");
        softAssert.assertTrue(pages.getCAREER_PAGE().isDisplayedLocation("New York"));
        softAssert.assertTrue(pages.getCAREER_PAGE().isDisplayedLocation("Sao Paulo"));
        softAssert.assertTrue(pages.getCAREER_PAGE().isDisplayedLocation("London"));
        softAssert.assertTrue(pages.getCAREER_PAGE().isDisplayedLocation("Paris"));

        // Scroll down to view the Life at Insider Block
        BrowserUtils.scrollDown("700");
        BrowserUtils.wait(2);

        // Check if the Life at Insider Block is displayed
        softAssert.assertEquals(pages.getCAREER_PAGE().getLifeAtInsiderText(), "Life at Insider");
        softAssert.assertTrue(pages.getCAREER_PAGE().lifeAtInsiderBlockIsDisplayed());

        // Scroll up to click the 'See all teams' button
        BrowserUtils.scrollUp("1000");


        // Click the 'See all teams' button
        pages.getCAREER_PAGE().clickSeeAllTeamsButton();

        // Scroll down to view the Quality Assurance section
        BrowserUtils.scrollDown("1600");

        // Click the 'Quality Assurance' section
        pages.getCAREER_PAGE().clickQualityAssuranceButton();

        // Click the 'See all QA jobs'
        pages.getQUALITY_ASSURANCE_PAGE().clickSeeAllQaJobs();
        BrowserUtils.wait(1);

        // Filter by Location - 'Istanbul, Turkey' and Department - 'Quality Assurance'
        BrowserUtils.scrollDown("600");
        pages.getOPEN_POSITIONS_PAGE().clickFilterByLocationDropDown("Istanbul, Turkey");
        pages.getOPEN_POSITIONS_PAGE().clickFilterByDepartmentDropDown("Quality Assurance");

        // Check if the job list is displayed
        pages.getOPEN_POSITIONS_PAGE().jobListIsDisplayed();
        BrowserUtils.wait(4);

        // Check if departments contain "Quality Assurance",
        // locations contain "Istanbul, Turkey", and "View Role" button is present

        // Because there is a job posting called "Software QA Tester- Insider Testinium Tech Hub (Remote)",
        // I didn't check whether positions contain "Quality Assurance"
        softAssert.assertTrue(pages.getOPEN_POSITIONS_PAGE().verifyElementDepartmentText("Quality Assurance"));
        softAssert.assertTrue(pages.getOPEN_POSITIONS_PAGE().verifyElementLocationText("Istanbul, Turkey"));
        pages.getOPEN_POSITIONS_PAGE().viewRoleButtonIsDisplayed();

        // Click the "View Role" button and switch to the Lever Application form page
        pages.getOPEN_POSITIONS_PAGE().clickViewRoleButton();
        BrowserUtils.switchToTab("Insider. - Senior Software Quality Assurance Engineer - Remote");

        // Check if this action redirects to the Lever Application form page
        softAssert.assertEquals(Driver.getDriver().getCurrentUrl(), "https://jobs.lever.co/useinsider/78ddbec0-16bf-4eab-b5a6-04facb993ddc");
        softAssert.assertEquals(Driver.getDriver().getTitle(), "Insider. - Senior Software Quality Assurance Engineer - Remote");
        softAssert.assertTrue(pages.getLEVER_FORM_PAGE().definitionBlockIsDisplayed());

        // Perform all the soft assertions
        softAssert.assertAll();

    }

}
