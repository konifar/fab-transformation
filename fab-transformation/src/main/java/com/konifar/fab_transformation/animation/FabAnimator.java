package com.konifar.fab_transformation.animation;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

import com.konifar.fab_transformation.FabTransformation;

public abstract class FabAnimator {

    static final float FAB_SCALE = 1.2f;
    static final Interpolator REVEAL_INTERPOLATOR = new DecelerateInterpolator();
    static final Interpolator FAB_INTERPOLATOR = new AccelerateInterpolator();
    static final Interpolator OVERLAY_INTERPOLATOR = new AccelerateDecelerateInterpolator();
    private long fabAnimationDuration;
    private long revealAnimationDuration;

    abstract void fabMoveIn(View fab, View transformView, FabAnimationCallback callback);

    abstract void fabMoveOut(View fab, View transformView, FabAnimationCallback callback);

    abstract void revealOn(View fab, View transformView, RevealCallback callback);

    abstract void revealOff(View fab, View transformView, RevealCallback callback);

    abstract void showOverlay(final View overlay);

    abstract void hideOverlay(final View overlay);

    private void calculateDuration(View fab, View transformView, long duration) {
        int fabTranslationX = getTranslationX(fab, transformView);
        int fabTranslationY = getTranslationY(fab, transformView);

        float maxFabTranslation = Math.max(Math.abs(fabTranslationX), Math.abs(fabTranslationY)) * 1.5f;
        float maxTransformView = Math.max(Math.abs(transformView.getWidth()), Math.abs(transformView.getHeight()));

        this.fabAnimationDuration = (long) (duration * (maxFabTranslation / (maxFabTranslation + maxTransformView)));
        this.revealAnimationDuration = (long) (duration * (maxTransformView / (maxFabTranslation + maxTransformView)));
    }

    long getFabAnimationDuration() {
        return fabAnimationDuration;
    }

    long getRevealAnimationDuration() {
        return revealAnimationDuration;
    }

    public void transformTo(final View fab, final View transformView, long duration, final View overlay,
                            final FabTransformation.OnTransformListener listener) {
        calculateDuration(fab, transformView, duration);

        fabMoveIn(fab, transformView, new FabAnimationCallback() {
            @Override
            public void onAnimationStart() {
                if (overlay != null) showOverlay(overlay);
                if (listener != null) listener.onStartTransform();
            }

            @Override
            public void onAnimationEnd() {
                revealOn(fab, transformView, new RevealCallback() {
                    @Override
                    public void onRevealStart() {
                        fab.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onRevealEnd() {
                        if (listener != null) listener.onEndTransform();
                    }
                });
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
    }

    public void transformOut(final View fab, final View transformView, long duration, final View overlay,
                             final FabTransformation.OnTransformListener listener) {
        calculateDuration(fab, transformView, duration);

        if (overlay != null) hideOverlay(overlay);

        revealOff(fab, transformView, new RevealCallback() {
            @Override
            public void onRevealStart() {
                if (listener != null) listener.onStartTransform();
            }

            @Override
            public void onRevealEnd() {
                fabMoveOut(fab, transformView, new FabAnimationCallback() {
                    @Override
                    public void onAnimationStart() {
                        fab.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd() {
                        if (listener != null) listener.onEndTransform();
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
            }
        });
    }

    int getCenterX(View view) {
        return view.getWidth() / 2 + view.getLeft();
    }

    int getCenterY(View view) {
        return view.getHeight() / 2 + view.getTop();
    }

    int getTranslationX(View fab, View transformView) {
        int fabX = ViewUtil.getRelativeLeft(fab) + fab.getWidth() / 2;
        int transformViewX = ViewUtil.getRelativeLeft(transformView) + transformView.getWidth() / 2;
        return transformViewX - fabX;
    }

    int getTranslationY(View fab, View transformView) {
        int fabY = ViewUtil.getRelativeTop(fab) + fab.getHeight() / 2;
        int transformViewY = ViewUtil.getRelativeTop(transformView) + transformView.getHeight() / 2;
        return transformViewY - fabY;
    }

    interface RevealCallback {
        void onRevealStart();

        void onRevealEnd();
    }

    interface FabAnimationCallback {
        void onAnimationStart();

        void onAnimationEnd();

        void onAnimationCancel();

        void onAnimationRepeat();
    }

}
