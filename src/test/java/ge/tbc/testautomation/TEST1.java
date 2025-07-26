package ge.tbc.testautomation;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import ge.tbcitacademy.steps.tbcHomePageSteps;
import io.qameta.allure.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("TBC Bank UI Automation")
@Feature("ChatBot and Branch Navigation")
@Owner("გივი დოლიძე")
public class TEST1 {

    private Page page;
    private tbcHomePageSteps tbcHomePageSteps;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        BrowserContext context = browser.newContext();
        page = context.newPage();
        tbcHomePageSteps = new tbcHomePageSteps(page);
    }

    @Test(priority = 1, description = "User navigates to tbcbank.ge homepage")
    @Story("Homepage Access")
    @Severity(SeverityLevel.CRITICAL)
    public void userNavigatesToHomePage() {
        page.navigate("https://tbcbank.ge/");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    @Test(priority = 2, dependsOnMethods = "userNavigatesToHomePage",
            description = "User rejects the cookie")
    @Story("Cookie Handling")
    @Severity(SeverityLevel.NORMAL)
    public void userRejectsCookies() {
        tbcHomePageSteps
                .clickCookieReject();
    }

    @Test(priority = 3, dependsOnMethods = "userRejectsCookies",
            description = "User opens ChatBot via kebab menu")
    @Story("ChatBot Access")
    @Severity(SeverityLevel.CRITICAL)
    public void userOpensChatBotViaKebabMenu() {
        tbcHomePageSteps
                .clickKebabMenu()
                .clickChatBotIcon()
                .waitForChatBotLoadComplete();
    }

    @Test(priority = 4, dependsOnMethods = "userOpensChatBotViaKebabMenu",
            description = "User types 'ფილიალები' in ChatBot search")
    @Story("ChatBot Interaction")
    @Severity(SeverityLevel.NORMAL)
    public void userTypesFirstSearchQuery() {
        tbcHomePageSteps
                .typeInSearch("ფილიალები");
    }

    @Test(priority = 5, dependsOnMethods = "userTypesFirstSearchQuery",
            description = "User types 'ფილიალის მისამართები' in ChatBot search")
    @Story("ChatBot Interaction")
    @Severity(SeverityLevel.NORMAL)
    public void userTypesSecondSearchQuery() {
        tbcHomePageSteps
                .typeInSearch("ფილიალის მისამართები");
    }

    @Test(priority = 6, dependsOnMethods = "userTypesSecondSearchQuery",
            description = "User clicks on 'Branches' button and verifies correct page is opened")
    @Story("Branch Navigation")
    @Severity(SeverityLevel.BLOCKER)
    public void userClicksBranchesButtonAndValidatesNavigation() {
        tbcHomePageSteps
                .clickBranchesButton()
                .assertBranchesPageURLIsDisplayed();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (page != null) {
            page.context().browser().close();
        }
    }
}
