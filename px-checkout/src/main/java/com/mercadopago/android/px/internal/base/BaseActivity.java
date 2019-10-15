package com.mercadopago.android.px.internal.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.mercadopago.android.px.R;
import com.mercadopago.android.px.internal.di.Session;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity
    implements BaseView {

    protected P presenter;

    @Override
    protected final void onCreate(@Nullable final Bundle savedInstanceState) {
        Log.d("PRUEBA", "ON CREATE " + getClass().getSimpleName());
        super.onCreate(savedInstanceState);
        if (!Session.getInstance().isInitialized()) {
            finish();
            Log.d("PRUEBA", "NOT INITED");
        }
    }

    @Override
    protected void onPostCreate(@Nullable final Bundle savedInstanceState) {
        Log.d("PRUEBA", "ON POST CREATE " + getClass().getSimpleName());
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        Log.d("PRUEBA", "ON RESUME " + getClass().getSimpleName());
        super.onResume();
    }

    @Override
    protected void onStart() {
        presenter.attachView(this);
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
        presenter.detachView();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("PRUEBA", "ON DESTROY " + getClass().getSimpleName());
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        presenter.onBackPressed();
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