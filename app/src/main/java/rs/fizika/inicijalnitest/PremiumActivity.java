package rs.fizika.inicijalnitest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

import java.util.*;

public class PremiumActivity extends Activity {
    private static final int TEAL_DARK = Color.rgb(5, 67, 64);
    private static final int TEAL = Color.rgb(0, 121, 97);
    private static final int TEAL_LIGHT = Color.rgb(35, 150, 120);
    private static final int GOLD = Color.rgb(219, 174, 70);
    private static final int BG = Color.rgb(244, 249, 247);
    private static final int TEXT = Color.rgb(25, 39, 36);
    private static final int MUTED = Color.rgb(93, 112, 107);
    private static final int LINE = Color.rgb(205, 224, 217);
    private static final String PREFS = "fizika1_premium_stats";

    private final Handler timerHandler = new Handler();
    private long startedAt = 0L;
    private boolean timerRunning = false;
    private List<Question> questions = new ArrayList<>();
    private int index = 0;
    private int score = 0;
    private boolean checked = false;
    private final Map<String,Integer> errorsByArea = new LinkedHashMap<>();

    private TextView progressText, scoreText, areaText, questionText, feedbackText, timerText;
    private ProgressBar progressBar;
    private RadioGroup radioGroup;
    private Button checkButton, nextButton;

    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        getWindow().setStatusBarColor(TEAL_DARK);
        getWindow().setNavigationBarColor(TEAL_DARK);
        showHome();
    }

    private int dp(int v){ return (int)(v*getResources().getDisplayMetrics().density+0.5f); }

    private GradientDrawable round(int fill, int stroke, int radius, int sw){
        GradientDrawable d = new GradientDrawable();
        d.setColor(fill);
        d.setCornerRadius(dp(radius));
        if(sw>0) d.setStroke(dp(sw), stroke);
        return d;
    }

    private TextView text(String s,int sp,int color){
        TextView t=new TextView(this); t.setText(s); t.setTextSize(sp); t.setTextColor(color); t.setLineSpacing(0,1.12f); return t;
    }

    private TextView centered(String s,int sp,int color){ TextView t=text(s,sp,color); t.setGravity(Gravity.CENTER); return t; }

    private Button primaryButton(String label){
        Button b=new Button(this); b.setText(label); b.setTextSize(17); b.setAllCaps(false); b.setTextColor(Color.WHITE); b.setTypeface(null,Typeface.BOLD);
        b.setBackground(round(TEAL, GOLD, 25, 1)); b.setElevation(dp(4)); return b;
    }

    private LinearLayout card(){
        LinearLayout c=new LinearLayout(this); c.setOrientation(LinearLayout.VERTICAL); c.setPadding(dp(16),dp(15),dp(16),dp(15));
        c.setBackground(round(Color.WHITE, LINE, 20, 1)); c.setElevation(dp(2)); return c;
    }

    private void gap(LinearLayout r,int h){ Space s=new Space(this); r.addView(s,new LinearLayout.LayoutParams(1,dp(h))); }

    private void showHome(){
        stopTimer();
        ScrollView scroll=new ScrollView(this); scroll.setFillViewport(true); scroll.setBackgroundColor(BG);
        LinearLayout root=new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setBackgroundColor(BG); scroll.addView(root);

        ImageView hero=new ImageView(this); hero.setImageResource(R.drawable.fizika1_hero); hero.setScaleType(ImageView.ScaleType.CENTER_CROP); hero.setAdjustViewBounds(false); hero.setContentDescription("Fizika 1 – Gimnazija Inđija");
        root.addView(hero,new LinearLayout.LayoutParams(-1,dp(455)));

        LinearLayout body=new LinearLayout(this); body.setOrientation(LinearLayout.VERTICAL); body.setPadding(dp(18),dp(14),dp(18),dp(28)); root.addView(body);

        Button start=primaryButton("▶   ZAPOČNI TEST    →"); start.setOnClickListener(v->startTest()); body.addView(start,new LinearLayout.LayoutParams(-1,dp(62)));

        gap(body,14);
        LinearLayout tiles=new LinearLayout(this); tiles.setOrientation(LinearLayout.HORIZONTAL); tiles.setGravity(Gravity.CENTER);
        addTile(tiles,"▤","OBLASTI","Ponovi ključne teme",v->showAreas());
        addTile(tiles,"▥","STATISTIKA","Prati svoj napredak",v->showStatistics());
        addTile(tiles,"⚙","OPCIJE","Prilagodi aplikaciju",v->showOptions());
        body.addView(tiles,new LinearLayout.LayoutParams(-1,-2));

        gap(body,14);
        SharedPreferences p=getSharedPreferences(PREFS,MODE_PRIVATE);
        int attempts=p.getInt("attempts",0), best=p.getInt("best",0);
        LinearLayout status=card();
        TextView sh=text("TVOJ NAPREDAK",12,TEAL_LIGHT); sh.setTypeface(null,Typeface.BOLD); sh.setLetterSpacing(.08f); status.addView(sh);
        TextView sv=text(attempts==0 ? "Još nema urađenih testova. Prvi rezultat će se sačuvati ovde." : "Urađenih testova: "+attempts+"   •   Najbolji rezultat: "+best+"%",14,TEXT); sv.setPadding(0,dp(7),0,0); status.addView(sv);
        body.addView(status,new LinearLayout.LayoutParams(-1,-2));

        TextView footer=centered("ISTRAŽUJ  •  MISLI  •  NAPREDUJ",11,MUTED); footer.setLetterSpacing(.08f); footer.setPadding(0,dp(20),0,dp(8)); body.addView(footer);
        setContentView(scroll);
    }

    private void addTile(LinearLayout row,String icon,String title,String sub,View.OnClickListener listener){
        LinearLayout c=card(); c.setGravity(Gravity.CENTER); c.setClickable(true); c.setFocusable(true); c.setOnClickListener(listener);
        TextView i=centered(icon,27,TEAL); i.setTypeface(null,Typeface.BOLD); c.addView(i);
        TextView h=centered(title,12,TEAL_DARK); h.setTypeface(null,Typeface.BOLD); h.setPadding(0,dp(3),0,0); c.addView(h);
        TextView s=centered(sub,10,MUTED); s.setPadding(0,dp(4),0,0); c.addView(s);
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(0,dp(120),1f); lp.setMargins(dp(4),0,dp(4),0); row.addView(c,lp);
    }

    private void showAreas(){
        showInfo("Oblasti","MEHANIKA I KRETANJE\n• kretanje, brzina, ubrzanje\n• sila, rad, energija i snaga\n\nTOPLOTNE POJAVE\n• temperatura, toplota i promene agregatnog stanja\n\nELEKTRICITET I MAGNETIZAM\n• struja, napon, otpor i strujna kola\n• magnetno polje\n\nOPTIKA, TALASI I ZVUK\n• svetlost i optičke pojave\n• talasi i zvuk\n\nFIZIČKE VELIČINE I JEDINICE");
    }

    private void showStatistics(){
        SharedPreferences p=getSharedPreferences(PREFS,MODE_PRIVATE);
        int attempts=p.getInt("attempts",0),best=p.getInt("best",0),sum=p.getInt("sum",0),last=p.getInt("last",0);
        int avg=attempts==0?0:Math.round(sum/(float)attempts);
        String weak=p.getString("weak","Još nema podataka.");
        showInfo("Statistika","Urađenih testova: "+attempts+"\nPoslednji rezultat: "+last+"%\nNajbolji rezultat: "+best+"%\nProsečan rezultat: "+avg+"%\n\nOblasti za dodatno ponavljanje:\n"+weak);
    }

    private void showOptions(){
        final String[] items={"Resetuj statistiku","O aplikaciji"};
        new AlertDialog.Builder(this).setTitle("Opcije").setItems(items,(d,w)->{
            if(w==0){ getSharedPreferences(PREFS,MODE_PRIVATE).edit().clear().apply(); Toast.makeText(this,"Statistika je resetovana.",Toast.LENGTH_SHORT).show(); }
            else new AlertDialog.Builder(this).setTitle("Fizika 1").setMessage("Inicijalni test iz fizike za obnavljanje gradiva 7. i 8. razreda.\n\nGimnazija Inđija").setPositiveButton("U redu",null).show();
        }).setNegativeButton("Zatvori",null).show();
    }

    private void showInfo(String title,String body){
        ScrollView sc=new ScrollView(this); sc.setFillViewport(true); sc.setBackgroundColor(BG);
        LinearLayout root=new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setPadding(dp(20),dp(22),dp(20),dp(26)); sc.addView(root);
        TextView h=text(title,30,TEAL_DARK); h.setTypeface(null,Typeface.BOLD); root.addView(h); gap(root,14);
        LinearLayout c=card(); TextView b=text(body,15,TEXT); c.addView(b); root.addView(c,new LinearLayout.LayoutParams(-1,-2)); gap(root,18);
        Button back=primaryButton("←  Nazad na početnu"); back.setOnClickListener(v->showHome()); root.addView(back,new LinearLayout.LayoutParams(-1,dp(56))); setContentView(sc);
    }

    private void startTest(){
        questions=TestBuilder.buildTest20(); index=0; score=0; checked=false; errorsByArea.clear(); startedAt=System.currentTimeMillis(); timerRunning=true;
        buildTestUi(); showQuestion(); timerHandler.post(timerTick);
    }

    private void buildTestUi(){
        LinearLayout page=new LinearLayout(this); page.setOrientation(LinearLayout.VERTICAL); page.setBackgroundColor(BG);
        ScrollView scroll=new ScrollView(this); scroll.setFillViewport(true);
        LinearLayout content=new LinearLayout(this); content.setOrientation(LinearLayout.VERTICAL); content.setPadding(dp(18),dp(18),dp(18),dp(22)); scroll.addView(content); page.addView(scroll,new LinearLayout.LayoutParams(-1,0,1f));

        TextView title=text("Fizika 1",25,TEAL_DARK); title.setTypeface(null,Typeface.BOLD); content.addView(title);
        TextView sub=text("Inicijalni test • gradivo 7. i 8. razreda",13,MUTED); sub.setPadding(0,dp(2),0,dp(13)); content.addView(sub);

        LinearLayout top=new LinearLayout(this); top.setOrientation(LinearLayout.HORIZONTAL); top.setGravity(Gravity.CENTER_VERTICAL);
        progressText=text("",13,MUTED); scoreText=text("",13,TEAL_DARK); scoreText.setTypeface(null,Typeface.BOLD); top.addView(progressText,new LinearLayout.LayoutParams(0,dp(34),1f)); top.addView(scoreText); content.addView(top);
        progressBar=new ProgressBar(this,null,android.R.attr.progressBarStyleHorizontal); progressBar.setMax(20); progressBar.setProgressTintList(android.content.res.ColorStateList.valueOf(TEAL)); content.addView(progressBar,new LinearLayout.LayoutParams(-1,dp(9)));

        gap(content,14); LinearLayout qcard=card();
        areaText=text("",13,TEAL_LIGHT); areaText.setTypeface(null,Typeface.BOLD); qcard.addView(areaText);
        questionText=text("",20,TEXT); questionText.setTypeface(null,Typeface.BOLD); questionText.setPadding(0,dp(8),0,dp(10)); qcard.addView(questionText);
        radioGroup=new RadioGroup(this); radioGroup.setOrientation(RadioGroup.VERTICAL); qcard.addView(radioGroup); content.addView(qcard,new LinearLayout.LayoutParams(-1,-2));

        feedbackText=text("",15,TEXT); feedbackText.setPadding(dp(14),dp(12),dp(14),dp(12)); feedbackText.setVisibility(View.GONE); LinearLayout.LayoutParams fbp=new LinearLayout.LayoutParams(-1,-2); fbp.setMargins(0,dp(12),0,0); content.addView(feedbackText,fbp);

        LinearLayout actions=new LinearLayout(this); actions.setOrientation(LinearLayout.HORIZONTAL); actions.setPadding(0,dp(15),0,0);
        checkButton=primaryButton("Proveri odgovor"); checkButton.setOnClickListener(v->checkAnswer()); actions.addView(checkButton,new LinearLayout.LayoutParams(0,dp(54),1f));
        nextButton=new Button(this); nextButton.setAllCaps(false); nextButton.setText("Sledeće pitanje"); nextButton.setTextSize(15); nextButton.setTextColor(TEAL_DARK); nextButton.setTypeface(null,Typeface.BOLD); nextButton.setBackground(round(Color.WHITE,LINE,20,1)); nextButton.setVisibility(View.GONE); nextButton.setOnClickListener(v->nextQuestion());
        LinearLayout.LayoutParams nlp=new LinearLayout.LayoutParams(0,dp(54),1f); nlp.setMargins(dp(10),0,0,0); actions.addView(nextButton,nlp); content.addView(actions);

        LinearLayout timer=new LinearLayout(this); timer.setGravity(Gravity.CENTER); timer.setBackgroundColor(TEAL_DARK); timerText=centered("Vreme 00:00",14,Color.WHITE); timerText.setTypeface(null,Typeface.BOLD); timer.addView(timerText); page.addView(timer,new LinearLayout.LayoutParams(-1,dp(50)));
        setContentView(page);
    }

    private RadioButton option(String value,int id){
        RadioButton rb=new RadioButton(this); rb.setId(id); rb.setText(value); rb.setTextSize(16); rb.setTextColor(TEXT); rb.setButtonTintList(android.content.res.ColorStateList.valueOf(TEAL)); rb.setPadding(dp(10),dp(10),dp(10),dp(10)); rb.setBackground(round(Color.rgb(249,252,251),LINE,14,1));
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(-1,-2); lp.setMargins(0,dp(5),0,dp(5)); rb.setLayoutParams(lp); return rb;
    }

    private void showQuestion(){
        if(index>=questions.size()){ showResults(); return; }
        checked=false; Question q=questions.get(index);
        progressText.setText("Pitanje "+(index+1)+" od "+questions.size()); scoreText.setText("Tačno: "+score); progressBar.setProgress(index+1);
        areaText.setText(q.calculation ? q.area+" • računski zadatak" : q.area); questionText.setText(q.text);
        radioGroup.removeAllViews(); for(int i=0;i<q.answers.length;i++) radioGroup.addView(option(q.answers[i],1000+i));
        feedbackText.setVisibility(View.GONE); checkButton.setEnabled(true); checkButton.setAlpha(1f); nextButton.setVisibility(View.GONE);
    }

    private void checkAnswer(){
        if(checked) return; int selected=radioGroup.getCheckedRadioButtonId(); if(selected==-1){ Toast.makeText(this,"Izaberi jedan odgovor.",Toast.LENGTH_SHORT).show(); return; }
        checked=true; int chosen=selected-1000; Question q=questions.get(index); boolean ok=chosen==q.correct;
        if(ok){ score++; feedbackText.setText("✓ Tačno!\n"+q.explanation); feedbackText.setBackground(round(Color.rgb(231,247,239),Color.rgb(105,190,145),14,1)); feedbackText.setTextColor(TEAL_DARK); }
        else { errorsByArea.put(q.area,errorsByArea.containsKey(q.area)?errorsByArea.get(q.area)+1:1); feedbackText.setText("✗ Netačno. Tačan odgovor: "+q.answers[q.correct]+"\n"+q.explanation); feedbackText.setBackground(round(Color.rgb(255,247,230),Color.rgb(233,188,89),14,1)); feedbackText.setTextColor(Color.rgb(105,75,28)); }
        feedbackText.setVisibility(View.VISIBLE); scoreText.setText("Tačno: "+score); for(int i=0;i<radioGroup.getChildCount();i++) radioGroup.getChildAt(i).setEnabled(false); checkButton.setEnabled(false); checkButton.setAlpha(.5f); nextButton.setText(index==questions.size()-1?"Rezultat":"Sledeće pitanje"); nextButton.setVisibility(View.VISIBLE);
    }

    private void nextQuestion(){ if(!checked)return; index++; if(index>=questions.size())showResults(); else showQuestion(); }

    private void showResults(){
        long elapsed=System.currentTimeMillis()-startedAt; stopTimer(); int total=questions.size(); int pct=total==0?0:Math.round(100f*score/total); saveStats(pct);
        ScrollView sc=new ScrollView(this); sc.setFillViewport(true); sc.setBackgroundColor(BG); LinearLayout root=new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setGravity(Gravity.CENTER_HORIZONTAL); root.setPadding(dp(20),dp(28),dp(20),dp(28)); sc.addView(root);
        TextView h=centered(pct>=90?"Odlično!":pct>=75?"Vrlo dobro!":pct>=60?"Dobro!":"Nastavi sa vežbanjem",28,TEAL_DARK); h.setTypeface(null,Typeface.BOLD); root.addView(h);
        TextView big=centered(pct+"%",54,TEAL); big.setTypeface(null,Typeface.BOLD); big.setPadding(0,dp(8),0,0); root.addView(big);
        TextView res=centered(score+" / "+total+" tačnih odgovora\nVreme: "+formatTime(elapsed),16,TEXT); root.addView(res); gap(root,16);
        LinearLayout weak=card(); TextView wh=text("Oblasti za dodatno ponavljanje",15,TEAL_DARK); wh.setTypeface(null,Typeface.BOLD); weak.addView(wh); TextView wb=text(buildWeak(),14,TEXT); wb.setPadding(0,dp(8),0,0); weak.addView(wb); root.addView(weak,new LinearLayout.LayoutParams(-1,-2)); gap(root,16);
        Button again=primaryButton("Uradi novi test"); again.setOnClickListener(v->startTest()); root.addView(again,new LinearLayout.LayoutParams(-1,dp(58))); gap(root,9);
        Button home=new Button(this); home.setText("Početna"); home.setAllCaps(false); home.setTextColor(TEAL_DARK); home.setTypeface(null,Typeface.BOLD); home.setBackground(round(Color.WHITE,LINE,20,1)); home.setOnClickListener(v->showHome()); root.addView(home,new LinearLayout.LayoutParams(-1,dp(54))); setContentView(sc);
    }

    private void saveStats(int pct){
        SharedPreferences p=getSharedPreferences(PREFS,MODE_PRIVATE); int attempts=p.getInt("attempts",0)+1; int sum=p.getInt("sum",0)+pct; int best=Math.max(p.getInt("best",0),pct);
        p.edit().putInt("attempts",attempts).putInt("sum",sum).putInt("best",best).putInt("last",pct).putString("weak",buildWeak()).apply();
    }

    private String buildWeak(){
        if(errorsByArea.isEmpty()) return "Nema izdvojenih slabijih oblasti — svi odgovori su tačni.";
        List<Map.Entry<String,Integer>> e=new ArrayList<>(errorsByArea.entrySet()); Collections.sort(e,(a,b)->b.getValue()-a.getValue()); StringBuilder s=new StringBuilder(); int n=Math.min(3,e.size()); for(int i=0;i<n;i++){ if(i>0)s.append("\n"); s.append("• ").append(e.get(i).getKey()).append(" — ").append(e.get(i).getValue()).append(" greš."); } return s.toString();
    }

    private String formatTime(long ms){ long sec=Math.max(0,ms/1000),min=sec/60; sec%=60; return String.format(Locale.US,"%02d:%02d",min,sec); }
    private final Runnable timerTick=new Runnable(){ @Override public void run(){ if(!timerRunning)return; if(timerText!=null)timerText.setText("Vreme "+formatTime(System.currentTimeMillis()-startedAt)); timerHandler.postDelayed(this,1000); } };
    private void stopTimer(){ timerRunning=false; timerHandler.removeCallbacks(timerTick); }
    @Override protected void onDestroy(){ stopTimer(); super.onDestroy(); }
}
