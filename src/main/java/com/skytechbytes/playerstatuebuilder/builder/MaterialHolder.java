package com.skytechbytes.playerstatuebuilder.builder;

import org.bukkit.Material;

public class MaterialHolder {
	Material m;
	boolean success = false;
	
	public MaterialHolder(Material m) {
		this.m = m;
	}
	

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Material getM() {
		return m;
	}

	public void setM(Material m) {
		this.m = m;
	}
	
}
