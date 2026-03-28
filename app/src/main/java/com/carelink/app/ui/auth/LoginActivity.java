package com.carelink.app.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.carelink.app.data.local.pref.PreferenceManager;
import com.carelink.app.databinding.ActivityLoginBinding;

/**
 * 本地调试版登录页面
 * 不依赖服务器/Firebase：
 * - 注册：本地保存邮箱/密码/昵称
 * - 登录：校验本地保存的邮箱/密码
 * - 已登录：按角色直接进入主页
 */
public class LoginActivity extends androidx.appcompat.app.AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private ActivityLoginBinding binding;
    private PreferenceManager preferenceManager;
    private boolean isProcessing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            binding = ActivityLoginBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
            preferenceManager = new PreferenceManager(this);
            initView();
            checkCurrentUser();
        } catch (Exception e) {
            Log.e(TAG, "onCreate 异常: " + e.getMessage(), e);
            Toast.makeText(this, "页面加载失败: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void initView() {
        binding.btnLogin.setOnClickListener(v -> {
            if (isProcessing) return;
            String email = binding.etEmail.getText().toString().trim();
            String password = binding.etPassword.getText().toString().trim();
            clearErrors();
            String validationError = validateInput(email, password);
            if (validationError != null) {
                showError(validationError);
                return;
            }
            performLogin(email, password);
        });

        binding.tvRegister.setOnClickListener(v -> {
            if (isProcessing) return;
            String email = binding.etEmail.getText().toString().trim();
            String password = binding.etPassword.getText().toString().trim();
            clearErrors();
            String validationError = validateInput(email, password);
            if (validationError != null) {
                showError(validationError);
                return;
            }
            performRegister(email, password);
        });

        binding.tvForgotPassword.setOnClickListener(v -> showForgotPasswordDialog());
    }

    private void checkCurrentUser() {
        if (!preferenceManager.isLoggedIn()) {
            Log.d(TAG, "本地未登录，显示登录页");
            return;
        }
        String role = preferenceManager.getRole();
        if (!role.isEmpty()) {
            Log.d(TAG, "本地已登录且已选角色: " + role);
            navigateToMain(role);
        } else {
            Log.d(TAG, "本地已登录但未选角色");
            navigateToRoleSelect();
        }
    }

    private void performLogin(String email, String password) {
        isProcessing = true;
        binding.btnLogin.setEnabled(false);
        showLoading();

        binding.getRoot().postDelayed(() -> {
            hideLoading();
            isProcessing = false;
            binding.btnLogin.setEnabled(true);

            String savedEmail = preferenceManager.getEmail();
            String savedPassword = preferenceManager.getToken(); // 调试期用 token 字段存密码

            if (savedEmail.isEmpty()) {
                showError("该邮箱未注册，请先点击注册");
                return;
            }

            if (!savedEmail.equalsIgnoreCase(email)) {
                showError("邮箱不存在，请检查输入");
                return;
            }

            if (!savedPassword.equals(password)) {
                showError("密码错误");
                return;
            }

            // 登录成功
            preferenceManager.saveUserIdStr("local_" + Math.abs(email.hashCode()));
            if (preferenceManager.getNickname().isEmpty()) {
                preferenceManager.saveNickname(email.split("@")[0]);
            }

            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            String role = preferenceManager.getRole();
            if (!role.isEmpty()) {
                navigateToMain(role);
            } else {
                navigateToRoleSelect();
            }
        }, 500);
    }

    private void performRegister(String email, String password) {
        isProcessing = true;
        binding.btnLogin.setEnabled(false);
        showLoading();

        binding.getRoot().postDelayed(() -> {
            hideLoading();
            isProcessing = false;
            binding.btnLogin.setEnabled(true);

            String savedEmail = preferenceManager.getEmail();
            if (!savedEmail.isEmpty() && savedEmail.equalsIgnoreCase(email)) {
                showError("该邮箱已被注册，请直接登录");
                return;
            }

            preferenceManager.saveEmail(email);
            preferenceManager.saveToken(password); // 调试期：本地保存密码
            preferenceManager.saveUserIdStr("local_" + Math.abs(email.hashCode()));
            preferenceManager.saveNickname(email.split("@")[0]);

            new AlertDialog.Builder(this)
                .setTitle("注册成功")
                .setMessage("当前为本地调试模式，无需邮箱验证。\n\n可直接登录并选择身份。")
                .setPositiveButton("知道了", null)
                .show();
        }, 500);
    }

    private void showForgotPasswordDialog() {
        EditText editText = new EditText(this);
        editText.setHint("请输入已注册邮箱");

        new AlertDialog.Builder(this)
            .setTitle("找回密码")
            .setView(editText)
            .setPositiveButton("查看", (dialog, which) -> {
                String email = editText.getText().toString().trim();
                if (email.isEmpty()) {
                    Toast.makeText(this, "请输入邮箱地址", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!email.equalsIgnoreCase(preferenceManager.getEmail())) {
                    Toast.makeText(this, "该邮箱未注册", Toast.LENGTH_SHORT).show();
                    return;
                }
                new AlertDialog.Builder(this)
                    .setTitle("调试模式提示")
                    .setMessage("当前为本地调试模式。\n\n该账号密码为：" + preferenceManager.getToken())
                    .setPositiveButton("确定", null)
                    .show();
            })
            .setNegativeButton("取消", null)
            .show();
    }

    private String validateInput(String email, String password) {
        if (email.isEmpty()) {
            binding.tilEmail.setError("请输入邮箱");
            return "请输入邮箱";
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.setError("邮箱格式不正确");
            return "邮箱格式不正确";
        }
        if (password.isEmpty()) {
            binding.tilPassword.setError("请输入密码");
            return "请输入密码";
        }
        if (password.length() < 6) {
            binding.tilPassword.setError("密码至少6位");
            return "密码至少6位";
        }
        return null;
    }

    private void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void showLoading() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        binding.progressBar.setVisibility(View.GONE);
    }

    private void clearErrors() {
        binding.tilEmail.setError(null);
        binding.tilPassword.setError(null);
    }

    private void navigateToRoleSelect() {
        Intent intent = new Intent(this, RoleSelectActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void navigateToMain(String role) {
        Class<?> targetActivity = "ELDER".equals(role)
            ? com.carelink.app.ui.elder.ElderMainActivity.class
            : com.carelink.app.ui.family.FamilyMainActivity.class;
        Intent intent = new Intent(this, targetActivity);
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
