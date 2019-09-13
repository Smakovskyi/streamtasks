package ua.kpi.entities;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Student {
    private String name;
    private double rating;
    private List<Exam> exams = new ArrayList<>();
}
