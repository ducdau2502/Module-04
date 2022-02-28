package dd.service;

import dd.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IStudentService {
    Page<Student> findAll(Pageable pageable);

    Page<Student> findAllByNameContainingOrPhoneNumberContaining(Pageable pageable, String search, String search1);

    Optional<Student> findStudentById(long id);

    Student save(Student student);

    void delete(long id);
}
