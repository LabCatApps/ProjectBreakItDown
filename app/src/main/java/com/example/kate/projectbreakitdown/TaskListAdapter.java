package com.example.kate.projectbreakitdown;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Created by Kate on 12/03/2017.
 */

public class TaskListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private final ArrayList<Task> task_list;
    private final LayoutInflater inflater;

    public TaskListAdapter(Context context, ArrayList<Task> task_list){
        this.context = context;
        this.task_list = task_list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Task task = (Task) getGroup(groupPosition);
        if (convertView == null){
            convertView = inflater.inflate(R.layout.task_view,null);
        }

        TextView title = (TextView) convertView.findViewById(R.id.task_title);
        title.setText(task.getTask_title().trim());
        TextView description = (TextView) convertView.findViewById(R.id.task_description);
        description.setText(task.getTask_description().trim());
        TextView duedate = (TextView) convertView.findViewById(R.id.task_duedate);
        duedate.setText("Due: " + task.getTask_duedate().trim());
        TextView priority = (TextView) convertView.findViewById(R.id.task_priority);
        priority.setText("Priority: " + task.getTask_priority().trim());
        TextView length = (TextView) convertView.findViewById(R.id.task_length);
        length.setText("Length (mins): " + task.getTask_length().trim());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Action action = (Action) getChild(groupPosition,childPosition);
        if (convertView == null){
            convertView = inflater.inflate(R.layout.action_view, null);
        }
        TextView title = (TextView) convertView.findViewById(R.id.action_title);
        title.setText(action.getName().trim());
        TextView description = (TextView) convertView.findViewById(R.id.action_description);
        description.setText(action.getDescription().trim());
        TextView priority = (TextView) convertView.findViewById(R.id.action_priority);
        priority.setText("Priority: " + action.getPriority().trim());
        TextView length = (TextView) convertView.findViewById(R.id.action_length);
        length.setText("Length: " + action.getLength().trim());

        return convertView;

    }

    @Override
    public int getGroupCount() {
        return task_list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return task_list.get(groupPosition).getActions().size();
    }

    @Override
    public Task getGroup(int groupPosition) {
        return task_list.get(groupPosition);
    }

    @Override
    public Action getChild(int groupPosition, int childPosition) {
        return task_list.get(groupPosition).getActions().get(childPosition);

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
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
