package com.wondertwo.app.application;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Allenieo on 2016/2/25.
 */
public class TopbarTestActivity extends Activity {

    private TopBar mTopbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 获得我们创建的TopBar对象
        mTopbar = (TopBar) findViewById(R.id.topBar);

        mTopbar.setOntopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                Toast.makeText(TopbarTestActivity.this, "left", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void rightClick() {
                Toast.makeText(TopbarTestActivity.this, "right", Toast.LENGTH_SHORT).show();
            }
        });

        // 设置按钮的显示状态
        mTopbar.setButtonVisable(0, true);
        mTopbar.setButtonVisable(1, true);

    }
}
