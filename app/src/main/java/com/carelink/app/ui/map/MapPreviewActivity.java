package com.carelink.app.ui.map;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;

public class MapPreviewActivity extends AppCompatActivity {

    private MapView mapView;
    private AMap aMap;
    private TextView debugText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(20, 20, 20, 20);

        debugText = new TextView(this);
        debugText.setText("地图页已打开，准备初始化地图...");
        debugText.setPadding(20, 20, 20, 20);
        debugText.setTextSize(16);

        mapView = new MapView(this);
        mapView.onCreate(savedInstanceState);

        root.addView(debugText, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        // 地图外层容器：固定高度 + 灰色背景，方便确认地图区域是否真的渲染出来
        LinearLayout mapContainer = new LinearLayout(this);
        mapContainer.setOrientation(LinearLayout.VERTICAL);
        mapContainer.setPadding(10, 10, 10, 10);
        mapContainer.setBackgroundColor(0xFFDDDDDD);

        LinearLayout.LayoutParams containerParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                1000
        );

        mapContainer.addView(mapView, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));

        root.addView(mapContainer, containerParams);

        setContentView(root);

        try {
            aMap = mapView.getMap();

            if (aMap == null) {
                debugText.setText("AMap 获取失败：aMap == null");
                return;
            } else {
                debugText.setText("AMap 获取成功，正在加载地图...");
            }

            LatLng point = new LatLng(31.2304, 121.4737); // 上海测试点
            aMap.addMarker(new MarkerOptions()
                    .position(point)
                    .title("测试位置"));

            aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 15f));

            debugText.append("\nMarker 已添加，镜头已移动到测试点。");
            debugText.append("\n请观察下方灰色区域内是否出现地图底图。");
        } catch (Exception e) {
            debugText.setText("地图初始化异常：\n"
                    + e.getClass().getSimpleName()
                    + "\n"
                    + e.getMessage());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mapView != null) {
            mapView.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mapView != null) {
            mapView.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mapView != null) {
            mapView.onDestroy();
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (mapView != null) {
            mapView.onLowMemory();
        }
    }
}
