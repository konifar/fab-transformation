package com.konifar.fab_transformation;

import android.animation.Animator;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;

import com.nineoldandroids.view.ViewPropertyAnimator;

import io.codetail.animation.SupportAnimator;

public class FabTransformation {
    private static void moveIn(final View fab, View transformView,
                               com.nineoldandroids.animation.Animator.AnimatorListener listener) {
        ViewPropertyAnimator.animate(fab)
                .translationX(getTranslationX(fab, transformView))
                .translationY(getTranslationY(fab, transformView))
                .setInterpolator(new AccelerateInterpolator())
                .setDuration(150L)
                .setListener(listener)
                .start();
    }

    private static int getTranslationX(View fab, View transformView) {
        int fabWidth = fab.getWidth();
        int fabLeft = ViewUtil.getRelativeLeft(fab);
        int fabRight = fabLeft + fabWidth;
        int fabCenterX = fabLeft + fabWidth / 2;
        int transformViewWidth = transformView.getWidth();
        int transformViewLeft = ViewUtil.getRelativeLeft(transformView);
        int transformViewRight = transformViewLeft + transformViewWidth;
        int transformViewCenterX = transformViewLeft + transformViewWidth / 2;

        if (fabCenterX > transformViewCenterX) {
            // Fab is on right of transform view
            int translationX = transformViewCenterX - fabCenterX;
            if (-translationX >= fabWidth / 2) {
                translationX = -fabWidth / 2;
            }
            if (-translationX < fabRight - transformViewRight) {
                translationX = -(fabRight - transformViewRight + fabWidth / 2);
            }
            return translationX;
        } else if (fabCenterX < transformViewCenterX) {
            // Fab is on left of transform view
            int translationX = transformViewCenterX - fabCenterX;
            if (translationX >= fabWidth / 2) {
                translationX = fabWidth / 2;
            }
            if (translationX > transformViewLeft - fabLeft) {
                translationX = transformViewLeft - fabLeft + fabWidth / 2;
            }
            return translationX;
        } else {
            // Fab is same position with transform view
            return 0;
        }
    }

    private static int getTranslationY(View fab, View transformView) {
        int fabHeight = fab.getHeight();
        int fabTop = ViewUtil.getRelativeTop(fab);
        int fabBottom = fabTop + fabHeight;
        int fabCenterY = fabTop + fabHeight / 2;
        int transformViewHeight = transformView.getHeight();
        int transformViewTop = ViewUtil.getRelativeTop(transformView);
        int transformViewBottom = transformViewTop + transformViewHeight;
        int transformViewCenterY = transformViewTop + transformViewHeight / 2;

        if (fabCenterY > transformViewCenterY) {
            // Fab is on below of transform view
            int translationY = transformViewCenterY - fabCenterY;
            if (-translationY >= fabHeight / 2) {
                translationY = -fabHeight / 2;
            }
            if (-translationY < fabBottom - transformViewBottom) {
                translationY = -(fabBottom - transformViewBottom + fabHeight / 2);
            }
            return translationY;
        } else if (fabCenterY < transformViewCenterY) {
            // Fab is on above of transform view
            int translationY = transformViewCenterY - fabCenterY;
            if (translationY >= fabHeight / 2) {
                translationY = fabHeight / 2;
            }
            if (translationY > transformViewTop - fabTop) {
                translationY = transformViewTop - fabTop + fabHeight / 2;
            }
            return translationY;
        } else {
            // Fab is same position with transform view
            return 0;
        }
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
        int centerX = ViewUtil.getRelativeLeft(fab) - ViewUtil.getRelativeLeft(transformView) + fab.getWidth() / 2;
        int centerY = ViewUtil.getRelativeTop(fab) - ViewUtil.getRelativeTop(transformView) + fab.getHeight() / 2;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Animator animator = ViewAnimationUtils.createCircularReveal(
                    transformView,
                    centerX,
                    centerY,
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
                            centerX,
                            centerY,
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
        int centerX = ViewUtil.getRelativeLeft(fab) - ViewUtil.getRelativeLeft(transformView) + fab.getWidth() / 2;
        int centerY = ViewUtil.getRelativeTop(fab) - ViewUtil.getRelativeTop(transformView) + fab.getHeight() / 2;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            SupportAnimator animator =
                    io.codetail.animation.ViewAnimationUtils.createCircularReveal(
                            transformView,
                            centerX,
                            centerY,
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
                            centerX,
                            centerY,
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

    private interface RevealCallback {
        void onRevealStart();

        void onRevealEnd();
    }
}
