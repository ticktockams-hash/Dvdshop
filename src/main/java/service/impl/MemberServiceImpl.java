package service.impl;

import java.util.List;


import dao.impl.MemberDaoImpl;
import model.Member;
import service.MemberService;

public class MemberServiceImpl implements MemberService{
	private static MemberDaoImpl memberDaoImpl = new MemberDaoImpl();

	@Override
	public void addMember(Member member) {
		memberDaoImpl.add(member); 
	}

	@Override
	public Member login(String username, String password) {
		return getSingleMember(memberDaoImpl.selectByUsernameAndPassword(username, password));
	}

	@Override
	public Member findMember(String username) {
		return getSingleMember(memberDaoImpl.selectByUsername(username));
	}

	@Override
	public boolean isUsernameTaken(String username) {
		return !memberDaoImpl.selectByUsername(username).isEmpty();
	}

	@Override
	public List<Member> selectAllMembers() {
		return memberDaoImpl.selectAllMembers();
	}

	@Override
	public void updateMember(Member member) {
		memberDaoImpl.update(member);
	}

	@Override
	public void deleteMember(int id) {
		memberDaoImpl.deleteMemberOrders(id);
		memberDaoImpl.delete(id);
	}

	@Override
	public Member selectById(int id) {
		return getSingleMember(memberDaoImpl.selectById(id));
	}
	

	private Member getSingleMember(List<Member> members) {
		return members.isEmpty() ? null : members.get(0);
	}
}