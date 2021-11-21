package com.example.smedy.helper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.widget.ProgressBar;

import com.example.smedy.R;

public class LoadingDialog {
    private Activity activity;
    private ProgressDialog progressDialog;

    public LoadingDialog(Activity activity){
        this.activity = activity;
    }

    public void startLoading(){
        progressDialog = new ProgressDialog(activity);
        progressDialog.show();

        progressDialog.setContentView(R.layout.loading_dialog);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    public void stopLoading(){
        progressDialog.dismiss();
    }

}
