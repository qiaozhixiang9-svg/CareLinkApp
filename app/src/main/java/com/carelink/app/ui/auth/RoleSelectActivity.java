package com.carelink.app.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.carelink.app.data.local.pref.PreferenceManager;
import com.carelink.app.databinding.ActivityRoleSelectBinding;
import com.carelink.app.ui.elder.ElderMainActivity;
import com.carelink.app.ui.family.FamilyMainActivity;

/**
 * 本地调试版身份选择页面
 */
public class RoleSelectActivity extends AppCompatActivity {

    private static final String TAG = "RoleSelectActivity";
    private ActivityRoleSelectBinding binding;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            binding = ActivityRoleSelectBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
            preferenceManager = new PreferenceManager(this);

            // 本地调试版：检查是否已登录（以本地 email 为准）
            if (preferenceManager.getEmail().isEmpty()) {
                goToLogin();
                return;
            }

            String existingRole = preferenceManager.getRole();
            if (!existingRole.isEmpty()) {
                navigateToRole(existingRole);
                return;
            }

            initView();
        } catch (Exception e) {
            Log.e(TAG, "onCreate 异常: " + e.getMessage(), e);
            Toast.makeText(this, "页面加载失败: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void initView() {
        binding.cardElder.setOnClickListener(v -> selectRole("ELDER"));
        binding.cardFamily.setOnClickListener(v -> selectRole("FAMILY"));
    }

    private void selectRole(String role) {
        preferenceManager.saveRole(role);
        preferenceManager.saveRoleSelectTime(System.currentTimeMillis());
        if (preferenceManager.getUserIdStr().isEmpty()) {
            preferenceManager.saveUserIdStr("local_" + System.currentTimeMillis());
        }
        if (preferenceManager.getNickname().isEmpty() && !preferenceManager.getEmail().isEmpty()) {
            preferenceManager.saveNickname(preferenceManager.getEmail().split("@")[0]);
        }
        navigateToRole(role);
    }

    private void navigateToRole(String role) {
        Intent intent = "ELDER".equals(role)
            ? new Intent(this, ElderMainActivity.class)
            : new Intent(this, FamilyMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
