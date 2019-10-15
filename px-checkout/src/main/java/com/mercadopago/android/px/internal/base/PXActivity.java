package com.mercadopago.android.px.internal.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.mercadopago.android.px.R;

public abstract class PXActivity<P extends BasePresenter> extends AppCompatActivity implements MvpView {

    protected static final String BUNDLE_CREATED = "bundle_created";

    protected P presenter;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        Log.d("PRUEBA", "ON CREATE " + getClass().getSimpleName());
        super.onCreate(savedInstanceState);
        new LoggingExceptionHandler(this);
    }

    @Override
    protected void onResume() {
        Log.d("PRUEBA", "ON RESUME " + getClass().getSimpleName());
        super.onResume();
    }

    @Override
    protected void onStart() {
        Log.d("PRUEBA", "ON START " + getClass().getSimpleName());
        super.onStart();
    }

    @Override
    protected void onPause() {
        Log.d("PRUEBA", "ON PAUSE " + getClass().getSimpleName());
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("PRUEBA", "ON STOP " + getClass().getSimpleName());
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("PRUEBA", "ON DESTROY " + getClass().getSimpleName());
        super.onDestroy();
    }

    @CallSuper
    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(BUNDLE_CREATED, true);
    }

    @Override
    public void onBackPressed() {
        if (presenter != null) {
            presenter.tracker.trackBack();
        }
        super.onBackPressed();
    }

    public void overrideTransitionIn() {
        overridePendingTransition(R.anim.px_slide_right_to_left_in, R.anim.px_slide_right_to_left_out);
    }

    public void overrideTransitionOut() {
        overridePendingTransition(R.anim.px_slide_left_to_right_in, R.anim.px_slide_left_to_right_out);
    }

    public void overrideTransitionFadeInFadeOut() {
        overridePendingTransition(R.anim.px_fade_in_seamless, R.anim.px_fade_out_seamless);
    }

    public void overrideTransitionWithNoAnimation() {
        overridePendingTransition(R.anim.px_no_change_animation, R.anim.px_no_change_animation);
    }
}