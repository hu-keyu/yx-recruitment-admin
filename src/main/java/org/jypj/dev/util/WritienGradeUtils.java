package org.jypj.dev.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.jypj.dev.entity.ScoreGradeWriten;


/**
 * @author WangYifan
 *统一笔试比例和分数线计算
 *
 */
public class WritienGradeUtils {
	
	/**
	 * 录取形式 比例分数线
	 */
	private static final int Enter_Condition_Score_Ratio=1;
	
	/**
	 * 录取形式 比例
	 */
	private static final int Enter_Condition_Ratio=2;
	
	/**
	 * 录取形式 分数
	 */
	private static final int Enter_Condition_Score=3;
	
	/**
	 * 对岗位分组
	 * @param grades
	 * @return
	 */
	private static Map<String ,List<ScoreGradeWriten>> groupByPositionId(List<ScoreGradeWriten> grades){
		Map<String ,List<ScoreGradeWriten>> mapDates=new HashMap<String ,List<ScoreGradeWriten>>();
		Set<String> positionIds=new HashSet<String>();
		for(ScoreGradeWriten grade : grades){
			positionIds.add(grade.getPositionId());
		}
		Iterator<String> it=positionIds.iterator();
		List<ScoreGradeWriten> gradeItems=null;
		while(it.hasNext()){
			String positionId=it.next();
			gradeItems=new ArrayList<ScoreGradeWriten>();
			for(ScoreGradeWriten grade : grades){
				if(positionId.equals(grade.getPositionId())){
					gradeItems.add(grade);
				}
			}
			mapDates.put(positionId, gradeItems);
		}
		return mapDates;
	}
	
	/**
	 * 返回是否通过最低分数线的集合
	 * @param grades
	 * @param interviewEnterLine
	 * @return
	 */
	private static Map<String, List<ScoreGradeWriten>> isPassScore(List<ScoreGradeWriten> grades,int interviewEnterLine){
		Map<String ,List<ScoreGradeWriten>> mapDates=new HashMap<String ,List<ScoreGradeWriten>>();
		List<ScoreGradeWriten> gradePass=new ArrayList<ScoreGradeWriten>();
		List<ScoreGradeWriten> gradeNoPass=new ArrayList<ScoreGradeWriten>();
		for(ScoreGradeWriten grade : grades){
			Double score=Double.valueOf(grade.getGrade());
			if(Double.compare(score, new Double(interviewEnterLine)) >= 0){
				gradePass.add(grade);
			}else{
				gradeNoPass.add(grade);
			}
		}
		mapDates.put("pass", gradePass);
		mapDates.put("noPass", gradeNoPass);
		return mapDates;
	} 
	
	/**
	 * 返回是否通过比例的集合
	 * @param grades
	 * @param interviewEnterPropo
	 * @return
	 */
	private static Map<String, List<ScoreGradeWriten>> isPassRatio(List<ScoreGradeWriten> grades,int interviewEnterPropo){
		int totleCount=grades.size();
		int index=totleCount/interviewEnterPropo;
		Map<String ,List<ScoreGradeWriten>> mapDates=new HashMap<String ,List<ScoreGradeWriten>>();
		List<ScoreGradeWriten> ratioPass=new ArrayList<ScoreGradeWriten>();
		List<ScoreGradeWriten> ratioNoPass=new ArrayList<ScoreGradeWriten>();
		if(index > 0 && index <= totleCount){
			grades=orderByScore(grades);
			boolean flag=hasRepeatGrade(grades);
			for(int i=0;i<index;i++){
				ratioPass.add(grades.get(i));
			}
			for(int i=index;i<grades.size();i++){
				ratioNoPass.add(grades.get(i));
			}
			if(flag){//有相同的分数
				ScoreGradeWriten grade=grades.get(index-1);
				Map<String, List<ScoreGradeWriten>> datas=isPassRatio(ratioPass,ratioNoPass,Double.valueOf(grade.getGrade()).doubleValue());
				ratioPass=datas.get("pass");
				ratioNoPass=datas.get("noPass");
			}
		}else{
			ratioNoPass=grades;
		}
		mapDates.put("pass", ratioPass);
		mapDates.put("noPass", ratioNoPass);
		return mapDates;
	}
	
