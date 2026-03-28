package com.carelink.app.ui.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.carelink.app.R;
import com.carelink.app.data.local.pref.PreferenceManager;
import com.carelink.app.ui.auth.LoginActivity;
import com.carelink.app.ui.auth.RoleSelectActivity;
import com.carelink.app.ui.settings.SettingsActivity;
import com.carelink.app.utils.FontScaleHelper;

/** 我的页面（极简稳定版） */
public class MyProfileFragment extends Fragment {

    private PreferenceManager preferenceManager;
    private ImageView avatarView;
    private TextView tvNickname;
    private TextView tvFamilyInfo;

    private final ActivityResultLauncher<String> avatarPickerLauncher =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                if (uri != null) {
                    preferenceManager.saveAvatarUrl(uri.toString());
                    safeSetAvatar(uri.toString());
                    Toast.makeText(requireContext(), "头像已更新", Toast.LENGTH_SHORT).show();
                }
            });

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        preferenceManager = new PreferenceManager(requireContext());
        int titleSize = FontScaleHelper.title(requireContext());
        int bodySize = FontScaleHelper.body(requireContext());

        ScrollView scrollView = new ScrollView(requireContext());
        scrollView.setFillViewport(true);

        LinearLayout root = new LinearLayout(requireContext());
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(24, 24, 24, 24);
        root.setBackgroundColor(0xFFF7F9FC);
        scrollView.addView(root);

        TextView title = new TextView(requireContext());
        title.setText("我的");
        title.setTextSize(titleSize);
        title.setPadding(0, 0, 0, 20);
        root.addView(title);

        LinearLayout card = new LinearLayout(requireContext());
        card.setOrientation(LinearLayout.VERTICAL);
        card.setBackgroundColor(0xFFFFFFFF);
        card.setPadding(24, 24, 24, 24);
        root.addView(card);

        avatarView = new ImageView(requireContext());
        avatarView.setImageResource(R.drawable.ic_my);
        avatarView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        LinearLayout.LayoutParams avatarParams = new LinearLayout.LayoutParams(180, 180);
        avatarParams.gravity = Gravity.CENTER_HORIZONTAL;
        avatarParams.bottomMargin = 20;
        avatarView.setLayoutParams(avatarParams);
        card.addView(avatarView);
        safeSetAvatar(preferenceManager.getAvatarUrl());

        Button btnUploadAvatar = createButton("上传头像", bodySize, v -> avatarPickerLauncher.launch("image/*"));
        card.addView(btnUploadAvatar);

        tvNickname = new TextView(requireContext());
        tvNickname.setText("昵称: " + getNickname());
        tvNickname.setTextSize(bodySize);
        tvNickname.setPadding(0, 16, 0, 10);
        card.addView(tvNickname);

        Button btnEditNickname = createButton("修改昵称", bodySize, v -> showEditNicknameDialog());
        card.addView(btnEditNickname);

        TextView tvEmail = new TextView(requireContext());
        tvEmail.setText("邮箱: " + preferenceManager.getEmail());
        tvEmail.setTextSize(bodySize);
        tvEmail.setPadding(0, 16, 0, 8);
        card.addView(tvEmail);

        TextView tvRole = new TextView(requireContext());
        tvRole.setText("身份: " + ("ELDER".equals(preferenceManager.getRole()) ? "老人" : "家属"));
        tvRole.setTextSize(bodySize);
        tvRole.setPadding(0, 8, 0, 8);
        card.addView(tvRole);

        tvFamilyInfo = new TextView(requireContext());
        tvFamilyInfo.setText(getFamilyText());
        tvFamilyInfo.setTextSize(bodySize);
        tvFamilyInfo.setPadding(0, 16, 0, 8);
        card.addView(tvFamilyInfo);

        root.addView(createButton("加入/创建家庭", bodySize, v -> showFamilyManageDialog()));
        root.addView(createButton("设置", bodySize, v -> startActivity(new Intent(requireContext(), SettingsActivity.class))));
        root.addView(createButton("切换身份", bodySize, v -> handleSwitchRole()));
        root.addView(createButton("退出登录", bodySize, v -> handleLogout()));

        return scrollView;
    }

    private Button createButton(String text, int fontSize, View.OnClickListener listener) {
        Button button = new Button(requireContext());
        button.setText(text);
        button.setTextSize(fontSize);
        button.setAllCaps(false);
        button.setOnClickListener(listener);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.topMargin = 14;
        button.setLayoutParams(params);
        return button;
    }

    private void safeSetAvatar(String avatarUrl) {
        if (avatarView == null) return;
        try {
            if (avatarUrl != null && !avatarUrl.isEmpty()) {
                avatarView.setImageURI(Uri.parse(avatarUrl));
            } else {
                avatarView.setImageResource(R.drawable.ic_my);
            }
        } catch (Exception e) {
            avatarView.setImageResource(R.drawable.ic_my);
        }
    }

    private String getNickname() {
        String nick = preferenceManager.getNickname();
        return nick.isEmpty() ? "未设置" : nick;
    }

    private String getFamilyText() {
        long familyId = preferenceManager.getFamilyId();
        if (familyId > 0) {
            return "家庭: " + preferenceManager.getFamilyName() + "\n邀请码: " + preferenceManager.getInviteCode();
        }
        return "未加入家庭";
    }

    private void showEditNicknameDialog() {
        EditText editText = new EditText(requireContext());
        editText.setHint("请输入新昵称");
        editText.setText(preferenceManager.getNickname());
        new AlertDialog.Builder(requireContext())
                .setTitle("修改昵称")
                .setView(editText)
                .setPositiveButton("保存", (dialog, which) -> {
                    String newNickname = editText.getText().toString().trim();
                    if (!newNickname.isEmpty()) {
                        preferenceManager.saveNickname(newNickname);
                        tvNickname.setText("昵称: " + newNickname);
                        Toast.makeText(requireContext(), "昵称已更新", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    private void handleSwitchRole() {
        new AlertDialog.Builder(requireContext())
                .setTitle("切换身份")
                .setMessage("当前已取消时间限制，可以直接切换身份。")
                .setPositiveButton("确定", (dialog, which) -> {
                    preferenceManager.saveRole("");
                    preferenceManager.saveRoleSelectTime(0);
                    Intent intent = new Intent(requireContext(), RoleSelectActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    if (getActivity() != null) getActivity().finish();
                })
                .setNegativeButton("取消", null)
                .show();
    }

    private void showFamilyManageDialog() {
        String[] options = {"创建新家庭", "加入已有家庭"};
        new AlertDialog.Builder(requireContext())
                .setTitle("家庭管理")
                .setItems(options, (dialog, which) -> {
                    if (which == 0) showCreateFamilyDialog();
                    else showJoinFamilyDialog();
                })
                .show();
    }

    private void showCreateFamilyDialog() {
        EditText inputName = new EditText(requireContext());
        inputName.setHint("请输入家庭名称");
        new AlertDialog.Builder(requireContext())
                .setTitle("创建家庭")
                .setView(inputName)
                .setPositiveButton("创建", (dialog, which) -> {
                    String familyName = inputName.getText().toString().trim();
                    if (!familyName.isEmpty()) {
                        String inviteCode = generateInviteCode();
                        preferenceManager.saveFamilyId(System.currentTimeMillis());
                        preferenceManager.saveFamilyName(familyName);
                        preferenceManager.saveInviteCode(inviteCode);
                        if ("ELDER".equals(preferenceManager.getRole())) preferenceManager.saveBindElderCount(1);
                        else preferenceManager.saveBindFamilyCount(1);
                        tvFamilyInfo.setText(getFamilyText());
                        Toast.makeText(requireContext(), "家庭创建成功！邀请码: " + inviteCode, Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    private void showJoinFamilyDialog() {
        EditText inputCode = new EditText(requireContext());
        inputCode.setHint("请输入家庭邀请码");
        new AlertDialog.Builder(requireContext())
                .setTitle("加入家庭")
                .setView(inputCode)
                .setPositiveButton("加入", (dialog, which) -> {
                    String inviteCode = inputCode.getText().toString().trim();
                    if (!inviteCode.isEmpty()) {
                        preferenceManager.saveFamilyId(System.currentTimeMillis());
                        preferenceManager.saveFamilyName("本地调试家庭");
                        preferenceManager.saveInviteCode(inviteCode);
                        tvFamilyInfo.setText(getFamilyText());
                        Toast.makeText(requireContext(), "成功加入家庭！", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    private String generateInviteCode() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) code.append(chars.charAt((int) (Math.random() * chars.length())));
        return code.toString();
    }

    private void handleLogout() {
        new AlertDialog.Builder(requireContext())
                .setTitle("退出登录")
                .setMessage("确定要退出登录吗？")
                .setPositiveButton("确定", (dialog, which) -> {
                    preferenceManager.clear();
                    Intent intent = new Intent(requireContext(), LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    if (getActivity() != null) getActivity().finish();
                })
                .setNegativeButton("取消", null)
                .show();
    }
}
