package prueba.test.actions;

public class BusinessActor extends WebActioner {
    static BusinessActor actor;
    static Float price;
    static Float realprice;
    static Float shippingprice;
    static Float taxvalue;
    static String color;
    static String size;
    static int amount;
    static String productname;

    public static BusinessActor getInstance(){
        if(actor==null){
            actor=new BusinessActor();
        }
        return actor;
    }

    public static void setPrice(String s) {price=Float.parseFloat(s);}
    public Float getPrice(){return price;}

    public static void setShippingPrice(String shipping) {shippingprice= Float.parseFloat(shipping);}
    public Float getShippingPrice(){return shippingprice;}

    public static void setRealPrice(String s){realprice= Float.parseFloat(s);}
    public Float getRealprice(){return realprice;}

    public static void setColor(String c){color= c;}
    public String getColor(){return color;}

    public static void setSize(String c){size= c;}
    public String getSize(){return size;}

    public static void setAmount(String a){amount=Integer.parseInt(a);}
    public int getAmount(){return amount;}

    public void setProductName(String c){productname= c;}
    public String getProductName(){return productname;}

    public static void setTaxValue(String tax){taxvalue=Float.parseFloat(tax);}
    public Float getTaxValue(){return taxvalue;}
}
