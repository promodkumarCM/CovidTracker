package nl.psdcompany.duonavigationdrawer.corona.myinterface;

import android.content.DialogInterface;

/**
 * Created by Basil on 12-Nov-18.
 */

public interface DialogBoxCallback {
    /**
     * interface for handling the dialog action handling
     * @param dialogInterface current active dialogInterface
     * @param btnType for identify the btn type
     */
    void dialogBtnClick(DialogInterface dialogInterface, boolean btnType);
    void dialogBtnClick(DialogInterface dialogInterface, boolean btnType, String msg);

}
