package com.namestore.alicenote.ui.home.adapter;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.namestore.alicenote.common.AppUtils;
import com.namestore.alicenote.models.RankingObj;

import java.util.ArrayList;

import com.namestore.alicenote.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by kienht on 12/7/16.
 */

public class RankingDialogAdapterRecycler extends RecyclerView.Adapter<RankingDialogAdapterRecycler.ViewHolder> {

    public static final int SERVICES = 0;
    public static final int EMPLOYEE = 1;

    private ArrayList<RankingObj.Data> arrayList;
    private Activity activity;
    private int TAG;

    public RankingDialogAdapterRecycler(Activity activity, ArrayList<RankingObj.Data> arrayList, int TAG) {
        this.activity = activity;
        this.arrayList = arrayList;
        this.TAG = TAG;
    }

    @Override
    public RankingDialogAdapterRecycler.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ranking_dialog, parent, false);
        return new RankingDialogAdapterRecycler.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(arrayList.get(position), position);
        holder.mTextViewNumber.setText(arrayList.get(position).getStt());
        holder.mTextViewName.setText(arrayList.get(position).getName());
        holder.mTextViewSubTitle.setText(activity.getResources().getString(R.string.bookings,
                String.valueOf(arrayList.get(position).getBooks())));
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextViewNumber;
        private CircleImageView mImgViewAvatar;
        private TextView mTextViewName;
        private TextView mTextViewSubTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextViewNumber = (TextView) itemView.findViewById(R.id.number);
            mImgViewAvatar = (CircleImageView) itemView.findViewById(R.id.image);
            mTextViewName = (TextView) itemView.findViewById(R.id.name);
            mTextViewSubTitle = (TextView) itemView.findViewById(R.id.subtile);
        }

        public void bindData(RankingObj.Data obj, int position) {
            switch (TAG) {
                case SERVICES:
                    mImgViewAvatar.setVisibility(View.GONE);
                    AppUtils.setTypeFontForTextView(activity, AppUtils.MEDIUM, mTextViewName, mTextViewNumber, mTextViewSubTitle);
                    switch (position) {
                        case 0: //pink case 2
                        case 1: //pink case 2
                        case 2:
                            mTextViewSubTitle.setTextColor(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ?
                                    activity.getApplicationContext().getColor(R.color.pink) :
                                    activity.getResources().getColor(R.color.pink));
                            break;
                        default:
                            mTextViewSubTitle.setTextColor(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ?
                                    activity.getApplicationContext().getColor(R.color.black) :
                                    activity.getResources().getColor(R.color.black));
                            break;
                    }
                    break;
                case EMPLOYEE:
                    switch (position) {
                        case 0:
                            mTextViewSubTitle.setTextColor(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ?
                                    activity.getApplicationContext().getColor(R.color.pink) :
                                    activity.getResources().getColor(R.color.pink));
                            mTextViewName.setTypeface(null, Typeface.BOLD);
                            mTextViewNumber.setTypeface(null, Typeface.BOLD);
                            AppUtils.setTypeFontForTextView(activity, AppUtils.BOLD, mTextViewName, mTextViewNumber);
                            AppUtils.setTypeFontForTextView(activity, AppUtils.MEDIUM, mTextViewSubTitle);
                            break;
                        case 1: //black case 2
                        case 2:
                            mTextViewSubTitle.setTextColor(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ?
                                    activity.getApplicationContext().getColor(R.color.black) :
                                    activity.getResources().getColor(R.color.black));
                            mTextViewName.setTypeface(null, Typeface.BOLD);
                            mTextViewNumber.setTypeface(null, Typeface.BOLD);
                            AppUtils.setTypeFontForTextView(activity, AppUtils.BOLD, mTextViewName, mTextViewNumber);
                            AppUtils.setTypeFontForTextView(activity, AppUtils.MEDIUM, mTextViewSubTitle);
                            break;
                        default:
                            mTextViewSubTitle.setTextColor(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ?
                                    activity.getApplicationContext().getColor(R.color.trout) :
                                    activity.getResources().getColor(R.color.trout));
                            AppUtils.setTypeFontForTextView(activity, AppUtils.MEDIUM, mTextViewName, mTextViewNumber, mTextViewSubTitle);
                            break;


                    }

                    break;
            }
        }
    }
}
