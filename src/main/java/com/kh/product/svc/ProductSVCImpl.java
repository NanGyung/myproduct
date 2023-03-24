package com.kh.product.svc;

import com.kh.product.dao.Product;
import com.kh.product.dao.ProductDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductSVCImpl implements ProductSVC{

  private final ProductDAO productDAO;

  /**
   * 등록
   *
   * @param product 상품(상품아이디, 상품명, 수량, 가격)
   * @return 상품아이디
   */
  @Override
  public Long save(Product product) {
    return productDAO.save(product);
  }

  /**
   * 조회
   *
   * @param pid 상품아이디
   * @return 상품
   */
  @Override
  public Optional<Product> findById(Long pid) {
    return productDAO.findById(pid);
  }

  /**
   * 수정
   *
   * @param pid 상품아이디
   * @param product   수정할 상품
   * @return 수정된 레코드 수
   */
  @Override
  public int update(Long pid, Product product) {
    return productDAO.update(pid,product);
  }

  /**
   * 삭제
   *
   * @param pid 상품아이디
   * @return 삭제된 레코드 수
   */
  @Override
  public int delete(Long pid) {
    return productDAO.delete(pid);
  }

  /**
   * 목록
   *
   * @return 상품목록
   */
  @Override
  public List<Product> findAll() {
    return productDAO.findAll();
  }
}
