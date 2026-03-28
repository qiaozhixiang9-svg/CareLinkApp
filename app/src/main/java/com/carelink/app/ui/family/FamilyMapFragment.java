package com.carelink.app.ui.family;

import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.carelink.app.data.local.pref.PreferenceManager;

public class FamilyMapFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        PreferenceManager preferenceManager = new PreferenceManager(requireContext());
        String status = preferenceManager.getShareStatus();
        String location = preferenceManager.getShareLastLocation();
        String time = preferenceManager.getShareLastTime();
        String endTime = preferenceManager.getShareEndTime();

        LinearLayout root = new LinearLayout(requireContext());
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(24, 24, 24, 24);
        root.setBackgroundColor(0xFFF4F8FF);

        root.addView(createHeroCard(status, location));
        root.addView(createMapCard());
        root.addView(createInfoCard(preferenceManager, status, location, time, endTime));
        root.addView(createTipCard());
        return root;
    }

    private View createHeroCard(String status, String location) {
        LinearLayout card = new LinearLayout(requireContext());
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(28, 28, 28, 28);
        card.setBackground(createRoundedBg(0xFF2F80ED, 30));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = 16;
        card.setLayoutParams(params);

        TextView title = new TextView(requireContext());
        title.setText("最近共享位置");
        title.setTextSize(24);
        title.setTypeface(Typeface.DEFAULT_BOLD);
        title.setTextColor(0xFFFFFFFF);
        card.addView(title);

        TextView content = new TextView(requireContext());
        content.setText("当前状态：" + status + "\n最近位置：" + location);
        content.setTextSize(16);
        content.setTextColor(0xFFEAF4FF);
        content.setPadding(0, 12, 0, 0);
        card.addView(content);
        return card;
    }

    private View createMapCard() {
        TextView mapPlaceholder = new TextView(requireContext());
        mapPlaceholder.setText("地图查看区域（产品框架层）\n\n┌────────────────────┐\n│   最近一次共享位置地图   │\n│   后续可接真实地图与标记 │\n│   当前先展示产品层结构   │\n└────────────────────┘\n");
        mapPlaceholder.setTextSize(16);
        mapPlaceholder.setPadding(24, 32, 24, 32);
        mapPlaceholder.setBackground(createRoundedBg(0xFFDCEBFF, 28));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f);
        params.bottomMargin = 16;
        mapPlaceholder.setLayoutParams(params);
        return mapPlaceholder;
    }

    private View createInfoCard(PreferenceManager preferenceManager, String status, String location, String time, String endTime) {
        TextView info = new TextView(requireContext());
        info.setText("老人姓名：" + (preferenceManager.getNickname().isEmpty() ? "家庭老人" : preferenceManager.getNickname())
                + "\n最近位置：" + location
                + "\n更新时间：" + time
                + "\n共享模式：" + status
                + "\n结束时间：" + endTime
                + "\n头像：沿用“我的”中头像");
        info.setTextSize(16);
        info.setPadding(20, 20, 20, 20);
        info.setBackground(createRoundedBg(0xFFFFFFFF, 24));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = 16;
        info.setLayoutParams(params);
        return info;
    }

    private View createTipCard() {
        TextView tip = new TextView(requireContext());
        tip.setText("产品说明：家属端查看的是老人主动共享的最近位置结果。当前为可落地项目的产品框架层，后续可按合规方式接真实位置数据。\n");
        tip.setTextSize(14);
        tip.setPadding(22, 22, 22, 22);
        tip.setBackground(createRoundedBg(0xFFFFFFFF, 22));
        return tip;
    }

    private GradientDrawable createRoundedBg(int color, int radius) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setCornerRadius(radius);
        return drawable;
    }
}
