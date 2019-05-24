package com.module.controller;

import com.module.model.Customer;
import com.module.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

  //hien form tao customer
  @GetMapping("/create")
  public ModelAndView showCreateForm() {
    ModelAndView modelAndView = new ModelAndView("/customer/create");
    modelAndView.addObject("customer", new Customer());
    return modelAndView;
  }

  @Autowired
  private CustomerService customerService;

  //sau khi dien form thi submit se gui save data vao db
  // hien thi reponse chua tai form vi k redict den trang khac
  @PostMapping("/create")
  public ModelAndView saveCustomer(@ModelAttribute("customer") Customer customer) {
    customerService.save(customer);

    ModelAndView modelAndView = new ModelAndView("/customer/create");
    modelAndView.addObject("customer", new Customer());
    modelAndView.addObject("message", "New customer created successfully");
    return modelAndView;
  }

  //hien thi danh sach customer
  @GetMapping("/list")
  public ModelAndView listCustomers(){
    List<Customer> customerList = customerService.findAll();
    ModelAndView modelAndView = new ModelAndView("/customer/list");
    modelAndView.addObject("listCustomer", customerList);
    return modelAndView;
  }

  //hien thi form sua customer dua theo id
  @GetMapping("/edit/{id}")
  public ModelAndView showEditForm(@PathVariable Long id){
    Customer customer = customerService.findById(id);
    if(customer != null) {
      ModelAndView modelAndView = new ModelAndView("/customer/edit");
      modelAndView.addObject("customer", customer);
      return modelAndView;

    }else {
      ModelAndView modelAndView = new ModelAndView("/error.404");
      return modelAndView;
    }
  }

  //sau khi sua form xong, submit se hien thong bao success
  // hien thi reponse thong bao tai form vi k redict den trang khac
  @PostMapping("/edit")
  public ModelAndView updateCustomer(@ModelAttribute("customer") Customer customer){
    customerService.save(customer);

    ModelAndView modelAndView = new ModelAndView("/customer/edit");
    modelAndView.addObject("customer", customer);
    modelAndView.addObject("message", "Customer updated successfully");
    return modelAndView;
  }

  // Nhay den trang xoa de xac nhan xoa
  @GetMapping("/delete/{id}")
  public ModelAndView showDeleteForm(@PathVariable Long id){
    Customer customer = customerService.findById(id);
    if(customer != null) {
      ModelAndView modelAndView = new ModelAndView("/customer/delete");
      modelAndView.addObject("customer", customer);
      return modelAndView;

    }else {
      ModelAndView modelAndView = new ModelAndView("/error.404");
      return modelAndView;
    }
  }

  //sau khi submit nhay ve trang hien thi danh sach
  @PostMapping("/delete")
  public String deleteCustomer(@ModelAttribute("customer") Customer customer){
    customerService.remove(customer.getId());
    return "redirect:list";
  }
}
