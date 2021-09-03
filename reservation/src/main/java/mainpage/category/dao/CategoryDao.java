package mainpage.category.dao;

import static mainpage.category.dao.CategoryDaoSqls.SELECT_CATEGORY;

import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import mainpage.category.dto.Category;

@Repository
public class CategoryDao {
	private NamedParameterJdbcTemplate jdbcTemplate;
	private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);
	
	public CategoryDao(DataSource ds) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(ds);
	}
	
	public List<Category> selectAll() {
		return jdbcTemplate.query(SELECT_CATEGORY, rowMapper);
	}
}
