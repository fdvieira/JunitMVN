public class OrderService {

  public void placeOrder(double orderTotal) throws OrderException {
    if (orderTotal < 0) {
      throw new OrderException("Order total has to be negatives!");
    }
  }

}
