package application.page;

import framework.element.Label;
import framework.form.BaseForm;

import org.openqa.selenium.By;

public class SelectedGamePage extends BaseForm {

   private Label gameTitle1 = new Label(By.xpath("//div[contains(@class, 'apphub_AppName')]"),
           "gameTitleOnPage");

   private Label gameTitle2 = new Label(By.xpath("//div[contains(@class, 'game_title_area')]//h2[contains(@class, 'pageheader')]"),
           "gameTitleOnPage");

   public Label gameDiscount = new Label(By.xpath("//div[contains(@class, 'game_purchase_action')]//div[contains(@class, 'discount_pct')]"),
           "game discount");

   public Label gameOriginalPrice
           = new Label(By.xpath("//div[contains(@class, 'game_purchase_action')]//div[contains(@class, 'discount_original_price')]"),
           "game OriginalPrice");

   public Label gameFinalPrice
           = new Label(By.xpath("//div[contains(@class, 'game_purchase_action')]//div[contains(@class, 'discount_final_price')]"),
           "game FinalPrice");

   public SelectedGamePage(String formName) {
       super(formName);
   }

   public Label getGameTitle() {
       Label title;
           if(gameTitle1.isElementPresent()) {
               title = gameTitle1;
           } else {
               title = gameTitle2;
           }
       return title;
   }


}
