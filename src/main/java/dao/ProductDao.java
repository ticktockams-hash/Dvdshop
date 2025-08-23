package dao;

import java.util.List;

import model.Product;

public interface ProductDao {

	//create
	void add(Product product);
	//read
	Product selectById(int id);
	List<Product> selectAllProducts();
	Product selectByName(String name);
	//update
	void update(Product product);
	//delete
	void delete(int id);
	
}
