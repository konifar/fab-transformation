package com.konifar.fab_transformation;

import android.animation.Animator;
import android.content.Context;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;

import com.nineoldandroids.view.ViewPropertyAnimator;

import io.codetail.animation.SupportAnimator;

public class FabTransformation {
    private static void moveIn(final View fab, View view,
                               com.nineoldandroids.animation.Animator.AnimatorListener listener) {
        int marginRight;
        int marginBottom;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            marginRight = 16;
            marginBottom = 16;
        } else {
            marginRight = 8;
            marginBottom = 0;
        }
        ViewPropertyAnimator.animate(fab)
                .translationX(-(view.getWidth() / 2) + (fab.getWidth() / 2) + dpToPx(view.getContext(), marginRight))
                .translationY(-(view.getHeight() / 2) + (fab.getHeight() / 2) + dpToPx(view.getContext(), marginBottom))
                .setInterpolator(new AccelerateInterpolator())
                .setDuration(150L)
                .setListener(listener)
                .start();
    }

    private static void moveOut(final View fab, com.nineoldandroids.animation.Animator.AnimatorListener listener) {
        ViewPropertyAnimator.animate(fab)
                .translationX(0)
                .translationY(0)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setDuration(150L)
                .setListener(listener)
                .start();
    }

    public static void transformIn(final View fab, final View transformView) {
        moveIn(fab, transformView, new com.nineoldandroids.animation.Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(com.nineoldandroids.animation.Animator animation) {

            }

            @Override
            public void onAnimationEnd(com.nineoldandroids.animation.Animator animation) {
                revealOn(fab, transformView, new RevealCallback() {
                    @Override
                    public void onRevealStart() {
                        fab.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onRevealEnd() {
                    }
                });
            }

            @Override
            public void onAnimationCancel(com.nineoldandroids.animation.Animator animation) {

            }

            @Override
            public void onAnimationRepeat(com.nineoldandroids.animation.Animator animation) {

            }
        });
    }

    public static void transformOut(final View fab, final View transformView) {
        revealOff(fab, transformView, new RevealCallback() {
            @Override
            public void onRevealStart() {
            }

            @Override
            public void onRevealEnd() {
                moveOut(fab, new com.nineoldandroids.animation.Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(com.nineoldandroids.animation.Animator animation) {
                        fab.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(com.nineoldandroids.animation.Animator animation) {

                    }

                    @Override
                    public void onAnimationCancel(com.nineoldandroids.animation.Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(com.nineoldandroids.animation.Animator animation) {

                    }
                });
            }
        });
    }

    private static void revealOn(final View fab, View transformView, final RevealCallback callback) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Animator animator = ViewAnimationUtils.createCircularReveal(
                    transformView,
                    transformView.getWidth() / 2,
                    transformView.getHeight() / 2,
                    fab.getWidth(),
                    (float) Math.hypot(transformView.getWidth(), transformView.getHeight()));
            transformView.setVisibility(View.VISIBLE);
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
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
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            });
            if (transformView.getVisibility() == View.VISIBLE) {
                animator.setDuration(350);
                animator.start();
                transformView.setEnabled(true);
            }
        } else {
            SupportAnimator animator =
                    io.codetail.animation.ViewAnimationUtils.createCircularReveal(
                            transformView,
                            transformView.getWidth() / 2,
                            transformView.getHeight() / 2,
                            fab.getWidth(),
                            (float) Math.hypot(transformView.getWidth(), transformView.getHeight()));
            transformView.setVisibility(View.VISIBLE);
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.addListener(new io.codetail.animation.SupportAnimator.AnimatorListener() {
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
                }

                @Override
                public void onAnimationRepeat() {
                }
            });
            if (transformView.getVisibility() == View.VISIBLE) {
                animator.setDuration(350);
                animator.start();
                transformView.setEnabled(true);
            }
        }
    }

    private static void revealOff(final View fab, final View transformView, final RevealCallback callback) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            SupportAnimator animator =
                    io.codetail.animation.ViewAnimationUtils.createCircularReveal(
                            transformView,
                            transformView.getWidth() / 2,
                            transformView.getHeight() / 2,
                            (float) Math.hypot(transformView.getWidth(), transformView.getHeight()),
                            fab.getWidth());
            animator.setInterpolator(new AccelerateInterpolator());
            animator.addListener(new io.codetail.animation.SupportAnimator.AnimatorListener() {
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
                }

                @Override
                public void onAnimationRepeat() {
                }
            });
            if (transformView.getVisibility() == View.VISIBLE) {
                animator.setDuration(350);
                animator.start();
                transformView.setEnabled(true);
            }
        } else {
            SupportAnimator animator =
                    io.codetail.animation.ViewAnimationUtils.createCircularReveal(
                            transformView,
                            transformView.getWidth() / 2,
                            transformView.getHeight() / 2,
                            (float) Math.hypot(transformView.getWidth(), transformView.getHeight()),
                            fab.getWidth());
            animator.setInterpolator(new AccelerateInterpolator());
            animator.addListener(new io.codetail.animation.SupportAnimator.AnimatorListener() {
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
                }

                @Override
                public void onAnimationRepeat() {
                }
            });
            if (transformView.getVisibility() == View.VISIBLE) {
                animator.setDuration(350);
                animator.start();
                transformView.setEnabled(true);
            }
        }
    }

    private static float dpToPx(Context context, float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value,
                context.getResources().getDisplayMetrics());
    }

    private interface RevealCallback {
        void onRevealStart();

        void onRevealEnd();
    }
}
