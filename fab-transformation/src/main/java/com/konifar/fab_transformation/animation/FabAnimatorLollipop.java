package com.konifar.fab_transformation.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;

public class FabAnimatorLollipop extends FabAnimator {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    final void revealOff(final View fab, final View transformView, final RevealCallback callback) {
        Animator animator = ViewAnimationUtils.createCircularReveal(
                transformView,
                getCenterX(fab, transformView),
                getCenterY(fab, transformView),
                (float) Math.hypot(transformView.getWidth(), transformView.getHeight()) / 2,
                fab.getWidth() / 2);
        animator.setInterpolator(REVEAL_INTERPOLATOR);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                callback.onRevealStart();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                transformView.setVisibility(View.INVISIBLE);
                callback.onRevealEnd();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                //
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                //
            }
        });
        if (transformView.getVisibility() == View.VISIBLE) {
            animator.setDuration(getRevealAnimationDuration());
            animator.start();
            transformView.setEnabled(true);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    final void revealOn(final View fab, View transformView, final RevealCallback callback) {
        Animator animator = ViewAnimationUtils.createCircularReveal(
                transformView,
                getCenterX(fab, transformView),
                getCenterY(fab, transformView),
                fab.getWidth() / 2,
                (float) Math.hypot(transformView.getWidth(), transformView.getHeight()) / 2);
        transformView.setVisibility(View.VISIBLE);
        animator.setInterpolator(REVEAL_INTERPOLATOR);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                callback.onRevealStart();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                callback.onRevealEnd();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                //
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                //
            }
        });
        if (transformView.getVisibility() == View.VISIBLE) {
            animator.setDuration(getRevealAnimationDuration());
            animator.start();
            transformView.setEnabled(true);
        }
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    final void fabMoveOut(final View fab, final View transformView, final FabAnimationCallback callback) {
        fab.animate()
                .scaleX(1)
                .scaleY(1)
                .translationX(0)
                .translationY(0)
                .setInterpolator(FAB_INTERPOLATOR)
                .setDuration(getFabAnimationDuration())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        callback.onAnimationStart();
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        callback.onAnimationEnd();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        callback.onAnimationCancel();
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        callback.onAnimationRepeat();
                    }
                })
                .start();
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    final void fabMoveIn(final View fab, View transformView, final FabAnimationCallback callback) {
        fab.animate()
                .scaleX(FAB_SCALE)
                .scaleY(FAB_SCALE)
                .translationX(getTranslationX(fab, transformView))
                .translationY(getTranslationY(fab, transformView))
                .setInterpolator(FAB_INTERPOLATOR)
                .setDuration(getRevealAnimationDuration())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        callback.onAnimationStart();
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        callback.onAnimationEnd();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        callback.onAnimationCancel();
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        callback.onAnimationRepeat();
                    }
                })
                .start();
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    void showOverlay(final View overlay) {
        overlay.animate()
                .alpha(1)
                .setDuration(getRevealAnimationDuration())
                .setInterpolator(OVERLAY_INTERPOLATOR)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        overlay.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        //
                    }
                })
                .start();
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    void hideOverlay(final View overlay) {
        overlay.animate()
                .alpha(0)
                .setDuration(getRevealAnimationDuration())
                .setInterpolator(OVERLAY_INTERPOLATOR)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        //
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        overlay.setVisibility(View.GONE);
                    }
                })
                .start();
    }

}
