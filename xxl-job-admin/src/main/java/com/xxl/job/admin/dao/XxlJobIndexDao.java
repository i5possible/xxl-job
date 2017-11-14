package com.xxl.job.admin.dao;

import org.apache.ibatis.annotations.Param;

public interface XxlJobIndexDao {

    public int getuserCount(@Param("workNumber") String workNumber, @Param("passWord") String passWord);

    public String getLoginPwd(@Param("workNumber") String workNumber);
}
