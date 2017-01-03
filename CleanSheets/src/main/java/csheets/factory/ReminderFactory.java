/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.factory;

import csheets.domain.Reminder;
import java.util.Calendar;

/**
 *
 * @author Gabriel
 */
public class ReminderFactory {

	public ReminderFactory() {
	}

	static public Reminder createReminder(String name, String dsc, Calendar tStp,
										  boolean alert) {
		return new Reminder(name, dsc, tStp, alert);
	}
}
