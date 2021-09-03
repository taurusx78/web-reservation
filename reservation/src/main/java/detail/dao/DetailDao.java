package detail.dao;

import static detail.dao.DetailDaoSqls.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import detail.dto.Comment;
import detail.dto.CommentImage;
import detail.dto.DisplayInfo;
import detail.dto.DisplayInfoImage;
import detail.dto.ProductImage;
import detail.dto.ProductPrice;

@Repository
public class DetailDao {
	private NamedParameterJdbcTemplate jdbcTemplate;
	private RowMapper<Comment> commentRowMapper = BeanPropertyRowMapper.newInstance(Comment.class);
	private RowMapper<CommentImage> commentImageRowMapper = BeanPropertyRowMapper.newInstance(CommentImage.class);
	private RowMapper<DisplayInfo> displayInfoRowMapper = BeanPropertyRowMapper.newInstance(DisplayInfo.class);
	private RowMapper<DisplayInfoImage> displayInfoImageRowMapper = BeanPropertyRowMapper.newInstance(DisplayInfoImage.class);
	private RowMapper<ProductImage> productImageRowMapper = BeanPropertyRowMapper.newInstance(ProductImage.class);
	private RowMapper<ProductPrice> productPriceRowMapper = BeanPropertyRowMapper.newInstance(ProductPrice.class);
	
	public DetailDao(DataSource ds) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(ds);
	}
	
	public double selectAverage(Integer id) {
		Map<String, Integer> params = Collections.singletonMap("id", id);
		return jdbcTemplate.queryForObject(SELECT_AVERAGE, params, Double.class);
	}
	
	public List<Comment> selectComment(Integer id) {
		Map<String, Integer> params = Collections.singletonMap("id", id);
		return jdbcTemplate.query(SELECT_COMMENT, params, commentRowMapper);
	}
	
	public List<CommentImage> selectCommentImage(Integer id, Integer commentId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("id", id);
		params.put("comment_id", commentId);
		return jdbcTemplate.query(SELECT_COMMENT_IMAGE, params, commentImageRowMapper);
	}
	
	public DisplayInfo selectDisplayInfo(Integer id) {
		Map<String, Integer> params = Collections.singletonMap("id", id);
		return jdbcTemplate.queryForObject(SELECT_DISPLAY_INFO, params, displayInfoRowMapper);
	}
	
	public DisplayInfoImage selectDisplayInfoImage(Integer id) {
		Map<String, Integer> params = Collections.singletonMap("id", id);
		return jdbcTemplate.queryForObject(SELECT_DISPLAY_INFO_IMAGE, params, displayInfoImageRowMapper);
	}
	
	public List<ProductImage> selectProductImage(Integer id) {
		Map<String, Integer> params = Collections.singletonMap("id", id);
		return jdbcTemplate.query(SELECT_PRODUCT_IMAGE, params, productImageRowMapper);
	}
	
	public List<ProductPrice> selectProductPrice(Integer id) {
		Map<String, Integer> params = Collections.singletonMap("id", id);
		return jdbcTemplate.query(SELECT_PRODUCT_PRICE, params, productPriceRowMapper);
	}
}
