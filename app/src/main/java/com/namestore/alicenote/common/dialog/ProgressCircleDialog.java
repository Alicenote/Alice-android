package com.namestore.alicenote.common.dialog;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Build;

import com.namestore.alicenote.R;


/**
 * Created by kienht on 12/15/16.
 */

public class ProgressCircleDialog {

    ProgressDialog progressDialog;

    private static ProgressCircleDialog _instance;

    public static ProgressCircleDialog onCreat(Activity activity) {
        if (_instance == null) {
            _instance = new ProgressCircleDialog(activity);
        }
        return _instance;
    }

    private ProgressCircleDialog(Activity activity) {
        progressDialog = new ProgressDialog(activity, R.style.ProgressCircleDialog);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.setIndeterminateDrawable(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ?
                activity.getResources().getDrawable(R.drawable.circle_dialog, activity.getTheme()) :
                activity.getResources().getDrawable(R.drawable.circle_dialog));
    }

    public boolean isPrgDialogShow() {
        return progressDialog.isShowing();
    }

    public void showPrgDialog() {
        progressDialog.show();
    }

    public void dissmissPrgDialog() {
        progressDialog.dismiss();
        clearInstance();
    }

    private void clearInstance() {
        _instance = null;
    }


}
