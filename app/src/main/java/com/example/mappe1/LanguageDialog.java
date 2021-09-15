package com.example.mappe1;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

public class LanguageDialog extends DialogFragment {

    private DialogClickListener callback;

    public interface DialogClickListener {
        public void onYesClick();
        public void onNoClick();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            callback = (DialogClickListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Kallende klasse må implementere interfacet!");
        }
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.dialogtekst).setPositiveButton(R.string.ja, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                callback.onYesClick();
                            }
                        }
                )
                .setNegativeButton(R.string.nei, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int whichButton) {
                                callback.onNoClick();
                            }
                        }
                )
                .create();
        }
    }
