package mainpage.promotion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mainpage.promotion.dao.PromotionDao;
import mainpage.promotion.dto.Promotion;

@Service
public class PromotionServiceImpl implements PromotionService {
	@Autowired
	PromotionDao promotionDao;
	
	@Override
	@Transactional
	public List<Promotion> getPromotions() {
		List<Promotion> list = promotionDao.selectAll();
		return list;
	}
}
