package detail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import detail.dao.DetailDao;
import detail.dto.Comment;
import detail.dto.CommentImage;
import detail.dto.DisplayInfo;
import detail.dto.DisplayInfoImage;
import detail.dto.ProductImage;
import detail.dto.ProductPrice;

@Service
public class DetailServiceImpl implements DetailService {
	@Autowired
	DetailDao detailDao;
	
	@Override
	@Transactional
	public List<Comment> getComments(int id) {
		List<Comment> list = detailDao.selectComment(id);
		int commentId;
		
		for(int i = 0, len = list.size(); i < len; i++) {
			commentId = list.get(i).getCommentId();
			List<CommentImage> imageList = detailDao.selectCommentImage(id, commentId);
			list.get(i).setCommentImages(imageList);
		}
		
		return list;
	}

	@Override
	@Transactional
	public DisplayInfo getDisplayInfo(int id) {
		DisplayInfo data = detailDao.selectDisplayInfo(id);
		return data;
	}

	@Override
	@Transactional
	public DisplayInfoImage getDisplayInfoImage(int id) {
		DisplayInfoImage data = detailDao.selectDisplayInfoImage(id);
		return data;
	}

	@Override
	@Transactional
	public List<ProductImage> getProductImages(int id) {
		List<ProductImage> list = detailDao.selectProductImage(id);
		return list;
	}

	@Override
	@Transactional
	public List<ProductPrice> getProductPrices(int id) {
		List<ProductPrice> list = detailDao.selectProductPrice(id);
		return list;
	}

	@Override
	public double getAverage(int id) {
		return detailDao.selectAverage(id);
	}
}
