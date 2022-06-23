package com.arsinae.apitest.controller.orders;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.arsinae.apitest.model.datecount.DateCount;
import com.arsinae.apitest.model.orders.Orders;
import com.arsinae.apitest.repository.orders.OrdersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
  public @ResponseBody Orders addNewOrder (@RequestParam Float price) {
    Orders order = new Orders();
    order.setPrice(price);
		order.setDate(new Date(System.currentTimeMillis()));
    order = orderRepository.save(order);
    return order;
  }

	@GetMapping("")
	public Iterable<Orders> getAllOrders () {
		return orderRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

	@GetMapping("/{id}")
	public Optional<Orders> getOrderById (@PathVariable String id) {
		return orderRepository.findById(Integer.parseInt(id));
	}

	@GetMapping("/date/count")
	public List<DateCount> getOrdersByDate () {
		return orderRepository.countOrdersByDate();
	}

	@GetMapping("/date/{date}")
	public Iterable<Orders> getOrdersByDate (@PathVariable String date) {
		return orderRepository.findAllByDate(Date.valueOf(date));
	}

	@GetMapping("/price/{price}")
	public Iterable<Orders> getOrdersByPrice (
		@PathVariable String price,
		@RequestParam(defaultValue = "0") String page
	) {
		Float priceFloat = Float.parseFloat(price);
		Pageable pageSet = PageRequest.of(Integer.parseInt(page), 1);
		return orderRepository.findAllByPrice(priceFloat, pageSet);
	}
}
