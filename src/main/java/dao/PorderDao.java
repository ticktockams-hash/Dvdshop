package dao;

import java.util.List;

import model.Porder;

public interface PorderDao {

	
	//create
	void add(Porder porder);
	
	//read
	List<Porder> selectAllPorders();
	List<Porder> selectByMemberId(int memberId);
	
	//update
	void update(Porder porder);
	
	//delete
	void delete(int id);
}
