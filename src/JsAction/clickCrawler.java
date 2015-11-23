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
		String  s="http://wallstreetcn.com/node/226442";
		List<String> url = new ArrayList<String>();
		url.clear();
		url.add(s);
		
		ActionChain  chain=new ActionChain();
		
		ActionNode  node=new ActionNode();
		node.setNodetype(ActionNodeType.Click).setEventElement("div.article-content p a");
		ActionFactory.CreateActionChain(node).ChainEnd("click.js");
		
		Spider.create(this).startUrls(url).setDownloader(new JsDownload(env,"click.js").setEnableclick(true)).run();
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
