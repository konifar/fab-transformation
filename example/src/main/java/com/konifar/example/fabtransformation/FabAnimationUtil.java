package com.konifar.example.fabtransformation;

import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

public class FabAnimationUtil {

    private static final long DEFAULT_SCALE_DURATION = 200L;
    private static final Interpolator FAST_OUT_SLOW_IN_INTERPOLATOR = new FastOutSlowInInterpolator();

    public static void animateIn(final View fab) {
        animateIn(fab, DEFAULT_SCALE_DURATION, null);
    }

    public static void animateIn(final View fab, long duration, final AnimateCallback callback) {
        if (fab == null) return;

        fab.setVisibility(View.VISIBLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            ViewCompat.animate(fab)
                    .scaleX(1.0F)
                    .scaleY(1.0F)
                    .alpha(1.0F)
                    .setDuration(duration)
                    .setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR)
                    .withLayer()
                    .setListener(new ViewPropertyAnimatorListener() {
                        public void onAnimationStart(View view) {
                            if (callback != null) callback.onAnimationStart();
                        }

                        public void onAnimationCancel(View view) {
                        }

                        public void onAnimationEnd(View view) {
                            view.setVisibility(View.VISIBLE);
                            if (callback != null) callback.onAnimationEnd();
                        }
                    }).start();
        } else {
            Animation anim = AnimationUtils.loadAnimation(fab.getContext(), R.anim.fab_in);
            anim.setDuration(duration);
            anim.setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR);
            anim.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationStart(Animation animation) {
                    if (callback != null) callback.onAnimationStart();
                }

                public void onAnimationEnd(Animation animation) {
                    fab.setVisibility(View.VISIBLE);
                    if (callback != null) callback.onAnimationEnd();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    //
                }
            });
            fab.startAnimation(anim);
        }
    }

    public static void animateOut(final View fab) {
        animateOut(fab, DEFAULT_SCALE_DURATION, null);
    }

    public static void animateOut(final View fab, final AnimateCallback callback) {
        animateOut(fab, DEFAULT_SCALE_DURATION, callback);
    }

    public static void animateOut(final View fab, long duration, final AnimateCallback callback) {
        if (fab == null) return;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            ViewCompat.animate(fab)
                    .scaleX(0.0F)
                    .scaleY(0.0F).alpha(0.0F)
                    .setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR)
                    .setDuration(duration)
                    .withLayer()
                    .setListener(new ViewPropertyAnimatorListener() {
                        public void onAnimationStart(View view) {
                            if (callback != null) callback.onAnimationStart();
                        }

                        public void onAnimationCancel(View view) {
                        }

                        public void onAnimationEnd(View view) {
                            view.setVisibility(View.INVISIBLE);
                            if (callback != null) callback.onAnimationEnd();
                        }
                    }).start();
        } else {
            Animation anim = AnimationUtils.loadAnimation(fab.getContext(), R.anim.fab_out);
            anim.setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR);
            anim.setDuration(duration);
            anim.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationStart(Animation animation) {
                    if (callback != null) callback.onAnimationStart();
                }

                public void onAnimationEnd(Animation animation) {
                    fab.setVisibility(View.INVISIBLE);
                    if (callback != null) callback.onAnimationEnd();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    //
                }
            });
            fab.startAnimation(anim);
        }
    }

    public interface AnimateCallback {
        void onAnimationStart();

        void onAnimationEnd();
    }

}
