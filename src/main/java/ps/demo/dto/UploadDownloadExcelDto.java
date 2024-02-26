package ps.demo.dto;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UploadDownloadExcelDto implements Serializable {

    @ExcelIgnore
    private Long id;

    @ExcelProperty(value = "first_name", index = 0)
    private String firstName;

    @ExcelProperty(value = "last_name", index = 1)
    private String lastName;

    @ExcelProperty(value = "age", index = 2)
    private int age;

    @ExcelProperty(value = "score", index = 3)
    private BigDecimal score;

}
