package com.example.lenovo.week100201;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Lenovo on 2017/4/15.
 */

public class ItemAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;
    int[] mIDs = { R.drawable.sample0
            , R.drawable.sample1
            , R.drawable.sample2
            , R.drawable.sample3
            , R.drawable.sample4
            , R.drawable.sample5
            , R.drawable.sample7
            , R.drawable.sample0
            , R.drawable.sample1
            , R.drawable.sample2
    };
    EditText ed;

    ItemAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
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
            convertView = mLayoutInflater.inflate(R.layout.item_layout, null);
            ImageView iv = (ImageView) convertView.findViewById(R.id.imageView2);
            iv.setImageResource(mIDs[position]);
            Button btn = (Button) convertView.findViewById(R.id.button);
            btn.setText("按键" + position);
            final EditText ed = (EditText) convertView.findViewById(R.id.editText);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

				
					// 以下为题4的代码
					//***********************************************
                    if (ed.getText() != null) {
                        Toast.makeText(mContext
                                , "item" + position + "的内容：" + ed.getText()
                                , Toast.LENGTH_SHORT).show();
                    }
					//************************************************

					// 以下为题5的代码
					//**********************************
                    //Intent intent = new Intent();
                    //intent.setAction("com.example.abc");//使用隐式启动
                    //intent.putExtra("imgid", mIDs[position]);
                    //intent.putExtra("msg", ed.getText().toString());
					//
                    //((Activity) mContext).startActivity(intent);
					//****************************************

                }
            });

        }

        return convertView;
    }
}
