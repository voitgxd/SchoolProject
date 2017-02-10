/**
 * 
 */
package com.platform.admin.dao.site;

import com.platform.admin.pojo.site.GameDownloadInfoPOJO;
import com.platform.admin.util.PageInfo;

/**
 * @author gxd
 *
 * 2015-2-9
 */
public interface GameDownloadDAO {
	public void getRecordsByPage(PageInfo<GameDownloadInfoPOJO> records);
	public Integer getRecordById(GameDownloadInfoPOJO record);
	public Integer deleteGameDownload(GameDownloadInfoPOJO record);
	public Integer saveGameDownload(GameDownloadInfoPOJO record);
}
