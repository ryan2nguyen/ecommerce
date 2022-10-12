package com.sotatek.prda.infrastructure.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sotatek.prda.domain.Account;
import com.sotatek.prda.domain.AccountHistory;
import com.sotatek.prda.domain.Customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "account_history")
@Table(schema = "account_history")
public class AccountHistoryEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
	
	@ManyToOne
    @JoinColumn(name = "customerId")
	public CustomerEntity customer;
	
	@Column(name = "type")
    public String type;
	
	@Column(name = "createTime")
    public Date createTime;
	
	@Column(name = "amount")
    public Long amount;
	
	@Column(name = "orderId")
    public Long orderId;
}
