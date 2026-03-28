package com.carelink.app.ui.alert;

import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
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
import com.carelink.app.utils.DemoDataHelper;

public class AlertCenterFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ScrollView scrollView = new ScrollView(requireContext());
        LinearLayout root = new LinearLayout(requireContext());
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(32, 32, 32, 32);
        root.setBackgroundColor(0xFFF6F8FC);
        scrollView.addView(root);

        TextView title = new TextView(requireContext());
        title.setText("异常中心");
        title.setTextSize(24);
        title.setTypeface(Typeface.DEFAULT_BOLD);
        title.setPadding(0, 0, 0, 20);
        root.addView(title);

        for (DemoDataHelper.AlertItemLocal item : DemoDataHelper.getAlerts()) {
            LinearLayout card = new LinearLayout(requireContext());
            card.setOrientation(LinearLayout.VERTICAL);
            card.setPadding(24, 24, 24, 24);
            card.setBackground(createRoundedBg(0xFFFFFFFF, 24));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.bottomMargin = 20;
            card.setLayoutParams(params);

            TextView level = new TextView(requireContext());
            level.setText("级别：" + item.level);
            level.setTextSize(15);
            level.setPadding(18, 10, 18, 10);
            level.setBackground(createRoundedBg(0xFFFFE0B2, 20));
            card.addView(level);

            TextView titleView = new TextView(requireContext());
            titleView.setText(item.title);
            titleView.setTextSize(20);
            titleView.setTypeface(Typeface.DEFAULT_BOLD);
            titleView.setPadding(0, 16, 0, 10);
            card.addView(titleView);

            TextView timeView = new TextView(requireContext());
            timeView.setText("发生时间：" + item.time + "\n建议：请尽快查看老人状态并决定是否联系。\n");
            timeView.setTextSize(15);
            card.addView(timeView);

            root.addView(card);
        }
        return scrollView;
    }

    private GradientDrawable createRoundedBg(int color, int radius) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setCornerRadius(radius);
        return drawable;
    }
}
