package main;

public abstract class  Person {
	String ID;
	String name;
	
	public Person(String id, String name) {
		this.ID = id;
		this.name = name;
	}
	
	public String getID() {
		return this.ID;
	}

	public void setID(String id) {
		this.ID = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
