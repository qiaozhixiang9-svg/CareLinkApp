package com.carelink.app.ui.settings;

import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.carelink.app.data.local.pref.PreferenceManager;
import com.carelink.app.utils.FontScaleHelper;

public class SettingsActivity extends AppCompatActivity {
    private PreferenceManager preferenceManager;
    private LinearLayout content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferenceManager = new PreferenceManager(this);
        ScrollView scrollView = new ScrollView(this);
        content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setPadding(32, 32, 32, 32);
        content.setBackgroundColor(0xFFF6F8FC);
        scrollView.addView(content);
        setContentView(scrollView);
        render();
    }

    private void render() {
        content.removeAllViews();
        content.addView(heroTitle());
        content.addView(sectionCard("显示与字体",
                info("当前字体大小: " + preferenceManager.getFontSize()),
                action("设置字体大小", v -> showFontSizeDialog()),
                info("默认日历视图: " + preferenceManager.getDefaultCalendarView()),
                action("切换默认日历视图", v -> toggleCalendarView())));

        content.addView(sectionCard("语音与播报",
                info("语音播报: " + (preferenceManager.isVoiceEnabled() ? "已开启" : "已关闭")),
                action("切换语音播报", v -> toggleVoice()),
                info("提醒开关: " + (preferenceManager.isReminderEnabled() ? "已开启" : "已关闭")),
                action("切换提醒开关", v -> toggleReminder())));

        content.addView(sectionCard("家庭与安全",
                info("紧急联系人: " + preferenceManager.getEmergencyContact()),
                action("设置紧急联系人", v -> showEmergencyContactDialog()),
                info("身份切换: 当前不受时间限制")));

        content.addView(sectionCard("乐曲与戏曲",
                info("进入页面自动播放: " + (preferenceManager.isAudioAutoplay() ? "已开启" : "已关闭")),
                action("切换自动播放", v -> toggleAudioAutoplay())));

        content.addView(sectionCard("关于应用",
                info("当前版本: 产品化可落地版框架"),
                info("后续真实能力可在当前结构上继续接入。")));
    }

    private View heroTitle() {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(28, 28, 28, 28);
        card.setBackground(createRoundedBg(0xFF2F80ED, 30));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = 18;
        card.setLayoutParams(params);

        TextView title = new TextView(this);
        title.setText("设置");
        title.setTextSize(FontScaleHelper.title(this));
        title.setTypeface(Typeface.DEFAULT_BOLD);
        title.setTextColor(0xFFFFFFFF);
        card.addView(title);

        TextView desc = new TextView(this);
        desc.setText("统一管理显示、语音、提醒、家庭与安全等设置。当前页面已按产品化结构收口。\n");
        desc.setTextSize(FontScaleHelper.body(this));
        desc.setTextColor(0xFFEAF4FF);
        desc.setPadding(0, 10, 0, 0);
        card.addView(desc);
        return card;
    }

    private View sectionCard(String titleText, View... children) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(24, 24, 24, 24);
        card.setBackground(createRoundedBg(0xFFFFFFFF, 24));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = 16;
        card.setLayoutParams(params);

        TextView title = new TextView(this);
        title.setText(titleText);
        title.setTextSize(FontScaleHelper.sectionTitle(this));
        title.setTypeface(Typeface.DEFAULT_BOLD);
        card.addView(title);

        for (View child : children) card.addView(child);
        return card;
    }

    private TextView info(String text) {
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setTextSize(FontScaleHelper.body(this));
        tv.setPadding(0, 12, 0, 10);
        return tv;
    }

    private Button action(String text, android.view.View.OnClickListener listener) {
        Button btn = new Button(this);
        btn.setText(text);
        btn.setTextSize(FontScaleHelper.body(this));
        btn.setAllCaps(false);
        btn.setBackground(createRoundedBg(0xFFEAF4FF, 20));
        btn.setOnClickListener(listener);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = 8;
        btn.setLayoutParams(params);
        return btn;
    }

    private void toggleVoice() {
        boolean value = !preferenceManager.isVoiceEnabled();
        preferenceManager.setVoiceEnabled(value);
        Toast.makeText(this, value ? "语音播报已开启" : "语音播报已关闭", Toast.LENGTH_SHORT).show();
        render();
    }

    private void toggleReminder() {
        boolean value = !preferenceManager.isReminderEnabled();
        preferenceManager.setReminderEnabled(value);
        Toast.makeText(this, value ? "提醒已开启" : "提醒已关闭", Toast.LENGTH_SHORT).show();
        render();
    }

    private void toggleCalendarView() {
        String next = "月视图".equals(preferenceManager.getDefaultCalendarView()) ? "列表视图" : "月视图";
        preferenceManager.saveDefaultCalendarView(next);
        Toast.makeText(this, "默认日历视图已切换为" + next, Toast.LENGTH_SHORT).show();
        render();
    }

    private void toggleAudioAutoplay() {
        boolean value = !preferenceManager.isAudioAutoplay();
        preferenceManager.setAudioAutoplay(value);
        Toast.makeText(this, value ? "自动播放已开启" : "自动播放已关闭", Toast.LENGTH_SHORT).show();
        render();
    }

    private void showEmergencyContactDialog() {
        EditText editText = new EditText(this);
        editText.setHint("请输入紧急联系人电话");
        editText.setText(preferenceManager.getEmergencyContact());
        new AlertDialog.Builder(this)
                .setTitle("设置紧急联系人")
                .setView(editText)
                .setPositiveButton("保存", (dialog, which) -> {
                    String phone = editText.getText().toString().trim();
                    if (!phone.isEmpty()) {
                        preferenceManager.saveEmergencyContact(phone);
                        Toast.makeText(this, "紧急联系人已更新", Toast.LENGTH_SHORT).show();
                        render();
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    private void showFontSizeDialog() {
        String[] sizes = {"标准", "偏大", "超大"};
        int current = preferenceManager.getFontSize();
        int checked = current >= 24 ? 2 : current >= 20 ? 1 : 0;
        new AlertDialog.Builder(this)
                .setTitle("设置字体大小")
                .setSingleChoiceItems(sizes, checked, null)
                .setPositiveButton("应用", (dialog, which) -> {
                    AlertDialog ad = (AlertDialog) dialog;
                    int index = ad.getListView().getCheckedItemPosition();
                    int size = index == 2 ? 24 : index == 1 ? 20 : 18;
                    preferenceManager.saveFontSize(size);
                    Toast.makeText(this, "字体大小已更新", Toast.LENGTH_SHORT).show();
                    render();
                })
                .setNegativeButton("取消", null)
                .show();
    }

    private GradientDrawable createRoundedBg(int color, int radius) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setCornerRadius(radius);
        return drawable;
    }
}
