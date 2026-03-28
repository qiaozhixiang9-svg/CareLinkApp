package com.carelink.app.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.carelink.app.data.local.pref.PreferenceManager;
import com.carelink.app.databinding.FragmentSettingsBinding;

/**
 * 设置页面
 */
public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;
    private PreferenceManager preferenceManager;

    @Nullable
    @Override
    public android.view.View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        
        // 直接初始化 PreferenceManager，不使用 Hilt 注入
        preferenceManager = new PreferenceManager(requireContext());
        
        initView();
        
        return binding.getRoot();
    }

    private void initView() {
        // 语音播报开关
        binding.switchVoice.setChecked(preferenceManager.isVoiceEnabled());
        binding.switchVoice.setOnCheckedChangeListener((btn, checked) -> {
            preferenceManager.setVoiceEnabled(checked);
            Toast.makeText(requireContext(),
                    checked ? "语音播报已开启" : "语音播报已关闭", Toast.LENGTH_SHORT).show();
        });

        // 退出登录
        binding.btnLogout.setOnClickListener(v -> {
            preferenceManager.clear();
            requireActivity().finish();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
