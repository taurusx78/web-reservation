package mainpage.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mainpage.product.dao.ProductDao;
import mainpage.product.dto.Product;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDao productDao;

	@Override
	@Transactional
	public List<Product> getAllProducts(int start) {
		List<Product> list = productDao.selectAllProduct(start, ProductService.LIMIT);
		return list;
	}

	@Override
	@Transactional
	public List<Product> getTargetProducts(int id, int start) {
		List<Product> list = productDao.selectTargetProduct(id, start, ProductService.LIMIT);
		return list;
	}

	@Override
	public int getAllCount() {
		return productDao.selectAllCount();
	}
	
	@Override
	public int getTargetCount(int id) {
		return productDao.selectTargetCount(id);
	}
}
