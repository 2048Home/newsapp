package com.example.api;


public class API {
	
//	public final static String COMMON_URL="http://120.25.166.191";
	public final static String COMMON_URL="http://192.168.31.180:8080";
    //展示商品
	public  final static int HOTSALE_API=123; 
	public  final static String HOTSALE_URL=COMMON_URL+"/Vegetable/servlet/goodShowServlet?"; 
	public  final static String HOTSALE_MESSAGE="com.example.hotsale"; 
	
	
	//登录
	public  final static int LOGIN_API=566; 
	public  final static String LOGIN_URL=COMMON_URL+"/Vegetable/servlet/LoginServletApp?"; 
	public  final static String LOGIN_MESSAGE="com.example.login"; 
	
	//修改密码
	public  final static int ChangPassword_API=567; 
	public  final static String ChangPassword_URL=COMMON_URL+"/Vegetable/servlet/ChangePassword?"; 
	public  final static String ChangPassword_MESSAGE="com.example.changepassword"; 
	
	//下订单
	public  final static int RequestOrder_API=211; 
	public  final static String RequestOrder_URL=COMMON_URL+"/Vegetable/servlet/OrderRequestServlet?"; 
	public  final static String RequestOrder_MESSAGE="com.example.requestorder"; 
	
	//展示已经完成的订单
	public  final static int FinishOrderOrder_API=212; 
	public  final static String FinishOrderOrder_URL=COMMON_URL+"/Vegetable/servlet/FinishOrderServlet?"; 
	public  final static String FinishOrderOrder_MESSAGE="com.example.finishtorder"; 
	
	//注册
	public  final static int REGISTER_API=568; 
	public  final static String REGISTER_URL=COMMON_URL+"/Vegetable/servlet/RegisterApp?"; 
	public  final static String REGISTER_MESSAGE="com.example.register"; 
	
	//获取地址
	public  final static int ADDRESS_API=613; 
	public  final static String ADDRESS_URL=COMMON_URL+"/Vegetable/servlet/UserAddressServlet?"; 
	public  final static String ADDRESS_MESSAGE="com.example.address"; 
	
	//修改地址
	public  final static int CHANGE_ADDRESS_API=612; 
	public  final static String CHANGE_ADDRESS_URL=COMMON_URL+"/Vegetable/servlet/ChangAddressServlet?"; 
	public  final static String CHANGE_ADDRESS_MESSAGE="com.example.changeaddress"; 
	
	//确认收货
	public final static int CONFIRM_ORDER=710;
	public final static String CONFIRM_ORDER_URL=COMMON_URL+"/Vegetable/servlet/ConfirmGood?";
	public final static String CONFIRM_ORDER_MESSAGE="com.example.confirmorder";
}
