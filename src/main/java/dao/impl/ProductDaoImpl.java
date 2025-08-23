package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ProductDao;
import model.Product;
import util.DbConnection;

public class ProductDaoImpl implements ProductDao {
	private static Connection connection = DbConnection.getDb();
	
	public static void main(String[] args) {
		// 這裡可以加入測試程式碼，用來驗證所有方法
	}

	@Override
	public void add(Product product) {
		String sql = "insert into product(productname, productprice) values(?,?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, product.getProductname());
			preparedStatement.setInt(2, product.getProductprice());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public List<Product> selectAllProducts() {
		String sql = "select * from product";
		return selectProductsByQuery(sql);
	}

	@Override
	public Product selectById(int id) {
		String sql = "select * from product where id=?";
		List<Product> products = selectProductsByQuery(sql, id);
		return products.isEmpty() ? null : products.get(0);
	}
	
	@Override
	public Product selectByName(String name) {
		String sql = "select * from product where productname=?";
		List<Product> products = selectProductsByQuery(sql, name);
		return products.isEmpty() ? null : products.get(0);
	}

	@Override
	public void update(Product product) {
		String sql = "update product set productname=?, productprice=? where id=?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, product.getProductname());
			preparedStatement.setInt(2, product.getProductprice());
			preparedStatement.setInt(3, product.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "delete from product where id=?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 新增一個私有方法來處理所有查詢的重複邏輯
	 */
	private List<Product> selectProductsByQuery(String sql, Object... params) {
		List<Product> products = new ArrayList<>();
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
					Product product = new Product();
					product.setId(resultSet.getInt("id"));
					product.setProductname(resultSet.getString("productname"));
					product.setProductprice(resultSet.getInt("productprice"));
					products.add(product);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}
}