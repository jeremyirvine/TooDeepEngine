package me.jezzagamermc.api.toodeepengine;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

public class GameWindow extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	boolean fse = false;
	int fsm = 0;
	GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
	private static String ENGINE_VERSION = "1.4";
	private static String ENGINE_VERSION_REVISION = "1";

	public GameWindow(int width, int height, String title)
	{
		setTitle(title + " | TooDeepEngine rev" + ENGINE_VERSION_REVISION + " v" + ENGINE_VERSION);
		setSize(width, height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	private void setfullscreen()
	{
		switch (fsm)
		{
			case 0:
				System.out.println("No Fullscreen");
				setUndecorated(false);
				break;
			case 1:
				setExtendedState(JFrame.MAXIMIZED_BOTH);
				setUndecorated(true);
				break;
			case 2:
				setUndecorated(true);
				device.setFullScreenWindow(this);
				break;
		}
	}
	
	public void setFullscreen(int fsnm)
	{
		fse = true;
		if(fsm <= 2)
		{
			this.fsm = fsnm;
			setfullscreen();
		} else if (fsm > 3)
		{
			System.err.println("[TooDeepEngine] ERROR: Unsupported: " + fsnm + " [me.jezzagamermc.api.toodeepengine.setFullscreen:50-55]");
			System.exit(0);
		}
	}
	
	public static String getENGINE_VERSION()
	{
		return ENGINE_VERSION;
	}
	
	public static String getENGINE_VERSION_REVISION()
	{
		return ENGINE_VERSION_REVISION;
	}
	
}
