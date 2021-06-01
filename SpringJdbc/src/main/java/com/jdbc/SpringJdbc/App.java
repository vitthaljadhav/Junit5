package com.jdbc.SpringJdbc;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jdbc.SpringJdbc.dao.PeopleDao;
import com.jdbc.SpringJdbc.pojo.People;
 
public class App 
{
    public static void main( String[] args )
    {
    ApplicationContext ctx=  new ClassPathXmlApplicationContext("config.xml");
    PeopleDao dao = ctx.getBean("peopleid",PeopleDao.class);

    People people2 = new People(105, "qqqq", "aaa", 40);
  //  dao.savePeople(people2);
   // System.out.println("Save people...");
    
    //dao.updatePeople(people2);
    //System.out.println("Done...");
    
    //People people = dao.getPeople(105);
    //System.out.println(people);
    
    //List<People> allPeople = dao.getAllPeople();
    //System.out.println(allPeople);
    
    //String deletePeople = dao.deletePeople(105);
    //System.out.println(deletePeople);
    
    //int count = dao.findTotalPeople("John");
    //System.out.println("Count  :"+count);
    
    People findPeopleById = dao.findPeopleById(104);
    System.out.println(findPeopleById);
    
    
    
    
    
    }
}
