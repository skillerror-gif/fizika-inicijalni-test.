package rs.fizika.inicijalnitest;

import java.util.ArrayList;
import java.util.List;

public final class CalculationBank {
    public static final String OSNOVNI = "OSNOVNI";
    public static final String SREDNJI = "SREDNJI";
    private CalculationBank() {}

    private static void add(List<Question> q, String level, String area, String text, String[] opts, int correct, String explanation) {
        q.add(new Question(area, text, opts, correct, explanation, true, level));
    }

    public static List<Question> all() {
        ArrayList<Question> q = new ArrayList<>();

        add(q,OSNOVNI,"Kretanje","Automobil poveća brzinu sa 5 m/s na 15 m/s za 5 s. Koliko je ubrzanje?",new String[]{"2 m/s²","4 m/s²","10 m/s²","50 m/s²"},0,"a=(15−5)/5=2 m/s².");
        add(q,OSNOVNI,"Kretanje","Telo kreće iz mirovanja ubrzanjem 2 m/s². Kolika je brzina posle 4 s?",new String[]{"8 m/s","6 m/s","2 m/s","16 m/s"},0,"v=at=2·4=8 m/s.");
        add(q,OSNOVNI,"Kretanje","Biciklista se kreće stalno 6 m/s tokom 20 s. Koliki put pređe?",new String[]{"120 m","26 m","3,3 m","300 m"},0,"s=vt=6·20=120 m.");
        add(q,OSNOVNI,"Kretanje","Telo usporava sa 18 m/s ubrzanjem 3 m/s². Za koliko se zaustavlja?",new String[]{"6 s","3 s","9 s","54 s"},0,"t=v0/a=18/3=6 s.");
        add(q,OSNOVNI,"Kretanje","Brzina poraste sa 10 m/s na 14 m/s za 2 s. Ubrzanje je:",new String[]{"2 m/s²","4 m/s²","7 m/s²","12 m/s²"},0,"a=(14−10)/2=2 m/s².");
        add(q,SREDNJI,"Kretanje","Telo kreće iz mirovanja ubrzanjem 2 m/s². Koliki put pređe za 5 s?",new String[]{"25 m","10 m","50 m","5 m"},0,"s=at²/2=2·25/2=25 m.");
        add(q,SREDNJI,"Kretanje","Automobil ima v0=8 m/s i ubrzava 3 m/s² tokom 4 s. Kolika je krajnja brzina?",new String[]{"20 m/s","12 m/s","32 m/s","44 m/s"},0,"v=v0+at=8+3·4=20 m/s.");
        add(q,SREDNJI,"Kretanje","Telo ima v0=4 m/s i a=2 m/s². Koliki put pređe za 3 s?",new String[]{"21 m","18 m","12 m","30 m"},0,"s=v0t+at²/2=4·3+2·9/2=21 m.");
        add(q,SREDNJI,"Kretanje","Voz usporava sa 20 m/s na 5 m/s za 5 s. Ubrzanje je:",new String[]{"−3 m/s²","3 m/s²","−5 m/s²","5 m/s²"},0,"a=(5−20)/5=−3 m/s².");
        add(q,SREDNJI,"Kretanje","Telo se kreće 12 m/s i ubrzava 2 m/s² tokom 6 s. Srednja brzina u tom intervalu je:",new String[]{"18 m/s","24 m/s","12 m/s","6 m/s"},0,"Krajnja brzina je 24 m/s, pa je vsr=(12+24)/2=18 m/s.");

        add(q,OSNOVNI,"Sile","Na telo mase 4 kg deluje rezultujuća sila 12 N. Ubrzanje je:",new String[]{"3 m/s²","48 m/s²","8 m/s²","0,33 m/s²"},0,"a=F/m=12/4=3 m/s².");
        add(q,OSNOVNI,"Sile","Telo mase 2 kg ubrzava 5 m/s². Rezultujuća sila je:",new String[]{"10 N","2,5 N","7 N","25 N"},0,"F=ma=2·5=10 N.");
        add(q,OSNOVNI,"Sile","Na telo deluju 10 N udesno i 4 N ulevo. Rezultanta je:",new String[]{"6 N udesno","14 N udesno","6 N ulevo","40 N"},0,"Frez=10−4=6 N udesno.");
        add(q,OSNOVNI,"Sile","Kolika je sila teže tela mase 3 kg za g≈10 N/kg?",new String[]{"30 N","3 N","13 N","300 N"},0,"Fg=mg=3·10=30 N.");
        add(q,OSNOVNI,"Sile","Ako sila 20 N deluje na masu 5 kg, ubrzanje je:",new String[]{"4 m/s²","100 m/s²","15 m/s²","0,25 m/s²"},0,"a=20/5=4 m/s².");
        add(q,SREDNJI,"Sile","Sila vuče 30 N deluje udesno, trenje 10 N ulevo na telo mase 5 kg. Ubrzanje je:",new String[]{"4 m/s²","6 m/s²","2 m/s²","8 m/s²"},0,"Frez=20 N, a=20/5=4 m/s².");
        add(q,SREDNJI,"Sile","Telo mase 8 kg ubrzava 1,5 m/s². Trenje je 4 N suprotno kretanju. Kolika vučna sila deluje?",new String[]{"16 N","12 N","8 N","20 N"},0,"Fvuče−4=8·1,5=12, pa Fvuče=16 N.");
        add(q,SREDNJI,"Sile","Dve sile istog smera, 7 N i 13 N, deluju na masu 4 kg. Ubrzanje je:",new String[]{"5 m/s²","20 m/s²","4 m/s²","80 m/s²"},0,"Frez=20 N, a=20/4=5 m/s².");
        add(q,SREDNJI,"Sile","Na telo mase 6 kg deluju 18 N udesno i 6 N ulevo. Za koliko se brzina promeni za 4 s?",new String[]{"8 m/s","2 m/s","12 m/s","24 m/s"},0,"Frez=12 N, a=2 m/s², pa Δv=at=8 m/s.");
        add(q,SREDNJI,"Sile","Telo mase 10 kg miruje. Na njega deluje horizontalna sila 25 N, a trenje 15 N. Kolika je brzina posle 3 s?",new String[]{"3 m/s","1 m/s","7,5 m/s","30 m/s"},0,"Frez=10 N, a=1 m/s², v=3 m/s.");

        add(q,OSNOVNI,"Gravitacija","Telo slobodno pada 3 s. Uz g≈10 m/s², kolika je brzina?",new String[]{"30 m/s","3 m/s","10 m/s","90 m/s"},0,"v=gt=30 m/s.");
        add(q,OSNOVNI,"Gravitacija","Kolika je sila teže tela mase 7 kg za g≈10 N/kg?",new String[]{"70 N","17 N","0,7 N","700 N"},0,"Fg=mg=70 N.");
        add(q,OSNOVNI,"Gravitacija","Telo je bačeno vertikalno naviše brzinom 20 m/s. Uz g≈10 m/s², za koliko stiže do najviše tačke?",new String[]{"2 s","1 s","10 s","20 s"},0,"t=v0/g=20/10=2 s.");
        add(q,OSNOVNI,"Gravitacija","Telo slobodno pada iz mirovanja 2 s. Koliki put pređe uz g≈10 m/s²?",new String[]{"20 m","10 m","40 m","5 m"},0,"s=gt²/2=10·4/2=20 m.");
        add(q,OSNOVNI,"Gravitacija","Telo mase 0,5 kg ima težinu približno:",new String[]{"5 N","0,05 N","10,5 N","20 N"},0,"Fg=0,5·10=5 N.");
        add(q,SREDNJI,"Gravitacija","Telo pada iz mirovanja sa visine 45 m. Uz g≈10 m/s², vreme pada je približno:",new String[]{"3 s","4,5 s","9 s","2 s"},0,"h=gt²/2, pa 45=5t², t=3 s.");
        add(q,SREDNJI,"Gravitacija","Lopta je bačena naviše brzinom 30 m/s. Najveća visina iznad mesta izbačaja je približno:",new String[]{"45 m","90 m","30 m","15 m"},0,"h=v0²/(2g)=900/20=45 m.");
        add(q,SREDNJI,"Gravitacija","Telo je bačeno vertikalno naniže brzinom 5 m/s. Kolika je brzina posle 2 s uz g≈10 m/s²?",new String[]{"25 m/s","20 m/s","15 m/s","30 m/s"},0,"v=v0+gt=5+20=25 m/s.");
        add(q,SREDNJI,"Gravitacija","Telo slobodno pada 4 s. Koliki put pređe u tom vremenu uz g≈10 m/s²?",new String[]{"80 m","40 m","160 m","20 m"},0,"s=gt²/2=10·16/2=80 m.");
        add(q,SREDNJI,"Gravitacija","Lopta je bačena naviše brzinom 25 m/s. Kolika je brzina posle 1,5 s uz g≈10 m/s²?",new String[]{"10 m/s naviše","15 m/s naviše","10 m/s naniže","25 m/s naviše"},0,"v=25−10·1,5=10 m/s naviše.");

        add(q,OSNOVNI,"Rad, energija i snaga","Sila 20 N pomeri telo 5 m u svom smeru. Rad je:",new String[]{"100 J","4 J","25 J","400 J"},0,"A=Fs=20·5=100 J.");
        add(q,OSNOVNI,"Rad, energija i snaga","Mašina izvrši 600 J rada za 3 s. Snaga je:",new String[]{"200 W","1800 W","603 W","0,005 W"},0,"P=A/t=600/3=200 W.");
        add(q,OSNOVNI,"Rad, energija i snaga","Telo mase 2 kg podignuto je 5 m. Uz g≈10 N/kg, potencijalna energija je:",new String[]{"100 J","20 J","50 J","10 J"},0,"Ep=mgh=2·10·5=100 J.");
        add(q,OSNOVNI,"Rad, energija i snaga","Telo mase 4 kg kreće se 3 m/s. Kinetička energija je:",new String[]{"18 J","12 J","36 J","6 J"},0,"Ek=mv²/2=4·9/2=18 J.");
        add(q,OSNOVNI,"Rad, energija i snaga","Motor snage 500 W radi 4 s. Koliki rad izvrši?",new String[]{"2000 J","125 J","504 J","20 J"},0,"A=Pt=500·4=2000 J.");
        add(q,SREDNJI,"Rad, energija i snaga","Telo mase 5 kg ubrza sa 2 m/s na 6 m/s. Za koliko se poveća kinetička energija?",new String[]{"80 J","100 J","40 J","160 J"},0,"ΔEk=m(v²−v0²)/2=5(36−4)/2=80 J.");
        add(q,SREDNJI,"Rad, energija i snaga","Dizalica podigne 100 kg na 6 m za 12 s. Uz g≈10 N/kg, snaga je:",new String[]{"500 W","6000 W","50 W","1200 W"},0,"A=mgh=6000 J, P=6000/12=500 W.");
        add(q,SREDNJI,"Rad, energija i snaga","Telo mase 2 kg pada sa visine 20 m bez otpora. Kolika je kinetička energija neposredno pre tla uz g≈10 N/kg?",new String[]{"400 J","200 J","40 J","800 J"},0,"Ep=mgh=400 J prelazi u kinetičku energiju.");
        add(q,SREDNJI,"Rad, energija i snaga","Sila 50 N pomera sanduk 8 m, a trenje obavi rad −120 J. Koliki je ukupni rad?",new String[]{"280 J","400 J","520 J","120 J"},0,"Rad vučne sile je 400 J, ukupno 400−120=280 J.");
        add(q,SREDNJI,"Rad, energija i snaga","Motor izvrši 18 kJ rada za 30 s. Snaga je:",new String[]{"600 W","60 W","540 W","1800 W"},0,"18 kJ=18000 J, P=18000/30=600 W.");

        add(q,OSNOVNI,"Toplotne pojave","Koliko toplote treba za zagrevanje 1 kg vode za 1 °C ako je c≈4200 J/(kg·°C)?",new String[]{"4200 J","420 J","42 J","8400 J"},0,"Q=mcΔT=1·4200·1=4200 J.");
        add(q,OSNOVNI,"Toplotne pojave","Telo primi 2000 J toplote. Ako nema drugih promena, njegova unutrašnja energija se poveća za:",new String[]{"2000 J","1000 J","0 J","4000 J"},0,"Ako nema rada i gubitaka, primljena toplota povećava unutrašnju energiju za isti iznos.");
        add(q,OSNOVNI,"Toplotne pojave","Za m=2 kg, c=500 J/(kg·°C) i ΔT=3 °C, količina toplote je:",new String[]{"3000 J","1000 J","1500 J","6000 J"},0,"Q=2·500·3=3000 J.");
        add(q,OSNOVNI,"Toplotne pojave","Aluminijum mase 0,5 kg ima c≈900 J/(kg·°C). Za porast od 2 °C treba:",new String[]{"900 J","450 J","1800 J","90 J"},0,"Q=0,5·900·2=900 J.");
        add(q,OSNOVNI,"Toplotne pojave","Telo od 1 kg primi 5000 J, a c=500 J/(kg·°C). Porast temperature je:",new String[]{"10 °C","5 °C","2,5 °C","50 °C"},0,"ΔT=Q/(mc)=5000/500=10 °C.");
        add(q,SREDNJI,"Toplotne pojave","2 kg vode se zagreva za 5 °C. Uz c≈4200 J/(kg·°C), potrebna toplota je:",new String[]{"42 kJ","4,2 kJ","84 kJ","21 kJ"},0,"Q=2·4200·5=42000 J=42 kJ.");
        add(q,SREDNJI,"Toplotne pojave","Metal mase 0,4 kg primi 3600 J i zagreje se za 10 °C. Specifični toplotni kapacitet je:",new String[]{"900 J/(kg·°C)","144 J/(kg·°C)","360 J/(kg·°C)","90 J/(kg·°C)"},0,"c=Q/(mΔT)=3600/(0,4·10)=900.");
        add(q,SREDNJI,"Toplotne pojave","Grejač snage 1000 W idealno greje 2 kg vode 42 s. Koliki je približan porast temperature, c=4200 J/(kg·°C)?",new String[]{"5 °C","10 °C","2 °C","20 °C"},0,"Q=Pt=42000 J, ΔT=42000/(2·4200)=5 °C.");
        add(q,SREDNJI,"Toplotne pojave","0,5 kg bakra, c≈400 J/(kg·°C), ohladi se za 20 °C. Koliko toplote preda?",new String[]{"4000 J","2000 J","8000 J","400 J"},0,"|Q|=mcΔT=0,5·400·20=4000 J.");
        add(q,SREDNJI,"Toplotne pojave","Telo mase 3 kg, c=800 J/(kg·°C), primi 24 kJ. Za koliko poraste temperatura?",new String[]{"10 °C","8 °C","24 °C","30 °C"},0,"ΔT=24000/(3·800)=10 °C.");

        add(q,OSNOVNI,"Oscilacije, talasi i svetlost","Telo napravi 20 oscilacija za 10 s. Frekvencija je:",new String[]{"2 Hz","0,5 Hz","20 Hz","200 Hz"},0,"f=N/t=20/10=2 Hz.");
        add(q,OSNOVNI,"Oscilacije, talasi i svetlost","Frekvencija je 5 Hz. Period je:",new String[]{"0,2 s","5 s","2 s","25 s"},0,"T=1/f=0,2 s.");
        add(q,OSNOVNI,"Oscilacije, talasi i svetlost","Talas ima λ=2 m i f=3 Hz. Brzina je:",new String[]{"6 m/s","1,5 m/s","5 m/s","0,67 m/s"},0,"v=λf=2·3=6 m/s.");
        add(q,OSNOVNI,"Oscilacije, talasi i svetlost","Zvuk brzine 340 m/s ima frekvenciju 170 Hz. Talasna dužina je:",new String[]{"2 m","0,5 m","510 m","170 m"},0,"λ=v/f=340/170=2 m.");
        add(q,OSNOVNI,"Oscilacije, talasi i svetlost","Predmet je 2 m ispred ravnog ogledala. Lik je iza ogledala na rastojanju:",new String[]{"2 m","1 m","4 m","0 m"},0,"Kod ravnog ogledala lik je jednako udaljen iza ogledala kao predmet ispred.");
        add(q,SREDNJI,"Oscilacije, talasi i svetlost","Talas pređe 24 m za 3 s, a frekvencija mu je 4 Hz. Talasna dužina je:",new String[]{"2 m","8 m","6 m","1 m"},0,"v=24/3=8 m/s, λ=v/f=8/4=2 m.");
        add(q,SREDNJI,"Oscilacije, talasi i svetlost","Izvor napravi 150 oscilacija za 30 s. Koliki je period?",new String[]{"0,2 s","5 s","0,5 s","30 s"},0,"f=150/30=5 Hz, T=1/5=0,2 s.");
        add(q,SREDNJI,"Oscilacije, talasi i svetlost","Eho se čuje 0,4 s nakon zvuka. Uz v=340 m/s, udaljenost prepreke je:",new String[]{"68 m","136 m","85 m","34 m"},0,"Zvuk prelazi dvostruki put: d=vt/2=340·0,4/2=68 m.");
        add(q,SREDNJI,"Oscilacije, talasi i svetlost","Talasna dužina je 0,5 m, a brzina 10 m/s. Frekvencija je:",new String[]{"20 Hz","5 Hz","10 Hz","0,05 Hz"},0,"f=v/λ=10/0,5=20 Hz.");
        add(q,SREDNJI,"Oscilacije, talasi i svetlost","Predmet i njegov lik u ravnom ogledalu međusobno su udaljeni 6 m. Koliko je predmet udaljen od ogledala?",new String[]{"3 m","6 m","12 m","1,5 m"},0,"Predmet i lik su simetrični u odnosu na ogledalo, pa je svako na 3 m.");

        add(q,OSNOVNI,"Elektricitet","Napon je 12 V, a otpor 4 Ω. Struja je:",new String[]{"3 A","48 A","8 A","0,33 A"},0,"I=U/R=12/4=3 A.");
        add(q,OSNOVNI,"Elektricitet","Kroz otpornik teče 2 A pri naponu 10 V. Otpor je:",new String[]{"5 Ω","20 Ω","12 Ω","0,2 Ω"},0,"R=U/I=10/2=5 Ω.");
        add(q,OSNOVNI,"Elektricitet","Uređaj radi na 6 V i vuče 2 A. Snaga je:",new String[]{"12 W","3 W","8 W","24 W"},0,"P=UI=6·2=12 W.");
        add(q,OSNOVNI,"Elektricitet","Struja 0,5 A protiče 20 s. Količina naelektrisanja je:",new String[]{"10 C","40 C","0,025 C","20,5 C"},0,"q=It=0,5·20=10 C.");
        add(q,OSNOVNI,"Elektricitet","Dva otpornika 2 Ω i 3 Ω vezana su redno. Ukupan otpor je:",new String[]{"5 Ω","6 Ω","1 Ω","2,5 Ω"},0,"Kod redne veze otpori se sabiraju.");
        add(q,SREDNJI,"Elektricitet","Otpornici 6 Ω i 3 Ω vezani su paralelno. Ekvivalentni otpor je:",new String[]{"2 Ω","9 Ω","3 Ω","18 Ω"},0,"1/R=1/6+1/3=1/2, pa R=2 Ω.");
        add(q,SREDNJI,"Elektricitet","Grejač snage 1200 W radi 5 min. Koliko energije utroši?",new String[]{"360 kJ","6 kJ","240 kJ","6000 kJ"},0,"t=300 s; E=Pt=1200·300=360000 J=360 kJ.");
        add(q,SREDNJI,"Elektricitet","Na otporniku 8 Ω teče 1,5 A. Napon je:",new String[]{"12 V","5,33 V","9,5 V","16 V"},0,"U=RI=8·1,5=12 V.");
        add(q,SREDNJI,"Elektricitet","Uređaj snage 100 W radi na 20 V. Struja je:",new String[]{"5 A","2 A","0,2 A","2000 A"},0,"I=P/U=100/20=5 A.");
        add(q,SREDNJI,"Elektricitet","Kroz uređaj pri naponu 230 V teče 2 A tokom 10 s. Utrošena energija je:",new String[]{"4600 J","460 J","2300 J","9200 J"},0,"E=UIt=230·2·10=4600 J.");

        return q;
    }
}
