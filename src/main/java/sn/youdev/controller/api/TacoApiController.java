package sn.youdev.controller.api;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.youdev.entities.Taco;
import sn.youdev.repository.TacoRepository;
import sn.youdev.service.JmsService;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/tacos", produces = "application/json")
@CrossOrigin(origins = "http://127.0.0.1:8080")
public class TacoApiController {
    private final TacoRepository tacoRepository;
    private final JmsService jms;

    public TacoApiController(TacoRepository tacoRepository, JmsService jmsService) {
        this.tacoRepository = tacoRepository;
        this.jms = jmsService;
    }

    @GetMapping("/recent")
    public Iterable<Taco> recentTacos() {
        int sum = 143;
        for (int i = 16; i < 31; i++) {
            System.out.println(i +" -> "+sum);
            sum+=18;
        }
        PageRequest page = PageRequest.of(
                0, 12, Sort.by("createdAt").descending());
        return tacoRepository.findAll(page).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> getById(@PathVariable("id") final Long id) {
        Optional<Taco> taco = tacoRepository.findById(id);
        taco.ifPresent(jms::sendOrder);
        return taco.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {

        return tacoRepository.save(taco);
    }
    @DeleteMapping("/{orderId}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public Boolean deleteOrder(@PathVariable("orderId") Long orderId) {
        try { tacoRepository.deleteById(orderId);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
//    public Ingredient getIngredientById(String ingredientId) {
//        return rest.getForObject("http://localhost:8080/ingredients/{id}",
//                Ingredient.class, ingredientId);
//    }

}
