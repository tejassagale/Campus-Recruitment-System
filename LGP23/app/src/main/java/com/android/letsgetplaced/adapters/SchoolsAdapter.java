package com.android.letsgetplaced.adapters;

import android.support.annotation.NonNull;

import com.android.letsgetplaced.datamodel.School;

import java.util.List;

/**
 * Created by ibrahim on 1/19/18.
 */

public class SchoolsAdapter extends ResumeEventAdapter<School> {

    public SchoolsAdapter(@NonNull List<School> list,
                          ResumeEventOnClickListener resumeEventOnClickListener) {
        super(list, resumeEventOnClickListener);
    }
}