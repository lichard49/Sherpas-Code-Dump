package com.sherpas.wheresmystuff.command;

public interface ICommand
{
	boolean execute();
	boolean undo();
}
