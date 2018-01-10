package zhangtao.bwie.com.quarterhour;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.zhy.autolayout.AutoLayoutActivity;

import ImmerSionUtil.ImmersionUtil;

/**
 * Created by Ning.A.C on 2018/1/10.
 */

public abstract class BaseActivity extends AutoLayoutActivity{


    private SystemBarTintManager tintManager;
    private int colors = R.color.statusbar_bg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
        setColor();
        initView();
        setListener();
        getData();
        moreAction();
    }

    protected abstract void moreAction();

    public abstract void getData();
    protected abstract void setListener();

    public abstract void initView();

    public void setColor(){
        new ImmersionUtil().setImmersion(getWindow(),getSupportActionBar());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        SharedPreferences data = getSharedPreferences("data", MODE_PRIVATE);
        tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        int color = data.getInt("color", colors);
        if(color != colors) {
            setBgColor(color);
        }else {
            setBgColor(colors);
        }
    }
    public void setBgColor(int color){
        tintManager.setStatusBarTintResource(color);
    }
    public abstract void setView();
    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


}
