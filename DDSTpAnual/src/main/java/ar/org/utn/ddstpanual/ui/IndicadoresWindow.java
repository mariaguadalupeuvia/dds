package ar.org.utn.ddstpanual.ui;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import java.awt.Color;
import java.util.ArrayList;

import ar.org.utn.ddspanual.controller.IndicadoresController;
import ar.org.utn.ddstpanual.model.EmpresaExcel;

@SuppressWarnings("serial")
public class IndicadoresWindow extends SimpleWindow<IndicadoresController> {

  public IndicadoresWindow(WindowOwner parent) {
    super(parent, new IndicadoresController());
    getModelObject().setError("");
    getModelObject().setEmpresasCheckbox(new ArrayList<String>());
    getModelObject().setCuentasCheckbox(new ArrayList<String>());
    getModelObject().setPeriodosCheckbox(new ArrayList<String>());
    getModelObject().obtenerIndicadores();
    getModelObject().obtenerEmpresas();
  }

  @Override
  protected void addActions(Panel actionsPanel) {
    // new Button(actionsPanel).setCaption("Guardar").onClick(() -> {
    // getModelObject().guardarIndicador();x
    // }).setAsDefault().disableOnError();
  }

  @Override
  protected void createFormPanel(Panel mainPanel) {
    Panel inputFormPanel = new Panel(mainPanel);
    inputFormPanel.setLayout(new ColumnLayout(2));

    new Label(inputFormPanel).setText("Indicador").setForeground(Color.BLUE);
    new Selector<String>(inputFormPanel).setWidth(150).bindValueToProperty("indicadores");
    new Label(inputFormPanel).setText("Empresa").setForeground(Color.BLUE);
    new Selector<String>(inputFormPanel).setWidth(150).bindValueToProperty("empresasCheckbox");
    new Label(inputFormPanel).setText("Cuenta").setForeground(Color.BLUE);
    new Selector<String>(inputFormPanel).setWidth(150).bindValueToProperty("cuentasCheckbox");
    new Label(inputFormPanel).setText("Periodo").setForeground(Color.BLUE);
    new Selector<String>(inputFormPanel).setWidth(150).bindValueToProperty("periodosCheckbox");
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
