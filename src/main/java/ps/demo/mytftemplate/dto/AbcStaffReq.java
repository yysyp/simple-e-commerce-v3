

package ps.demo.mytftemplate.dto;

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
public class AbcStaffReq extends BasePageReq {
    private String key;


    private String firstName;
    private String lastName;
    private Integer age;
    private BigDecimal score;
    private Boolean passed;
    private Date birthday;
    private String comments;


}





