package nl.psdcompany.duonavigationdrawer.example.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;

import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import nl.psdcompany.duonavigationdrawer.example.R;

/**
 * Created by Basil1112 on 4/10/17.
 */

public class BasilHelper {

    @Nullable
    private static ProgressDialog sProgressDialog;

    /**
     * method to check if android version is lollipop
     *
     * @return this return value
     */
    public static boolean isAndroid5() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }




    /**
     * method for get a custom CustomToast
     *
     * @param Message this is the second parameter for CustomToast  method
     */
    public static void CustomToast(Context mContext, String Message) {

        LinearLayout CustomToastLayout = new LinearLayout(mContext.getApplicationContext());
        CustomToastLayout.setBackgroundResource(R.drawable.bg_custom_toast);
        CustomToastLayout.setGravity(Gravity.TOP);
        TextView message = new TextView(mContext.getApplicationContext());
        message.setTextColor(Color.WHITE);
        message.setTextSize(13);
        message.setPadding(20, 20, 20, 20);
        message.setGravity(Gravity.CENTER);
        message.setText(Message);
        CustomToastLayout.addView(message);
        Toast toast = new Toast(mContext.getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(CustomToastLayout);
        toast.setGravity(Gravity.CENTER, 0, 50);
        toast.show();
    }


    /**
     * method to get color
     *
     * @param context this is the first parameter for getColor  method
     * @param id      this is the second parameter for getColor  method
     * @return return value
     */
    public static int getColor(Context context, int id) {
        if (isAndroid5()) {
            return ContextCompat.getColor(context, id);
        } else {
            return context.getResources().getColor(id);
        }
    }


    /**
     * @param email
     * @return true or false
     */


    public static boolean isValidEmail(String email) {
       /* boolean isValid = false;

        String expression = "^[\\w\\.-]+@([" +
                "\\w\\-]+\\.)+[A-Z]{1,20}$";

        Pattern pattern = Pattern.compile(expression, Patterns.EMAIL_ADDRESS);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            isValid = true;
        }
         isValid;*/
        return !(email == null || email.isEmpty()) && Patterns.EMAIL_ADDRESS.matcher(email).

                matches();

    }

    /**
     * @param editText
     * @param errormessage
     */

    public static void setErrorMessageEditText(TextInputEditText editText, String errormessage) {
        editText.setError(errormessage);
        editText.requestFocus();
    }

    /**
     * @param target is email string
     * @return boolean
     */

    public static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }

    }


    /**
     * method to show snack bar
     *
     * @param mContext    this is the first parameter for Snackbar  method
     * @param view        this is the second parameter for Snackbar  method
     * @param Message     this is the thirded parameter for Snackbar  method
     * @param colorId     this is the fourth parameter for Snackbar  method
     * @param TextColorId this is the fifth parameter for Snackbar  method
     */
    public static void Snackbar(Context mContext, View view, String Message, int colorId, int TextColorId) {
        Snackbar snackbar = Snackbar.make(view, Message, Snackbar.LENGTH_LONG);
        View snackView = snackbar.getView();
        snackView.setBackgroundColor(ContextCompat.getColor(mContext, colorId));
        TextView snackbarTextView = (TextView) snackView.findViewById(com.google.android.material.R.id.snackbar_text);
        snackbarTextView.setTextColor(ContextCompat.getColor(mContext, TextColorId));
        snackbar.show();
    }

    public static void logCat(String message) {

        Log.d("BASIL", message.toString());
    }


    public static void showProgress(@Nullable Activity activity) {

        if (null != activity) {
            if (null == sProgressDialog)
            {
                sProgressDialog = new ProgressDialog(activity);
                sProgressDialog.setProgressStyle(R.style.MBillProgressTheme);

                sProgressDialog.setIndeterminate(true);
                sProgressDialog.setCancelable(false);
                if (sProgressDialog.getWindow() != null) {
                    sProgressDialog.getWindow()
                            .setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                }

                try {
                    if (!sProgressDialog.isShowing())
                        sProgressDialog.show();
                } catch (WindowManager.BadTokenException e) {

                }
                sProgressDialog.setContentView(R.layout.basil_layout_progress_dialog);
            }
//            sProgressDialog.setIndeterminate(true);
//            sProgressDialog.setCancelable(false);
//            if (sProgressDialog.getWindow() != null) {
//                sProgressDialog.getWindow()
//                        .setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//            }
//
//            try {
//                if (!sProgressDialog.isShowing())
//                    sProgressDialog.show();
//            } catch (WindowManager.BadTokenException e) {
//
//            }
//            sProgressDialog.setContentView(R.layout.basil_layout_progress_dialog);

        }
    }

    /**
     * To dismiss the progress
     */
    public static void hideProgress() {
        if (null != sProgressDialog) {
            sProgressDialog.dismiss();
            sProgressDialog = null;
        }
    }

    /**
     * To dismiss keyboard
     */
    public static void hideKeyBoard(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * date validation start end dates
     */

    public static boolean CheckDates(String startDate, String endDate) {
        SimpleDateFormat dfDate = new SimpleDateFormat("dd/MM/yyyy");

        boolean b = false;
        try {
            if (dfDate.parse(startDate).before(dfDate.parse(endDate))) {
                b = true;//If start date is before end date
            } else if (dfDate.parse(startDate).equals(dfDate.parse(endDate))) {
                b = true;//If two dates are equal
            } else {
                b = false; //If start date is after the end date
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return b;
    }

    /**
     * Date Validation with timestamp
     */

    public static boolean CheckDateTime(String startDate, String endDate){

        try {
            SimpleDateFormat dfDateTime = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");


            Date fromDate = dfDateTime.parse(startDate);
            Date toDate = dfDateTime.parse(endDate);

            long fromDateTime = fromDate.getTime();
            long toDateTime = toDate.getTime();

            if(fromDateTime >= toDateTime ){
                return false;
            }else {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * time validation start time and end time
     */

    public static boolean isTimeAfter(String sTime, String eTime) {

        boolean valid = false;
        try {
            SimpleDateFormat format = new SimpleDateFormat("hh:mm aa");
            SimpleDateFormat outFormat = new SimpleDateFormat("hh:mm");

            Date start_12_Time = format.parse(sTime);
            Date end_12_Time = format.parse(eTime);

            if (end_12_Time.before(start_12_Time)) {

                valid = false;
            } else {
                valid = true;
            }


        } catch (Exception ex) {
            valid = false;
        }


        return valid;
    }

    /**
     * Show snack bar for no internet
     */

    public static void noInternetSnackBar(View view,Context context)
    {
        BasilHelper.hideKeyBoard(view);
        BasilHelper.Snackbar(context, view, context.getString(R.string.noInternet), R.color.colorPrimaryDark, R.color.white);
    }

}
