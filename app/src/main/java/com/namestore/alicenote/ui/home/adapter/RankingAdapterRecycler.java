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
        holder.textView.setText(arrayList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private Button button;
        private CircleImageView imageView1st;
        private CircleImageView imageView2nd;
        private CircleImageView imageView3rd;
        private TextView name1st;
        private TextView name2nd;
        private TextView name3rd;
        private TextView subtTile1st;
        private TextView subtTile2nd;
        private TextView subtTile3rd;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.title);
            button = (Button) itemView.findViewById(R.id.btn_ranking);
            imageView1st = (CircleImageView) itemView.findViewById(R.id.image_1st);
            imageView2nd = (CircleImageView) itemView.findViewById(R.id.image_2nd);
            imageView3rd = (CircleImageView) itemView.findViewById(R.id.image_3rd);
            name1st = (TextView) itemView.findViewById(R.id.name_1st);
            name2nd = (TextView) itemView.findViewById(R.id.name_2nd);
            name3rd = (TextView) itemView.findViewById(R.id.name_3rd);
            subtTile1st = (TextView) itemView.findViewById(R.id.subtile_1st);
            subtTile2nd = (TextView) itemView.findViewById(R.id.subtile_2nd);
            subtTile3rd = (TextView) itemView.findViewById(R.id.subtile_3rd);
        }

        public void bindData(RankingObj obj) {
            switch (obj.getTAG()) {
                case RankingObj.TOP_SERVICES:
                    button.setText("Full Service Rankings");
                    imageView1st.setVisibility(View.GONE);
                    imageView2nd.setVisibility(View.GONE);
                    imageView3rd.setVisibility(View.GONE);

                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(40, 10, 0, 0); //margin Left 40dp & Top 10
                    name1st.setLayoutParams(layoutParams);
                    name2nd.setLayoutParams(layoutParams);
                    name3rd.setLayoutParams(layoutParams);

                    name1st.setText(obj.getDatas().get(FIRST).getName());
                    name2nd.setText(obj.getDatas().get(SECOND).getName());
                    name3rd.setText(obj.getDatas().get(THIRD).getName());

                    subtTile1st.setText(activity.getResources().getString(R.string.bookings,
                            String.valueOf(obj.getDatas().get(FIRST).getBooks())));
                    subtTile2nd.setText(activity.getResources().getString(R.string.bookings,
                            String.valueOf(obj.getDatas().get(SECOND).getBooks())));
                    subtTile3rd.setText(activity.getResources().getString(R.string.bookings,
                            String.valueOf(obj.getDatas().get(THIRD).getBooks())));

                    button.setOnClickListener(view -> listener.onFullServiceButton());
                    break;

                case RankingObj.TOP_EMPLOYEE:
                    button.setText("Full Employee Rankings");

                    name1st.setText(obj.getDatas().get(FIRST).getName());
                    name2nd.setText(obj.getDatas().get(SECOND).getName());
                    name3rd.setText(obj.getDatas().get(THIRD).getName());

                    subtTile1st.setText(obj.getDatas().get(FIRST).getRank());
                    subtTile2nd.setText(obj.getDatas().get(SECOND).getRank());
                    subtTile3rd.setText(obj.getDatas().get(THIRD).getRank());

                    button.setOnClickListener(view -> listener.onFullEmployeeButton());
                    break;

                case RankingObj.REPORT_VIEW:
                    button.setVisibility(View.INVISIBLE);

                    name1st.setText(obj.getDatas().get(FIRST).getName());
                    name2nd.setText(obj.getDatas().get(SECOND).getName());
                    name3rd.setText(obj.getDatas().get(THIRD).getName());

                    subtTile1st.setText(activity.getResources().getString(R.string.peoples,
                            String.valueOf(obj.getDatas().get(FIRST).getBooks())));
                    subtTile2nd.setText(activity.getResources().getString(R.string.views,
                            String.valueOf(obj.getDatas().get(SECOND).getBooks())));
                    subtTile3rd.setText(activity.getResources().getString(R.string.views,
                            String.valueOf(obj.getDatas().get(THIRD).getBooks())));
                    break;
            }

        }
    }
}
