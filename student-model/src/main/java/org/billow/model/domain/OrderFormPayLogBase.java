package org.billow.model.domain;  

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.billow.model.base.BaseModel;
  
/**
 * 
 * 订单支付日志表（支付、退款）数据库模型<br>
 *
 * 对应的表名：t_order_form_pay_log
 * @version 2.0
 * @author billow<br>
 * @Mail lyongtao123@126.com<br>
 * @date 2017-11-30 17:00:16
 */
public class OrderFormPayLogBase extends BaseModel implements Serializable { 
	
	public OrderFormPayLogBase() {
		super();
	}
	
	/**
	 * 主键构造器
	 * @param id 
	 */
	public OrderFormPayLogBase(String id ) {
		super();
		this.id = id;
	}
	
	// 创建时间
    private Date createTime;
	// 订单金额
    private BigDecimal totalAmount;
	// 操作人名称
    private String loginUserName;  
	// 订单号
    private String orderFormId;  
	// 业务号：trade_no-支付宝交易号
    private String businessNo;  
	// 操作人id
    private Integer loginUserId;  
	// 
    private String id;  
	// 买家支付用户号
    private String buyerId;  
	// 支付状态,1-支付失败，3-支付成功，2-退款失败，4-退款成功，98-未付款交易超时关闭，或支付完成后全额退款，99-交易结束，不可退款
    private String status;  
	// 返回报文
    private String info;  
      
	/**
	 * 创建时间
	 * 
	 * @return
	 * @author billow<br>
	 * @date: 2017-11-30 17:00:16
	 */
    public Date getCreateTime(){  
        return this.createTime;  
    } 
    
    /**
	 * 创建时间
	 * 
	 * @param createTime
	 * @author billow<br>
	 * @date: 2017-11-30 17:00:16
	 */
    public void setCreateTime(Date createTime){  
        this.createTime=createTime;  
    }  
     
	/**
	 * 订单金额
	 * 
	 * @return
	 * @author billow<br>
	 * @date: 2017-11-30 17:00:16
	 */
    public BigDecimal getTotalAmount(){  
        return this.totalAmount;  
    } 
    
    /**
	 * 订单金额
	 * 
	 * @param totalAmount
	 * @author billow<br>
	 * @date: 2017-11-30 17:00:16
	 */
    public void setTotalAmount(BigDecimal totalAmount){  
        this.totalAmount=totalAmount;  
    }  
     
	/**
	 * 操作人名称
	 * 
	 * @return
	 * @author billow<br>
	 * @date: 2017-11-30 17:00:16
	 */
    public String getLoginUserName(){  
        return this.loginUserName;  
    } 
    
    /**
	 * 操作人名称
	 * 
	 * @param loginUserName
	 * @author billow<br>
	 * @date: 2017-11-30 17:00:16
	 */
    public void setLoginUserName(String loginUserName){  
        this.loginUserName=loginUserName;  
    }  
     
	/**
	 * 订单号
	 * 
	 * @return
	 * @author billow<br>
	 * @date: 2017-11-30 17:00:16
	 */
    public String getOrderFormId(){  
        return this.orderFormId;  
    } 
    
    /**
	 * 订单号
	 * 
	 * @param orderFormId
	 * @author billow<br>
	 * @date: 2017-11-30 17:00:16
	 */
    public void setOrderFormId(String orderFormId){  
        this.orderFormId=orderFormId;  
    }  
     
	/**
	 * 业务号：trade_no-支付宝交易号
	 * 
	 * @return
	 * @author billow<br>
	 * @date: 2017-11-30 17:00:16
	 */
    public String getBusinessNo(){  
        return this.businessNo;  
    } 
    
    /**
	 * 业务号：trade_no-支付宝交易号
	 * 
	 * @param businessNo
	 * @author billow<br>
	 * @date: 2017-11-30 17:00:16
	 */
    public void setBusinessNo(String businessNo){  
        this.businessNo=businessNo;  
    }  
     
	/**
	 * 操作人id
	 * 
	 * @return
	 * @author billow<br>
	 * @date: 2017-11-30 17:00:16
	 */
    public Integer getLoginUserId(){  
        return this.loginUserId;  
    } 
    
    /**
	 * 操作人id
	 * 
	 * @param loginUserId
	 * @author billow<br>
	 * @date: 2017-11-30 17:00:16
	 */
    public void setLoginUserId(Integer loginUserId){  
        this.loginUserId=loginUserId;  
    }  
     
	/**
	 * 
	 * 
	 * @return
	 * @author billow<br>
	 * @date: 2017-11-30 17:00:16
	 */
    public String getId(){  
        return this.id;  
    } 
    
    /**
	 * 
	 * 
	 * @param id
	 * @author billow<br>
	 * @date: 2017-11-30 17:00:16
	 */
    public void setId(String id){  
        this.id=id;  
    }  
     
	/**
	 * 买家支付用户号
	 * 
	 * @return
	 * @author billow<br>
	 * @date: 2017-11-30 17:00:16
	 */
    public String getBuyerId(){  
        return this.buyerId;  
    } 
    
    /**
	 * 买家支付用户号
	 * 
	 * @param buyerId
	 * @author billow<br>
	 * @date: 2017-11-30 17:00:16
	 */
    public void setBuyerId(String buyerId){  
        this.buyerId=buyerId;  
    }  
     
	/**
	 * 支付状态,1-支付失败，3-支付成功，2-退款失败，4-退款成功，98-未付款交易超时关闭，或支付完成后全额退款，99-交易结束，不可退款
	 * 
	 * @return
	 * @author billow<br>
	 * @date: 2017-11-30 17:00:16
	 */
    public String getStatus(){  
        return this.status;  
    } 
    
    /**
	 * 支付状态,1-支付失败，3-支付成功，2-退款失败，4-退款成功，98-未付款交易超时关闭，或支付完成后全额退款，99-交易结束，不可退款
	 * 
	 * @param status
	 * @author billow<br>
	 * @date: 2017-11-30 17:00:16
	 */
    public void setStatus(String status){  
        this.status=status;  
    }  
     
	/**
	 * 返回报文
	 * 
	 * @return
	 * @author billow<br>
	 * @date: 2017-11-30 17:00:16
	 */
    public String getInfo(){  
        return this.info;  
    } 
    
    /**
	 * 返回报文
	 * 
	 * @param info
	 * @author billow<br>
	 * @date: 2017-11-30 17:00:16
	 */
    public void setInfo(String info){  
        this.info=info;  
    }  
     

	/**
	 * 主键toString 非主键不允许添加
	 */
	@Override
	public String toString() {
		return "PK[id = " + id + "]";
	}
}  