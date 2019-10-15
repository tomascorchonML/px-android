package com.mercadopago.android.px.internal.features.bank_deal_detail;

import com.mercadopago.android.px.internal.base.AbstractBasePresenter;
import com.mercadopago.android.px.tracking.internal.views.BankDealsDetailViewTracker;
import com.squareup.picasso.Callback;

/* default */ class BankDealDetailPresenter extends AbstractBasePresenter<BankDealDetail.View> implements Callback {

    /* default */ BankDealDetailPresenter() {
        setCurrentViewTracker(new BankDealsDetailViewTracker());
    }

    @Override
    public void onSuccess() {
        getView().hideLogoName();
    }

    @Override
    public void onError() {
        getView().hideLogo();
    }
}