package com.mercadopago.android.px.internal.features.express.slider;

import com.mercadopago.android.px.internal.base.BaseView;

public interface PaymentMethod {
    interface View extends BaseView {
        void disable();
    }

    interface Action {
        void onViewResumed();
    }
}