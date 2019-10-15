package com.mercadopago.android.px.internal.base;

import android.support.v4.app.Fragment;

public class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {

    protected P presenter;

    @Override
    public void onStart() {
        super.onStart();
        presenter.attachView(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detachView();
    }
}