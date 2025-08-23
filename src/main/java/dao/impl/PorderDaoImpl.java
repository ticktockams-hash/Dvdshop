package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dao.PorderDao;
import model.Porder;
import util.DbConnection;


public class PorderDaoImpl implements PorderDao {

	private static Connection connection = DbConnection.getDb();
	
	public static void main(String[] args) {
		
	}
	
	@Override
	public void add(Porder porder) {
		String sql = "insert into porder(memberid, username, productname, quantity, amount) values(?, ?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, porder.getMemberid());
			preparedStatement.setString(2, porder.getUsername());
			preparedStatement.setString(3, porder.getProductname());
			preparedStatement.setInt(4, porder.getQuantity());
			preparedStatement.setInt(5, porder.getAmount());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Porder> selectAllPorders() {
		String sql = "select * from porder";
		return selectPordersByQuery(sql);
	}

	@Override
	public List<Porder> selectByMemberId(int memberId) {
		String sql = "select * from porder where memberid=?";
		return selectPordersByQuery(sql, memberId);
	}

	@Override
	public void update(Porder porder) {
		String sql = "update porder set memberid=?, username=?, productname=?, quantity=?, amount=? where id=?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, porder.getMemberid());
			preparedStatement.setString(2, porder.getUsername());
			preparedStatement.setString(3, porder.getProductname());
			preparedStatement.setInt(4, porder.getQuantity());
			preparedStatement.setInt(5, porder.getAmount());
			preparedStatement.setInt(6, porder.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "delete from porder where id=?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	private List<Porder> selectPordersByQuery(String sql, Object... params) {
		List<Porder> porders = new ArrayList<>();
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			// 設定參數
			for (int i = 0; i < params.length; i++) {
				if (params[i] instanceof Integer) {
					preparedStatement.setInt(i + 1, (Integer) params[i]);
				} else if (params[i] instanceof String) {
					preparedStatement.setString(i + 1, (String) params[i]);
				}
			}
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					Porder porder = new Porder();
					porder.setId(resultSet.getInt("id"));
					porder.setMemberid(resultSet.getInt("memberid"));
					porder.setUsername(resultSet.getString("username"));
					porder.setProductname(resultSet.getString("productname"));
					porder.setQuantity(resultSet.getInt("quantity"));
					porder.setAmount(resultSet.getInt("amount"));
					porder.setPorderdate(resultSet.getTimestamp("porderdate"));
					porders.add(porder);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return porders;
	}
}