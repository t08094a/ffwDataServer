/**
 * 
 */
package com.leif.ffDataServer.models;

/**
 * The status of a member of the fire fighting association. (de: Mitgliedsstatus im Verein)
 * @author leif
 *
 */
public enum MemberStatus
{
	/**
	 * This person is also an active fire fighter.
	 */
	Active,
	
	/**
	 * This person was an active fire fighter.
	 */
	Passive,
	
	/**
	 * This person is only supporting the association by money. 
	 */
	Supporting
}
