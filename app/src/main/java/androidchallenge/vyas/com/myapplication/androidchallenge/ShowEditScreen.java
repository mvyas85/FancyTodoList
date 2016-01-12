package androidchallenge.vyas.com.myapplication.androidchallenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidchallenge.vyas.com.myapplication.R;

public class ShowEditScreen extends Activity {

	private Button submit;
	private EditText newTask;
	private View overlay;
	private ImageView red,orange,green,blue,purple;
	private Context context;
	private int selectedColor;
	
	public static final String NEW_TASK = "NEW_TASK";
	public static final String COLOR = "COLOR";
	public static final int RED =0;
	public static final int ORANGE =1;
	public static final int GREEN =2;
	public static final int BLUE =3;
	public static final int PURPLE =4;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_edit_screen);
		
		context = getApplicationContext();
		submit = (Button)findViewById(R.id.btn_submit);
		newTask = (EditText)findViewById(R.id.et_newTask);
		overlay = (View)findViewById(R.id.overlay_layout);
		
		red = (ImageView)findViewById(R.id.img_red);
		orange = (ImageView)findViewById(R.id.img_orange);
		green = (ImageView)findViewById(R.id.img_green);
		blue = (ImageView)findViewById(R.id.img_blue);
		purple = (ImageView)findViewById(R.id.img_purple);
		
		setSelected(red);
		selectedColor = RED;
		
		submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String newTaskStr = newTask.getText().toString();
				if(newTaskStr.equals("") || newTaskStr.trim().equals("")){
					Toast.makeText(getApplicationContext(),
							  "Please Enter some text!" , Toast.LENGTH_LONG).show();
					Intent intent = getIntent();;
					setResult(RESULT_CANCELED, intent);
					finish();
				}
				Intent intent = getIntent();
				intent.putExtra(ShowEditScreen.NEW_TASK, newTaskStr);
				intent.putExtra(ShowEditScreen.COLOR, selectedColor);
				setResult(RESULT_OK, intent);
				finish();
			}
		});
		overlay.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = getIntent();
				setResult(RESULT_CANCELED, intent);
				finish();
			}
		});
		
		red.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setSelected((ImageView)v);
			}
		});
		orange.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setSelected((ImageView)v);
			}
		});
		green.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setSelected((ImageView)v);
			}
		});
		blue.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setSelected((ImageView)v);
			}
		});
		purple.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setSelected((ImageView)v);
				
			}
		});
	}
	
	public void setSelected(ImageView view){
		//Toast.makeText(context,view.getId()+" and "+red.getId()+"" , Toast.LENGTH_LONG).show();
		if(view.getId() == red.getId()){
			red.setBackground(getResources().getDrawable(R.drawable.border_image_red));
			selectedColor = RED;
			orange.setBackgroundColor(getResources().getColor(R.color.orange));
			green.setBackgroundColor(getResources().getColor(R.color.green));
			blue.setBackgroundColor(getResources().getColor(R.color.blue));
			purple.setBackgroundColor(getResources().getColor(R.color.purple));
		}else if(view.getId() == orange.getId()){
			orange.setBackground(getResources().getDrawable(R.drawable.border_image_orange));
			selectedColor = ORANGE;
			red.setBackgroundColor(getResources().getColor(R.color.red));
			green.setBackgroundColor(getResources().getColor(R.color.green));
			blue.setBackgroundColor(getResources().getColor(R.color.blue));
			purple.setBackgroundColor(getResources().getColor(R.color.purple));
		}else if(view.getId() == green.getId()){
			green.setBackground(getResources().getDrawable(R.drawable.border_image_green));
			selectedColor = GREEN;
			orange.setBackgroundColor(getResources().getColor(R.color.orange));
			red.setBackgroundColor(getResources().getColor(R.color.red));
			blue.setBackgroundColor(getResources().getColor(R.color.blue));
			purple.setBackgroundColor(getResources().getColor(R.color.purple));
		}else if(view.getId() == blue.getId()){
			blue.setBackground(getResources().getDrawable(R.drawable.border_image_blue));
			selectedColor = BLUE;
			orange.setBackgroundColor(getResources().getColor(R.color.orange));
			green.setBackgroundColor(getResources().getColor(R.color.green));
			red.setBackgroundColor(getResources().getColor(R.color.red));
			purple.setBackgroundColor(getResources().getColor(R.color.purple));
		}else if(view.getId() == purple.getId()){
			purple.setBackground(getResources().getDrawable(R.drawable.border_image_purple));
			selectedColor = PURPLE;
			orange.setBackgroundColor(getResources().getColor(R.color.orange));
			green.setBackgroundColor(getResources().getColor(R.color.green));
			blue.setBackgroundColor(getResources().getColor(R.color.blue));
			red.setBackgroundColor(getResources().getColor(R.color.red));
		}
		
	}
}
