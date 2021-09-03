package detail.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import detail.dto.Comment;
import detail.dto.DisplayInfo;
import detail.dto.DisplayInfoImage;
import detail.dto.ProductImage;
import detail.dto.ProductPrice;
import detail.service.DetailService;

@RestController
@RequestMapping("/api")
public class RestDetailController {
	@Autowired
	DetailService detailService;
	
	@GetMapping("/products/{id}")
	public Map<String, Object> detail(@PathVariable("id") int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Comment> comments = detailService.getComments(id);
		DisplayInfo displayInfo = detailService.getDisplayInfo(id);
		DisplayInfoImage displayInfoImage = detailService.getDisplayInfoImage(id);
		List<ProductImage> productImages = detailService.getProductImages(id);
		List<ProductPrice> productPrices = detailService.getProductPrices(id);
		double average = detailService.getAverage(id);
		
		map.put("comments", comments);
		map.put("displayInfo", displayInfo);
		map.put("displayInfoImage", displayInfoImage);
		map.put("productImages", productImages);
		map.put("productPrices", productPrices);
		map.put("averageScore", average);
		return map;
	}
}
