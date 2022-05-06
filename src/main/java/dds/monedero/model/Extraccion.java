package dds.monedero.model;

public class Extraccion implements TipoMovimiento{
  public double calcularValor(Cuenta cuenta,double monto) {
      return cuenta.getSaldo() - monto;
  }
}
