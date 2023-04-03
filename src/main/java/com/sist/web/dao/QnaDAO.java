package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.QnaEntity;

@Repository
public interface QnaDAO extends JpaRepository<QnaEntity, Integer> {
	@Query(value="SELECT * from minit_qna ORDER BY qno DESC LIMIT :start,10",nativeQuery= true)
	public List<QnaEntity> qnaListData(@Param("start") Integer start);
	
	@Query(value="SELECT (COUNT(*)/10.0) FROM minit_qna",nativeQuery=true)
	public int qnaTotalPage();
	
	public QnaEntity findByQno(int qno);
}
