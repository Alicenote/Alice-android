package com.namestore.alicenote.ui.firstsetup.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.namestore.alicenote.R;
import com.namestore.alicenote.common.AppUtils;
import com.namestore.alicenote.network.request.FirstSetupRequest;
import com.namestore.alicenote.ui.BaseFragment;
import com.namestore.alicenote.ui.firstsetup.FirstSetupAcitivity;
import com.namestore.alicenote.ui.firstsetup.interfaces.OnFirstSetupActivityListener;
import com.namestore.alicenote.common.ViewUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kienht on 10/25/16.
 */

public class ShopWorkingDayFragment extends BaseFragment {

    public static final int MONDAY = 1;
    public static final int TUESDAY = 2;
    public static final int WEDNESDAY = 3;
    public static final int THURSDAY = 4;
    public static final int FRIDAY = 5;
    public static final int SATURDAY = 6;
    public static final int SUNDAY = 7;

    Button mButtonBack;
    Button mButtonNext;
    TextView mTextViewTitle;
    CheckBox mCheckBoxMonday;
    CheckBox mCheckBoxTuesday;
    CheckBox mCheckBoxWednesday;
    CheckBox mCheckBoxThursday;
    CheckBox mCheckBoxFriday;
    CheckBox mCheckBoxSaturday;
    CheckBox mCheckBoxSunday;
    Spinner mSpinnerTimeStartMonday, mSpinnerTimeEndMonday;
    Spinner mSpinnerTimeStartTuesday, mSpinnerTimeEndTuesday;
    Spinner mSpinnerTimeStartWednesday, mSpinnerTimeEndWednesday;
    Spinner mSpinnerTimeStartThursday, mSpinnerTimeEndThursday;
    Spinner mSpinnerTimeStartFriday, mSpinnerTimeEndFriday;
    Spinner mSpinnerTimeStartSaturday, mSpinnerTimeEndSaturday;
    Spinner mSpinnerTimeStartSunday, mSpinnerTimeEndSunday;
    private FirstSetupAcitivity firstSetupAcitivity;


