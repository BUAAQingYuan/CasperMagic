package JsAction;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Crawler.JsDownload;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class clickCrawler implements  PageProcessor,Runnable{

	private Site site;
	
	@Override
	public void run() {
		String[]  env={"Path=E:\\casperjs\\bin;E:\\phantomjs-1.9.2;"};
		
		String  s="http://10.2.16.5/login.jsp";
		List<String> url = new ArrayList<String>();
		url.clear();
		url.add(s);
		
		ActionChain  chain=new ActionChain();
		ActionNode  node=new ActionNode();
		//chain.ChainEnd("test.js");
		
		Map<String,String>  attribute=new HashMap<String,String>();
		attribute.put("Login","test");
		attribute.put("Password", "123456");
		node.setNodetype(ActionNodeType.Form).setDataElement("form#form").setAttribute(attribute).setCheckElement("div#link_list",7000);
		chain=ActionFactory.CreateActionChain(node).ChainEnd("tmail.js");
		
		Spider.create(this).startUrls(url).setDownloader(new JsDownload(env,chain).setEnableclick(true)).run();
	}

	@Override
	public Site getSite() {
		site = Site.me().setSleepTime(0).setRetryTimes(5).setCycleRetryTimes(3).setTimeOut(60000);
		site.setCharset("gbk");
		return site;
	}

	@Override
	public void process(Page page) {
		
		//Spider.this.setDownloader(downloader);
		
		/*
		String  test=page.getHtml().xpath("//form[@id='J_StaticForm']/@action").toString();
		
		System.out.println(page.getHtml().toString());
		
		System.out.println(test);
		*/
		
		
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter("query.html",true);
			fileWriter.write(page.getHtml().toString());  
		    fileWriter.close(); // 关闭数据流  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
	   
	}
	
	
	public static void main(String[] args)
	{
		new clickCrawler().run();
	}

}
