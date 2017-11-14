package com.xxl.job.admin.service.impl;

import com.kyee.common.auth.CommonAuthUtil;
import com.kyee.common.auth.UserInfoVo;
import com.xxl.job.admin.service.XxlJobUserService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class XxlJobUserServiceImpl implements XxlJobUserService {

    @Override
    public Map<String, Object> executeValidateSso(String workNumber, String token, HttpServletRequest request) {

        Map<String, Object> map=new HashMap<String,Object>();

        UserInfoVo userInfo=CommonAuthUtil.getUserInfo(workNumber, token);



        if(userInfo == null){
            map.put("flag", false);
            return map;
        }

        String info=userInfo.getUSER_ID();

        map.put("flag",true);
        return map;
    }

}
