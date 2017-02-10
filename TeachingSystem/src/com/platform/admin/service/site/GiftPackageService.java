package com.platform.admin.service.site;

import com.platform.admin.pojo.site.GiftPackagePOJO;
import com.platform.admin.util.PageInfo;

public interface GiftPackageService {

	public PageInfo<GiftPackagePOJO> queryAllGiftPackage();

	public int deleteGiftPackage(GiftPackagePOJO giftPackage);

	public PageInfo<GiftPackagePOJO> queryUpdateGiftPackage(
			GiftPackagePOJO giftPackage);

	public int updateGiftPackage(GiftPackagePOJO gift);

	public int addGiftPackage(GiftPackagePOJO gift);
}
