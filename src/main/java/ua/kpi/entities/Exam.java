package ua.kpi.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor(staticName = "of")
@Getter
@EqualsAndHashCode
public class Exam {
    public enum Type{
        ENGLISH, MATH
    }
    private Type type;
    private double score;
}
