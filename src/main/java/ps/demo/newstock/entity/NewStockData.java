

package ps.demo.newstock.entity;

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
@Table(name = "new_stock_data")
public class NewStockData implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String createdBy;
    private Instant createdOn;
    private Boolean isActive;
    private Boolean isLogicalDeleted;
    private String modifiedBy;
    private Instant modifiedOn;


    private String companyCode;
    private Integer periodYear;
    private Integer periodMonth;
    private String companyName;
    private String rawPeriod;
    private String kemuType;
    private String rawKemu;
    private String kemuEn;
    private String kemu;
    private String rawKemuValue;
    private BigDecimal kemuValue;
    private BigDecimal yoy;
    private BigDecimal pctInAssetOrRevenue;
    private BigDecimal coreProfitOnAssetEffect;
    private Integer flag;
    private String fileName;
    private String comments;


}


