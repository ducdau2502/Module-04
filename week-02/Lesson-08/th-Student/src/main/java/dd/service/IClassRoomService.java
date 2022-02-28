package dd.service;

import dd.model.ClassRoom;
import java.util.Optional;

public interface IClassRoomService {
    Iterable<ClassRoom> findClassAll();

    Optional<ClassRoom> findClassRoomById(long id);
}
