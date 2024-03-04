

package ps.demo.mytftemplate.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ps.demo.common.BaseDto;


import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
public class AbcStaffDto extends BaseDto {

    private String firstName;
    private String lastName;
    private Integer age;
    private BigDecimal score;
    private Boolean passed;
    private String comments;
    private Date birthday;


}


