package Spider;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Crawler.JsDownload;
import JsAction.ActionChain;
import JsAction.ActionFactory;
import JsAction.ActionNode;
import JsAction.ActionNodeType;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class LoginSpider implements  PageProcessor,Runnable{

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

		
		Map<String,String>  attribute=new HashMap<String,String>();
		attribute.put("Login","test");
		attribute.put("Password", "123456");
		node.setNodetype(ActionNodeType.Form).setDataElement("form#form").setAttribute(attribute).setCheckElement("div#link_list",7000);
		
		chain=ActionFactory.CreateActionChain(node).ChainEnd("login.js");
		
		Spider.create(this).startUrls(url).setDownloader(new JsDownload(env,chain).setEnableclick(false)).run();
	}

	@Override
	public Site getSite() {
		site = Site.me().setSleepTime(0).setRetryTimes(5).setCycleRetryTimes(3).setTimeOut(60000);
		site.setCharset("gb2312");
		return site;
	}

	@Override
	public void process(Page page) {
		
		System.out.println(page.getHtml().toString());
	}
	
	
	public static void main(String[] args)
	{
		new LoginSpider().run();
	}

}
