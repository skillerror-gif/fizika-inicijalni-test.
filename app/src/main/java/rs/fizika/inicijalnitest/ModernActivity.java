package rs.fizika.inicijalnitest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

import java.util.*;

public class ModernActivity extends Activity {
    private static final int GREEN_DARK = Color.rgb(18,78,54);
    private static final int GREEN = Color.rgb(35,128,82);
    private static final int GREEN_MID = Color.rgb(54,155,103);
    private static final int GREEN_PALE = Color.rgb(245,250,247);
    private static final int GOLD = Color.rgb(210,164,64);
    private static final int TEXT = Color.rgb(28,38,33);
    private static final int MUTED = Color.rgb(88,104,95);
    private static final int LINE = Color.rgb(211,228,217);
    private static final String PREFS = "fizika1_stats";

    private final Handler timerHandler = new Handler();
    private long startedAt = 0L;
    private boolean timerRunning = false;
    private List<Question> questions = new ArrayList<>();
    private int index = 0, score = 0;
    private boolean checked = false;
    private final Map<String,Integer> errorsByArea = new LinkedHashMap<>();
    private LinearLayout content;
    private TextView progressText, areaText, questionText, scoreText, feedbackText, timerText;
    private ProgressBar progressBar;
    private RadioGroup radioGroup;
    private Button checkButton, nextButton;

    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        getWindow().setStatusBarColor(GREEN_DARK);
        getWindow().setNavigationBarColor(GREEN_PALE);
        showStartScreen();
    }

    private int dp(int v){return (int)(v*getResources().getDisplayMetrics().density+0.5f);}
    private GradientDrawable rounded(int fill,int stroke,float radius,int sw){
        GradientDrawable d=new GradientDrawable(); d.setColor(fill); d.setCornerRadius(dp((int)radius));
        if(sw>0)d.setStroke(dp(sw),stroke); return d;
    }
    private TextView txt(String s,int sp,int c){TextView t=new TextView(this);t.setText(s);t.setTextSize(sp);t.setTextColor(c);t.setLineSpacing(0,1.12f);return t;}
    private TextView centerTxt(String s,int sp,int c){TextView t=txt(s,sp,c);t.setGravity(Gravity.CENTER);return t;}
    private Button greenButton(String s){Button b=new Button(this);b.setText(s);b.setTextSize(16);b.setTextColor(Color.WHITE);b.setAllCaps(false);b.setTypeface(null,Typeface.BOLD);b.setBackground(rounded(GREEN,GREEN,16,0));return b;}
    private LinearLayout card(){LinearLayout c=new LinearLayout(this);c.setOrientation(LinearLayout.VERTICAL);c.setPadding(dp(16),dp(14),dp(16),dp(14));c.setBackground(rounded(Color.WHITE,LINE,18,1));c.setElevation(dp(2));return c;}
    private void gap(LinearLayout root,int h){Space s=new Space(this);root.addView(s,new LinearLayout.LayoutParams(1,dp(h)));}

    private void showStartScreen(){
        stopTimer();
        ScrollView scroll=new ScrollView(this);scroll.setFillViewport(true);
        LinearLayout root=new LinearLayout(this);root.setOrientation(LinearLayout.VERTICAL);root.setGravity(Gravity.CENTER_HORIZONTAL);root.setPadding(dp(20),dp(18),dp(20),dp(28));root.setBackgroundColor(GREEN_PALE);scroll.addView(root);

        LinearLayout school=card();school.setGravity(Gravity.CENTER_HORIZONTAL);
        TextView monogram=centerTxt("GI",20,Color.WHITE);monogram.setTypeface(null,Typeface.BOLD);monogram.setGravity(Gravity.CENTER);monogram.setBackground(rounded(GREEN_DARK,GOLD,14,2));school.addView(monogram,new LinearLayout.LayoutParams(dp(52),dp(52)));
        TextView schoolName=centerTxt("GIMNAZIJA INĐIJA",16,GREEN_DARK);schoolName.setTypeface(null,Typeface.BOLD);schoolName.setLetterSpacing(0.08f);schoolName.setPadding(0,dp(8),0,0);school.addView(schoolName);
        TextView schoolSub=centerTxt("ZNANJE  •  VREDNOSTI  •  BUDUĆNOST",10,MUTED);schoolSub.setLetterSpacing(0.05f);school.addView(schoolSub);
        root.addView(school,new LinearLayout.LayoutParams(-1,-2));

        gap(root,14);
        LinearLayout hero=card();hero.setPadding(dp(18),dp(18),dp(18),dp(18));
        TextView theme=centerTxt("⚙  MEHANIKA   ◉  ELEKTRICITET   ◇  OPTIKA",12,GREEN_MID);theme.setTypeface(null,Typeface.BOLD);hero.addView(theme);
        TextView title=centerTxt("FIZIKA 1",34,GREEN_DARK);title.setTypeface(null,Typeface.BOLD);title.setPadding(0,dp(12),0,0);hero.addView(title);
        TextView sub=centerTxt("Inicijalni test",20,GREEN_MID);sub.setTypeface(null,Typeface.BOLD);hero.addView(sub);
        TextView intro=centerTxt("20 nasumično izabranih pitanja\niz gradiva 7. i 8. razreda",15,MUTED);intro.setPadding(dp(6),dp(10),dp(6),dp(10));hero.addView(intro);
        TextView orbit=centerTxt("↗  F = m·a        λ = v/f        U = R·I  ↘",16,GREEN_DARK);orbit.setTypeface(Typeface.create(Typeface.SERIF,Typeface.ITALIC));orbit.setPadding(0,dp(4),0,0);hero.addView(orbit);
        root.addView(hero,new LinearLayout.LayoutParams(-1,-2));

        gap(root,14);
        Button start=greenButton("ZAPOČNI TEST   →");start.setOnClickListener(v->startTest());root.addView(start,new LinearLayout.LayoutParams(-1,dp(58)));

        gap(root,14);
        LinearLayout quote=card();
        TextView qhead=txt("MILUTIN MILANKOVIĆ",12,GREEN_MID);qhead.setTypeface(null,Typeface.BOLD);qhead.setLetterSpacing(0.08f);quote.addView(qhead);
        TextView q=txt("„Mene čudnom snagom beskonačnost privlači i želim da dograbim celu vasionu…“",15,TEXT);q.setTypeface(Typeface.create(Typeface.SERIF,Typeface.ITALIC));q.setPadding(0,dp(8),0,0);quote.addView(q);
        TextView qnote=txt("Naučnik, matematičar, astronom i geofizičar",12,MUTED);qnote.setPadding(0,dp(8),0,0);quote.addView(qnote);
        root.addView(quote,new LinearLayout.LayoutParams(-1,-2));

        gap(root,14);
        LinearLayout tiles=new LinearLayout(this);tiles.setOrientation(LinearLayout.HORIZONTAL);
        addHomeTile(tiles,"OBLASTI","Ponovi ključne teme",v->showAreas());
        addHomeTile(tiles,"STATISTIKA","Prati svoj napredak",v->showStatistics());
        addHomeTile(tiles,"OPCIJE","Prilagodi aplikaciju",v->showOptions());
        root.addView(tiles,new LinearLayout.LayoutParams(-1,-2));

        TextView footer=centerTxt("ISTRAŽUJ  •  MISLI  •  NAPREDUJ",11,MUTED);footer.setLetterSpacing(0.08f);footer.setPadding(0,dp(20),0,0);root.addView(footer);
        setContentView(scroll);
    }

    private void addHomeTile(LinearLayout row,String title,String sub,View.OnClickListener l){
        LinearLayout c=card();c.setGravity(Gravity.CENTER);c.setOnClickListener(l);c.setClickable(true);c.setFocusable(true);
        TextView h=centerTxt(title,13,GREEN_DARK);h.setTypeface(null,Typeface.BOLD);c.addView(h);
        TextView s=centerTxt(sub,11,MUTED);s.setPadding(0,dp(5),0,0);c.addView(s);
        LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(0,dp(105),1f);p.setMargins(dp(3),0,dp(3),0);row.addView(c,p);
    }

    private void showAreas(){showInfoPage("Oblasti","• Mehanika i kretanje\n• Sila, rad, energija i snaga\n• Toplotne pojave\n• Elektricitet i strujna kola\n• Magnetizam\n• Optika\n• Talasi i zvuk\n• Osnovne fizičke veličine i jedinice");}

    private void showStatistics(){
        SharedPreferences p=getSharedPreferences(PREFS,MODE_PRIVATE);
        int attempts=p.getInt("attempts",0),best=p.getInt("best",0),sum=p.getInt("sum",0);
        int avg=attempts==0?0:Math.round(sum/(float)attempts);
        String lastWeak=p.getString("weak","Još nema rezultata.");
        String body="Urađenih testova: "+attempts+"\nNajbolji rezultat: "+best+"%\nProsečan rezultat: "+avg+"%\n\nOblasti za ponavljanje iz poslednjeg testa:\n"+lastWeak;
        showInfoPage("Statistika",body);
    }

    private void showOptions(){
        final String[] items={"Resetuj statistiku"};
        AlertDialog.Builder b=new AlertDialog.Builder(this);b.setTitle("Opcije");b.setItems(items,(d,which)->{
            getSharedPreferences(PREFS,MODE_PRIVATE).edit().clear().apply();
            Toast.makeText(this,"Statistika je resetovana.",Toast.LENGTH_SHORT).show();
        });b.setNegativeButton("Zatvori",null);b.show();
    }

    private void showInfoPage(String title,String body){
        LinearLayout root=new LinearLayout(this);root.setOrientation(LinearLayout.VERTICAL);root.setPadding(dp(22),dp(26),dp(22),dp(26));root.setBackgroundColor(GREEN_PALE);
        TextView h=txt(title,28,GREEN_DARK);h.setTypeface(null,Typeface.BOLD);root.addView(h);
        gap(root,14);LinearLayout c=card();TextView t=txt(body,16,TEXT);c.addView(t);root.addView(c,new LinearLayout.LayoutParams(-1,-2));
        gap(root,18);Button back=greenButton("←  Nazad na početnu");back.setOnClickListener(v->showStartScreen());root.addView(back,new LinearLayout.LayoutParams(-1,dp(54)));setContentView(root);
    }

    private void startTest(){questions=TestBuilder.buildTest20();index=0;score=0;checked=false;errorsByArea.clear();startedAt=System.currentTimeMillis();timerRunning=true;buildTestUi();showQuestion();timerHandler.post(timerTick);}

    private void buildTestUi(){
        LinearLayout page=new LinearLayout(this);page.setOrientation(LinearLayout.VERTICAL);page.setBackgroundColor(GREEN_PALE);
        ScrollView scroll=new ScrollView(this);scroll.setFillViewport(true);content=new LinearLayout(this);content.setOrientation(LinearLayout.VERTICAL);content.setPadding(dp(18),dp(18),dp(18),dp(24));scroll.addView(content);page.addView(scroll,new LinearLayout.LayoutParams(-1,0,1f));
        TextView title=txt("Fizika 1 – inicijalni test",23,GREEN_DARK);title.setTypeface(null,Typeface.BOLD);content.addView(title);TextView subtitle=txt("Gradivo 7. i 8. razreda",14,MUTED);subtitle.setPadding(0,dp(3),0,dp(12));content.addView(subtitle);
        LinearLayout top=new LinearLayout(this);top.setOrientation(LinearLayout.HORIZONTAL);top.setGravity(Gravity.CENTER_VERTICAL);progressText=txt("",14,MUTED);scoreText=txt("",14,TEXT);scoreText.setTypeface(null,Typeface.BOLD);top.addView(progressText,new LinearLayout.LayoutParams(0,dp(36),1f));top.addView(scoreText);content.addView(top);
        progressBar=new ProgressBar(this,null,android.R.attr.progressBarStyleHorizontal);progressBar.setMax(20);progressBar.setProgressTintList(android.content.res.ColorStateList.valueOf(GREEN));content.addView(progressBar,new LinearLayout.LayoutParams(-1,dp(10)));
        areaText=txt("",14,GREEN_MID);areaText.setTypeface(null,Typeface.BOLD);areaText.setPadding(0,dp(17),0,dp(6));content.addView(areaText);questionText=txt("",20,TEXT);questionText.setTypeface(null,Typeface.BOLD);questionText.setPadding(0,0,0,dp(12));content.addView(questionText);
        radioGroup=new RadioGroup(this);radioGroup.setOrientation(RadioGroup.VERTICAL);content.addView(radioGroup);feedbackText=txt("",15,TEXT);feedbackText.setPadding(dp(14),dp(12),dp(14),dp(12));feedbackText.setVisibility(View.GONE);LinearLayout.LayoutParams fbp=new LinearLayout.LayoutParams(-1,-2);fbp.setMargins(0,dp(14),0,0);content.addView(feedbackText,fbp);
        LinearLayout actions=new LinearLayout(this);actions.setOrientation(LinearLayout.HORIZONTAL);actions.setPadding(0,dp(16),0,0);checkButton=greenButton("Proveri odgovor");checkButton.setOnClickListener(v->checkAnswer());actions.addView(checkButton,new LinearLayout.LayoutParams(0,dp(52),1f));nextButton=new Button(this);nextButton.setText("Sledeće pitanje");nextButton.setTextSize(15);nextButton.setAllCaps(false);nextButton.setTextColor(GREEN_DARK);nextButton.setTypeface(null,Typeface.BOLD);nextButton.setBackground(rounded(Color.WHITE,LINE,14,1));nextButton.setVisibility(View.GONE);nextButton.setOnClickListener(v->nextQuestion());LinearLayout.LayoutParams nlp=new LinearLayout.LayoutParams(0,dp(52),1f);nlp.setMargins(dp(10),0,0,0);actions.addView(nextButton,nlp);content.addView(actions);
        LinearLayout timerBar=new LinearLayout(this);timerBar.setGravity(Gravity.CENTER);timerBar.setPadding(dp(12),dp(10),dp(12),dp(10));timerBar.setBackgroundColor(Color.WHITE);timerText=centerTxt("Vreme: 00:00",15,GREEN_DARK);timerText.setTypeface(null,Typeface.BOLD);timerBar.addView(timerText);page.addView(timerBar,new LinearLayout.LayoutParams(-1,dp(48)));setContentView(page);
    }

    private RadioButton optionButton(String value,int id){RadioButton rb=new RadioButton(this);rb.setId(id);rb.setText(value);rb.setTextSize(16);rb.setTextColor(TEXT);rb.setButtonTintList(android.content.res.ColorStateList.valueOf(GREEN));rb.setPadding(dp(8),dp(8),dp(8),dp(8));rb.setBackground(rounded(Color.WHITE,LINE,12,1));LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(-1,-2);lp.setMargins(0,dp(5),0,dp(5));rb.setLayoutParams(lp);return rb;}
    private void showQuestion(){if(index>=questions.size()){showResults();return;}checked=false;Question q=questions.get(index);progressText.setText("Pitanje "+(index+1)+" od "+questions.size());scoreText.setText("Tačno: "+score);progressBar.setProgress(index+1);String badge=q.calculation?q.area+"  •  računski – "+("OSNOVNI".equals(q.level)?"osnovni nivo":"srednji nivo"):q.area;areaText.setText(badge);questionText.setText(q.text);radioGroup.removeAllViews();for(int i=0;i<q.answers.length;i++)radioGroup.addView(optionButton(q.answers[i],1000+i));feedbackText.setText("");feedbackText.setVisibility(View.GONE);checkButton.setEnabled(true);checkButton.setAlpha(1f);nextButton.setVisibility(View.GONE);}
    private void checkAnswer(){if(checked)return;int selectedId=radioGroup.getCheckedRadioButtonId();if(selectedId==-1){Toast.makeText(this,"Izaberi jedan odgovor.",Toast.LENGTH_SHORT).show();return;}checked=true;int chosen=selectedId-1000;Question q=questions.get(index);boolean ok=chosen==q.correct;if(ok){score++;feedbackText.setText("✓ Tačno!\n"+q.explanation);feedbackText.setBackground(rounded(Color.rgb(232,245,233),Color.rgb(129,199,132),12,1));feedbackText.setTextColor(Color.rgb(27,94,32));}else{Integer old=errorsByArea.get(q.area);errorsByArea.put(q.area,old==null?1:old+1);feedbackText.setText("✗ Netačno. Tačan odgovor: "+q.answers[q.correct]+"\n"+q.explanation);feedbackText.setBackground(rounded(Color.rgb(255,243,224),Color.rgb(255,183,77),12,1));feedbackText.setTextColor(Color.rgb(121,85,72));}feedbackText.setVisibility(View.VISIBLE);scoreText.setText("Tačno: "+score);for(int i=0;i<radioGroup.getChildCount();i++)radioGroup.getChildAt(i).setEnabled(false);checkButton.setEnabled(false);checkButton.setAlpha(.55f);nextButton.setText(index==questions.size()-1?"Rezultat":"Sledeće pitanje");nextButton.setVisibility(View.VISIBLE);}
    private void nextQuestion(){if(!checked)return;index++;if(index>=questions.size())showResults();else showQuestion();}

    private void showResults(){
        long elapsed=System.currentTimeMillis()-startedAt;stopTimer();int total=questions.size();int percent=total==0?0:Math.round(100f*score/total);String label=percent>=90?"Odlično!":percent>=75?"Vrlo dobro!":percent>=60?"Dobro!":percent>=45?"Solidno!":"Pokušaj ponovo!";
        saveStats(percent);
        ScrollView scroll=new ScrollView(this);scroll.setFillViewport(true);LinearLayout root=new LinearLayout(this);root.setOrientation(LinearLayout.VERTICAL);root.setGravity(Gravity.CENTER_HORIZONTAL);root.setPadding(dp(22),dp(30),dp(22),dp(30));root.setBackgroundColor(GREEN_PALE);scroll.addView(root);TextView done=centerTxt(label,30,GREEN_DARK);done.setTypeface(null,Typeface.BOLD);root.addView(done);TextView pct=centerTxt(percent+"%",48,GREEN);pct.setTypeface(null,Typeface.BOLD);root.addView(pct);TextView scoreView=centerTxt(score+" / "+total+" tačnih odgovora",18,TEXT);root.addView(scoreView);TextView timeView=centerTxt("Vreme: "+formatTime(elapsed),15,MUTED);timeView.setPadding(0,dp(8),0,dp(18));root.addView(timeView);
        LinearLayout c=card();TextView head=txt("Oblasti za dodatno ponavljanje",16,GREEN_DARK);head.setTypeface(null,Typeface.BOLD);c.addView(head);TextView weak=txt(buildWeakAreas(),14,TEXT);weak.setPadding(0,dp(8),0,0);c.addView(weak);root.addView(c,new LinearLayout.LayoutParams(-1,-2));Button again=greenButton("Uradi novi test");again.setOnClickListener(v->startTest());LinearLayout.LayoutParams alp=new LinearLayout.LayoutParams(-1,dp(56));alp.setMargins(0,dp(18),0,0);root.addView(again,alp);Button home=greenButton("Početna");home.setOnClickListener(v->showStartScreen());LinearLayout.LayoutParams hlp=new LinearLayout.LayoutParams(-1,dp(52));hlp.setMargins(0,dp(10),0,0);root.addView(home,hlp);setContentView(scroll);
    }

    private void saveStats(int percent){SharedPreferences p=getSharedPreferences(PREFS,MODE_PRIVATE);int attempts=p.getInt("attempts",0)+1;int best=Math.max(p.getInt("best",0),percent);int sum=p.getInt("sum",0)+percent;p.edit().putInt("attempts",attempts).putInt("best",best).putInt("sum",sum).putString("weak",buildWeakAreas()).apply();}
    private String buildWeakAreas(){if(errorsByArea.isEmpty())return "Nema izdvojenih slabijih oblasti — svi odgovori su tačni.";List<Map.Entry<String,Integer>> e=new ArrayList<>(errorsByArea.entrySet());Collections.sort(e,(a,b)->b.getValue().compareTo(a.getValue()));StringBuilder sb=new StringBuilder();for(int i=0;i<Math.min(3,e.size());i++){if(i>0)sb.append("\n");sb.append("• ").append(e.get(i).getKey()).append(" — ").append(e.get(i).getValue()).append(" greš.");}return sb.toString();}
    private String formatTime(long millis){long sec=Math.max(0,millis/1000),min=sec/60;sec%=60;return String.format(Locale.US,"%02d:%02d",min,sec);}
    private final Runnable timerTick=new Runnable(){@Override public void run(){if(!timerRunning)return;if(timerText!=null)timerText.setText("Vreme: "+formatTime(System.currentTimeMillis()-startedAt));timerHandler.postDelayed(this,1000);}};
    private void stopTimer(){timerRunning=false;timerHandler.removeCallbacks(timerTick);}
    @Override protected void onDestroy(){stopTimer();super.onDestroy();}
}
