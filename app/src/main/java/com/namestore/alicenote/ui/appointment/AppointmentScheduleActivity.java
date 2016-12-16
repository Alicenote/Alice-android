package com.namestore.alicenote.ui.appointment;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kienht.materialcalendarview.CalendarDay;
import com.kienht.materialcalendarview.MaterialCalendarView;
import com.namestore.alicenote.R;
import com.namestore.alicenote.common.AppUtils;
import com.namestore.alicenote.common.ViewUtils;
import com.namestore.alicenote.ui.BaseActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by kienht on 12/15/16.
 */

public class AppointmentScheduleActivity extends BaseActivity {

    @SuppressLint("SimpleDateFormat")
    private static final DateFormat FORMATTER = new SimpleDateFormat("EEEE, MMM dd yyyy");

    private MaterialCalendarView mMaterialCalendarView;
    private TextView mTextViewDaySelection;
    private TextView mTextViewTitleBar;
    private Button mButtonLeftBar;
    private Button mButtonRightBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_listing);
        initViews();
        initModels();
    }

    protected void initViews() {
        mMaterialCalendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
        mTextViewDaySelection = (TextView) findViewById(R.id.textview_day_selection);
        mTextViewTitleBar = (TextView) findViewById(R.id.include).findViewById(R.id.toolbar_title);
        mButtonLeftBar = (Button) findViewById(R.id.include).findViewById(R.id.toolbar_backpress);
        mButtonRightBar = (Button) findViewById(R.id.include).findViewById(R.id.toolbar_edit);
    }

    protected void initModels() {
        mButtonRightBar.setVisibility(View.INVISIBLE);
        mTextViewTitleBar.setText("Appointment Schedule");
        ViewUtils.setAndScaleDrawableButton(mButtonLeftBar, R.drawable.icon_back, 0, 0, 0, 0.8);
        AppUtils.setTypeFontForTextView(this, AppUtils.MEDIUM, mTextViewDaySelection);
        AppUtils.setTypeFontForTextView(this, AppUtils.BOLD, mTextViewTitleBar);

        mButtonLeftBar.setOnClickListener(view -> {
            finish();
            overridePendingTransition(0, 0);
        });

        mMaterialCalendarView.setOnDateChangedListener((widget1, date, selected) -> {
            mTextViewDaySelection.setText(getSelectedDatesString());
        });
        mMaterialCalendarView.setSelectedDate(Calendar.getInstance().getTime());

        Typeface type = Typeface.createFromAsset(getAssets(), AppUtils.MEDIUM);
        mMaterialCalendarView.setTypeFaceFont(type);

        mTextViewDaySelection.setText(getSelectedDatesString());
    }

    private String getSelectedDatesString() {
        CalendarDay date = mMaterialCalendarView.getSelectedDate();
        if (date == null) {
            return "No Selection";
        }
        return FORMATTER.format(date.getDate());
    }


    @Override
    public void onClick(View view) {

    }
}
