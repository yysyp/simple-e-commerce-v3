package ps.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ps.demo.entity.Product;
import ps.demo.repository.ProductMapper;



/**
 //@Cacheable will check the condition before executing the method (cannot get #result yet), if it returns true, query cache
 @Cacheable(value = "user", key = "#id", condition = "#id lt 10")
 public User conditionFindById(final Long id)

 //@CachePut will check the condition after executing the method (can get #result now) , if it returns true, put into cache
 @CachePut(value = "user", key = "#id", condition = "#result.username ne 'zhang'")
 public User conditionSave(final User user)

 //@CachePut will check unless after executing the method (can get #result now), if it returns false, put into cache (opposite of condition)
 @CachePut(value = "user", key = "#user.id", unless = "#result.username eq 'zhang'")
 public User conditionSave2(final User user)

 //@CacheEvict, beforeInvocation=false means evict cache after executing method (can get #result); check condition, evict cache if true
 @CacheEvict(value = "user", key = "#user.id", beforeInvocation = false, condition = "#result.username ne 'zhang'")
 public User conditionDelete(final User user)

 condition not specified equals to true, unless not specified equals to false
 When condition = false, will definitely not cache;
 When condition = true, and unless = true, will not cache;
 When condition = true, and unless = false, will cache;

 id—>user;
 username—>id;
 email—>id;

 @Cacheable(value="cacheName", key="#user.username", cacheValue="#caches\[0\].get(#caches\[0\].get(#username).get())")
 public User findByUsername(String username)
 */
@Slf4j
@Service
@CacheConfig(cacheNames = {"myCache"})
public class CacheService {

    @Autowired
    private ProductMapper productMapper;

    /*
    If the return product is null or product.id is null or product.id is 0 or product.name is null or product.name length is 0
    then Not to cache the result.
     */
    @Cacheable(key = "targetClass+'-Prod-'+#id",
            unless = "#result?.id == null or #result?.id == 0 or #result?.name == null or #result?.name?.length == 0")
    public Product findProductById(Long id) {
        log.info("Actually find product by id, id={}", id);
        Product product = productMapper.selectById(id);
        return product;
    }

    @CacheEvict(key = "targetClass+'-Prod-'+#p0")
    public void deleteProductFromCache(Long id) {
        log.info("Delete product id={} from cache", id);
        //productMapper.deleteById(id);
    }


}
