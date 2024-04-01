package ps.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.*;
import java.util.stream.Collectors;

public class TreeMenuTest {


    public static void main(String[] args) throws JsonProcessingException {
        Set<String> mockPaths = mockData1();
        for (String str : mockPaths) {
            System.out.println("mockPath = " + str);
        }
        List<List<String>> tbl = mockPaths.stream().map(e -> Lists.newArrayList(e.split("/"))).collect(Collectors.toList());

        TheTreeNode root = new TheTreeNode("", "root");
        List<String> level1List = tbl.stream().map(li -> li.get(0)).distinct().sorted().collect(Collectors.toList());


        for (int i = 0; i < level1List.size(); i++) {
            String level1 = level1List.get(i);
            TheTreeNode treeNode1 = new TheTreeNode(level1, root.getKey()+"-"+i);
            root.addChild(treeNode1);

            List<String> level2List = tbl.stream().filter(e -> level1.equalsIgnoreCase(e.get(0))).map(li -> li.get(1)).distinct().sorted().collect(Collectors.toList());
            for (int j = 0; j < level2List.size(); j++) {
                String level2 = level2List.get(j);
                TheTreeNode treeNode2 = new TheTreeNode(level2, treeNode1.getKey()+"-"+j);
                treeNode1.addChild(treeNode2);


                constructLatest(treeNode2, tbl, level1, level2, 5);

                constructHistorical(treeNode2, tbl, level1, level2, 5);

            }
        }

        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(root);
        System.out.println("jsonStr = " + jsonStr);

        Map<String, List<List<String>>> groupedBy = groupBy(tbl, 2, true);


        for (Map.Entry<String, List<List<String>>> entry : groupedBy.entrySet()) {

            System.out.println("entry = " + entry);
        }
    }

    private static void constructHistorical(TheTreeNode treeNode2, List<List<String>> tbl, String level1, String level2, int limit) {
        TheTreeNode historical = new TheTreeNode("Historical", treeNode2.getKey()+"-historical");
        treeNode2.addChild(historical);
        List<List<String>> subTbl = tbl.stream().filter(e -> level1.equalsIgnoreCase(e.get(0))
                && level2.equalsIgnoreCase(e.get(1))).collect(Collectors.toList());
        Map<String, List<List<String>>> groupedSubTblMap = groupBy(subTbl, 2, false);
        constructTree(historical, groupedSubTblMap, 2, limit, false);
    }

    private static void constructLatest(TheTreeNode treeNode2, List<List<String>> tbl, String level1, String level2, int limit) {
        TheTreeNode latest = new TheTreeNode("Latest", treeNode2.getKey()+"-latest");
        treeNode2.addChild(latest);
        List<List<String>> subTbl = tbl.stream().filter(e -> level1.equalsIgnoreCase(e.get(0))
                && level2.equalsIgnoreCase(e.get(1))).collect(Collectors.toList());
        Map<String, List<List<String>>> groupedSubTblMap = groupBy(subTbl, 3, true);
        constructTree(latest, groupedSubTblMap, 3, limit, true);
    }

    private static void constructTree(TheTreeNode subHead, Map<String, List<List<String>>> groupedSubTblMap, int level, int limit, boolean latest) {
        int i = 0;
        for (Map.Entry<String, List<List<String>>> entry : groupedSubTblMap.entrySet()) {
            i++;
            String title = entry.getKey();
            TheTreeNode treeNode = new TheTreeNode(title, subHead.getKey()+"-"+i);
            subHead.addChild(treeNode);

            List<List<String>> subList = entry.getValue();
            if (CollectionUtils.isNotEmpty(subList) && level + 1 < limit) {
                //int subLevel = level++;
                constructTree(treeNode, groupBy(subList, level + 1, true), level + 1, limit, latest);
            } else {
                if (latest) {
                    subList.get(0).set(2, "*");
                }
                treeNode.setData(String.join("/", subList.get(0)));
            }
        }
    }



    public static Set<String> mockData1() {
//        List<String> objs = Lists.newArrayList("obj1", "obj2", "obj3");
//        List<String> brhs = Lists.newArrayList("b1", "b2");
//        List<String> dates = Lists.newArrayList("20230101", "20230102", "20230103", "20230104");
//        List<String> nms = Lists.newArrayList("n1", "n2", "n3", "n4");
//        List<String> docNms = Lists.newArrayList("dn1", "dn2", "dn3", "dn4", "dn5");
//
//        Set<String> sets = new HashSet<>();
//        for (int i = 0; i < 10; i ++) {
//            String stringBuilder = objs.get(RandomUtils.nextInt(0, objs.size())) + "/" +
//                    brhs.get(RandomUtils.nextInt(0, brhs.size()))  + "/" +
//                    dates.get(RandomUtils.nextInt(0, dates.size()))  + "/" +
//                    nms.get(RandomUtils.nextInt(0, nms.size()))  + "/" +
//                    docNms.get(RandomUtils.nextInt(0, docNms.size()));
//            sets.add(stringBuilder);
//        }
//        return sets;

        Set<String> sets = new HashSet<>();
        sets.add("obj1/b1/20230122/n1/docn1");
        //sets.add("obj1/b1/20230122/n1/docn1"); v2
        sets.add("obj1/b2/20231102/n2/docn2");
        sets.add("obj2/b1/20230104/n2/docn1");
        sets.add("obj3/b1/20231212/n4/docn3");
        sets.add("obj3/b1/20231231/n4/docn3");
        sets.add("obj3/b2/20231103/n3/docn1");

        return sets;
    }

    public static Map<String, List<List<String>>> groupBy(List<List<String>> tbl, int groupKey, boolean asc) {
        Map<String, List<List<String>>> grouped = tbl.stream().collect(Collectors.groupingBy(list -> list.get(groupKey)));
        Map<String, List<List<String>>> sortedMap = new LinkedHashMap<>();

        if (asc) {
            grouped.entrySet().stream().sorted(Map.Entry.<String, List<List<String>>>comparingByKey())
                    .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
        } else {
            grouped.entrySet().stream().sorted(Map.Entry.<String, List<List<String>>>comparingByKey().reversed())
                    .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
        }
        return sortedMap;
    }
}
