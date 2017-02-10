package com.platform.admin.dao.site;

import com.platform.admin.pojo.site.GiftPackagePOJO;
import com.platform.admin.util.PageInfo;

public interface GiftPackageDao {

	public PageInfo<GiftPackagePOJO> queryGiftPackage();

	public int deleteGiftPackage(GiftPackagePOJO giftPojo);

	public PageInfo<GiftPackagePOJO> queryUpdateGiftPackage(
			GiftPackagePOJO giftPojo);

	public int updateGiftPackage(GiftPackagePOJO gift);

	public int addGiftPackage(GiftPackagePOJO gift);
}
