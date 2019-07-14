package com.jiangjie.ohs.dto;

public class WhereInfo {

	private String keyInfo;
	private String keyChnInfo;

	public String getKeyInfo() {
		return keyInfo;
	}

	public void setKeyInfo(String keyInfo) {
		this.keyInfo = keyInfo;
	}

	public String getKeyChnInfo() {
		return keyChnInfo;
	}

	public void setKeyChnInfo(String keyChnInfo) {
		this.keyChnInfo = keyChnInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((keyChnInfo == null) ? 0 : keyChnInfo.hashCode());
		result = prime * result + ((keyInfo == null) ? 0 : keyInfo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WhereInfo other = (WhereInfo) obj;
		if (keyChnInfo == null) {
			if (other.keyChnInfo != null)
				return false;
		} else if (!keyChnInfo.equals(other.keyChnInfo))
			return false;
		if (keyInfo == null) {
			if (other.keyInfo != null)
				return false;
		} else if (!keyInfo.equals(other.keyInfo))
			return false;
		return true;
	}
	
	

}
