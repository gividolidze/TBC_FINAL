package ge.tbcitacademy.steps;

import com.microsoft.playwright.Page;
import ge.tbcitacademy.pages.CardPage;
import io.qameta.allure.Step;

public class CardSteps {
    Page page;
    CardPage cardPage;

    public CardSteps(Page page) {
        this.page = page;
        this.cardPage = new CardPage(page);
    }
    @Step("Click 'All Offers' button and wait for navigation")
    public CardSteps clickAllOfferButton() {
        page.waitForNavigation(() -> {
            cardPage.allOfferButton.click();
        });
        return this;
    }
}
