package me.jezzagamermc.api.toodeepengine;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class TDEGameLoop extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;
	private Thread thread;
	private boolean running = false;
	
	private int fps;
	private int tps;
	
	private int width;
	private int height;
	
	public Graphics2D g2d;
	private BufferedImage img;
	
	public static double currFps = 120D;
	
	public TDEGameLoop(int width, int height)
	{
		this.width = width;
		this.height = height;
		
		setPreferredSize(new Dimension(width, height));
		setFocusable(false);
		requestFocus();
	}
	
	@Override
	public void addNotify()
	{
		super.addNotify();
		
		if(thread == null)
		{
			thread = new Thread(this);
			thread.start();
		}
	}

	@Override
	public void run()
	{
		
		/*INIT SECTION*/
		init();
		
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / currFps;
		int frames = 0;
		int ticks = 0;
		long lastTimer = System.currentTimeMillis();
		double deltaTime = 0;
		
		while(running)
		{
			long now = System.nanoTime();
			deltaTime += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = false;
			
			while(deltaTime >= 1)
			{
				ticks++;
				/*Calculations for TICK and DELTATIME*/
				tick(deltaTime);
				deltaTime -= 1;
				shouldRender = true;
			}
			if(shouldRender)
			{
				frames++;
				/*RENDER SECTION*/
				render();
			}
			
			try
			{
				Thread.sleep(2);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			if(System.currentTimeMillis() - lastTimer >= 1000)
			{
				lastTimer += 1000;
				tps = ticks;
				fps = frames;
				frames = 0;
				ticks = 0;
			}
		}
		
	}
	
	public void init()
	{
		img = new BufferedImage (width, height, BufferedImage.TYPE_INT_RGB);
		g2d = (Graphics2D) img.getGraphics();
		running = true;
		
	}
	
	public void tick(double deltaTime)
	{
		
	}

	public void render()
	{
		g2d.clearRect(0, 0, width, height);
		
	}
	
	public void clear()
	{
		Graphics g2 = getGraphics();
		if(img != null)
		{
			g2.drawImage(img, 0, 0, null);
			g2.dispose();
		}
	}
	
	public int getFps()
	{
		return fps;
	}
	
	public int getTps()
	{
		return tps;
	}
}
