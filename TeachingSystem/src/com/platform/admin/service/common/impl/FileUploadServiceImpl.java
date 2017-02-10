package com.platform.admin.service.common.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.platform.admin.dao.common.FileUploadDAO;
import com.platform.admin.pojo.common.FileInfoPOJO;
import com.platform.admin.service.common.FileUploadService;
import com.platform.admin.util.PageInfo;

@Service("fileUploadService")
@Transactional
public class FileUploadServiceImpl implements FileUploadService {
	// private static Log logger=LogFactory.getLog(FileUploadServiceImpl.class);
	@Resource
	private FileUploadDAO fileUploadDAO;

	public PageInfo<FileInfoPOJO> getAllFileInfo(String params, int pageSize,
			int pageIndex) throws Exception {
		return fileUploadDAO.getAllFileInfo(params, pageSize, pageIndex);
	}

	public int saveFileInfo(FileInfoPOJO fileInfo) throws Exception {
		return fileUploadDAO.saveFileInfo(fileInfo);
	}

}
