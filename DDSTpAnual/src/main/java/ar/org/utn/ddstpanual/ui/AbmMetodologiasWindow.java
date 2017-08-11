package ar.org.utn.ddstpanual.ui;

import org.uqbar.arena.bindings.ObservableProperty;
import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.lacar.ui.model.ListBuilder;
import org.uqbar.lacar.ui.model.bindings.Binding;

import java.awt.Color;

import ar.org.utn.ddstpanual.controller.AbmMetodologiasController;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.metodologia.Condicion;
import ar.org.utn.ddstpanual.model.metodologia.Filtro;
import ar.org.utn.ddstpanual.model.metodologia.TipoOrden;

public class AbmMetodologiasWindow extends SimpleWindow<AbmMetodologiasController> {

  private static final long serialVersionUID = 5698396008193940382L;

  public AbmMetodologiasWindow(final WindowOwner parent) {
    super(parent, new AbmMetodologiasController());
    this.getModelObject().inicializarVariables();
  }

  @Override
  protected void addActions(final Panel actionsPanel) {
    new Button(actionsPanel).setCaption("Cargar Condicion").onClick(() -> {
      getModelObject().cargarCondicion();
      createResultsGridIndicadores(actionsPanel);
    }).setAsDefault().disableOnError();

    new Button(actionsPanel).setCaption("Guardar Metodologia").onClick(() -> {
      getModelObject().guardarMetodologia();
      getModelObject().inicializarVariables();
      createResultsGridIndicadores(actionsPanel);
    }).setAsDefault().disableOnError();
  }

  @Override
  protected void createFormPanel(final Panel mainPanel) {
    final Panel inputFormPanel = new Panel(mainPanel);
    inputFormPanel.setLayout(new ColumnLayout(2));

    new Label(inputFormPanel).setText("Nombre").setForeground(Color.BLUE);
    new TextBox(inputFormPanel).setWidth(150).bindValueToProperty("nombre");

    new Label(inputFormPanel).setText("Indicador de Orden").setForeground(Color.BLUE);
    final Selector<Indicador> selectorIndicadorOrder = new Selector<Indicador>(inputFormPanel).allowNull(false);
    selectorIndicadorOrder.bindValueToProperty("indicadorOrdenCheckbox");
    selectorIndicadorOrder.setWidth(150);
    @SuppressWarnings({"unchecked", "rawtypes"})
    final Binding<Indicador, Selector<Indicador>, ListBuilder<Indicador>> propiedadIndicadorOrden =
        selectorIndicadorOrder.bindItems(new ObservableProperty(getModelObject(), "indicadoresSeleccionados"));
    propiedadIndicadorOrden.setAdapter(new PropertyAdapter(Indicador.class, "nombre"));

    new Label(inputFormPanel).setText("Tipo de Orden").setForeground(Color.BLUE);
    final Selector<TipoOrden> selectorTipoOrder = new Selector<TipoOrden>(inputFormPanel).allowNull(false);
    selectorTipoOrder.bindValueToProperty("tipoOrdenCheckbox");
    selectorTipoOrder.setWidth(150);
    @SuppressWarnings({"unchecked", "rawtypes"})
    final Binding<TipoOrden, Selector<TipoOrden>, ListBuilder<TipoOrden>> propiedadTipoOrden =
        selectorTipoOrder.bindItems(new ObservableProperty(getModelObject(), "tiposOrden"));
    propiedadTipoOrden.setAdapter(new PropertyAdapter(TipoOrden.class, "nombreOrden"));

    new Label(inputFormPanel).setText("Indicador").setForeground(Color.BLUE);
    final Selector<Indicador> selectorIndicador = new Selector<Indicador>(inputFormPanel).allowNull(false);
    selectorIndicador.bindValueToProperty("indicadorCheckbox");
    selectorIndicador.setWidth(150);
    @SuppressWarnings({"unchecked", "rawtypes"})
    final Binding<Indicador, Selector<Indicador>, ListBuilder<Indicador>> propiedadIndicador =
        selectorIndicador.bindItems(new ObservableProperty(getModelObject(), "indicadores"));
    propiedadIndicador.setAdapter(new PropertyAdapter(Indicador.class, "nombre"));

    new Label(inputFormPanel).setText("Filtro").setForeground(Color.BLUE);
    final Selector<Filtro> selectorFiltro = new Selector<Filtro>(inputFormPanel).allowNull(false);
    selectorFiltro.bindValueToProperty("tipoCondicionCheckbox");
    selectorFiltro.setWidth(150);
    @SuppressWarnings({"unchecked", "rawtypes"})
    final Binding<Filtro, Selector<Filtro>, ListBuilder<Filtro>> propiedadFiltro =
        selectorFiltro.bindItems(new ObservableProperty(getModelObject(), "tiposCondiciones"));
    propiedadFiltro.setAdapter(new PropertyAdapter(Filtro.class, "nombre"));

    new Label(inputFormPanel).setText("Valor").setForeground(Color.BLUE);
    new TextBox(inputFormPanel).setWidth(150).bindValueToProperty("valor");
  }

  @Override
  protected void createMainTemplate(final Panel mainPanel) {
    this.setTitle("ABM Metodologia");

    final Label error = new Label(mainPanel);
    error.setForeground(Color.RED).bindValueToProperty("error");

    super.createMainTemplate(mainPanel);

    this.createResultsGridIndicadores(mainPanel);
  }

  protected void createResultsGridIndicadores(final Panel mainPanel) {
    final Table<Condicion> table = new Table<Condicion>(mainPanel, Condicion.class);
    table.setNumberVisibleRows(4);
    table.setWidth(450);

    table.bindItemsToProperty("condiciones");

    this.describeResultsGridIndicador(table);
  }

  protected void describeResultsGridIndicador(final Table<Condicion> table) {
    new Column<Condicion>(table) //
        .setTitle("Indicador").setFixedSize(100).bindContentsToProperty("indicador");

    new Column<Condicion>(table) //
        .setTitle("Filtro").setFixedSize(150).bindContentsToProperty("filtro");

  }


}
