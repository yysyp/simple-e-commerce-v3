package [(${packageName}+'.'+${moduleName}+'.'+${dtoFolder})];

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;
import java.util.List;
import java.util.*;
import java.math.*;
import ps.demo.common.BaseDto;
import lombok.*;
import java.util.*;
import java.math.*;
@Getter
@Setter
@ToString
public class [(${dtoName})] extends BaseDto {
[# th:each="attr,attrStat:${entityAttrs}" ]
    private [(${attr.get('type')})] [(${attr.get('name')})];
[/]

}