package com.rakesh.practice.api.commons.factory;

import java.util.List;

import com.rakesh.practice.api.commons.model.ApiServerResponse;
import com.rakesh.practice.api.commons.model.CanonicalAPIProxyModel;

public abstract class ApiProductFactory {
	
//	public abstract List<Policy> getPolicies();
	
	public abstract List<ApiServerResponse> createAndApplyPolicy(CanonicalAPIProxyModel canonicalModel) throws Exception;
	
}
