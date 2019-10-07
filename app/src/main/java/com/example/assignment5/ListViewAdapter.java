package com.example.assignment5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


class ListViewAdapter extends ArrayAdapter<Demo> {
        private List<Demo> demoList;
        private Context c;

        public ListViewAdapter(List<Demo> demoList, Context c) {
            super(c, R.layout.list_items, demoList);
            this.demoList = demoList;
            this.c = c;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(c);

            View listViewItem = inflater.inflate(R.layout.list_items, null, true);
            TextView txtNimi = (TextView) listViewItem.findViewById(R.id.txtNimi);
            TextView txtPvm =(TextView) listViewItem.findViewById(R.id.txtPvm);

            Demo demo = demoList.get(position);
            txtNimi.setText(demo.getNimi());
            txtPvm.setText(demo.getPvm());

            return listViewItem;
        }

    }