    List<String> status = new ArrayList<>();
    List<Pair<Integer, Integer>> hour = new ArrayList<>();
    ArrayList<FirstSetupRequest.Schedule> scheduleArrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_shop_working_days, container, false);
        initViews(view);
        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initModels();
    }

    @Override
    protected void initModels() {
        for (int i = 0; i < 7; i++) {
            hour.add(i, null);
            scheduleArrayList.add(i, null);
        }
        mTextViewTitle.setText("When is \"Your Salon\" Open?");
        mButtonBack.setOnClickListener(this);
        mButtonNext.setOnClickListener(this);
        setupSpinner();
        configSpinner();
    }

    public void setupSpinner() {
        mCheckBoxMonday.setText("Monday");
        mCheckBoxTuesday.setText("Tuesday");
        mCheckBoxWednesday.setText("Wednesday");
        mCheckBoxThursday.setText("Thursday");
        mCheckBoxFriday.setText("Friday");
        mCheckBoxSaturday.setText("Saturday");
        mCheckBoxSunday.setText("Sunday");
    }

    public void configSpinner() {
        ArrayList<String> hour_open = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.hour_open)));
        ViewUtils.configSpinner(getActivity(), hour_open, mSpinnerTimeEndMonday, mSpinnerTimeEndTuesday,
                mSpinnerTimeEndWednesday, mSpinnerTimeEndThursday, mSpinnerTimeEndFriday,
                mSpinnerTimeEndSaturday, mSpinnerTimeEndSunday,
                mSpinnerTimeStartMonday, mSpinnerTimeStartTuesday,
                mSpinnerTimeStartWednesday, mSpinnerTimeStartThursday,
                mSpinnerTimeStartFriday, mSpinnerTimeStartSaturday, mSpinnerTimeStartSunday);
    }

    @Override
    protected void initViews(View view) {
        mTextViewTitle = (TextView) view.findViewById(R.id.tile_time_open).findViewById(R.id.title_first_setup);

        mButtonBack = (Button) view.findViewById(R.id.button_next_back).findViewById(R.id.button_back);
        mButtonNext = (Button) view.findViewById(R.id.button_next_back).findViewById(R.id.button_next);

        mCheckBoxMonday = (CheckBox) view.findViewById(R.id.day1).findViewById(R.id.days);
        mCheckBoxTuesday = (CheckBox) view.findViewById(R.id.day2).findViewById(R.id.days);
        mCheckBoxWednesday = (CheckBox) view.findViewById(R.id.day3).findViewById(R.id.days);
        mCheckBoxThursday = (CheckBox) view.findViewById(R.id.day4).findViewById(R.id.days);
        mCheckBoxFriday = (CheckBox) view.findViewById(R.id.day5).findViewById(R.id.days);
        mCheckBoxSaturday = (CheckBox) view.findViewById(R.id.day6).findViewById(R.id.days);
        mCheckBoxSunday = (CheckBox) view.findViewById(R.id.day7).findViewById(R.id.days);

        mSpinnerTimeStartMonday = (Spinner) view.findViewById(R.id.day1).findViewById(R.id.hour_start);
        mSpinnerTimeEndMonday = (Spinner) view.findViewById(R.id.day1).findViewById(R.id.hour_end);

        mSpinnerTimeStartTuesday = (Spinner) view.findViewById(R.id.day2).findViewById(R.id.hour_start);
        mSpinnerTimeEndTuesday = (Spinner) view.findViewById(R.id.day2).findViewById(R.id.hour_end);

        mSpinnerTimeStartWednesday = (Spinner) view.findViewById(R.id.day3).findViewById(R.id.hour_start);
        mSpinnerTimeEndWednesday = (Spinner) view.findViewById(R.id.day3).findViewById(R.id.hour_end);

        mSpinnerTimeStartThursday = (Spinner) view.findViewById(R.id.day4).findViewById(R.id.hour_start);
        mSpinnerTimeEndThursday = (Spinner) view.findViewById(R.id.day4).findViewById(R.id.hour_end);

        mSpinnerTimeStartFriday = (Spinner) view.findViewById(R.id.day5).findViewById(R.id.hour_start);
        mSpinnerTimeEndFriday = (Spinner) view.findViewById(R.id.day5).findViewById(R.id.hour_end);

        mSpinnerTimeStartSaturday = (Spinner) view.findViewById(R.id.day6).findViewById(R.id.hour_start);
        mSpinnerTimeEndSaturday = (Spinner) view.findViewById(R.id.day6).findViewById(R.id.hour_end);

        mSpinnerTimeStartSunday = (Spinner) view.findViewById(R.id.day7).findViewById(R.id.hour_start);
        mSpinnerTimeEndSunday = (Spinner) view.findViewById(R.id.day7).findViewById(R.id.hour_end);

        for (int i = 0; i < 7; i++) {
            status.add(i, "on");
        }

        initScheduleMonday();
        initScheduleTuesday();
        initScheduleWednesday();
        initScheduleThursDay();
        initScheduleFriday();
        initScheduleSaturday();
        initScheduleSunday();
    }

    /**
     * Các phương thức initSchedule để đăng ký lắng nghe sự kiện của checkboxDay và Spinner Start End hour
     */

    private void initScheduleMonday() {
        mCheckBoxMonday.setOnCheckedChangeListener((compoundButton, b) -> status.add(0, b ? "on" : "off"));

        mSpinnerTimeStartMonday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                hour.set(0, new Pair<Integer, Integer>(position - 1, 0));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mSpinnerTimeEndMonday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                hour.set(0, new Pair<Integer, Integer>(hour.get(0).first, position - 1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initScheduleTuesday() {
        mCheckBoxTuesday.setOnCheckedChangeListener((compoundButton, b) -> status.add(1, b ? "on" : "off"));

        mSpinnerTimeStartTuesday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                hour.set(1, new Pair<Integer, Integer>(position - 1, 0));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mSpinnerTimeEndTuesday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                hour.set(1, new Pair<Integer, Integer>(hour.get(1).first, position - 1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initScheduleWednesday() {
        mCheckBoxWednesday.setOnCheckedChangeListener((compoundButton, b) -> status.add(2, b ? "on" : "off"));

        mSpinnerTimeStartWednesday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                hour.set(2, new Pair<Integer, Integer>(position - 1, 0));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mSpinnerTimeEndWednesday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                hour.set(2, new Pair<Integer, Integer>(hour.get(2).first, position - 1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initScheduleThursDay() {
        mCheckBoxThursday.setOnCheckedChangeListener((compoundButton, b) -> status.add(3, b ? "on" : "off"));

        mSpinnerTimeStartThursday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                hour.set(3, new Pair<Integer, Integer>(position - 1, 0));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mSpinnerTimeEndThursday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                hour.set(3, new Pair<Integer, Integer>(hour.get(3).first, position - 1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initScheduleFriday() {
        mCheckBoxFriday.setOnCheckedChangeListener((compoundButton, b) -> status.add(4, b ? "on" : "off"));

        mSpinnerTimeStartFriday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                hour.set(4, new Pair<Integer, Integer>(position - 1, 0));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mSpinnerTimeEndFriday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                hour.set(4, new Pair<Integer, Integer>(hour.get(4).first, position - 1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initScheduleSaturday() {
        mCheckBoxSaturday.setOnCheckedChangeListener((compoundButton, b) -> status.add(5, b ? "on" : "off"));

        mSpinnerTimeStartSaturday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                hour.set(5, new Pair<Integer, Integer>(position - 1, 0));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mSpinnerTimeEndSaturday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                hour.set(5, new Pair<Integer, Integer>(hour.get(5).first, position - 1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initScheduleSunday() {
        mCheckBoxSunday.setOnCheckedChangeListener((compoundButton, b) -> status.add(6, b ? "on" : "off"));

        mSpinnerTimeStartSunday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                hour.set(6, new Pair<Integer, Integer>(position - 1, 0));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mSpinnerTimeEndSunday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                hour.set(6, new Pair<Integer, Integer>(hour.get(6).first, position - 1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_back:
                if (mActivity instanceof OnFirstSetupActivityListener) {
                    ((OnFirstSetupActivityListener) mActivity).showShopRegisterFragment();
                }
                break;

            case R.id.button_next:
                for (int i = 0; i < 7; i++) {
                    if (hour.get(i).first == -1 || hour.get(i).second == -1 || hour.get(i).first >= hour.get(i).second) {
                        AppUtils.showNoticeDialog(getActivity(), "Please choose open hour or end hour");
                        return;
                    }
                }
                //Thêm list Schedule data salon vào firstSetupRequest Obj
                FirstSetupRequest.Schedule monday = new FirstSetupRequest().new Schedule(MONDAY, "Monday", String.valueOf(hour.get(0).first),
                        String.valueOf(hour.get(0).second), status.get(0));
                FirstSetupRequest.Schedule tuesday = new FirstSetupRequest().new Schedule(TUESDAY, "Tuesday", String.valueOf(hour.get(1).first),
                        String.valueOf(hour.get(1).second), status.get(1));
                FirstSetupRequest.Schedule wednesday = new FirstSetupRequest().new Schedule(WEDNESDAY, "Wednesday", String.valueOf(hour.get(2).first),
                        String.valueOf(hour.get(2).second), status.get(2));
                FirstSetupRequest.Schedule thursday = new FirstSetupRequest().new Schedule(THURSDAY, "Thursday", String.valueOf(hour.get(3).first),
                        String.valueOf(hour.get(3).second), status.get(3));
                FirstSetupRequest.Schedule friday = new FirstSetupRequest().new Schedule(FRIDAY, "Friday", String.valueOf(hour.get(4).first),
                        String.valueOf(hour.get(4).second), status.get(4));
                FirstSetupRequest.Schedule saturday = new FirstSetupRequest().new Schedule(SATURDAY, "Saturday", String.valueOf(hour.get(5).first),
                        String.valueOf(hour.get(5).second), status.get(5));
                FirstSetupRequest.Schedule sunday = new FirstSetupRequest().new Schedule(SUNDAY, "Sunday", String.valueOf(hour.get(6).first),
                        String.valueOf(hour.get(6).second), status.get(6));

                FirstSetupRequest.Schedule[] schedules = {monday, tuesday, wednesday, thursday, friday, saturday, sunday};

                firstSetupAcitivity.firstSetupRequest.setSchedules(addScheduleArrayList(scheduleArrayList, schedules));

                if (mActivity instanceof OnFirstSetupActivityListener) {
                    ((OnFirstSetupActivityListener) mActivity).showShopServicesCategoryFragment();
                }
                break;
            default:
                break;
        }
        super.onClick(view);
    }

    public ArrayList<FirstSetupRequest.Schedule> addScheduleArrayList(
            ArrayList<FirstSetupRequest.Schedule> arrayList, FirstSetupRequest.Schedule[] schedules) {
        for (int i = 0; i < 7; i++) {
            arrayList.set(i, schedules[i]);
        }
        return arrayList;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof FirstSetupAcitivity) {
            this.firstSetupAcitivity = (FirstSetupAcitivity) context;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof FirstSetupAcitivity) {
            this.firstSetupAcitivity = (FirstSetupAcitivity) activity;
        }
    }
}
