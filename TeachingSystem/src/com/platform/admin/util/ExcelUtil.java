package com.platform.admin.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.platform.admin.pojo.common.FileInfoPOJO;

public class ExcelUtil extends AbstractExcelView {
	// public static void exportExcel(List<?> resultList,String filePath){
	//		
	// }

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 参数获取
		List<FileInfoPOJO> fileInfoList = ((PageInfo) model.get("pageInfo"))
				.getList();
		String excelName = (String) model.get("excelName");
		String[] fieldSet = (String[]) model.get("fieldSet");
		// 设置response方式,使执行此controller时候自动出现下载页面,而非直接使用excel打开
		response.setContentType("APPLICATION/OCTET-STREAM");
		try {
			response.setHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode(excelName + ".xls", "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		// 设置
		HSSFSheet sheet = workbook.createSheet("图片地址");
		HSSFRow header = sheet.createRow(0);

		if (fieldSet != null) {
			for (String field : fieldSet) {
				HSSFCell cell = header.createCell((short) header
						.getPhysicalNumberOfCells());
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(FieldSet.MAP_FIELD.get(field));
			}
			if (fileInfoList != null && fileInfoList.size() > 0) {
				for (FileInfoPOJO fileInfo : fileInfoList) {
					HSSFRow row = sheet.createRow(sheet.getLastRowNum() + 1);
					for (String field : fieldSet) {
						String methodName = FieldSet.transformField2Get(field);
						Object value = FileInfoPOJO.class.getMethod(methodName)
								.invoke(fileInfo);
						String returnType = FileInfoPOJO.class.getMethod(
								methodName).getReturnType().getName();
						String cellValue = value.toString();

						HSSFCell rowcell = row.createCell((short) row
								.getPhysicalNumberOfCells());
						//
						if (returnType.equals("java.lang.Integer")) {
							rowcell.setCellValue(Integer.parseInt(cellValue));
						} else if (returnType.equals("java.lang.Long")) {
							rowcell.setCellValue(Long.parseLong(cellValue));
						} else if (returnType.equals("java.lang.Double")) {
							rowcell.setCellValue(Double.parseDouble(cellValue));
						} else {
							rowcell.setCellValue(cellValue);
						}

					}

				}

			} else {
				logger.info("fileInfoList...为空!");
			}

		}

	}
}
