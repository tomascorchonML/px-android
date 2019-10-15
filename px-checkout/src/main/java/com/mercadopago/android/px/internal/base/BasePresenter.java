package com.mercadopago.android.px.internal.base;

import android.os.Bundle;
import android.support.annotation.NonNull;

public interface BasePresenter<V extends BaseView> {

    void attachView(@NonNull final V view);

    void detachView();

    void onBackPressed();

    default void onViewAttached(@NonNull final V view) {
    }

    default void onViewDetached() {
    }

    default void recoverFromBundle(@NonNull final Bundle bundle) {
    }

    default void storeInBundle(@NonNull final Bundle bundle) {
    }
}