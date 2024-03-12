package ps.demo.mongo.entity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Document(collection = "carts")
public class MgCart {
    @Id
    private String id;
    private List<MgProduct> mgProducts;


}
