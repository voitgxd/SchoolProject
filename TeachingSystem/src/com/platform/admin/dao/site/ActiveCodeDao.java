package com.platform.admin.dao.site;

import org.springframework.stereotype.Repository;

import com.platform.admin.pojo.site.ActiveCodePOJO;
import com.platform.admin.util.PageInfo;

/**
 * 激活码业务持久层
 * 
 * @author gxd
 * 
 */
// 查询激活码信息
@Repository("activeCodeDao")
public interface ActiveCodeDao {
	public void getAllActiveCode(PageInfo<ActiveCodePOJO> pageInfo);

	// 删除激活码信息
	public int deleteActiveCode(ActiveCodePOJO activeCode);

	// 通过excel导入激活码信息
	public int insertActiveCode(ActiveCodePOJO activeCode);
}