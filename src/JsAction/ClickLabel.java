package JsAction;

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
