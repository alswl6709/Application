package org.techtown.application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

     public Context mContext;
     public ExpandableListView mListView;
     public List mModel;

    public ExpandableListViewAdapter(Context pContext, ExpandableListView pListView, List pModel) {
        this.mContext = pContext;
        this.mListView = pListView;
        this.mModel = pModel;
    }

    public void addItem(Child item,Parent parentData){
        if(!mModel.contains(parentData)){
            mModel.add(parentData);
        }
        int ind = mModel.indexOf(parentData);
        List isItems = ((Parent)mModel.get(ind)).getItems();
        isItems.add(item);
        ((Parent)mModel.get(ind)).setItems(isItems);
    }

    //ChildListView에 대한 method
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        //groupPosition과 childPosition을 통해 childList의 원소를 얻어옴
        List item = ((Parent)mModel.get(groupPosition)).getItems();
        return item.get(childPosition);
    }

    @Override
    //ChildList의 Id로 long형 값을 반환
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    //ChildList의 View
    //위 ParentList의 View를 얻을 때와 비슷하게 layout 연결 후
    //layout 내 TextView연결
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        Child item = (Child)getChild(groupPosition,childPosition);
        if(view == null){
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.child_listview,null);
        }
        TextView childText = (TextView)view.findViewById(R.id.childText);
        childText.setText(item.getChildData());
        return view;
    }

    @Override
    //ChildList의 크기를 int형으로 반환
    public int getChildrenCount(int groupPosition) {
        return ((Parent)mModel.get(groupPosition)).getItems().size();
    }

    @Override
    //ParentList의 position을 받아 해당 TextView에 반영될 String을 반환
    public Object getGroup(int groupPosition) {
        return mModel.get(groupPosition);
    }

    @Override
    //ParentList의 원소개수 반환
    public int getGroupCount() {
        return mModel.size();
    }

    @Override
    //ParentList의 position을 받아 long값으로 반환
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    //ParentList의 View
    public View getGroupView(int groupPosition, boolean isLastChild, View view, ViewGroup parent) {
        Parent parent1 = (Parent)getGroup(groupPosition);
        if(view == null){
            //ParentList의 layout 연결, root로 argument 중 parent를 받으며
            //root로 고정하지는 않음
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.parent_listview,null);
        }
        //ParentList의 Layout 연결 후 해당 layout에 내 TextView를 연결
        TextView parentText = (TextView)view.findViewById(R.id.parentText);
        parentText.setText(parent1.getData());
        return view;

    }

    @Override
    //stable ID인지 boolean 값으로 반환
    public boolean hasStableIds() {
        return true;
    }

    @Override
    //선택여부를 boolean 값으로 반환
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
