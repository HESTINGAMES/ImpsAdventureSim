package com.hestingames.impsadventuresim;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;


public class AppRaterDialog extends Dialog implements
            android.view.View.OnClickListener {

        private final static String APP_PNAME = "com.hestingames.impsadventuresim";// Package Name

        public Activity c;
        public Context mContext;
        public Button rate, later, never;

        public AppRaterDialog(Context mContext, Activity a) {
            super(a);
            this.mContext = mContext;
            // TODO Auto-generated constructor stub
            this.c = a;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.rate_dialog);

            rate = findViewById(R.id.rate_btn);
            later = findViewById(R.id.later_btn);
            never = findViewById(R.id.never_btn);

            rate.setOnClickListener(this);
            later.setOnClickListener(this);
            never.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.rate_btn:
                    rate();
                    break;
                case R.id.later_btn:
                    later();
                    break;
                case R.id.never_btn:
                    dimis();
                    break;
                default:
                    break;
            }
        }

        public void rate() {
            mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + APP_PNAME)));
            dimis();
        }

        public void later(){
            SharedPreferences prefs = mContext.getSharedPreferences("apprater", 0);
            SharedPreferences.Editor editor = prefs.edit();
            if (editor != null) {
                Long date_firstLaunch = System.currentTimeMillis();
                editor.putLong("date_firstlaunch", date_firstLaunch);
                editor.commit();
            }
            dismiss();
        }

        public void dimis(){
            SharedPreferences prefs = mContext.getSharedPreferences("apprater", 0);
            SharedPreferences.Editor editor = prefs.edit();
            if (editor != null) {
                editor.putBoolean("dontshowagain", true);
                editor.commit();
            }
            dismiss();
        }
    }