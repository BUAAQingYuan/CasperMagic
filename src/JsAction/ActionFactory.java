package JsAction;

public class ActionFactory {
	
		public  static   ActionChain   CreateActionChain(ActionNode node)
		{
			ActionChain  chain=new ActionChain();
			return  chain.addActionNode(node);
		}
		
}
