/*
 * This source code is licensed under the MIT-style license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.ljfxyj2008.scalabletimebarDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ljfxyj2008.scaletimebar.RecordDataExistTimeSegment;
import com.ljfxyj2008.scaletimebar.ScalableTimebarView;
import com.ljfxyj2008.scaletimebar.TimebarTickCriterion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "ljfxyj2008";

    private TextView currentTimeTextView;
    private ImageView zoomInButton, zoomOutButton;
    private ScalableTimebarView mScalableTimebarView;
    private int recordDays = 7;
    private long currentRealDateTime = System.currentTimeMillis();

    private static long ONE_MINUTE_IN_MS = 60 * 1000;
    private static long ONE_HOUR_IN_MS = 60 * ONE_MINUTE_IN_MS;
    private static long ONE_DAY_IN_MS = 24 * ONE_HOUR_IN_MS;

    private SimpleDateFormat zeroTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScalableTimebarView = (ScalableTimebarView) findViewById(R.id.my_timebar_view);
        currentTimeTextView = (TextView) findViewById(R.id.current_time_tv);
        zoomInButton = (ImageView) findViewById(R.id.timebar_zoom_in_btn);
        zoomOutButton = (ImageView) findViewById(R.id.timebar_zoom_out_btn);

        zoomInButton.setOnClickListener(this);
        zoomOutButton.setOnClickListener(this);

        long timebarRightEndPointTime = currentRealDateTime + 3 * ONE_HOUR_IN_MS;
        long timebarLeftEndPointTime = timebarRightEndPointTime - recordDays * ONE_DAY_IN_MS;
        long timabarCursorCurrentTime = timebarRightEndPointTime;
        mScalableTimebarView.initTimebarLengthAndPosition(timebarLeftEndPointTime,
                timebarRightEndPointTime, timabarCursorCurrentTime);

        List<RecordDataExistTimeSegment> recordDataList = new ArrayList<>();
        recordDataList.add(new RecordDataExistTimeSegment(currentRealDateTime - ONE_DAY_IN_MS, currentRealDateTime - ONE_DAY_IN_MS + ONE_MINUTE_IN_MS * 32));
        recordDataList.add(new RecordDataExistTimeSegment(currentRealDateTime - ONE_DAY_IN_MS + 18 * ONE_HOUR_IN_MS, currentRealDateTime - ONE_DAY_IN_MS + 18 * ONE_HOUR_IN_MS + ONE_MINUTE_IN_MS * 32));
        recordDataList.add(new RecordDataExistTimeSegment(currentRealDateTime - 5 * ONE_MINUTE_IN_MS, currentRealDateTime));
        mScalableTimebarView.setRecordDataExistTimeClipsList(recordDataList);

        mScalableTimebarView.setOnBarMoveListener(new ScalableTimebarView.OnBarMoveListener() {
            @Override
            public void onBarMove(long screenLeftTime, long screenRightTime, long currentTime) {
                currentTimeTextView.setText(zeroTimeFormat.format(currentTime));
                Log.d(TAG, "onBarMove()");
            }

            @Override
            public void OnBarMoveFinish(long screenLeftTime, long screenRightTime, long currentTime) {
                Log.d(TAG, "OnBarMoveFinish()");
            }
        });

        mScalableTimebarView.setOnBarScaledListener(new ScalableTimebarView.OnBarScaledListener() {
            @Override
            public void onBarScaled(long screenLeftTime, long screenRightTime, long currentTime) {
                currentTimeTextView.setText(zeroTimeFormat.format(currentTime));
                Log.d(TAG, "onBarScaled()");
            }

            @Override
            public void onBarScaleFinish(long screenLeftTime, long screenRightTime, long currentTime) {
                Log.d(TAG, "onBarScaleFinish()");
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.timebar_zoom_in_btn:
                mScalableTimebarView.scaleByPressingButton(true);
                break;

            case R.id.timebar_zoom_out_btn:
                mScalableTimebarView.scaleByPressingButton(false);
                break;

            default:
                break;
        }
    }



}
