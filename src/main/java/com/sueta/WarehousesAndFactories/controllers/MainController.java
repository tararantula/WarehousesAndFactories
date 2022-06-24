package com.sueta.WarehousesAndFactories.controllers;

import com.sueta.WarehousesAndFactories.Repository.WarehousesRepository;
import com.sueta.WarehousesAndFactories.entity.Warehouses;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.Async;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

@RestController
@AllArgsConstructor
@RequestMapping("/warehousesAndFactories")
public class MainController {
    WarehousesRepository warehousesRepository;
//добавть продукт
    @PostMapping("/addProduct")
    public void addProduct(@RequestParam String nameWarehouses, @RequestParam String nameProduct, @RequestParam String description, @RequestParam int number) {
        warehousesRepository.save(Warehouses.builder().nameWarehouse(nameWarehouses).nameProduct(nameProduct).description(description).number(number).build());
    }
//пополнение магазина со склада
    @PostMapping("/productDelivery/{idProductFromWarehouse}&{number}&{name}&{description}")
    public ResponseEntity<?> productDelivery(@PathVariable Integer idProductFromWarehouse, @PathVariable int number, @PathVariable String name, @PathVariable String description) {
        Warehouses warehouses = warehousesRepository.findById(idProductFromWarehouse).get();
        if (warehouses.getNumber() > number && warehouses.getNameProduct().equals(name) && warehouses.getDescription().equals(description)) {
            warehouses.setNumber(warehouses.getNumber() - number);
            warehousesRepository.save(warehouses);
            return ResponseEntity.ok().build();
        }
            return ResponseEntity.status(400).build();
    }
}

