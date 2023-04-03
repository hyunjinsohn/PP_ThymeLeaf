package com.sist.web.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/*
 * 	qno int AI PK 
	subject varchar(1000) 
	content text 
	pwd varchar(34) 
	regdate datetime 
	id varchar(34)
 */
@Entity
@Table(name="qna")
@Getter
@Setter
public class QnaEntity {
	@Id
	private Integer qno;
	private String subject,content,pwd,id;
	private LocalDateTime regdate;
	
	@PrePersist
	public void regdate() {
		this.regdate=LocalDateTime.now();
	}
}
