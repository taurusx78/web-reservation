package mainpage.product.dao;

public class ProductDaoSqls {
	public static final String SELECT_ALL_PRODUCT = "SELECT display_info.id AS display_info_id, display_info.place_name, product.content AS product_content, "
			+ "product.description AS product_description, product.id AS product_id, file_info.file_name AS product_image_url "
			+ "FROM display_info INNER JOIN product ON display_info.product_id = product.id "
			+ "INNER JOIN product_image ON product.id = product_image.product_id "
			+ "INNER JOIN file_info ON product_image.file_id = file_info.id "
			+ "WHERE product_image.type='th' "
			+ "ORDER BY display_info.id "
			+ "LIMIT :start, :limit";	
	
	public static final String SELECT_TARGET_PRODUCT = "SELECT display_info.id AS display_info_id, display_info.place_name, product.content AS product_content, "
			+ "product.description AS product_description, product.id AS product_id, file_info.file_name AS product_image_url "
			+ "FROM display_info INNER JOIN product ON display_info.product_id = product.id "
			+ "INNER JOIN product_image ON product.id = product_image.product_id "
			+ "INNER JOIN file_info ON product_image.file_id = file_info.id "
			+ "WHERE product_image.type='th' AND product.category_id = :id "
			+ "ORDER BY display_info.id "
			+ "LIMIT :start, :limit";
	
	public static final String SELECT_ALL_COUNT = "SELECT count(*) FROM display_info";
	
	public static final String SELECT_TARGET_COUNT = "SELECT count(*) FROM display_info INNER JOIN product ON display_info.product_id = product.id AND category_id = :id";
}
