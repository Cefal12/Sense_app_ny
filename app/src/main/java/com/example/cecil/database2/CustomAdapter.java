package com.example.cecil.database2;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textservice.TextInfo;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.cecil.database2.R.drawable.no_image_available;

class CustomAdapter extends ArrayAdapter<Mad> {

    CustomAdapter(Context context, Mad[] resource){
        super(context, R.layout.custom_row_view, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater mInflater = LayoutInflater.from(getContext());
        View customView = mInflater.inflate(R.layout.custom_row_view, parent, false);

        Mad singleItem = getItem(position);
        TextView mma = (TextView) customView.findViewById(R.id.mma);
        TextView date = (TextView) customView.findViewById(R.id.date);
        TextView hf12 = (TextView) customView.findViewById(R.id.hf12);
        TextView hf3 = (TextView) customView.findViewById(R.id.hf3);
        TextView hf4 = (TextView) customView.findViewById(R.id.hf4);
        TextView fedt = (TextView) customView.findViewById(R.id.fedt);
        ImageView image = (ImageView) customView.findViewById(R.id.image);

        date.setText(singleItem.getDato());
        mma.setText(singleItem.getMMA());
        hf12.setText("HF1+2:"+ " "+singleItem.getHF12());
        hf3.setText("HF3:"+ " "+singleItem.getHF3());
        hf4.setText("HF4:"+ " "+singleItem.getHF4());
        fedt.setText("Fedt:"+ " "+singleItem.getFedt());

        if(singleItem.getFoto() == null){
            //image.setImageResource(no_image_available);
        }
        else {
            Bitmap bmp = BitmapFactory.decodeByteArray(singleItem.getFoto(), 0, singleItem.getFoto().length);
            image.setImageBitmap(bmp);
        }

        return customView;
    }
}
