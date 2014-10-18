package com.vyas.customViews;

import java.util.ArrayList;
import com.example.androidchallenge.R;
import com.vyas.androidchallenge.MainActivity;
import com.vyas.androidchallenge.ShowEditScreen;
import com.vyas.myClasses.ListDB;
import com.vyas.myClasses.ListPojo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CoolListAdapter extends ArrayAdapter<ListPojo> {
    ArrayList<ListPojo> mTaskList;
    
    final Context mContext;

	private ListDB datasource;

    public CoolListAdapter(Context context, ArrayList<ListPojo> tasks) {
        super(context, R.layout.a_list_row, tasks);
        mContext = context;
        mTaskList = new ArrayList<ListPojo>();
        mTaskList = tasks;
        datasource = new ListDB(context);
    }

    public class StringViewHolder {
        public RelativeLayout preview_layout;
        public ImageView delete;
        public TextView content;
    }

    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StringViewHolder StringViewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.a_list_row, null);

            StringViewHolder = new StringViewHolder();
            StringViewHolder.preview_layout = (RelativeLayout) convertView.findViewById(R.id.task_wrap);
            StringViewHolder.delete = (ImageView) convertView.findViewById(R.id.menu_delete);
            StringViewHolder.content = (TextView) convertView.findViewById(R.id.task_text);
        }else{
            StringViewHolder = (StringViewHolder) convertView.getTag();
        }

        final ListPojo task = mTaskList.get(position);

        StringViewHolder.content.setText(task.getTaskStr());
        //StringViewHolder.delete.setTag(new Integer(position));
        
        int taskThemeColor = task.getThemeColor();
    		if(taskThemeColor == ShowEditScreen.RED){
    			StringViewHolder.preview_layout.setBackgroundColor(mContext.getResources().getColor(R.color.red_bg));
    			StringViewHolder.content.setBackgroundColor(mContext.getResources().getColor(R.color.red_bg));
    			StringViewHolder.content.setTextColor(mContext.getResources().getColor(R.color.red_text));
    		}else if(taskThemeColor == ShowEditScreen.ORANGE){
    			StringViewHolder.preview_layout.setBackgroundColor(mContext.getResources().getColor(R.color.orange_bg));
    			StringViewHolder.content.setBackgroundColor(mContext.getResources().getColor(R.color.orange_bg));
    			StringViewHolder.content.setTextColor(mContext.getResources().getColor(R.color.orange_text));
    		}else if(taskThemeColor == ShowEditScreen.GREEN){
    			StringViewHolder.preview_layout.setBackgroundColor(mContext.getResources().getColor(R.color.green_bg));
    			StringViewHolder.content.setBackgroundColor(mContext.getResources().getColor(R.color.green_bg));
    			StringViewHolder.content.setTextColor(mContext.getResources().getColor(R.color.green_text));
    		}else if(taskThemeColor == ShowEditScreen.BLUE){
    			StringViewHolder.preview_layout.setBackgroundColor(mContext.getResources().getColor(R.color.blue_bg));
    			StringViewHolder.content.setBackgroundColor(mContext.getResources().getColor(R.color.blue_bg));
    			StringViewHolder.content.setTextColor(mContext.getResources().getColor(R.color.blue_text));
    		}else if(taskThemeColor == ShowEditScreen.PURPLE){
    			StringViewHolder.preview_layout.setBackgroundColor(mContext.getResources().getColor(R.color.purple_bg));
    			StringViewHolder.content.setBackgroundColor(mContext.getResources().getColor(R.color.purple_bg));
    			StringViewHolder.content.setTextColor(mContext.getResources().getColor(R.color.purple_text));
    		}
    		
        StringViewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	mTaskList.remove(task);
            	datasource.open();
            		datasource.deleteTask(task);
            	datasource.close();
                MainActivity.listAdapter.remove(task);
                MainActivity.listAdapter.notifyDataSetChanged();
            }
        });
        convertView.setTag(StringViewHolder);
        return convertView;
    }
    

}