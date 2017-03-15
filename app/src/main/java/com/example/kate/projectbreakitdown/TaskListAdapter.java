package com.example.kate.projectbreakitdown;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Kate on 12/03/2017.
 */

public class TaskListAdapter implements ExpandableListAdapter {

    private Context context;
    private ArrayList<Task> task_parent;
    private HashMap<String,Action> task_children;

    public TaskListAdapter(Context context, ArrayList<Task> task_parent, HashMap<String, Action> task_children){
        this.context = context;
        this.task_parent = task_parent;
        this.task_children = task_children;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return task_parent.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<Action> task_children = task_parent.get(groupPosition).getActions();
        return task_children.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return task_parent.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        //ArrayList<Action> task_children = task_parent.get(groupPosition).getActions();
        return task_children.get(task_parent.get(groupPosition).getTask_title());
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
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Task taskInfo = (Task) getGroup(groupPosition);
        if (convertView == null){
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.task_view, null);
        }

        TextView task_title = (TextView) convertView.findViewById(R.id.task_title);
        task_title.setText(taskInfo.getTask_title().trim());
        TextView task_duedate = (TextView) convertView.findViewById(R.id.task_duedate);
        task_duedate.setText("Due: " + taskInfo.getTask_duedate().toString());
        TextView task_priority = (TextView) convertView.findViewById(R.id.task_priority);
        task_priority.setText("Priority: " + taskInfo.getTask_priority());
        TextView task_length = (TextView) convertView.findViewById(R.id.task_length);
        task_length.setText("Length (min): " + taskInfo.getTask_length());
        TextView task_description = (TextView) convertView.findViewById(R.id.task_description);
        task_description.setText(taskInfo.getTask_description());
        //NEED TO ADD IMAGE SETTER
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Action actionInfo = (Action) getChild(groupPosition, childPosition);
        if (convertView == null){
            LayoutInflater childinf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = childinf.inflate(R.layout.action_view, null);
        }
        TextView action_name = (TextView) convertView.findViewById(R.id.action_title);
        action_name.setText(actionInfo.getName().trim());
        TextView action_priority = (TextView) convertView.findViewById(R.id.action_priority);
        action_priority.setText("Priority: " + actionInfo.getPriority());
        TextView action_length = (TextView) convertView.findViewById(R.id.action_length);
        action_length.setText("Length (min): " + actionInfo.getLength());
        TextView action_description = (TextView) convertView.findViewById(R.id.action_description);
        action_description.setText(actionInfo.getDescription());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }
}
