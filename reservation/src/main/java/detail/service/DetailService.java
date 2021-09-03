package detail.service;

import java.util.List;

import detail.dto.Comment;
import detail.dto.DisplayInfo;
import detail.dto.DisplayInfoImage;
import detail.dto.ProductImage;
import detail.dto.ProductPrice;

public interface DetailService {
	public List<Comment> getComments(int id);
	public DisplayInfo getDisplayInfo(int id);
	public DisplayInfoImage getDisplayInfoImage(int id);
	public List<ProductImage> getProductImages(int id);
	public List<ProductPrice> getProductPrices(int id);
	public double getAverage(int id);
}
