package com.basicmvvmarchitecturefromscratch;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chetan on 25/04/17.
 */


// tell the dagger to search provider for object
@Module
public class AppModule
{


    Application mApplication;

    public AppModule(Application mApplication)
    {
        this.mApplication = mApplication;
    }


    @Provides
    @Singleton
    Application provideApplication()
    {
        return  this.mApplication;
    }

}
