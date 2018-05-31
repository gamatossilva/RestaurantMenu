package com.example.guilh.restaurantmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class RestaurantMenuAdapter extends BaseExpandableListAdapter {

    private List<String> listGroups;
    private Map<String, List<RestaurantMenuItems>> listItemsGroup;
    private Context context;
    ImageView arrowImageView;

    public RestaurantMenuAdapter(Context context, List<String> groups, Map<String, List<RestaurantMenuItems>> itemsGroup) {
        this.context = context;
        listGroups = groups;
        listItemsGroup = itemsGroup;
    }

    @Override
    public int getGroupCount() {
        return listGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listItemsGroup.get(getGroup(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listGroups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listItemsGroup.get(getGroup(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.group, null);
        }

        arrowImageView = convertView.findViewById(R.id.arrowImageView);
        arrowImageView.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
        if (isExpanded) {
            arrowImageView.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
        }
        TextView tvGroup = (TextView) convertView.findViewById(R.id.tvGroup);
        tvGroup.setText(getGroup(groupPosition).toString());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.group_item, null);
        }

        TextView tvItemName = (TextView) convertView.findViewById(R.id.nameTextView);
        TextView tvItemDescription = (TextView) convertView.findViewById(R.id.descriptionTextView);
        TextView tvItemPrice = (TextView) convertView.findViewById(R.id.priceTextView);

        RestaurantMenuItems restaurantMenuItems = (RestaurantMenuItems) getChild(groupPosition, childPosition);

        tvItemName.setText(restaurantMenuItems.getItemName());
        tvItemName.setPadding(16, 4, 16, 0);

        tvItemDescription.setText(restaurantMenuItems.getItemDescription());
        tvItemDescription.setPadding(16, 4, 16, 0);

        tvItemPrice.setText(restaurantMenuItems.getItemPrice());
        tvItemPrice.setPadding(16, 4, 16, 0);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    
}
