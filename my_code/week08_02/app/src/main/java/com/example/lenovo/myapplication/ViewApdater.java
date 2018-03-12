package com.example.lenovo.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Lenovo on 2017/3/28.
 */

public class ViewApdater extends BaseAdapter {

    private int[] mIDs = { R.drawable.sample0
            , R.drawable.sample1
            , R.drawable.sample2
            , R.drawable.sample3
            , R.drawable.sample4
            , R.drawable.sample5
            , R.drawable.sample6
            , R.drawable.sample7
            , R.drawable.sample0
    };
    private Context mContext;
    private LayoutInflater mInflater;
    ViewApdater(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return mIDs.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.item_layout, null);
            ImageView iv = (ImageView) convertView.findViewById(R.id.imageView);
            iv.setImageResource(mIDs[position]);
            Button btn = (Button) convertView.findViewById(R.id.button);
            btn.setText("按键" + position);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext
                            , "序号" + position
                            , Toast.LENGTH_LONG).show();
                }
            });

        }
        return convertView;
    }
}
