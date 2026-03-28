package com.carelink.app.ui.elder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.carelink.app.R;
import com.carelink.app.data.local.pref.PreferenceManager;
import com.carelink.app.ui.auth.LoginActivity;
import com.carelink.app.ui.profile.MyProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/** 本地调试版老人端主界面 */
public class ElderMainActivity extends AppCompatActivity {
    private static final String TAG = "ElderMainActivity";
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            PreferenceManager pm = new PreferenceManager(this);
            if (pm.getEmail().isEmpty()) {
                goToLogin();
                return;
            }
            if (!"ELDER".equals(pm.getRole())) {
                pm.clear();
                goToLogin();
                return;
            }

            LinearLayout root = new LinearLayout(this);
            root.setOrientation(LinearLayout.VERTICAL);
            root.setFitsSystemWindows(true);

            FrameLayout container = new FrameLayout(this);
            container.setId(R.id.elder_content_container);
            container.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f));
            root.addView(container);

            BottomNavigationView bottomNav = new BottomNavigationView(this);
            bottomNav.setId(R.id.elder_bottom_nav);
            bottomNav.setLabelVisibilityMode(BottomNavigationView.LABEL_VISIBILITY_LABELED);
            bottomNav.inflateMenu(R.menu.menu_elder_bottom);
            root.addView(bottomNav);
            setContentView(root);

            if (savedInstanceState == null) {
                loadFragment(new ElderHomeFragment(), "home");
            }

            bottomNav.setOnItemSelectedListener(item -> {
                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    switchTab(new ElderHomeFragment(), "home");
                } else if (id == R.id.nav_task) {
                    switchTab(new TodayTaskFragment(), "task");
                } else if (id == R.id.nav_emergency) {
                    switchTab(new EmergencyFragment(), "emergency");
                } else if (id == R.id.nav_schedule) {
                    switchTab(new ScheduleFragment(), "schedule");
                } else if (id == R.id.nav_my) {
                    switchTab(new MyProfileFragment(), "my");
                } else {
                    Toast.makeText(this, "功能开发中", Toast.LENGTH_SHORT).show();
                }
                return true;
            });
        } catch (Exception e) {
            Log.e(TAG, "onCreate 异常", e);
            showFatalError(e);
        }
    }

    private void switchTab(Fragment fragment, String tag) {
        try {
            if (currentFragment != null && currentFragment.getClass().equals(fragment.getClass())) return;
            loadFragment(fragment, tag);
        } catch (Exception e) {
            Toast.makeText(this, "页面加载失败", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadFragment(Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.elder_content_container, fragment, tag)
                .commitAllowingStateLoss();
        currentFragment = fragment;
    }

    private void goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void showFatalError(Exception e) {
        LinearLayout errorLayout = new LinearLayout(this);
        errorLayout.setOrientation(LinearLayout.VERTICAL);
        errorLayout.setGravity(Gravity.CENTER);
        errorLayout.setPadding(50, 100, 50, 50);
        TextView errorView = new TextView(this);
        errorView.setText("页面加载失败\n\n" + e.getClass().getSimpleName() + ": " + e.getMessage());
        errorView.setTextSize(16);
        errorView.setGravity(Gravity.CENTER);
        errorLayout.addView(errorView);
        setContentView(errorLayout);
    }
}
