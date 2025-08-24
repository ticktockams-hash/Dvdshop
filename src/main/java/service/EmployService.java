package service;

import java.util.List;

import model.Employ;



public interface EmployService {
	//create
	void addEmploy(Employ employ); 
	//read
	Employ login(String username, String password);
	Employ findEmploy(String username);
	Employ findEmploy(int id);
	boolean isUsernameTaken(String username);
	List<Employ> selectAllEmploys();
	//update
	void updateEmploy(Employ employ);
	//delete
	void deleteEmploy(int id);
}

