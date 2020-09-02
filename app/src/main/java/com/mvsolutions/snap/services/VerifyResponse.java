package com.mvsolutions.snap.services;

public class VerifyResponse {
    String ResponseCode;
    String PolicyNo;
    String NaicNo;
    String CompName;
    String ErrorMessage;

    public String getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(String responseCode) {
        ResponseCode = responseCode;
    }

    public String getPolicyNo() {
        return PolicyNo;
    }

    public void setPolicyNo(String policyNo) {
        PolicyNo = policyNo;
    }

    public String getNaicNo() {
        return NaicNo;
    }

    public void setNaicNo(String naicNo) {
        NaicNo = naicNo;
    }

    public String getCompName() {
        return CompName;
    }

    public void setCompName(String compName) {
        CompName = compName;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "VerifyResponse{" +
                "ResponseCode='" + ResponseCode + '\'' +
                ", PolicyNo='" + PolicyNo + '\'' +
                ", NaicNo='" + NaicNo + '\'' +
                ", CompName='" + CompName + '\'' +
                ", ErrorMessage='" + ErrorMessage + '\'' +
                '}';
    }

}
