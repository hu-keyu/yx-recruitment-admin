package org.jypj.dev.service;

import java.util.List;
import java.util.Map;

import org.jypj.dev.vo.InterviewNoticeVo;
import org.jypj.dev.vo.TalkNoticeVo;
import org.jypj.dev.vo.WrittenNoticeVo;

public interface ActionNoticeService {
    
 	/**
	 * 获取面试通知单
	 * @param user
	 * @return 
	 */	
	public List<InterviewNoticeVo> getInterviewNotices(Map<String,Object> map);
	
	/**
     * 获取统一笔试通知单
     * @param user
     * @return 
     */ 
    public List<WrittenNoticeVo> getWrittenNotices(Map<String,Object> map);
    
    /**
     * 获取统一试讲通知单
     * @param map
     * @return
     */
    public TalkNoticeVo getTalkNotices(Map<String,Object> map);
	
}
