package rs.fizika.inicijalnitest;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import java.util.*;

public class MainActivity extends Activity {

    static class Question {
        String area, text, explanation;
        String[] answers;
        int correct;
        Question(String area, String text, String[] answers, int correct, String explanation) {
            this.area = area; this.text = text; this.answers = answers;
            this.correct = correct; this.explanation = explanation;
        }
    }

    private final List<Question> questions = new ArrayList<>(Arrays.asList(
            new Question("Merenje i fizičke veličine", "Koja je osnovna SI jedinica za dužinu?", new String[]{"centimetar (cm)", "metar (m)", "kilometar (km)", "milimetar (mm)"}, 1, "Osnovna SI jedinica za dužinu je metar (m)."),
            new Question("Merenje i fizičke veličine", "Koja je SI jedinica za vreme?", new String[]{"minut", "čas", "sekunda", "dan"}, 2, "Osnovna SI jedinica za vreme je sekunda (s)."),
            new Question("Merenje i fizičke veličine", "Koliko je 2,5 km izraženo u metrima?", new String[]{"25 m", "250 m", "2500 m", "25 000 m"}, 2, "1 km = 1000 m, pa je 2,5 km = 2500 m."),
            new Question("Kretanje", "Automobil pređe 150 m za 10 s. Kolika je srednja brzina?", new String[]{"15 m/s", "1500 m/s", "0,067 m/s", "140 m/s"}, 0, "v = s/t = 150 m / 10 s = 15 m/s."),
            new Question("Kretanje", "Telo se kreće ravnomerno pravolinijski. Koja veličina je konstantna?", new String[]{"brzina", "pređeni put", "položaj", "ubrzanje različito od nule"}, 0, "Kod ravnomernog pravolinijskog kretanja brzina je konstantna."),
            new Question("Kretanje", "Biciklista se kreće brzinom 5 m/s tokom 20 s. Koliki put pređe?", new String[]{"4 m", "25 m", "100 m", "400 m"}, 2, "s = v·t = 5 m/s · 20 s = 100 m."),
            new Question("Kretanje", "Ako se brzina tela povećava za jednake iznose u jednakim vremenskim intervalima, kretanje je:", new String[]{"ravnomerno", "ravnomerno ubrzano", "kružno ravnomerno", "oscilatorno"}, 1, "To je karakteristika ravnomerno ubrzanog kretanja."),
            new Question("Sila", "Koja je SI jedinica za silu?", new String[]{"džul (J)", "vat (W)", "njutn (N)", "paskal (Pa)"}, 2, "Sila se meri u njutnima (N)."),
            new Question("Sila", "Ako na telo deluju dve jednake sile suprotnih smerova, rezultanta je:", new String[]{"dvostruko veća sila", "jednaka nuli", "jednaka jednoj sili", "uvek vertikalna"}, 1, "Jednake sile suprotnih smerova se poništavaju."),
            new Question("Sila", "Masa tela je 2 kg. Ako uzmemo g ≈ 10 N/kg, sila teže je:", new String[]{"0,2 N", "5 N", "12 N", "20 N"}, 3, "Fg = m·g = 2 kg · 10 N/kg = 20 N."),
            new Question("Sila", "Koja sila se suprotstavlja klizanju jednog tela po drugom?", new String[]{"sila teže", "sila trenja", "električna sila", "magnetna sila"}, 1, "Sila trenja deluje suprotno relativnom kretanju dodirnih površina."),
            new Question("Pritisak", "Pritisak se računa formulom:", new String[]{"p = F/S", "p = F·S", "p = S/F", "p = m·g"}, 0, "Pritisak je količnik normalne sile i površine: p = F/S."),
            new Question("Pritisak", "Kako se menja pritisak ako ista sila deluje na dva puta manju površinu?", new String[]{"smanji se dva puta", "ne menja se", "poveća se dva puta", "postaje nula"}, 2, "Pošto je p = F/S, prepolovljena površina daje dvostruki pritisak."),
            new Question("Rad, energija i snaga", "Mehanički rad sile koja deluje u smeru pomeranja računa se kao:", new String[]{"A = F/s", "A = F·s", "A = m·g", "A = P/t"}, 1, "Kada su sila i pomeranje istog pravca i smera, A = F·s."),
            new Question("Rad, energija i snaga", "Koju energiju ima podignuto telo zbog svog položaja?", new String[]{"kinetičku", "električnu", "gravitacionu potencijalnu", "unutrašnju"}, 2, "Podignuto telo ima gravitacionu potencijalnu energiju."),
            new Question("Rad, energija i snaga", "Koju energiju ima telo koje se kreće?", new String[]{"kinetičku", "potencijalnu samo", "hemijsku", "nema energiju"}, 0, "Kretanje je povezano sa kinetičkom energijom."),
            new Question("Rad, energija i snaga", "Snaga predstavlja:", new String[]{"rad izvršen u jedinici vremena", "silu po jedinici površine", "put u jedinici vremena", "masu u jedinici zapremine"}, 0, "P = A/t, pa snaga pokazuje koliko se rada izvrši u jedinici vremena."),
            new Question("Rad, energija i snaga", "Koja je SI jedinica za rad i energiju?", new String[]{"njutn", "vat", "džul", "paskal"}, 2, "Rad i energija mere se u džulima (J)."),
            new Question("Toplota", "Pri zagrevanju većine čvrstih tela njihove dimenzije se:", new String[]{"povećavaju", "smanjuju", "ne menjaju", "uvek prepolove"}, 0, "Većina tela se pri zagrevanju toplotno širi."),
            new Question("Toplota", "Prenošenje toplote kroz čvrsto telo bez kretanja materije naziva se:", new String[]{"konvekcija", "zračenje", "provođenje", "isparavanje"}, 2, "Provođenje je prenos toplote kroz materijal bez makroskopskog prenosa materije."),
            new Question("Toplota", "Temperatura je mera:", new String[]{"količine materije", "stepena zagrejanosti tela", "mase tela", "električnog naboja"}, 1, "Temperatura opisuje stepen zagrejanosti tela."),
            new Question("Elektricitet", "Koja je SI jedinica za jačinu električne struje?", new String[]{"volt", "amper", "om", "kulon"}, 1, "Jačina električne struje meri se amperima (A)."),
            new Question("Elektricitet", "Za provodnik važi U = 12 V i I = 3 A. Koliki je otpor?", new String[]{"4 Ω", "9 Ω", "15 Ω", "36 Ω"}, 0, "R = U/I = 12 V / 3 A = 4 Ω."),
            new Question("Elektricitet", "Koja je SI jedinica za električni napon?", new String[]{"volt", "amper", "vat", "om"}, 0, "Električni napon meri se voltima (V)."),
            new Question("Elektricitet", "U rednoj vezi električnih potrošača jačina struje je:", new String[]{"ista kroz sve potrošače", "uvek nula", "najveća na prvom potrošaču", "različita bez pravila"}, 0, "Kod redne veze ista struja prolazi kroz sve elemente kola."),
            new Question("Magnetizam", "Koji polovi magneta se privlače?", new String[]{"dva severna", "dva južna", "raznoimeni", "nijedni"}, 2, "Raznoimeni polovi se privlače, a istoimeni odbijaju."),
            new Question("Optika", "Kod ravnog ogledala lik predmeta je:", new String[]{"realan i obrnut", "virtuelan, uspravan i jednake veličine", "uvek umanjen", "uvek uvećan"}, 1, "Ravno ogledalo daje virtuelan, uspravan lik jednake veličine."),
            new Question("Optika", "Pri prelasku svetlosti iz jednog optičkog sredstva u drugo može doći do:", new String[]{"prelamanja", "nestanka mase", "povećanja gravitacije", "stvaranja zvuka"}, 0, "Promena brzine svetlosti na granici sredstava dovodi do prelamanja."),
            new Question("Talasi i zvuk", "Od čega prvenstveno zavisi visina tona?", new String[]{"od amplitude", "od frekvencije", "od mase slušaoca", "od temperature tela koje sluša"}, 1, "Veća frekvencija odgovara višem tonu."),
            new Question("Talasi i zvuk", "Zvuk se kroz vakuum:", new String[]{"širi veoma brzo", "širi sporije nego kroz vazduh", "ne može širiti", "širi samo noću"}, 2, "Zvuku je za prostiranje potrebna materijalna sredina.")
    ));

