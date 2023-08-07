package io.github.handharbeni.erpas.cores;

import android.app.Application;

import com.google.firebase.FirebaseApp;

public class CoreApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(getApplicationContext());
    }
}
