package com.s3corp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LAYOUT")
public class Layout implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LAYOUT_ID")
	private Integer id;

	@Column(name = "LAYOUT_NAME", nullable = false)
	private String layoutName;

	public Layout() {
		super();
	}

	public Layout(Integer id, String layoutName) {
		super();
		this.id = id;
		this.layoutName = layoutName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLayoutName() {
		return layoutName;
	}

	public void setLayoutName(String layoutName) {
		this.layoutName = layoutName;
	}

	@Override
	public String toString() {
		return "Layout [id=" + id + ", layoutName=" + layoutName + "]";
	}

}
