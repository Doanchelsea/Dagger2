package com.example.dagger2_api_login.model.history;


import android.annotation.SuppressLint;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.chad.library.adapter.base.entity.SectionEntity;

import java.util.Objects;

public class TripSection extends SectionEntity<TripPackage> {

    public boolean isHeader;
    public String header;

    public TripSection(boolean isHeader, String header) {
        super(isHeader, header);
        this.isHeader = isHeader;
        this.header = header;
    }
    public TripSection(TripPackage trip) {
        super(trip);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripSection that = (TripSection) o;
        return isHeader == that.isHeader &&
                Objects.equals(header, that.header);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(isHeader, header);
    }

}
