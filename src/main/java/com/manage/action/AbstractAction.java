package com.manage.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jmind.core.util.DataUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.util.HtmlUtils;

import com.manage.domain.Condition;

/**
 * @author youfang.mu
 * @2014年3月26日 下午3:30:41
 */
@Controller
public class AbstractAction {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
    private static final String URL_PREFIX = "/";
    private static final String FORWARD = "forward:";
    private static final String REDIRECT = "redirect:";

    protected static final String ERROR500 = "500";
    protected static final String ERROR404 = "404";

    protected String forward(String path) {
        return new StringBuilder(FORWARD).append(path).toString();
    }

    protected String redirect(String path) {
        return new StringBuilder(REDIRECT).append(path).toString();
    }
    
    static String getParam(ServletRequest request, String name, String defaultVal) {
        String v = request.getParameter(name);
        return DataUtil.isEmpty(v) ? defaultVal : v;
    }

    static int getParamInt(ServletRequest request, String name, int defaultVal) {
        String v = request.getParameter(name);
        return DataUtil.isEmpty(v) ? defaultVal : DataUtil.toInt(v);
    }

    protected String getRefer(HttpServletRequest request) {
        return request.getHeader("Referer");
    }

    /* ------------------------- ajax操作jsonp支持 ------------------------- */

    protected String wrapJsonCallback(ServletRequest request, String json) {
        String callback = request.getParameter("callback");
        return DataUtil.isEmpty(callback) ? json : String.format("%s(%s)", HtmlUtils.htmlEscape(callback), json);
    }
    
    protected void responseText(HttpServletResponse response,ServletRequest request,
            String json) {
        // 指定内容类型
        response.setContentType("text/html;charset=UTF-8");
        // 禁止缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        PrintWriter out = null;
        try {
        	String result = wrapJsonCallback(request, json);
            out = response.getWriter();
            out.print(result);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
    
    protected void responseXml(HttpServletResponse response, String xml) {
        // 指定内容类型
    	response.setContentType("text/xml;charset=UTF-8");
    	//response.setCharacterEncoding("utf-8");
        // 禁止缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print(xml);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    protected void logErr(HttpServletRequest request) {
        logger.error("request: {}{}", request.getRequestURL(),
                request.getQueryString() != null ? "?" + request.getQueryString() : "");
        // request.getHeader("Accept")
        logger.error(":UA=" + request.getHeader("User-Agent") + "[refer=" + this.getRefer(request) + "][ajax="
                + request.getHeader("X-Requested-With"));

    }

    /* ------------------------- 异常处理 ------------------------- */

    @ExceptionHandler({ MissingServletRequestParameterException.class, TypeMismatchException.class,
            ConversionFailedException.class })
    public String handleConversionFailedException(Exception e, HttpServletRequest request) {
        request.setAttribute("exception", e);
        request.setAttribute("message", "参数不正确");
        logger.error("e", e);
        logErr(request);
        return URL_PREFIX + "500";
    }

    @ExceptionHandler(Throwable.class)
    public String handleException(Throwable e, HttpServletRequest request) {
        request.setAttribute("exception", e);
        request.setAttribute("message", "未知错误");
        logger.error("e", e);
        logErr(request);
        return URL_PREFIX + "500";
    }

    public boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }
    
    
    
    /* ------------------------- 设置分页（hushengmeng） ------------------------- */
    /**
	 * 设置分页
	 * @param dqPage
	 * @param condition
	 * @param totalRow
	 */
	public Condition setPageInfo(Condition condition,int dqPage, int totalRow,int pageSize) {
		int startRow=0;
		int totalPage=0;  
		if(totalRow%pageSize==0){
    		totalPage=totalRow/pageSize;
    	}else{
    		totalPage=(totalRow/pageSize)+1;
    	}
    	if(dqPage>totalPage){
    		dqPage=totalPage;
    	}
    	if(dqPage>0){
    	    startRow=(dqPage-1)*pageSize;
    	}
    	int upPageNum=0;
		upPageNum=(dqPage-1)<1?1:(dqPage-1);
		int downPageNum=0;
		downPageNum=(dqPage+1)>totalPage?totalPage:(dqPage+1);
		
		condition.setStartRow(startRow);
		condition.setPageSize(pageSize);
		condition.setUpPageNum(upPageNum);
		condition.setDownPageNum(downPageNum);
		condition.setTotalRow(totalRow);
		condition.setTotalPage(totalPage);
		condition.setDqPage(dqPage);
		return condition;
	}
}
