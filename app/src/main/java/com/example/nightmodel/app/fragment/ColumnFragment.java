package com.example.nightmodel.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.*;
import android.widget.Toast;

import com.example.nightmodel.R;


/**
 * 栏目
 * Email:brokge@gmail.com
 * Created by User:chenlw on 2014/12/24.
 */
public class ColumnFragment extends BaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_column,null);
        return view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
       getActivity().getMenuInflater().inflate(R.menu.menu_column, menu);
       super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_recyle:
                Toast.makeText(getActivity(),"同步",1).show();
            case R.id.action_edit:
                Toast.makeText(getActivity(),"编辑",1).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
