package main;


public abstract class  Person {

	String id;
	String name;
	
	public Person(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getid() {
		return id;
	}

	public void setid(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
