package com.jack;

// this could be:
//   public outer of MsgControler
//   inner public
//   inner public static
//   inner static
//   inner
// to get json response
   
public class Result {
    String ret;
    String data;
    public Result(String ret, String data) {
	this.ret=ret;
	this.data=data;
    }
    public String getRet() {
	return ret;
    }
    public String getData() {
	return data;
    }
    public void setRet(String ret) {
	this.ret=ret;
    }
    public void setData(String data) {
	this.data=data;
    }
}
