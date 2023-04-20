package sn.youdev.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sn.youdev.entities.Ingredient;
import sn.youdev.repository.IngredientRepository;

@RestController
@RequestMapping(path = "/api/ingredients", produces = "application/json")
@CrossOrigin(origins = "http://127.0.0.1:8080")
public class IngredientController {
    private IngredientRepository repo;
    @Autowired
    public IngredientController(IngredientRepository repo){
        this.repo = repo;
    }
    @GetMapping
    public Iterable<Ingredient> allIngredients(){
        return repo.findAll();
    }
    @PostMapping
    @PreAuthorize(("#{hasRole('ADMIN')}"))
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient saveIngredient(@RequestBody Ingredient ingredient) {
        return repo.save(ingredient); }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIngredient(@PathVariable("id") String ingredientId) {
        repo.deleteById(ingredientId);
    }
}
