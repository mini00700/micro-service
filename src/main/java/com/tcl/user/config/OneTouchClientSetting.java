package com.tcl.user.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "OneTouch")
public class OneTouchClientSetting {
	 private String AWSKey;
	 private String AWSSecret;
	 private String AWSIdentityPoolId;
	 private String StripeAuthorization;
	 private String StripeAccount;

	public String getAWSKey() {
		return AWSKey;
	}
	public void setAWSKey(String aWSKey) {
		AWSKey = aWSKey;
	}
	public String getAWSSecret() {
		return AWSSecret;
	}
	public void setAWSSecret(String aWSSecret) {
		AWSSecret = aWSSecret;
	}
	public String getAWSIdentityPoolId() {
		return AWSIdentityPoolId;
	}
	public void setAWSIdentityPoolId(String aWSIdentityPoolId) {
		AWSIdentityPoolId = aWSIdentityPoolId;
	}
	public String getStripeAuthorization() {
		return StripeAuthorization;
	}
	public void setStripeAuthorization(String stripeAuthorization) {
		StripeAuthorization = stripeAuthorization;
	}
	public String getStripeAccount() {
		return StripeAccount;
	}
	public void setStripeAccount(String stripeAccount) {
		StripeAccount = stripeAccount;
	}



}
