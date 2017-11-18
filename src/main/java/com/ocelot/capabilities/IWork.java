package com.ocelot.capabilities;

/**
 * This is implemented for any machines created
 * 
 * @author Ocelot5836
 */
public interface IWork {

	/**
	 * @return the cooldown for the work
	 */
	int getWorkDone();

	/**
	 * @return the maximum cooldown or the max amount of work
	 */
	int getMaxWork();

	/**
	 * Called every tick to do work.
	 */
	void doWork();

	/**
	 * Called when the work is done.
	 */
	void workDone();

}