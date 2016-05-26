package com.yjbo.testswipeback;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/***
 * 测试滑到关闭activity
 *
 * @author yjbo
 *         2016年5月26日17:04:37
 */
//摘要：关于SwipeBackActivity，主要注意以下几种情况：
// 1.引入 compile 'me.imid.swipebacklayout.lib:library:1.0.0'
// 2.将app的主题设置加入 <item name="android:windowIsTranslucent">true</item>
//      --这样主题就可以使透明的，不能用默认的主题；使用默认背景就是黑屏了，这个属性就是设置防止黑屏的。
//      关于阻止黑屏可查看：http://blog.csdn.net/fancylovejava/article/details/39643449
// 3.这个侧滑关闭页面的方法，同时这个三方的包的出处地址是：https://github.com/ikew0ng/SwipeBackLayout（里面有完整项目），感谢
// 4.滑动关闭有4种形式，在下面已经列举了
// 5.这里面已经通过监听滑动的手势来设置震动效果了：vibrate方法，（记住在配置文件中得申明震动的权限）
// 6.已经就遇到了，今天终于不是仅仅看人家的代码了，自己把代码写出来真的很好，印象也加深了。

public class MainActivity extends SwipeBackActivity {

    private SwipeBackLayout mSwipeBackLayout;
    private static final int VIBRATE_DURATION = 20;
    /***
     * 滑动关闭当前页面的方向有四种形式
     *  1.SwipeBackLayout.EDGE_RIGHT 右边关闭页面
     *  2.SwipeBackLayout.EDGE_LEFT  左边关闭页面
     *  3.SwipeBackLayout.EDGE_BOTTOM 底部关闭页面
     *  4.SwipeBackLayout.EDGE_ALL   以上三种形式关闭页面
     */
    int edgeFlag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.other_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OtherActivity.class));
            }
        });
        setSwipeBackEnable(true);
        mSwipeBackLayout = getSwipeBackLayout();


        edgeFlag = SwipeBackLayout.EDGE_RIGHT;
        //edgeFlag = SwipeBackLayout.EDGE_LEFT;
        //edgeFlag = SwipeBackLayout.EDGE_BOTTOM;
        //edgeFlag = SwipeBackLayout.EDGE_ALL;
        mSwipeBackLayout.setEdgeTrackingEnabled(edgeFlag);
        //震动效果
        mSwipeBackLayout.addSwipeListener(new SwipeBackLayout.SwipeListener() {
            @Override
            public void onScrollStateChange(int state, float scrollPercent) {
            }

            @Override
            public void onEdgeTouch(int edgeFlag) {
                vibrate(VIBRATE_DURATION);
            }

            @Override
            public void onScrollOverThreshold() {
                vibrate(VIBRATE_DURATION);
            }
        });
    }

    /***
     * 震动效果
     * @param duration
     */
    private void vibrate(long duration) {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = {0, duration};
        vibrator.vibrate(pattern, -1);
    }
}
