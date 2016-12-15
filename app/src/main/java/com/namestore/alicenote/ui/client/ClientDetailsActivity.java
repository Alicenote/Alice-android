package com.namestore.alicenote.ui.client;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.ScrollView;
import android.widget.TextView;

import com.kyleduo.switchbutton.SwitchButton;
import com.namestore.alicenote.R;
import com.namestore.alicenote.common.AppUtils;
import com.namestore.alicenote.common.ViewUtils;
import com.namestore.alicenote.models.ClientObj;
import com.namestore.alicenote.ui.BaseActivity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by kienht on 12/11/16.
 */

public class ClientDetailsActivity extends BaseActivity {
    public static final String TAG = "TAG";
    public static final String CLIENT_OBJ = "clientObj";
    public static final String CLIENT_SCREEN = "client_screen";
    public static final int MALE = 0;
    public static final int FEMALE = 1;
    public static final int OTHER = 2;

    private LinearLayout linearLayoutPicker;
    private LinearLayout linearLayoutGender;
    private LinearLayout linearLayoutDob;
    private LinearLayout linearLayoutMain;
    private LinearLayout linearLayoutFake; //layout bù chiều cao khi keyboard hiện
    private ScrollView mScrollView;
    private EditText mEditTextClientName;
    private EditText mEditTextClientPhone;
    private EditText mEditTextClientEmail;
    private TextView mTextViewTitleBar;
    private TextView mTextViewGender;
    private TextView mTextViewDob;
    private TextView mTextViewTitlePicker;
    private Button mButtonCancel;
    private Button mButtonDone;
    private Button mButtonLeftBar;
    private Button mButtonRightBar;
    private NumberPicker mNumberPicker;
    private DatePicker mDatePicker;
    private SwitchButton mSwitchButton;

    private View mLineUnderName;
    private View mLineUnderPhone;
    private View mLineUnderEmail;
    private View mLineUnderGender;
    private View mLineUnderDob;

    private ClientObj mClientObj = new ClientObj();

    private String mName;
    private String mPhone;
    private String mEmail;
    private String mGender;
    private int mGenDerId;
    private String mDateOfBirth;
    private boolean mReceiveEmail = false;
    private boolean isEditMode = false;
    private boolean isKeyBoardShow = false;
    private boolean isScroll = false;
    private boolean isChangedDatePicker = false;
    private boolean isChangedGenderPicker = false;

