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
		private  String  checkElement;
		
		
		//构造器
		public   ActionNode()
		{
			
		}
		
		public String getDataElement() {
			return dataElement;
		}
		public ActionNode setDataElement(String dataElement) {
			this.dataElement = dataElement;
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
			return this;
		}

		public ClickLabel getLabel() {
			return label;
		}

		public ActionNode setLabel(ClickLabel label) {
			this.label = label;
			return this;
		}

		
}
