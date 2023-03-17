package lanche.thiago.service;

import lanche.thiago.model.Ingredient;
import lanche.thiago.model.Promotion;
import lanche.thiago.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PromotionService {
    
    @Autowired
    private PromotionRepository promotionRepository;

    public Promotion createCustomSandwich(Promotion sandwich) {
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (Ingredient ingredient : sandwich.getIngredients()) {
            totalPrice = totalPrice.add(ingredient.getPrice());
        }
        totalPrice = applyPromotions(totalPrice, sandwich.getIngredients());
        sandwich.setTotalPrice(totalPrice);
        return promotionRepository.save(sandwich);
    }

    private BigDecimal applyPromotions(BigDecimal totalPrice, List<Ingredient> ingredients) {

        //verifica regra de light
        if (ingredients.stream().anyMatch(i -> i.getName().equals("Alface"))
                && !ingredients.stream().anyMatch(i -> i.getName().equals("Bacon"))) {

            long discount = 10;
            totalPrice = totalPrice.multiply(BigDecimal.valueOf(1 - (discount / 100.0)));
        }

        long quantity = ingredients.stream().filter(i -> i.getName().equals("Hambúrguer")).count();
        if (quantity > 0) {
            //verificar regra de muita carne
            if (quantity == 3){
                long discountedQuantity = 1;
                totalPrice = totalPrice.subtract((BigDecimal.valueOf(discountedQuantity * 3.0)));
            } else if (quantity == 6){
                long discountedQuantity = 2;
                totalPrice = totalPrice.subtract((BigDecimal.valueOf(discountedQuantity * 3.0)));
            } else if (quantity == 9){
                long discountedQuantity = 3;
                totalPrice = totalPrice.subtract((BigDecimal.valueOf(discountedQuantity * 3.0)));
            }
        }

        long quantityQueijo = ingredients.stream().filter(i -> i.getName().equals("Queijo")).count();
        if (quantityQueijo > 0) {
            //verificar regra de muito queijo
            if (quantityQueijo == 3){
                long discountedQuantityQueijo = 1;
                totalPrice = totalPrice.subtract((BigDecimal.valueOf(discountedQuantityQueijo * 1.5)));
            } else if (quantityQueijo == 6){
                long discountedQuantityQueijo = 2;
                totalPrice = totalPrice.subtract((BigDecimal.valueOf(discountedQuantityQueijo * 1.5)));
            } else if (quantityQueijo == 9){
                long discountedQuantityQueijo = 3;
                totalPrice = totalPrice.subtract((BigDecimal.valueOf(discountedQuantityQueijo * 1.5)));
            }
        }
        return totalPrice;
    }

    public List<Promotion> getAllCustomSandwich() {
        return promotionRepository.findAll();
    }

    public Optional<Promotion> getCustomSandwichById(Integer id) {
        return promotionRepository.findById(id);
    }

    public void deleteCustomSandwich(Integer id){
        promotionRepository.deleteById(id);
    }

}