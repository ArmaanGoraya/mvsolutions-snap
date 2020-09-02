package com.mvsolutions.snap.services;

public class VerifyRequest {
    private String PlateNo;
    private String StateCode;
    private String SecurityKey = "jb234632e8027g";

    public VerifyRequest(String plateNo, String stateCode) {
        PlateNo = plateNo;
        StateCode = stateCode;
    }

    public String getStateCode() {
        return StateCode;
    }

    public void setStateCode(String stateCode) {
        StateCode = stateCode;
    }

    public String getSecurityKey() {
        return SecurityKey;
    }

    public void setSecurityKey(String securityKey) {
        SecurityKey = securityKey;
    }

    public String getPlateNo() {
        return PlateNo;
    }

    public void setPlateNo(String plateNo) {
        PlateNo = plateNo;
    }

    @Override
    public String toString() {
        return "Post{" +
                "PlateNo='" + PlateNo + '\'' +
                ", StateCode='" + StateCode + '\'' +
                ", SecurityKey='" + SecurityKey + '\'' +
                '}';
    }
}
