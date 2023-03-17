package lanche.thiago.controller;

import lanche.thiago.model.Promotion;
import lanche.thiago.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/promotion")
public class SandwichController {

        @Autowired
        private PromotionService promotionService;

        @PostMapping
        public ResponseEntity<Promotion> createCustomSandwich(@RequestBody Promotion promotion) {
            Promotion createdSandwich = promotionService.createCustomSandwich(promotion);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSandwich);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Promotion> getCustomSandwichById(@PathVariable Integer id) {
            Optional<Promotion> order = promotionService.getCustomSandwichById(id);
            return order.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        }

        @GetMapping
        public ResponseEntity<List<Promotion>> getAllCustomSandwich() {
            List<Promotion> promotions = promotionService.getAllCustomSandwich();
            return ResponseEntity.ok(promotions);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteCustomSandwich(@PathVariable Integer id) {
            promotionService.deleteCustomSandwich(id);
            return ResponseEntity.noContent().build();
        }


    }