	/**
	 * 处理相同分数的考生 如：3:1的比例，A/B/C同为80分，则A/B/C都录取
	 * @param ratioPass 
	 * @param ratioNoPass
	 * @param score
	 * @return
	 */
	private static Map<String, List<ScoreGradeWriten>> isPassRatio(List<ScoreGradeWriten> ratioPass, List<ScoreGradeWriten> ratioNoPass, double score) {
		Map<String ,List<ScoreGradeWriten>> mapDates=new HashMap<String ,List<ScoreGradeWriten>>();
		List<ScoreGradeWriten> sameGrade=new ArrayList<ScoreGradeWriten>();
		for(ScoreGradeWriten grade : ratioNoPass){
			if(score == Double.valueOf(grade.getGrade()).doubleValue()){
				sameGrade.add(grade);
			}
		}
		if(sameGrade !=null && !sameGrade.isEmpty() ){
			ratioNoPass.removeAll(sameGrade);
			ratioPass.addAll(sameGrade);
		}
		mapDates.put("pass", ratioPass);
		mapDates.put("noPass", ratioNoPass);
		return mapDates;
	}

	/**
	 * 返回排序后的成绩
	 * @param grades
	 * @param index
	 * @return
	 */
	private static List<ScoreGradeWriten> orderByScore(List<ScoreGradeWriten> grades){
		ScoreGradeWriten [] gradeArray=grades.toArray(new ScoreGradeWriten []{});
		for(int i = 0; i < gradeArray.length -1; i++){
			for(int j = 0 ;j < gradeArray.length - i - 1; j++){
				if(Double.valueOf(gradeArray[j].getGrade()) < Double.valueOf(gradeArray[j+1].getGrade())){
					ScoreGradeWriten temp = gradeArray[j];
					gradeArray[j] = gradeArray[j+1];
					gradeArray[j+1] = temp;
				}
			}
		}
		return Arrays.asList(gradeArray);
	}
	
	/**
	 * 判断是否有相同的分数
	 * @param grades
	 * @return
	 */
	private static boolean hasRepeatGrade(List<ScoreGradeWriten> grades){
		boolean flag=false;
		ScoreGradeWriten [] gradeArray=grades.toArray(new ScoreGradeWriten []{});
		for(int i = 0; i < gradeArray.length; i++){
			for(int j = i+1 ;j < gradeArray.length; j++){
				if(gradeArray[i].getGrade().equals(gradeArray[j].getGrade())){
					flag=true;
					break;
				}
			}
			if(flag){
				break;
			}
		}
		return flag;
	} 
	
	/**
	 * 
	 * @param grades
	 * @param enterCondition 录取形式
	 * @param interviewEnterPropo 录取比例
	 * @param interviewEnterLine 录取分数线
	 * @return
	 */
	private static Map<String, List<ScoreGradeWriten>> isEmploy(List<ScoreGradeWriten> grades,int enterCondition,int interviewEnterPropo,int interviewEnterLine){
		Map<String ,List<ScoreGradeWriten>> mapDates=new HashMap<String ,List<ScoreGradeWriten>>();
		if(Enter_Condition_Score_Ratio == enterCondition){//比例分数线 
			mapDates=isPassScore(grades, interviewEnterLine);
			List<ScoreGradeWriten> gradepass=mapDates.get("pass");
			List<ScoreGradeWriten> gradeNoPass=mapDates.get("noPass");
			mapDates=isPassRatio(gradepass,interviewEnterPropo);
			gradepass=mapDates.get("pass");
			gradeNoPass.addAll(mapDates.get("noPass"));
			mapDates.clear();
			mapDates.put("pass", gradepass);
			mapDates.put("noPass", gradeNoPass);
		}else if(Enter_Condition_Ratio == enterCondition){//比例
			mapDates=isPassRatio(grades,interviewEnterPropo);
		}else if(Enter_Condition_Score == enterCondition){//分数
			mapDates=isPassScore(grades, interviewEnterLine);
		}
		return mapDates;
	}
	
	/**
	 * 
	 * @param grades
	 * @param enterCondition 录取形式 1比例分数线 2比例 3分数
	 * @param interviewEnterPropo 录取比例
	 * @param interviewEnterLine 录取分数线
	 * @return Map
	 */
	public static Map<String ,List<ScoreGradeWriten>> groupByPositionIdIsEmploy(List<ScoreGradeWriten> grades,int enterCondition,int interviewEnterPropo,int interviewEnterLine){
		Map<String ,List<ScoreGradeWriten>> datas=groupByPositionId(grades);
		List<ScoreGradeWriten> gradePass=new ArrayList<ScoreGradeWriten>();
		List<ScoreGradeWriten> gradeNoPass=new ArrayList<ScoreGradeWriten>();
		for(Entry<String, List<ScoreGradeWriten>> items : datas.entrySet()){
			List<ScoreGradeWriten> gradeItems=items.getValue();
			Map<String ,List<ScoreGradeWriten>> data=isEmploy(gradeItems,enterCondition,interviewEnterPropo,interviewEnterLine);
			gradePass.addAll(data.get("pass"));
			gradeNoPass.addAll(data.get("noPass"));
		}
		datas.put("pass", gradePass);
		datas.put("noPass", gradeNoPass);
		return datas;
	}
}
