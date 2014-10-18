package com.vyas.androidchallenge;

import java.util.ArrayList;
import java.util.List;

import com.example.androidchallenge.R;
import com.vyas.swipeHelper.*;
import com.vyas.customViews.CoolListAdapter;
import com.vyas.myClasses.ListDB;
import com.vyas.myClasses.ListPojo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends Activity {
	private ImageView editbtn;
	private ListView taskList;
	public static CoolListAdapter listAdapter ;
	private int taskThemeColor;

	private ListDB datasource;
	private List<ListPojo> mList;
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_screen);
		
		editbtn = (ImageView)findViewById(R.id.btn_edit);
		taskList = (ListView)findViewById(R.id.mainListView);
		ArrayList<ListPojo> simpleList = new ArrayList<ListPojo>(); 
		listAdapter = new CoolListAdapter(this, simpleList);  
		
		datasource = new ListDB(this);
	    datasource.open();
	    	mList = datasource.getAllTasksList();
	    datasource.close();
 
	    for(int i=0;i<mList.size();i++){
	    	listAdapter.add(mList.get(i));
	    }
	    
		taskList.setAdapter( listAdapter );
	    
		SwipeDismissListViewTouchListener touchListener =
                new SwipeDismissListViewTouchListener(
                		taskList,
                        new SwipeDismissListViewTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                	ListPojo taskTodelete = listAdapter.getItem(position);

                                	datasource.open();
                                		datasource.deleteTask(taskTodelete);
                                	datasource.close();
                                    listAdapter.remove(taskTodelete);
                                }
                                listAdapter.notifyDataSetChanged();
                            }
                        });
		taskList.setOnTouchListener(touchListener);
        // Setting this scroll listener is required to ensure that during ListView scrolling,
        // we don't look for swipes.
		taskList.setOnScrollListener(touchListener.makeScrollListener());
		editbtn.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		 	    Intent startEdit = new Intent(MainActivity.this, ShowEditScreen.class);
		 	    startActivityForResult(startEdit,1);
			}
	    });
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == 1) {
	        if(resultCode == RESULT_OK){
	            String newTaskStr;
	            newTaskStr = data.getStringExtra(ShowEditScreen.NEW_TASK);
	            taskThemeColor = data.getIntExtra(ShowEditScreen.COLOR, ShowEditScreen.RED);
	            
	            ListPojo newTask = new ListPojo();
	            
	            datasource.open();
	            	newTask = datasource.createNewTask(newTaskStr, taskThemeColor);
			    datasource.close();
			    
//			  Toast.makeText(getApplicationContext(),
//					  newTask.getId()+"\n"+newTask.getTaskStr() , Toast.LENGTH_LONG).show();
				
	            listAdapter.add(newTask);
	            listAdapter.notifyDataSetChanged();
	        }
	        if (resultCode == RESULT_CANCELED) {
	        }
	    }
	}
	
}
