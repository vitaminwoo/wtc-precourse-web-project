package com.wtcwebproject.wtc_precourse_web_project.lotto.domain;

public class Purchase {
    private final int LOTTO_PRICE = 1000;
    private final int purchasePrice;
    private final int purchaseAmount;

    public Purchase(int purchasePriceInput) {
        validate(purchasePriceInput);
        this.purchasePrice = purchasePriceInput;
        this.purchaseAmount = calculatePurchaseAmount(purchasePriceInput);
    }

    private void validate(int purchasePrice) {
        if (purchasePrice < LOTTO_PRICE) {
            throw new IllegalArgumentException("[ERROR] 로또 구입을 위해서는 1,000원 이상 지불해야 합니다");
        }
        if (purchasePrice % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 로또 구입 금액은 1,000원 단위여야 합니다");
        }
    }

    private int calculatePurchaseAmount(int purchasePrice) {
        return purchasePrice / LOTTO_PRICE;
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }

    public int getPurchaseAmount() {
        return purchaseAmount;
    }

}
