/**
 * 
 */
package com.platform.admin.service.site;

import com.platform.admin.pojo.site.GameDownloadInfoPOJO;
import com.platform.admin.util.PageInfo;

/**
 * @author gxd
 *
 * 2015-2-9
 */
public interface GameDownloadService {
	public void getRecords(PageInfo<GameDownloadInfoPOJO> pageInfo);
	public Integer getRecord(GameDownloadInfoPOJO record);
	public Integer deleteGameDownload(GameDownloadInfoPOJO record);
	public Integer saveGameDownload(GameDownloadInfoPOJO record);
}
