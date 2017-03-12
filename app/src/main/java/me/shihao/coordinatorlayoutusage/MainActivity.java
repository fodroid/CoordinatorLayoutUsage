package me.shihao.coordinatorlayoutusage;

import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import me.shihao.library.XStatusBarHelper;

public class MainActivity extends AppCompatActivity {

    private TextView tvTitle;
    private TextView tvTitleNick;
    private ImageButton ibtnTitleIco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        XStatusBarHelper.immersiveStatusBar(this);
        XStatusBarHelper.setHeightAndPadding(this, toolbar);

        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleNick = (TextView) findViewById(R.id.tv_title_nick);
        ibtnTitleIco = (ImageButton) findViewById(R.id.ibtn_title_ico);
        AppBarLayout appBar = (AppBarLayout) findViewById(R.id.app_bar);
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float total = appBarLayout.getTotalScrollRange() * 1.0f;
                //计算出滑动百分比
                float p = Math.abs(verticalOffset) / total;
                if (p > 0.5) {
                    tvTitle.setAlpha(1.0f / 0.5f * (p - 0.5f));
                    tvTitleNick.setAlpha(0);
                } else {
                    tvTitle.setAlpha(0);
                    tvTitleNick.setAlpha(1.0f - 1.0f / 0.5f * p);
                }
                ibtnTitleIco.setVisibility(p == 1 ? View.VISIBLE : View.INVISIBLE);
            }
        });
    }
}
