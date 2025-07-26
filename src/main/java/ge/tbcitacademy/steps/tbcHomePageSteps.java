package ge.tbcitacademy.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import ge.tbcitacademy.pages.tbcHomePage;
import io.qameta.allure.Step;

import java.util.logging.Logger;

import static org.testng.Assert.assertTrue;

public class tbcHomePageSteps {
    Page page;
    tbcHomePage tbcHomePage;
    private static final Logger logger = Logger.getLogger(tbcHomePageSteps.class.getName());

    public tbcHomePageSteps(Page page) {
        this.tbcHomePage = new tbcHomePage(page);
        this.page = page;
    }

    @Step("Click kebab menu")
    public tbcHomePageSteps clickKebabMenu() {
        tbcHomePage.kebabMenu.click();
        return this;
    }

    @Step("Click ChatBot icon")
    public tbcHomePageSteps clickChatBotIcon() {
        tbcHomePage.chatBotIcon.click();
        return this;
    }

    @Step("Wait for ChatBot input field to become visible")
    public tbcHomePageSteps waitForChatBotLoadComplete() {
        tbcHomePage.branchesField.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        return this;
    }

    @Step("Assert that the URL contains 'atms&branches'")
    public tbcHomePageSteps assertBranchesPageURLIsDisplayed() {
        String currentUrl = page.url();
        logger.info("Current URL is: " + currentUrl);
        assertTrue(currentUrl.contains("atms&branches"), "URL does not contain expected part 'atms&branches'");
        return this;
    }

    @Step("Type search query into ChatBot: {search}")
    public tbcHomePageSteps typeInSearch(String search) {
        page.waitForTimeout(2000);
        tbcHomePage.ChatBotSearchField.fill(search);
        tbcHomePage.ChatBotSearchField.press("Enter");
        return this;
    }

    @Step("Click on 'Branches' button and wait for new page")
    public tbcHomePageSteps clickBranchesButton() {
        Page newPage = page.waitForPopup(() -> tbcHomePage.branchesButton.click());
        this.page = newPage;
        return this;
    }

    @Step("Reject cookie consent popup")
    public tbcHomePageSteps clickCookieReject() {
        tbcHomePage.cookieRejectButton.click();
        return this;
    }

    @Step("Hover over 'Personal' tab")
    public tbcHomePageSteps hoverOnPersonalTab() {
        tbcHomePage.Personal.first().hover();
        return this;
    }

    @Step("Click on 'Pupil Card' link and wait for navigation")
    public tbcHomePageSteps ClickOnPupilCard() {
        page.waitForNavigation(() -> {
            tbcHomePage.PupilCardButton.click();
        });
        return this;
    }
}
