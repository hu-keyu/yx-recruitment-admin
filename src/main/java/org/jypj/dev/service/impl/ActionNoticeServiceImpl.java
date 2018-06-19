package org.jypj.dev.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jypj.dev.constant.ConstantUitl;
import org.jypj.dev.dao.DictionaryDao;
import org.jypj.dev.dao.InformationDao;
import org.jypj.dev.dao.PostsetDao;
import org.jypj.dev.dao.StudentApplyInfoDao;
import org.jypj.dev.dao.StudentInfoDao;
import org.jypj.dev.entity.Dictionary;
import org.jypj.dev.entity.EaxmLabsInfo;
import org.jypj.dev.entity.EaxmLabsRecord;
import org.jypj.dev.entity.ExamItemsInfo;
import org.jypj.dev.entity.Information;
import org.jypj.dev.entity.Notice;
import org.jypj.dev.entity.Postset;
import org.jypj.dev.entity.StudentApplyInfo;
import org.jypj.dev.entity.StudentInfo;
import org.jypj.dev.service.ActionNoticeService;
import org.jypj.dev.service.EaxmLabsInfoService;
import org.jypj.dev.service.EaxmLabsRecordService;
import org.jypj.dev.service.ExamItemsInfoService;
import org.jypj.dev.service.ExamLectureGroupService;
import org.jypj.dev.service.NoticeService;
import org.jypj.dev.service.ScoreEnterTrialService;
import org.jypj.dev.service.ScoreGradeTrialService;
import org.jypj.dev.vo.InterviewNoticeVo;
import org.jypj.dev.vo.TalkNoticeVo;
import org.jypj.dev.vo.WrittenNoticeVo;
import org.springframework.stereotype.Service;

@Service("actionNoticeService")
public class ActionNoticeServiceImpl implements ActionNoticeService {
    @Resource 
    private StudentApplyInfoDao studentApplyInfoDao;
    
    @Resource 
    private StudentInfoDao studentInfoDao;
    
    @Resource
    private PostsetDao postsetDao;
    
    @Resource
    private DictionaryDao dictionaryDao;
    
    @Resource
    private InformationDao informationDao;
    
    @Resource
    private EaxmLabsRecordService examLabsRecordService;
    
    @Resource
    private ExamItemsInfoService examItemsInfoService;
    
    @Resource
    private EaxmLabsInfoService examLabsInfoService;
    
    @Resource
    private ScoreGradeTrialService scoreGradeTrialService;
    
    @Resource
    private ScoreEnterTrialService scoreEnterTrialService;
    
    @Resource
    private NoticeService noticeService;
    
    @Resource
    private ExamLectureGroupService examLectureGroupService;
    
    @Override
    public List<InterviewNoticeVo> getInterviewNotices(Map<String, Object> map) {
        //发布面试入围名单
        map.put("applyStatus", 7);
        List<StudentApplyInfo> saiList = studentApplyInfoDao.selectInterviewList(map);
        List<InterviewNoticeVo> interviewList = new ArrayList<InterviewNoticeVo>();
        for (StudentApplyInfo sai : saiList) {
            if (sai.getStudentId().equals(map.get("sid").toString())) {
                StudentInfo si = studentInfoDao.selectStudentInfoById(map.get("sid").toString());
              //获取考生端的面试数据
                InterviewNoticeVo interview = new InterviewNoticeVo();
                interview.setStudentApplyId(sai.getId());
                interview.setStudentName(si.getName());
                interview.setIdentityCard(si.getIdentityCard());
                interview.setAdmissionTicket(si.getTicketnum());
                //岗位名称
                Postset ps = postsetDao.selectPostsetById(sai.getApplyJobId());
                interview.setPostName(ps == null ? "" : ps.getPostName());
                //查询学科
                Dictionary dicXk = new Dictionary();
                dicXk.setCode("xklb");
                dicXk.setValue(ps.getSubject());
                dicXk = dictionaryDao.selectObjectByDictionary(dicXk);
                interview.setSubject(dicXk.getText());
                
                //查询面试信息
                Information info = new Information();
                info.setType("1");
                info.setProjectId(sai.getEmployItemsId());
                info.setPositionId(sai.getApplyJobId());  
                info.setSchoolId(sai.getApplyDepId());
                info = informationDao.selectObjectByInformation(info);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date now = new Date();
                interview.setInterviewTime(sdf.format(info.getStartDate() == null? now : info.getStartDate()) + " 至 " + 
                        sdf.format(info.getEndDate() == null ? now : info.getEndDate()));
                interview.setInterviewSite(info.getSite());
                interviewList.add(interview);
            }
        }
        return interviewList;
    }

