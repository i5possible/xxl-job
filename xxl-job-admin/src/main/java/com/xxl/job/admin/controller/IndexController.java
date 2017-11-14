package com.xxl.job.admin.controller;

import com.xxl.job.admin.controller.annotation.PermessionLimit;
import com.xxl.job.admin.controller.interceptor.PermissionInterceptor;
import com.xxl.job.admin.dao.XxlJobIndexDao;
import com.xxl.job.admin.service.XxlJobService;
import com.xxl.job.admin.service.XxlJobUserService;
import com.xxl.job.core.biz.model.ReturnT;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * index controller
 * @author xuxueli 2015-12-19 16:13:16
 */
@Controller
public class IndexController {

	@Resource
	private XxlJobService xxlJobService;

	@Resource
	private XxlJobUserService xxlJobUserService;

	@Resource
	private XxlJobIndexDao xxlJobIndexDao;

	@RequestMapping("/")
	public String index(Model model) {

		Map<String, Object> dashboardMap = xxlJobService.dashboardInfo();
		model.addAllAttributes(dashboardMap);

		return "index";
	}

    @RequestMapping("/triggerChartDate")
	@ResponseBody
	@PermessionLimit(limit=false)
	public ReturnT<Map<String, Object>> triggerChartDate() {
        ReturnT<Map<String, Object>> triggerChartDate = xxlJobService.triggerChartDate();
        return triggerChartDate;
    }
	
	@RequestMapping("/toLogin")
	@PermessionLimit(limit=false)
	public String toLogin(Model model, HttpServletRequest request) {
		if (PermissionInterceptor.ifLogin(request)) {
			return "redirect:/";
		}
		return "login";
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	@ResponseBody
	@PermessionLimit(limit=false)
	public ReturnT<String> loginDo(HttpServletRequest request, HttpServletResponse response, String workNumber, String password, String ifRemember){

		if (!PermissionInterceptor.ifLogin(request)) {

			//通过workNumber查询loginPwd
			String loginPwd = xxlJobIndexDao.getLoginPwd(workNumber);

			if(loginPwd!=null){

				if (StringUtils.isNotBlank(workNumber) && StringUtils.isNotBlank(password)
						&& loginPwd.equals(password)) {
					boolean ifRem = false;
					if (StringUtils.isNotBlank(ifRemember) && "on".equals(ifRemember)) {
						ifRem = true;
					}
					PermissionInterceptor.login(response, ifRem);

				} else {
					return new ReturnT<String>(500, "账号或密码错误");
				}
			}else{
				return new ReturnT<String>(500, "账号不存在，请输入正确的账号");
			}

		}
		return ReturnT.SUCCESS;
	}


	/**
	 * 接入公共服务平台，校验公共服务平台用户信息
	 * @param workNumber
	 * @param token
	 * @return
	 */
	@RequestMapping("/publicLogin")
	public String publicLogin(Model model, String workNumber, String token, HttpServletRequest request, HttpServletResponse response){

			return "index";
	}

	@RequestMapping(value="publiclogin", method=RequestMethod.GET)
	@ResponseBody
	@PermessionLimit(limit=false)
	public ReturnT<String> publicLogin(String workNumber, String token, HttpServletRequest request, HttpServletResponse response){

		Map<String, Object> validateSso=xxlJobUserService.executeValidateSso(workNumber, token, request);

		boolean flag = (boolean) validateSso.get("flag");

		if(flag){

			String loginPwd = xxlJobIndexDao.getLoginPwd(workNumber);

			if(loginPwd == null){
				return  new ReturnT<String>(500, "密码为空");
			}

			PermissionInterceptor.login(response, false);
			return ReturnT.SUCCESS;
		}
		return  new ReturnT<String>(500, "Sorry！您没有权限！");
	}



	@RequestMapping(value="logout", method=RequestMethod.POST)
	@ResponseBody
	@PermessionLimit(limit=false)
	public ReturnT<String> logout(HttpServletRequest request, HttpServletResponse response){
		if (PermissionInterceptor.ifLogin(request)) {
			PermissionInterceptor.logout(request, response);
		}
		return ReturnT.SUCCESS;
	}
	
	@RequestMapping("/help")
	public String help() {

		/*if (!PermissionInterceptor.ifLogin(request)) {
			return "redirect:/toLogin";
		}*/

		return "help";
	}

}
