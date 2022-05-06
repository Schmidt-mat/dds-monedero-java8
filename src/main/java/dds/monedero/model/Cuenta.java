package dds.monedero.model;

import dds.monedero.exceptions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cuenta {

  private double saldo;
  private List<Movimiento> extracciones = new ArrayList<>();
  private List<Movimiento> depositos = new ArrayList<>();

  public Cuenta() {
    saldo = 0;
  }

  public Cuenta(double montoInicial) {
    saldo = montoInicial;
  }

  //public void setMovimientos(List<Movimiento> movimientos) {this.movimientos = movimientos;  } no se puede setear una lista, la idea es meter de a uno por vez, no hacer =

  public void poner(double cuanto) {
    if (cuanto <= 0) {
      throw new MontoNegativoException(cuanto + ": el monto a ingresar debe ser un valor positivo");
    }

    if (depositos.size()>= 3) {
      throw new MaximaCantidadDepositosException("Ya excedio los " + 3 + " depositos diarios");
    }
    agregarNuevoDeposito(LocalDate.now(),cuanto);
  }

  public void sacar(double cuanto) {
    if (cuanto <= 0) {
      throw new MontoNegativoException(cuanto + ": el monto a ingresar debe ser un valor positivo");
    }
    if (getSaldo() - cuanto < 0) {
      throw new SaldoMenorException("No puede sacar mas de " + getSaldo() + " $");
    }
    double montoExtraidoHoy = getMontoExtraidoA(LocalDate.now());
    double limite = 1000 - montoExtraidoHoy;
    if (cuanto > limite) {
      throw new MaximoExtraccionDiarioException("No puede extraer mas de $ " + 1000
          + " diarios, límite: " + limite);
    }
    agregarNuevaExtraccion(LocalDate.now(), cuanto);
  }

  public void agregarNuevaExtraccion(LocalDate cuando, double cuanto) {
    Movimiento extraccionNueva =new Movimiento(cuando, cuanto,new Extraccion());
    setSaldo(extraccionNueva.calcularElValorDe(this,cuanto));
    extracciones.add(extraccionNueva);
  }
  public void agregarNuevoDeposito(LocalDate cuando, double cuanto) {
    Movimiento depositoNuevo =new Movimiento(cuando, cuanto,new Deposito());
    setSaldo(depositoNuevo.calcularElValorDe(this,cuanto));
    depositos.add(depositoNuevo);
  }

  public double getMontoExtraidoA(LocalDate fecha) {
    return extracciones.stream().mapToDouble(extraccion->extraccion.getMonto()).sum();
  }

  public double getSaldo() {
    return saldo;
  }

  public void setSaldo(double saldo) {
    this.saldo = saldo;
  }


  public void setExtracciones(List<Movimiento> extracciones) {
    this.extracciones = extracciones;
  }
  public void agregarDeposito(Movimiento depositoAAgregar) {
    this.depositos.add(depositoAAgregar);
  }
  public void agregarExtraccion(Movimiento extraccionAAgregar) {
    this.depositos.add(extraccionAAgregar);
  }

  public List<Movimiento> getDepositos() {
    return depositos;
  }

  public void setDepositos(List<Movimiento> depositos) {
    this.depositos = depositos;
  }
}