    @Override
    public List<WrittenNoticeVo> getWrittenNotices(Map<String, Object> map) {
        String recruitId = map.get("recruitId").toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sid = map.get("sid").toString();
        //发布笔试入围名单和考场编排        
        map.put("applyStatus", 8);
        //List<StudentApplyInfo> saiList = studentApplyInfoDao.selectAllByStudentApplyInfo(sai);
        List<StudentApplyInfo> saiList = studentApplyInfoDao.selectInterviewList(map);
        StudentInfo si = studentInfoDao.selectStudentInfoById(map.get("sid").toString());
        
        List<WrittenNoticeVo> writtenList = new ArrayList<WrittenNoticeVo>();
        for (StudentApplyInfo s : saiList) {
            //获取考生端的面试数据
            WrittenNoticeVo written = new WrittenNoticeVo();
            written.setStudentApplyId(s.getId());
            written.setStudentName(si.getName());
            written.setIdentityCard(si.getIdentityCard());
            written.setAdmissionTicket(si.getTicketnum());
            //岗位名称
            Postset ps = postsetDao.selectPostsetById(s.getApplyJobId());
            written.setPostName(ps == null ? "" : ps.getPostName());
            //查询学科
            Dictionary dicXk = new Dictionary();
            dicXk.setCode("xklb");
            dicXk.setValue(ps.getSubject());
            dicXk = dictionaryDao.selectObjectByDictionary(dicXk);
            written.setSubject(dicXk.getText());
            //获取笔试时间
            //根据考生id获取座位号
            EaxmLabsRecord elr = new EaxmLabsRecord();
            elr.setSubjectId(recruitId);
            elr.setStudentId(sid);
            elr = examLabsRecordService.selectObjectByEaxmLabsRecord(elr);
            if (elr != null) {
                written.setWrittenSeat(elr.getSeatNum());//座位号
                EaxmLabsInfo eli = examLabsInfoService.selectEaxmLabsInfoById(elr.getLabsId());
                if (eli != null) {
                    ExamItemsInfo eii = examItemsInfoService.selectExamItemsInfoById(eli.getTestId());
                    if (eii != null) {
                        written.setWrittenExam(eii.getTestName());//考场名称
                        written.setWrittenSite(eii.getTestAddr());//考场地址
                        written.setWrittenRoom(eli.getLabsNum());//试室号
                        written.setRecruitId(recruitId);//招聘项目id
                        written.setWrittenTime(sdf.format(eii.getStartTime()) + " 至 " + sdf.format(eii.getEndTime()));//统一笔试时间
                        //根据考生id查询考场编排
                        writtenList.add(written);
                    }
                }
            }
        }
        return writtenList;
    }


    @Override
    public TalkNoticeVo getTalkNotices(Map<String, Object> map) {
        //发布试讲入围名单和考场编排
        TalkNoticeVo talkNotice = studentApplyInfoDao.selectWrittenOrTalkList(map);
        String talkTime = "";
        if (talkNotice != null && talkNotice.getTalkTime().indexOf("上午") > -1) {
            talkTime = talkNotice.getTalkTime();
            talkTime = talkTime.replaceAll(" ", "").replaceAll("上午", "");
            talkNotice.setTaklArriveTime(talkTime + ConstantUitl.TALK_ARRIVE_TIME_AM);
        } else if(talkNotice != null && talkNotice.getTalkTime().indexOf("下午") > -1){
            talkTime = talkNotice.getTalkTime();
            talkTime = talkTime.replaceAll(" ", "").replaceAll("下午", "");
            talkNotice.setTaklArriveTime(talkTime + ConstantUitl.TALK_ARRIVE_TIME_PM);
        }
        return talkNotice;
    }
}
