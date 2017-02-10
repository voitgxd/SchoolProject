/**
 * 
 */
package com.platform.admin.service.site;

import com.platform.admin.pojo.site.GameTypeInfoPOJO;
import com.platform.admin.util.PageInfo;

/**
 * 游戏类型模块
 * @author gxd
 *
 * 2015-1-30
 */
public interface GameTypeService {
	public void getAllTypes(PageInfo<GameTypeInfoPOJO> pageInfo);
	public Integer getGameTypeById(PageInfo<GameTypeInfoPOJO> pageInfo, Integer gameTypeId);
	public Integer updateGameType(GameTypeInfoPOJO gameType);
	public Integer saveGameType(GameTypeInfoPOJO gameType);
	public Integer deleteGameType(Integer gameTypeId);
}
