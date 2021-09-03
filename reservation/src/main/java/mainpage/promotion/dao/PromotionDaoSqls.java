package mainpage.promotion.dao;

public class PromotionDaoSqls {
	public static final String SELECT_PROMOTION = "SELECT promotion.id, promotion.product_id, file_name AS product_image_url "
			+ "FROM promotion INNER JOIN product_image ON promotion.product_id = product_image.product_id "
			+ "INNER JOIN file_info ON product_image.file_id = file_info.id "
			+ "WHERE product_image.type = 'th' "
			+ "ORDER BY promotion.id";
}
