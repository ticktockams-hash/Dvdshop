package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.EmployDao;
import model.Employ;

import util.DbConnection;

public class EmployDaoImpl implements EmployDao{
	private static Connection connection = DbConnection.getDb();
	

	public static void main(String[] args) {

	}

	@Override
	public void add(Employ employ) {
		String sql = "insert into employ(name,username,password,phone,role) values(?,?,?,?,?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, employ.getName());
			preparedStatement.setString(2, employ.getUsername());
			preparedStatement.setString(3, employ.getPassword());
			preparedStatement.setString(4, employ.getPhone());
			preparedStatement.setString(5, employ.getRole());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public List<Employ> selectAllEmploys() {
		String sql = "select * from employ";
		return selectEmploysByQuery(sql);
	}

	@Override
	public List<Employ> selectByUsername(String username) {
		String sql = "select * from employ where username=?";
		return selectEmploysByQuery(sql, username);
	}

	@Override
	public List<Employ> selectByUsernameAndPassword(String username, String password) {
		String sql = "select * from employ where username=? and password=?";
		return selectEmploysByQuery(sql, username, password);
	}

	@Override
	public void update(Employ employ) {
		String sql = "update employ set name=?,username=?,password=?,phone=?,role=? where id=?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, employ.getName());
			preparedStatement.setString(2, employ.getUsername());
			preparedStatement.setString(3, employ.getPassword());
			preparedStatement.setString(4, employ.getPhone());
			preparedStatement.setString(5, employ.getRole());
			preparedStatement.setInt(6, employ.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "delete from employ where id=?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Employ> selectById(int id) {
		String sql = "select * from employ where id=?";
		return selectEmploysByQuery(sql, id);
	}


	private List<Employ> selectEmploysByQuery(String sql, Object... params) {
		List<Employ> employs = new ArrayList<>();
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			// 設定參數
			for (int i = 0; i < params.length; i++) {
				if (params[i] instanceof String) {
					preparedStatement.setString(i + 1, (String) params[i]);
				} else if (params[i] instanceof Integer) {
					preparedStatement.setInt(i + 1, (Integer) params[i]);
				}
			}
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					Employ employ = new Employ();
					employ.setId(resultSet.getInt("id"));
					employ.setName(resultSet.getString("name"));
					employ.setUsername(resultSet.getString("username"));
					employ.setPassword(resultSet.getString("password"));
					employ.setPhone(resultSet.getString("phone"));
					employ.setRole(resultSet.getString("role"));
					employs.add(employ);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employs;
	}
}	