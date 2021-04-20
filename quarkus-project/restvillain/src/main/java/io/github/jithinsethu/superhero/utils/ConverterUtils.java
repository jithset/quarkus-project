package io.github.jithinsethu.superhero.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ConverterUtils {

    public static List<?> convertObjectToList(Object obj) {
        List<?> list;
        if (isCollection(obj)) {
            list = new ArrayList<>((Collection<?>)obj);
        } else {
            list = Arrays.asList((Object[])obj);
        }
        return list;
    }

    public static boolean isCollection(Object obj) {
        return obj.getClass().isArray() || obj instanceof Collection;
    }

    public static String jdbcToReactive(String jdbcUrl) {
        //jdbc:postgresql://localhost:49200/heroes_database?loggerLevel=OFF
        // postgresql://localhost:8090/heroes_database
        return jdbcUrl.replace("jdbc:", "");
    }

}
