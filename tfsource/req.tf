package [(${packageName}+'.'+${moduleName}+'.'+${dtoFolder})];

import lombok.*;
import java.util.*;
import java.math.*;

import ps.demo.common.BasePageReq;


@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class [(${reqName})] extends BasePageReq {
    private String key;
[# th:each="attr,attrStat:${entityAttrs}" ]
    private [(${attr.get('type')})] [(${attr.get('name')})];
[/]

}



