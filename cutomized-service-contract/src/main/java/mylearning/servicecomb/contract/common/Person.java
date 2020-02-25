package mylearning.servicecomb.contract.common;

/**
 * @author zhangchi02
 * @date 2019年3月5日
 */
public class Person {

	private String name;
	private String gender;

	public Person() {
	}

	public Person(String name, String gender) {
		super();
		this.name = name;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
