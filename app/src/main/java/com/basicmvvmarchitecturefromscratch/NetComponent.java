package com.basicmvvmarchitecturefromscratch;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by chetan on 25/04/17.
 */
@Singleton
@Component( modules = {AppModule.class, NetworkModule.class})
public interface NetComponent
{
    void inject( MainActivity mainActivity );
    //    void inject( Fragment fragment );
    //    void inject( Service service );

    /*
    An important aspect of Dagger 2 is that the library generates code for classes annotated with the @Component interface.
    You can use a class prefixed with Dagger (i.e. DaggerTwitterApiComponent.java) that will be responsible for instantiating an instance of our dependency graph
    and using it to perform the injection work for fields annotated with @Inject. See the setup guide.
     */

}
