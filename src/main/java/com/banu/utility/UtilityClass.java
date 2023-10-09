package com.banu.utility;

import java.util.Scanner;

public class UtilityClass {

    public static String stringDeger(String aciklama){
        System.out.print(aciklama);
        return new Scanner(System.in).nextLine();
    }

    public static int intDeger(String aciklama){
        System.out.print(aciklama);
        String deger =new Scanner(System.in).nextLine();
        return Integer.parseInt(deger);
    }

    public static Long longDeger(String aciklama){
        System.out.print(aciklama);
        String deger =new Scanner(System.in).nextLine();
        return Long.parseLong(deger);
    }


}
