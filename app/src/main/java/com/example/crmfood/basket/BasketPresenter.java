package com.example.crmfood.basket;

public class BasketPresenter implements BasketContract.Presenter{
    private BasketContract.View view;

    public BasketPresenter(BasketContract.View view) {
        this.view = view;
    }
}
