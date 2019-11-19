package com.itheima.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * ���ԤԼ��Ϣ
 */
public class Order implements Serializable{
    public static final String ORDERTYPE_TELEPHONE = "�绰ԤԼ";
    public static final String ORDERTYPE_WEIXIN = "΢��ԤԼ";
    public static final String ORDERSTATUS_YES = "�ѵ���";
    public static final String ORDERSTATUS_NO = "δ����";
    private Integer id;
    private Integer memberId;//��Աid
    private Date orderDate;//ԤԼ����
    private String orderType;//ԤԼ���� �绰ԤԼ/΢��ԤԼ
    private String orderStatus;//ԤԼ״̬���Ƿ��
    private Integer setmealId;//����ײ�id

    public Order() {
    }

    public Order(Integer memberId, Date orderDate, Integer setmealId) {
        this.memberId = memberId;
        this.orderDate = orderDate;
        this.setmealId = setmealId;
    }

    public Order(Integer id) {
        this.id = id;
    }

    public Order(Integer memberId, Date orderDate, String orderType, String orderStatus, Integer setmealId) {
        this.memberId = memberId;
        this.orderDate = orderDate;
        this.orderType = orderType;
        this.orderStatus = orderStatus;
        this.setmealId = setmealId;
    }

    public Order(Integer id, Integer memberId, Date orderDate, String orderType, String orderStatus, Integer setmealId) {
        this.id = id;
        this.memberId = memberId;
        this.orderDate = orderDate;
        this.orderType = orderType;
        this.orderStatus = orderStatus;
        this.setmealId = setmealId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getSetmealId() {
        return setmealId;
    }

    public void setSetmealId(Integer setmealId) {
        this.setmealId = setmealId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", orderDate=" + orderDate +
                ", orderType='" + orderType + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", setmealId=" + setmealId +
                '}';
    }
}
