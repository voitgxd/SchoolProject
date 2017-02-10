/**
 * 
 */
package com.platform.admin.service.eapp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.platform.admin.dao.eapp.impl.TopicDAOImpl;
import com.platform.admin.pojo.eapp.AppTopicPOJO;
import com.platform.admin.pojo.eapp.AppsPOJO;
import com.platform.admin.service.eapp.TopicService;
import com.platform.admin.util.PageInfo;

/**
 * @author gxd
 *
 * 2015-3-11
 */
@Service("topicService")
public class TopicServiceImpl implements TopicService {
	@Autowired
	@Qualifier("topicDAO")
	private TopicDAOImpl topicDAO;
	
	public void getTopics(PageInfo<AppTopicPOJO> pageInfo) {
		topicDAO.getTopics(pageInfo);
	}

	public void getTopic(int topicId, PageInfo<AppTopicPOJO> pageInfo) {
		topicDAO.getTopic(topicId, pageInfo);
	}

	public void getTopicApp(int topicId, PageInfo<AppsPOJO> pageInfo) {
		topicDAO.getTopicApp(topicId, pageInfo);
	}
	
	public int saveTopic(AppTopicPOJO topic) {
		return topicDAO.saveTopic(topic);
	}

	public int updateTopic(AppTopicPOJO topic) {
		return topicDAO.updateTopic(topic);
	}
	
	public int deleteTopic(AppTopicPOJO topic) {
		return topicDAO.deleteTopic(topic);
	}
	
	public int updateTopicPic(AppTopicPOJO topic) {
		return topicDAO.updateTopicPic(topic);
	}

	/**
	 * 2级页面
	 */
	public int deleteAppFromTopic(int topicId, int appId) {
		return topicDAO.deleteAppFromTopic(topicId, appId);
	}

	public int setAppToTop(int topicId, int appId, String expireTime) {
		return topicDAO.setAppToTop(topicId, appId, expireTime);
	}

	public int upOrDown(int flag, int topicId, int appId) {
		return topicDAO.upOrDown(flag, topicId, appId);
	}

	public void getNeedApp(int topicId, PageInfo<AppsPOJO> pageInfo) {
		topicDAO.getNeedApp(topicId, pageInfo);
	}

	/**
	 * 三级页面
	 */
	public int addAppToTopic(int topicId, String appIds) {
		return topicDAO.addAppToTopic(topicId, appIds);
	}

	public int cancelTop(int topicId, int appId) {
		return topicDAO.cancelTop(topicId, appId);
	}

}
