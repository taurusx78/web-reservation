package mainpage.product.dao;

import static mainpage.product.dao.ProductDaoSqls.SELECT_ALL_COUNT;
import static mainpage.product.dao.ProductDaoSqls.SELECT_ALL_PRODUCT;
import static mainpage.product.dao.ProductDaoSqls.SELECT_TARGET_COUNT;
import static mainpage.product.dao.ProductDaoSqls.SELECT_TARGET_PRODUCT;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import mainpage.product.dto.Product;

@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbcTemplate;
	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);
	
	public ProductDao(DataSource ds) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(ds);
	}
	
	public List<Product> selectAllProduct(Integer start, Integer limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		return jdbcTemplate.query(SELECT_ALL_PRODUCT, params, rowMapper);
	}
	
	public List<Product> selectTargetProduct(Integer id, Integer start, Integer limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("id", id);
		params.put("start", start);
		params.put("limit", limit);
		return jdbcTemplate.query(SELECT_TARGET_PRODUCT, params, rowMapper);
	}
	
	public int selectAllCount() {
		return jdbcTemplate.queryForObject(SELECT_ALL_COUNT,  Collections.emptyMap(), Integer.class);
	}
	
	public int selectTargetCount(Integer id) {
		Map<String, Integer> params = Collections.singletonMap("id", id);
		return jdbcTemplate.queryForObject(SELECT_TARGET_COUNT,  params, Integer.class);
	}
}
