package com.konifar.example.fabtransformation;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class AppUtil {
    public static void showToast(String message, Context context, int duration) {
        if (message == null || message.length() == 0) return;

        try {
            Toast toast = Toast.makeText(context, message, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } catch (Exception e) {
            //
        }
    }

    public static void showToast(int stringResId, Context context, int duration) {
        showToast(context.getResources().getString(stringResId), context, duration);
    }

    public static void showToast(String message, Context context) {
        showToast(message, context, Toast.LENGTH_SHORT);
    }

    public static void showToast(int stringResId, Context context) {
        showToast(context.getResources().getString(stringResId), context);
    }

}
