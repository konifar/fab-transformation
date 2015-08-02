package com.konifar.fab_transformation.animation;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;

public class FabAnimatorLollipop extends FabAnimator {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    final void revealOut(final View fab, final View transformView, final RevealCallback callback) {
        Animator animator = ViewAnimationUtils.createCircularReveal(
                transformView,
                getCenterX(fab, transformView),
                getCenterY(fab, transformView),
                (float) Math.hypot(transformView.getWidth(), transformView.getHeight()),
                fab.getWidth());
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
            animator.setDuration(REVEAL_ANIMATION_DURATION);
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
                fab.getWidth(),
                (float) Math.hypot(transformView.getWidth(), transformView.getHeight()));
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
            animator.setDuration(REVEAL_ANIMATION_DURATION);
            animator.start();
            transformView.setEnabled(true);
        }
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    final void moveOut(final View fab, final View transformView, final FabAnimationCallback callback) {
        fab.animate()
                .translationX(0)
                .translationY(0)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setDuration(FAB_ANIMATION_DURATION)
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
    final void moveIn(final View fab, View transformView, final FabAnimationCallback callback) {
        fab.animate()
                .translationX(getTranslationX(fab, transformView))
                .translationY(getTranslationY(fab, transformView))
                .setInterpolator(REVEAL_INTERPOLATOR)
                .setDuration(REVEAL_ANIMATION_DURATION)
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

}