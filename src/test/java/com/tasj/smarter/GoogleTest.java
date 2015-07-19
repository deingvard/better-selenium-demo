package com.tasj.smarter;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.tasj.smarter.Conditions.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by yashaka on 7/16/15.
 */
public class GoogleTest extends BaseTest {

    GooglePage page = new GooglePage(driver);

    {
        Configuration.timeout = 8;
    }

    @Test
    public void testGoogleSearch(){

        page.visit();
        page.search("Selenium");

        assertThat(listNthElementHasText(page.results, 0, "Then you want to use Selenium WebDriver"));
        assertThat(textIn(page.resultsStats, "26,200,000"));
    }

    @Test
    public void testFollowingLinkInFirstResult(){
        page.visit();
        page.search("Selenium");

        page.followResult(0);
        assertThat(titleIs("Selenium - Web Browser Automation"));
    }

    @Test
    public void testStraightforwardStyleDemoFollowAlternativeResults(){
        open("http://google.com/ncr");
        $(By.name("q")).sendKeys("Selenium", Keys.ENTER);
        $("#rhs_block a").click();
        assertThat(valueOf($(By.name("q")), "selenium element"));
    }

    /*
        Compare to Selenide version:

    public void testStraightforwardStyleDemoFollowAlternativeResults(){
        open();
        $(By.name("q")).setValue("Selenium").pressEnter();
        $("#rhs_block a").click();
        $(By.name("q")).shouldHave(value("selenium element");
    }
    */

    @Test
    public void testStraightforwardStyleDemoSearchResult(){
        open("http://google.com/ncr");
        $(By.name("q")).sendKeys("Selenium", Keys.ENTER);
        assertThat(listNthElementHasText($$(".srg .g"), 0, "Then you want to use Selenium WebDriver"));
    }

    /*
        Compare to Selenide version:

    public void testStraignForwardStyleDemoSearchResult(){
        open();
        $(By.name("q")).setValue("Selenium").pressEnter();
        $$(".srg .g").get(9).shouldHave(text("Detailed instructions for Windows users"));
    }

    */

}
