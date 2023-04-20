package sn.youdev.controller;
import jakarta.validation.Valid;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import lombok.extern.slf4j.Slf4j;
import sn.youdev.config.Props;
import sn.youdev.entities.TacoOrder;
import org.springframework.validation.Errors;
import sn.youdev.entities.User;
import sn.youdev.repository.OrderRepository;

import java.util.Optional;


@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {
    private OrderRepository orderRepo;
    private Props props;
    public OrderController(OrderRepository orderRepo, Props props) {
        this.orderRepo = orderRepo;
        this.props = props;
    }
    @GetMapping("/current")
    public String orderForm(Model model) {
        model.addAttribute("title", "Fill in order");
        model.addAttribute("content", "order_form");
        return "layout";
    }
    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors,SessionStatus sessionStatus,@AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            log.info("error in Order submittion: {}", errors);
            return "orderForm";
        }
        order.setUser(user);
        orderRepo.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
    @GetMapping("/liste")
    public String ordersForUser(
            @AuthenticationPrincipal User user, Model model) {
        Optional<Pageable> pageableOptional = PageRequest.of(0, 20).toOptional();
        pageableOptional.ifPresent(pageable -> model.addAttribute("orders", orderRepo.findByUserOrderByPlacedAtDesc(user, pageable)));
        model.addAttribute("title", "Liste of order");
        model.addAttribute("content", "order_liste");
        return "layout";
    }
}
