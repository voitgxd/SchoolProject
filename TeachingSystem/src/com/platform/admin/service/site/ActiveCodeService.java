package com.platform.admin.service.site;

import java.util.List;

import com.platform.admin.pojo.site.ActiveCodePOJO;
import com.platform.admin.util.PageInfo;

/**
 * 激活码信息管理业务
 * 
 * @author gxd
 * 
 */
public interface ActiveCodeService {
	// 获得激活码信息
	public void getActiveCodeInfo(PageInfo<ActiveCodePOJO> pageInfo);

	// 删除激活码信息
	public int deleteActiveCode(ActiveCodePOJO active);

	// 通过excel导入激活码信息
	public List<Integer> insertActiveCode(List<ActiveCodePOJO> actcList);
}
