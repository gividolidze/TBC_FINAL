package ge.tbcitacademy.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class OffersPage {
    public Locator checkbox,headers;
    public OffersPage(Page page) {
        this.checkbox = page.locator("//div[contains(normalize-space(), 'ტრანსპორტი')]/input[@type='checkbox']");
        this.headers = page.locator("//tbcx-pw-card//h3");
    }
}
