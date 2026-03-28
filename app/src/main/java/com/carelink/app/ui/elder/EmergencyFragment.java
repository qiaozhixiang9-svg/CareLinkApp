package com.carelink.app.ui.elder;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.carelink.app.utils.FontScaleHelper;

public class EmergencyFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int titleSize = FontScaleHelper.title(requireContext());
        int bodySize = FontScaleHelper.body(requireContext());

        ScrollView scrollView = new ScrollView(requireContext());
        LinearLayout root = new LinearLayout(requireContext());
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(32, 32, 32, 32);
        root.setBackgroundColor(0xFFF7F9FC);
        scrollView.addView(root);

        LinearLayout hero = new LinearLayout(requireContext());
        hero.setOrientation(LinearLayout.VERTICAL);
        hero.setPadding(28, 28, 28, 28);
        hero.setBackground(createRoundedBg(0xFFFF6B6B, 30));
        LinearLayout.LayoutParams heroParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        heroParams.bottomMargin = 18;
        hero.setLayoutParams(heroParams);

        TextView title = new TextView(requireContext());
        title.setText("紧急求助");
        title.setTextSize(titleSize);
        title.setTypeface(Typeface.DEFAULT_BOLD);
        title.setTextColor(0xFFFFFFFF);
        hero.addView(title);

        TextView desc = new TextView(requireContext());
        desc.setText("当你身体不适、外出迷路或需要家属协助时，可在这里快速发起求助。\n长按红色按钮拨打急救电话，点击下方按钮通知家属。\n");
        desc.setTextSize(bodySize);
        desc.setTextColor(0xFFFFF1F1);
        desc.setPadding(0, 10, 0, 0);
        hero.addView(desc);
        root.addView(hero);

        Button emergencyBtn = new Button(requireContext());
        emergencyBtn.setText("SOS\n长按求助");
        emergencyBtn.setTextSize(titleSize - 2);
        emergencyBtn.setTypeface(Typeface.DEFAULT_BOLD);
        emergencyBtn.setAllCaps(false);
        emergencyBtn.setBackground(createRoundedBg(0xFFFF6B6B, 36));
        LinearLayout.LayoutParams emergencyParams = new LinearLayout.LayoutParams(260, 260);
        emergencyParams.bottomMargin = 20;
        emergencyParams.gravity = android.view.Gravity.CENTER_HORIZONTAL;
        emergencyBtn.setLayoutParams(emergencyParams);
        root.addView(emergencyBtn);

        Button assistBtn = new Button(requireContext());
        assistBtn.setText("通知家属协助");
        assistBtn.setTextSize(bodySize);
        assistBtn.setAllCaps(false);
        assistBtn.setBackground(createRoundedBg(0xFFFFFFFF, 22));
        LinearLayout.LayoutParams assistParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        assistParams.bottomMargin = 18;
        root.addView(assistBtn, assistParams);

        root.addView(createTipCard("立即呼救", "长按红色按钮可直接拨打 120。"));
        root.addView(createTipCard("通知家属", "点击“通知家属协助”可向家属端发送协助提示。"));
        root.addView(createTipCard("产品说明", "当前为可落地版框架，后续可接真实通知和协助服务。"));

        emergencyBtn.setOnLongClickListener(v -> {
            v.performHapticFeedback(android.view.HapticFeedbackConstants.LONG_PRESS);
            Toast.makeText(requireContext(), "正在准备拨打急救电话...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:120"));
            startActivity(intent);
            return true;
        });

        assistBtn.setOnClickListener(v -> Toast.makeText(requireContext(), "已向家属端发送协助提示", Toast.LENGTH_SHORT).show());
        return scrollView;
    }

    private View createTipCard(String titleText, String contentText) {
        LinearLayout card = new LinearLayout(requireContext());
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(20, 20, 20, 20);
        card.setBackground(createRoundedBg(0xFFFFFFFF, 20));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = 14;
        card.setLayoutParams(params);

        TextView title = new TextView(requireContext());
        title.setText(titleText);
        title.setTextSize(FontScaleHelper.sectionTitle(requireContext()));
        title.setTypeface(Typeface.DEFAULT_BOLD);
        card.addView(title);

        TextView content = new TextView(requireContext());
        content.setText(contentText);
        content.setTextSize(FontScaleHelper.body(requireContext()));
        content.setPadding(0, 8, 0, 0);
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
