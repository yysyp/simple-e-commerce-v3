package ps.demo.mongo.entity;


import lombok.*;
import java.math.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class MgProduct {
    private String id;
    private String name;
    private BigDecimal price;


}
