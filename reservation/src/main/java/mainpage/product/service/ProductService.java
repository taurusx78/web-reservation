package mainpage.product.service;

import java.util.List;

import mainpage.product.dto.Product;

public interface ProductService {
	public static final int LIMIT = 4;
	public List<Product> getAllProducts(int start);
	public List<Product> getTargetProducts(int id, int start);
	public int getAllCount();
	public int getTargetCount(int id);
}
