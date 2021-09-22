package com.example.instrument.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.instrument.R;
import com.example.instrument.bean.InstrumentItem;
import com.example.instrument.model.MyAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String       TAG = "MainActivity";
    private static       MainActivity Instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Instance = this;

        GridView instrumentMenu = findViewById(R.id.instrumentMenu);
        ArrayList<InstrumentItem> items = new ArrayList<>();

        // TODO 乐器列表
        items.add(new InstrumentItem("Guitar", R.mipmap.guitar_icon, GuitarActivity.class));
        //items.add(new InstrumentItem("Guitar2", R.mipmap.guitar_icon, GuitarActivity.class));
        //items.add(new InstrumentItem("Guitar3", R.mipmap.guitar_icon, GuitarActivity.class));
        //items.add(new InstrumentItem("Guitar4", R.mipmap.guitar_icon, GuitarActivity.class));

        MyAdapter<InstrumentItem> myAdapter = new MyAdapter(items, R.layout.item_instrument) {
            @Override
            public void bindView(ViewHolder holder, Object obj) {
                InstrumentItem item = (InstrumentItem) obj;
                holder.setImageResource(R.id.instrument_img, item.getImgId());
                holder.setText(R.id.instrument_name, item.getName());
            }
        };

        instrumentMenu.setAdapter(myAdapter);
        instrumentMenu.setOnItemClickListener((parent, view, position, id) -> {
            Log.i(TAG, "onItemClick: " + myAdapter.getItem((int) id).getName());
            Intent intent = new Intent(Instance, myAdapter.getItem((int) id).getActivity());
            startActivity(intent);
        });
    }
}