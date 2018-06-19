package org.jypj.dev.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class MathUtil {

	private static void run(List<List<String>> dimvalue, List<String> result, int layer, String curstring) {
		// 大于一个集合时：
		if (layer < dimvalue.size() - 1) {
			// 大于一个集合时，第一个集合为空
			if (dimvalue.get(layer).size() == 0)
				run(dimvalue, result, layer + 1, curstring);
			else {
				for (int i = 0; i < dimvalue.get(layer).size(); i++) {
					StringBuilder s1 = new StringBuilder();
					s1.append(curstring);
					s1.append("," + dimvalue.get(layer).get(i));
					run(dimvalue, result, layer + 1, s1.toString());
				}
			}
		}
		// 只有一个集合时：
		else if (layer == dimvalue.size() - 1) {
			// 只有一个集合，且集合中没有元素
			if (dimvalue.get(layer).size() == 0)
				result.add(curstring);
			// 只有一个集合，且集合中有元素时：其笛卡尔积就是这个集合元素本身
			else {
				for (int i = 0; i < dimvalue.get(layer).size(); i++) {
					result.add(curstring + "," + dimvalue.get(layer).get(i));
				}
			}
		}
	}

	public static List<Map<String, String>> runArray(Map<String, String[]> arg) {
		List<List<String>> arrays = new ArrayList<>();
		List<String> descripts = new ArrayList<>();
		for (Map.Entry<String, String[]> entry : arg.entrySet()) {
			descripts.add(entry.getKey());
			arrays.add(Arrays.asList(entry.getValue()));
		}

		List<String> result = new ArrayList<String>();
		MathUtil.run(arrays, result, 0, "");
		List<Map<String, String>> list = new ArrayList<>();
		for (String s : result) {
			s = s.substring(1);
			String[] strs = s.split(",");
			Map<String, String> map = new HashMap<>();
			for (int x = 0; x < strs.length; x++) {
				map.put(descripts.get(x), strs[x]);
			}
			list.add(map);
		}
		Collections.shuffle(list);
		return list;
	}

	private static void doRandom(int n,int L,List<Integer> result) throws Exception{
		System.out.println(n+","+L);
		System.out.println(result);
		//如果两者相等
		if(n<1){
			throw new Exception();
		}else if(n>L){
			for(int i = 0 ; i<L ; i++){
				result.add(1);
			}
		}else if(n==1){
			result.add(L);
		}else if(n==L){
			for(int i = 0 ; i<n ; i++){
				result.add(1);
			}
		}else{//假设n为5,L为100
			Random rand = new Random();
			int i =rand.nextInt(L - n + 1 ) + 1;//在1~96中取随机数
			result.add(i);
			n--;
			doRandom(n,L-i,result);
		}
	}
	
	public static List<Integer> random(int n,int L) throws Exception{
		List<Integer> result=new ArrayList<>();
		doRandom(n,L,result);
		return result;
	}
	
	
	public static List<Integer> random2(int n,int L) throws Exception{
		Set<Integer> indexes=new HashSet<>();
		List<Integer> results=new ArrayList<>();
		int index=0;
		Random rand = new Random();
		if(n<=0){
			throw new Exception();
		}else if(n==1){//如果XX份分成随机一份,那么返回一份
			results.add(L);
		}else if(n>L){//如果5份平均分成6份,不够分
			for(int i=0;i<L;i++){
				results.add(1);
			}
		}else if(n==2){//如果分成2份
			if(L<31){//如果总份数小于31,那么随机分成2份
				index=rand.nextInt(L-1)+1;
				indexes.add(index);
			}else if(L==31){//如果总份数等于31,那么1份为1,1份为30
				indexes.add(1);
			}else{//如果总份数大于31,假设为32,那么一份为1~2之间,另外份为30~31之间
				index=rand.nextInt(L-31)+1;
			}
		}else if(L-n<=29){
			if(L==n+29){//如果总数量减去份数等于29,假设份数为3,总数量为32,那么保证一份达到30个,其他全部为1
				results.add(30);
				for(int i = 0 ;i < n-1 ; i++){
					results.add(1);
				}
			}else{//如果总数量减去份数小于29,假设份数为3,总数量为31,那么不可能有一份达到30个,随机分配
				for(int i=0;i<n-1;){
					index=rand.nextInt(L-1)+1;
					if(!indexes.contains(index)){
						i++;
						indexes.add(index);
					}
				}
			}
		}else if((n-2)*30+2>L){//无法保证其中2份小于30,其余均大于30,那么保证一份大于30,其余随机分配,假设n为4,L为34~61
			for(int i=0;i<n-1;){
				index=rand.nextInt(L-30)+1;
				if(!indexes.contains(index)){
					i++;
					indexes.add(index);
				}
			}
		}else if((n-2)*30+2==L){//刚好保证2个为1,其余为30的情况
			results.add(30);
			for(int i = 0 ;i < n-1 ; i++){
				results.add(1);
			}
		}else{//满足条件的设置2个小于30,其余大于30的情况,假设n为4,L为63
			int sL=L-(n-2)*29;//预留其余大于30的数据,假设为2*29=58,剩下的5个均分为n=4份,不可为0
			for(int i = 0;i< n-1 ;){
				index=rand.nextInt(sL-1)+1;
				if(i>1){
					index+=29*(i-1);
				}
				if(!indexes.contains(index)){
					i++;
					indexes.add(index);
				}
			}
		}
		if(results.size()>0){
			return results;
		}else if(indexes.size()==0){
			throw new Exception();
		}else{
			List<Integer> ints=new ArrayList<>(indexes);
			//排序
			Integer[] in=new Integer[ints.size()];
			ints.toArray(in);
			Arrays.sort(in);
			for(int i = 0;i<in.length;i++){
				results.add(i==0?in[i]:(in[i]-in[i-1]));
			}
			results.add(L-in[in.length-1]);
		}
		
		return results;
	}
	
	
	public static void main(String[] args){
		//random1(5, 100);
		try {
			System.out.println(random(5,7));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
