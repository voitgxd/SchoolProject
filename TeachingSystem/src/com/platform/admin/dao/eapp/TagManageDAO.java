package com.platform.admin.dao.eapp;

import com.platform.admin.pojo.eapp.AppTagePOJO;
import com.platform.admin.pojo.eapp.AppsPOJO;
import com.platform.admin.pojo.eapp.TagPOJO;
import com.platform.admin.util.PageInfo;

/**
 * 标签管理持久层
 * 
 * @author gxd
 * 
 */
public interface TagManageDAO {
	// 获取所有标签信息
	public void getAllTagInfo(PageInfo<TagPOJO> pageInfo);

	// 添加标签
	public int addTag(TagPOJO tag);

	// 删除标签
	public int deleteTag(TagPOJO tag);

	// 获取要修改的标签信息
	public PageInfo<TagPOJO> getTagInfo(TagPOJO tag);

	// 修改标签信息
	public int updateTagInfo(TagPOJO tag);

	// 根据标签获取游戏信息
	public void getAppsByTag(Integer tagId, PageInfo<AppsPOJO> pageInfo);

	// 取消游戏标签
	public int deleteAppTag(AppTagePOJO appTag);

	// 获取没有打某标签的游戏信息
	public void getAddApps(Integer tagId, PageInfo<AppsPOJO> pageInfo);

	// 为游戏添加标签
	public int addAppToTag(Integer tagId, String appIds);
}
