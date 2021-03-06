package com.jvm.constant;

public class FieldRefInfo extends ConstantInfo{
	private int tag = ConstantInfo.FIELDREF_INFO;
	
	private int class_index;
	
	private int nameAndType_index;

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public int getClass_index() {
		return class_index;
	}

	public void setClass_index(int class_index) {
		this.class_index = class_index;
	}

	public int getNameAndType_index() {
		return nameAndType_index;
	}

	public void setNameAndType_index(int nameAndType_index) {
		this.nameAndType_index = nameAndType_index;
	}

	@Override
	public String toString() {
		return "FieldrefInfo [tag=" + tag + ", class_index=" + class_index
				+ ", nameAndType_index=" + nameAndType_index + "]";
	}

	@Override
	public void accept(Vistor vistor) {
		vistor.visitFieldRef(this);
		
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return getTag();
	}
	
	
	
}
