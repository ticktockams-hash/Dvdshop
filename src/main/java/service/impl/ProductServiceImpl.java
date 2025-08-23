package service.impl;

import java.util.List;
import dao.impl.ProductDaoImpl;
import model.Product;
import service.ProductService;

public class ProductServiceImpl implements ProductService {
	private static ProductDaoImpl productDaoImpl = new ProductDaoImpl();
	
	@Override
	public void addProduct(Product product) {
		productDaoImpl.add(product);
	}
	
	@Override
	public List<Product> selectAllProducts() {
		return productDaoImpl.selectAllProducts();
	}
	
	@Override
	public Product selectByName(String name) {
		return productDaoImpl.selectByName(name);
	}

	@Override
	public void updateProduct(Product product) {
		productDaoImpl.update(product);
	}

	@Override
	public void deleteProduct(int id) {
		productDaoImpl.delete(id);
	}
	

}