package orderList;

import java.util.List;

public class OrderList {
    private List<OrderFromList> orders;
    private PageInfo pageInfo;
    private List<AvailableStations> availableStations;

    public List<OrderFromList> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderFromList> orders) {
        this.orders = orders;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<AvailableStations> getAvailableStations() {
        return availableStations;
    }

    public void setAvailableStations(List<AvailableStations> availableStations) {
        this.availableStations = availableStations;
    }
}
