package main;

/**
 * Date: June 12-2022
 * This is the person class
 * @author Team1
 *
 */
public abstract class  Person {
    /**
     * this is person ID
     */
    String ID;
    /**
     * this is person name
     */
    String name;

    /**
     * this is an constructor that initiate person ' ID & Name '
     * @param id assign a uniqe ID to the person
     * @param name assign a name to the person
     */
	public Person(String id, String name) {
		this.ID = id;
		this.name = name;
	}

    /**
     * method to return the ID
     * @return ID
     */
	public String getID() {
		return this.ID;
	}

    /**
     * method to set the ID
     * @param id
     */
	public void setID(String id) {
		this.ID = id;
	}

    /**
     * method to get the person name
     * @return name
     */
	public String getName() {
		return name;
	}

    /**
     * method to set a person name
     * @param name
     */
	public void setName(String name) {
		this.name = name;
	}
}
