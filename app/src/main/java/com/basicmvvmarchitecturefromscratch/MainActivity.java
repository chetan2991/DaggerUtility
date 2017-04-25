package com.basicmvvmarchitecturefromscratch;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = "MainActivity";

    @Inject
    SharedPreferences mSharedPreferences;

    @Inject
    @Named("OkkHttpClientWithoutCache")
    OkHttpClient mOkkHttpClientWithoutCache;

    @Inject
    @Named("OkkHttpClientWithCache")
    OkHttpClient mOkkHttpClientWithCache;



    private Button welcomebtn;
    private Button injectWithNamed;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this is dagger initialization code

        /*

        Adavantages of Using Dagger
        1.  Dagger 2 provides a simple way to obtain references to shared instances.
        2.  easy configuration of complex dependencies:::
         There is an implicit order in which your objects are often created.
         Dagger 2 walks through the dependency graph and generates code that is both easy to understand and trace,
         while also saving you from writing the large amount of boilerplate code you would normally need to write by hand to obtain references and pass them to other objects as dependencies.
         It also helps simplify refactoring, since you can focus on what modules to build rather than focusing on the order in which they need to be created.
        3. Easier Unit and integration Testing : because dependency graph is created for us
            we can easily swap out modules that make network responses and mock out this behavior.
        4. Scoped Instances : not only u can  manage instances that can last the entire application lifecycle,
         you can also leverage Dagger 2 to define instances with shorter lifetimes (i.e. bound to a user session, activity lifecycle, etc.).

         */
        CustomeApplication.getNetComponent( this ).inject( this );
        welcomebtn = ( Button ) findViewById( R.id.welcomebtn );
        welcomebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if( mSharedPreferences != null )
                {
                    Toast.makeText( MainActivity.this, "Shared Preference Object Inject SucessFully ", Toast.LENGTH_LONG ).show();
                }

            }
        });

        injectWithNamed = ( Button  ) findViewById( R.id.checkedInjectWithNamed );

        injectWithNamed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if( mOkkHttpClientWithCache != null )
                {
                    Toast.makeText( MainActivity.this, "Inject OkkHttp With named", Toast.LENGTH_LONG ).show();
                }
                if( mOkkHttpClientWithoutCache != null )
                {
                    Toast.makeText( MainActivity.this, "Inject OkkHttp Without named", Toast.LENGTH_LONG ).show();

                }
            }
        });

    }


    @Override
    protected void onResume()
    {
        super.onResume();



    }




    @Override
    protected void onStart()
    {
        super.onStart();
        Log.d( TAG ," In OnStart Method of MainActivity ");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.d( TAG, " In OnStop Method of MainActivity ");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.d( TAG, " In OnPause Method of MainActivity ");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.d( TAG, " In OnDestroy Method of MainActivity ");
    }
}


