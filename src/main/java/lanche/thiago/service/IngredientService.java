package lanche.thiago.service;

import lanche.thiago.model.Ingredient;
import lanche.thiago.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {

    public static final String NOT_FOUND = "Ingredient not found";

    @Autowired
    private IngredientRepository ingredientRepository;

    public Ingredient createIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }


    public Optional<Ingredient> getIngredientById(Integer id) {
        return ingredientRepository.findById(id);
    }


    public Ingredient updateIngredient(Integer id, Ingredient ingredient) {
        Ingredient existingIngredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException (NOT_FOUND));
        existingIngredient.setName(ingredient.getName());
        existingIngredient.setPrice(ingredient.getPrice());
        return ingredientRepository.save(existingIngredient);
    }


    public void deleteIngredient(Integer id) {
        ingredientRepository.deleteById(id);
    }


    public List<Ingredient> getAllIngredient() {
        return ingredientRepository.findAll();
    }

}
