package androidchallenge.vyas.com.myapplication.myClasses;

public class ListPojo
{
	private long id;
	private String taskStr;
	private int themeColor;
	
	public ListPojo(){}
	public ListPojo(long id, String taskStr, int themeColor) {
		super();
		this.id = id;
		this.taskStr = taskStr;
		this.themeColor = themeColor;
	}
	public long getId() {
		return id;
	}
	public String getTaskStr() {
		return taskStr;
	}
	public int getThemeColor() {
		return themeColor;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setTaskStr(String taskStr) {
		this.taskStr = taskStr;
	}
	public void setThemeColor(int themeColor) {
		this.themeColor = themeColor;
	}
	@Override
	public String toString() {
		return "ListPojo [id=" + id + ", taskStr=" + taskStr + ", themeColor="
		+ themeColor + "]";
	}
	  
} 