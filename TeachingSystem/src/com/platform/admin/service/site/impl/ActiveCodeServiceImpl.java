package com.platform.admin.service.site.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.platform.admin.dao.site.impl.ActiveCodeDaoImpl;
import com.platform.admin.pojo.site.ActiveCodePOJO;
import com.platform.admin.service.site.ActiveCodeService;
import com.platform.admin.util.PageInfo;

/**
 * 激活码信息业务
 * 
 * @author gxd
 * 
 */

@Service("activeCodeService")
public class ActiveCodeServiceImpl implements ActiveCodeService {
	@Autowired
	@Qualifier("activeCodeDao")
	private ActiveCodeDaoImpl activeCodeDao;

	/**
	 * 查询所有的激活码信息
	 */
	public void getActiveCodeInfo(PageInfo<ActiveCodePOJO> pageInfo) {
		activeCodeDao.getAllActiveCode(pageInfo);

	}

	/**
	 * 删除激活码信息
	 * 
	 * @return
	 */
	public int deleteActiveCode(ActiveCodePOJO active) {

		return activeCodeDao.deleteActiveCode(active);
	}

	/**
	 * 通过excel模板导入激活码数据
	 */
	public List<Integer> insertActiveCode(List<ActiveCodePOJO> actcList) {
		List<Integer> retList = new ArrayList<Integer>();
		for (int i = 0; i < actcList.size(); i++) {
			int ret = activeCodeDao.insertActiveCode(actcList.get(i));
			retList.add(ret);
		}
		return retList;
	}

	public ActiveCodeDaoImpl getActiveCodeDao() {
		return activeCodeDao;
	}

	public void setActiveCodeDao(ActiveCodeDaoImpl activeCodeDao) {
		this.activeCodeDao = activeCodeDao;
	}

}
