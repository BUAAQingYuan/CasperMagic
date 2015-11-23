package JsAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import JsFactory.JsFactory;

public class ActionChain {
	
	  private List<ActionNode>  chain=new ArrayList<ActionNode>();
	
	  public  ActionChain   addActionNode(ActionNode node)
	  {
		  this.chain.add(node);
		  return this;
	  }
	  
	  //指定
	  public  ActionChain   ChainEnd(String jsname)
	  {
		  //生成js
		   new  JsFactory().CreateChainJs(this, jsname);
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

}
