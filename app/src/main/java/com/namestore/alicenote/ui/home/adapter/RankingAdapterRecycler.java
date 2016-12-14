package com.namestore.alicenote.ui.home.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.namestore.alicenote.R;
import com.namestore.alicenote.common.AppUtils;
import com.namestore.alicenote.models.RankingObj;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by kienht on 12/5/16.
 */

public class RankingAdapterRecycler extends RecyclerView.Adapter<RankingAdapterRecycler.ViewHolder> {

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;

    private ArrayList<RankingObj> arrayList;
    private Activity activity;
    private OnRankClickItemListener listener;

    public interface OnRankClickItemListener {
        void onFullServiceButton();

        void onFullEmployeeButton();
    }

    public RankingAdapterRecycler(Activity activity, ArrayList<RankingObj> arrayList, OnRankClickItemListener listener) {
        this.activity = activity;
        this.arrayList = arrayList;
        this.listener = listener;
    }

    @Override
    public RankingAdapterRecycler.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ranking, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RankingAdapterRecycler.ViewHolder holder, int position) {
        holder.bindData(arrayList.get(position));
        holder.mTextViewTitle.setText(arrayList.get(position).getTitle());

        AppUtils.setTypeFontForTextView(activity, AppUtils.MEDIUM, holder.mTextViewName1st, holder.mTextViewName2nd,
                holder.mTextViewName3rd, holder.mTextViewSubTitle1st, holder.mTextViewSubTitle2nd, holder.mTextViewSubTitle3rd);

        AppUtils.setTypeFontForTextView(activity, AppUtils.BOLD, holder.mTextViewTitle);

        AppUtils.setTypeFontForButton(activity, AppUtils.MEDIUM, holder.mButtonRanking);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextViewTitle;
        private Button mButtonRanking;
        private CircleImageView mImgViewAvatar1st;
        private CircleImageView mImgViewAvatar2nd;
        private CircleImageView mImgViewAvatar3rd;
        private TextView mTextViewName1st;
        private TextView mTextViewName2nd;
        private TextView mTextViewName3rd;
        private TextView mTextViewSubTitle1st;
        private TextView mTextViewSubTitle2nd;
        private TextView mTextViewSubTitle3rd;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextViewTitle = (TextView) itemView.findViewById(R.id.title);
            mButtonRanking = (Button) itemView.findViewById(R.id.btn_ranking);
            mImgViewAvatar1st = (CircleImageView) itemView.findViewById(R.id.image_1st);
            mImgViewAvatar2nd = (CircleImageView) itemView.findViewById(R.id.image_2nd);
            mImgViewAvatar3rd = (CircleImageView) itemView.findViewById(R.id.image_3rd);
            mTextViewName1st = (TextView) itemView.findViewById(R.id.name_1st);
            mTextViewName2nd = (TextView) itemView.findViewById(R.id.name_2nd);
            mTextViewName3rd = (TextView) itemView.findViewById(R.id.name_3rd);
            mTextViewSubTitle1st = (TextView) itemView.findViewById(R.id.subtile_1st);
            mTextViewSubTitle2nd = (TextView) itemView.findViewById(R.id.subtile_2nd);
            mTextViewSubTitle3rd = (TextView) itemView.findViewById(R.id.subtile_3rd);
        }

        public void bindData(RankingObj obj) {
            switch (obj.getTAG()) {
                case RankingObj.TOP_SERVICES:
                    mButtonRanking.setText("Full Service Rankings");
                    mImgViewAvatar1st.setVisibility(View.GONE);
                    mImgViewAvatar2nd.setVisibility(View.GONE);
                    mImgViewAvatar3rd.setVisibility(View.GONE);

                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(40, 10, 0, 0); //margin Left 40dp & Top 10
                    mTextViewName1st.setLayoutParams(layoutParams);
                    mTextViewName2nd.setLayoutParams(layoutParams);
                    mTextViewName3rd.setLayoutParams(layoutParams);

                    mTextViewName1st.setText(obj.getDatas().get(FIRST).getName());
                    mTextViewName2nd.setText(obj.getDatas().get(SECOND).getName());
                    mTextViewName3rd.setText(obj.getDatas().get(THIRD).getName());

                    mTextViewSubTitle1st.setText(activity.getResources().getString(R.string.bookings,
                            String.valueOf(obj.getDatas().get(FIRST).getBooks())));
                    mTextViewSubTitle2nd.setText(activity.getResources().getString(R.string.bookings,
                            String.valueOf(obj.getDatas().get(SECOND).getBooks())));
                    mTextViewSubTitle3rd.setText(activity.getResources().getString(R.string.bookings,
                            String.valueOf(obj.getDatas().get(THIRD).getBooks())));

                    mButtonRanking.setOnClickListener(view -> listener.onFullServiceButton());
                    break;

                case RankingObj.TOP_EMPLOYEE:
                    mButtonRanking.setText("Full Employee Rankings");

                    mTextViewName1st.setText(obj.getDatas().get(FIRST).getName());
                    mTextViewName2nd.setText(obj.getDatas().get(SECOND).getName());
                    mTextViewName3rd.setText(obj.getDatas().get(THIRD).getName());

                    mTextViewSubTitle1st.setText(obj.getDatas().get(FIRST).getRank());
                    mTextViewSubTitle2nd.setText(obj.getDatas().get(SECOND).getRank());
                    mTextViewSubTitle3rd.setText(obj.getDatas().get(THIRD).getRank());

                    mButtonRanking.setOnClickListener(view -> listener.onFullEmployeeButton());
                    break;

                case RankingObj.REPORT_VIEW:
                    mButtonRanking.setVisibility(View.INVISIBLE);

                    mTextViewName1st.setText(obj.getDatas().get(FIRST).getName());
                    mTextViewName2nd.setText(obj.getDatas().get(SECOND).getName());
                    mTextViewName3rd.setText(obj.getDatas().get(THIRD).getName());

                    mTextViewSubTitle1st.setText(activity.getResources().getString(R.string.peoples,
                            String.valueOf(obj.getDatas().get(FIRST).getBooks())));
                    mTextViewSubTitle2nd.setText(activity.getResources().getString(R.string.views,
                            String.valueOf(obj.getDatas().get(SECOND).getBooks())));
                    mTextViewSubTitle3rd.setText(activity.getResources().getString(R.string.views,
                            String.valueOf(obj.getDatas().get(THIRD).getBooks())));
                    break;
            }

        }
    }
}
