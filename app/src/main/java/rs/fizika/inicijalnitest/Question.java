package rs.fizika.inicijalnitest;

public class Question {
    public final String area;
    public final String text;
    public final String[] answers;
    public final int correct;
    public final String explanation;
    public final boolean calculation;
    public final String level;

    public Question(String area, String text, String[] answers, int correct, String explanation) {
        this(area, text, answers, correct, explanation, false, "STANDARD");
    }

    public Question(String area, String text, String[] answers, int correct, String explanation,
                    boolean calculation, String level) {
        this.area = area;
        this.text = text;
        this.answers = answers;
        this.correct = correct;
        this.explanation = explanation;
        this.calculation = calculation;
        this.level = level;
    }
}
