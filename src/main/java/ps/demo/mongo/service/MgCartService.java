package ps.demo.mongo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ps.demo.mongo.entity.MgCart;
import ps.demo.mongo.entity.MgProduct;
import ps.demo.mongo.repository.MgCartRepository;

import java.util.List;

@Service
public class MgCartService {

    @Autowired
    private MgCartRepository cartRepository;

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

    
}