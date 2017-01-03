package csheets.persistence;

/**
 * @author Paulo Gandra Sousa
 *
 */
public interface RepositoryFactory {

	ContactRepository contacts();

	EventRepository events();

	ReminderRepository reminders();

	CalendarRepository calendars();

	NoteRepository notes();

	ListRepository lists();

	TaskRepository task();

	ChatUserRepository chatUsers();

	EmailRepository emails();

	RoomRepository rooms();

	UserRepository users();

	MessageRepository messages();

	AddressRepository addresses();
}
