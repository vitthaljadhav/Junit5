package com.jdbc.SpringJdbc.dao;

import java.util.List;

import com.jdbc.SpringJdbc.pojo.People;

public interface PeopleDao {
	public String savePeople(People p);

	public String updatePeople(People p);

	public String deletePeople(int peopleId);

	public People getPeople(int peopleId);

	public List<People> getAllPeople();

	
	public int findTotalPeople(String firstName);
	
	public People findPeopleById(int peopleId);
}
