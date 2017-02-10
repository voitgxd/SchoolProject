package com.platform.admin.web.controller.site;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.platform.admin.pojo.site.ActiveCodePOJO;
import com.platform.admin.service.site.ActiveCodeService;
import com.platform.admin.util.Common;
import com.platform.admin.util.PageInfo;
import com.platform.admin.util.UploadFile;

@Controller
@RequestMapping("/activeCode")
public class ActiveCodeController {
	@Autowired
	@Qualifier("activeCodeService")
	private ActiveCodeService activeCodeService;
	private final static String UPLOADDIR = "/upload";
	private final static String DOWNLOADFILE = "activeCode.xls";

	/**
	 * 
	 * 查询所有的激活码信息
	 * 
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/showActiveCode")
	public String showActiveCode(PageInfo pageInfo, Model model) {
		if (pageInfo.getPageIndex() == 0) {
			pageInfo.setPageIndex(1);
		}
		pageInfo.setPageSize(10);
		activeCodeService.getActiveCodeInfo(pageInfo);
		model.addAttribute("pageInfo", pageInfo);
		return "/site/activeCode-index";
	}

	/**
	 * 
	 * 删除激活码信息
	 * 
	 * @return
	 */
	@RequestMapping("/deleteActiveCode")
	@ResponseBody
	public Map<String, Object> deleteActiveCode(
			@RequestParam("codeId") String codeId) {
		Map<String, Object> result = new HashMap<String, Object>();
		ActiveCodePOJO acp = new ActiveCodePOJO();
		acp.setCodeId(codeId);
		int retCode = activeCodeService.deleteActiveCode(acp);
		result.put("result", retCode);
		result.put("msg", Common.getMsgByCode(retCode));
		return result;
	}

	/**
	 * 
	 * 通过模板导入激活码信息
	 * 
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/insertActiveCode", method = RequestMethod.POST)
	public String insertActiveCode(MultipartFile file,
			HttpServletRequest request) throws IOException {
		String realPath = request.getSession().getServletContext().getRealPath(
				UPLOADDIR);
		File newFile = new File(realPath);
		if (!newFile.exists()) {
			newFile.mkdir();
		}
		UploadFile uf = new UploadFile();
		String rename = uf.rename(file.getOriginalFilename());// 上传文件重命名
		FileUtils.copyInputStreamToFile(file.getInputStream(), new File(
				realPath, rename));
		String totalPath = realPath.replace('\\', '/') + "/" + rename;
		String[][] result = uf.getData(new File(totalPath), 1);
		int rowLength = result.length;
		List<ActiveCodePOJO> actcList = new ArrayList<ActiveCodePOJO>();
		for (int i = 0; i < rowLength; i++) {
			ActiveCodePOJO acp = new ActiveCodePOJO();
			acp.setCodeId(result[i][0]);
			acp.setPackageId(Integer.parseInt(result[i][1]));
			acp.setCodeStat(Integer.parseInt(result[i][2]));

			actcList.add(acp);
		}
		activeCodeService.insertActiveCode(actcList);

		return "forward:/activeCode/showActiveCode";

	}

	/**
	 * 下载模板
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("static-access")
	@RequestMapping("/getFileName")
	@ResponseBody
	public String getFileName(HttpServletRequest request,
			HttpServletResponse response) {
		String realPath = request.getSession().getServletContext().getRealPath(
				UPLOADDIR);
		String totalPath = realPath.replace('\\', '/') + "/" + DOWNLOADFILE;
		System.out.println(totalPath);
		UploadFile up = new UploadFile();
		up.writeFile(new File(totalPath), response);
		return "/site/activeCode-index";
	}
}
