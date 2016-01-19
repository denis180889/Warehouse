package com.entities;

import javax.xml.bind.annotation.*;


//@XmlRootElement
//@XmlAccessorType(XmlAccessType.FIELD)
public class Item {
	public String type = "food";
	public int price = 10;
	public String name;
	public String msg;
	
	public Item() {

	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
