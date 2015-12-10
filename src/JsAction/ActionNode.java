package JsAction;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ActionNode {
		private  int       nodetype;
		//填充数据的元素
		private  String   dataElement;
		private  Map<String,String> attribute=new HashMap<String,String>();
	 
		//发生事件的元素
		private  String   eventElement;
		//fill 、submit、click
		private  String   action;
		private  ClickLabel label;
		
		//检测元素, 
		private  boolean  ischeck=false;
		private  String  checkElement;
		//检测超时
		private  int     checktimeout=ActionFactory.checktimeout;
		
		//构造器
		public   ActionNode()
		{
			
		}
		
		public String getDataElement() {
			return dataElement;
		}
		
		//设置form表单元素
		public ActionNode setDataElement(String dataElement) {
			this.dataElement = dataElement;
			this.ischeck=false;
			return this;
		}
						
		public Map<String,String> getAttribute() {
			return attribute;
		}
		public ActionNode setAttribute(Map<String,String> attribute) {
			this.attribute = attribute;
			return this;
		}
		public String getEventElement() {
			return eventElement;
		}
		public ActionNode setEventElement(String eventElement) {
			this.eventElement = eventElement;
			return this;
		}
		public String getAction() {
			return action;
		}
		public ActionNode setAction(String action) {
			this.action = action;
			return this;
		}

		public int getNodetype() {
			return nodetype;
		}

		public ActionNode setNodetype(int nodetype) {
			this.nodetype = nodetype;
			return  this;
		}

		public String getCheckElement() {
			return checkElement;
		}

		public ActionNode setCheckElement(String checkElement) {
			this.checkElement = checkElement;
			this.ischeck=true;
			return this;
		}
		
		public ActionNode setCheckElement(String checkElement,int checktimeout) {
			this.checkElement = checkElement;
			this.checktimeout=checktimeout;
			this.ischeck=true;
			return this;
		}

		public ClickLabel getLabel() {
			return label;
		}

		public ActionNode setLabel(ClickLabel label) {
			this.label = label;
			return this;
		}

		public boolean isIscheck() {
			return ischeck;
		}

		public void setIscheck(boolean ischeck) {
			this.ischeck = ischeck;
		}

		public int getChecktimeout() {
			return checktimeout;
		}

		public void setChecktimeout(int checktimeout) {
			this.checktimeout = checktimeout;
		}

		
}
