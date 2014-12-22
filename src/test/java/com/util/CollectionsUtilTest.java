package com.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Person
{
	private String name;
	private String surname;
	private int age;
	private String gender;

	public Person(String name, String surname, int age,String gender)
	{
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.gender = gender;
	}

	public int getAge()
	{
		return age;
	}

	public String getName()
	{
		return name;
	}

	public String getSurname()
	{
		return surname;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setSurname(String surname)
	{
		this.surname = surname;
	}
	public String getGender()
	{
		return gender;
	}
	public void setGender(String gender)
	{
		this.gender = gender;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Name : ").append(name)
			.append(", Surname : ").append(surname)
			.append(", Age : ").append(age)
			.append(", Gender : ").append(gender);
		return builder.toString();
	}
	
}


public class CollectionsUtilTest
{
	
	public static void main(String[] args) throws Exception
	{
		Object returnValue = null;
		
		Person krishna = new Person("John", "Doe", 30,"Male");
		List<Person> personList = new ArrayList<Person>();
		personList.add(krishna);

		Person person = CollectionsUtil.getByVariableValue(personList, Person.class, "age",30);
		System.out.println(person);
		
		Map<String,Person> nameVsPersonMap = new HashMap<String,Person>();
		nameVsPersonMap.put("some key", person);
		
		returnValue = CollectionsUtil.getByValueAsVariableValue(nameVsPersonMap, Person.class, "gender", "Male");
		System.out.println(returnValue);
		
		Map<Person,String> personVsNameMap = new HashMap<Person,String>();
		personVsNameMap.put(person,"some value");
		
		returnValue = CollectionsUtil.getByKeyAsVariableValue(personVsNameMap, Person.class, "surname", "Patni");
		System.out.println(returnValue);
		
		
	}

}
