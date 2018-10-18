package com.revature.dao;

import java.util.List;

import com.revature.beans.Activation;

public interface ActivationDao {
	public List<Activation> selectAllActivation();
	public Activation selectActivationById(Integer id);
	public Activation setActivation(Activation activation);
}
