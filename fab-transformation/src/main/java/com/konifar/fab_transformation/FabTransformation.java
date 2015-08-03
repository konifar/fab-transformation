package com.konifar.fab_transformation;

import android.os.Build;
import android.view.View;

import com.konifar.fab_transformation.animation.FabAnimator;
import com.konifar.fab_transformation.animation.FabAnimatorLollipop;
import com.konifar.fab_transformation.animation.FabAnimatorPreL;

public class FabTransformation {

    private static final boolean IS_PRE_LOLLIPOP = Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP;
    private static final long DEFAULT_DURATION = 300;

    public static Builder with(View fab) {
        return new Builder(fab);
    }

    public interface OnTransformListener {
        void onStartTransform();

        void onEndTransform();
    }

    public static class Builder {
        private View fab;
        private View overlay;
        private FabAnimator animator;
        private long duration;
        private OnTransformListener listener;

        public Builder(View fab) {
            this.fab = fab;
            this.animator = IS_PRE_LOLLIPOP ? new FabAnimatorPreL() : new FabAnimatorLollipop();
            this.duration = DEFAULT_DURATION;
        }

        public Builder duration(long millsecond) {
            this.duration = millsecond;
            return this;
        }

        public Builder setListener(OnTransformListener listener) {
            this.listener = listener;
            return this;
        }

        public Builder setOverlay(View overlay) {
            this.overlay = overlay;
            return this;
        }

        public void transformTo(View transformView) {
            if (transformView == null) {
                throw new IllegalStateException("transformView is not set.");
            }
            if (fab.getVisibility() == View.VISIBLE) {
                animator.transformTo(fab, transformView, duration, overlay, listener);
            }
        }

        public void transformFrom(View transformView) {
            if (transformView == null) {
                throw new IllegalStateException("transformView is not set.");
            }
            if (fab.getVisibility() != View.VISIBLE) {
                animator.transformOut(fab, transformView, duration, overlay, listener);
            }
        }

    }
}
