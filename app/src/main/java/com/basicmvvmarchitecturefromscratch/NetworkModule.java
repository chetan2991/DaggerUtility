package com.basicmvvmarchitecturefromscratch;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by chetan on 25/04/17.
 */

// tell the dagger search object provider in this module
//@Module to signal to Dagger to search within the available methods for possible instance providers.

@Module
public class NetworkModule {


    String mBaseUrl;

    public NetworkModule(String mBaseUrl) {
        this.mBaseUrl = mBaseUrl;
    }

    /*
     The methods that will actually expose available return types should also be annotated with @Provides decorator.
     The Singleton annotation also signals to the Dagger compiler that the instance should be created only once in the application.
     In the following example, we are specifying SharedPreferences, Gson, Cache, OkHttpClient, and Retrofit as the return types that can be used as part of the dependency list.

    Note:-
     that the method names (i.e. provideSharedPreferences(), provideRetrofit(), etc) do not matter and can be named anything.
     The return type annotated with a @Provides decorator is used to associate this instantiation with any other modules of the same type.
     The @Singleton annotation is used to declare to Dagger to be only initialized only once during the entire lifecycle of the application.
     */


    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    /*
    A Retrofit instance depends both on a Gson and OkHttpClient instance,
    so we can define another method within the same class that takes these two types.
    The @Provides annotation and these two parameters in the method will cause
    Dagger to recognize that there is a dependency on Gson and OkHttpClient to build a Retrofit instance.
     */



    /*
     if we want to use two different object of same return type then used @Named Qualifier Anotation

     */


    /*
    If we need two different objects of the same return type, we can use the @Named qualifier annotation. You will define it both where you provide the singletons (@Provides annotation), and where you inject them (@Inject annotations):

@Provides @Named("cached")
@Singleton
OkHttpClient provideOkHttpClient(Cache cache) {
    OkHttpClient client = new OkHttpClient();
    client.setCache(cache);
    return client;
}

@Provides @Named("non_cached") @Singleton
OkHttpClient provideOkHttpClient() {
    OkHttpClient client = new OkHttpClient();
    return client;
}
Injection will also require these named annotations too:

@Inject @Named("cached") OkHttpClient client;
@Inject @Named("non_cached") OkHttpClient client2;
@Named is a qualifier that is pre-defined by dagger, but you can create your own qualifier annotations as well:

@Qualifier
@Documented
@Retention(RUNTIME)
public @interface DefaultPreferences {
}

     */


    @Provides
    @Singleton
    Cache provideOkHttpCache(Application application )
    {
        int cacheSize = 10*1024*1024; // 10 MiB
        Cache cache;
        cache = new Cache( application.getCacheDir(), cacheSize );
        return cache;
    }


    @Provides
    @Singleton
    @Named("OkkHttpClientWithCache")
    OkHttpClient provideOkkHttpClient( )
    {
        OkHttpClient okHttpClient = new OkHttpClient();
        return  okHttpClient;
    }

    @Provides
    @Singleton
    @Named("OkkHttpClientWithoutCache")
    OkHttpClient provideOkkHttpClientWihoutCache( Cache cache )
    {
      OkHttpClient okHttpClient = new OkHttpClient.Builder().cache( cache  ).build();
        return  okHttpClient;
    }

}
