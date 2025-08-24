package service;

import java.util.List;


import model.Porder;


public interface PorderService {

	//create
	void addPorder(Porder porder);
	
	//read
	List<Porder> selectAllPorders();
	List<Porder> selectPordersByMemberId(int memberId);
	
	//update
	void updatePorder(Porder porder);
	
	//delete
	void deletePorder(int id);
	
	

}