package oodj.Dashboard.event;

public class ModelOrder {

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }
    

    public String getFurnitureName() {
        return furnitureName;
    }

    public void setFurnitureName(String furnitureName) {
        this.furnitureName = furnitureName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
    if (quantity >= 0) {
        this.quantity = quantity;
    } else {
        throw new IllegalArgumentException("Quantity cannot be negative");
    }
}

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
    
    public String getSalesperson() {
        return salesperson;
    }

    public void setSalesperson(String salesperson) {
        this.salesperson = salesperson;
    }
    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public ModelOrder(String orderID, String furnitureName, int quantity, String dateTime, String salesperson, String status){
        this.orderID = orderID;
        this.furnitureName = furnitureName;
        this.quantity = quantity;
        this.dateTime = dateTime;
        this.salesperson = salesperson;
        this.status = status;
    }
    
    private String orderID;
    private String furnitureName;
    private int quantity;
    private String dateTime;
    private String salesperson;
    private String status;

}
