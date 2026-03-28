package com.carelink.app.ui.family;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/** 家属端成员管理页面 */
public class MemberFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ScrollView scrollView = new ScrollView(requireContext());
        LinearLayout root = new LinearLayout(requireContext());
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(24, 24, 24, 24);
        scrollView.addView(root);

        TextView title = new TextView(requireContext());
        title.setText("家庭成员");
        title.setTextSize(24);
        title.setPadding(0, 0, 0, 20);
        root.addView(title);

        root.addView(createCard("老人", "王奶奶", "状态：今天已完成2项打卡"));
        root.addView(createCard("家属", "大儿子", "今日负责：晚间视频联系"));
        root.addView(createCard("家属", "二女儿", "今日负责：提醒复诊资料准备"));

        return scrollView;
    }

    private View createCard(String role, String name, String info) {
        LinearLayout card = new LinearLayout(requireContext());
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(24, 20, 24, 20);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = 16;
        card.setLayoutParams(params);
        card.setBackgroundColor(0xFFFFFFFF);

        TextView tvRole = new TextView(requireContext());
        tvRole.setText(role);
        tvRole.setTextSize(14);
        tvRole.setTextColor(0xFF888888);
        card.addView(tvRole);

        TextView tvName = new TextView(requireContext());
        tvName.setText(name);
        tvName.setTextSize(20);
        tvName.setPadding(0, 8, 0, 8);
        card.addView(tvName);

        TextView tvInfo = new TextView(requireContext());
        tvInfo.setText(info);
        tvInfo.setTextSize(15);
        card.addView(tvInfo);

        return card;
    }
}
