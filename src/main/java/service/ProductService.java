package service;

import java.util.List;

import model.Product;

public interface ProductService {
	//create
	void addProduct(Product product);
	//read
	List<Product> selectAllProducts();
	Product selectByName(String name);
	//update
	void updateProduct(Product product);
	//delete
	void deleteProduct(int id);
}
