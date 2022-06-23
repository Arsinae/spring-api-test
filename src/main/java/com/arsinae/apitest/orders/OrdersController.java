package com.arsinae.apitest.orders;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/order")
public class OrdersController {

	@Autowired
	private OrdersRepository orderRepository;

	@PostMapping(path="")
  public @ResponseBody Orders addNewUser (@RequestParam Float price) {
    Orders order = new Orders();
    order.setPrice(price);
		order.setDate(new Date(System.currentTimeMillis()));
    order = orderRepository.save(order);
    return order;
  }

	@GetMapping("")
	public Iterable<Orders> getAllOrder () {
		return orderRepository.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Orders> getOrderById (@PathVariable String id) {
		return orderRepository.findById(Integer.parseInt(id));
	}

	@GetMapping("/date/{date}")
	public Iterable<Orders> getOrdesrByDate (@PathVariable String date) {
		return orderRepository.findByDate(Date.valueOf(date));
	}
}
