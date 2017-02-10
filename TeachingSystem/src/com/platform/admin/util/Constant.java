package com.platform.admin.util;

public class Constant {
	// --正确返回码
	public static final int RET_OK = 1;
	// --参数错误码
	public static final int SYSTEM_ERROR = -200;// 系统错误
	public static final int BLANK_PARAM_ERROR = -201;// 参数为null
	public static final int GAME_ORDER_NOTFOUND = -203;// 参数为null

	/** token不存在 */
	public static final int TOKEN_NOT_EXIST = -205;

	// --安全错误码
	public static final int SIGN_ERROR = -301;// 签名校验错误

	// --系统错误码
	public static final int DB_ERROR = -500;// 数据库异常

	// 常量
	/** 配置文件项目名 */
	public static final String PROJECT_CONFIG_NAME = "pfAdmin";
	/** 签名key对应的属性名 */
	public static final String SIGN_KEY = "key";
	/** 返回码对应的属性名 */
	public static final String RESULT_CODE_KEY = "resultCode";
	
	/** 返回码对应的属性名 */
	public static final String RESULT_INFO_KEY = "resultInfo";
	/** passport信息对应的属性名 */
	public static final String PASSPORT_INFO_KEY = "passportInfo";
	/** account信息对应的属性名 */
	public static final String ACCOUNT_INFO_KEY = "account";
	/** 登录Token */
	public static final String TOKEN_KEY = "token";
	/** 登录Token对应的cookie名称 */
	public static final String TOKEN_COOKIE_KEY = "ttid";
	/** requestId */
	public static final String REQUEST_ID_KEY = "request_id";
	/** 默认连接pfserver客户端线程数 */
	public static final int DEFAULT_PFSERVER_CLIENT_THREADS = 100;
	/** 业务上用来识别新增保存和修改保存的状态吗*/
	public static final int DEFAULT_SAVE_FLAG = -1;
	
	//
	public static final String SESSION_USER = "session_user";
	
	public static final String SESSION_ALL_RESOURCE_BEAN_LIST = "session_all_resource_bean_list";//系统全部所有resources
	public static final String SESSION_ROLE_RESOURCE_BEAN_LIST = "session_role_resource_bean_list";	//角色全部所有resources
	public static final String SESSION_SITE_RESOURCE_BEAN_LIST = "session_site_resource_bean_list";	//前台资源,包括主菜单和次菜单
	public static final String SESSION_SECOND_RESOURCE_LIST="session_second_resource_bean_list";
	public static final String SESSION_ROLE_LIST="session_role_list";//角色信息
	
	public static final int RES_TYPE_MAIN=1;
	/** 上传应用所用的根路径*/
	public static final String UPLOAD_DIR = "/upload/apps";
	/** 上传图标所用的根路径*/
	public static final String UPLOAD_ICON_DIR = "/upload/icons";
	/** 上传图标到服务器所用的相对路径*/
	public static final String UPLOAD_ICON_TO_SERVER_DIR = "wan";
	/** 访问图标服务器所用的前缀*/
	public static final String ICON_DIR_PREFIX = "http://img.linekong.com";
	/** 上传批量导入Excell所用的根路径*/
	public static final String UPLOAD_EXCELL_DIR = "/upload/excell";
	/** Excell模板所在路径*/
	public static final String DOWNLOAD_EXCELL_DIR = "/upload/excell/model/apps.xls";
	
	public final static String USERCOOKIEINFO = "HB_LOGIN_UNIQUE";
}
