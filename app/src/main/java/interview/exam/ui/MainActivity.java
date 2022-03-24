package interview.exam.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;


import dagger.android.support.HasSupportFragmentInjector;
import interview.exam.R;
import interview.exam.dependency_injection.Injectable;

public class MainActivity extends AppCompatActivity implements Injectable,HasSupportFragmentInjector {
    @Inject DispatchingAndroidInjector<Fragment> androidInjector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return androidInjector;
    }
}