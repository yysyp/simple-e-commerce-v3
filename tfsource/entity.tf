package [(${packageName}+'.'+${moduleName}+'.'+${entityFolder})];

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.*;
import java.math.*;

import lombok.*;
import java.util.*;
import java.math.*;
import jakarta.persistence.*;
import java.time.Instant;

@Getter
@Setter
@ToString
@Entity
@Table(name = "[(${tableName})]")
public class [(${entityName})] implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String createdBy;
    private Instant createdOn;
    private Boolean isActive;
    private Boolean isLogicalDeleted;
    private String modifiedBy;
    private Instant modifiedOn;

[# th:each="attr,attrStat:${entityAttrs}" ]
    private [(${attr.get('type')})] [(${attr.get('name')})];
[/]

}
