package org.jypj.dev.test;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.jypj.dev.entity.StudentEduInfo;
import org.jypj.dev.entity.StudentFamInfo;
import org.jypj.dev.entity.StudentInfo;
import org.jypj.dev.service.StudentEduInfoService;
import org.jypj.dev.service.StudentFamInfoService;
import org.jypj.dev.service.StudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/spring/spring*.xml")
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false) 
public class StudentInfoServiceImplTest {
    
    @Autowired
    private StudentInfoService studentInfoService;
    
    @Autowired
    private StudentFamInfoService studentFamService;
    
    @Autowired
    private StudentEduInfoService studentEduServie;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {}

    @Before
    public void setUp() throws Exception {}

    @Test
    public void testSaveStudentInfoByField() {
        fail("Not yet implemented");
    }

    @Test
    public void testSaveStudentInfo() {
        StudentInfo studentInfo = new StudentInfo();
        studentInfo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        studentInfo.setEmployItemsId("123");
        studentInfo.setName("hhhhhj");
        studentInfo.setSex("1");
        studentInfo.setIdentityCard("421181199108237634");
        studentInfo.setBirthday(new Date());
        studentInfo.setPassword("000000");
        studentInfo.setNationCode("258");
        studentInfo.setNativePlace("158");
        studentInfo.setOriginPlace("158");
        studentInfo.setFamilyRegister("北京市");
        studentInfo.setBirthControl("0");
        studentInfo.setHealthy("1");
        studentInfo.setPoliticalStatus("1253");
        studentInfo.setPhotoAttId("1");
        studentInfo.setCertiType("8");
        studentInfo.setExpertiseCode("9");
        studentInfo.setHonorCode("000");
        studentInfo.setTeachingSubject("语文");
        studentInfo.setPublicOffice("0");
        studentInfo.setContractTeacher("1");
        studentInfo.setAttachedUnits("杭州市教育局局");
        studentInfo.setFreeStudent("1");
        studentInfo.setProfessionalCourse("0");
        studentInfo.setMandarinScore("250");
        studentInfo.setEducationScore("250");
        studentInfo.setPsychologyScore("250");
        studentInfo.setPhoneNumber("13712345678");
        studentInfo.setContactPhone("13712345678");
        studentInfo.setAddress("北京市");
        studentInfo.setResume("123456");
        studentInfo.setWorkExperience("123456");
        studentInfo.setStudentType("1");
        studentInfo.setCreateUser("admin");
        studentInfo.setCtime(new Date());
        studentInfo.setModifyUser("admin");
        studentInfo.setMtime(new Date());
        
        
        List<StudentEduInfo> studentEduInfoList = new ArrayList<StudentEduInfo>();
        StudentEduInfo eduInfo = new StudentEduInfo();
        eduInfo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        eduInfo.setEmployItemsId("123");
        eduInfo.setStudentId(studentInfo.getId());
        eduInfo.setEduCode("5555");
        eduInfo.setEduName("本科");
        eduInfo.setEduSchoolCode("9999");
        eduInfo.setEduSchoolName("北京师范大学");
        eduInfo.setEduGraduateTime(new Date());
        eduInfo.setEduProfessionCode("2222");
        eduInfo.setEduProfessionName("汉语言文学");
        eduInfo.setIsSimilarTerm("1");
        eduInfo.setSimilarTermCode("中国经济学");
        eduInfo.setEduBachelorCode("589");
        eduInfo.setEduType("1");
        eduInfo.setCreateUser(studentInfo.getCreateUser());
        eduInfo.setCtime(studentInfo.getCtime());
        eduInfo.setModifyUser(studentInfo.getModifyUser());
        eduInfo.setMtime(studentInfo.getMtime());
        studentEduInfoList.add(eduInfo);

        studentInfo.setStudentEduInfoList(studentEduInfoList);
        
        List<StudentFamInfo> studentFamInfoList = new ArrayList<StudentFamInfo>();
        StudentFamInfo studentFamInfo = new StudentFamInfo();
        studentFamInfo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        studentFamInfo.setEmployItemsId(studentInfo.getEmployItemsId());
        studentFamInfo.setStudentId(studentInfo.getId());
        studentFamInfo.setStudentName(studentInfo.getName());
        studentFamInfo.setMemberRelation("5");
        studentFamInfo.setMemberAge(40);
        studentFamInfo.setMemberName("父亲");
        studentFamInfo.setCreateUser(studentInfo.getCreateUser());
        studentFamInfo.setCtime(studentInfo.getCtime());
        studentFamInfo.setModifyUser(studentInfo.getModifyUser());
        studentFamInfo.setMtime(studentInfo.getMtime());
        studentFamInfoList.add(studentFamInfo);
        
        studentInfoService.saveStudentInfo(studentInfo);
        
        for (StudentFamInfo sfi : studentFamInfoList) {
            studentFamService.saveStudentFamInfo(sfi);
        }
        
        for (StudentEduInfo sei : studentEduInfoList) {
            studentEduServie.saveStudentEduInfo(sei);
        }
    }

    @Test
    public void testDeleteStudentInfoById() {
        fail("Not yet implemented");
    }

    @Test
    public void testDeleteStudentInfoByObject() {
        fail("Not yet implemented");
    }

    @Test
    public void testUpdateStudentInfoByField() {
        fail("Not yet implemented");
    }

    @Test
    public void testUpdateStudentInfo() {
        fail("Not yet implemented");
    }

    @Test
    public void testSelectStudentInfoById() {
        fail("Not yet implemented");
    }

    @Test
    public void testSelectOnePageByMap() {
        fail("Not yet implemented");
    }

    @Test
    public void testSelectOnePageByStudentInfo() {
        fail("Not yet implemented");
    }

    @Test
    public void testSelectAllByMap() {
        fail("Not yet implemented");
    }

    @Test
    public void testSelectAllByStudentInfo() {
        fail("Not yet implemented");
    }

    @Test
    public void testSelectObjectByMap() {
        fail("Not yet implemented");
    }

    @Test
    public void testSelectObjectByStudentInfo() {
        fail("Not yet implemented");
    }

}
