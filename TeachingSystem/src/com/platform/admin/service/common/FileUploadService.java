package com.platform.admin.service.common;

import com.platform.admin.pojo.common.FileInfoPOJO;
import com.platform.admin.util.PageInfo;

public interface FileUploadService {
	public PageInfo<FileInfoPOJO> getAllFileInfo(String params,int pageSize,int pageIndex) throws Exception ;
	public int saveFileInfo(FileInfoPOJO fileInfo)throws Exception;
}
