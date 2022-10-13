package com.sotatek.rea.infrastructure.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "settlement")
@Table(schema = "settlement")
public class SettlementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    
    @Column(name = "createTime")
    public Date createTime;
    
    @Column(name = "retailId")
    public Long retailId;
    
    @Column(name = "state")
    public String state;
    
    @Column(name = "orderAmount")
    public long orderAmount;
    
    @Column(name = "retailAmount")
    public long retailAmount;
}
