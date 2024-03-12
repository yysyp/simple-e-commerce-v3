package ps.demo.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ps.demo.mongo.entity.MgCart;

public interface MgCartRepository extends MongoRepository<MgCart, String> {

}
