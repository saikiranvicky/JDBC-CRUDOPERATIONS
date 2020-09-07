package com.src.studentinfo;

public class StudentData {
	private String name;
	private int age;
	private int gre_score;
	
	public StudentData(String name, int age, int gre_score) {
		super();
		this.name = name;
		this.age = age;
		this.gre_score = gre_score;
	}

	public StudentData() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getGre_score() {
		return gre_score;
	}

	public void setGre_score(int gre_score) {
		this.gre_score = gre_score;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + gre_score;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentData other = (StudentData) obj;
		if (age != other.age)
			return false;
		if (gre_score != other.gre_score)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
