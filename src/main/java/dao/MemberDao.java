package dao;

import java.util.List;

import model.Member;

public interface MemberDao {
	//create
	void add(Member member);
	//read
	List<Member> selectAllMembers();
	List<Member> selectByUsername(String username);
	List<Member> selectByUsernameAndPassword(String username, String password);
	List<Member> selectById(int id);
	//update
	void update(Member member);
	//delete
	void delete(int id);
	void deleteMemberOrders(int memberId);
}
