package org.jypj.dev.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.jypj.dev.entity.Grade;
import org.jypj.dev.util.GradeUtils;

public class TestUser {
	
	public static List<Grade> createGradeList(){
		List<Grade> grades=new ArrayList<Grade>();
		grades.add(new Grade("id9","student9","67.34","0","projectId","语文岗","学校1"));
		grades.add(new Grade("id10","student10","78.23","0","projectId","语文岗","学校1"));
		grades.add(new Grade("id1","student1","61.54","1","projectId","语文岗","学校1"));
		grades.add(new Grade("id2","student2","69","1","projectId","语文岗","学校1"));
		grades.add(new Grade("id3","student3","49.23","1","projectId","语文岗","学校1"));
		grades.add(new Grade("id4","student4","45.79","1","projectId","语文岗","学校1"));
		grades.add(new Grade("id5","student5","66","1","projectId","语文岗","学校1"));
		grades.add(new Grade("id6","student6","73.10","1","projectId","语文岗","学校1"));
		grades.add(new Grade("id7","student7","73.10","1","projectId","语文岗","学校1"));
		grades.add(new Grade("id8","student8","70.10","1","projectId","语文岗","学校1"));
		
		grades.add(new Grade("id11","student11","67.55","0","projectId","数学岗","学校1"));
		grades.add(new Grade("id12","student12","21.55","0","projectId","数学岗","学校1"));
		grades.add(new Grade("id13","student13","70.78","1","projectId","数学岗","学校1"));
		grades.add(new Grade("id14","student14","56.14","0","projectId","数学岗","学校1"));
		grades.add(new Grade("id15","student15","43.14","0","projectId","数学岗","学校1"));
		return grades;
	}
	
	@Test
	public void isEmploy(){
		List<Grade> grades=createGradeList();
		Map<String ,List<Grade>> datas=GradeUtils.groupByPositionIdIsEmploy(grades,1,3,50);
		System.out.println("size="+datas.get("pass").size()+" "+datas.get("pass").toString());
		System.out.println("***************************");
		System.out.println("size="+datas.get("noPass").size()+" "+datas.get("noPass").toString());
	}
}
