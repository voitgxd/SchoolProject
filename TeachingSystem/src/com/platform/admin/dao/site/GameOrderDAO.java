/**
 * 
 */
package com.platform.admin.dao.site;

import com.platform.admin.pojo.site.GameInfoPOJO;
import com.platform.admin.pojo.site.GameOrderInfoPOJO;
import com.platform.admin.util.PageInfo;

/**
 * 游戏排行模块
 * @author gxd
 *
 * 2015-1-30
 */
public interface GameOrderDAO {


	public PageInfo<GameOrderInfoPOJO> getAll();
	/**
	 * 获取要修改的gameOrder信息
	 */
	public GameOrderInfoPOJO getOrder(Integer gameId);
	/**
	 * 修改游戏排行数据
	 */
	public Integer updateGameOrder(GameOrderInfoPOJO order);
	/**
	 * 保存游戏排行数据
	 */
	public Integer saveGameOrder(GameOrderInfoPOJO order);
	/**
	 * 删除游戏排行数据
	 */
	public Integer deleteGameOrder(Integer gameId);
	/**
	 * 上升游戏排行
	 */
	public Integer upGameOrder(Integer gameId);
	/**
	 * 下降游戏排行
	 */
	public Integer downGameOrder(Integer gameId);
	/**
	 * 查找所有的未被添加到游戏排行的游戏名称
	 */
	public void getAllGameNames(PageInfo<GameInfoPOJO> pageInfo);
}
