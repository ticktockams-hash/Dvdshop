package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.MemberDao;
import model.Member;
import util.DbConnection;

public class MemberDaoImpl implements MemberDao{
	private static Connection connection = DbConnection.getDb();
	
	public static void main(String[] args) {

	}
	
	@Override
	public void add(Member member) {
		String sql = "insert into member(name,username,password,phone,role) values(?,?,?,?,?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, member.getName());
			preparedStatement.setString(2, member.getUsername());
			preparedStatement.setString(3, member.getPassword());
			preparedStatement.setString(4, member.getPhone());
			preparedStatement.setString(5, member.getRole());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	@Override
	public List<Member> selectAllMembers() {
		String sql = "select * from member";
		List<Member> members = new ArrayList<Member>();
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					Member Member = new Member();
					Member.setId(resultSet.getInt("id"));
					Member.setName(resultSet.getString("name"));
					Member.setUsername(resultSet.getString("username"));
					Member.setPassword(resultSet.getString("password"));
					Member.setPhone(resultSet.getString("phone"));
					Member.setRole(resultSet.getString("role"));
					members.add(Member);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return members;
	}
	@Override
	public List<Member> selectByUsername(String username) {
		String sql = "select * from member where username=?";
		List<Member> members = new ArrayList<Member>();
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, username);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					Member Member = new Member();
					Member.setId(resultSet.getInt("id"));
					Member.setName(resultSet.getString("name"));
					Member.setUsername(resultSet.getString("username"));
					Member.setPassword(resultSet.getString("password"));
					Member.setPhone(resultSet.getString("phone"));
					Member.setRole(resultSet.getString("role"));
					members.add(Member);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return members;
	}
	@Override
	public List<Member> selectByUsernameAndPassword(String username, String password) {
		String sql = "select * from member where username=? and password=?";
		List<Member> members = new ArrayList<Member>();
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					Member Member = new Member();
					Member.setId(resultSet.getInt("id"));
					Member.setName(resultSet.getString("name"));
					Member.setUsername(resultSet.getString("username"));
					Member.setPassword(resultSet.getString("password"));
					Member.setPhone(resultSet.getString("phone"));
					Member.setRole(resultSet.getString("role"));
					members.add(Member);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return members;
	}
	@Override
	public void update(Member member) {

		String sql = "update member set name=?, username=?, password=?, phone=?, role=? where id=?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, member.getName());
			preparedStatement.setString(2, member.getUsername());
			preparedStatement.setString(3, member.getPassword());
			preparedStatement.setString(4, member.getPhone());
			preparedStatement.setString(5, member.getRole());
			preparedStatement.setInt(6, member.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void delete(int id) {
		String sql = "delete from member where id=?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void deleteMemberOrders(int memberId) {
		String sql = "delete from porder where memberid=?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, memberId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public List<Member> selectById(int id) {
		String sql = "select * from member where id=?";
		List<Member> members = new ArrayList<Member>();
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					Member Member = new Member();
					Member.setId(resultSet.getInt("id"));
					Member.setName(resultSet.getString("name"));
					Member.setUsername(resultSet.getString("username"));
					Member.setPassword(resultSet.getString("password"));
					Member.setPhone(resultSet.getString("phone"));
					Member.setRole(resultSet.getString("role"));
					members.add(Member);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return members;
	}
}