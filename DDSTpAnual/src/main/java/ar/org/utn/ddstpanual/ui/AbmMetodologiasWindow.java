package ar.org.utn.ddstpanual.ui;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.GroupPanel;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import java.awt.Color;

import ar.org.utn.ddstpanual.controllerOld.AbmMetodologiasController;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.metodologia.Condicion;
import ar.org.utn.ddstpanual.model.metodologia.Filtro;
import ar.org.utn.ddstpanual.model.metodologia.Orden;

public class AbmMetodologiasWindow extends SimpleWindow<AbmMetodologiasController> {

  private static final long serialVersionUID = 5698396008193940382L;

  public AbmMetodologiasWindow(final WindowOwner parent) {
    super(parent, new AbmMetodologiasController());
    this.getModelObject().inicializarVariables();
  }

  @Override
  protected void addActions(final Panel actionsPanel) {

    new Button(actionsPanel).setCaption("Guardar Metodologia").onClick(() -> {
      getModelObject().guardarMetodologia();
      getModelObject().inicializarVariables();
    }).setAsDefault().disableOnError();
  }

  @Override
  protected void createFormPanel(final Panel mainPanel) {
    final Panel inputFormPanel = new Panel(mainPanel);
    inputFormPanel.setLayout(new ColumnLayout(2));

    new Label(inputFormPanel).setText("Nombre").setForeground(Color.BLUE);
    new TextBox(inputFormPanel).setWidth(150).bindValueToProperty("nombre");

    crearGrupoOrdenes(mainPanel);
    crearGrupoCondiciones(mainPanel);

  }

  private void crearGrupoOrdenes(final Panel mainPanel) {
    GroupPanel grupoOrden = new GroupPanel(mainPanel);
    grupoOrden.setTitle("Orden");
    grupoOrden.setLayout(new ColumnLayout(2));

    new Label(grupoOrden).setText("Indicador");
    Selector<Indicador> selectorIndicadorOrder = new Selector<Indicador>(grupoOrden);
    selectorIndicadorOrder.setWidth(150);
    selectorIndicadorOrder.bindValueToProperty("indicadorOrdenCheckbox");
    selectorIndicadorOrder.bindItemsToProperty("indicadores2");

    new Label(grupoOrden).setText("Tipo");
    Selector<String> selectorTipo = new Selector<String>(grupoOrden);
    selectorTipo.setWidth(150);
    selectorTipo.bindValueToProperty("tipoOrdenCheckbox");
    selectorTipo.bindItemsToProperty("tiposOrdenes");

    new Button(grupoOrden).setCaption("Cargar Orden").onClick(() -> {
      getModelObject().cargarOrden();
      createResultsGridOrdenes(grupoOrden);
    }).setAsDefault().disableOnError();
  }

  private void crearGrupoCondiciones(final Panel mainPanel) {
    GroupPanel grupoFiltro = new GroupPanel(mainPanel);
    grupoFiltro.setTitle("Condiciones");
    grupoFiltro.setLayout(new ColumnLayout(2));

    new Label(grupoFiltro).setText("Indicador");
    Selector<Indicador> selectorIndicadorOrder = new Selector<Indicador>(grupoFiltro);
    selectorIndicadorOrder.setWidth(150);
    selectorIndicadorOrder.bindValueToProperty("indicadorCheckbox");
    selectorIndicadorOrder.bindItemsToProperty("indicadores");

    new Label(grupoFiltro).setText("Filtro");
    Selector<Filtro> selectorTipo = new Selector<Filtro>(grupoFiltro);
    selectorTipo.setWidth(150);
    selectorTipo.bindValueToProperty("tipoCondicionCheckbox");
    selectorTipo.bindItemsToProperty("tiposCondiciones");

    new Label(grupoFiltro).setText("Valor").setForeground(Color.BLUE);
    new TextBox(grupoFiltro).setWidth(150).bindValueToProperty("valor");

    new Button(grupoFiltro).setCaption("Cargar Condicion").onClick(() -> {
      getModelObject().cargarCondicion();
      createResultsGridIndicadores(grupoFiltro);
    }).setAsDefault().disableOnError();


  }

  @Override
  protected void createMainTemplate(final Panel mainPanel) {
    this.setTitle("ABM Metodologia");

    final Label error = new Label(mainPanel);
    error.setForeground(Color.RED).bindValueToProperty("error");

    super.createMainTemplate(mainPanel);
    this.createResultsGridIndicadores(mainPanel);
    this.createResultsGridOrdenes(mainPanel);
  }

  protected void createResultsGridIndicadores(final Panel mainPanel) {
    final Table<Condicion> table = new Table<Condicion>(mainPanel, Condicion.class);
    table.setNumberVisibleRows(4);
    table.setWidth(450);
    table.bindItemsToProperty("condiciones");
    this.describeResultsGridIndicador(table);
  }

  protected void createResultsGridOrdenes(final Panel mainPanel) {
    final Table<Orden> table = new Table<Orden>(mainPanel, Orden.class);
    table.setNumberVisibleRows(4);
    table.setWidth(450);
    table.bindItemsToProperty("ordenes");
    new Column<Orden>(table) //
        .setTitle("Indicador").setFixedSize(100).bindContentsToProperty("indicador");
    new Column<Orden>(table) //
        .setTitle("Tipo").setFixedSize(100).bindContentsToProperty("tipoOrden");
  }

  protected void describeResultsGridIndicador(final Table<Condicion> table) {
    new Column<Condicion>(table) //
        .setTitle("Indicador").setFixedSize(100).bindContentsToProperty("indicador");

    new Column<Condicion>(table) //
        .setTitle("Filtro").setFixedSize(150).bindContentsToProperty("filtro");

    new Column<Condicion>(table) //
        .setTitle("Valor").setFixedSize(150).bindContentsToProperty("valor");

  }


}
