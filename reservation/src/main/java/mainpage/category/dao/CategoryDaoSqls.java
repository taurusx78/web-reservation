package mainpage.category.dao;

public class CategoryDaoSqls {
	public static final String SELECT_CATEGORY = "SELECT category.id, category.name, count(*) AS count "
			+ "FROM category INNER JOIN product ON category.id = product.category_id "
			+ "INNER JOIN display_info ON product.id=display_info.product_id "
			+ "GROUP BY category.id "
			+ "ORDER BY category.id";
}
