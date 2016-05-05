package JsFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import JsAction.ActionChain;
import JsAction.ActionFactory;
import JsAction.ActionNode;
import JsAction.ClickLabel;
import JsAction.InputText;

public class JsFactory {
	
	private  Logger logger = LoggerFactory.getLogger(JsFactory.class);
	
	//生成要运行的js文件
	public    boolean   CreateJs(String filename,List<String> content)
	{
		  try {  
	            FileWriter fileWriter = new FileWriter(filename);  
	            
	            for(String line:content)
	            {
	            	String s =line+"\n";
		            fileWriter.write(s);  
	            }
	            fileWriter.close(); // 关闭数据流  
	        } catch (IOException e) {  
	        	logger.error("Can't Create js file!");
	            e.printStackTrace();  
	            return false;
	        }  
		  logger.info("Create js file :"+filename+" success.");
		return false;
	}
	
	//追加
	public  boolean  AddJs(String filename,List<String> content)
	{
		 try {  
	            FileWriter fileWriter = new FileWriter(filename,true);  
	            
	            for(String line:content)
	            {
	            	String s =line+"\n";
		            fileWriter.write(s);  
	            }
	            fileWriter.close(); // 关闭数据流  
	        } catch (IOException e) {  
	        	logger.error("Can't add content to this file!");
	            e.printStackTrace();  
	            return false;
	        }  
		  logger.info("Add content to this file :"+filename+" success.");
		return false;
	}
	
	//js start
	private  List<String>  getJsStart()
	{
		List<String>  content=new ArrayList<String>();
		content.add("var  casper= require('casper').create();");
		content.add("var  url=casper.cli.get(0);");
		content.add("var  charset=casper.cli.get('charset');");
		content.add("var  isaction=casper.cli.get('action');");
		content.add("phantom.outputEncoding=charset;");
		
		/*
		content.add("var fs = require('fs');");
		content.add("var data = fs.read('cookie.txt');");
		content.add("phantom.cookies = JSON.parse(data);");
		*/
		
		content.add("var  pagecontent=true;");
		content.add("var  checktimeout="+ActionFactory.checktimeout+";");
		content.add("if(casper.cli.has('checktimeout'))");
		content.add("{");
		content.add("      checktimeout=casper.cli.get('checktimeout');");
		content.add("}");
		
        content.add("casper.start(url);");
        /*
		content.add("casper.start();");
		content.add("casper.open(url,{");
		content.add("       method:'get',");
		content.add("       headers: {");
		*/
		//content.add("       	'Accept':'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8',");
        /*
		content.add("       	'Accept-Encoding':'gzip, deflate, sdch',");
		content.add("       	'Accept-Language':'zh-CN,zh;q=0.8',");
		content.add("       	'Cache-Control':'no-cache',");
		content.add("       	'Connection':'keep-alive',");
		content.add("       	'Cookie':'_s_tentry=weibo.com; Apache=1883687529998.0017.1461333594095; SINAGLOBAL=1883687529998.0017.1461333594095; ULV=1461333594107:1:1:1:1883687529998.0017.1461333594095:; YF-Page-G0=ab26db581320127b3a3450a0429cde69; SUB=_2AkMgRqFwf8NhqwJRmP0UxWnjbo13wwjEiebDAHzsJxJjHkg67T9lqCSrR_2CttCuxF_-Bz3qBJIw7xEp1Q..; SUBP=0033WrSXqPxfM72-Ws9jqgMF55529P9D9W5TFRoVb_iLQLp6aO3H7O7b; YF-V5-G0=5f9bd778c31f9e6f413e97a1d464047a',");
		content.add("       	'Host':'weibo.com',");
		content.add("       	'Pragma':'no-cache',");
		content.add("       	'Upgrade-Insecure-Requests':'1',");
		content.add("       	'User-Agent':'Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36'");
        
		content.add("		}");
		content.add("		encoding:'utf8'");
		content.add("});");
		*/
		return content;
	}
	
	//js request
	private List<String>  getJsRequest()
	{
		return null;
	}
	
	//js end
	private  List<String>  getJsEnd()
	{
		List<String> content=new ArrayList<String>();
		content.add("casper.then(function() {");
		content.add("         if(pagecontent)");
		content.add("         {");
		content.add("              this.echo(this.getPageContent());");
		content.add("         }");
		
		/*
		content.add("         var fs = require('fs');");
		content.add("         var cookies = JSON.stringify(phantom.cookies);");
		content.add("         fs.write('cookie2.txt', cookies, 644);");
		*/
		
        content.add("});");
        content.add("casper.run();");
        
        return content;
	}
	
