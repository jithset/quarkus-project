package io.github.jithinsethu.superheroe.utils;

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
        /*if (obj.getClass().isArray()) {
            list = Arrays.asList((Object[])obj);
        } else if (obj instanceof Collection) {
            list = new ArrayList<>((Collection<?>)obj);
        }*/
        return list;
    }

    public static boolean isCollection(Object obj) {
        return obj.getClass().isArray() || obj instanceof Collection;
    }

}
