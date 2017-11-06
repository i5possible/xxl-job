package com.xxl.job.core.handler.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.google.gson.Gson;
import com.xxl.job.core.biz.model.ExecuteResult;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;

public abstract class KyeeJobHandler extends IJobHandler {

	protected ExecuteResult executeResult = new ExecuteResult();
	
	@Override
	public ReturnT<String> execute(String...params) throws Exception{
		
		ReturnT<String> result = new ReturnT<String>();
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String startTime = simpleDateFormat.format(new Date());
		executeResult.setStartTime(startTime);
		
		ReturnT<String> taskResult = executeTask(params);
		
		String endTime = simpleDateFormat.format(new Date());
		executeResult.setEndTime(endTime);
		
		int code = taskResult.getCode();
		result.setCode(code);
		
		if(200 == result.getCode()){
			executeResult.setState("调度执行成功");
		}
		else{
			executeResult.setState("调度执行失败");
		}
		
		//executeResult.setMsg(taskResult.getMsg());
		
		Gson gson = new Gson();
		String message = gson.toJson(executeResult);
		result.setMsg(message);
		
		return result;		
	}
	
	public abstract ReturnT<String> executeTask(String... params) throws Exception;
	
}

