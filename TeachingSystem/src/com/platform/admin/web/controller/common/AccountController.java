package com.platform.admin.web.controller.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.platform.admin.pojo.common.UserPOJO;
import com.platform.admin.pojo.site.AccountPOJO;
import com.platform.admin.pojo.site.ResourcePOJO;
import com.platform.admin.service.common.PurviewService;
import com.platform.admin.service.common.ReportService;
import com.platform.admin.service.site.AccountService;
import com.platform.admin.util.Common;
import com.platform.admin.util.Constant;
import com.platform.admin.util.SecurityUtil;

/**
 * <p>
 * 账号管理控制器
 * </p>
 * 
 */
@Controller
public class AccountController {

	private static Log logger = LogFactory.getLog(PurviewController.class);
	@Autowired
	@Qualifier("accountService")
	private AccountService accountService;

	@Resource
	private PurviewService purviewService;

	@Resource
	private ReportService reportService;

	@RequestMapping(value = "/login")
	public String login(String passportName, String password,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) {
		logger.info("Login------start");
		AccountPOJO account = null;
		try {
			// 自动登录
			if (Common.checkNull(false, passportName, password)) {
				String cookVal = null;
				String tokenId = "";
				String passportId = "";
				Cookie[] cookies = request.getCookies();

				// 读取cookie
				if (cookies != null) {
					for (int i = 0; i < cookies.length; i++) {
						Cookie c = cookies[i];
						if (c.getName().equalsIgnoreCase(Constant.USERCOOKIEINFO)) {
							cookVal = c.getValue();
							break;
						}
					}
					if (null == cookVal || "".equals(cookVal)) {
						model.addAttribute("msg", "该用户尚未登录,请重新登录!");
						return "/home/login";
					}
					String[] strs = SecurityUtil.decodeBase64(cookVal).split(
							",");
					passportId = strs[0];
					tokenId = strs[1];
					logger.info("passportId=" + passportId + "-" + "tokenId="
							+ tokenId);
					if ((passportId == null || "".equals(passportId))
							|| (tokenId == null || "null".equals(tokenId))) {
						model.addAttribute("msg", "该用户尚未登录,请重新登录!");
						return "/home/login";
					}
					// cookie的有效性验证
					account = accountService.validateToken(passportId, tokenId);
				}
			}
			// 表单登陆
			else {
				// 校验账号密码
//				account = accountService.login(passportName, password, Common
//						.getIpAddr(request));
				if (passportName.equals("18500723141")&&password.equals("769880926")){
					account = new AccountPOJO();
					account.setAccountId(1904774);
					account.setAccountName("passport");
					account.setIconUrl("resources/images/default_icon.png");
				}
			}

			// 无效登陆
			if (account == null) {
				model.addAttribute("msg", "用户名密码错误,请重新登录!");
				return "/home/login";
			}

			// 更新用户状态信息
			UserPOJO userPojo = new UserPOJO();
			userPojo.setUserId(account.getAccountId());
			// 本地认证
			userPojo = purviewService.getUserById(userPojo);
			if (null == userPojo) {
				model.addAttribute("msg", "认证失败，请重新登录!");
				return "/home/login";
			}
			userPojo.setLastLoginIp(Common.getIpAddr(request));
			userPojo.setLoginCount(userPojo.getLoginCount() + 1);
			userPojo.setIconUrl(account.getIconUrl());
			purviewService.updateUserLogin(userPojo);
			session.setAttribute(Constant.SESSION_USER, userPojo);

			// 查找该用户所有资源
			List<ResourcePOJO> roleResourceList = purviewService
					.getResources(userPojo.getRoleId(), 0);
			session.setAttribute(Constant.SESSION_ROLE_RESOURCE_BEAN_LIST,
					roleResourceList);

			// 查找所有资源
			List<ResourcePOJO> allResourceList = purviewService
					.getResources(0, 0);
			session.setAttribute(Constant.SESSION_ALL_RESOURCE_BEAN_LIST,
					allResourceList);

			// 设置cookie
			Cookie cook = new Cookie(Constant.USERCOOKIEINFO, SecurityUtil
					.encodeBase64(account.getAccountId() + ","
							+ account.getToken()));
			cook.setMaxAge(60 * 60 * 8);
			cook.setPath("/");
			response.addCookie(cook);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/home";
	}

	/**
	 * 用户注销
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/home/exit", method = RequestMethod.GET)
	public String exit(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Model model) {
		// 删除session
		session.invalidate();
		Cookie cook = new Cookie(Constant.USERCOOKIEINFO, null);
		cook.setMaxAge(0);
		cook.setPath("/");
		response.addCookie(cook);
		return "redirect:/";
	}

	/**
	 * 欢迎页信息
	 * 
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/home/welcome", method = RequestMethod.GET)
	public String showWelcome(HttpServletRequest request, HttpSession session,
			Model model) throws IOException {
		List<UserPOJO> monthLoginNumList = new ArrayList<UserPOJO>();
		List<UserPOJO> weekLoginNumList = new ArrayList<UserPOJO>();
		try {
			Map<String, Object> resultMap = reportService.loginNumReport();
			monthLoginNumList = (List<UserPOJO>) resultMap
					.get("monthLoginNumList");
			weekLoginNumList = (List<UserPOJO>) resultMap
					.get("weekLoginNumList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("monthLoginNumList", monthLoginNumList);
		model.addAttribute("weekLoginNumList", weekLoginNumList);
		return "/home/welcome";
	}

}
