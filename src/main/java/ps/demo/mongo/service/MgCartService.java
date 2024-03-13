package ps.demo.mongo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import ps.demo.mongo.entity.MgCart;
import ps.demo.mongo.entity.MgProduct;
import ps.demo.mongo.repository.MgCartRepository;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOptions;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MgCartService {

    @Autowired
    private MgCartRepository cartRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public MgCart createMgCart(List<MgProduct> products) {
        MgCart cart = new MgCart();
        cart.setMgProducts(products);
        return cartRepository.save(cart);
    }

    public MgCart getMgCart(String cartId) {
        return cartRepository.findById(cartId).orElse(null);
    }

    public MgCart updateMgCart(String cartId, List<MgProduct> updatedMgProducts) {
        MgCart cart = cartRepository.findById(cartId).orElse(null);
        if (cart != null) {
            cart.setMgProducts(updatedMgProducts);
            return cartRepository.save(cart);
        }
        return null;
    }

    public void deleteMgCart(String cartId) {
        cartRepository.deleteById(cartId);
    }

    public Document groupSortFindFirstN(String fieldName, String sortFieldName, int firstN) {
        // 创建分组操作
        GroupOperation groupOperation = Aggregation.group(fieldName)
                .first(fieldName).as(fieldName)  // 选择分组的第一个元素
                .first(sortFieldName).as(sortFieldName);  // 选择分组的第一个排序字段的元素

        // 创建排序操作
        SortOperation sortOperation = Aggregation.sort(Sort.Direction.ASC, sortFieldName);

        // 创建聚合操作列表
        List<AggregationOperation> aggregationOperations = new ArrayList<>();
        aggregationOperations.add(groupOperation);
        aggregationOperations.add(sortOperation);

        // 限制结果集的大小
        AggregationOptions aggregationOptions = AggregationOptions.builder().allowDiskUse(true).build();
        aggregationOperations.add(Aggregation.limit(firstN));

        // 创建聚合
        Aggregation aggregation = Aggregation.newAggregation(aggregationOperations);

        // 执行聚合查询
        AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, "YourCollection", Document.class);

        // 假设我们只关心第一个结果
        if (!results.getMappedResults().isEmpty()) {
            return results.getMappedResults().get(0);
        }
        return null;
    }

    
}