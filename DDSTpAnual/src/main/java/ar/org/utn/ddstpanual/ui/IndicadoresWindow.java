package ar.org.utn.ddstpanual.ui;

import org.uqbar.arena.bindings.ObservableProperty;
import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.lacar.ui.model.ListBuilder;
import org.uqbar.lacar.ui.model.bindings.Binding;

import java.awt.Color;

import ar.org.utn.ddspanual.controller.IndicadoresController;
import ar.org.utn.ddstpanual.model.Cuenta;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.EmpresaExcel;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.Periodo;

@SuppressWarnings("serial")
public class IndicadoresWindow extends SimpleWindow<IndicadoresController> {

  public IndicadoresWindow(WindowOwner parent) {
    super(parent, new IndicadoresController());
    getModelObject().setError("");
    getModelObject().obtenerIndicadores();
    getModelObject().obtenerEmpresas();
  }

  @Override
  protected void addActions(Panel actionsPanel) {
    new Button(actionsPanel).setCaption("Ejecutar").onClick(() -> {
      getModelObject().ejecutarIndicador();
    }).setAsDefault().disableOnError();
  }


  @Override
  protected void createFormPanel(Panel mainPanel) {
    Panel inputFormPanel = new Panel(mainPanel);
    inputFormPanel.setLayout(new ColumnLayout(2));

    new Label(inputFormPanel).setText("Indicador").setForeground(Color.BLUE);
    Selector<Indicador> selectorIndicador =
        new Selector<Indicador>(inputFormPanel).allowNull(false);
    selectorIndicador.bindValueToProperty("indicador");
    selectorIndicador.setWidth(150);
    @SuppressWarnings({"unchecked", "rawtypes"})
    Binding<Indicador, Selector<Indicador>, ListBuilder<Indicador>> propiedadIndicador =
        selectorIndicador.bindItems(new ObservableProperty(getModelObject(), "indicadores"));
    propiedadIndicador.setAdapter(new PropertyAdapter(Indicador.class, "nombre"));

    new Label(inputFormPanel).setText("Empresa").setForeground(Color.BLUE);
    Selector<Empresa> selectorEmpresa = new Selector<Empresa>(inputFormPanel).allowNull(false);
    selectorEmpresa.bindValueToProperty("empresaCheckbox");
    selectorEmpresa.setWidth(150);
    @SuppressWarnings({"unchecked", "rawtypes"})
    Binding<Empresa, Selector<Empresa>, ListBuilder<Empresa>> propiedadEmpresa =
        selectorEmpresa.bindItems(new ObservableProperty(getModelObject(), "empresas"));
    propiedadEmpresa.setAdapter(new PropertyAdapter(Empresa.class, "nombre"));


    new Label(inputFormPanel).setText("Cuenta").setForeground(Color.BLUE);
    Selector<Cuenta> selectorCuenta = new Selector<Cuenta>(inputFormPanel).allowNull(false);
    selectorCuenta.bindValueToProperty("cuentaCheckbox");
    selectorCuenta.setWidth(150);
    @SuppressWarnings({"unchecked", "rawtypes"})
    Binding<Cuenta, Selector<Cuenta>, ListBuilder<Cuenta>> propiedadCuenta = selectorCuenta
        .bindItems(new ObservableProperty(getModelObject(), "empresaCheckbox.cuentas"));
    propiedadCuenta.setAdapter(new PropertyAdapter(Cuenta.class, "nombre"));

    new Label(inputFormPanel).setText("Periodo").setForeground(Color.BLUE);
    Selector<Periodo> selectorPeriodo = new Selector<Periodo>(inputFormPanel).allowNull(false);
    selectorPeriodo.bindValueToProperty("periodoCheckbox");
    selectorPeriodo.setWidth(150);
    @SuppressWarnings({"unchecked", "rawtypes"})
    Binding<Periodo, Selector<Periodo>, ListBuilder<Periodo>> propiedadPeriodo = selectorPeriodo
        .bindItems(new ObservableProperty(getModelObject(), "cuentaCheckbox.periodos"));
    propiedadPeriodo.setAdapter(new PropertyAdapter(Periodo.class, "fecha"));
  }

  @Override
  protected void createMainTemplate(Panel mainPanel) {
    this.setTitle("Indicador");

    Label error = new Label(mainPanel);
    error.setForeground(Color.RED).bindValueToProperty("error");

    super.createMainTemplate(mainPanel);

    this.createResultsGridIndicadores(mainPanel);
  }

  protected void createResultsGridIndicadores(Panel mainPanel) {
    Table<EmpresaExcel> table = new Table<EmpresaExcel>(mainPanel, EmpresaExcel.class);
    table.setNumberVisibleRows(4);
    table.setWidth(450);

    table.bindItemsToProperty("tabla");

    this.describeResultsGridIndicador(table);
  }

  protected void describeResultsGridIndicador(Table<EmpresaExcel> table) {
    new Column<EmpresaExcel>(table).setTitle("Cuenta").setFixedSize(100)
        .bindContentsToProperty("nombreCuenta");
    new Column<EmpresaExcel>(table).setTitle("Periodo").setFixedSize(150)
        .bindContentsToProperty("fecha");
    new Column<EmpresaExcel>(table).setTitle("Indicador").setFixedSize(150)
        .bindContentsToProperty("valor");
  }

}
