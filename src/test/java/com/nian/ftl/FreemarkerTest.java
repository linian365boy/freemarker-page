package com.nian.ftl;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.nian.model.News;

public class FreemarkerTest {
	FreemarkerUtil fu;
	//创建数据模型
	Map<String,Object> root = null;
	
	@Before
	public void setUp(){
		fu = new FreemarkerUtil();
		root = new HashMap<String,Object>();
	}
	
	@Test
	public void test03(){
		root.put("users", "");
		sprint("list.ftl");
		fprint("list.ftl","F:/javaproject/new/freemarker-page/src/main/webapp/html/","list.html");
	}
	
	@Test
	public void testList(){
		Map<String,List<News>> newsMap = new HashMap<String,List<News>>();
		News news1 = new News();
		news1.setTitle("过九点十分是的覆盖件大事福建高速的分配给少打牌风格");
		news1.setContent("UUUUhfs dfhasjdfo asjdfo jsdo jfods jfsod fjosdj of jsodj fosdjfosjdf osj dof");
		news1.setClicks(0);
		News news2 = new News();
		news2.setTitle("攀上快递发红包，是地方哭个屁的反馈高频度地方广东省");
		news2.setContent("UUU发斯蒂芬jfods jfsod fjosdj of 地方刚收到过是电饭锅第三方广东省法国是电饭锅第三方公司jdf osj dof");
		News news3 = new News();
		news3.setTitle("水电费高分公司电饭锅第三方觉得发货sdfsd的高发斯蒂芬是");
		news3.setContent("UU但发生的发生地方方式地方了坚实的房间爱上代价高家度搜发哦电视剧佛电视剧偶发家欧舒丹飞机哦电视剧供电局f osj dof");
		News news4 = new News();
		news4.setTitle("sdfsdf sjdf jasdj gfajsdgjalsd f lasd gasdf");
		news4.setContent("UUUjf sjdofj奇偶电视剧佛山就欧迪芬搜到计费偶家搜狗极爱哦十几天哦啊奇偶体积哦啊点击佛第四家覅节奏i徐新村of");
		News news5 = new News();
		news5.setTitle("jdfgh dfsdfs是度发生的发飞asdghdf hdth gfasd gasdf");
		news5.setContent("广东省法国是东方红手榴弹家覅该时间段费共计水电费该角色的哦机构而进入Tyson过电视剧偶发高三党费V型成v幸福");
		List<News> news = Arrays.asList(news1,news2,news3,news4,news5);
		newsMap.put("1", news);
		root.put("newsMap", newsMap);
		sprint("list.ftl");
		fprint("list.ftl","F:/javaproject/new/freemarker-page/src/main/webapp/html/","list.html");
	}
	
	private void sprint(String name){
		fu.print(name, root);
	}
	
	private void fprint(String name,String fileParentPath,String filename){
		fu.fprint(name, root, fileParentPath,filename);
	}
}
