package com.quanminjieshui.waterchain.base.permission;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.quanminjieshui.waterchain.ui.view.AlertChainDialog;


/**
 * Created by WanghongHe on 2018/11/12 17:38.
 * Click listener for either {@link RationaleDialogFragment} or {@link RationaleDialogFragmentCompat}.
 */
class RationaleDialogConfig {

    private static final String KEY_POSITIVE_BUTTON = "positiveButton";
    private static final String KEY_NEGATIVE_BUTTON = "negativeButton";
    private static final String KEY_RATIONALE_MESSAGE = "rationaleMsg";
    private static final String KEY_REQUEST_CODE = "requestCode";
    private static final String KEY_PERMISSIONS = "permissions";

    String positiveButton;
    String negativeButton;
    int requestCode;
    String rationaleMsg;
    String[] permissions;

    RationaleDialogConfig(@NonNull String positiveButton, @NonNull String negativeButton,
                          @NonNull String rationaleMsg, int requestCode,
                          @NonNull String[] permissions) {

        this.positiveButton = positiveButton;
        this.negativeButton = negativeButton;
        this.rationaleMsg = rationaleMsg;
        this.requestCode = requestCode;
        this.permissions = permissions;
    }

    RationaleDialogConfig(Bundle bundle) {
        positiveButton = bundle.getString(KEY_POSITIVE_BUTTON);
        negativeButton = bundle.getString(KEY_NEGATIVE_BUTTON);
        rationaleMsg = bundle.getString(KEY_RATIONALE_MESSAGE);
        requestCode = bundle.getInt(KEY_REQUEST_CODE);
        permissions = bundle.getStringArray(KEY_PERMISSIONS);
    }

    Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_POSITIVE_BUTTON, positiveButton);
        bundle.putString(KEY_NEGATIVE_BUTTON, negativeButton);
        bundle.putString(KEY_RATIONALE_MESSAGE, rationaleMsg);
        bundle.putInt(KEY_REQUEST_CODE, requestCode);
        bundle.putStringArray(KEY_PERMISSIONS, permissions);

        return bundle;
    }

    AlertChainDialog createDialog(Context context, View.OnClickListener listener) {
        AlertChainDialog alertChainDialog = new AlertChainDialog(context).builder();
        alertChainDialog.setCancelable(false);
        alertChainDialog.setPositiveButton(positiveButton, listener)
                .setNegativeButton(negativeButton, listener)
                .setTitle("申请权限")
                .setMsg(rationaleMsg)
                .show();
        return alertChainDialog;

    }

}
