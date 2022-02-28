package dd.service.impl;

import dd.model.Student;
import dd.repository.IStudentRepository;
import dd.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    IStudentRepository studentRepository;

    @Override
    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Page<Student> findAllByNameContainingOrPhoneNumberContaining(Pageable pageable, String search) {
        return studentRepository.findAllByNameContainingOrPhoneNumberContaining(pageable, search, search);
    }

    @Override
    public Optional<Student> findStudentById(long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student save(Student student) {
       return studentRepository.save(student);
    }

    @Override
    public void delete(long id) {
        studentRepository.deleteById(id);
    }
}
