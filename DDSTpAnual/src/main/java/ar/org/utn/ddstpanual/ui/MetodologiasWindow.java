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

import ar.org.utn.ddstpanual.controller.MetodologiasController;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Periodo;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;

public class MetodologiasWindow extends SimpleWindow<MetodologiasController> {

  private static final long serialVersionUID = 1L;

  public MetodologiasWindow(final WindowOwner parent) {
    super(parent, new MetodologiasController());
    getModelObject().setError("");
    getModelObject().obtenerEmpresas();
    getModelObject().obtenerMetodologias();
    getModelObject().obtenerPeriodos();
  }

  @Override
  protected void addActions(final Panel actionsPanel) {
    new Button(actionsPanel).setCaption("Ejecutar").onClick(() -> {
      getModelObject().ejecutarMetodologia();
    }).setAsDefault().disableOnError();
  }


  @Override
  protected void createFormPanel(final Panel mainPanel) {
    final Panel inputFormPanel = new Panel(mainPanel);
    inputFormPanel.setLayout(new ColumnLayout(2));

    new Label(inputFormPanel).setText("Metodologia").setForeground(Color.BLUE);
    final Selector<Metodologia> selectorMetodologia = new Selector<Metodologia>(inputFormPanel).allowNull(false);
    selectorMetodologia.bindValueToProperty("metodologiaCheckbox");
    selectorMetodologia.setWidth(150);
    final Binding<Metodologia, Selector<Metodologia>, ListBuilder<Metodologia>> propiedadMetodologia =
        selectorMetodologia.bindItems(new ObservableProperty<Metodologia>(getModelObject(), "metodologias"));
    propiedadMetodologia.setAdapter(new PropertyAdapter(Metodologia.class, "nombre"));

    new Label(inputFormPanel).setText("Periodo").setForeground(Color.BLUE);
    final Selector<Periodo> selectorPeriodo = new Selector<Periodo>(inputFormPanel).allowNull(false);
    selectorPeriodo.bindValueToProperty("periodoCheckbox");
    selectorPeriodo.setWidth(150);
    final Binding<Periodo, Selector<Periodo>, ListBuilder<Periodo>> propiedadPeriodo =
        selectorPeriodo.bindItems(new ObservableProperty<Periodo>(getModelObject(), "periodos"));
    propiedadPeriodo.setAdapter(new PropertyAdapter(Periodo.class, "fecha"));
  }

  @Override
  protected void createMainTemplate(final Panel mainPanel) {
    this.setTitle("Metodologia");

    final Label error = new Label(mainPanel);
    error.setForeground(Color.RED).bindValueToProperty("error");

    super.createMainTemplate(mainPanel);

    this.createResultsGridEmpresas(mainPanel);
  }

  protected void createResultsGridEmpresas(final Panel mainPanel) {
    final Table<Empresa> table = new Table<Empresa>(mainPanel, Empresa.class);
    table.setNumberVisibleRows(4);
    table.setWidth(450);

    table.bindItemsToProperty("empresasResultado");

    this.describeResultsGridEmpresa(table);
  }

  protected void describeResultsGridEmpresa(final Table<Empresa> table) {
    new Column<Empresa>(table).setTitle("Empresa").setFixedSize(100).bindContentsToProperty("nombre");
  }

}
