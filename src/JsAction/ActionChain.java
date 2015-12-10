package JsAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import JsFactory.JsFactory;

public class ActionChain {
	
	  private List<ActionNode>  chain=new ArrayList<ActionNode>();
	  private String   jsname;
	  private int        checktimeout;
	  
	  
	
	  public  ActionChain   addActionNode(ActionNode node)
	  {
		  this.chain.add(node);
		  
		  //获取node中的全局参数
		  if(node.isIscheck())
		  {
			  this.checktimeout=node.getChecktimeout();
		  }
		  
		  return this;
	  }
	  
	  //指定
	  public  ActionChain   ChainEnd(String jsname)
	  {
		  //生成js
		   new  JsFactory().CreateChainJs(this, jsname);
		   this.jsname=jsname;
		   return this;
	  }
	  
	 
	public List<ActionNode> getChain() {
		return this.chain;
	}
	
	public static void main(String[] args)
	{
		ActionChain  chain=new ActionChain();
		Map<String,String> attribute=new HashMap<String,String>();
		attribute.put("wd", "CasperJS");
		ActionNode  node=new ActionNode();
		node.setNodetype(ActionNodeType.Form).setDataElement("form#form").setAttribute(attribute);
		ActionFactory.CreateActionChain(node).ChainEnd("first.js");
		
	}

	public String getJsname() {
		return jsname;
	}

	public void setJsname(String jsname) {
		this.jsname = jsname;
	}

	public int getChecktimeout() {
		return checktimeout;
	}

	public void setChecktimeout(int checktimeout) {
		this.checktimeout = checktimeout;
	}

}
