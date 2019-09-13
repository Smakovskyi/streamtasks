package ua.kpi.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import ua.kpi.entities.Exam;
import ua.kpi.entities.Exam.Type;
import ua.kpi.entities.Student;
import ua.kpi.repositories.StudentRepository;

public class StudentService {

  private StudentRepository studentRepository;

  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }


  public Optional<Student> findWithMaxExam(Type type) {
    List<Student> students = studentRepository.findAll();
    OptionalDouble maxExam = students.stream()
        .flatMap(student -> student.getExams().stream())
        .filter(exam -> exam.getType() == type)
        .mapToDouble(Exam::getScore)
        .max();

    if(!maxExam.isPresent())
      return Optional.empty();

    Exam exam = Exam.of(type,maxExam.getAsDouble());
    return students.stream()
            .filter(student -> student.getExams().contains(exam))
            .findFirst();
  }

  public List<Student> findWithEnoughExam(Type examType, double passRate) {
    return studentRepository.findAll()
        .stream()
        .filter( student -> student.getExams().stream()
                                  .filter( exam -> exam.getType() == examType &&
                                                   exam.getScore() >= passRate )
                                  .findAny().isPresent() )
        .collect(Collectors.toList());
  }
}
