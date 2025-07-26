package ge.tbcitacademy.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class CardPage {
    public Locator allOfferButton;
    public CardPage(Page page){
        this.allOfferButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("ყველა"));
    }
}
