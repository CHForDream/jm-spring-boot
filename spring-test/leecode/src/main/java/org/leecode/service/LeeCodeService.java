package org.leecode.service;

import org.leecode.result.IResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class LeeCodeService implements ApplicationContextAware {
	private static final Logger log = LoggerFactory.getLogger(LeeCodeService.class);
	private ApplicationContext ctx;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.ctx = applicationContext;
	}

	public String getResult(int type, int index) {
		IResult result = getResultBean(index);
		if (result == null) {
			log.error("getResult error: " + index);
			return null;
		}

		if (type == 0) {
			return getResultBean(index).process();
		}
		return getResultBean(index).gerneral();
	}

	private IResult getResultBean(int index) {
		return (IResult) ctx.getBean("result_" + index);
	}

}
