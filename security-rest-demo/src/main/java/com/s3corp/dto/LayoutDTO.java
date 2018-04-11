package com.s3corp.dto;

public class LayoutDTO {

	private Integer id;
	private String layoutName;

	public LayoutDTO() {
		super();
	}

	public LayoutDTO(Integer id, String layoutName) {
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
		return "LayoutDTO [id=" + id + ", layoutName=" + layoutName + "]";
	}

}
