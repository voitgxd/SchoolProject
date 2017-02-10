package com.platform.admin.service.eapp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.platform.admin.dao.eapp.TagManageDAO;
import com.platform.admin.pojo.eapp.AppTagePOJO;
import com.platform.admin.pojo.eapp.AppsPOJO;
import com.platform.admin.pojo.eapp.TagPOJO;
import com.platform.admin.service.eapp.TagManageService;
import com.platform.admin.util.PageInfo;

/**
 * 标签管理业务实现
 * 
 * @author chentao
 * 
 */
@Service("tagManageService")
public class TagManageServiceImpl implements TagManageService {
	@Autowired
	@Qualifier("tagManageDAO")
	private TagManageDAO tagManageDAO;

	// 添加标签
	public int addTag(TagPOJO tag) {

		return tagManageDAO.addTag(tag);
	}

	// 删除标签
	public int deleteTag(TagPOJO tag) {

		return tagManageDAO.deleteTag(tag);
	}

	// 获取所有标签
	public void getAllTagInfo(PageInfo<TagPOJO> pageInfo) {
		tagManageDAO.getAllTagInfo(pageInfo);

	}

	// 获取要修改的标签
	public PageInfo<TagPOJO> getTagInfo(TagPOJO tag) {
		return tagManageDAO.getTagInfo(tag);
	}

	// 修改标签
	public int updateTagInfo(TagPOJO tag) {
		return tagManageDAO.updateTagInfo(tag);
	}

	// 根据标签获取游戏信息
	public void getAppsByTag(Integer tagId, PageInfo<AppsPOJO> pageInfo) {
		tagManageDAO.getAppsByTag(tagId, pageInfo);

	}

	// 取消游戏标签
	public int deleteAppTag(AppTagePOJO appTag) {
		return tagManageDAO.deleteAppTag(appTag);
	}

	// 获得未贴该标签的游戏信息
	public void getAddApps(Integer tagId, PageInfo<AppsPOJO> pageInfo) {
		tagManageDAO.getAddApps(tagId, pageInfo);

	}

	// 给游戏贴标签
	public int addAppToTag(Integer tagId, String appIds) {
		return tagManageDAO.addAppToTag(tagId, appIds);

	}
}
