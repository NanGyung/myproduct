package com.kh.product.dao;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@Slf4j
@SpringBootTest
public class ProductDAOImplTest {
  @Autowired
  ProductDAO productDAO;

//  조회
  @Test
  @DisplayName("상품조회")
  void findById(){
    Long pid = 15L;
    Optional<Product> product = productDAO.findById(pid);
    Product findedProduct = product.orElseThrow();
    Assertions.assertThat(findedProduct.getPname()).isEqualTo("자전수정");
    Assertions.assertThat(findedProduct.getQuantity()).isEqualTo(20L);
    Assertions.assertThat(findedProduct.getPrice()).isEqualTo(15000L);
  }

  //수정
  @Test
  @DisplayName("상품수정")
  void update() {
    Long pid = 15L;
    Product product = new Product();
    product.setPname("자전거_수정");
    product.setQuantity(20L);
    product.setPrice(2000L);
    int updatedRowCount = productDAO.update(pid, product);
    Optional<Product> findedProduct = productDAO.findById(pid);

    Assertions.assertThat(updatedRowCount).isEqualTo(1);
    Assertions.assertThat(findedProduct.get().getPname()).isEqualTo(product.getPname());
    Assertions.assertThat(findedProduct.get().getQuantity()).isEqualTo(product.getQuantity());
    Assertions.assertThat(findedProduct.get().getPrice()).isEqualTo(product.getPrice());
  }
}
