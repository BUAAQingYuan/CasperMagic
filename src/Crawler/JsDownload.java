package Crawler;

import java.util.List;

import JsAction.ActionChain;
import JsAction.ActionFactory;
import JsExec.CasperExec;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.AbstractDownloader;
import us.codecraft.webmagic.downloader.HttpClientGenerator;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.PlainText;


public class JsDownload extends AbstractDownloader {
	
	private HttpClientGenerator httpClientGenerator = new HttpClientGenerator();
	private String[] env;
	private String  jsname;
	private int  checktimeout=ActionFactory.checktimeout;
	
	//默认为false
	private  boolean  enableaction=false;
	
	public JsDownload(String[] env,String jsname)
	{
		this.env=env;
		this.jsname=jsname;
	}
	
	public JsDownload(String[] env,ActionChain chain)
	{
		this.env=env;
		this.jsname=chain.getJsname();
		this.checktimeout=chain.getChecktimeout();
	}
	
	@Override
	public Page download(Request request, Task task) {
		
		Site site=null;
		if(task!=null) {
			site=task.getSite();
		}
		
		//默认为UTF-8
		String charset=null;
		
		if(site!=null){
			charset=site.getCharset();
		}
		
		if(charset==null){
			charset="UTF-8";
		}
		
        Page page=new Page();
        String  url=request.getUrl();

        //exec js
        String  paras=" --charset="+charset+"  --action="+Boolean.toString(enableaction)+"  --checktimeout="+this.checktimeout;  
    	String  cmd="casperjs.exe  "+jsname+"  "+url+paras;
		//String[]  env={"Path=E:\\casperjs\\bin;E:\\phantomjs-1.9.2;"};
    	System.out.println(cmd);
		List<String> content=CasperExec.ExecCommand(cmd,env);
		String html="";
		for(String line:content)
		{
			html+=line;
		}
		Html   ht=new Html(html);
		page.setHtml(ht);
		page.setRawText(html);
		page.setUrl(new PlainText(url));
        page.setRequest(request);
        
		return page;
	}

	@Override
	public void setThread(int thread) {
		httpClientGenerator.setPoolSize(thread);
	}

	
	public JsDownload setEnableclick(boolean enableclick) {
		this.enableaction = enableclick;
		return this;
	}



}
