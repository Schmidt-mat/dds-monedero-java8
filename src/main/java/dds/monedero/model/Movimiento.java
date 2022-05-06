package dds.monedero.model;

import java.time.LocalDate;

public class Movimiento {
  private LocalDate fecha;
  // Nota: En ningún lenguaje de programación usen jamás doubles (es decir, números con punto flotante) para modelar dinero en el mundo real.
  // En su lugar siempre usen numeros de precision arbitraria o punto fijo, como BigDecimal en Java y similares
  // De todas formas, NO es necesario modificar ésto como parte de este ejercicio. 
  private double monto;
  private TipoMovimiento tipoMovimiento;
  public Movimiento(LocalDate fecha, double monto,TipoMovimiento tipoMovimiento) {
    this.fecha = fecha;
    this.monto = monto;
    this.tipoMovimiento=tipoMovimiento;
  }
  public Movimiento nuevoMovimiento(LocalDate fecha, double monto,TipoMovimiento tipoMovimiento){
    return new Movimiento(fecha,monto,tipoMovimiento);
  }

  public Double calcularElValorDe(Cuenta cuenta, Double cuanto){
    return getTipoMovimiento().calcularValor(cuenta,cuanto);
  }

  public TipoMovimiento getTipoMovimiento() {
    return tipoMovimiento;
  }

  public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
    this.tipoMovimiento = tipoMovimiento;
  }

  public double getMonto() {
    return monto;
  }

  public LocalDate getFecha() {
    return fecha;
  }

  public boolean esDeLaFecha(LocalDate fecha) {
    return this.fecha.equals(fecha);
  }

}
