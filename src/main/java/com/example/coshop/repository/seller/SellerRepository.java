package com.example.coshop.repository.seller;

import com.example.coshop.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller,Long>, SellerRepositoryCustom {



}
