package com.Test.themoviedb.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.Test.themoviedb.R;
import com.Test.themoviedb.ui.activity.base.LoadDataActivity;
import com.Test.themoviedb.ui.presenter.SplashPresenter;
import com.Test.themoviedb.ui.presenter.base.IPresenter;

/**
 * This activity must be the launcher one, is purpose is to download data if the content provider
 * is empty
 */
public class SplashActivity extends LoadDataActivity<Void> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        this.presenter.execute();
    }

    @Override
    protected IPresenter createPresenter() {
        return new SplashPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void setData(Void data) {
        //launch home activity when presenter is done
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
        finish();
    }
}
