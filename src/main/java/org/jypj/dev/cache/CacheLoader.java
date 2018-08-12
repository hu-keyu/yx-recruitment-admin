package org.jypj.dev.cache;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.jypj.dev.entity.Dictionary;
import org.jypj.dev.service.DictionaryService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

/**
 * 将字典数据加载到内存中
 * 
 * @author ChenYu
 *
 */
@Component
public class CacheLoader implements ApplicationContextAware, ServletContextAware, InitializingBean,
		ApplicationListener<ContextRefreshedEvent> {

	@Resource
	private DictionaryService dictionaryService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {

	}

	@Override
	public void afterPropertiesSet() throws Exception {

	}

	@Override
	public void setServletContext(ServletContext servletContext) {

	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		try {			List<Dictionary> all = dictionaryService.selectAllByDictionary(new Dictionary());
			if (all != null && all.size() > 0) {
				DictionaryCache.setDicCache(all);
			} else {
				new Exception("字典数据加载失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void testGithub() {

	}

}
