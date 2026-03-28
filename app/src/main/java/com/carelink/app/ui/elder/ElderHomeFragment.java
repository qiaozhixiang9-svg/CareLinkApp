package com.carelink.app.ui.elder;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
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
import com.carelink.app.ui.map.MapPreviewActivity;
import com.carelink.app.utils.DemoDataHelper;
import com.carelink.app.utils.FontScaleHelper;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ElderHomeFragment extends Fragment {
    private PreferenceManager preferenceManager;
    private TextView shareStatusTag;
    private TextView shareContent;
    private TextView shareFooter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        int baseFont = FontScaleHelper.body(requireContext());
        preferenceManager = new PreferenceManager(requireContext());

        ScrollView scrollView = new ScrollView(requireContext());
        LinearLayout root = new LinearLayout(requireContext());
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(30, 30, 30, 30);
        root.setBackgroundColor(0xFFF5F8FF);
        scrollView.addView(root);

        LinearLayout hero = new LinearLayout(requireContext());
        hero.setOrientation(LinearLayout.VERTICAL);
        hero.setPadding(32, 32, 32, 32);
        hero.setBackgroundColor(0xFF4A90E2);
        LinearLayout.LayoutParams heroParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        heroParams.bottomMargin = 24;
        hero.setLayoutParams(heroParams);

        TextView greeting = new TextView(requireContext());
        greeting.setText(DemoDataHelper.getGreeting());
        greeting.setTextSize(baseFont + 10);
        greeting.setTextColor(0xFFFFFFFF);
        greeting.setPadding(0, 0, 0, 10);
        hero.addView(greeting);

        TextView date = new TextView(requireContext());
        date.setText(DemoDataHelper.getTodayDate());
        date.setTextSize(baseFont + 2);
        date.setTextColor(0xFFEAF4FF);
        hero.addView(date);
        root.addView(hero);

        root.addView(createShareCard(baseFont));

        LinearLayout quickActions = new LinearLayout(requireContext());
        quickActions.setOrientation(LinearLayout.HORIZONTAL);
        quickActions.setGravity(Gravity.CENTER);
        quickActions.setPadding(0, 0, 0, 24);
        quickActions.addView(createActionButton("报平安", "已通知家属你状态良好", baseFont));
        quickActions.addView(createActionButton("喝水提醒", "请记得补充一杯温水", baseFont));
        root.addView(quickActions);

        root.addView(createCard("今日完成情况", "已完成 2/4 项任务，继续保持", 0xFFFFFFFF, baseFont));
        root.addView(createCard("健康提醒", "下午记得散步 15 分钟，并在晚间测量血压", 0xFFFFFFFF, baseFont));
        root.addView(createCard("家属关怀", "今天家属已查看你的状态 2 次，晚上 19:00 视频联系", 0xFFFFFFFF, baseFont));
        root.addView(createCard("紧急帮助", "如感觉不适，可在底部点击“紧急”进行快速求助", 0xFFFFF3E0, baseFont));

        Button musicButton = new Button(requireContext());
        musicButton.setText("打开乐曲与戏曲");
        musicButton.setTextSize(baseFont);
        musicButton.setAllCaps(false);
        musicButton.setOnClickListener(v -> requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(com.carelink.app.R.id.elder_content_container, new AudioLibraryFragment(), "audio")
                .commitAllowingStateLoss());
        root.addView(musicButton);

        Button mapTestButton = new Button(requireContext());
        mapTestButton.setText("打开地图测试");
        mapTestButton.setTextSize(baseFont);
        mapTestButton.setAllCaps(false);
        mapTestButton.setOnClickListener(v ->
                startActivity(new Intent(requireContext(), MapPreviewActivity.class))
        );
        root.addView(mapTestButton);

        refreshShareViews();
        return scrollView;
    }

    private View createShareCard(int baseFont) {
        LinearLayout card = new LinearLayout(requireContext());
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(28, 24, 28, 24);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = 20;
        card.setLayoutParams(params);
        card.setBackgroundColor(0xFFE8F5E9);

        TextView title = new TextView(requireContext());
        title.setText("位置共享");
        title.setTextSize(baseFont + 4);
        title.setPadding(0, 0, 0, 10);
        card.addView(title);

        shareStatusTag = new TextView(requireContext());
        shareStatusTag.setTextSize(FontScaleHelper.secondary(requireContext()));
        shareStatusTag.setPadding(18, 12, 18, 12);
        card.addView(shareStatusTag);

        shareContent = new TextView(requireContext());
        shareContent.setTextSize(baseFont);
        shareContent.setPadding(0, 18, 0, 18);
        card.addView(shareContent);

        TextView shareDesc = new TextView(requireContext());
        shareDesc.setText("说明：仅在你主动操作时共享位置，家属端只查看最近一次共享结果，不做持续追踪。\n临时共享可选 30 分钟或 1 小时，到时自动结束。\n");
        shareDesc.setTextSize(FontScaleHelper.secondary(requireContext()));
        shareDesc.setPadding(0, 0, 0, 18);
        card.addView(shareDesc);

        LinearLayout row1 = new LinearLayout(requireContext());
        row1.setOrientation(LinearLayout.HORIZONTAL);
        row1.addView(createShareActionButton("更新当前位置", "单次共享", "家中 · 客厅附近"));
        row1.addView(createShareActionButton("共享30分钟", "临时共享中", "社区活动中心"));
        card.addView(row1);

        LinearLayout row2 = new LinearLayout(requireContext());
        row2.setOrientation(LinearLayout.HORIZONTAL);
        row2.addView(createShareActionButton("共享1小时", "临时共享中", "医院门诊楼"));
        row2.addView(createStopShareButton(baseFont));
        card.addView(row2);

        shareFooter = new TextView(requireContext());
        shareFooter.setTextSize(FontScaleHelper.secondary(requireContext()));
        shareFooter.setPadding(0, 16, 0, 0);
        card.addView(shareFooter);
        return card;
    }

    private Button createShareActionButton(String text, String status, String location) {
        int baseFont = FontScaleHelper.body(requireContext());
        Button button = new Button(requireContext());
        button.setText(text);
        button.setTextSize(baseFont);
        button.setAllCaps(false);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
        params.setMargins(8, 0, 8, 0);
        button.setLayoutParams(params);
        button.setOnClickListener(v -> {
            String now = new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(new Date());
            preferenceManager.saveShareStatus(status);
            preferenceManager.saveShareLastLocation(location);
            preferenceManager.saveShareLastTime(now);
            if ("临时共享中".equals(status)) {
                preferenceManager.saveShareEndTime(text.contains("30") ? "30分钟后自动结束" : "1小时后自动结束");
            } else {
                preferenceManager.saveShareEndTime("本次共享完成后结束");
            }
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
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
        params.setMargins(8, 0, 8, 0);
        button.setLayoutParams(params);
        button.setOnClickListener(v -> {
            preferenceManager.saveShareStatus("已结束");
            preferenceManager.saveShareEndTime("已手动结束");
            refreshShareViews();
            Toast.makeText(requireContext(), "已停止共享", Toast.LENGTH_SHORT).show();
        });
        return button;
    }

    private void refreshShareViews() {
        String status = preferenceManager.getShareStatus();
        String location = preferenceManager.getShareLastLocation();
        String time = preferenceManager.getShareLastTime();
        String endTime = preferenceManager.getShareEndTime();
        shareStatusTag.setText("当前状态：" + status);
        if ("临时共享中".equals(status)) {
            shareStatusTag.setBackgroundColor(0xFFFFF59D);
        } else if ("单次共享".equals(status)) {
            shareStatusTag.setBackgroundColor(0xFFBBDEFB);
        } else if ("已结束".equals(status)) {
            shareStatusTag.setBackgroundColor(0xFFFFCDD2);
        } else {
            shareStatusTag.setBackgroundColor(0xFFC8E6C9);
        }
        shareContent.setText("最近位置：" + location + "\n最近更新时间：" + time + "\n共享结束：" + endTime + "\n紧急联系人：" + preferenceManager.getEmergencyContact());
        shareFooter.setText("状态说明：未共享 / 单次共享 / 临时共享中 / 已结束");
    }

    private View createActionButton(String text, String message, int baseFont) {
        Button button = new Button(requireContext());
        button.setText(text);
        button.setTextSize(baseFont);
        button.setAllCaps(false);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
        params.setMargins(8, 0, 8, 0);
        button.setLayoutParams(params);
        button.setOnClickListener(v -> Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show());
        return button;
    }

    private View createCard(String title, String content, int bgColor, int baseFont) {
        LinearLayout card = new LinearLayout(requireContext());
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(28, 24, 28, 24);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = 20;
        card.setLayoutParams(params);
        card.setBackgroundColor(bgColor);

        TextView tvTitle = new TextView(requireContext());
        tvTitle.setText(title);
        tvTitle.setTextSize(baseFont + 4);
        tvTitle.setTextColor(0xFF222222);
        tvTitle.setPadding(0, 0, 0, 10);
        card.addView(tvTitle);

        TextView tvContent = new TextView(requireContext());
        tvContent.setText(content);
        tvContent.setTextSize(baseFont);
        tvContent.setTextColor(0xFF555555);
        card.addView(tvContent);

        return card;
    }
}
