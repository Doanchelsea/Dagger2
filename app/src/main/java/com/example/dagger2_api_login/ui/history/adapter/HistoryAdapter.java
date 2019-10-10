package com.example.dagger2_api_login.ui.history.adapter;

import android.util.Log;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dagger2_api_login.R;
import com.example.dagger2_api_login.contract.AppConstants;
import com.example.dagger2_api_login.model.history.History;
import com.example.dagger2_api_login.model.history.ListPickUpPoint;
import com.example.dagger2_api_login.model.history.TripPackage;
import com.example.dagger2_api_login.model.history.TripSection;
import com.example.dagger2_api_login.untils.DateUtils;
import com.example.dagger2_api_login.untils.StringUtils;

import java.util.List;

public class HistoryAdapter extends BaseSectionQuickAdapter<TripSection, BaseViewHolder> {

    public HistoryAdapter() {
        super(R.layout.item_history_content, R.layout.item_history_header, null );
    }


    @Override
    protected void convert(BaseViewHolder holder, TripSection tripSection) {
        preventScrollChangeDate(holder);
        List<ListPickUpPoint> histories = tripSection.t.getListPickUpPoint();
        int sizeList = histories.size();
        if (sizeList == 1) {
            loadCreateTimeTrip(holder, DateUtils.convertMiliToTime(tripSection.t.getCreatedDate()));
            loadPickUpPointAddress(holder, tripSection.t.getListPickUpPoint().get(0).getAddress());
            loadBackgroundTripStatus(holder, tripSection.t.getTripPackageStatus());


            gone(holder, R.id.item_history_content_iv_dot_one,
                    R.id.item_history_content_iv_dot_two,
                    R.id.item_history_content_iv_marker_drof_off_one,
                    R.id.item_history_content_iv_marker_drof_off_two,
                    R.id.item_history_content_tv_drof_off_one,
                    R.id.item_history_content_tv_drof_off_two,
                    R.id.item_history_content_view_divider_one,
                    R.id.item_history_content_view_divider_two);

        } else if (sizeList == 2) {
            loadCreateTimeTrip(holder, DateUtils.convertMiliToTime(tripSection.t.getCreatedDate()));
            loadPickUpPointAddress(holder, tripSection.t.getListPickUpPoint().get(0).getAddress());
            loadDrofOffOneAddress(holder, tripSection.t.getListPickUpPoint().get(1).getAddress());
            loadBackgroundTripStatus(holder, tripSection.t.getTripPackageStatus());


            gone(holder,
                    R.id.item_history_content_iv_dot_two,
                    R.id.item_history_content_iv_marker_drof_off_two,
                    R.id.item_history_content_tv_drof_off_two,
                    R.id.item_history_content_view_divider_two);

        } else if (sizeList == 3) {
            loadCreateTimeTrip(holder, DateUtils.convertMiliToTime(tripSection.t.getCreatedDate()));
            loadPickUpPointAddress(holder, tripSection.t.getListPickUpPoint().get(0).getAddress());
            loadDrofOffOneAddress(holder, tripSection.t.getListPickUpPoint().get(1).getAddress());
            loadDrofOffTwoAddress(holder, tripSection.t.getListPickUpPoint().get(2).getAddress());
            loadBackgroundTripStatus(holder, tripSection.t.getTripPackageStatus());
        }
    }

    @Override
    protected void convertHead(BaseViewHolder holder, TripSection tripSection) {
        preventScrollChangeDate(holder);
        loadCreateDateTrip(holder,tripSection.header);
    }

    private void preventScrollChangeDate(BaseViewHolder holder) {
        holder.setIsRecyclable(false);
    }

    private void loadCreateDateTrip(BaseViewHolder holder, String createDated) {
        if (StringUtils.isEmpty(createDated)) {
            return;
        }
        holder.setText(R.id.item_history_header_tv_create_date, createDated);
    }

    private void loadCreateTimeTrip(BaseViewHolder holder, String createTimed) {
        if (StringUtils.isEmpty(createTimed)) {
            return;
        }
        holder.setText(R.id.item_history_content_tv_create_time, createTimed);
    }

    private void loadPickUpPointAddress(BaseViewHolder holder, String pickUpPointAddress) {
        holder.setText(R.id.item_history_content_tv_pickup_point, pickUpPointAddress);
    }

    private void loadDrofOffOneAddress(BaseViewHolder holder, String drofOffOneAddress) {
        holder.setText(R.id.item_history_content_tv_drof_off_one, drofOffOneAddress);
    }

    private void loadDrofOffTwoAddress(BaseViewHolder holder, String drofOffTwoAddress) {
        holder.setText(R.id.item_history_content_tv_drof_off_two, drofOffTwoAddress);
    }

    private void loadBackgroundTripStatus(BaseViewHolder holder, int tripStatus) {
        if (tripStatus == AppConstants.TRIP_STATUS_69) {
            holder.setBackgroundRes(R.id.item_history_content_tv_trip_status, R.color.history_color_bg_trip_status_ok);
            holder.setText(R.id.item_history_content_tv_trip_status, R.string.history_label_trip_status_success);
        } else {
            holder.setBackgroundRes(R.id.item_history_content_tv_trip_status, R.color.history_color_bg_trip_status_cancel);
            holder.setText(R.id.item_history_content_tv_trip_status, R.string.history_label_trip_status_cancel);
        }
    }

    private void gone(BaseViewHolder holder, int... views) {
        if (views != null && views.length > 0) {
            for (int id : views) {
                holder.setGone(id, false);
            }
        }
    }
}
