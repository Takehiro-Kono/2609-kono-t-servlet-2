package com.javaexercise.list;

public class Employee {
	
	private String mail;
	private String name;
	private int age;
	private String profile;
	private String birthplace;
	private double height;
	private double weight;
	
	public Employee(
			String mail,
			String name,
			int age,
			String profile,
			String birthplace,
			double height,
			double weight) {
		
		this.mail = mail;
		this.name = name;
		this.age = age;
		this.profile = profile;
		this.birthplace = birthplace;
		this.height = height;
		this.weight = weight;
    }
	public String getMail() {
	    return mail;
	}
	public String getName() {
	    return name;
	}

	public int getAge() {
	    return age;
	}

	public String getBirthplace() {
        return birthplace;
    }

    public String getProfile() {
        return profile;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }
}
