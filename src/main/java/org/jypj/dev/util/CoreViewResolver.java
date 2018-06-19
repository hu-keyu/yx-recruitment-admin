/**
 * 
 */
package org.jypj.dev.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

/**
 * @author HyNo
 * 
 */
public class CoreViewResolver implements ViewResolver {

	public View resolveViewName(String viewName, Locale locale)
			throws Exception {
		for (Map.Entry<Set<String>, ViewResolver> map : viewResolverMap
				.entrySet()) {
			Set<String> suffixs = map.getKey();
			for (String suffix : suffixs) {
				if (viewName.endsWith(suffix)) {
					ViewResolver viewResolver = map.getValue();
					if (null != viewResolver) {
						return viewResolver.resolveViewName(viewName, locale);
					}
				}
			}
		}

		if (defaultViewResolver != null) {
			return defaultViewResolver.resolveViewName(viewName, locale);
		}
		return null;
	}

	private Map<Set<String>, ViewResolver> viewResolverMap = new HashMap<Set<String>, ViewResolver>();

	private ViewResolver defaultViewResolver = null;

	public Map<Set<String>, ViewResolver> getViewResolverMap() {
		return viewResolverMap;
	}

	public void setViewResolverMap(
			Map<Set<String>, ViewResolver> viewResolverMap) {
		this.viewResolverMap = viewResolverMap;
	}

	public ViewResolver getDefaultViewResolver() {
		return defaultViewResolver;
	}

	public void setDefaultViewResolver(ViewResolver defaultViewResolver) {
		this.defaultViewResolver = defaultViewResolver;
	}
}
