package oodj.Dashboard.event;

public interface TableActionEvent {

    public void onViewDetail(int row);

    public void onOrder(int row);
    
    public void setQuantity(int row,int quantity);
}
