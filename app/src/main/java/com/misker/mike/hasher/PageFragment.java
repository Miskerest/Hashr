package com.misker.mike.hasher;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * Created by Mike on 3/3/17.
 * Supporting class for paginated main activity
 */

public class PageFragment extends Fragment {

    private static final String ARG_PAGE_NUMBER = "page_number";

    public PageFragment() {
    }

    static PageFragment newInstance(int page) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE_NUMBER, page);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_main, container, false);
        int page;
        TextView txt = new TextView(getActivity());

        try {
            //noinspection ConstantConditions
            page = getArguments().getInt(ARG_PAGE_NUMBER, -1);
        }
        catch (java.lang.NullPointerException e) {
            page = -1;
        }

        txt.setText(String.format(Locale.getDefault(), "Page %d", page));
        return rootView;
    }
}
