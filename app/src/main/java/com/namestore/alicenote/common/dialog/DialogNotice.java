package com.namestore.alicenote.common.dialog;

import android.app.Activity;
import android.app.Dialog;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


import com.namestore.alicenote.R;

/**
 * Created by kienht on 9/11/2016.
 */

public class DialogNotice {

    public void showDialog(Activity activity, String msg) {
        final Dialog dialog = new Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_notice);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);
        
        TextView text = (TextView) dialog.findViewById(R.id.tvDialogContent);
        text.setText(msg);

        Button dialogButton = (Button) dialog.findViewById(R.id.btnDone);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
}

