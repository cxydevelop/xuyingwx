package xuyingwx;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;

public class User {
	
	private String id;
	private String name;
	private String age;
	private String from;
	private int intFiled;
	private double doubleFiled;
	private Date dateFiled;
	
	
	public int getIntFiled() {
		return intFiled;
	}

	public void setIntFiled(int intFiled) {
		this.intFiled = intFiled;
	}

	public double getDoubleFiled() {
		return doubleFiled;
	}

	public void setDoubleFiled(double doubleFiled) {
		this.doubleFiled = doubleFiled;
	}

	public Date getDateFiled() {
		return dateFiled;
	}

	public void setDateFiled(Date dateFiled) {
		this.dateFiled = dateFiled;
	}

	public String getName() {
		return name;
	}
	
	public String getJson(User user) {
		return JSONObject.toJSONString(user);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", from=" + from + ", intFiled=" + intFiled
				+ ", doubleFiled=" + doubleFiled + ", dateFiled=" + dateFiled + "]";
	}

	public User(String id, String name, String age, String from, int intFiled, double doubleFiled, Date dateFiled) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.from = from;
		this.intFiled = intFiled;
		this.doubleFiled = doubleFiled;
		this.dateFiled = dateFiled;
	}

	
}
