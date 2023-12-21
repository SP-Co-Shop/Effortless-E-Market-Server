package com.example.coshop.repository.seller;

import com.example.coshop.Entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

public interface SellerRepository extends JpaRepository<Seller,Long>, SellerRepositoryCustom {



}
