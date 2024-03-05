

package ps.demo.newstock.dto;

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
public class NewStockDataDto extends BaseDto {

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


