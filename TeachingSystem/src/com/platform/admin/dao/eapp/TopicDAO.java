/**
 * 
 */
package com.platform.admin.dao.eapp;

import com.platform.admin.pojo.eapp.AppTopicPOJO;
import com.platform.admin.pojo.eapp.AppsPOJO;
import com.platform.admin.util.PageInfo;

/**
 * @author gxd
 *
 * 2015-3-11
 */
public interface TopicDAO {
	
	public void getTopics(PageInfo<AppTopicPOJO> pageInfo);
	public void getTopic(int topicId, PageInfo<AppTopicPOJO> pageInfo);
	public int saveTopic(AppTopicPOJO topic);
	public int updateTopic(AppTopicPOJO topic);
	public int deleteTopic(AppTopicPOJO topic);
	public int updateTopicPic(AppTopicPOJO topic);
	public void getTopicApp(int topicId, PageInfo<AppsPOJO> pageInfo);
	
	public int deleteAppFromTopic( int topicId, int appId);
	public int upOrDown(int flag, int topicId, int appId);
	public int setAppToTop(int topicId, int appId, String expireTime);
	public int cancelTop(int topicId, int appId);
	public void getNeedApp(int topicId, PageInfo<AppsPOJO> pageInfo);
	
	public int addAppToTopic(int topicId, String appIds);
}
