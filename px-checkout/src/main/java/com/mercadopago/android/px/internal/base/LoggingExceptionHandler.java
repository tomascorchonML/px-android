package com.mercadopago.android.px.internal.base;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import com.mercadopago.android.px.internal.di.Session;

public class LoggingExceptionHandler implements Thread.UncaughtExceptionHandler {

    @NonNull private final Thread.UncaughtExceptionHandler rootHandler;
    @NonNull private final Activity activity;
    private boolean isFinishing;

    /* default */ LoggingExceptionHandler(@NonNull final Activity activity) {
        // we should store the current exception handler -- to invoke it for all not handled exceptions ...
        rootHandler = Thread.getDefaultUncaughtExceptionHandler();
        // we replace the exception handler now with us -- we will properly dispatch the exceptions ...
        Thread.currentThread().setUncaughtExceptionHandler(this);
        this.activity = activity;
        finishIfNeeded();
    }

    private void finishIfNeeded() {
        final boolean notInitialized = !Session.getInstance().isInitialized();
        if (notInitialized) {
            Log.d("PRUEBA", "NOT INITED");
            throw new OnCreateException();
        }
    }

    @Override
    public void uncaughtException(@NonNull final Thread thread, @NonNull final Throwable ex) {
        if (!isFinishing) {
            Log.d("PRUEBA", "IS NOT FINISHING");
            handleOnCreateException(thread, ex);
            Thread.currentThread().setUncaughtExceptionHandler(rootHandler);
        } else {
            Log.d("PRUEBA", "IS FINISHING");
        }
    }

    private void handleOnCreateException(@NonNull final Thread thread, @NonNull final Throwable ex) {
        Log.d("PRUEBA", "Handling exception");
        if (isFinishException(ex)) {
            Log.d("PRUEBA", "HOLA MUNDO");
            Log.d("PRUEBA", activity.getClass().getSimpleName());
            activity.finish();
            Log.d("PRUEBA", String.valueOf(activity.isFinishing()));
            isFinishing = true;
        } else {
            Log.d("PRUEBA", "CHAU MUNDO");
            rootHandler.uncaughtException(thread, ex);
        }
    }

    private boolean isFinishException(@NonNull final Throwable ex) {
        return ex instanceof RuntimeException &&
            ex.getMessage().contains(OnCreateException.class.getName());
    }
}