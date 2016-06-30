package cn.zhichi.preventclick.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Toast;

import cn.zhichi.preventclick.application.CommonApp;


public class ViewUtils {



    public static void toastShort(String msg) {
        Context context = CommonApp.getInstance();
        toastDialog(context, msg, Toast.LENGTH_SHORT);
    }


    public static void toastLong(String msg) {
        Context context = CommonApp.getInstance();
        toastDialog(context, msg, Toast.LENGTH_LONG);
    }


    private static Toast toast;

    public static void toastDialog(Context ctx, String msg, int duration) {
        if (msg != null && !msg.equalsIgnoreCase("")) {
            if (toast != null) {
                toast.cancel();
            }
            toast = Toast.makeText(ctx, msg, duration);
            toast.show();
        }
    }

    /**
     * 应用应用显示区域
     * <p/>
     * 注: 注意显示时机onWindowFocusChanged
     *
     * @param act
     * @return
     */
    public static Rect getAppRect(Activity act) {
        Rect rc = new Rect();
        act.getWindow().getDecorView().getWindowVisibleDisplayFrame(rc);

        return rc;
    }





    /**
     * view的显示和隐藏方法，在5.0版本有圆形动画
     *
     * @param view
     * @param visibility
     */
    public static void setViewVisbility(final View view, final int visibility) {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            int cx = view.getWidth() / 2;
            int cy = view.getHeight() / 2;
            int finalRadius = Math.max(view.getWidth(), view.getHeight());
            if (visibility == View.VISIBLE) {
                if (view.getVisibility() != View.VISIBLE) {
                    Animator animVisible = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);
                    view.setVisibility(visibility);
                    animVisible.start();
                }
            } else {
                if (view.getVisibility() == View.VISIBLE) {
                    Animator animInVisble = ViewAnimationUtils.createCircularReveal(view, cx, cy, finalRadius, 0);
                    animInVisble.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            view.setVisibility(visibility);
                        }
                    });
                    animInVisble.start();
                }
            }
        } else {
            view.setVisibility(visibility);
        }
    }

}
