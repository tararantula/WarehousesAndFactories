package com.sueta.WarehousesAndFactories.Repository;

import com.sueta.WarehousesAndFactories.entity.Warehouses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface WarehousesRepository extends JpaRepository<Warehouses,Integer> {

}
