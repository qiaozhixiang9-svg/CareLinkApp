package com.carelink.app.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;

import com.carelink.app.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * 应用自动更新管理器
 * 使用 Firebase Firestore 存储版本信息
 */
public class UpdateManager {

    // 如果不需要自动更新功能，可以将这些配置设为空
    // 可以在 Firebase Firestore 中配置版本信息，也可以在代码中直接配置
    private static final String VERSION_CHECK_URL = ""; // 如果有版本检查接口可以填在这里
    
    private final Context context;
    private AlertDialog updateDialog;
    private ProgressBar progressBar;

    public UpdateManager(Context context) {
        this.context = context;
    }

    /**
     * 检查是否有新版本
     * 如果需要使用 Firestore，可以在这里添加逻辑
     */
    public void checkForUpdate() {
        // 暂时跳过检查，如果需要可以后续添加 Firestore 逻辑
        // 或者使用自己的服务器接口检查版本
        
        // 示例：检查更新（可以后续配置）
        if (VERSION_CHECK_URL != null && !VERSION_CHECK_URL.isEmpty()) {
            checkVersionFromServer();
        } else {
            // 不检查更新，直接跳过
        }
    }

    /**
     * 从服务器检查版本
     */
    private void checkVersionFromServer() {
        // TODO: 实现自己的版本检查逻辑
        // 可以使用 Retrofit 或其他方式从自己的服务器获取版本信息
    }

    /**
     * 显示更新对话框（供外部调用）
     */
    public void showUpdateDialog(String version, String message, String downloadUrl, boolean forceUpdate) {
        if (context instanceof Activity && ((Activity) context).isFinishing()) {
            return;
        }

        View dialogView = LayoutInflater.from(context).inflate(
                R.layout.dialog_update, null);

        android.widget.TextView tvVersion = dialogView.findViewById(R.id.tv_version);
        android.widget.TextView tvMessage = dialogView.findViewById(R.id.tv_message);
        android.widget.Button btnUpdate = dialogView.findViewById(R.id.btn_update);
        android.widget.Button btnCancel = dialogView.findViewById(R.id.btn_cancel);
        progressBar = dialogView.findViewById(R.id.progress_bar);

        tvVersion.setText("发现新版本 v" + version);
        tvMessage.setText(message != null ? message : "是否立即更新？");

        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setView(dialogView)
                .setCancelable(!forceUpdate);

        updateDialog = builder.create();
        updateDialog.setCanceledOnTouchOutside(false);

        btnUpdate.setOnClickListener(v -> {
            downloadAndInstall(downloadUrl);
        });

        if (forceUpdate) {
            btnCancel.setVisibility(View.GONE);
        } else {
            btnCancel.setOnClickListener(v -> {
                updateDialog.dismiss();
            });
        }

        updateDialog.show();
    }

    /**
     * 下载并安装 APK
     */
    private void downloadAndInstall(String downloadUrl) {
        if (downloadUrl == null || downloadUrl.isEmpty()) {
            Toast.makeText(context, "下载链接无效", Toast.LENGTH_SHORT).show();
            return;
        }
        
        progressBar.setVisibility(View.VISIBLE);
        
        new Thread(() -> {
            File apkFile = null;
            try {
                apkFile = downloadApk(downloadUrl);
                if (apkFile != null) {
                    installApk(apkFile);
                } else {
                    showToast("下载失败");
                }
            } catch (Exception e) {
                showToast("下载失败: " + e.getMessage());
            } finally {
                hideProgress();
            }
        }).start();
    }

    /**
     * 下载 APK 文件
     */
    private File downloadApk(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();

        if (connection.getResponseCode() != HttpsURLConnection.HTTP_OK) {
            return null;
        }

        int fileLength = connection.getContentLength();
        InputStream input = connection.getInputStream();
        
        String fileName = "FamilyCare_update.apk";
        File outputDir = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        if (outputDir == null) {
            outputDir = context.getFilesDir();
        }
        File apkFile = new File(outputDir, fileName);

        FileOutputStream output = new FileOutputStream(apkFile);
        byte[] buffer = new byte[4096];
        long total = 0;
        int count;

        while ((count = input.read(buffer)) != -1) {
            total += count;
            if (fileLength > 0) {
                final long finalTotal = total;
                new Handler(Looper.getMainLooper()).post(() -> {
                    if (progressBar != null) {
                        progressBar.setProgress((int) (100 * finalTotal / fileLength));
                    }
                });
            }
            output.write(buffer, 0, count);
        }

        output.close();
        input.close();
        
        return apkFile;
    }

    /**
     * 安装 APK
     */
    private void installApk(File apkFile) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Uri apkUri = FileProvider.getUriForFile(
                        context,
                        context.getPackageName() + ".fileprovider",
                        apkFile
                );
                intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            } else {
                intent.setDataAndType(Uri.fromFile(apkFile), 
                        "application/vnd.android.package-archive");
            }
            
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            showToast("安装失败: " + e.getMessage());
        }
    }

    private void showToast(String message) {
        new Handler(Looper.getMainLooper()).post(() -> 
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show());
    }

    private void hideProgress() {
        new Handler(Looper.getMainLooper()).post(() -> {
            if (progressBar != null) {
                progressBar.setVisibility(View.GONE);
            }
            if (updateDialog != null) {
                updateDialog.dismiss();
            }
        });
    }
}
