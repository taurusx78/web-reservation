package mainpage.promotion.dao;

import static mainpage.promotion.dao.PromotionDaoSqls.SELECT_PROMOTION;

import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import mainpage.promotion.dto.Promotion;

@Repository
public class PromotionDao {
	private NamedParameterJdbcTemplate jdbcTemplate;
	private RowMapper<Promotion> rowMapper = BeanPropertyRowMapper.newInstance(Promotion.class);
	
	public PromotionDao(DataSource ds) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(ds);
	}
	
	public List<Promotion> selectAll() {
		return jdbcTemplate.query(SELECT_PROMOTION, rowMapper);
	}
}
