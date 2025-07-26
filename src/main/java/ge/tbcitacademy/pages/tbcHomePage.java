package ge.tbcitacademy.pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class tbcHomePage {
    public Locator kebabMenu, chatBotIcon, chatBotInputBar,
            currencyField, branchesButton,branchesField, cookieRejectButton,
            Personal, PupilCardButton,  offerTitles,ChatBotSearchField;
    public FrameLocator chatFrame;
    public tbcHomePage(Page page) {
        this.kebabMenu = page.locator("//tbcx-icon[text()='kebab-menu-vertical-outlined']");
        this.chatBotIcon = page.locator("//tbcx-icon[text()='chat-dots-filled']");
        this.chatBotInputBar = page.locator("//textarea[@placeholder='აკრიფეთ ტექსტი']");
        this.currencyField = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("ვალუტის კურსები"));
        this.branchesButton = page.locator("//a[@href=\"https://beta.tbcbank.ge/ka-GE/atms&branches\"]");
        this.cookieRejectButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(" უარყოფა "));
        this.Personal = page.getByText(" ჩემთვის ");
        this.PupilCardButton = page.getByText("მოსწავლის ბარათი");
        this.offerTitles = page.locator("//tbcx-pw-card//h3[contains(normalize-space(), 'ტრანსპორტი')]");
        this.branchesField=page.locator("//button[text()=\"ფილიალები\"]");
        this.chatFrame = page.frameLocator("iframe[title='Messaging window']");
        this.branchesButton = chatFrame.locator("//a[@href='https://beta.tbcbank.ge/ka-GE/atms&branches']");
        this.ChatBotSearchField = chatFrame.locator("//textarea[@placeholder='აკრიფეთ ტექსტი']");
        this.branchesField  = chatFrame.locator("//button[text()='ფილიალები']");
    }


}
