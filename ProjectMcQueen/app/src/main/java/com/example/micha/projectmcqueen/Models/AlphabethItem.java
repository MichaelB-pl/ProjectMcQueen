package com.example.micha.projectmcqueen.Models;

import com.example.micha.projectmcqueen.R;

/**
 * Created by micha on 03.10.2017.
 */

public class AlphabethItem {
    public final String Letter;

    public final String FirstImageName;
    public final String SecondImageName;
    public final String ThirdImageName;
    public final String FourthImageName;
    public final String FifthImageName;

    public final int FirstImageResId;
    public final int SecondImageResId;
    public final int ThirdImageResId;
    public final int FourthImageResId;
    public final int FifthImageResId;

    public AlphabethItem(String letter,
                         String firstImageName, String secondImageName, String thirdImageName, String fourthImageName, String fifthImageName,
                         int firstImageResId, int secondImageResId, int thirdImageResId, int fourthImageResId, int fifthImageResId) {
        Letter = letter;

        FirstImageName = firstImageName;
        SecondImageName = secondImageName;
        ThirdImageName = thirdImageName;
        FourthImageName = fourthImageName;
        FifthImageName = fifthImageName;

        FirstImageResId = firstImageResId;
        SecondImageResId = secondImageResId;
        ThirdImageResId = thirdImageResId;
        FourthImageResId = fourthImageResId;
        FifthImageResId = fifthImageResId;
    }

