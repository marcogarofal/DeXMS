package org.zefxis.dexms.dex.protocols.dpws;

public class DpwsEventProvider extends Thread {
	
	
	private int eventCounter = 0;
	private DpwsEvent dpwsEvent = null;
	
	public DpwsEventProvider(DpwsEvent dpwsEvent){
		
		
		this.dpwsEvent = dpwsEvent;
	}
	
	@Override
	public void run(){
		
		
		while(true){
				
			try {
				
				//Thread.sleep(5000);
				//System.err.println("  begin fire Event ");
				dpwsEvent.fireHelloWorldSimpleEvent(eventCounter++);
				//System.err.println("  end fire Event ");
			}
			catch (Exception e) {e.printStackTrace();}
		}
	}
}
