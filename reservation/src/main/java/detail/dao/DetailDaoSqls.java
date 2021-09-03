package detail.dao;

public class DetailDaoSqls {
	public static final String SELECT_AVERAGE = "SELECT CASE WHEN avg(score) IS NULL THEN 0 ELSE avg(score) END 'average_score' "
			+ "FROM reservation_user_comment INNER JOIN display_info ON reservation_user_comment.product_id = display_info.product_id AND display_info.id = :id";
	
	public static final String SELECT_DISPLAY_INFO = "SELECT category.id AS category_id, category.name AS category_name, display_info.create_date, display_info.id AS display_info_id, display_info.email, "
			+ "display_info.homepage, display_info.modify_date, display_info.opening_hours, display_info.place_lot, display_info.place_name, display_info.place_street, "
			+ "product.content AS product_content, product.description AS product_description, product.event AS product_event, product.id AS product_id, display_info.tel AS telephone "
			+ "FROM display_info INNER JOIN product ON display_info.product_id = product.id "
			+ "INNER JOIN category ON product.category_id = category.id "
			+ "WHERE display_info.id = :id";
	
	public static final String SELECT_DISPLAY_INFO_IMAGE = "SELECT file_info.content_type, file_info.create_date, file_info.delete_flag, display_info_image.display_info_id, "
			+ "display_info_image.id AS display_info_image_id, display_info_image.file_id, file_info.file_name, file_info.modify_date, file_info.save_file_name "
			+ "FROM display_info INNER JOIN display_info_image ON display_info.id = display_info_image.display_info_id "
			+ "INNER JOIN file_info ON display_info_image.file_id = file_info.id "
			+ "WHERE display_info.id = :id";
	
	public static final String SELECT_PRODUCT_IMAGE = "SELECT file_info.content_type, file_info.create_date, file_info.delete_flag, file_info.id AS file_info_id, file_info.file_name, "
			+ "file_info.modify_date, product_image.product_id, product_image.id AS product_image_id, file_info.save_file_name, product_image.type "
			+ "FROM display_info INNER JOIN product_image ON display_info.product_id = product_image.product_id "
			+ "INNER JOIN file_info ON product_image.file_id = file_info.id AND (product_image.type = 'ma' OR product_image.type = 'et') "
			+ "WHERE display_info.id = :id "
			+ "LIMIT 0, 2";
	
	public static final String SELECT_PRODUCT_PRICE = "SELECT product_price.create_date, product_price.discount_rate, product_price.modify_date, product_price.price, "
			+ "product_price.price_type_name, product_price.product_id, product_price.id AS product_price_id "
			+ "FROM display_info INNER JOIN product_price ON display_info.product_id = product_price.product_id "
			+ "WHERE display_info.id = :id";
	
	public static final String SELECT_COMMENT = "SELECT reservation_user_comment.comment, reservation_user_comment.id AS comment_id, reservation_user_comment.create_date, "
			+ "reservation_user_comment.modify_date, reservation_user_comment.product_id, reservation_info.reservation_date, reservation_info.reservation_email, "
			+ "reservation_info.id AS reservation_info_id, reservation_info.reservation_name, reservation_info.reservation_tel AS reservation_telephone, reservation_user_comment.score "
			+ "FROM display_info INNER JOIN reservation_user_comment ON display_info.product_id = reservation_user_comment.product_id "
			+ "INNER JOIN reservation_info ON reservation_user_comment.reservation_info_id = reservation_info.id "
			+ "WHERE display_info.id = :id "
			+ "ORDER BY reservation_user_comment.id DESC";
	
	public static final String SELECT_COMMENT_IMAGE = "SELECT file_info.content_type, file_info.create_date, file_info.delete_flag, file_info.id AS file_id, file_info.file_name, reservation_user_comment_image.id AS image_id, "
			+ "file_info.modify_date, reservation_user_comment_image.reservation_info_id, reservation_user_comment_image.reservation_user_comment_id, file_info.save_file_name "
			+ "FROM display_info INNER JOIN reservation_user_comment ON display_info.product_id = reservation_user_comment.product_id "
			+ "INNER JOIN reservation_user_comment_image ON reservation_user_comment.id = reservation_user_comment_image.reservation_user_comment_id "
			+ "INNER JOIN file_info ON reservation_user_comment_image.file_id = file_info.id "
			+ "WHERE display_info.id = :id AND reservation_user_comment.id = :comment_id";
}
