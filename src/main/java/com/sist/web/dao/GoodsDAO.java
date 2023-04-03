package com.sist.web.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.*;
@Repository
public interface GoodsDAO extends JpaRepository<GoodsEntity, Integer> {
	 @Query(value="SELECT gno,name,price,info,poster FROM minit_goods",nativeQuery = true)
	  public List<GoodsEntity> goodsListData();
	public GoodsEntity findByGno(@Param("gno") Integer gno);

	@Query(value="SELECT * FROM minit_goods "
		       +"WHERE name LIKE CONCAT('%',:name,'%') ORDER BY gno LIMIT :start,50",nativeQuery = true)
	public List<GoodsEntity> goodsFindData(@Param("name") String name,@Param("start") Integer start);
	
	@Query(value="SELECT CEIL(COUNT(*)/10.0) FROM minitmute_goods "
		       +"WHERE name LIKE CONCAT('%',:name,'%')",nativeQuery = true)
	public int goodsFindTotalPage(String name);

	

}
