/**
 * 
 */
package com.platform.admin.service.site;

import com.platform.admin.pojo.site.GameInfoPOJO;
import com.platform.admin.pojo.site.GameOrderInfoPOJO;
import com.platform.admin.util.PageInfo;

/**
 * 游戏排行模块
 * @author gxd
 *
 * 2015-1-30
 */
public interface GameOrderService {
	public PageInfo<GameOrderInfoPOJO> getAll();
	public GameOrderInfoPOJO getOrder(Integer gameId);
	public Integer updateGameOrder(GameOrderInfoPOJO order);
	public Integer saveGameOrder(GameOrderInfoPOJO order);
	public Integer deleteGameOrder(Integer gameId);
	public Integer upGameOrder(Integer gameId);
	public Integer downGameOrder(Integer gameId);
	
	public void getAllGameNames(PageInfo<GameInfoPOJO> pageInfo);
}
