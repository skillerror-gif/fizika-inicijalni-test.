package rs.fizika.inicijalnitest;

import java.util.ArrayList;
import java.util.List;

public final class QuestionBank {
    private QuestionBank() {}

    private static void add(List<Question> q, String a, String t, String[] o, int c, String e) {
        q.add(new Question(a,t,o,c,e));
    }

    public static List<Question> all() {
        ArrayList<Question> q = new ArrayList<>();

        add(q,"Kretanje","Ubrzanje je fizička veličina koja opisuje:",new String[]{"promenu brzine u vremenu","pređeni put","masu tela","silu po površini"},0,"Ubrzanje opisuje koliko se brzina promeni u jedinici vremena.");
        add(q,"Kretanje","SI jedinica za ubrzanje je:",new String[]{"m/s","m/s²","N","J"},1,"SI jedinica za ubrzanje je m/s².");
        add(q,"Kretanje","Kod ravnomerno ubrzanog pravolinijskog kretanja ubrzanje je:",new String[]{"konstantno","jednako nuli","uvek promenljivo","jednako putu"},0,"Kod ravnomerno ubrzanog kretanja ubrzanje je konstantno.");
        add(q,"Kretanje","Telo polazi iz mirovanja i ravnomerno ubrzava. Njegova brzina tokom vremena:",new String[]{"raste ravnomerno","opada ravnomerno","ostaje ista","odmah postaje nula"},0,"Pri konstantnom pozitivnom ubrzanju brzina raste ravnomerno.");
        add(q,"Kretanje","Ako telo usporava ravnomerno, intenzitet njegove brzine:",new String[]{"smanjuje se za jednake iznose u jednakim intervalima","uvek raste","ne menja se","mora biti nula"},0,"Ravnomerno usporavanje znači jednaku promenu brzine u jednakim vremenskim intervalima.");
        add(q,"Kretanje","Brzina tela se promeni sa 4 m/s na 10 m/s za 3 s. Ubrzanje je:",new String[]{"2 m/s²","3 m/s²","6 m/s²","14 m/s²"},0,"a=(10−4)/3=2 m/s².");
        add(q,"Kretanje","Telo se kreće 12 m/s i usporava 3 m/s². Za koliko se zaustavlja?",new String[]{"2 s","4 s","9 s","36 s"},1,"t=v/a=12/3=4 s.");
        add(q,"Kretanje","Grafik brzine u funkciji vremena kod stalnog ubrzanja je:",new String[]{"prava linija","kružnica","uvek horizontalna linija","parabola uvek"},0,"Za v=v0+at zavisnost brzine od vremena je linearna.");
        add(q,"Kretanje","Površina ispod v–t grafika predstavlja:",new String[]{"pređeni put/pomeraj","ubrzanje","masu","silu"},0,"Površina ispod grafika brzine daje pomeraj, odnosno put kada nema promene smera.");
        add(q,"Kretanje","Ako je ubrzanje jednako nuli, telo može:",new String[]{"mirovati ili se kretati stalnom brzinom","samo ubrzavati","samo padati","imati promenljivu brzinu"},0,"a=0 znači da se vektor brzine ne menja.");

        add(q,"Sila i Njutnovi zakoni","Prvi Njutnov zakon je zakon:",new String[]{"inercije","gravitacije","održanja energije","pritiska"},0,"Prvi Njutnov zakon opisuje inerciju.");
        add(q,"Sila i Njutnovi zakoni","Drugi Njutnov zakon može se zapisati kao:",new String[]{"F=ma","F=mv","F=pS","F=A/t"},0,"Rezultujuća sila jednaka je proizvodu mase i ubrzanja.");
        add(q,"Sila i Njutnovi zakoni","Ako je rezultanta sila na telo jednaka nuli, ubrzanje je:",new String[]{"0","g","1 m/s²","zavisi samo od brzine"},0,"Iz ΣF=ma sledi a=0 kada je rezultanta sila nula.");
        add(q,"Sila i Njutnovi zakoni","Sile akcije i reakcije prema trećem Njutnovom zakonu:",new String[]{"jednake su po intenzitetu i suprotnih smerova","deluju na isto telo","uvek se poništavaju na jednom telu","imaju različite intenzitete"},0,"Akcija i reakcija deluju na različita tela, jednake su i suprotne.");
        add(q,"Sila i Njutnovi zakoni","Inercija tela je veća kada je:",new String[]{"masa veća","brzina nula","zapremina manja","temperatura viša"},0,"Masa je mera inertnosti tela.");
        add(q,"Sila i Njutnovi zakoni","Na telo mase 5 kg deluje rezultujuća sila 15 N. Ubrzanje je:",new String[]{"3 m/s²","5 m/s²","15 m/s²","75 m/s²"},0,"a=F/m=15/5=3 m/s².");
        add(q,"Sila i Njutnovi zakoni","Sila trenja klizanja usmerena je:",new String[]{"suprotno relativnom klizanju","uvek vertikalno naviše","uvek u smeru kretanja","ka centru Zemlje"},0,"Trenje se suprotstavlja relativnom klizanju dodirnih površina.");
        add(q,"Sila i Njutnovi zakoni","Ako se pri istoj sili masa tela udvostruči, ubrzanje se:",new String[]{"prepolovi","udvostruči","ne menja","učetvorostruči"},0,"Iz a=F/m sledi da veća masa daje manje ubrzanje.");
        add(q,"Sila i Njutnovi zakoni","Težina tela je sila kojom telo usled gravitacije deluje na:",new String[]{"podlogu ili vešanje","Zemlju isključivo","drugo naelektrisano telo","magnet"},0,"Težina je sila kojom telo deluje na oslonac ili vešanje.");
        add(q,"Sila i Njutnovi zakoni","Telo se kreće stalnom brzinom po pravoj liniji. Rezultanta sila je:",new String[]{"nula","jednaka težini","uvek veća od nule","jednaka brzini"},0,"Stalna brzina znači a=0, pa je rezultanta sila nula.");

        add(q,"Gravitacija","Sila teže u blizini Zemljine površine računa se približno:",new String[]{"Fg=mg","Fg=m/g","Fg=g/m","Fg=mv"},0,"Sila teže je Fg=mg.");
        add(q,"Gravitacija","U slobodnom padu bez otpora vazduha sva tela imaju:",new String[]{"isto ubrzanje g","istu masu","istu brzinu u svakom trenutku bez obzira na početne uslove","istu težinu"},0,"Ubrzanje slobodnog pada ne zavisi od mase tela.");
        add(q,"Gravitacija","U najvišoj tački vertikalnog hica naviše brzina je:",new String[]{"0","g","maksimalna","beskonačna"},0,"U najvišoj tački trenutna brzina je nula.");
        add(q,"Gravitacija","U najvišoj tački vertikalnog hica naviše ubrzanje je:",new String[]{"g naniže","0","g naviše","zavisi od mase"},0,"Gravitaciono ubrzanje i dalje deluje naniže.");
        add(q,"Gravitacija","Za računanje u školskim zadacima često se uzima:",new String[]{"g≈10 m/s²","g≈1 m/s²","g≈100 m/s²","g≈0,1 m/s²"},0,"Često se koristi približna vrednost g≈10 m/s².");
        add(q,"Gravitacija","Telo slobodno pada 2 s, uz g≈10 m/s². Brzina je približno:",new String[]{"20 m/s","5 m/s","10 m/s","40 m/s"},0,"v=gt=10·2=20 m/s.");
        add(q,"Gravitacija","Telo bačeno horizontalno istovremeno:",new String[]{"kreće se horizontalno i slobodno pada","ima samo horizontalno kretanje","ima samo vertikalno kretanje","nema ubrzanje"},0,"Horizontalni hitac je složeno kretanje: horizontalno ravnomerno i vertikalno ubrzano.");
        add(q,"Gravitacija","Ako zanemarimo otpor vazduha, vreme pada sa iste visine:",new String[]{"ne zavisi od mase tela","proporcionalno je masi","obrnuto je proporcionalno masi","uvek je 1 s"},0,"U vakuumu masa ne utiče na ubrzanje slobodnog pada.");
        add(q,"Gravitacija","Sila kojom Zemlja privlači telo usmerena je približno:",new String[]{"ka centru Zemlje","horizontalno","od Zemlje","u smeru brzine"},0,"Gravitaciona sila je usmerena ka centru Zemlje.");
        add(q,"Gravitacija","Pri slobodnom padu iz mirovanja brzina tela:",new String[]{"raste ravnomerno","ostaje ista","opada ravnomerno","menja se nasumično"},0,"Za stalno g brzina raste linearno sa vremenom.");

        add(q,"Ravnoteža","Telo je u translacionoj ravnoteži kada je:",new String[]{"zbir svih sila jednak nuli","brzina uvek nula","masa jednaka nuli","samo jedna sila prisutna"},0,"Uslov translacione ravnoteže je ΣF=0.");
        add(q,"Ravnoteža","Moment sile zavisi od sile i:",new String[]{"kraka sile","mase vazduha","temperature","zapremine"},0,"Moment sile je proizvod sile i njenog kraka u jednostavnom slučaju.");
        add(q,"Ravnoteža","SI jedinica za moment sile je:",new String[]{"N·m","J/s","Pa","kg/m³"},0,"Moment sile se meri u njutn-metrima.");
        add(q,"Ravnoteža","Poluga je u ravnoteži kada su momenti sila oko oslonca:",new String[]{"jednaki po intenzitetu i suprotnih smerova obrtanja","uvek nula pojedinačno","proporcionalni masama bez krakova","jednaki brzinama"},0,"Za ravnotežu poluge važi F1·l1=F2·l2.");
        add(q,"Ravnoteža","Težište tela je tačka u kojoj možemo smatrati da deluje:",new String[]{"ukupna sila teže","sila trenja","samo elastična sila","električna sila"},0,"Za mnoge proračune ukupna sila teže može se smatrati da deluje u težištu.");
        add(q,"Ravnoteža","Stabilnost tela se povećava kada je težište:",new String[]{"niže","više","izvan oslonca","bez mase"},0,"Niže težište uglavnom povećava stabilnost.");
        add(q,"Ravnoteža","Telo će se prevrnuti kada vertikala kroz težište:",new String[]{"izađe iz površine oslonca","prođe kroz sredinu oslonca","poklopi se sa silom teže","bude kraća"},0,"Kada linija dejstva težine izađe van oslonca, telo se prevrće.");
        add(q,"Ravnoteža","Na poluzi sila 20 N deluje na kraku 0,5 m. Moment je:",new String[]{"10 N·m","40 N·m","20 N·m","0,025 N·m"},0,"M=F·l=20·0,5=10 N·m.");
        add(q,"Ravnoteža","Ako se krak sile udvostruči uz istu silu, moment sile se:",new String[]{"udvostruči","prepolovi","ne menja","učetvorostruči"},0,"M=F·l, pa je moment proporcionalan kraku.");
        add(q,"Ravnoteža","Vaga sa jednakim kracima je u ravnoteži kada su:",new String[]{"momenti težina jednaki","brzine tela jednake","zapremine jednake","temperature jednake"},0,"Ravnoteža nastaje kada su momenti sila oko oslonca jednaki.");

        add(q,"Rad, energija i snaga","Mehanički rad pri sili u smeru kretanja je:",new String[]{"A=Fs","A=F/s","A=s/F","A=Pt²"},0,"Za silu paralelnu pomeranju važi A=Fs.");
        add(q,"Rad, energija i snaga","SI jedinica za rad i energiju je:",new String[]{"džul","vat","paskal","njutn"},0,"Rad i energija mere se u džulima.");
        add(q,"Rad, energija i snaga","Snaga se računa kao:",new String[]{"P=A/t","P=At","P=t/A","P=F/S"},0,"Snaga je rad izvršen u jedinici vremena.");
        add(q,"Rad, energija i snaga","SI jedinica za snagu je:",new String[]{"vat","džul","njutn","amper"},0,"Snaga se meri u vatima.");
        add(q,"Rad, energija i snaga","Kinetička energija zavisi od:",new String[]{"mase i kvadrata brzine","samo visine","pritiska i zapremine","napona i struje"},0,"Ek=mv²/2.");
        add(q,"Rad, energija i snaga","Gravitaciona potencijalna energija blizu Zemlje je:",new String[]{"Ep=mgh","Ep=mv","Ep=Fs/t","Ep=U/I"},0,"Ep=mgh u odnosu na izabrani nulti nivo.");
        add(q,"Rad, energija i snaga","Ako se brzina tela udvostruči, kinetička energija se:",new String[]{"učetvorostruči","udvostruči","prepolovi","ne menja"},0,"Kinetička energija je proporcionalna kvadratu brzine.");
        add(q,"Rad, energija i snaga","Zakon održanja mehaničke energije važi kada možemo zanemariti:",new String[]{"trenje i druge disipativne sile","gravitaciju","masu","brzinu"},0,"Bez disipacije zbir kinetičke i potencijalne energije ostaje stalan.");
        add(q,"Rad, energija i snaga","Mašina izvrši 600 J rada za 3 s. Snaga je:",new String[]{"200 W","1800 W","603 W","0,005 W"},0,"P=A/t=600/3=200 W.");
        add(q,"Rad, energija i snaga","Ako telo miruje na visini, može imati:",new String[]{"gravitacionu potencijalnu energiju","kinetičku energiju zbog mirovanja","uvek nultu energiju","samo električnu energiju"},0,"Položaj u gravitacionom polju može dati potencijalnu energiju.");

        add(q,"Toplotne pojave","Temperatura opisuje:",new String[]{"stepen zagrejanosti tela","količinu materije","električni naboj","samo zapreminu"},0,"Temperatura je mera termičkog stanja tela.");
        add(q,"Toplotne pojave","Unutrašnja energija tela povezana je sa:",new String[]{"kretanjem i međudelovanjem čestica","samo položajem tela u prostoriji","samo bojom tela","električnim otporom isključivo"},0,"Unutrašnja energija obuhvata mikroskopske kinetičke i potencijalne energije čestica.");
        add(q,"Toplotne pojave","Toplota spontano prelazi sa:",new String[]{"toplijeg na hladnije telo","hladnijeg na toplije bez drugih promena","tela manje mase na veće mase","tela manje zapremine na veću"},0,"Bez spoljnog rada toplota prelazi sa višeg na niži nivo temperature.");
        add(q,"Toplotne pojave","Količina toplote pri zagrevanju bez promene agregatnog stanja je:",new String[]{"Q=mcΔT","Q=mgh","Q=Fs","Q=UI"},0,"Za zagrevanje važi Q=mcΔT.");
        add(q,"Toplotne pojave","Specifični toplotni kapacitet pokazuje koliko toplote treba da se:",new String[]{"1 kg supstance zagreje za 1 °C","1 m supstance pomeri 1 m","1 C naboja prenese","1 kg tela ubrza"},0,"To je definicija specifičnog toplotnog kapaciteta.");
        add(q,"Toplotne pojave","Pri topljenju čiste kristalne supstance na stalnom pritisku temperatura:",new String[]{"ostaje približno stalna tokom promene faze","uvek raste","uvek opada","postaje 0 K"},0,"Dovedena toplota troši se na promenu agregatnog stanja.");
        add(q,"Toplotne pojave","Prenos toplote strujanjem fluida naziva se:",new String[]{"konvekcija","provođenje","zračenje","difrakcija"},0,"Konvekcija je prenos toplote kretanjem fluida.");
        add(q,"Toplotne pojave","Sunčeva energija do Zemlje najvećim delom stiže:",new String[]{"zračenjem","provođenjem kroz vakuum","konvekcijom kroz vakuum","trenjem"},0,"Elektromagnetno zračenje se može prostirati kroz vakuum.");
        add(q,"Toplotne pojave","Dobar toplotni provodnik je:",new String[]{"bakar","vuna","stiropor","vazduh u mirovanju"},0,"Metali poput bakra dobro provode toplotu.");
        add(q,"Toplotne pojave","Ako dva tela različitih temperatura dovedemo u toplotni kontakt, ravnoteža nastupa kada:",new String[]{"imaju istu temperaturu","imaju istu masu","imaju istu zapreminu","prestane gravitacija"},0,"Toplotna ravnoteža znači jednake temperature.");

        add(q,"Oscilacije, talasi i zvuk","Period oscilovanja je vreme potrebno za:",new String[]{"jednu potpunu oscilaciju","deset oscilacija uvek","promenu mase","jedan metar puta"},0,"Period je trajanje jednog ciklusa.");
        add(q,"Oscilacije, talasi i zvuk","Frekvencija predstavlja broj oscilacija u:",new String[]{"jedinici vremena","jedinici dužine","jedinici mase","jedinici sile"},0,"f=N/t.");
        add(q,"Oscilacije, talasi i zvuk","SI jedinica za frekvenciju je:",new String[]{"herc","sekunda","metar","vat"},0,"Frekvencija se meri u hercima.");
        add(q,"Oscilacije, talasi i zvuk","Između perioda i frekvencije važi:",new String[]{"f=1/T","f=T","f=T²","f=T/2"},0,"Period i frekvencija su recipročne veličine.");
        add(q,"Oscilacije, talasi i zvuk","Talas prenosi kroz sredinu prvenstveno:",new String[]{"energiju","čestice sredine trajno sa talasom","masu izvora","temperaturu bez energije"},0,"Talasi prenose energiju, dok čestice sredine osciluju oko ravnotežnih položaja.");
        add(q,"Oscilacije, talasi i zvuk","Brzina talasa povezana je sa talasnom dužinom i frekvencijom:",new String[]{"v=λf","v=λ/f","v=f/λ","v=λ+f"},0,"Osnovna talasna relacija je v=λf.");
        add(q,"Oscilacije, talasi i zvuk","Visina tona prvenstveno zavisi od:",new String[]{"frekvencije","amplitude","boje izvora","mase slušaoca"},0,"Veća frekvencija odgovara višem tonu.");
        add(q,"Oscilacije, talasi i zvuk","Jačina zvuka je povezana sa:",new String[]{"amplitudom oscilovanja","samo frekvencijom","samo periodom","talasnom dužinom isključivo"},0,"Veća amplituda uglavnom znači intenzivniji zvuk.");
        add(q,"Oscilacije, talasi i zvuk","Zvuk se kroz vakuum:",new String[]{"ne prostire","prostire brže nego kroz čelik","prostire brzinom svetlosti","prostire samo noću"},0,"Zvuk je mehanički talas i potrebna mu je materijalna sredina.");
        add(q,"Oscilacije, talasi i zvuk","Eho nastaje zbog:",new String[]{"odbijanja zvuka","prelamanja svetlosti","električne struje","magnetizacije"},0,"Eho je posledica refleksije zvučnog talasa.");

        add(q,"Svetlosne pojave","U homogenoj providnoj sredini svetlost se prostire približno:",new String[]{"pravolinijski","kružno","nasumično","uvek vertikalno"},0,"Pravolinijsko prostiranje objašnjava senku i polusenku.");
        add(q,"Svetlosne pojave","Zakon odbijanja svetlosti kaže da je ugao odbijanja:",new String[]{"jednak uglu upada","dvostruko veći","uvek 90°","uvek nula"},0,"Uglovi se mere prema normali i međusobno su jednaki.");
        add(q,"Svetlosne pojave","Lik u ravnom ogledalu je:",new String[]{"virtuelan, uspravan i iste veličine","realan i obrnut","uvek uvećan","uvek umanjen"},0,"Ravno ogledalo daje virtuelan, uspravan lik jednake veličine.");
        add(q,"Svetlosne pojave","Prelamanje svetlosti nastaje pri:",new String[]{"prelasku svetlosti iz jedne sredine u drugu","odbijanju od ogledala isključivo","zagrevanju provodnika","magnetizaciji gvožđa"},0,"Promena brzine svetlosti na granici sredina menja njen pravac.");
        add(q,"Svetlosne pojave","Sabirno sočivo je u sredini:",new String[]{"deblje nego na krajevima","tanje nego na krajevima","uvek ravno","neprovidno"},0,"Tipično sabirno sočivo je deblje u sredini.");
        add(q,"Svetlosne pojave","Rasejavanje bele svetlosti na boje naziva se:",new String[]{"disperzija","indukcija","konvekcija","rezonanca"},0,"Disperzija nastaje jer indeks prelamanja zavisi od talasne dužine.");
        add(q,"Svetlosne pojave","Žižna daljina sočiva meri rastojanje između:",new String[]{"optičkog centra i žiže","predmeta i oka","dva ogledala","dve boje spektra"},0,"Žižna daljina je rastojanje od optičkog centra do žiže.");
        add(q,"Svetlosne pojave","Kod sabirnog sočiva paralelni zraci posle prelamanja:",new String[]{"skupljaju se u žiži","postaju paralelni bez promene","uvek se vraćaju nazad","nestaju"},0,"Sabirno sočivo fokusira paralelne zrake.");
        add(q,"Svetlosne pojave","Kratkovidost se najčešće koriguje:",new String[]{"rasipnim sočivom","sabirnim sočivom","ravnim ogledalom","prizmom"},0,"Rasipno sočivo pomera žižu unazad do mrežnjače.");
        add(q,"Svetlosne pojave","Duga je povezana sa:",new String[]{"prelamanjem, odbijanjem i disperzijom svetlosti u kapljicama","samo magnetnim poljem","samo zvukom","električnim otporom"},0,"Kapljice vode prelamaju, odbijaju i razlažu Sunčevu svetlost.");

        add(q,"Elektricitet","Postoje dve vrste električnog naboja:",new String[]{"pozitivan i negativan","severni i južni","topao i hladan","lak i težak"},0,"Električni naboj se javlja kao pozitivan i negativan.");
        add(q,"Elektricitet","Istoimeni električni naboji se:",new String[]{"odbijaju","privlače","poništavaju bez kontakta","ne međudeliju"},0,"Istoimeni naboji se odbijaju, raznoimeni privlače.");
        add(q,"Elektricitet","SI jedinica za električni naboj je:",new String[]{"kulon","amper","volt","om"},0,"Naboj se meri u kulonima.");
        add(q,"Elektricitet","Jačina električne struje meri se u:",new String[]{"amperima","voltima","omima","džulima"},0,"SI jedinica struje je amper.");
        add(q,"Elektricitet","Električni napon meri se u:",new String[]{"voltima","amperima","vatima","teslama"},0,"Napon se meri u voltima.");
        add(q,"Elektricitet","Električni otpor meri se u:",new String[]{"omima","kulonima","džulima","njutnima"},0,"SI jedinica otpora je om.");
        add(q,"Elektricitet","Omov zakon za deo kola je:",new String[]{"U=RI","U=R/I","I=UR","R=UI"},0,"Za ohmski provodnik važi U=RI.");
        add(q,"Elektricitet","U rednoj vezi potrošača jačina struje je:",new String[]{"ista kroz sve elemente","najveća na prvom","nula na poslednjem","uvek različita"},0,"Kroz sve elemente redne veze protiče ista struja.");
        add(q,"Elektricitet","U paralelnoj vezi napon na granama je:",new String[]{"isti","uvek nula","proporcionalan masi","uvek različit"},0,"Sve paralelne grane priključene su na iste dve tačke kola.");
        add(q,"Elektricitet","Električna snaga može se računati:",new String[]{"P=UI","P=U/I","P=I/U","P=R/U"},0,"Električna snaga je proizvod napona i struje.");

        add(q,"Magnetizam i struktura materije","Magnet ima dva pola koja nazivamo:",new String[]{"severni i južni","pozitivni i negativni","gornji i donji","levi i desni"},0,"Magnetni polovi su severni i južni.");
        add(q,"Magnetizam i struktura materije","Istoimeni magnetni polovi se:",new String[]{"odbijaju","privlače","uvek poništavaju","ne međudeliju"},0,"Istoimeni polovi se odbijaju.");
        add(q,"Magnetizam i struktura materije","Oko provodnika kroz koji teče struja postoji:",new String[]{"magnetno polje","samo gravitaciono polje","vakuum","nulta energija"},0,"Električna struja stvara magnetno polje.");
        add(q,"Magnetizam i struktura materije","Elektromagnet može da se napravi pomoću:",new String[]{"kalema sa strujom i feromagnetnog jezgra","staklene prizme","ravnog ogledala","termometra"},0,"Kalem sa strujom stvara polje, a gvozdeno jezgro ga pojačava.");
        add(q,"Magnetizam i struktura materije","Atom se sastoji od:",new String[]{"jezgra i elektronskog omotača","samo protona","samo neutrona","molekula i talasa"},0,"Jezgro sadrži protone i neutrone, a elektroni čine omotač.");
        add(q,"Magnetizam i struktura materije","Proton ima:",new String[]{"pozitivan naboj","negativan naboj","nema naboj","promenljiv naboj"},0,"Proton nosi pozitivan elementarni naboj.");
        add(q,"Magnetizam i struktura materije","Neutron je:",new String[]{"električno neutralan","pozitivno naelektrisan","negativno naelektrisan","uvek izvan jezgra"},0,"Neutron nema neto električni naboj.");
        add(q,"Magnetizam i struktura materije","Atomski broj elementa jednak je broju:",new String[]{"protona u jezgru","neutrona uvek","svih nukleona","elektrona samo kod jona"},0,"Atomski broj Z definiše broj protona.");
        add(q,"Magnetizam i struktura materije","Jon nastaje kada atom:",new String[]{"izgubi ili primi elektron","promeni broj protona u hemijskoj reakciji","izgubi svu masu","prestane da postoji"},0,"Gubitak ili prijem elektrona daje neto naboj.");
        add(q,"Magnetizam i struktura materije","Odgovorno korišćenje izvora energije podrazumeva:",new String[]{"smanjenje nepotrebne potrošnje i zagađenja","uvek veću potrošnju","zanemarivanje otpada","isključivo fosilna goriva"},0,"Energetska efikasnost i smanjenje zagađenja su važni za zaštitu životne sredine.");

        return q;
    }
}
