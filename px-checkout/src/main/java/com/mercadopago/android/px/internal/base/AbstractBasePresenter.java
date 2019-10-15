package com.mercadopago.android.px.internal.base;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mercadopago.android.px.tracking.internal.TrackingContract;
import com.mercadopago.android.px.tracking.internal.events.AbortEvent;
import com.mercadopago.android.px.tracking.internal.events.BackEvent;
import com.mercadopago.android.px.tracking.internal.views.ViewTracker;
import java.lang.ref.WeakReference;

/**
 * Base class for all <code>BasePresenter</code> implementations.
 */

@SuppressWarnings("AbstractClassWithoutAbstractMethods")
public abstract class AbstractBasePresenter<V extends BaseView> implements BasePresenter<V> {

    @Nullable private transient WeakReference<V> view;
    @Nullable /* default */ transient ViewTracker viewTracker;

    protected final transient TrackingContract tracker = new TrackingContract() {

        @Override
        public void trackAbort() {
            if (viewTracker != null) {
                new AbortEvent(viewTracker).track();
            }
        }

        @Override
        public void trackBack() {
            if (viewTracker != null) {
                new BackEvent(viewTracker).track();
            }
        }
    };

    @CallSuper
    @Override
    public void onBackPressed() {
        tracker.trackBack();
    }

    protected final void setCurrentViewTracker(@NonNull final ViewTracker viewTracker) {
        this.viewTracker = viewTracker;
        viewTracker.track();
    }

    @Override
    public final void attachView(@NonNull final V view) {
        this.view = new WeakReference<>(view);
        onViewAttached(view);
    }

    @Override
    public final void detachView() {
        if (view != null) {
            view.clear();
            view = null;
        }
        onViewDetached();
    }

    protected boolean isViewAttached() {
        return view != null && view.get() != null;
    }

    @NonNull
    protected V getView() {
        if (!isViewAttached()) {
            throw new IllegalStateException("view not attached");
        } else {
            return view.get();
        }
    }
}