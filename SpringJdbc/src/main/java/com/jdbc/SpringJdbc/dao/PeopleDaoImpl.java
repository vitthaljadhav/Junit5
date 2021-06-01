package com.jdbc.SpringJdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.jdbc.SpringJdbc.mapper.PeopleRowMapper;
import com.jdbc.SpringJdbc.pojo.People;

public class PeopleDaoImpl extends JdbcDaoSupport implements PeopleDao {

	/*
	 * private JdbcTemplate jdbcTemplate;
	 * 
	 * public void setJdbcTemplate(JdbcTemplate jdbcTemplate) { this.jdbcTemplate =
	 * jdbcTemplate; }
	 */
	

	@Override
	public String savePeople(People p) {

		String query = "insert into people values(?,?,?,?)";
		int k = getJdbcTemplate().update(query,
				new Object[] { p.getId(), p.getFirstName(), p.getLastName(), p.getAge() });
		if (k == 1)
			return "People creation is SUCCESS";
		else
			return "People creation is FAILURE";
	}

	@Override
	public String updatePeople(People p) {
		String query = "UPDATE people SET first_name = ?, last_Name = ?, age = ? WHERE id = ?";

		int returnValue = getJdbcTemplate().update(query,
				new Object[] { p.getFirstName(), p.getLastName(), p.getAge(), p.getId() });
		if (1 == returnValue)
			return "people updation is SUCCESS";
		else
			return "people updation is FAILURE";
	}

	@Override
	public String deletePeople(int peopleId) {

		String query="Delete from people where id=?";
		int k = getJdbcTemplate().update(query, new Object[] {peopleId});
		if(k==1)
			 return "People deletion is SUCCESS";
        else
            return "People deletion is FAILURE";
	}

/*	@Override
	public People getPeople(int peopleId) {

		String query = "Select * from people where id=?";
		People queryForObject = getJdbcTemplate().queryForObject(query, new Object[] { peopleId }, new RowMapper<People>() {

			@Override
			public People mapRow(ResultSet rs, int rowNum) throws SQLException {
				People people = new People();
				people.setId(rs.getInt(1));
				people.setFirstName(rs.getString(2));
				people.setLastName(rs.getString(3));
				people.setAge(rs.getInt(4));
				return people;
			}

		});
		return queryForObject;
	}*/
	
	
	@Override
	public People getPeople(int peopleId) {
		String query = "Select * from people where id=?";
		People queryForObject = getJdbcTemplate().queryForObject(query, new Object[] { peopleId }, new PeopleRowMapper());
		return queryForObject;
	}

	@Override
	public List<People> getAllPeople() {
		String query="SELECT * FROM PEOPLE";
		List<People> listOfPeople = getJdbcTemplate().query(query, new PeopleRowMapper());
		return listOfPeople;
	}

	@Override
	public int findTotalPeople(String firstNAme) {
		String query="SELECT COUNT(*) FROM PEOPLE where first_name=?;";
		Integer count = getJdbcTemplate().queryForObject(query, new Object[] {firstNAme},Integer.class);
		return count;
	}

	//Using BeanPropertyRoWMapper Class
	@Override
	public People findPeopleById(int peopleId) {
		String query="Select * from people where id=?";
		People people = getJdbcTemplate().queryForObject(query, new Object[] {peopleId}, new BeanPropertyRowMapper<People>(People.class));
		return people;
	}
}