    private boolean isAddClientActivity = false; //flag to check ClientDetailScreen or AddClientScreen

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acvitity_client_details);
        initView();
        isAddClientActivity = getIntent().getBooleanExtra(CLIENT_SCREEN, false);
        initModel();
        if (!isAddClientActivity) {
            getInforUser();
            isEditMode(false); //view or edit view
        }
        resizeHeightWhenKeyBoardShow();
    }

    public static Intent createIntent(Context context, ClientObj obj, boolean isAddClientActivity) {
        Intent intent = new Intent(context, ClientDetailsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra(CLIENT_SCREEN, isAddClientActivity);
        intent.putExtra(CLIENT_OBJ, obj);
        return intent;
    }

    protected void initView() {
        linearLayoutMain = (LinearLayout) findViewById(R.id.activity_main);
        linearLayoutPicker = (LinearLayout) findViewById(R.id.layout_picker);
        linearLayoutGender = (LinearLayout) findViewById(R.id.layout_gender);
        linearLayoutDob = (LinearLayout) findViewById(R.id.layout_dob);
        linearLayoutFake = (LinearLayout) findViewById(R.id.keyboard);

        mScrollView = (ScrollView) findViewById(R.id.scrollview);

        mEditTextClientName = (EditText) findViewById(R.id.edittext_client_name);
        mEditTextClientPhone = (EditText) findViewById(R.id.edittext_client_phone);
        mEditTextClientEmail = (EditText) findViewById(R.id.edittext_client_email);

        mTextViewTitleBar = (TextView) findViewById(R.id.include).findViewById(R.id.toolbar_title);
        mTextViewGender = (TextView) findViewById(R.id.textview_gender);
        mTextViewDob = (TextView) findViewById(R.id.textview_dob);
        mTextViewTitlePicker = (TextView) findViewById(R.id.title_picker);

        mNumberPicker = (NumberPicker) findViewById(R.id.picker_gender);
        mDatePicker = (DatePicker) findViewById(R.id.date_picker);

        mButtonLeftBar = (Button) findViewById(R.id.include).findViewById(R.id.toolbar_backpress);
        mButtonRightBar = (Button) findViewById(R.id.include).findViewById(R.id.toolbar_edit);
        mButtonCancel = (Button) findViewById(R.id.button_cancel);
        mButtonDone = (Button) findViewById(R.id.button_done);
        mSwitchButton = (SwitchButton) findViewById(R.id.switchbutton_email);

        mLineUnderName = findViewById(R.id.line_name);
        mLineUnderPhone = findViewById(R.id.line_phone);
        mLineUnderEmail = findViewById(R.id.line_email);
        mLineUnderGender = findViewById(R.id.line_gender);
        mLineUnderDob = findViewById(R.id.line_dob);
    }

    private void getInforUser() {
        mClientObj = (ClientObj) getIntent().getSerializableExtra(CLIENT_OBJ);
        mName = mClientObj.getmName();
        mPhone = mClientObj.getmPhone();
        mEmail = mClientObj.getmEmail();
        mDateOfBirth = mClientObj.getmDob();
        mEditTextClientName.setText(mClientObj.getmName());
        mEditTextClientPhone.setText(mClientObj.getmPhone());
        mEditTextClientEmail.setText(mClientObj.getmEmail());
        mSwitchButton.setChecked(mClientObj.ismReceive());
        switch (mClientObj.getmGender()) {
            case MALE:
                mGender = "Male";
                mTextViewGender.setText("Male");
                mNumberPicker.setValue(MALE);
                break;
            case FEMALE:
                mGender = "Female";
                mTextViewGender.setText("Female");
                mNumberPicker.setValue(FEMALE);
                break;
            case OTHER:
                mGender = "Other";
                mTextViewGender.setText("Other");
                mNumberPicker.setValue(OTHER);
                break;
        }
        mTextViewDob.setText(mClientObj.getmDob());
    }

    protected void initModel() {
        mLineUnderDob.setVisibility(View.GONE);

        //set font for view
        AppUtils.setTypeFontForTextView(ClientDetailsActivity.this, AppUtils.BOLD, mTextViewTitleBar, mTextViewTitlePicker);
        AppUtils.setTypeFontForTextView(ClientDetailsActivity.this, AppUtils.MEDIUM, mTextViewGender, mTextViewDob,
                (TextView) findViewById(R.id.textview_title_client_name), (TextView) findViewById(R.id.textview_more_detail),
                (TextView) findViewById(R.id.textview_title_phone), (TextView) findViewById(R.id.textview_title_email),
                (TextView) findViewById(R.id.textview_title_receive_email), (TextView) findViewById(R.id.textview_title_gender),
                (TextView) findViewById(R.id.textview_title_dob));
        AppUtils.setTypeFontForButton(ClientDetailsActivity.this, AppUtils.MEDIUM, mButtonLeftBar, mButtonRightBar, mButtonCancel, mButtonDone);
        AppUtils.setTypeFontForEditText(ClientDetailsActivity.this, AppUtils.MEDIUM, mEditTextClientEmail, mEditTextClientName, mEditTextClientPhone);
        //set font for view

        //set cursor at end position of text length
        setSelectionEditText(mEditTextClientName, mEditTextClientPhone, mEditTextClientEmail);

        //listener event from linearLayoutMain
        linearLayoutMain.setFocusableInTouchMode(true);
        linearLayoutMain.setOnTouchListener((view, motionEvent) -> {
            linearLayoutMain.requestFocus();
            return false;
        });
        linearLayoutMain.setOnClickListener(view -> AppUtils.hideKeyBoard(ClientDetailsActivity.this));
        //listener event from linearLayoutMain

        //listener event from edittext
        configEditText(mEditTextClientName);
        configEditText(mEditTextClientEmail);
        configEditText(mEditTextClientPhone);
        //listener event from edittext

        linearLayoutFake.setVisibility(View.GONE);
        linearLayoutPicker.setVisibility(View.GONE);

        linearLayoutGender.setOnClickListener(view -> {
            mTextViewTitlePicker.setText("Select Gender");
            if (linearLayoutPicker.getVisibility() != View.VISIBLE) {
                linearLayoutPicker.setVisibility(View.VISIBLE);
            }
            if (linearLayoutFake.getVisibility() == View.VISIBLE && isKeyBoardShow) {
                AppUtils.hideKeyBoard(ClientDetailsActivity.this);
                linearLayoutFake.setVisibility(View.GONE);
            }
            mNumberPicker.setVisibility(View.VISIBLE);
            mDatePicker.setVisibility(View.GONE);
            mScrollView.postDelayed(() -> mScrollView.fullScroll(ScrollView.FOCUS_DOWN), 50);
            //isScrollingScrollView(true);
        });

        linearLayoutGender.setOnTouchListener((view, motionEvent) -> {
            linearLayoutMain.requestFocus();
            return false;
        });

        linearLayoutDob.setOnClickListener(view -> {
            mTextViewTitlePicker.setText("Date of Birth");
            if (linearLayoutPicker.getVisibility() != View.VISIBLE) {
                linearLayoutPicker.setVisibility(View.VISIBLE);
            }
            if (linearLayoutFake.getVisibility() == View.VISIBLE && isKeyBoardShow) {
                AppUtils.hideKeyBoard(ClientDetailsActivity.this);
                linearLayoutFake.setVisibility(View.GONE);
            }
            mDatePicker.setVisibility(View.VISIBLE);
            mNumberPicker.setVisibility(View.GONE);
            mScrollView.postDelayed(() -> mScrollView.fullScroll(ScrollView.FOCUS_DOWN), 50);
            // isScrollingScrollView(true);
        });

        linearLayoutDob.setOnTouchListener((view, motionEvent) -> {
            linearLayoutMain.requestFocus();
            return false;
        });


        mButtonCancel.setOnClickListener(view -> {
            linearLayoutPicker.setVisibility(View.GONE);
            //isScrollingScrollView(false);
        });

        mButtonDone.setOnClickListener(view -> {
            linearLayoutPicker.setVisibility(View.GONE);
            if (!isChangedDatePicker && mDatePicker.getVisibility() == View.VISIBLE) {
                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                mDateOfBirth = mDay + "/" + mMonth + "/" + mYear;
            }
            if (!isChangedGenderPicker && mNumberPicker.getVisibility() == View.VISIBLE) {
                mGender = "Male";
            }
            mTextViewGender.setText(mGender);
            mTextViewDob.setText(mDateOfBirth);
            //isScrollingScrollView(false);
        });

        if (isAddClientActivity) {
            setViewToolBarInAddClientScreen();
        } else {
            setViewToolBarInClientDetailScreen();
        }

        setUpGenderPicker();
        setUpDatePicker();
        setupEditTextActionListener();
    }

    private void setViewToolBarInClientDetailScreen() {
        mTextViewTitleBar.setText("Client Details");
        //set icon and scale  for viewicon button
        ViewUtils.setAndScaleDrawableButton(mButtonLeftBar, R.drawable.icon_back, 0, 0, 0, 0.8);
        ViewUtils.setAndScaleDrawableButton(mButtonRightBar, 0, 0, R.drawable.icon_edit, 0, 0.7);

        mButtonLeftBar.setOnClickListener(view -> {
            if (!isEditMode) {
                finish();
                overridePendingTransition(0, 0);
            } else {
                if (linearLayoutPicker.getVisibility() == View.VISIBLE) {
                    linearLayoutPicker.setVisibility(View.GONE);
                }
                setTextTitleBar("", "Client Details", "");
                ViewUtils.setAndScaleDrawableButton(mButtonLeftBar, R.drawable.icon_back, 0, 0, 0, 0.8);
                ViewUtils.setAndScaleDrawableButton(mButtonRightBar, 0, 0, R.drawable.icon_edit, 0, 0.7);
                isEditMode(false);
                isEditMode = false;
                if (isKeyBoardShow) {
                    AppUtils.hideKeyBoard(ClientDetailsActivity.this);
                }
            }
        });

        mButtonRightBar.setOnClickListener(view -> {
            if (!isEditMode) {
                mButtonLeftBar.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                mButtonRightBar.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                setTextTitleBar("Cancel", "Edit Client Details", "Save");
                mButtonLeftBar.setText("Cancel");
                isEditMode(true);
                isEditMode = true;
                linearLayoutMain.clearFocus();
                mEditTextClientName.performClick();
            } else {
                if (!checkEmptyTextView()) {
                    isEditMode = false;
                    if (linearLayoutPicker.getVisibility() == View.VISIBLE) {
                        linearLayoutPicker.setVisibility(View.GONE);
                    }
                    setTextTitleBar("", "Client Details", "");
                    ViewUtils.setAndScaleDrawableButton(mButtonLeftBar, R.drawable.icon_back, 0, 0, 0, 0.8);
                    ViewUtils.setAndScaleDrawableButton(mButtonRightBar, 0, 0, R.drawable.icon_edit, 0, 0.7);
                    isEditMode(false);
                    if (isKeyBoardShow) {
                        AppUtils.hideKeyBoard(ClientDetailsActivity.this);
                    }
                }
            }
        });
    }

    private void setViewToolBarInAddClientScreen() {
        mTextViewTitleBar.setText("Create New Client");
        mButtonLeftBar.setText("Cancel");
        mButtonRightBar.setText("Save");

        mButtonLeftBar.setOnClickListener(view -> {
            finish();
            overridePendingTransition(0, 0);
        });

        mButtonRightBar.setOnClickListener(view -> {
            if (!checkEmptyTextView()) {
                finish();
                overridePendingTransition(0, 0);
            }
        });
    }

    /**
     * @return true nếu bất cứ 1 textview hoặc 1 edittext nào còn trống
     */
    private boolean checkEmptyTextView() {
        boolean isEmty = true;

        mName = mEditTextClientName.getText().toString();
        mPhone = mEditTextClientPhone.getText().toString();
        mEmail = mEditTextClientEmail.getText().toString();

        if (TextUtils.isEmpty(mName)) {
            mEditTextClientName.performClick();
            mLineUnderName.setBackgroundColor(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ?
                    getApplicationContext().getColor(R.color.pink) :
                    getResources().getColor(R.color.pink));
        } else if (TextUtils.isEmpty(mPhone)) {
            mEditTextClientPhone.performClick();
            mLineUnderPhone.setBackgroundColor(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ?
                    getApplicationContext().getColor(R.color.pink) :
                    getResources().getColor(R.color.pink));
        } else if (TextUtils.isEmpty(mEmail)) {
            mEditTextClientEmail.performClick();
            mLineUnderEmail.setBackgroundColor(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ?
                    getApplicationContext().getColor(R.color.pink) :
                    getResources().getColor(R.color.pink));
        } else if (TextUtils.isEmpty(mGender)) {
            linearLayoutGender.performClick();
            mScrollView.smoothScrollTo(0, linearLayoutMain.getRootView().getHeight() * 2);
            mLineUnderGender.setBackgroundColor(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ?
                    getApplicationContext().getColor(R.color.pink) :
                    getResources().getColor(R.color.pink));
        } else if (TextUtils.isEmpty(mDateOfBirth)) {
            linearLayoutDob.performClick();
            mScrollView.smoothScrollTo(0, linearLayoutMain.getRootView().getHeight() * 2);
            mLineUnderDob.setVisibility(View.VISIBLE);
        } else {
            isEmty = false;
        }

        mEditTextClientName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(charSequence)) {
                    mLineUnderName.setBackgroundColor(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ?
                            getApplicationContext().getColor(R.color.pink) :
                            getResources().getColor(R.color.pink));
                    return;
                }
                mLineUnderName.setBackgroundColor(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ?
                        getApplicationContext().getColor(R.color.aquasqueeze) :
                        getResources().getColor(R.color.aquasqueeze));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mEditTextClientPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(charSequence)) {
                    mLineUnderPhone.setBackgroundColor(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ?
                            getApplicationContext().getColor(R.color.pink) :
                            getResources().getColor(R.color.pink));
                    return;
                }
                mLineUnderPhone.setBackgroundColor(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ?
                        getApplicationContext().getColor(R.color.aquasqueeze) :
                        getResources().getColor(R.color.aquasqueeze));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mEditTextClientEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(charSequence)) {
                    mLineUnderEmail.setBackgroundColor(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ?
                            getApplicationContext().getColor(R.color.pink) :
                            getResources().getColor(R.color.pink));
                    return;
                }
                mLineUnderEmail.setBackgroundColor(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ?
                        getApplicationContext().getColor(R.color.aquasqueeze) :
                        getResources().getColor(R.color.aquasqueeze));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mTextViewGender.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mLineUnderGender.setBackgroundColor(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ?
                        getApplicationContext().getColor(R.color.aquasqueeze) :
                        getResources().getColor(R.color.aquasqueeze));
            }
        });

        mTextViewDob.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mLineUnderDob.setVisibility(View.GONE);
            }
        });

        return isEmty;
    }

    private void setTextTitleBar(String left, String center, String right) {
        mButtonLeftBar.setText(left);
        mTextViewTitleBar.setText(center);
        mButtonRightBar.setText(right);
    }

    //dump data into NumberPicker
    private void setUpGenderPicker() {
        final ArrayList<String> arrayGender = new ArrayList<>();
        arrayGender.add("Male");
        arrayGender.add("Female");
        arrayGender.add("Other");
        mNumberPicker.setMinValue(0);
        mNumberPicker.setMaxValue(arrayGender.size() - 1);
        mNumberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        mNumberPicker.setDisplayedValues(arrayGender.toArray(new String[arrayGender.size()]));
        setDividerNumberPickerColor(mNumberPicker);
        overrideFontNumberPicker(mNumberPicker);

        mNumberPicker.setOnValueChangedListener((numberPicker, i, newVal) -> {
            isChangedGenderPicker = true;
            if (newVal == numberPicker.getValue()) {
                mGenDerId = newVal;
                runOnUiThread(() -> mGender = arrayGender.get(newVal));
            }
        });
    }

    //dump data into DatePicker
    private void setUpDatePicker() {
        boolean showCalendar = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            showCalendar = getResources().getConfiguration().smallestScreenWidthDp >= 600;
        } else {
            showCalendar =
                    (getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_XLARGE;
        }
        mDatePicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        mDatePicker.setCalendarViewShown(showCalendar);
        colorizeDatePicker(mDatePicker);
        overrideFontDatePicker(mDatePicker);
        mDatePicker.init(
                mDatePicker.getYear(), mDatePicker.getMonth(), mDatePicker.getDayOfMonth(),
                (datePicker, year, monthOfYear, dayOfMonth) -> {
                    isChangedDatePicker = true;
                    mDateOfBirth = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                });


    }

    /**
     * @param isEdit false to only View || true to Edit View
     */
    private void isEditMode(boolean isEdit) {
        disableEditText(isEdit, mEditTextClientName, mEditTextClientEmail, mEditTextClientPhone);
        mSwitchButton.setEnabled(isEdit);
        mSwitchButton.setClickable(isEdit);
        linearLayoutGender.setClickable(isEdit);
        linearLayoutGender.setEnabled(isEdit);
        linearLayoutDob.setClickable(isEdit);
        linearLayoutDob.setEnabled(isEdit);
    }

    /**
     * @param isEdit   to set view mode or edit mode
     * @param editText
     */
    private void disableEditText(boolean isEdit, EditText... editText) {
        for (EditText _editText : editText) {
            _editText.setFocusable(isEdit);
            _editText.setEnabled(isEdit);
            _editText.setCursorVisible(isEdit);
        }
    }

    public void configEditText(final EditText edittext) {
        edittext.setOnClickListener(view -> {
            switch (view.getId()) {
                case R.id.edittext_client_name:
                    mScrollView.post(() -> mScrollView.smoothScrollBy(0, -linearLayoutMain.getRootView().getHeight() / 2));
                    break;
                default:
                    isScroll = true;
                    break;
            }
            if (linearLayoutPicker.getVisibility() == View.VISIBLE) {
                linearLayoutPicker.setVisibility(View.GONE);
            }
            linearLayoutMain.clearFocus();
            edittext.requestFocusFromTouch();
            linearLayoutFake.setVisibility(View.VISIBLE);
            InputMethodManager keyBoard =
                    (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            keyBoard.showSoftInput(edittext, InputMethodManager.SHOW_IMPLICIT);
        });

        edittext.setOnTouchListener((view, motionEvent) -> {
            if (isAddClientActivity) {
                linearLayoutMain.clearFocus();
                edittext.requestFocusFromTouch();
                linearLayoutFake.setVisibility(View.VISIBLE);
            }
            return false;
        });

        edittext.setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                edittext.clearFocus();
                edittext.setFocusableInTouchMode(false);
            }
        });
    }

    /**
     * listener when press button next or done on soft keyboard
     */
    private void setupEditTextActionListener() {
        mEditTextClientName.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                isScroll = true;
            }
            return false;
        });
        mEditTextClientPhone.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                isScroll = true;
            }
            return false;
        });
        mEditTextClientEmail.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                AppUtils.hideKeyBoard(ClientDetailsActivity.this);
            }
            return false;
        });
    }

    private void setSelectionEditText(EditText... editText) {
        for (EditText _edittex : editText) {
            _edittex.setSelection(_edittex.length());
        }
    }

    /**
     * @param isBlockedScrollView true to disable || false to enable Scrolling
     */
    private void isScrollingScrollView(final boolean isBlockedScrollView) {
        mScrollView.setOnTouchListener((v, event) -> isBlockedScrollView);
    }

    //khi soft keyboard hiện lên sẽ đẩy layout trên bàn phím bằng cách thêm 1 layout có chiều cao bằng bàn phím
    private void resizeHeightWhenKeyBoardShow() {
        linearLayoutMain.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            Rect r = new Rect();
            linearLayoutMain.getWindowVisibleDisplayFrame(r);
            int screenHeight = linearLayoutMain.getRootView().getHeight();
            final int keypadHeight = screenHeight - r.bottom;
            if (keypadHeight > screenHeight * 0.15) { // 0.15 ratio is perhaps enough to determine keypad height.
                isKeyBoardShow = true;
                setHeightLayoutFake(keypadHeight);
                if (isScroll) {
                    mScrollView.postDelayed(() -> {
                        mScrollView.smoothScrollBy(0, keypadHeight);
                        isScroll = false;
                    }, 50);
                }
            } else {
                isKeyBoardShow = false;
                setHeightLayoutFake(0);
            }
        });
    }

    private void setDividerNumberPickerColor(NumberPicker picker) {
        Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    ColorDrawable colorDrawable = new ColorDrawable(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ?
                            getApplicationContext().getResources().getColor(R.color.aquasqueeze) :
                            getResources().getColor(R.color.aquasqueeze));
                    pf.set(picker, colorDrawable);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    private void colorizeDatePicker(DatePicker datePicker) {
        Resources system = Resources.getSystem();
        int dayId = system.getIdentifier("day", "id", "android");
        int monthId = system.getIdentifier("month", "id", "android");
        int yearId = system.getIdentifier("year", "id", "android");

        NumberPicker dayPicker = (NumberPicker) datePicker.findViewById(dayId);
        NumberPicker monthPicker = (NumberPicker) datePicker.findViewById(monthId);
        NumberPicker yearPicker = (NumberPicker) datePicker.findViewById(yearId);

        setDividerNumberPickerColor(dayPicker);
        setDividerNumberPickerColor(monthPicker);
        setDividerNumberPickerColor(yearPicker);
    }

    private void overrideFontNumberPicker(NumberPicker numberPicker) {
        int count = numberPicker.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = numberPicker.getChildAt(i);
            if (child instanceof EditText) {
                try {
                    Field selectorWheelPaintField = numberPicker.getClass()
                            .getDeclaredField("mSelectorWheelPaint");
                    selectorWheelPaintField.setAccessible(true);
                    Typeface type = Typeface.createFromAsset(getAssets(), AppUtils.MEDIUM);
                    ((Paint) selectorWheelPaintField.get(numberPicker)).setTypeface(type);
                    ((EditText) child).setTypeface(type);
                    numberPicker.invalidate();
                } catch (NoSuchFieldException e) {
                } catch (IllegalAccessException e) {
                } catch (IllegalArgumentException e) {
                }
            }
        }
    }

    private void overrideFontDatePicker(View v) {
        ViewGroup picker = (DatePicker) v;
        LinearLayout layout1 = (LinearLayout) picker.getChildAt(0);
        LinearLayout layout = (LinearLayout) layout1.getChildAt(0);
        for (int j = 0; j < 3; j++) {
            try {
                if (layout.getChildAt(j) instanceof NumberPicker) {
                    NumberPicker v1 = (NumberPicker) layout.getChildAt(j);
                    final int count = v1.getChildCount();
                    for (int i = 0; i < count; i++) {
                        View child = v1.getChildAt(i);
                        try {
                            Field wheelpaint_field = v1.getClass().getDeclaredField("mSelectorWheelPaint");
                            wheelpaint_field.setAccessible(true);
                            Typeface type = Typeface.createFromAsset(getAssets(), AppUtils.MEDIUM);
                            ((Paint) wheelpaint_field.get(v1)).setTypeface(type);
                            ((EditText) child).setTypeface(type);
                            v1.invalidate();
                        } catch (Exception e) {
                            //TODO catch.
                            //If java cant find field then it will catch here and app wont crash.
                        }
                    }
                }
            } catch (Exception e) {
                //TODO catch.
                //If java cant find field then it will catch here and app wont crash.
            }
        }

    }

    private void setHeightLayoutFake(int height) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
        linearLayoutFake.setLayoutParams(layoutParams);
    }

    @Override
    public void onBackPressed() {
        if (linearLayoutPicker.getVisibility() == View.VISIBLE) {
            linearLayoutPicker.setVisibility(View.GONE);
        } else {
            finish();
            overridePendingTransition(0, 0);
        }
    }

    @Override
    public void onClick(View view) {

    }
}
