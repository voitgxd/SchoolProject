/**
 * 
 */
package com.platform.admin.service.site.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.platform.admin.dao.site.impl.GameDownloadDAOImpl;
import com.platform.admin.pojo.site.GameDownloadInfoPOJO;
import com.platform.admin.service.site.GameDownloadService;
import com.platform.admin.util.PageInfo;

/**
 * @author gxd
 *
 * 2015-2-9
 */
@Service("gameDownloadService")
public class GameDownloadServiceImpl implements GameDownloadService {
	@Autowired
	@Qualifier("gameDownloadDAO")
	private GameDownloadDAOImpl gameDownloadDAO;

	/**
	 * 获取该页的网页下载记录数据
	 */
	public void getRecords(PageInfo<GameDownloadInfoPOJO> pageInfo) {
		gameDownloadDAO.getRecordsByPage(pageInfo);
	}
	
	public Integer getRecord(GameDownloadInfoPOJO record) {
		return gameDownloadDAO.getRecordById(record);
	}

	public Integer deleteGameDownload(GameDownloadInfoPOJO record) {
		return gameDownloadDAO.deleteGameDownload(record);
	}
	
	public Integer saveGameDownload(GameDownloadInfoPOJO record) {
		return gameDownloadDAO.saveGameDownload(record);
	}

}
