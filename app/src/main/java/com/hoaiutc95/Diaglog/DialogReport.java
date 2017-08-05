package com.hoaiutc95.Diaglog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoaiutc95.doan.R;

/**
 * Created by Thu Hoai on 4/15/2017.
 */

public class DialogReport {
    Context context;
    Dialog dialog;

    public DialogReport(Context context) {
        this.context = context;
    }

    public void showWin(int score) {
        if (this.dialog != null) {
            this.dialog.show();
        } else {
            this.dialog = new Dialog(this.context);
            this.dialog.requestWindowFeature(1);
            this.dialog.setContentView(R.layout.dialgo_ngoan);
            this.dialog.setCancelable(false);
            ImageView imCup = (ImageView) this.dialog.findViewById(R.id.imCup);
            ImageView btOk = (ImageView) this.dialog.findViewById(R.id.btOk);
            TextView tvBeNgoan = (TextView) this.dialog.findViewById(R.id.tvBeNgoan);
            ((TextView) this.dialog.findViewById(R.id.tvScore)).setText(this.context.getString(R.string.hello_world) + ": " + score);
            tvBeNgoan.setText("Very Good !");
            btOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //dong
                    DialogReport.this.dialog.dismiss();
                    ((Activity) DialogReport.this.context).finish();
                }
            });

        }
        this.dialog.show();
    }
}
