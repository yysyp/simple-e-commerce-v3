

package ps.demo.mytftemplate.dto;

import lombok.*;
import ps.demo.common.BasePageReq;


import java.math.BigDecimal;
import java.util.Date;


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
    private String comments;
    private Date birthday;


}





