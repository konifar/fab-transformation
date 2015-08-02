package com.konifar.fab_transformation;

import android.os.Build;
import android.view.View;

import com.konifar.fab_transformation.animation.FabAnimator;
import com.konifar.fab_transformation.animation.FabAnimatorLollipop;
import com.konifar.fab_transformation.animation.FabAnimatorPreL;

public class FabTransformation {

    private final static boolean IS_PRE_LOLLIPOP = Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP;

    public static Builder with(View fab) {
        return new Builder(fab);
    }

    public static class Builder {
        private View fab;
        private View transformView;
        private FabAnimator animator;

        public Builder(View fab) {
            this.fab = fab;
            this.animator = IS_PRE_LOLLIPOP ? new FabAnimatorPreL() : new FabAnimatorLollipop();
        }

        public Builder setTransformView(View transformView) {
            this.transformView = transformView;
            return this;
        }

        public void transformIn() {
            if (transformView == null) {
                throw new IllegalStateException("transformView is not set.");
            }
            if (fab.getVisibility() == View.VISIBLE) {
                animator.transformIn(fab, transformView);
            }
        }

        public void transformOut() {
            if (transformView == null) {
                throw new IllegalStateException("transformView is not set.");
            }
            if (fab.getVisibility() != View.VISIBLE) {
                animator.transformOut(fab, transformView);
            }
        }
    }
}
