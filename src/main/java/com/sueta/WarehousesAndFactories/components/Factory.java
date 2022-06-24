package com.sueta.WarehousesAndFactories.components;

import com.sueta.WarehousesAndFactories.Repository.WarehousesRepository;
import com.sueta.WarehousesAndFactories.entity.Warehouses;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class Factory {
    WarehousesRepository warehousesRepository;
    @Scheduled(fixedDelay = 5*1000)
    public void someMethod() {
        List<Warehouses> list = warehousesRepository.findAll();
        for (int i = 0; i < list.size(); i++) {
            Warehouses numbers = warehousesRepository.findById(list.get(i).getId()).get();
            if(numbers.getNumber() + 5 <= 5000) {
                numbers.setNumber(numbers.getNumber() + 5);
                warehousesRepository.save(numbers);
            }
        }
    }
}
