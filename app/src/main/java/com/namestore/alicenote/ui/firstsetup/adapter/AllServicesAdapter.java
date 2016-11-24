package com.namestore.alicenote.ui.firstsetup.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.namestore.alicenote.R;
import com.namestore.alicenote.common.PrefUtils;
import com.namestore.alicenote.models.SubServicesObj;

import java.util.ArrayList;

/**
 * Created by kienht on 10/26/16.
 */

public class AllServicesAdapter extends RecyclerView.Adapter<AllServicesAdapter.ViewHolder> {

    private ArrayList<SubServicesObj> servicesArrayList;
    private OnItemClickListener listener;
    public String[] durationTimeService;
    public Activity activity;


    public AllServicesAdapter(Activity activity, ArrayList<SubServicesObj> services,
                              String durationTimeService[], OnItemClickListener listener) {
        this.servicesArrayList = new ArrayList<>(services);
        this.listener = listener;
        this.durationTimeService = durationTimeService;
        this.activity = activity;
    }

    public interface OnItemClickListener {
        void onClickSpinnerDurationTime(int position, int positionSpinner);

        void onClickEdittexPrice(int position, float price);

        void onClickCheckBoxStaff(int position, boolean isWork);

        void onClickItem(int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_salon_service, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bindData(servicesArrayList.get(position));

    }

    public void swapData(ArrayList<SubServicesObj> services) {
        servicesArrayList.clear();
        servicesArrayList.addAll(services);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return servicesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewNameService;
        private LinearLayout linearLayout;
        private Button mButtonDown;
        private Spinner mSpinnerDurationTime;
        private CheckBox checkboxStaff;
        private EditText editTextPrice;

        public ViewHolder(View itemView) {
            super(itemView);

            mTextViewNameService = (TextView) itemView.findViewById(R.id.name_service_name);
            mButtonDown = (Button) itemView.findViewById(R.id.button_down);
            mSpinnerDurationTime = (Spinner) itemView.findViewById(R.id.spinner_duation_time);
            checkboxStaff = (CheckBox) itemView.findViewById(R.id.checkbox_staff);
            editTextPrice = (EditText) itemView.findViewById(R.id.edittext_price);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.layout_config_services);
            linearLayout.setVisibility(View.GONE);
        }

        public void bindData(final SubServicesObj services) {
            checkboxStaff.setChecked(true);
            String ownerName = PrefUtils.getInstance(activity).get(PrefUtils.OWNER_NAME, "Owner Name");
            checkboxStaff.setText(ownerName);

            mTextViewNameService.setText(services.getNameSubServices());

            linearLayout.setFocusableInTouchMode(true);
            linearLayout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    editTextPrice.clearFocus();
                    linearLayout.setFocusableInTouchMode(true);
                    InputMethodManager keyBoard =
                            (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    keyBoard.hideSoftInputFromWindow(linearLayout.getWindowToken(), 0);
                    return false;
                }
            });

            mTextViewNameService.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClickItem(getPosition());
                    if (linearLayout.getVisibility() != View.VISIBLE) {
                        linearLayout.setVisibility(View.VISIBLE);
                        mButtonDown.setBackgroundResource(R.drawable.icon_up);

                    } else {
                        linearLayout.setVisibility(View.GONE);
                        mButtonDown.setBackgroundResource(R.drawable.icon_down);
                    }
                }
            });

            mButtonDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mTextViewNameService.performClick();
                }
            });

            editTextPrice.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    String defFloat = editTextPrice.getText().toString();
                    if (defFloat.isEmpty()) {
                        defFloat = "0";
                    }
                    listener.onClickEdittexPrice(getPosition(), Float.valueOf(defFloat));
                }
            });

            checkboxStaff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isWork) {
                    listener.onClickCheckBoxStaff(getPosition(), isWork);
                }
            });

            settingSpinner();
        }

        public void settingSpinner() {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(activity, android.R.layout.simple_spinner_item, durationTimeService);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            mSpinnerDurationTime.setAdapter(adapter);
            mSpinnerDurationTime.setSelection(0);

            mSpinnerDurationTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int positionSpinner, long l) {
                    listener.onClickSpinnerDurationTime(getPosition(), positionSpinner);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }

}
