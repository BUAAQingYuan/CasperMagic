package JsAction;

import Crawler.JsDownload;

/*
 * for example:
 * <a href="...">My link is beautiful</a>
 * 
 * In CasperJS,this will be 
 * 			this.clickLabel('My link is beautiful', 'a');
 * type=a,value=My link is beautiful
 * 
 * before this,you should set 
 * 			new JsDownload(env,chain).setEnableclick(true);
 */

public class ClickLabel {
		private  String   type;
		private  String   value;
		
		public  ClickLabel()
		{
			
		}
		
		public  ClickLabel(String type,String value)
		{
			this.type=type;
			this.value=value;
		}
		
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
}
