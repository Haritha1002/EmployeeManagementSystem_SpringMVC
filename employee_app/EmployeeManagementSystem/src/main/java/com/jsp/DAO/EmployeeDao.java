package com.jsp.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.transaction.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.DTO.Employee;

@Repository
public class EmployeeDao {
	
	@Autowired
	EntityManager manager;
	
	@Autowired
	EntityTransaction transaction;
	
	//to insert employee object to DB
	
	public void saveEmployee(Employee employee)
	{
		transaction.begin();
		manager.persist(employee);
		transaction.commit();
	}
	 // to get employee based on id
	public Employee getEmployeeById(int id)
	{
		Employee e = manager.find(Employee.class, id);
		return e;
		
	}
	
	//to delete employee based on id
	public void deleteEmployee(int id)
	{
		Employee e = manager.find(Employee.class, id);
		if(e!=null)
		{
	       transaction.begin();
	       manager.remove(e);
	       transaction.commit();
		}
	}
	//update employee details
	public void updateEmployee(Employee updateEmployee)
	{
		transaction.begin();
		manager.merge(updateEmployee);
		transaction.commit();
		
	}
	//to get all employee details
	
	public List<Employee> getAllEmployee()
	{
		Query q = manager.createQuery("select e from Employee e");
		List<Employee> employees = q.getResultList();
		return employees;
	}
	
	
	
	
	

}
