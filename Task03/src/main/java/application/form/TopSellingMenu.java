package application.form;

import application.model.Game;
import framework.element.Button;
import framework.element.MultipleElement;
import framework.element.SubElement;
import framework.form.BaseForm;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TopSellingMenu extends BaseForm {

    private By gameNameLocator = By.xpath("../..//div[contains(@class,'tab_item_name')]");

    private By originalPriceLocator = By.xpath("../..//div[contains(@class,'discount_original_price')]");

    private By finalPriceLocator = By.xpath("../..//div[contains(@class,'discount_final_price')]");

    private By multipleElementLocator = By.xpath("//div[@id='TopSellersRows']//div[contains(@class, 'discount_pct')]");

    public Button topSellingButton
            = new Button(By.xpath("//div[contains(@id,'tab_select_TopSellers')]"), "Top selling menu");

    public TopSellingMenu(String formName) {
        super(formName);
    }

    public SubElement getWantedDiscountSubLable() {

        MultipleElement multipleElement = new MultipleElement(multipleElementLocator, "Discount MultipleElement");

        List<String> discounts = new ArrayList<>();
        for (int i = 0; i < multipleElement.getElements().size(); i++) {
            discounts.add(multipleElement.getElementTextByIndex(i));
        }
        String maxDiscount = Collections.max(discounts);

        SubElement wantedDiscount = null;
        for (int i = 0; i < discounts.size(); i++) {
            if (multipleElement.getElementTextByIndex(i).equals(maxDiscount)) {
                wantedDiscount = new SubElement(multipleElement.getElementByIndex(i), "WantedDiscountElement");
            }
        }
        return wantedDiscount;
    }

    public Game getWantedGame(SubElement subElement) {

        Game game = new Game();
        game.setDiscount(subElement.getSubElementText());

        SubElement wantedName = subElement.getSubElement(gameNameLocator, "gameName");
        String wantedNameText = wantedName.getSubElementText();
        game.setName(wantedNameText);

        SubElement wantedOriginalPrice = subElement.getSubElement(originalPriceLocator, "gameOriginalPrice");
        String wantedOriginalPriceText = wantedOriginalPrice.getSubElementText();
        game.setOriginalPrice(wantedOriginalPriceText);

        SubElement wantedFinalPrice = subElement.getSubElement(finalPriceLocator, "gameFinalPrice");
        String wantedFinalPriceText = wantedFinalPrice.getSubElementText();
        game.setFinalPrice(wantedFinalPriceText);

        return game;
    }

}



