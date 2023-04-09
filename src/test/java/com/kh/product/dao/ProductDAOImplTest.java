package com.kh.product.dao;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@SpringBootTest
public class ProductDAOImplTest {
  @Autowired
  ProductDAO productDAO;

//  등록
  @Test
  @DisplayName("상품등록")
  void save(){
    Product product = new Product();
    product.setPname("딸기");
    product.setQuantity(20L);
    product.setPrice(1000L);
    Long pid = productDAO.save(product);
    log.info("pid={}",pid);
    Assertions.assertThat(pid).isGreaterThan(0L);
  }

//  조회
  @Test
  @DisplayName("상품조회")
  void findById(){
    Long pid = 22L;
    Optional<Product> product = productDAO.findById(pid);
    Product findedProduct = product.orElseThrow();
    Assertions.assertThat(findedProduct.getPname()).isEqualTo("복숭아");
    Assertions.assertThat(findedProduct.getQuantity()).isEqualTo(40L);
    Assertions.assertThat(findedProduct.getPrice()).isEqualTo(20000L);
  }

  //수정
  @Test
  @DisplayName("상품수정")
  void update() {
    Long pid = 22L;
    Product product = new Product();
    product.setPname("복숭아_수정");
    product.setQuantity(40L);
    product.setPrice(20000L);
    int updatedRowCount = productDAO.update(pid, product);
    Optional<Product> findedProduct = productDAO.findById(pid);

    Assertions.assertThat(updatedRowCount).isEqualTo(1);
    Assertions.assertThat(findedProduct.get().getPname()).isEqualTo(product.getPname());
    Assertions.assertThat(findedProduct.get().getQuantity()).isEqualTo(product.getQuantity());
    Assertions.assertThat(findedProduct.get().getPrice()).isEqualTo(product.getPrice());
  }

//  삭제
  @Test
  @DisplayName("상품삭제")
  void delete(){
    Long pid = 4L;
    int deleteRowCnt = productDAO.delete(pid);
    Optional<Product> findedProduct = productDAO.findById(pid);

    Assertions.assertThatThrownBy(()->findedProduct.orElseThrow()).isInstanceOf(NoSuchElementException.class);

  }
//  전체삭제
  @Test
  @DisplayName("전체삭제")
  void deleteAll(){
    productDAO.deleteAll();
    int cntOfRecord = productDAO.countOfRecord();
    Assertions.assertThat(cntOfRecord).isEqualTo(0);
  }

//   등록된 상품 개수
  @Test
  @DisplayName("등록된 상품 개수")
  void countOfRecord(){
    int cntOfRecord = productDAO.countOfRecord();
    log.info("countOfRecord = {}",cntOfRecord);
  }

//  부분삭제
  @Test
  @DisplayName("부분 삭제")
  void deleteParts(){
    List<Long> pids = Arrays.asList(41L, 45L, 43L);
    int rows = productDAO.deleteParts(pids);
    Assertions.assertThat(rows).isEqualTo(pids.size());
  }

  @Test
  @DisplayName("상품존재")
  void isExist(){
    Long prodocutId = 44L;
    boolean exist = productDAO.isExist(prodocutId);
    Assertions.assertThat(exist).isTrue();
  }

  @Test
  @DisplayName("상품존재하지 않음")
  void isExist2(){
    Long prodocutId = 0L;
    boolean exist = productDAO.isExist(prodocutId);
    Assertions.assertThat(exist).isFalse();
  }
}
