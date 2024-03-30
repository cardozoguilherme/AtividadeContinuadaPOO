package br.edu.cesarschool.cc.poo.ac.utils;

import java.lang.String;
public class StringUtils {
    private StringUtils() {
    }

    public static boolean isVaziaOuNula(String valor){
        return valor == null || valor.trim().isEmpty();
    }
}