    private LinearLayout root, resultBox;
    private TextView progressText, areaText, questionText, scoreText, feedbackText, resultText, weakText;
    private ProgressBar progressBar;
    private RadioGroup radioGroup;
    private Button checkButton, nextButton, restartButton;
    private int index = 0, score = 0;
    private boolean checked = false;
    private final Map<String,Integer> errorsByArea = new HashMap<>();

    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        Collections.shuffle(questions);
        buildUi();
        showQuestion();
    }

    private int dp(int v) {
        return (int)(v * getResources().getDisplayMetrics().density + 0.5f);
    }

    private GradientDrawable bg(int color, float radiusDp) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(dp((int)radiusDp));
        return d;
    }

    private TextView text(String value, int sp, int color) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(sp);
        t.setTextColor(color);
        t.setLineSpacing(0,1.12f);
        return t;
    }

    private void buildUi() {
        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);

        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(18),dp(18),dp(18),dp(28));
        root.setBackgroundColor(Color.rgb(248,250,249));
        scroll.addView(root);

        TextView title = text("Fizika – inicijalni test", 26, Color.rgb(32,92,67));
        title.setTypeface(null, Typeface.BOLD);
        root.addView(title);

        TextView subtitle = text("Ponavljanje gradiva osnovne škole", 15, Color.rgb(94,107,101));
        subtitle.setPadding(0,dp(4),0,dp(16));
        root.addView(subtitle);

        LinearLayout topRow = new LinearLayout(this);
        topRow.setOrientation(LinearLayout.HORIZONTAL);
        topRow.setGravity(Gravity.CENTER_VERTICAL);

        progressText = text("",14,Color.rgb(94,107,101));
        scoreText = text("",14,Color.rgb(30,42,36));
        scoreText.setTypeface(null,Typeface.BOLD);

        topRow.addView(progressText,new LinearLayout.LayoutParams(0,dp(40),1));
        topRow.addView(scoreText);
        root.addView(topRow);

        progressBar = new ProgressBar(this,null,android.R.attr.progressBarStyleHorizontal);
        progressBar.setMax(questions.size());
        root.addView(progressBar,new LinearLayout.LayoutParams(-1,dp(12)));

        areaText = text("",15,Color.rgb(46,125,90));
        areaText.setTypeface(null,Typeface.BOLD);
        areaText.setPadding(0,dp(18),0,dp(6));
        root.addView(areaText);

        questionText = text("",20,Color.rgb(30,42,36));
        questionText.setTypeface(null,Typeface.BOLD);
        questionText.setPadding(0,0,0,dp(14));
        root.addView(questionText);

        radioGroup = new RadioGroup(this);
        radioGroup.setOrientation(RadioGroup.VERTICAL);
        root.addView(radioGroup,new LinearLayout.LayoutParams(-1,-2));

        feedbackText = text("",15,Color.rgb(30,42,36));
        feedbackText.setPadding(dp(14),dp(12),dp(14),dp(12));
        feedbackText.setVisibility(View.GONE);

        LinearLayout.LayoutParams fbp = new LinearLayout.LayoutParams(-1,-2);
        fbp.setMargins(0,dp(14),0,0);
        root.addView(feedbackText,fbp);

        LinearLayout actions = new LinearLayout(this);
        actions.setOrientation(LinearLayout.HORIZONTAL);
        actions.setPadding(0,dp(16),0,0);

        checkButton = new Button(this);
        checkButton.setText("Proveri odgovor");
        checkButton.setTextColor(Color.WHITE);
        checkButton.setBackground(bg(Color.rgb(46,125,90),10));
        checkButton.setOnClickListener(v -> checkAnswer());
        actions.addView(checkButton,new LinearLayout.LayoutParams(0,dp(52),1));

        nextButton = new Button(this);
        nextButton.setText("Sledeće pitanje");
        nextButton.setVisibility(View.GONE);
        nextButton.setOnClickListener(v -> nextQuestion());

        LinearLayout.LayoutParams np = new LinearLayout.LayoutParams(0,dp(52),1);
        np.setMargins(dp(10),0,0,0);
        actions.addView(nextButton,np);
        root.addView(actions);

        resultBox = new LinearLayout(this);
        resultBox.setOrientation(LinearLayout.VERTICAL);
        resultBox.setPadding(dp(18),dp(18),dp(18),dp(18));
        resultBox.setBackground(bg(Color.WHITE,14));
        resultBox.setVisibility(View.GONE);

        resultText = text("",24,Color.rgb(32,92,67));
        resultText.setTypeface(null,Typeface.BOLD);
        resultBox.addView(resultText);

        weakText = text("",15,Color.rgb(30,42,36));
        weakText.setPadding(0,dp(12),0,dp(16));
        resultBox.addView(weakText);

        restartButton = new Button(this);
        restartButton.setText("Ponovi kviz");
        restartButton.setTextColor(Color.WHITE);
        restartButton.setBackground(bg(Color.rgb(46,125,90),10));
        restartButton.setOnClickListener(v -> restart());
        resultBox.addView(restartButton,new LinearLayout.LayoutParams(-1,dp(52)));

        LinearLayout.LayoutParams rp = new LinearLayout.LayoutParams(-1,-2);
        rp.setMargins(0,dp(18),0,0);
        root.addView(resultBox,rp);

        setContentView(scroll);
    }

    private void showQuestion() {
        checked = false;

        Question q = questions.get(index);
        progressText.setText("Pitanje " + (index+1) + " od " + questions.size());
        scoreText.setText(score + " bodova");
        progressBar.setProgress(index+1);
        areaText.setText(q.area);
        questionText.setText(q.text);
        radioGroup.removeAllViews();

        for (int i=0;i<q.answers.length;i++) {
            RadioButton rb = new RadioButton(this);
            rb.setId(1000+i);
            rb.setText(q.answers[i]);
            rb.setTextSize(17);
            rb.setTextColor(Color.rgb(30,42,36));
            rb.setPadding(dp(8),dp(8),dp(8),dp(8));

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1,-2);
            lp.setMargins(0,0,0,dp(6));
            radioGroup.addView(rb,lp);
        }

        feedbackText.setVisibility(View.GONE);
        checkButton.setVisibility(View.VISIBLE);
        nextButton.setVisibility(View.GONE);
    }

    private void checkAnswer() {
        if (checked) return;

        int id = radioGroup.getCheckedRadioButtonId();

        if (id == -1) {
            feedbackText.setText("Izaberi jedan odgovor.");
            feedbackText.setBackground(bg(Color.rgb(255,248,225),10));
            feedbackText.setVisibility(View.VISIBLE);
            return;
        }

        checked = true;
        int chosen = id - 1000;
        Question q = questions.get(index);
        boolean ok = chosen == q.correct;

        if (ok) {
            score++;
            feedbackText.setText("Tačno!\n\n" + q.explanation);
            feedbackText.setBackground(bg(Color.rgb(223,244,232),10));
        } else {
            errorsByArea.put(q.area, errorsByArea.getOrDefault(q.area,0)+1);
            feedbackText.setText(
                    "Nije tačno. Tačan odgovor: "
                    + q.answers[q.correct]
                    + "\n\n"
                    + q.explanation
            );
            feedbackText.setBackground(bg(Color.rgb(252,229,229),10));
        }

        scoreText.setText(score + " bodova");
        feedbackText.setVisibility(View.VISIBLE);

        for (int i=0;i<radioGroup.getChildCount();i++) {
            radioGroup.getChildAt(i).setEnabled(false);
        }

        checkButton.setVisibility(View.GONE);
        nextButton.setText(
                index == questions.size()-1
                        ? "Prikaži rezultat"
                        : "Sledeće pitanje"
        );
        nextButton.setVisibility(View.VISIBLE);
    }

    private void nextQuestion() {
        if (index < questions.size()-1) {
            index++;
            showQuestion();
        } else {
            showResult();
        }
    }

    private void showResult() {
        areaText.setVisibility(View.GONE);
        questionText.setVisibility(View.GONE);
        radioGroup.setVisibility(View.GONE);
        feedbackText.setVisibility(View.GONE);
        checkButton.setVisibility(View.GONE);
        nextButton.setVisibility(View.GONE);

        int percent = (int)Math.round(score * 100.0 / questions.size());

        resultText.setText(
                "Uspešnost: " + percent + "%\n"
                + score + "/" + questions.size()
                + " tačnih odgovora"
        );

        if (errorsByArea.isEmpty()) {
            weakText.setText("Odlično! Sva pitanja su tačno rešena.");
        } else {
            List<Map.Entry<String,Integer>> list =
                    new ArrayList<>(errorsByArea.entrySet());

            list.sort((a,b) -> b.getValue()-a.getValue());

            StringBuilder sb =
                    new StringBuilder("Oblasti za dodatno ponavljanje:\n");

            for (Map.Entry<String,Integer> e : list) {
                sb.append("• ")
                  .append(e.getKey())
                  .append(" — ")
                  .append(e.getValue())
                  .append(" grešaka\n");
            }

            weakText.setText(sb.toString().trim());
        }

        resultBox.setVisibility(View.VISIBLE);
    }

    private void restart() {
        Collections.shuffle(questions);
        index = 0;
        score = 0;
        checked = false;
        errorsByArea.clear();

        areaText.setVisibility(View.VISIBLE);
        questionText.setVisibility(View.VISIBLE);
        radioGroup.setVisibility(View.VISIBLE);
        resultBox.setVisibility(View.GONE);

        showQuestion();
    }
          }
