public class VoucherGeneratorService {

  public double generateVoucher(double orderTotal) {
    if (orderTotal < 100) {
      return orderTotal * 0.125;
    }
    return orderTotal * 0.1;
  }
}
