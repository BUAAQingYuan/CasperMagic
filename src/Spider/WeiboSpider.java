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
import Util.FileUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class WeiboSpider implements  PageProcessor,Runnable{

	private Site site;
	
	@Override
	public void run() {
		String[]  env={"Path=E:\\casperjs\\bin;E:\\phantomjs-1.9.2;"};
		
		String s="http://weibo.com/u/1557721227?is_hot=1";
		List<String> url = new ArrayList<String>();
		url.clear();
		url.add(s);
		
		ActionChain  chain=new ActionChain();
		ActionNode  node=new ActionNode();
		
		node.setNodetype(ActionNodeType.Wait).setCheckElement("div.WB_detail").setChecktimeout(10000);
		
		chain=ActionFactory.CreateActionChain(node).ChainEnd("weibo.js");
		
		Spider.create(this).startUrls(url).setDownloader(new JsDownload(env,chain).setEnableclick(false)).run();
	}

	@Override
	public Site getSite() {
		site = Site.me().setSleepTime(0).setRetryTimes(5).setCycleRetryTimes(3).setTimeOut(60000);
		site.setCharset("GBK");
		
		return site;
	}

	@Override
	public void process(Page page) {
		
		
	    FileUtil.PrintToTxt("weiuser.html",page.getHtml().toString());
	    
	    List<String>  weibos=page.getHtml().xpath("//div[@class='WB_detail']//div[@class='WB_text W_f14']/text()").all();
	    for(String one:weibos)
	    {
	    	System.out.println(one);
	    }
	}
	
	
	public static void main(String[] args)
	{
		new WeiboSpider().run();
	}

}
