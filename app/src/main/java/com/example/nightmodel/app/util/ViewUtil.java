package com.example.nightmodel.app.util;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Email:chenlw@dxyer.com
 * Created by User:chenlw on 2014/10/31.
 */
public class ViewUtil {


    public static List<View> getAllChildView(View view) {
        List<View> allChildView = new ArrayList<View>();
        if (view instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) view;
            for (int i = 0; i < vg.getChildCount(); i++) {
                View viewChild = vg.getChildAt(i);
                allChildView.add(viewChild);
                allChildView.addAll(getAllChildView(viewChild));
            }
        }
        return allChildView;
    }

    public static List<TextView> getAllTextView(List<View> views) {
        List<TextView> allTextViewList = new ArrayList<TextView>();
        for (View view : views) {
            if (view instanceof CheckBox) {
            } else if (view instanceof TextView) {
                allTextViewList.add((TextView) view);
            }
        }
        return allTextViewList;
    }

    private static List<View> getViewList(List<View> allChildViews) {
        List<View> allViewsList = new ArrayList<View>();
        for (View child : allChildViews) {
            if ((child instanceof ViewGroup)||(child instanceof ImageView)||(child instanceof TextView)) {
            } else if (child instanceof View) {
                allViewsList.add(child);
            }
        }
        return allViewsList;
    }

    public static List<View> getViewList(View view) {
        return getViewList(getAllChildView(view));
    }

    public static List<View> getViewListByTag(List<View> viewList,String tag){
        List<View> tagViewList=new ArrayList<View>();
        for(View view:viewList){
            if(view.getTag()!=null&&view.getTag().equals(tag)){
                tagViewList.add(view);
            }
        }
        return tagViewList;
    }
    public static List<ImageView> getallImageView(List<View> views){
        List<ImageView> allTextViewList = new ArrayList<ImageView>();
        for (View view : views) {
            if (view instanceof CheckBox) {
            } else if (view instanceof ImageView) {
                allTextViewList.add((ImageView) view);
            }
        }
        return allTextViewList;
    }
    public static void setViewBackgroundAlpha(ActionBar view, int baseColor) {
        int rgb = baseColor;
        Drawable drawable = new ColorDrawable(rgb);
        if(view!=null)
            view.setBackgroundDrawable(drawable);
    }
}
