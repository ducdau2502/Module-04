package dd.repository;

import dd.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepository extends PagingAndSortingRepository<Student, Long> {
    Page<Student> findAllByNameContainingOrPhoneNumberContaining(Pageable pageable, String search, String search1);
}
