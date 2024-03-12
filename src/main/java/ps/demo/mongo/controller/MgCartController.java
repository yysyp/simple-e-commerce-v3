package ps.demo.mongo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ps.demo.mongo.entity.MgCart;
import ps.demo.mongo.entity.MgProduct;
import ps.demo.mongo.service.MgCartService;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class MgCartController {

    @Autowired
    private MgCartService cartService;

    @PostMapping
    public MgCart createMgCart(@RequestBody List<MgProduct> products) {
        return cartService.createMgCart(products);
    }

    @GetMapping("/{cartId}")
    public MgCart getMgCart(@PathVariable String cartId) {
        return cartService.getMgCart(cartId);
    }

    @PutMapping("/{cartId}")
    public MgCart updateMgCart(@PathVariable String cartId, @RequestBody List<MgProduct> updatedMgProducts) {
        return cartService.updateMgCart(cartId, updatedMgProducts);
    }

    @DeleteMapping("/{cartId}")
    public void deleteMgCart(@PathVariable String cartId) {
        cartService.deleteMgCart(cartId);
    }
}