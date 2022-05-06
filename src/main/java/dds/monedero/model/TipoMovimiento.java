package dds.monedero.model;

public interface  TipoMovimiento {
  double calcularValor(Cuenta cuenta,double monto);
}
