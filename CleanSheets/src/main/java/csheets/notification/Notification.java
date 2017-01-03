/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.notification;

/**
 *
 * @author Marcelo Barroso 1131399
 */
public class Notification {

	private static Notifier contactInformer = new Notifier() {
	};
	private static Notifier eventInformer = new Notifier() {
	};
	private static Notifier reminderInformer = new Notifier() {
	};
	private static Notifier extensionInformer = new Notifier() {
	};
	private static Notifier chatMessageInformer = new Notifier() {
	};
	private static Notifier panelMessageInformer = new Notifier() {
	};
	private static Notifier calendarInformer = new Notifier() {
	};
	private static Notifier noteInformer = new Notifier() {
	};
	private static Notifier formInformer = new Notifier() {
	};
	private static Notifier gamerInformer = new Notifier() {
	};
	private static Notifier cellInformer = new Notifier() {
	};
	private static Notifier commentInformer = new Notifier() {
	};
	private static Notifier taskInformer = new Notifier() {
	};
	private static Notifier linkFileInformer = new Notifier() {
	};
	private static Notifier emailInformer = new Notifier() {
	};
	private static Notifier filesInformer = new Notifier() {
	};
	private static Notifier exploreInformer = new Notifier() {
	};
	private static Notifier addressInformer = new Notifier() {
	};
	private static Notifier tablesInformer = new Notifier() {
	};

	public static Notifier contactInformer() {
		return Notification.contactInformer;
	}

	public static Notifier eventInformer() {
		return Notification.eventInformer;
	}

	public static Notifier reminderInformer() {
		return Notification.reminderInformer;
	}

	public static Notifier extensionInformer() {
		return Notification.extensionInformer;
	}

	public static Notifier chatMessageInformer() {
		return Notification.chatMessageInformer;
	}

	public static Notifier panelMessageInformer() {
		return Notification.panelMessageInformer;
	}

	public static Notifier calendarInformer() {
		return Notification.calendarInformer;
	}

	public static Notifier noteInformer() {
		return Notification.noteInformer;
	}

	public static Notifier formInformer() {
		return Notification.formInformer;
	}

	public static Notifier gameInformer() {
		return Notification.gamerInformer;
	}

	public static Notifier cellInformer() {
		return Notification.cellInformer;
	}

	public static Notifier commentInformer() {
		return Notification.commentInformer;
	}

	public static Notifier taskInformer() {
		return Notification.taskInformer;
	}

	public static Notifier linkFileInformer() {
		return Notification.linkFileInformer;
	}

	public static Notifier emailInformer() {
		return Notification.emailInformer;
	}

	public static Notifier filesInformer() {
		return Notification.filesInformer;
	}

	public static Notifier exploreInformer() {
		return Notification.exploreInformer;
	}

	public static Notifier addressInformer() {
		return Notification.addressInformer;
	}

	public static Notifier tablesInformer() {
		return Notification.tablesInformer;
	}
}
