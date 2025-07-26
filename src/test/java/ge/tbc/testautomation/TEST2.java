package ge.tbc.testautomation;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import ge.tbcitacademy.steps.CardSteps;
import ge.tbcitacademy.steps.OffersSteps;
import ge.tbcitacademy.steps.tbcHomePageSteps;
import io.qameta.allure.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("TBC Bank UI Automation")
@Feature("Pupil Card Offers Filtering")
@Owner("გივი დოლიძე")
public class TEST2 {

    private Page page;
    private tbcHomePageSteps tbcHomePageSteps;
    private OffersSteps offersSteps;
    private CardSteps cardSteps;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        BrowserContext context = browser.newContext();
        page = context.newPage();
        tbcHomePageSteps = new tbcHomePageSteps(page);
        offersSteps = new OffersSteps(page);
        cardSteps = new CardSteps(page);
    }

    @Test(priority = 1, description = "User opens TBC Bank homepage")
    @Story("Homepage Access")
    @Severity(SeverityLevel.CRITICAL)
    public void userNavigatesToTbcHomePage() {
        page.navigate("https://tbcbank.ge/");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    @Test(priority = 2, dependsOnMethods = "userNavigatesToTbcHomePage",
            description = "User rejects cookie")
    @Story("Cookie Handling")
    @Severity(SeverityLevel.NORMAL)
    public void userRejectsCookieConsent() {
        tbcHomePageSteps.clickCookieReject();
    }

    @Test(priority = 3, dependsOnMethods = "userRejectsCookieConsent",
            description = "User navigates to Pupil Card page via Personal tab")
    @Story("Navigation through Personal Tab")
    @Severity(SeverityLevel.CRITICAL)
    public void userNavigatesToPupilCardPageFromPersonalTab() {
        tbcHomePageSteps
                .hoverOnPersonalTab()
                .ClickOnPupilCard();
    }

    @Test(priority = 5, dependsOnMethods = "userNavigatesToPupilCardPageFromPersonalTab",
            description = "User clicks 'All Offers' button on Pupil Card page")
    @Story("Offers Navigation")
    @Severity(SeverityLevel.NORMAL)
    public void userClicksAllOffersButtonOnCardPage() {
        cardSteps.clickAllOfferButton();
    }

    @Test(priority = 6, dependsOnMethods = "userClicksAllOffersButtonOnCardPage",
            description = "User selects 'Transport' category checkbox and fetches all offer headers")
    @Story("Offer Filtering")
    @Severity(SeverityLevel.NORMAL)
    public void userSelectsTransportCategoryCheckboxAndFetchesHeaders() {
        offersSteps
                .clickTransportCheckbox()
                .fetchAllHeaders();
    }

    @Test(priority = 8, dependsOnMethods = "userSelectsTransportCategoryCheckboxAndFetchesHeaders",
            description = "System filters and asserts that all fetched headers contain 'Transport'")
    @Story("Validation of Offer Titles")
    @Severity(SeverityLevel.BLOCKER)
    public void systemFiltersHeadersAndValidatesTheyContainTransporti() {
        offersSteps
                .filterHeadersContainingTransport()
                .assertAllContainTransport()
                .printFilteredHeaders();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (page != null) {
            page.context().browser().close();
        }
    }
}
