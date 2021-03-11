package com.example.zubayer.strudentsattendance;

import java.util.ArrayList;

/**
 * Created by Zubayer on 01-Apr-19.
 */

public class SectionDataModel {
    ArrayList<SingleItemModel> singleItemModels;
    String headertitle;

    public SectionDataModel(ArrayList<SingleItemModel> singleItemModels, String headertitle) {
        this.singleItemModels = singleItemModels;
        this.headertitle = headertitle;
    }

    public SectionDataModel() {
    }

    public ArrayList<SingleItemModel> getSingleItemModels() {
        return singleItemModels;
    }

    public void setSingleItemModels(ArrayList<SingleItemModel> singleItemModels) {
        this.singleItemModels = singleItemModels;
    }

    public String getHeaderTitle() {
        return headertitle;
    }

    public void setHeaderTitle(String headertitle) {
        this.headertitle = headertitle;
    }
}
