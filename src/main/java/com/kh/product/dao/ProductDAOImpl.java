package com.kh.product.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProductDAOImpl implements ProductDAO{
  private final NamedParameterJdbcTemplate template;


  /**
   * 등록
   *
   * @param product 상품(상품아이디, 상품명, 수량, 가격)
   * @return 상품아이디
   */
  @Override
  public Long save(Product product) {
    StringBuffer sb = new StringBuffer();
    sb.append("insert into product(pid,pname,quantity,price) ");
    sb.append("values(product_pid_seq.nextval, :pname, :quantity, :price) ");

    BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(product);

    GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

    template.update(sb.toString(),param,keyHolder,new String[]{"pid"});
    long productId = keyHolder.getKey().longValue();  // 상품아이디


    return productId;
  }

  /**
   * 조회
   *
   * @param pid 상품아이디
   * @return 상품
   */
  @Override
  public Optional<Product> findById(Long pid) {
    StringBuffer sb = new StringBuffer();
    sb.append("select pid, pname, quantity, price ");
    sb.append("  from product ");
    sb.append(" where pid = :id ");

    try{
      Map<String,Long> param = Map.of("id",pid);

      Product product = template.queryForObject(sb.toString(),param, BeanPropertyRowMapper.newInstance(Product.class));
      return Optional.of(product);
    }catch (EmptyResultDataAccessException e){
      return Optional.empty();
    }
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
    StringBuffer sb = new StringBuffer();
    sb.append("update product ");
    sb.append("set pname = :pname, ");
    sb.append("quantity = :quantity, ");
    sb.append("price = :price ");
    sb.append("where pid = :id ");

    SqlParameterSource param = new MapSqlParameterSource()
        .addValue("pname",product.getPname())
        .addValue("quantity", product.getQuantity())
        .addValue("price",product.getPrice())
        .addValue("id",pid);

    return template.update(sb.toString(),param);
  }

  /**
   * 삭제
   *
   * @param pid 상품아이디
   * @return 삭제된 레코드 수
   */
  @Override
  public int delete(Long pid) {
    String sql = "delete from product where pid = :id";
    return template.update(sql,Map.of("id",pid));
  }
  @Override
  public int deleteParts(List<Long> pids) {
    String sql = "delete from product where pid in ( :ids ) ";
    Map<String, List<Long>> param = Map.of("ids", pids);
    return template.update(sql,param);
  }

  @Override
  public int deleteAll() {
    String sql = "delete from product ";
    Map<String,String> param = new LinkedHashMap<>();
    int deletedRowCnt = template.update(sql, param);
    return deletedRowCnt;
  }


  /**
   * 목록
   *
   * @return 상품목록
   */
  @Override
  public List<Product> findAll() {
    StringBuffer sb = new StringBuffer();
    sb.append("select pid, pname, quantity, price ");
    sb.append("from product ");

    List<Product> list = template.query(
      sb.toString(),
      BeanPropertyRowMapper.newInstance(Product.class)
    );

//    log.info("list={}",list);
    return list;
  }

  /**
   * 상품존재유무
   *
   * @param pid 상품아이디
   * @return
   */
  @Override
  public boolean isExist(Long pid) {
    boolean isExist = false;
    String sql = "select count(*) from product where pid = :pid";

    Map<String,Long> param = Map.of("pid",pid);
    Integer integer = template.queryForObject(sql, param, Integer.class);
    isExist = (integer > 0) ? true : false;
    return isExist;
  }

  /**
   * 등록된 상품수
   *
   * @return 레코드 건수
   */
  @Override
  public int countOfRecord() {
    String sql = "select count(*) from product ";
    Map<String,String> param = new LinkedHashMap<>();
    Integer rows = template.queryForObject(sql, param, Integer.class);
    return rows;
  }


}
