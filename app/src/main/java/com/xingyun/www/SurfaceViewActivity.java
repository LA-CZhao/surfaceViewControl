package com.xingyun.www;

import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.IOException;

public class SurfaceViewActivity extends AppCompatActivity implements View.OnClickListener {

    private SurfaceView sv;
    private SurfaceHolder holder1 = null;
    private Camera cam = null;
    private boolean previewRunning = true;
    private Button py;
    private Button sx;
    private Button yd;
    private Button fd;
    private Button fy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_surface_view);

        sv = (SurfaceView) findViewById(R.id.sv);

        holder1 = sv.getHolder();

        holder1.addCallback(new MySurfaceViewCallback());

        py = (Button) findViewById(R.id.py);
        sx = (Button) findViewById(R.id.sx);
        yd = (Button) findViewById(R.id.yd);
        fd = (Button) findViewById(R.id.fd);
        fy = (Button) findViewById(R.id.fy);
        py.setOnClickListener(this);
        sx.setOnClickListener(this);
        yd.setOnClickListener(this);
        fd.setOnClickListener(this);
        fy.setOnClickListener(this);

        sv.setOnClickListener(this);

//       int w = sv.getWidth();
//       int  h = sv.getHeight();
//
//        Log.d("w",w+"");
//        Log.d("w",h+"");
//        SharedPreferences.Editor edit = getSharedPreferences("config", 0).edit();
//        edit.putInt("width", w);
//        edit.putInt("height", h);
//
//        edit.commit();



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.py:
                SurfaceViewPingYi();
                break;

            case R.id.sx:
                SurfaceViewSuoXiao();
                break;
            case R.id.yd:
                SurfaceViewYuanDian();
                break;
            case R.id.fd:
                SurfaceViewFangDa();
                break;
            case R.id.fy:
                SurfaceViewFuYuan();
                break;

            case R.id.sv:
                SurfaceViewSuoXiao();
                break;
        }
    }

    private void SurfaceViewFuYuan() {

//        final float scale = getResources().getDisplayMetrics().density;
//        int w =(int) (200 / scale + 0.5f);
        LinearLayout.LayoutParams  params = new LinearLayout.LayoutParams(200,200);

        sv.setLayoutParams(params);
    }

    /*
    * 恢复图片原来的位置
    * **/
    private void SurfaceViewFangDa() {
        int svWidth = sv.getWidth();
        int svHeight = sv.getHeight();
        LinearLayout.LayoutParams  params = new LinearLayout.LayoutParams(svWidth*2,svHeight*2);

        sv.setLayoutParams(params);
    }

    /*
    * 回归原点
    * **/
    private void SurfaceViewYuanDian() {
        int svWidth = sv.getWidth();
        int svHeight = sv.getHeight();
        LinearLayout.LayoutParams  params = new LinearLayout.LayoutParams(svWidth,svHeight);

        sv.setLayoutParams(params);
    }

    private void SurfaceViewSuoXiao() {
        int svWidth = sv.getWidth();
        int svHeight = sv.getHeight();
        LinearLayout.LayoutParams  params = new LinearLayout.LayoutParams(svWidth/2,svHeight/2);

        sv.setLayoutParams(params);

    }

    private void SurfaceViewPingYi() {
        int svWidth = sv.getWidth();
        int svHeight = sv.getHeight();
        LinearLayout.LayoutParams  params = new LinearLayout.LayoutParams(svWidth,svHeight);

        params.setMargins(60,0,svWidth+60,0);

        sv.setLayoutParams(params);

    }


    private class MySurfaceViewCallback implements SurfaceHolder.Callback{

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            cam = Camera.open(); // 取得第一个摄像头

			cam.setDisplayOrientation(90); // 纠正摄像头自动旋转，纠正角度，如果引用，则摄像角度偏差90度

            try {
                cam.setPreviewDisplay(holder);
            } catch (IOException e) {
            }

            cam.startPreview(); // 进行预览
            previewRunning = true; // 已经开始预览
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            // TODO Auto-generated method stub

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            if (cam != null) {
                if (previewRunning) {
                    cam.stopPreview(); // 停止预览
                    previewRunning = false;
                }
                cam.release();
            }

        }

    }
}
