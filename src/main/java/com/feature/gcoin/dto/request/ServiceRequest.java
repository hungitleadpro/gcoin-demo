package com.feature.gcoin.dto.request;

import java.io.Serializable;

public class ServiceRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    Long serviceId;
    int total;

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
