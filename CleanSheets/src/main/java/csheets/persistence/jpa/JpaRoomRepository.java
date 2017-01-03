/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.persistence.jpa;

import csheets.ext.chat.domain.Room;
import csheets.ext.chat.domain.User;
import csheets.framework.persistence.repositories.impl.jpa.JpaRepository;
import csheets.persistence.RoomRepository;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcelo Barroso 1131399
 */
public class JpaRoomRepository extends JpaRepository<Room, Long> implements RoomRepository {

	@Override
	protected String persistenceUnitName() {
		return PersistenceSettings.PERSISTENCE_UNIT_NAME;
	}

	@Override
	public Iterable<Room> rooms(User user) {
		List<Room> rooms = new ArrayList();
		for (Room room : this.all()) {
			if (room.creator().equals(user)) {
				rooms.add(room);
			}
		}
		return rooms;
	}

	@Override
	public Room findName(String name) {
		for (Room room : this.all()) {
			if (room.name().equals(name)) {
				return room;
			}
		}
		return null;
	}

}
