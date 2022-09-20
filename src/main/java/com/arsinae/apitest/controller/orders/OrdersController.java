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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
  public @ResponseBody ResponseEntity<Orders> addNewOrder (
		@RequestBody Orders order
	) {
    order.setDate(new Date(System.currentTimeMillis()));
    order = orderRepository.save(order);
		return new ResponseEntity<Orders>(order, HttpStatus.CREATED);
  }

	@GetMapping("")
	public ResponseEntity<Iterable<Orders>> getAllOrders () {
		return new ResponseEntity<Iterable<Orders>>(orderRepository.findAll(Sort.by(Sort.Direction.DESC, "id")), HttpStatus.OK);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<Orders>> getOrdersByDateAndPrice (
		@RequestParam(defaultValue = "10") String price,
		@RequestParam @DateTimeFormat(pattern="MM/dd/yyyy") java.util.Date date
	) {
		return new ResponseEntity<List<Orders>>(orderRepository.findAllByPriceAndDate(Float.parseFloat(price), new Date(date.getTime())), HttpStatus.OK);
	}

	@GetMapping("/date/count")
	public ResponseEntity<List<DateCount>> countOrdersByDate () {
		return new ResponseEntity<List<DateCount>>(orderRepository.countOrdersByDate(), HttpStatus.OK);
	}

	@GetMapping("/date/{date}")
	public ResponseEntity<Iterable<Orders>> getOrdersByDate (@PathVariable String date) {
		return new ResponseEntity<Iterable<Orders>>(orderRepository.findAllByDate(Date.valueOf(date)), HttpStatus.OK);
	}

	@GetMapping("/price")
	public ResponseEntity<Iterable<Orders>> getOrdersByPrice (
		@RequestParam(defaultValue = "10") String price,
		@RequestParam(defaultValue = "0") String page,
		@RequestParam(defaultValue = "5") String size,
		@RequestParam(defaultValue = "id") String orderField,
		@RequestParam(defaultValue = "asc") String orderDir
	) {
		Float priceFloat = Float.parseFloat(price);
		Sort sort = orderDir.equals("asc") ? Sort.by(orderField).ascending() : Sort.by(orderField).descending();
		Pageable pageSet = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size), sort);
		return new ResponseEntity<Iterable<Orders>>(orderRepository.findAllByPrice(priceFloat, pageSet).getContent(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Orders>> getOrderById (@PathVariable String id) {
		Optional<Orders> order = orderRepository.findById(Integer.parseInt(id));
		if (order.isPresent()) {
			return new ResponseEntity<Optional<Orders>>(order, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
