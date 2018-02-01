package com.abhinendra.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

import javax.persistence.*;

@Entity
@Data
@Table(name="Person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated person ID")
    private int id;

    @Column(name="PayerName")
    @ApiModelProperty(notes = "The name of  person", required = true)
    //private String RefId;
    private String payerName;
    private String payeeName;
    //private String payerAccount;
    //private String payeeAccount;
    private float amount;
    private Date date;

    public  Person(){
    }
    public Person(String payerName,String payeeName,Date date,float amount){
        //this.RefId = RefId;
        this.payerName = payerName;
        //this.payerAccount = payerAccount;
        this.payeeName = payeeName;
        //this.payeeAccount = payeeAccount;
        this.date = date;
        this.amount = amount;
    }
    @Override
    public String toString() {
      return " PayerName: "+payerName+" PayeeName: "+payeeName+" Amount: "+amount+" Date: "+date;
    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPayerName() {
		return payerName;
	}
	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}
	public String getPayeeName() {
		return payeeName;
	}
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
    
}




