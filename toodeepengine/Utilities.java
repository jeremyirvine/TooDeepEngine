package me.jezzagamermc.api.toodeepengine;


public class Utilities
{

	private static boolean debugMode = false;
	
	public Utilities()
	{
		// TODO Auto-generated constructor stub
	}
	
	public void setDebugMode(boolean debugMode)
	{
		this.debugMode = debugMode;
	}
	
	public static void OutputMessage(String str, MessageType importance, String className)
	{
		
		
		switch (importance)
		{
			case DEBUG:
				if(debugMode)
					System.out.println("[DD][Developer][Debug] " + "[" + className + "]: " + str);
				break;
			case ERROR:
				System.err.println("[DD][Error] " + "[" + className + "]: " + str);
				break;
			case INFO:
				System.out.println("[DD][Info] " + "[" + className + "]: " + str);
				break;
			case WARNING:
				System.out.println("[DD][Warn] " + "[" + className + "]: " + str);
				break;
			
		}
	}
	
	public enum MessageType
	{

		WARNING,
		ERROR,
		INFO,
		DEBUG
		
	}


}
