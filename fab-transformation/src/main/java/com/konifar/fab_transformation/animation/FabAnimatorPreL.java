package com.konifar.fab_transformation.animation;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.view.ViewPropertyAnimator;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;

public class FabAnimatorPreL extends FabAnimator {

    @Override
    final void revealOff(final View fab, final View transformView, final RevealCallback callback) {
        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(
                transformView,
                getCenterX(fab),
                getCenterY(fab),
                (float) Math.hypot(transformView.getWidth(), transformView.getHeight()) / 2,
                fab.getWidth() / 2);
        animator.setInterpolator(REVEAL_INTERPOLATOR);
        animator.addListener(new SupportAnimator.AnimatorListener() {
            @Override
            public void onAnimationStart() {
                callback.onRevealStart();
            }

            @Override
            public void onAnimationEnd() {
                transformView.setVisibility(View.INVISIBLE);
                callback.onRevealEnd();
            }

            @Override
            public void onAnimationCancel() {
                //
            }

            @Override
            public void onAnimationRepeat() {
                //
            }
        });
        if (transformView.getVisibility() == View.VISIBLE) {
            animator.setDuration((int) getRevealAnimationDuration());
            animator.start();
            transformView.setEnabled(true);
        }
    }

    @Override
    final void revealOn(final View fab, final View transformView, final RevealCallback callback) {
        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(
                transformView,
                getCenterX(fab),
                getCenterY(fab),
                fab.getWidth() / 2,
                (float) Math.hypot(transformView.getWidth(), transformView.getHeight()) / 2);
        transformView.setVisibility(View.VISIBLE);
        animator.setInterpolator(FAB_INTERPOLATOR);
        animator.addListener(new SupportAnimator.AnimatorListener() {
            @Override
            public void onAnimationStart() {
                callback.onRevealStart();
            }

            @Override
            public void onAnimationEnd() {
                callback.onRevealEnd();
            }

            @Override
            public void onAnimationCancel() {
                //
            }

            @Override
            public void onAnimationRepeat() {
                //
            }
        });
        if (transformView.getVisibility() == View.VISIBLE) {
            animator.setDuration((int) getRevealAnimationDuration());
            animator.start();
            transformView.setEnabled(true);
        }
    }

    @Override
    final void fabMoveOut(final View fab, final View transformView, final FabAnimationCallback callback) {
        ViewPropertyAnimator.animate(fab)
                .scaleX(1)
                .scaleY(1)
                .translationX(0)
                .translationY(0)
                .setInterpolator(new AccelerateDecelerateInterpolator())
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

    @Override
    final void fabMoveIn(final View fab, final View transformView, final FabAnimationCallback callback) {
        ViewPropertyAnimator.animate(fab)
                .scaleX(FAB_SCALE)
                .scaleY(FAB_SCALE)
                .translationX(getTranslationX(fab, transformView))
                .translationY(getTranslationY(fab, transformView))
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

    @Override
    void showOverlay(final View overlay) {
        ViewPropertyAnimator.animate(overlay)
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

    @Override
    void hideOverlay(final View overlay) {
        ViewPropertyAnimator.animate(overlay)
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
