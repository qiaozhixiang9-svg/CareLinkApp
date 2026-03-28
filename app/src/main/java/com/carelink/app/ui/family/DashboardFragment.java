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

public class DashboardFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        PreferenceManager preferenceManager = new PreferenceManager(requireContext());
        LinearLayout root = new LinearLayout(requireContext());
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(24, 24, 24, 24);
        root.setBackgroundColor(0xFFF5F8FC);

        root.addView(createHeroCard("家属端总览", "当前项目已进入产品化重构阶段，家属端以共享、日历、异常和备注为核心。"));
        root.addView(createCard("共享中心", "状态：" + preferenceManager.getShareStatus() + "\n最近位置：" + preferenceManager.getShareLastLocation() + "\n下一步建议：在首页查看最近共享位置详情。"));
        root.addView(createCard("日历与提醒", "查看老人日程、家属新增安排与提醒状态。当前支持月视图和万年历入口。"));
        root.addView(createCard("异常与备注", "异常页用于查看重点事件，备注页用于记录家庭照护信息，后续可接真实后端。"));
        root.addView(createCard("邀请码协助", "当前为安全版协助框架：支持协助会话提示和步骤引导，不提供远程控制。"));
        return root;
    }

    private View createHeroCard(String titleText, String contentText) {
        LinearLayout card = new LinearLayout(requireContext());
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(28, 28, 28, 28);
        card.setBackground(createRoundedBg(0xFF2F80ED, 30));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = 18;
        card.setLayoutParams(params);

        TextView title = new TextView(requireContext());
        title.setText(titleText);
        title.setTextSize(24);
        title.setTypeface(Typeface.DEFAULT_BOLD);
        title.setTextColor(0xFFFFFFFF);
        card.addView(title);

        TextView content = new TextView(requireContext());
        content.setText(contentText);
        content.setTextSize(16);
        content.setTextColor(0xFFEAF4FF);
        content.setPadding(0, 12, 0, 0);
        card.addView(content);
        return card;
    }

    private View createCard(String titleText, String contentText) {
        LinearLayout card = new LinearLayout(requireContext());
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(24, 24, 24, 24);
        card.setBackground(createRoundedBg(0xFFFFFFFF, 24));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = 16;
        card.setLayoutParams(params);

        TextView title = new TextView(requireContext());
        title.setText(titleText);
        title.setTextSize(18);
        title.setTypeface(Typeface.DEFAULT_BOLD);
        card.addView(title);

        TextView content = new TextView(requireContext());
        content.setText(contentText);
        content.setTextSize(15);
        content.setPadding(0, 10, 0, 0);
        card.addView(content);
        return card;
    }

    private GradientDrawable createRoundedBg(int color, int radius) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setCornerRadius(radius);
        return drawable;
    }
}
