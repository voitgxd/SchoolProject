/**
 * 
 */
package com.platform.admin.service.site.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.platform.admin.dao.site.impl.GameOrderDAOImpl;
import com.platform.admin.pojo.site.GameInfoPOJO;
import com.platform.admin.pojo.site.GameOrderInfoPOJO;
import com.platform.admin.service.site.GameOrderService;
import com.platform.admin.util.PageInfo;

/**
 * @author gxd
 *
 * 2015-1-30
 */
@Service("gameOrderService")
public class GameOrderServiceImpl implements GameOrderService {
	@Autowired
	@Qualifier("gameOrderDAO")
	private GameOrderDAOImpl gameOrderDAO;
	
	public PageInfo<GameOrderInfoPOJO> getAll() {
		return gameOrderDAO.getAll();
	}
	
	public GameOrderInfoPOJO getOrder(Integer gameId) {
		return gameOrderDAO.getOrder(gameId);
	}
	
	public Integer updateGameOrder(GameOrderInfoPOJO order){
		return gameOrderDAO.updateGameOrder(order);
	}
	
	public Integer saveGameOrder(GameOrderInfoPOJO order){
		return gameOrderDAO.saveGameOrder(order);
	}
	
	public Integer deleteGameOrder(Integer gameId){
		return gameOrderDAO.deleteGameOrder(gameId);
	}
	
	public Integer upGameOrder(Integer gameId){
		return gameOrderDAO.upGameOrder(gameId);
	}
	
	public Integer downGameOrder(Integer gameId){
		return gameOrderDAO.downGameOrder(gameId);
	}
	
	public void getAllGameNames(PageInfo<GameInfoPOJO> pageInfo) {
		gameOrderDAO.getAllGameNames(pageInfo);
	}
}
