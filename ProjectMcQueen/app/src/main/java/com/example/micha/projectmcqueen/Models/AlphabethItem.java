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
                        R.drawable.agrafka, R.drawable.ic_pineapple, R.drawable.ic_camera, R.drawable.ic_watermelon, R.drawable.autobus),
                new AlphabethItem("B",
                        "Balon", "Banan", "Biedronka", "Bocian", "Butelka",
                        R.drawable.ic_balloons, R.drawable.ic_banana, R.drawable.ic_ladybug, R.drawable.bocian, R.drawable.ic_bottle_of_water),
                new AlphabethItem("C",
                        "Chmura", "Ciężarówka", "Cytryna", "Czapka", "Czapla",
                        R.drawable.ic_cloud, R.drawable.ic_truck, R.drawable.ic_lemon, R.drawable.czapka, R.drawable.czapla),
                new AlphabethItem("D",
                        "Delfin", "Dom", "Drzewo", "Dynia", "Dźwig",
                        R.drawable.ic_dolphin, R.drawable.ic_house, R.drawable.ic_tree, R.drawable.ic_pumpkin, R.drawable.ic_trucking),
                new AlphabethItem("E",
                        "Ekran", "Emu", "", "", "",
                        R.drawable.ic_monitor, R.drawable.emu, R.drawable.transparent_rectangle, R.drawable.transparent_rectangle, R.drawable.transparent_rectangle),
                new AlphabethItem("F",
                        "Fala", "Flet", "Foka", "Fortepian", "Frytki",
                        R.drawable.ic_wave, R.drawable.flet, R.drawable.ic_seal, R.drawable.ic_piano, R.drawable.frytki),
                new AlphabethItem("G",
                        "Gepard", "Gołąb", "Grabie", "Gruszka", "Grzyb",
                        R.drawable.ic_cheetah, R.drawable.ic_pigeon, R.drawable.ic_rake, R.drawable.ic_pear, R.drawable.ic_mushrooms),
                new AlphabethItem("H",
                        "Hak", "Helikopter", "Hipopotam", "Hulajnoga", "Huśtawka",
                        R.drawable.ic_hook, R.drawable.ic_helicopter, R.drawable.hipopotam, R.drawable.ic_scooter, R.drawable.ic_swing),
                new AlphabethItem("I",
                        "Iglo", "Igła", "Imbir", "Indyk", "",
                        R.drawable.ic_igloo, R.drawable.ic_needle, R.drawable.imbir, R.drawable.ic_turkey, R.drawable.transparent_rectangle),
                new AlphabethItem("J",
                        "Jabłko", "Jajko", "Jeleń", "Jeż", "Język",
                        R.drawable.ic_apple, R.drawable.ic_egg, R.drawable.ic_deer, R.drawable.ic_hedgehog, R.drawable.ic_tongue_out),
                new AlphabethItem("K",
                        "Kawa", "Kiwi", "Komputer", "Kot", "Książka",
                        R.drawable.ic_coffee, R.drawable.kiwi, R.drawable.ic_computer, R.drawable.kot, R.drawable.ksiazka),
                new AlphabethItem("L",
                        "Lampka", "Laptop", "Latarka", "Lew", "Lis",
                        R.drawable.ic_lamp, R.drawable.laptop, R.drawable.ic_flashlight, R.drawable.lew, R.drawable.lis),
                new AlphabethItem("Ł",
                        "Łabędź", "Łopatka", "Łoś", "Łódka", "Łyżka",
                        R.drawable.ic_swan, R.drawable.ic_shovel, R.drawable.los, R.drawable.lodka, R.drawable.ic_spoon),
                new AlphabethItem("M",
                        "Malina", "Małpka", "Marchewka", "Motor", "Motyl",
                        R.drawable.ic_raspberry, R.drawable.malpka, R.drawable.ic_carrot, R.drawable.motor, R.drawable.motyl),
                new AlphabethItem("N",
                        "Niedźwiedź", "Nietoperz", "Nosorożec", "Nożyczki", "Nuta",
                        R.drawable.ic_bear, R.drawable.ic_bat, R.drawable.ic_rhino, R.drawable.nozyczki, R.drawable.ic_note),
                new AlphabethItem("O",
                        "Okulary", "Ołówek", "Osiołek", "Ośmiornica", "Owca",
                        R.drawable.ic_glasses, R.drawable.ic_pencil, R.drawable.ic_donkey, R.drawable.osmiornica, R.drawable.owca),
                new AlphabethItem("P",
                        "Pingwin", "Pociąg", "Poduszka", "Pomarańcza", "Pomidor",
                        R.drawable.ic_penguin, R.drawable.ic_train, R.drawable.ic_cushion, R.drawable.ic_orange, R.drawable.ic_tomato),
                new AlphabethItem("R",
                        "Rak", "Rekin", "Rower", "Róża", "Ryba",
                        R.drawable.ic_lobster, R.drawable.ic_shark, R.drawable.ic_bicycle, R.drawable.ic_rose, R.drawable.ic_fish),
                new AlphabethItem("S",
                        "Sałata", "Samochód", "Samolot", "Słoń", "Sowa",
                        R.drawable.salata, R.drawable.ic_car, R.drawable.samolot, R.drawable.ic_elephant, R.drawable.ic_owl),
                new AlphabethItem("Ś",
                        "Ślimak", "Śliwka", "Śrubokręt", "Świeczka", "Świnka",
                        R.drawable.ic_snail, R.drawable.ic_plum, R.drawable.ic_screwdriver, R.drawable.ic_candle, R.drawable.swinka),
                new AlphabethItem("T",
                        "Telefon", "Traktor", "Truskawka", "Tukan", "Tygrys",
                        R.drawable.ic_phone, R.drawable.ic_tractor, R.drawable.ic_strawberry, R.drawable.tukan, R.drawable.ic_tiger),
                new AlphabethItem("W",
                        "Wąż", "Wiatrak", "Widelec", "Wielbłąd", "Wiewiórka",
                        R.drawable.waz, R.drawable.ic_windmill, R.drawable.ic_fork, R.drawable.ic_camel, R.drawable.ic_squirrel),
                new AlphabethItem("Z",
                        "Zając", "Zamek", "Ząb", "Zebra", "Zupa",
                        R.drawable.ic_rabbit, R.drawable.ic_castle, R.drawable.ic_tooth, R.drawable.ic_zebra, R.drawable.ic_soup),
                new AlphabethItem("Ż",
                        "Żaba", "Zółw", "Żyrafa", "", "",
                        R.drawable.ic_frog, R.drawable.ic_turtle, R.drawable.ic_giraffe, R.drawable.transparent_rectangle, R.drawable.transparent_rectangle),
        };

        return alphabeth;
    }
}
