package com.weg.gestacaoescolar.util;

public class gerarIn {
    public static String gerando(int size){
        StringBuilder sql = new StringBuilder("(");

        for(int i = 0; i < size; i++){
            sql.append("?");
            if(i < size - 1){
                sql.append(", ");
            }
        }
        sql.append(")");
        return sql.toString();
    }
}