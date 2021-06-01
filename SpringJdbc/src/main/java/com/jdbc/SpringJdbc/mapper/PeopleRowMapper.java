package com.jdbc.SpringJdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jdbc.SpringJdbc.pojo.People;

public class PeopleRowMapper implements RowMapper<People> {

	@Override
	public People mapRow(ResultSet rs, int rowNum) throws SQLException {
		People people = new People();
		people.setId(rs.getInt(1));
		people.setFirstName(rs.getString(2));
		people.setLastName(rs.getString(3));
		people.setAge(rs.getInt(4));
		
		System.out.println("Row Number : "+rowNum);
		return people;
	}

}
