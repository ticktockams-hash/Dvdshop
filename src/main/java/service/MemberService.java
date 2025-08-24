package service;

import java.util.List;

import model.Member;

public interface MemberService {
	//create
	void addMember(Member member); 
	//read
	Member login(String username, String password);
	Member findMember(String username);
	boolean isUsernameTaken(String username);
	List<Member> selectAllMembers();
	Member selectById(int id);
	//update
	void updateMember(Member member);
	//delete
	void deleteMember(int id);
}