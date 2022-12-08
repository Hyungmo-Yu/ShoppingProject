package domain;

public class Basket
{
	int nBasketID;
	int userid;
	int productid;
	int numbers;
	int validity;
	
	public Basket(int nBasketID, int userid, int productid, int numbers, int validity)
	{
		this.nBasketID = nBasketID;
		this.userid = userid;
		this.productid = productid;
		this.numbers = numbers;
		this.validity = validity;
	}
	
	public int getBasketID()
	{
		return nBasketID;
	}
	
	public void setBasketid(int nBasketID)
	{
		this.nBasketID = nBasketID;
	}
	
	public int getUserid()
	{
		return userid;
	}
	
	public void setUserid(int userid)
	{
		this.userid = userid;
	}
	
	public int getProductid()
	{
		return productid;
	}
	
	public void setProductid(int productid)
	{
		this.productid = productid;
	}
	
	public int getNumbers()
	{
		return numbers;
	}
	
	public void setNumbers(int numbers)
	{
		this.numbers = numbers;
	}
	
	public int getValidity()
	{
		return validity;
	}
	
	public void setValidity(int validity)
	{
		this.validity = validity;
	}
}