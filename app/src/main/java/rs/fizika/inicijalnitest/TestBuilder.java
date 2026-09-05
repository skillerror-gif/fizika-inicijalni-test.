package rs.fizika.inicijalnitest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class TestBuilder {
    private TestBuilder() {}

    public static List<Question> buildTest20() {
        List<Question> standard = new ArrayList<>(QuestionBank.all());
        Collections.shuffle(standard);

        List<Question> basic = new ArrayList<>();
        List<Question> medium = new ArrayList<>();
        for (Question q : CalculationBank.all()) {
            if (CalculationBank.OSNOVNI.equals(q.level)) basic.add(q);
            else if (CalculationBank.SREDNJI.equals(q.level)) medium.add(q);
        }
        Collections.shuffle(basic);
        Collections.shuffle(medium);

        ArrayList<Question> test = new ArrayList<>(20);
        test.addAll(standard.subList(0, 14));
        test.addAll(basic.subList(0, 3));
        test.addAll(medium.subList(0, 3));
        Collections.shuffle(test);
        return test;
    }
}
