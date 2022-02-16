package com.hestingames.impsadventuresim.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hestingames.impsadventuresim.R;

public class CustomSpinnerAdapter extends BaseAdapter {
    Context context;
    int icons[];
    String[] cardNames;
    LayoutInflater inflter;

    public CustomSpinnerAdapter(Context applicationContext, int[] icons, String[] cardNames) {
        this.context = applicationContext;
        this.icons = icons;
        this.cardNames = cardNames;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return icons.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.tarot_card_spinner, null);
        ImageView icon = (ImageView) view.findViewById(R.id.imageView);
        TextView names = (TextView) view.findViewById(R.id.textView);
        icon.setImageResource(icons[i]);
        names.setText(cardNames[i]);
        return view;
    }
}