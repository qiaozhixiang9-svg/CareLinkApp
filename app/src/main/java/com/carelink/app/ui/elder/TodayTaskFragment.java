package com.carelink.app.ui.elder;

import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
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
import com.carelink.app.data.local.pref.PreferenceManager;
import com.carelink.app.utils.FontScaleHelper;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TodayTaskFragment extends Fragment {
    private PreferenceManager preferenceManager;
    private TextView statusTag;
    private TextView infoView;
    private TextView assistView;
    private TextView stepView;
    private TextView countdownView;
    private TextView inviteHintView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        preferenceManager = new PreferenceManager(requireContext());
        int titleSize = FontScaleHelper.title(requireContext());
        int bodySize = FontScaleHelper.body(requireContext());

        ScrollView scrollView = new ScrollView(requireContext());
        LinearLayout root = new LinearLayout(requireContext());
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(32, 32, 32, 32);
        root.setBackgroundColor(0xFFF4F7FB);
        scrollView.addView(root);

        root.addView(createHeroCard(titleSize, bodySize));
        root.addView(createMapCard(bodySize));
        root.addView(createStatusCard(bodySize));
        root.addView(createActionSection(bodySize));
        root.addView(createAssistSection(bodySize));

        refreshShareViews();
        return scrollView;
    }

    private View createHeroCard(int titleSize, int bodySize) {
        LinearLayout hero = new LinearLayout(requireContext());
        hero.setOrientation(LinearLayout.VERTICAL);
        hero.setPadding(30, 30, 30, 30);
        hero.setBackground(createRoundedBg(0xFF2F80ED, 32));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = 20;
        hero.setLayoutParams(params);

        TextView eyebrow = new TextView(requireContext());
        eyebrow.setText("家庭守护 · 共享中心");
        eyebrow.setTextSize(FontScaleHelper.secondary(requireContext()));
        eyebrow.setTextColor(0xFFDCEBFF);
        hero.addView(eyebrow);

        TextView title = new TextView(requireContext());
        title.setText("位置共享");
        title.setTextSize(titleSize + 2);
        title.setTypeface(Typeface.DEFAULT_BOLD);
        title.setTextColor(0xFFFFFFFF);
        title.setPadding(0, 10, 0, 10);
        hero.addView(title);

        TextView desc = new TextView(requireContext());
        desc.setText("你可以在这里主动共享最近位置，让家属查看最近一次共享结果；也可以接收邀请码协助提示，按步骤完成操作。 ");
        desc.setTextSize(bodySize);
        desc.setTextColor(0xFFEAF4FF);
        hero.addView(desc);
        return hero;
    }

    private View createMapCard(int bodySize) {
        TextView mapBox = new TextView(requireContext());
        mapBox.setText("地图显示区域（现代框架）\n\n┌────────────────────┐\n│   共享位置地图主视图区域  │\n│   显示最近共享位置、状态   │\n│   后续可接真实地图与标记   │\n└────────────────────┘\n");
        mapBox.setTextSize(bodySize);
        mapBox.setPadding(30, 40, 30, 40);
        mapBox.setBackground(createRoundedBg(0xFFD9EAFE, 28));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = 18;
        mapBox.setLayoutParams(params);
        return mapBox;
    }

    private View createStatusCard(int bodySize) {
        LinearLayout card = new LinearLayout(requireContext());
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(24, 24, 24, 24);
        card.setBackground(createRoundedBg(0xFFFFFFFF, 26));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = 18;
        card.setLayoutParams(params);

        statusTag = new TextView(requireContext());
        statusTag.setTextSize(FontScaleHelper.secondary(requireContext()));
        statusTag.setPadding(20, 14, 20, 14);
        card.addView(statusTag);

        countdownView = new TextView(requireContext());
        countdownView.setTextSize(FontScaleHelper.secondary(requireContext()));
        countdownView.setPadding(0, 14, 0, 16);
        card.addView(countdownView);

        infoView = new TextView(requireContext());
        infoView.setTextSize(bodySize);
        card.addView(infoView);
        return card;
    }

    private View createActionSection(int bodySize) {
        LinearLayout section = new LinearLayout(requireContext());
        section.setOrientation(LinearLayout.VERTICAL);
        section.setPadding(0, 0, 0, 8);

        TextView title = new TextView(requireContext());
        title.setText("共享操作");
        title.setTextSize(bodySize + 2);
        title.setTypeface(Typeface.DEFAULT_BOLD);
        title.setPadding(0, 0, 0, 12);
        section.addView(title);

        LinearLayout row1 = new LinearLayout(requireContext());
        row1.setOrientation(LinearLayout.HORIZONTAL);
        row1.addView(createShareActionButton("更新当前位置", "单次共享", "家中 · 客厅附近", "本次共享完成后结束"));
        row1.addView(createShareActionButton("共享30分钟", "临时共享中", "社区活动中心", "剩余 30 分钟"));
        section.addView(row1);

        LinearLayout row2 = new LinearLayout(requireContext());
        row2.setOrientation(LinearLayout.HORIZONTAL);
        row2.addView(createShareActionButton("共享1小时", "临时共享中", "医院门诊楼", "剩余 1 小时"));
        row2.addView(createStopShareButton(bodySize));
        section.addView(row2);
        return section;
    }

    private View createAssistSection(int bodySize) {
        LinearLayout section = new LinearLayout(requireContext());
        section.setOrientation(LinearLayout.VERTICAL);
        section.setPadding(0, 20, 0, 0);

        TextView title = new TextView(requireContext());
        title.setText("邀请码协助");
        title.setTextSize(bodySize + 2);
        title.setTypeface(Typeface.DEFAULT_BOLD);
        title.setPadding(0, 0, 0, 12);
        section.addView(title);

        inviteHintView = new TextView(requireContext());
        inviteHintView.setText("当前协助会话：未接入邀请码\n后续当家属端输入邀请码后，这里会显示协助会话状态、请求来源和步骤提示。\n");
        inviteHintView.setTextSize(bodySize);
        inviteHintView.setPadding(24, 24, 24, 24);
        inviteHintView.setBackground(createRoundedBg(0xFFEAF7EE, 24));
        section.addView(inviteHintView);

        assistView = new TextView(requireContext());
        assistView.setTextSize(bodySize);
        assistView.setPadding(24, 24, 24, 24);
        assistView.setBackground(createRoundedBg(0xFFFFF3E0, 24));
        LinearLayout.LayoutParams assistParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        assistParams.topMargin = 14;
        assistView.setLayoutParams(assistParams);
        section.addView(assistView);

        stepView = new TextView(requireContext());
        stepView.setTextSize(FontScaleHelper.secondary(requireContext()));
        stepView.setPadding(24, 20, 24, 20);
        stepView.setBackground(createRoundedBg(0xFFFFFFFF, 24));
        LinearLayout.LayoutParams stepParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        stepParams.topMargin = 14;
        stepView.setLayoutParams(stepParams);
        section.addView(stepView);

        LinearLayout assistRow1 = new LinearLayout(requireContext());
        assistRow1.setOrientation(LinearLayout.HORIZONTAL);
        assistRow1.addView(createAssistButton("查看步骤", "家属建议：先确认你正在共享页，再按页面提示完成。", "步骤1：先看顶部共享状态。\n步骤2：点击需要的共享按钮。\n步骤3：观察最近位置和更新时间是否变化。\n步骤4：如不确定，再点语音提示。"));
        assistRow1.addView(createAssistButton("语音提示", "家属语音提示：不要着急，先看蓝色地图框，再看下面的位置卡片。", "语音步骤：\n1. 先听提示。\n2. 再看共享状态颜色。\n3. 按提示一步一步完成。"));
        section.addView(assistRow1);

        LinearLayout assistRow2 = new LinearLayout(requireContext());
        assistRow2.setOrientation(LinearLayout.HORIZONTAL);
        assistRow2.addView(createAssistButton("出门协助", "家属建议：出门前先点共享，再确认当前位置是否刷新。", "出门步骤：\n1. 先点“更新当前位置”。\n2. 再点 30 分钟或 1 小时共享。\n3. 看最近位置是否已变化。"));
        assistRow2.addView(createAssistButton("回家确认", "家属建议：到家后记得停止共享，保护隐私。", "回家步骤：\n1. 确认当前位置为家中。\n2. 点击停止共享。\n3. 状态变为“已结束”即完成。"));
        section.addView(assistRow2);
        return section;
    }

    private Button createShareActionButton(String text, String status, String location, String endText) {
        int baseFont = FontScaleHelper.body(requireContext());
        Button button = new Button(requireContext());
        button.setText(text);
        button.setTextSize(baseFont);
        button.setAllCaps(false);
        button.setBackground(createRoundedBg(0xFFFFFFFF, 22));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
        params.setMargins(8, 0, 8, 12);
        button.setLayoutParams(params);
        button.setOnClickListener(v -> {
            String now = new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(new Date());
            preferenceManager.saveShareStatus(status);
            preferenceManager.saveShareLastLocation(location);
            preferenceManager.saveShareLastTime(now);
            preferenceManager.saveShareEndTime(endText);
            refreshShareViews();
            Toast.makeText(requireContext(), text + " 已更新", Toast.LENGTH_SHORT).show();
        });
        return button;
    }

    private Button createStopShareButton(int baseFont) {
        Button button = new Button(requireContext());
        button.setText("停止共享");
        button.setTextSize(baseFont);
        button.setAllCaps(false);
        button.setBackground(createRoundedBg(0xFFFFEBEE, 22));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
        params.setMargins(8, 0, 8, 12);
        button.setLayoutParams(params);
        button.setOnClickListener(v -> {
            preferenceManager.saveShareStatus("已结束");
            preferenceManager.saveShareEndTime("已手动结束");
            refreshShareViews();
            Toast.makeText(requireContext(), "已停止共享", Toast.LENGTH_SHORT).show();
        });
        return button;
    }

    private Button createAssistButton(String text, String message, String steps) {
        int baseFont = FontScaleHelper.body(requireContext());
        Button button = new Button(requireContext());
        button.setText(text);
        button.setTextSize(baseFont);
        button.setAllCaps(false);
        button.setBackground(createRoundedBg(0xFFFFFFFF, 22));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
        params.setMargins(8, 0, 8, 12);
        button.setLayoutParams(params);
        button.setOnClickListener(v -> {
            assistView.setText(message);
            stepView.setText(steps);
            Toast.makeText(requireContext(), "已显示协助提示", Toast.LENGTH_SHORT).show();
        });
        return button;
    }

    private void refreshShareViews() {
        String status = preferenceManager.getShareStatus();
        String location = preferenceManager.getShareLastLocation();
        String time = preferenceManager.getShareLastTime();
        String endTime = preferenceManager.getShareEndTime();
        statusTag.setText("当前状态：" + status);
        if ("临时共享中".equals(status)) {
            statusTag.setBackground(createRoundedBg(0xFFFFF59D, 24));
        } else if ("单次共享".equals(status)) {
            statusTag.setBackground(createRoundedBg(0xFFBBDEFB, 24));
        } else if ("已结束".equals(status)) {
            statusTag.setBackground(createRoundedBg(0xFFFFCDD2, 24));
        } else {
            statusTag.setBackground(createRoundedBg(0xFFC8E6C9, 24));
        }
        countdownView.setText("状态提示：" + endTime);
        infoView.setText("最近位置：" + location + "\n最近更新时间：" + time + "\n共享结束：" + endTime + "\n共享模式：" + status + "\n当前说明：家属端将同步查看最近一次共享位置。\n");
        inviteHintView.setText("当前协助会话：未接入邀请码\n后续当家属端输入邀请码后，这里会显示协助会话状态、请求来源和步骤提示。\n");
        assistView.setText("当前未收到新的家属协助提示。\n你可以点击下方按钮查看步骤提示或语音提示示例。\n");
        stepView.setText("引导步骤将在这里显示。\n当前建议：先使用上方共享按钮，再观察状态变化。\n");
    }

    private GradientDrawable createRoundedBg(int color, int radius) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setCornerRadius(radius);
        return drawable;
    }
}
