package com.xxl.job.admin.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


public interface XxlJobUserService  {


    Map<String,Object>  executeValidateSso(String workNumber, String token, HttpServletRequest request);

}
