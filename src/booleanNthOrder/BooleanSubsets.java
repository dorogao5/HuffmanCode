package booleanNthOrder;

import java.util.ArrayList;
import java.util.List;

public class BooleanSubsets {
    public static List<List<Double>> booleanSubsets(List<Double> s) {
        if (s.isEmpty()) {
            List<List<Double>> emptySubset = new ArrayList<>();
            emptySubset.add(new ArrayList<>());
            return emptySubset;
        } else {
            Double x = s.get(0);
            List<Double> rest = s.subList(1, s.size());
            List<List<Double>> tmp = booleanSubsets(rest);
            List<List<Double>> result = new ArrayList<>(tmp);

            for (List<Double> subset : tmp) {
                List<Double> newSubset = new ArrayList<>(subset);
                newSubset.add(0, x);
                result.add(newSubset);
            }

            return result;
        }
    }

    public static List<Object> booleanSubsetsNthOrder(List<?> s, int n) {
        if (n == 1) {
            return new ArrayList<>(booleanSubsets((List<Double>) s));
        } else {
            List<Object> lowerOrder = booleanSubsetsNthOrder(s, n - 1);
            List<Object> result = new ArrayList<>();
            result.add(new ArrayList<>());
            for (Object obj : lowerOrder) {
                List<Object> temp = new ArrayList<>(result);
                for (Object subset : result) {
                    List<Object> newSubset = new ArrayList<>((List<?>) subset);
                    newSubset.add(obj);
                    temp.add(newSubset);
                }
                result = temp;
            }
            return result;
        }
    }

}
