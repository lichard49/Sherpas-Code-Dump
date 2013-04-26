package com.sherpas.wheresmystuff.command;

import java.util.Stack;


public class CommandProcessor
{
	private static CommandProcessor cp;
	
	private Stack<ICommand> undoStack;
	private Stack<ICommand> redoStack;
	
	protected CommandProcessor()
	{
		undoStack = new Stack<ICommand>();
		redoStack = new Stack<ICommand>();
	}
	
	public static CommandProcessor getInstance()
	{
		if(cp == null)
		{
			synchronized(CommandProcessor.class)
			{
				if(cp == null)
					cp = new CommandProcessor();
			}
		}
		return cp;
	}
	

	public void execute(ICommand cmd)
	{
		if (cmd.execute())
		{
		   undoStack.push(cmd);
		}
		else
		{
			undoStack.clear();
		}
		
	}
	
	public void undoLast()
	{
		if (undoStack.isEmpty()) return;
		ICommand cmd = undoStack.pop();
		cmd.undo();
		redoStack.push(cmd);
	}
	
	public void redoLast()
	{
		if (redoStack.isEmpty()) return;
		ICommand cmd = redoStack.pop();
		execute(cmd);
	}
}
