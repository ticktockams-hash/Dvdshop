package dao;

import java.util.List;

import model.Employ;



public interface EmployDao {
	//create
	void add(Employ employ);
	//read
	List<Employ> selectAllEmploys();
	List<Employ> selectByUsername(String username);
	List<Employ> selectByUsernameAndPassword(String username, String password);
	List<Employ> selectById(int id);
	//update
	void update(Employ employ);
	//delete
	void delete(int id);
}
