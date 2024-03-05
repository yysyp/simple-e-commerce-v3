

package ps.demo.mytftemplate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "abc_staff")
public class AbcStaff implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected String createdBy;
    protected Instant createdOn;
    protected Boolean isActive;
    protected Boolean isLogicalDeleted;
    protected String modifiedBy;
    protected Instant modifiedOn;

    private String firstName;
    private String lastName;
    private Integer age;
    private BigDecimal score;
    private Boolean passed;
    private String comments;
    private Date birthday;


}


