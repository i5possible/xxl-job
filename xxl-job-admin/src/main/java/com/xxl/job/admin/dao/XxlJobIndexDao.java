package com.xxl.job.admin.dao;

import com.xxl.job.admin.core.model.XxlJobIndex;
import org.apache.ibatis.annotations.Param;

public interface XxlJobIndexDao {

    public String getLoginPwd(@Param("workNumber") String workNumber);

}
