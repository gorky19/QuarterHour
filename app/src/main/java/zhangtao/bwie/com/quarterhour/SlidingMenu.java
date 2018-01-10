package zhangtao.bwie.com.quarterhour;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.nineoldandroids.view.ViewHelper;

/**
 * Created by ZhangTAO on 2018/1/10.
 */

public class SlidingMenu extends HorizontalScrollView{
    /**
     *  手机屏幕宽度
     */
    private int ScreenWidth;

    /**
     * rightPadding
     */
    private int MenuRightPadding = 28;
    /**
     * 菜单的宽度
     */
    private int MenuWidth;
    /**
     * 菜单的一半宽度
     */
    private int HalfMenuWidth;
    //开关
    private boolean once;
    private boolean isOpen;
    private ViewGroup menu;
    private ViewGroup contexts;
    private float scale;
    private float width = 670;
    private float trans;

    public SlidingMenu(Context context) {
        this(context,null);
    }
    public SlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public SlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        //屏幕宽度
        ScreenWidth = context.getResources().getDisplayMetrics().widthPixels;
        //定义属性
        TypedArray array = context.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.SlidingMenu, defStyleAttr, 0);
        int indexCount = array.getIndexCount();
        for(int i=0;i<indexCount;i++) {
            int index = array.getIndex(i);
            switch (index) {
                case R.styleable.SlidingMenu_rightPadding:
                    //默认35
                    array.getDimensionPixelSize(index, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP,25,getResources().getDisplayMetrics()
                    ));
                    break;
            }
        }
            array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         * 显示的设置一个高度
         */
        if(!once) {
            LinearLayout lin = (LinearLayout) getChildAt(0);
            menu = (ViewGroup) lin.getChildAt(0);
            contexts = (ViewGroup) lin.getChildAt(1);
            //设置菜单RightPadding
            MenuRightPadding = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,MenuRightPadding,contexts.getResources().getDisplayMetrics()
            );
            //菜单宽度
            MenuWidth = ScreenWidth - MenuRightPadding;
            HalfMenuWidth = MenuWidth / 2;
            menu.getLayoutParams().width = MenuWidth;
            contexts.getLayoutParams().width = ScreenWidth;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if(changed) {
            //将菜单隐藏
            scrollTo(MenuWidth,0);
        }
    }
    /**
     * 打开菜单
     */
    public void openMenu() {
            smoothScrollTo(MenuWidth, 0);
            isOpen = false;
    }
    /**
     * 关闭菜单
     */
    public void closeMenu() {
            smoothScrollTo(0, 0);
            isOpen = true;
    }
    /**
     * 切换菜单状态
     */
    public void toggle() {
        if(isOpen) {
            openMenu();
        }else {
            closeMenu();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            // Up时，进行判断,如果显示区域大于菜单宽度一半则完全显示，否则隐藏
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                if(scrollX > HalfMenuWidth) {
                     openMenu();
                }else {
                     closeMenu();
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        //菜单以显示的宽度
        scale = l * 1.0f / MenuWidth;
        trans = width * scale;
        ViewHelper.setTranslationX(menu, trans);
    }
}
