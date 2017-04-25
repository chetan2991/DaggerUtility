package com.basicmvvmarchitecturefromscratch;

import android.app.Application;
import android.content.Context;

/**
 * Created by chetan on 25/04/17.
 */

public class CustomeApplication extends Application
{
    private NetComponent mNetComponent;
    @Override
    public void onCreate() {
        super.onCreate();

        mNetComponent = DaggerNetComponent
                .builder()
                .appModule( new AppModule(this ))
                .networkModule( new NetworkModule("https://"))
                .build();
    }


    public static NetComponent getNetComponent(Context context )
    {

        CustomeApplication app  = (CustomeApplication)context.getApplicationContext();
        if( app != null )
        {
            return  app.provideNetComponent();
        }

        return null;
    }

    private NetComponent provideNetComponent()
    {
        return  mNetComponent;
    }
}
