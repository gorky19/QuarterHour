package zhangtao.bwie.com.quarterhour;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private SlidingMenu sliding;
    private TextView toggle_back;
    private GradientDrawable toggle_draw_back;
    private TextView toggle_shape;
    private RelativeLayout toggle_button;
    private boolean flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    protected void moreAction() {

    }
    @Override
    public void getData() {

    }
    @Override
    protected void setListener() {
        toggle_button.setOnClickListener(this);
    }
    @Override
    public void initView() {
        sliding = (SlidingMenu) findViewById(R.id.menu_sliding);
        toggle_back = (TextView) findViewById(R.id.toggle_button_back);
        toggle_shape = (TextView) findViewById(R.id.toggle_btn_shape);
        toggle_button = (RelativeLayout) findViewById(R.id.toggle_button);
        toggle_button = (RelativeLayout) findViewById(R.id.toggle_button);
        toggle_draw_back = (GradientDrawable) toggle_back.getBackground();
//        toggle_draw_back.setColor(Color.RED);
    }
    @Override
    public void setView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toggle_button:
                if(flag) {

                }
                break;
        }
    }
}
