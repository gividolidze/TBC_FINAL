package ge.tbcitacademy.steps;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import ge.tbcitacademy.pages.OffersPage;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.testng.Assert.assertTrue;

public class OffersSteps {
    private List<String> allHeaders = new ArrayList<>();
    private List<String> filteredHeaders = new ArrayList<>();
    Page page;
    OffersPage offersPage;
    private static final Logger logger = Logger.getLogger(OffersSteps.class.getName());

    public OffersSteps(Page page) {
        this.page = page;
        this.offersPage = new OffersPage(page);
    }

    @Step("Click on 'Transporti' category checkbox and wait for navigation")
    public OffersSteps clickTransportCheckbox() {
        page.waitForNavigation(() -> {
            offersPage.checkbox.click();
        });
        return this;
    }

    @Step("Fetch all offer headers from the page")
    public OffersSteps fetchAllHeaders() {
        page.waitForSelector("//tbcx-pw-card//h3", new Page.WaitForSelectorOptions()
                .setState(WaitForSelectorState.VISIBLE));
        int count = offersPage.headers.count();

        allHeaders.clear();
        for (int i = 0; i < count; i++) {
            allHeaders.add(offersPage.headers.nth(i).innerText());
        }
        return this;
    }

    @Step("Filter headers that contain the word 'ტრანსპორტი'")
    public OffersSteps filterHeadersContainingTransport() {
        filteredHeaders = allHeaders.stream()
                .filter(text -> text.contains("ტრანსპორტი"))
                .collect(Collectors.toList());
        return this;
    }

    @Step("Assert that all headers contain the word 'ტრანსპორტი'")
    public OffersSteps assertAllContainTransport() {
        assertTrue(filteredHeaders.size() == allHeaders.size());
        return this;
    }

    public List<String> getAllHeaders() {
        return allHeaders;
    }

    public List<String> getFilteredHeaders() {
        return filteredHeaders;
    }

    @Step("Print all filtered headers to log")
    public OffersSteps printFilteredHeaders() {
        filteredHeaders.forEach(text -> logger.info("Filtered header: " + text));
        return this;
    }
}
