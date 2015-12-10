package JsAction;

public class ActionFactory {
	    public static final  int  checktimeout=6000;
	
		public  static   ActionChain   CreateActionChain(ActionNode node)
		{
			ActionChain  chain=new ActionChain();
			return  chain.addActionNode(node);
		}
		
}
