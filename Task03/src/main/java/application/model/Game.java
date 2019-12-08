package application.model;

import java.util.Objects;

public class Game {

    private String name;
    private String discount;
    private String originalPrice;
    private String finalPrice;

    public Game() {
    }

    public Game(String name, String discount, String originalPrice, String finalPrice) {
        this.name = name;
        this.discount = discount;
        this.originalPrice = originalPrice;
        this.finalPrice = finalPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(String finalPrice) {
        this.finalPrice = finalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Game game = (Game) o;
        return name.equals(game.name)
                && discount.equals(game.discount)
                && originalPrice.equals(game.originalPrice)
                && finalPrice.equals(game.finalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, discount, originalPrice, finalPrice);
    }

    @Override
    public String toString() {
        return "Game{"
                + "name='" + name + '\''
                + ", discount='" + discount + '\''
                + ", originalPrice='" + originalPrice + '\''
                + ", finalPrice='" + finalPrice + '\''
                + '}';
    }
}
