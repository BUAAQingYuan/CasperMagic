package JsAction;

//输入文本框
public class InputText {
		
	private  String   selector;
	
	private  String    value;
	
	public InputText()
	{
		
	}
	
	public InputText(String selector,String value)
	{
		this.selector=selector;
		this.value=value;
	}

	public String getSelector() {
		return selector;
	}

	public void setSelector(String selector) {
		this.selector = selector;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
