package com.yjbo.testswipeback;

import android.os.Bundle;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/***
 * 这是最简单的方法
 * @author  yjbo 2016年5月26日18:20:38
 */
public class OtherActivity extends SwipeBackActivity {

    private SwipeBackLayout mSwipeBackLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        setSwipeBackEnable(true);
        mSwipeBackLayout = getSwipeBackLayout();

        int edgeFlag;
        edgeFlag = SwipeBackLayout.EDGE_LEFT;
        mSwipeBackLayout.setEdgeTrackingEnabled(edgeFlag);
    }
}
