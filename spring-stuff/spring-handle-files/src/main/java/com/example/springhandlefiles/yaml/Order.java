package com.example.springhandlefiles.yaml;

import java.time.LocalDate;
import java.util.List;

public class Order {
    String orderNo;
    String customerName;
    List<OrderLine> orderLines;


    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    @Override
    public String toString() {
        return "YamlExample{" +
                "orderNo='" + orderNo + '\'' +
                ", customerName='" + customerName + '\'' +
                ", orderLines=" + orderLines +
                '}';
    }
}
