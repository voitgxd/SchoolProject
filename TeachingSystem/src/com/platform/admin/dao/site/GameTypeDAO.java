/**
 * 
 */
package com.platform.admin.dao.site;

import com.platform.admin.pojo.site.GameTypeInfoPOJO;
import com.platform.admin.util.PageInfo;

/**
 * 游戏类型模块
 * @author gxd
 *
 * 2015-1-30
 */
public interface GameTypeDAO {

	public void getAllTypes(PageInfo<GameTypeInfoPOJO> pageInfo);
	/**
	 * 获取要修改的gameOrder信息
	 */
	public Integer getGameTypeById(PageInfo<GameTypeInfoPOJO> pageInfo, Integer gameTypeId);
	/**
	 * 修改游戏排行数据
	 */
	public Integer updateGameType(GameTypeInfoPOJO gameType);
	/**
	 * 保存游戏排行数据
	 */
	public Integer saveGameType(GameTypeInfoPOJO gameType);
	/**
	 * 删除游戏排行数据
	 */
	public Integer deleteGameType(Integer gameTypeId);
}
