package com.example.nightmodel.app.util;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import org.apache.http.util.EncodingUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.InputStream;
import java.util.Set;

/**
 * Email:chenlw@dxyer.com
 * Created by User:chenlw on 2014/12/23.
 */
public class AppUtil {

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int dp2px(Context context, float f) {
        return (int) (0.5F + f * context.getResources().getDisplayMetrics().density);
    }

    public static File getImageFile(Context context, Intent data) {
        Uri selectedImage = data.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picPath = cursor.getString(columnIndex);
        cursor.close();
        return new File(picPath);
    }

    public static Bitmap decodeSampledBitmapFromFile(String filePath, int inSampleSize) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        options.inSampleSize = inSampleSize;
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    public static String[] getProvinces(Context context) {
        String[] provinces = null;
        try {
            JSONArray jsonAll = new JSONArray(getAreaInfo(context));
            provinces = new String[jsonAll.length()];
            for (int i = 0; i < jsonAll.length(); i++) {
                JSONObject oneJsonObject = (JSONObject) jsonAll.get(i);
                String oneArea = oneJsonObject.getString("label");
                provinces[i] = oneArea;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return provinces;
    }

    public static String[] getCitys(Context context, String provinceName) {
        String[] citys = null;
        if (provinceName == null || provinceName.equals("")) {
            provinceName = getProvinces(context)[0];
        }

        try {
            JSONArray jsonAll = new JSONArray(getAreaInfo(context));
            for (int i = 0; i < jsonAll.length(); i++) {
                JSONObject oneJsonObject = (JSONObject) jsonAll.get(i);
                String oneArea = oneJsonObject.getString("label");
                if (oneArea.equals(provinceName)) {
                    JSONArray oneAreaJsonArray = new JSONArray(
                            oneJsonObject.getString("children"));
                    citys = new String[oneAreaJsonArray.length()];
                    for (int k = 0; k < oneAreaJsonArray.length(); k++) {
                        JSONObject twoJsonObject = (JSONObject) oneAreaJsonArray
                                .get(k);
                        String twoArea = twoJsonObject.getString("label");
                        citys[k] = twoArea;
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return citys;
    }


    /**
     * 根据选择的一级和二级地市获取相应的国家地区的编码
     *
     * @param context
     * @param oneAreaName 一级地市
     * @param twoAreaName 二级地市
     * @return
     */
    public static String getLocationCode(Context context, String oneAreaName, String twoAreaName) {
        String locationCode = null;
        try {
            JSONArray jsonAll = new JSONArray(getAreaInfo(context));
            for (int i = 0; i < jsonAll.length(); i++) {
                JSONObject oneJsonObject = (JSONObject) jsonAll.get(i);
                String oneArea = oneJsonObject.getString("label");
                if (oneArea.equals(oneAreaName)) {
                    JSONArray oneAreaJsonArray = new JSONArray(oneJsonObject.getString("children"));
                    for (int k = 0; k < oneAreaJsonArray.length(); k++) {
                        JSONObject twoJsonObject = (JSONObject) oneAreaJsonArray.get(k);
                        String twoArea = twoJsonObject.getString("label");
                        if (twoArea.equals(twoAreaName)) {
                            locationCode = twoJsonObject.getString("key");
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return locationCode;
    }

    private static String getAreaInfo(Context context) {
        String result = null;
        try {
            InputStream in = context.getResources().getAssets().open("location.js");
            int lenght = in.available();
            byte[] buffer = new byte[lenght];
            in.read(buffer);
            result = EncodingUtils.getString(buffer, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getRecommendOftenDrugs(Context context) {
        String result = null;
        try {
            InputStream in = context.getResources().getAssets().open("recommend-often-drug.txt");
            int lenght = in.available();
            byte[] buffer = new byte[lenght];
            in.read(buffer);
            result = EncodingUtils.getString(buffer, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 动态设置ListView的高度
     *
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        if (listView == null) return;

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public static boolean isNotEmpty(Set<String> set) {
        if (set != null && !set.isEmpty()) {
            return true;
        }
        return false;
    }

}