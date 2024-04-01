package ps.demo.service;


import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class TheTreeNode {

    private String title;
    private String key;
    private String data;

    private List<TheTreeNode> children = new ArrayList<>();

    public TheTreeNode(String title, String key) {
        this.title = title;
        this.key = key;
    }

    public void addChild(TheTreeNode theTreeNode) {
        this.children.add(theTreeNode);
    }
}
