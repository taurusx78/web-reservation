package mainpage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mainpage.category.dto.Category;
import mainpage.category.service.CategoryService;
import mainpage.product.dto.Product;
import mainpage.product.service.ProductService;
import mainpage.promotion.dto.Promotion;
import mainpage.promotion.service.PromotionService;

@RestController
@RequestMapping("/api")
public class RestMainController {
	@Autowired
	CategoryService categoryService;
	@Autowired
	PromotionService promotionService;
	@Autowired
	ProductService productService;

	@GetMapping("/categories")
	public Map<String, Object> categories() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Category> list = categoryService.getCategories();
		map.put("items", list);
		return map;
	}
	
	@GetMapping("/promotions")
	public Map<String, Object> promotions() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Promotion> list = promotionService.getPromotions();
		map.put("items", list);
		return map;
	}
	
	@GetMapping("/products")
	public Map<String, Object> products(@RequestParam(value = "categoryId", required = false) String categoryId,
			@RequestParam(value = "start", defaultValue = "0") int start) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Product> list;
		int count;
		
		if(categoryId != null) {
			list = productService.getTargetProducts(Integer.parseInt(categoryId), start);
			count = productService.getTargetCount(Integer.parseInt(categoryId));
		} else {
			list = productService.getAllProducts(start);
			count = productService.getAllCount();
		}
		
		map.put("items", list);
		map.put("totalCount", count);
		return map;
	}
}