    public static AlphabethItem[] GetAlphabeth() {
        AlphabethItem[] alphabeth = new AlphabethItem[]{
                new AlphabethItem("A",
                        "Agrafka", "Ananas", "Aparat", "Arbuz", "Autobus",
                        R.drawable.agrafka, R.drawable.ananas, R.drawable.aparat, R.drawable.arbuz, R.drawable.autobus),
                new AlphabethItem("B",
                        "Balon", "Banan", "Biedronka", "Bocian", "Butelka",
                        R.drawable.balon, R.drawable.banan, R.drawable.biedronka, R.drawable.bocian, R.drawable.butelka),
                new AlphabethItem("C",
                        "Chmura", "Ciężarówka", "Cytryna", "Czapka", "Czapla",
                        R.drawable.chmura, R.drawable.ciezarowka, R.drawable.cytryna, R.drawable.czapka, R.drawable.czapla),
                new AlphabethItem("D",
                        "Delfin", "Dom", "Drzewo", "Dynia", "Dźwig",
                        R.drawable.delfin, R.drawable.dom, R.drawable.drzewo, R.drawable.dynia, R.drawable.dzwig),
                new AlphabethItem("E",
                        "Ekran", "Emu", "", "", "",
                        R.drawable.ekran, R.drawable.emu, R.drawable.transparent_rectangle, R.drawable.transparent_rectangle, R.drawable.transparent_rectangle),
                new AlphabethItem("F",
                        "Fala", "Flet", "Foka", "Fortepian", "Frytki",
                        R.drawable.fala, R.drawable.flet, R.drawable.foka, R.drawable.fortepian, R.drawable.frytki),
                new AlphabethItem("G",
                        "Gepard", "Gołąb", "Grabie", "Gruszka", "Grzyb",
                        R.drawable.gepard, R.drawable.golab, R.drawable.grabie, R.drawable.gruszka, R.drawable.grzyb),
                new AlphabethItem("H",
                        "Hak", "Helikopter", "Hipopotam", "Hulajnoga", "Huśtawka",
                        R.drawable.hak, R.drawable.helikopter, R.drawable.hipopotam, R.drawable.hulajnoga, R.drawable.hustawka),
                new AlphabethItem("I",
                        "Iglo", "Igła", "Imbir", "Indyk", "",
                        R.drawable.iglo, R.drawable.igla, R.drawable.imbir, R.drawable.indyk, R.drawable.transparent_rectangle),
                new AlphabethItem("J",
                        "Jabłko", "Jajko", "Jeleń", "Jeż", "Język",
                        R.drawable.jablko, R.drawable.jajko, R.drawable.jelen, R.drawable.jez, R.drawable.jezyk),
                new AlphabethItem("K",
                        "Kawa", "Kiwi", "Komputer", "Kot", "Książka",
                        R.drawable.kawa, R.drawable.kiwi, R.drawable.komputer, R.drawable.kot, R.drawable.ksiazka),
                new AlphabethItem("L",
                        "Lampka", "Laptop", "Latarka", "Lew", "Lis",
                        R.drawable.lampka, R.drawable.laptop, R.drawable.latarka, R.drawable.lew, R.drawable.lis),
                new AlphabethItem("Ł",
                        "Łabędź", "Łopatka", "Łoś", "Łódka", "Łyżka",
                        R.drawable.labedz, R.drawable.lopatka, R.drawable.los, R.drawable.lodka, R.drawable.lyzka),
                new AlphabethItem("M",
                        "Malina", "Małpka", "Marchewka", "Motor", "Motyl",
                        R.drawable.malina, R.drawable.malpka, R.drawable.marchewka, R.drawable.motor, R.drawable.motyl),
                new AlphabethItem("N",
                        "Niedźwiedź", "Nietoperz", "Nosorożec", "Nożyczki", "Nuta",
                        R.drawable.niedzwiedz, R.drawable.nietoperz, R.drawable.nosorozec, R.drawable.nozyczki, R.drawable.nuta),
                new AlphabethItem("O",
                        "Okulary", "Ołówek", "Osiołek", "Ośmiornica", "Owca",
                        R.drawable.okulary, R.drawable.olowek, R.drawable.osiolek, R.drawable.osmiornica, R.drawable.owca),
                new AlphabethItem("P",
                        "Pingwin", "Pociąg", "Poduszka", "Pomarańcza", "Pomidor",
                        R.drawable.pingwin, R.drawable.pociag, R.drawable.poduszka, R.drawable.pomarancza, R.drawable.pomidor),
                new AlphabethItem("R",
                        "Rak", "Rekin", "Rower", "Róża", "Ryba",
                        R.drawable.rak, R.drawable.rekin, R.drawable.rower, R.drawable.roza, R.drawable.ryba),
                new AlphabethItem("S",
                        "Sałata", "Samochód", "Samolot", "Słoń", "Sowa",
                        R.drawable.salata, R.drawable.samochod, R.drawable.samolot, R.drawable.slon, R.drawable.sowa),
                new AlphabethItem("Ś",
                        "Ślimak", "Śliwka", "Śrubokręt", "Świeczka", "Świnka",
                        R.drawable.slimak, R.drawable.sliwka, R.drawable.srubokret, R.drawable.swieczka, R.drawable.swinka),
                new AlphabethItem("T",
                        "Telefon", "Traktor", "Truskawka", "Tukan", "Tygrys",
                        R.drawable.telefon, R.drawable.traktor, R.drawable.truskawka, R.drawable.tukan, R.drawable.tygrys),
                new AlphabethItem("W",
                        "Wąż", "Wiatrak", "Widelec", "Wielbłąd", "Wiewiórka",
                        R.drawable.waz, R.drawable.wiatrak, R.drawable.widelec, R.drawable.wielblad, R.drawable.wiewiorka),
                new AlphabethItem("Z",
                        "Zając", "Zamek", "Ząb", "Zebra", "Zupa",
                        R.drawable.zajac, R.drawable.zamek, R.drawable.zab, R.drawable.zebra, R.drawable.zupa),
                new AlphabethItem("Ż",
                        "Żaba", "Zółw", "Żyrafa", "", "",
                        R.drawable.zaba, R.drawable.zolw, R.drawable.zyrafa, R.drawable.transparent_rectangle, R.drawable.transparent_rectangle),
        };

        return alphabeth;
    }
}
