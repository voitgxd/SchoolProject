/**
 * 
 */
package com.platform.admin.service.site.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.platform.admin.dao.site.impl.GameTypeDAOImpl;
import com.platform.admin.pojo.site.GameTypeInfoPOJO;
import com.platform.admin.service.site.GameTypeService;
import com.platform.admin.util.PageInfo;

/**
 * @author gxd
 *
 * 2015-1-30
 */
@Service("gameTypeService")
public class GameTypeServiceImpl implements GameTypeService {
	@Autowired
	@Qualifier("gameTypeDAO")
	private GameTypeDAOImpl gameTypeDAO;
	
	public void getAllTypes(PageInfo<GameTypeInfoPOJO> pageInfo) {
		gameTypeDAO.getAllTypes(pageInfo);
	}
	
	public Integer getGameTypeById(PageInfo<GameTypeInfoPOJO> pageInfo, Integer gameTypeId) {
		return gameTypeDAO.getGameTypeById(pageInfo, gameTypeId);
	}
	
	public Integer updateGameType(GameTypeInfoPOJO gameType){
		return gameTypeDAO.updateGameType(gameType);
	}
	
	public Integer saveGameType(GameTypeInfoPOJO gameType){
		return gameTypeDAO.saveGameType(gameType);
	}
	
	public Integer deleteGameType(Integer gameTypeId){
		return gameTypeDAO.deleteGameType(gameTypeId);
	}
}