	public   List<String> getJsForm(ActionNode node)
	{
		String selector=node.getDataElement();
		Map<String,String> attributes=node.getAttribute();
		boolean addcheck=node.isIscheck();
		
		List<String> content=new ArrayList<String>();
		content.add("if(Boolean(isaction))");
		content.add("{");
		content.add("    casper.then(function() {");
		content.add("         this.fill('"+selector+"',{");
		
		int  mapsize=attributes.entrySet().size();
		int  j=1;
		for(Entry<String,String> one:attributes.entrySet())
		{	
			if(j<mapsize)
			{
				content.add("                 '"+one.getKey()+"': '"+one.getValue()+"' ,");
			}
			else{
				content.add("                 '"+one.getKey()+"': '"+one.getValue()+"' ");
			}
			j++;
		}
        content.add("         },true);");
        
        //check
        if(addcheck)
        {
        	content.add("         this.waitFor(function check(){");
        	content.add("             return this.evaluate(function(){");
        	content.add("                  return document.querySelectorAll('"+node.getCheckElement()+"').length > 0;");
        	content.add("                          });");
        	content.add("         },function then(){");
        	content.add("                 pagecontent=true;");
        	content.add("         },function timeout(){");
        	content.add("                 this.echo('Wait timeout of "+node.getChecktimeout()+"ms expired. i cannot find the checked tag!');");
        	content.add("                 pagecontent=false;");
        	content.add("         },checktimeout);");
        }
        
        content.add("    });");
        content.add("}");
        
        return content;
	}
	
	public  List<String> getJsClickLabel(ClickLabel label)
	{
		List<String> content=new ArrayList<String>();
		content.add("if(Boolean(isaction))");
		content.add("{");
		content.add("    casper.then(function() {");
		
		//clicklabel
		content.add("         this.clickLabel('"+label.getValue()+"','"+label.getType()+"');");
		
		content.add("    });");
	    content.add("}");
		return content;
	}
	
	public  List<String>  getJsClick(ActionNode node)
	{
		List<String> content=new ArrayList<String>();
		boolean addcheck=node.isIscheck();
		
		content.add("if(Boolean(isaction))");
		content.add("{");
		content.add("    casper.then(function() {");
		
		//click
		content.add("         this.click(\""+node.getEventElement()+"\");");
		
		 //check
        if(addcheck)
        {
        	content.add("         this.waitFor(function check(){");
        	content.add("             return this.evaluate(function(){");
        	content.add("                  			return document.querySelectorAll(\""+node.getCheckElement()+"\").length > 0;");
        	content.add("               	 });");
        	content.add("         },function then(){");
        	content.add("                 pagecontent=true;");
        	content.add("         },function timeout(){");
        	content.add("                 this.echo('Wait timeout of "+node.getChecktimeout()+"ms expired. i cannot find the checked tag!');");
        	content.add("                 pagecontent=false;");
        	content.add("         },checktimeout);");
        }
		
        content.add("    });");
	    content.add("}");
	    
		return content;
	}
	
	//等待页面类型
	public  List<String>  getJsWait(ActionNode node)
	{
		List<String> content=new ArrayList<String>();
		boolean addcheck=node.isIscheck();
		
		content.add("casper.then(function() {");
		//check
        if(addcheck)
        {
        	content.add("         this.waitFor(function check(){");
        	content.add("             return this.evaluate(function(){");
        	content.add("                  return document.querySelectorAll('"+node.getCheckElement()+"').length > 0;");
        	content.add("                          });");
        	content.add("         },function then(){");
        	content.add("                 pagecontent=true;");
        	content.add("         },function timeout(){");
        	content.add("                 this.echo('Wait timeout of "+node.getChecktimeout()+"ms expired. i cannot find the checked tag!');");
        	content.add("                 pagecontent=false;");
        	content.add("         },checktimeout);");
        }
        
        content.add("});");
		
		return content;
	}
	
	//填充input输入框
	public List<String>  getJsInputText(ActionNode node)
	{
		List<String> content=new ArrayList<String>();
		InputText input=node.getInput();
		
		content.add("casper.evaluate(function() {");
		content.add("       document.querySelector(\""+input.getSelector()+"\").value="+input.getValue()+";");
		content.add("});");
		
		return content;
	}
	
	//根据ActionChain生成js文件
	public   boolean   CreateChainJs(ActionChain  chain,String jsname)
	{
		CreateJs(jsname,getJsStart());
		
		for(ActionNode node:chain.getChain())
		{
			switch(node.getNodetype())
			{
		     	case 2:
		     		AddJs(jsname,getJsForm(node));
		     		break;
		     	case 3:
		     		AddJs(jsname,getJsClickLabel(node.getLabel()));
		     		break;
		     	case 4:
		     		AddJs(jsname,getJsClick(node));
		     		break;
		     	case 5:
		     		AddJs(jsname,getJsWait(node));
		     		break;
		     	case 6:
		     		AddJs(jsname,getJsInputText(node));
		     		break;
			}
		}
		
		AddJs(jsname,getJsEnd());
		return true;
	}
	
	
	public static void main(String[] args)
	{
		JsFactory  factory=new JsFactory();
		ClickLabel  click=new ClickLabel();
		click.setValue("博客园");
		click.setType("a");
		System.out.println(factory.getJsClickLabel(click));
	}
	
	
}
