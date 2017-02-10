package com.platform.admin.web.controller.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.platform.admin.pojo.common.FileInfoPOJO;
import com.platform.admin.pojo.common.UserPOJO;
import com.platform.admin.service.common.FileUploadService;
import com.platform.admin.util.Common;
import com.platform.admin.util.Constant;
import com.platform.admin.util.PageInfo;
import com.platform.admin.util.ReportExcelFactory;
import com.platform.admin.util.XmlRpcUtil;

@Controller
public class FileUploadController {
	private static  Log logger=LogFactory.getLog(FileUploadController.class);
	@Resource
	private FileUploadService fileUploadService;
	
	@RequestMapping(value="/upload/showFileUpload",method=RequestMethod.GET)
	public String showFileUploadList(Model model,HttpSession session){
		logger.info("showFileUploadList...begin");
		UserPOJO userPojo =(UserPOJO)session.getAttribute(Constant.SESSION_USER);
		model.addAttribute(userPojo);
		return "/upload/fileUploadList";
	}
	
	@RequestMapping(value="/upload/fileUpload",method=RequestMethod.POST)
	public String fileUploadNew(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
		logger.info("fileUploadNew...begin");
		String responseStr="";
		List<FileInfoPOJO> fileInfoList= new ArrayList<FileInfoPOJO>();
		
		MultipartHttpServletRequest multiRequest  =(MultipartHttpServletRequest) request;
		UserPOJO userPojo =(UserPOJO)session.getAttribute(Constant.SESSION_USER);
		try {
			Map<String,MultipartFile> fileMap=multiRequest.getFileMap();
			String fileName=null;
			for(Map.Entry<String, MultipartFile> entry:fileMap.entrySet()){
				MultipartFile file=entry.getValue();
				fileName=file.getOriginalFilename();
				String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();  
				try {
					String result=this.getImageUrl(fileExt,file.getBytes());
					FileInfoPOJO fileInfo= new FileInfoPOJO();
					fileInfo.setPassportId(userPojo.getUserId());
					fileInfo.setUrl("http://img.linekong.com"+result);
					fileInfoList.add(fileInfo);
					logger.info("result="+result);
					responseStr="上传成功";  
				} catch (IOException e) {
					responseStr="上传失败";  
					e.printStackTrace();
					continue;
				}
			}
			
			
			if(fileInfoList!=null&&fileInfoList.size()>0){
				for(int i=0;i<fileInfoList.size();i++){
					////将上传文件入库
					int resultCode=fileUploadService.saveFileInfo(fileInfoList.get(i));
					logger.info("resultCode"+resultCode);
				}
			}else{
				responseStr="没有需要插入的文件";
			}
			
			logger.info("responseStr="+responseStr);
			
		} catch (Exception e) {
			responseStr="上传失败";
			e.printStackTrace();
		}
		response.getWriter().write(responseStr);
		
		return "redirect:/upload/showFileUpload";
	}
	
	@SuppressWarnings("unchecked")
	public String getImageUrl(String fileExt,byte[] fileBytes) throws Exception{
		XmlRpcUtil xmlRpc=new XmlRpcUtil(Common.getImageUrl(),100);
		String da = new String(Base64.encodeBase64(fileBytes));
		Vector params = new Vector();
		params.add("wan");
		params.add(String.valueOf(System.currentTimeMillis()/1000));
		params.add(da);
		params.add("."+fileExt);
		Object result = xmlRpc.invoke("insert", params);
		return result.toString();
	}
	
	@RequestMapping(value="/upload/getAllFileInfo",method=RequestMethod.GET)
	public void getAllFileInfo(Model model,HttpServletRequest request,HttpServletResponse response){
		logger.info("getAllFileInfo...start begin");
		String sEcho = "", iDisplayStart = "", iDisplayLength = "",sSearch="";
		try {
			String jsonData=request.getParameter("aoData");
			JSONArray jsonArr=JSONArray.fromObject(jsonData);
			for (int i = 0; i < jsonArr.size(); i++) {
				JSONObject obj = (JSONObject) jsonArr.get(i);
				if (obj.get("name").equals("sEcho"))
					sEcho = obj.get("value").toString();
				if (obj.get("name").equals("iDisplayStart"))
					iDisplayStart = obj.get("value").toString();
				if (obj.get("name").equals("iDisplayLength"))
					iDisplayLength = obj.get("value").toString();
				if (obj.get("name").equals("sSearch"))
					sSearch = obj.get("value").toString();
				}
			//分页的当期页号
			int pageIndex=(Integer.parseInt(iDisplayStart)/Integer.parseInt(iDisplayLength))+1;
			logger.info("sEcho="+sEcho+"-"+"iDisplayStart="+pageIndex+"-"+"iDisplayLength="+iDisplayLength+"sSearch="+sSearch);
			PageInfo<FileInfoPOJO> fileInfo=fileUploadService.getAllFileInfo(sSearch,Integer.parseInt(iDisplayLength),pageIndex);
			String result=this.toJSON(fileInfo,Integer.parseInt(sEcho));
			response.getWriter().print(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/upload/fileReportExcel",method=RequestMethod.GET)
	public ModelAndView fileReportExcel(FileInfoPOJO fileInfo, HttpSession session, HttpServletRequest request, HttpServletResponse response){
		logger.info("fileReportExcel....start");
		PageInfo pageInfo=new PageInfo();
		try {
			pageInfo = fileUploadService.getAllFileInfo(null, 600000, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ReportExcelFactory.fileReport(fileInfo, pageInfo);
	}
	
	
	@SuppressWarnings("unchecked")
	public String toJSON(PageInfo<?> obj,int sEcho)throws Exception{
		String jsonStr=null;
		String aaData="";
		List<Object> resultList=new ArrayList<Object>();
		if(obj!=null&&obj.getList().size()>0){
			resultList=(List<Object>) obj.getList();
			aaData=JSONArray.fromObject(resultList).toString();
		}
		if(StringUtils.isBlank(aaData)){
			aaData="0";
		}
		jsonStr="{\"sEcho\":"+sEcho+",\"iTotalRecords\":"+obj.getTotalSize()+",\"iTotalDisplayRecords\":" +
		obj.getTotalSize()+",\"aaData\":"+aaData+"}";
		
		
		return jsonStr;
	}
}
