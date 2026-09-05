package rs.fizika.inicijalnitest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

import java.util.*;

public class MainActivity extends Activity {

    private static final int GREEN_DARK = Color.rgb(27, 94, 62);
    private static final int GREEN = Color.rgb(46, 125, 50);
    private static final int GREEN_MID = Color.rgb(56, 142, 60);
    private static final int GREEN_PALE = Color.rgb(246, 250, 247);
    private static final int TEXT = Color.rgb(31, 41, 35);
    private static final int MUTED = Color.rgb(92, 108, 98);

    private final Handler timerHandler = new Handler();
    private long startedAt = 0L;
    private boolean timerRunning = false;

    private List<Question> questions = new ArrayList<>();
    private int index = 0;
    private int score = 0;
    private boolean checked = false;
    private final Map<String, Integer> errorsByArea = new LinkedHashMap<>();

    private LinearLayout content;
    private TextView progressText, areaText, questionText, scoreText, feedbackText, timerText;
    private ProgressBar progressBar;
    private RadioGroup radioGroup;
    private Button checkButton, nextButton;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        getWindow().setStatusBarColor(GREEN_DARK);
        getWindow().setNavigationBarColor(GREEN_PALE);
        showStartScreen();
    }

    private int dp(int value) {
        return (int) (value * getResources().getDisplayMetrics().density + 0.5f);
    }

    private GradientDrawable rounded(int fill, int stroke, float radius, int strokeWidth) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(fill);
        d.setCornerRadius(dp((int) radius));
        if (strokeWidth > 0) d.setStroke(dp(strokeWidth), stroke);
        return d;
    }

    private TextView txt(String value, int sp, int color) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(sp);
        t.setTextColor(color);
        t.setLineSpacing(0, 1.12f);
        return t;
    }

    private TextView centerTxt(String value, int sp, int color) {
        TextView t = txt(value, sp, color);
        t.setGravity(Gravity.CENTER);
        return t;
    }

    private Button greenButton(String label) {
        Button b = new Button(this);
        b.setText(label);
        b.setTextSize(16);
        b.setTextColor(Color.WHITE);
        b.setAllCaps(false);
        b.setTypeface(null, Typeface.BOLD);
        b.setBackground(rounded(GREEN, GREEN, 14, 0));
        b.setPadding(dp(14), 0, dp(14), 0);
        return b;
    }

    private void showStartScreen() {
        stopTimer();

        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER_HORIZONTAL);
        root.setPadding(dp(22), dp(24), dp(22), dp(28));
        root.setBackgroundColor(GREEN_PALE);
        scroll.addView(root);

        ImageView hero = new ImageView(this);
        hero.setImageResource(R.drawable.hero_fizika1);
        hero.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        hero.setContentDescription("Ilustracija fizike");
        root.addView(hero, new LinearLayout.LayoutParams(dp(205), dp(205)));

        TextView title = centerTxt("Fizika 1", 31, GREEN_DARK);
        title.setTypeface(null, Typeface.BOLD);
        LinearLayout.LayoutParams tp = new LinearLayout.LayoutParams(-1, -2);
        tp.setMargins(0, dp(16), 0, 0);
        root.addView(title, tp);

        TextView sub = centerTxt("Inicijalni test", 21, GREEN_MID);
        sub.setTypeface(null, Typeface.BOLD);
        root.addView(sub);

        TextView intro = centerTxt("20 nasumično izabranih pitanja iz gradiva 7. i 8. razreda", 15, MUTED);
        LinearLayout.LayoutParams ip = new LinearLayout.LayoutParams(-1, -2);
        ip.setMargins(dp(8), dp(10), dp(8), dp(18));
        root.addView(intro, ip);

        Button start = greenButton("Počni test  →");
        start.setOnClickListener(v -> startTest());
        root.addView(start, new LinearLayout.LayoutParams(-1, dp(56)));

        LinearLayout quoteCard = new LinearLayout(this);
        quoteCard.setOrientation(LinearLayout.VERTICAL);
        quoteCard.setPadding(dp(18), dp(16), dp(18), dp(14));
        quoteCard.setBackground(rounded(Color.WHITE, Color.rgb(200, 230, 201), 18, 1));
        quoteCard.setElevation(dp(2));

        TextView quote = txt("„Mene čudnom snagom beskonačnost privlači i želim da dograbim celu vasionu…“", 15, TEXT);
        quote.setTypeface(Typeface.create(Typeface.SERIF, Typeface.ITALIC));
        quoteCard.addView(quote);

        TextView author = txt("Milutin Milanković", 13, GREEN_DARK);
        author.setTypeface(null, Typeface.BOLD);
        author.setGravity(Gravity.END);
        author.setPadding(0, dp(8), 0, 0);
        quoteCard.addView(author);

        LinearLayout.LayoutParams qp = new LinearLayout.LayoutParams(-1, -2);
        qp.setMargins(0, dp(18), 0, 0);
        root.addView(quoteCard, qp);

        TextView note = centerTxt("Srećno! Pažljivo pročitaj svako pitanje.", 13, MUTED);
        LinearLayout.LayoutParams np = new LinearLayout.LayoutParams(-1, -2);
        np.setMargins(0, dp(16), 0, 0);
        root.addView(note, np);

        setContentView(scroll);
    }

    private void startTest() {
        questions = TestBuilder.buildTest20();
        index = 0;
        score = 0;
        checked = false;
        errorsByArea.clear();
        startedAt = System.currentTimeMillis();
        timerRunning = true;
        buildTestUi();
        showQuestion();
        timerHandler.post(timerTick);
    }

    private void buildTestUi() {
        LinearLayout page = new LinearLayout(this);
        page.setOrientation(LinearLayout.VERTICAL);
        page.setBackgroundColor(GREEN_PALE);

        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);

        content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setPadding(dp(18), dp(18), dp(18), dp(24));
        scroll.addView(content);

        page.addView(scroll, new LinearLayout.LayoutParams(-1, 0, 1f));

        TextView title = txt("Fizika 1 – inicijalni test", 23, GREEN_DARK);
        title.setTypeface(null, Typeface.BOLD);
        content.addView(title);

        TextView subtitle = txt("Gradivo 7. i 8. razreda", 14, MUTED);
        subtitle.setPadding(0, dp(3), 0, dp(12));
        content.addView(subtitle);

        LinearLayout top = new LinearLayout(this);
        top.setOrientation(LinearLayout.HORIZONTAL);
        top.setGravity(Gravity.CENTER_VERTICAL);

        progressText = txt("", 14, MUTED);
        scoreText = txt("", 14, TEXT);
        scoreText.setTypeface(null, Typeface.BOLD);
        top.addView(progressText, new LinearLayout.LayoutParams(0, dp(36), 1f));
        top.addView(scoreText);
        content.addView(top);

        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
        progressBar.setMax(20);
        progressBar.setProgressTintList(android.content.res.ColorStateList.valueOf(GREEN));
        content.addView(progressBar, new LinearLayout.LayoutParams(-1, dp(10)));

        areaText = txt("", 14, GREEN_MID);
        areaText.setTypeface(null, Typeface.BOLD);
        areaText.setPadding(0, dp(17), 0, dp(6));
        content.addView(areaText);

        questionText = txt("", 20, TEXT);
        questionText.setTypeface(null, Typeface.BOLD);
        questionText.setPadding(0, 0, 0, dp(12));
        content.addView(questionText);

        radioGroup = new RadioGroup(this);
        radioGroup.setOrientation(RadioGroup.VERTICAL);
        content.addView(radioGroup);

        feedbackText = txt("", 15, TEXT);
        feedbackText.setPadding(dp(14), dp(12), dp(14), dp(12));
        feedbackText.setVisibility(View.GONE);
        LinearLayout.LayoutParams fbp = new LinearLayout.LayoutParams(-1, -2);
        fbp.setMargins(0, dp(14), 0, 0);
        content.addView(feedbackText, fbp);

        LinearLayout actions = new LinearLayout(this);
        actions.setOrientation(LinearLayout.HORIZONTAL);
        actions.setPadding(0, dp(16), 0, 0);

        checkButton = greenButton("Proveri odgovor");
        checkButton.setOnClickListener(v -> checkAnswer());
        actions.addView(checkButton, new LinearLayout.LayoutParams(0, dp(52), 1f));

        nextButton = new Button(this);
        nextButton.setText("Sledeće pitanje");
        nextButton.setTextSize(15);
        nextButton.setAllCaps(false);
        nextButton.setTextColor(GREEN_DARK);
        nextButton.setTypeface(null, Typeface.BOLD);
        nextButton.setBackground(rounded(Color.WHITE, Color.rgb(165, 214, 167), 14, 1));
        nextButton.setVisibility(View.GONE);
        nextButton.setOnClickListener(v -> nextQuestion());

        LinearLayout.LayoutParams nlp = new LinearLayout.LayoutParams(0, dp(52), 1f);
        nlp.setMargins(dp(10), 0, 0, 0);
        actions.addView(nextButton, nlp);
        content.addView(actions);

        LinearLayout timerBar = new LinearLayout(this);
        timerBar.setGravity(Gravity.CENTER);
        timerBar.setPadding(dp(12), dp(10), dp(12), dp(10));
        timerBar.setBackgroundColor(Color.WHITE);
        timerText = centerTxt("Vreme: 00:00", 15, GREEN_DARK);
        timerText.setTypeface(null, Typeface.BOLD);
        timerBar.addView(timerText);
        page.addView(timerBar, new LinearLayout.LayoutParams(-1, dp(48)));

        setContentView(page);
    }

    private RadioButton optionButton(String value, int id) {
        RadioButton rb = new RadioButton(this);
        rb.setId(id);
        rb.setText(value);
        rb.setTextSize(16);
        rb.setTextColor(TEXT);
        rb.setButtonTintList(android.content.res.ColorStateList.valueOf(GREEN));
        rb.setPadding(dp(8), dp(8), dp(8), dp(8));
        rb.setBackground(rounded(Color.WHITE, Color.rgb(220, 230, 223), 12, 1));
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, -2);
        lp.setMargins(0, dp(5), 0, dp(5));
        rb.setLayoutParams(lp);
        return rb;
    }

    private void showQuestion() {
        if (index >= questions.size()) {
            showResults();
            return;
        }

        checked = false;
        Question q = questions.get(index);

        progressText.setText("Pitanje " + (index + 1) + " od " + questions.size());
        scoreText.setText("Tačno: " + score);
        progressBar.setProgress(index + 1);

        String badge = q.calculation
                ? q.area + "  •  računski – " + ("OSNOVNI".equals(q.level) ? "osnovni nivo" : "srednji nivo")
                : q.area;
        areaText.setText(badge);
        questionText.setText(q.text);

        radioGroup.removeAllViews();
        for (int i = 0; i < q.answers.length; i++) {
            radioGroup.addView(optionButton(q.answers[i], 1000 + i));
        }

        feedbackText.setText("");
        feedbackText.setVisibility(View.GONE);
        checkButton.setEnabled(true);
        checkButton.setAlpha(1f);
        nextButton.setVisibility(View.GONE);
    }

    private void checkAnswer() {
        if (checked) return;
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if (selectedId == -1) {
            Toast.makeText(this, "Izaberi jedan odgovor.", Toast.LENGTH_SHORT).show();
            return;
        }

        checked = true;
        int chosen = selectedId - 1000;
        Question q = questions.get(index);
        boolean ok = chosen == q.correct;

        if (ok) {
            score++;
            feedbackText.setText("✓ Tačno!\n" + q.explanation);
            feedbackText.setBackground(rounded(Color.rgb(232, 245, 233), Color.rgb(129, 199, 132), 12, 1));
            feedbackText.setTextColor(Color.rgb(27, 94, 32));
        } else {
            Integer old = errorsByArea.get(q.area);
            errorsByArea.put(q.area, old == null ? 1 : old + 1);
            feedbackText.setText("✗ Netačno. Tačan odgovor: " + q.answers[q.correct] + "\n" + q.explanation);
            feedbackText.setBackground(rounded(Color.rgb(255, 243, 224), Color.rgb(255, 183, 77), 12, 1));
            feedbackText.setTextColor(Color.rgb(121, 85, 72));
        }

        feedbackText.setVisibility(View.VISIBLE);
        scoreText.setText("Tačno: " + score);
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            radioGroup.getChildAt(i).setEnabled(false);
        }
        checkButton.setEnabled(false);
        checkButton.setAlpha(0.55f);
        nextButton.setText(index == questions.size() - 1 ? "Rezultat" : "Sledeće pitanje");
        nextButton.setVisibility(View.VISIBLE);
    }

    private void nextQuestion() {
        if (!checked) return;
        index++;
        if (index >= questions.size()) showResults();
        else showQuestion();
    }

    private void showResults() {
        long elapsed = System.currentTimeMillis() - startedAt;
        stopTimer();

        int total = questions.size();
        int percent = total == 0 ? 0 : Math.round(100f * score / total);
        String label;
        if (percent >= 90) label = "Odlično!";
        else if (percent >= 75) label = "Vrlo dobro!";
        else if (percent >= 60) label = "Dobro!";
        else if (percent >= 45) label = "Solidno!";
        else label = "Pokušaj ponovo!";

        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER_HORIZONTAL);
        root.setPadding(dp(22), dp(30), dp(22), dp(30));
        root.setBackgroundColor(GREEN_PALE);
        scroll.addView(root);

        TextView done = centerTxt(label, 30, GREEN_DARK);
        done.setTypeface(null, Typeface.BOLD);
        root.addView(done);

        TextView pct = centerTxt(percent + "%", 48, GREEN);
        pct.setTypeface(null, Typeface.BOLD);
        LinearLayout.LayoutParams pp = new LinearLayout.LayoutParams(-1, -2);
        pp.setMargins(0, dp(10), 0, dp(2));
        root.addView(pct, pp);

        TextView scoreView = centerTxt(score + " / " + total + " tačnih odgovora", 18, TEXT);
        root.addView(scoreView);

        TextView timeView = centerTxt("Vreme: " + formatTime(elapsed), 15, MUTED);
        timeView.setPadding(0, dp(8), 0, dp(18));
        root.addView(timeView);

        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(dp(16), dp(14), dp(16), dp(14));
        card.setBackground(rounded(Color.WHITE, Color.rgb(200, 230, 201), 16, 1));

        TextView head = txt("Oblasti za dodatno ponavljanje", 16, GREEN_DARK);
        head.setTypeface(null, Typeface.BOLD);
        card.addView(head);

        TextView weak = txt(buildWeakAreas(), 14, TEXT);
        weak.setPadding(0, dp(8), 0, 0);
        card.addView(weak);
        root.addView(card, new LinearLayout.LayoutParams(-1, -2));

        Button again = greenButton("Uradi novi test");
        again.setOnClickListener(v -> startTest());
        LinearLayout.LayoutParams alp = new LinearLayout.LayoutParams(-1, dp(56));
        alp.setMargins(0, dp(18), 0, 0);
        root.addView(again, alp);

        Button home = new Button(this);
        home.setText("Početna");
        home.setAllCaps(false);
        home.setTextColor(GREEN_DARK);
        home.setBackground(rounded(Color.WHITE, Color.rgb(165, 214, 167), 14, 1));
        home.setOnClickListener(v -> showStartScreen());
        LinearLayout.LayoutParams hlp = new LinearLayout.LayoutParams(-1, dp(52));
        hlp.setMargins(0, dp(10), 0, 0);
        root.addView(home, hlp);

        setContentView(scroll);
    }

    private String buildWeakAreas() {
        if (errorsByArea.isEmpty()) return "Nema izdvojenih slabijih oblasti — svi odgovori su tačni.";
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(errorsByArea.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
            @Override public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                return b.getValue().compareTo(a.getValue());
            }
        });
        StringBuilder sb = new StringBuilder();
        int limit = Math.min(3, entries.size());
        for (int i = 0; i < limit; i++) {
            if (i > 0) sb.append("\n");
            sb.append("• ").append(entries.get(i).getKey())
              .append(" — ").append(entries.get(i).getValue()).append(" greš.");
        }
        return sb.toString();
    }

    private String formatTime(long millis) {
        long sec = Math.max(0, millis / 1000);
        long min = sec / 60;
        sec %= 60;
        return String.format(Locale.US, "%02d:%02d", min, sec);
    }

    private final Runnable timerTick = new Runnable() {
        @Override public void run() {
            if (!timerRunning) return;
            if (timerText != null) timerText.setText("Vreme: " + formatTime(System.currentTimeMillis() - startedAt));
            timerHandler.postDelayed(this, 1000);
        }
    };

    private void stopTimer() {
        timerRunning = false;
        timerHandler.removeCallbacks(timerTick);
    }

    @Override protected void onDestroy() {
        stopTimer();
        super.onDestroy();
    }
}
