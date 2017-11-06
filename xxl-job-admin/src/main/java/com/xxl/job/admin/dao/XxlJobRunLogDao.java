package com.xxl.job.admin.dao;

import com.xxl.job.admin.core.model.XxlJobRunLog;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

public interface XxlJobRunLogDao {
	
	public int save(XxlJobRunLog xxlJobRunLog);
	
	public int clearRunLog(@Param("jobGroup") int jobGroup,
						   @Param("jobId") int jobId,
			               @Param("clearBeforeTime") Date clearBeforeTime,
			               @Param("clearBeforeNum") int clearBeforeNum);
}
