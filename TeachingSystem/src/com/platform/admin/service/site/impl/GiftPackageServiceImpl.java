package com.platform.admin.service.site.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.platform.admin.dao.site.GiftPackageDao;
import com.platform.admin.pojo.site.GiftPackagePOJO;
import com.platform.admin.service.site.GiftPackageService;
import com.platform.admin.util.PageInfo;

/**
 * 礼包信息业务模块
 * 
 * @author gxd
 * 
 */
@Service("giftPackageService")
public class GiftPackageServiceImpl implements GiftPackageService {
	@Autowired
	@Qualifier("giftPackageDao")
	private GiftPackageDao giftPackageDao;

	public GiftPackageDao getGiftPackageDao() {
		return giftPackageDao;
	}

	public void setGiftPackageDao(GiftPackageDao giftPackageDao) {
		this.giftPackageDao = giftPackageDao;
	}

	// 添加礼包信息
	public int addGiftPackage(GiftPackagePOJO gift) {
		return giftPackageDao.addGiftPackage(gift);
	}

	// 删除礼包信息
	public int deleteGiftPackage(GiftPackagePOJO giftPackage) {
		return giftPackageDao.deleteGiftPackage(giftPackage);
	}

	// 查询所有礼包信息
	public PageInfo<GiftPackagePOJO> queryAllGiftPackage() {
		return giftPackageDao.queryGiftPackage();
	}

	// 查询要修改的礼包信息
	public PageInfo<GiftPackagePOJO> queryUpdateGiftPackage(
			GiftPackagePOJO giftPackage) {
		return giftPackageDao.queryUpdateGiftPackage(giftPackage);
	}

	// 修改礼包信息
	public int updateGiftPackage(GiftPackagePOJO gift) {
		return giftPackageDao.updateGiftPackage(gift);
	}

}
