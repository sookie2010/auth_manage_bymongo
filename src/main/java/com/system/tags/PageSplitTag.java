package com.system.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

/**
 * JSP自定义标签类 , 分页按钮工具条
 * @author 结发受长生
 *
 */
public class PageSplitTag extends TagSupport {
	private static final long serialVersionUID = 7708371317711245049L;
	private static Logger log = Logger.getLogger(PageSplitTag.class);
	private Page page;
	
	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try{
			if(page.getResult()==null || page.getResult().isEmpty()) {
				out.println("没有可以显示的数据");
				out.flush();
				return SKIP_BODY;
			}
			out.println("第" + page.getPageNow() + "页/共" + page.getPageCount() + "页");
			if(page.getPageNow() != 1){
				//如果当前页不是第一页,则给"首页"添加超链接,并且添加"上一页"链接
				out.println(
						"<a class='page_btn' href='" + page.getLinkUrl() + "' page='1''>首页</a>");
				out.println(
						"<a class='page_btn back' href='" + page.getLinkUrl() + "' "
						+ "page='" + (page.getPageNow()-1) + "'"
						+ ">上一页</a>");
			} else {
				//如果当前页是首页 ,则只显示"首页"文字 ,且没有"上一页"按钮
				out.println("<span class='page_btn'>首页</span>");
			}
			if(page.getPageNow() > 3){
				out.println("<span>...</span>");
			}
			
			//只显示当前页的前2页与后2页的超链接
			for(int i=page.getPageNow()-2 ; i<=page.getPageNow()+2 ; i++){
				if(i<=0 || i>page.getPageCount()) {
					continue;
				} 
				if(i == page.getPageNow()){
					//当前页添加超链接
					out.println("<span class='page_btn'>"+i+"</span>");
				} else {
					out.println(
							"<a class='page_btn' href='" + page.getLinkUrl() +"'"
							+ " page='" + i +"'"
							+ ">"+i+"</a>");
					
				}
			}
			if(page.getPageNow() <= page.getPageCount()-3){
				out.println("<span>...</span>");
			}
			if(page.getPageNow() == page.getPageCount()){
				//判断当前页是否是最后一页
				out.println("<span class='page_btn'>尾页</span>");
			} else {
				out.println(
						"<a class='page_btn next' href='" + page.getLinkUrl() +"' "
						+ "page='" + (page.getPageNow()+1) + "'"
						+ ">下一页</a>");
				out.println(
						"<a class='page_btn' href='" + page.getLinkUrl() + "' page='" + page.getPageCount() + "'>尾页</a>");
			}
			
		} catch (IOException e){
			log.error("分页标签执行出错",e);
		}
		return SKIP_BODY;
	}
	
	@Override
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			if(page.getResult()!=null && !page.getResult().isEmpty()) {
				out.println("<select name='pageSize' style='width:60px;'>");
				for(int i=10 ; i<=50 ; i+=10){
					if(i == page.getPageSize()){
						out.println("<option value='"+i+"' selected>"+i+"</option>");
					} else {
						out.println("<option value='"+i+"'>"+i+"</option>");
					}
				}
				out.println("</select>");
			}
		} catch (IOException e){
			log.error("分页标签执行出错",e);
		}
		
		return EVAL_PAGE;
	}
	
	@Override
	public void release() {
		page = null;
		super.release();
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

}
