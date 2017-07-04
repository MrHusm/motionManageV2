package com.manage.webinit;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;


@Component
public class InitContext implements ServletContextAware {
	private ServletContext context;
    @PostConstruct
    public void init() {
        //常量
        //context.setAttribute("consts", BookUtil.constsMap);
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.context = servletContext;
    }
}
