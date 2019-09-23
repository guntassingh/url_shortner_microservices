package com.rsystems.services;

import org.springframework.stereotype.Service;

import com.rsystems.exceptions.UnAuthorizedException;
import com.rsystems.utils.Constants;

@Service
public class ThirdPartyServiceImpl implements ThirdPartyService {

	@Override
	public boolean validateToken(String userToken) {

		if (userToken.equals(Constants.VALID_USER)) {
			return Boolean.TRUE;
		}
		throw new UnAuthorizedException(Constants.UNAUTHORIZED_USER);
	}
}
