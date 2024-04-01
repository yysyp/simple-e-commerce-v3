package ps.demo.service;

import com.google.common.collect.Lists;
import org.checkerframework.checker.units.qual.N;

import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

public class NestedListGroupingAndSortingExample {
    public static void main(String[] args) {

//        List<String> testDates = Lists.newArrayList("20230305", "20231001", "20240101", "20221111", "20201212", "19981212");
//        testDates.sort(Comparator.comparing(e -> e.toString()).reversed());
//        System.out.println("sorted = " + testDates);


        List<List<String>> nestedList = Arrays.asList(
                Arrays.asList("Alice", "5", "20230305"),
                Arrays.asList("Bob", "20", "20231001"),
                Arrays.asList("Charlie", "25", "20240101"),
                Arrays.asList("David", "30", "20221111"),
                Arrays.asList("Eve", "20", "20201212")
        );

        Map<String, List<List<String>>> groupSortedMap = groupBy(nestedList, 1, "int", true);
        groupSortedMap.forEach((key, value) -> {
            System.out.println("entry = " + key + " == " + value);
        });
//         多条件分组
//        Map<List<String>, List<List<String>>> grouped = nestedList.stream()
//                .collect(Collectors.groupingBy(
//                        list -> Arrays.asList(list.get(0), list.get(2)) // 分组键：第一个和第三个元素
//                ));
//
//
////        // 对每个分组进行复合排序
////        Map<List<String>, List<List<String>>> sortedGroups = grouped.entrySet().stream()
////                .collect(Collectors.toMap(
////                        Map.Entry::getKey, // 分组键保持不变
////                        entry -> entry.getValue().stream() // 分组内的元素流
////                                .sorted(Comparator.comparing(list -> list.get(1)).thenComparing(list -> list.get(0))) // 复合排序：按第二个元素，然后按第一个元素
////                                .collect(Collectors.toList()) // 收集排序后的元素
////                ));
//
//        // 打印排序后的分组
//        grouped.forEach((key, value) -> {
//            System.out.println("Group: " + key);
//            value.forEach(innerList -> System.out.println(innerList));
//            System.out.println();
//        });
    }

    public static Map<String, List<List<String>>> groupBy(List<List<String>> tbl, int groupKey, String type, boolean asc) {
        return groupBy(tbl, groupKey, new Comparator<Map.Entry<String, List<List<String>>>>() {
            @Override
            public int compare(Map.Entry<String, List<List<String>>> o1, Map.Entry<String, List<List<String>>> o2) {
                if ("int".equalsIgnoreCase(type)) {
                    Integer n1 = Integer.parseInt(o1.getKey());
                    Integer n2 = Integer.parseInt(o2.getKey());
                    return asc? n1.compareTo(n2) : n2.compareTo(n1);
                } else {
                    return asc? o1.getKey().compareTo(o2.getKey()) : o2.getKey().compareTo(o1.getKey());
                }
            }
        });
    }

    public static Map<String, List<List<String>>> groupBy(List<List<String>> tbl, int groupKey, Comparator<Map.Entry<String, List<List<String>>>> cp) {
        Map<String, List<List<String>>> grouped = tbl.stream().collect(Collectors.groupingBy(list -> list.get(groupKey)));
        Map<String, List<List<String>>> sortedMap = new LinkedHashMap<>();
        grouped.entrySet().stream().sorted(cp).forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
//        if (asc) {
//            grouped.entrySet().stream().sorted(Map.Entry.<String, List<List<String>>>comparingByKey())
//                    .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
//        } else {
//            grouped.entrySet().stream().sorted(Map.Entry.<String, List<List<String>>>comparingByKey().reversed())
//                    .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
//        }
        return sortedMap;
    }

}