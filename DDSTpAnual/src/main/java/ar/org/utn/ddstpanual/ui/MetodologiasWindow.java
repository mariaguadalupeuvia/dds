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
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;
import ar.org.utn.ddstpanual.model.metodologia.MetodologiaResultado;

public class MetodologiasWindow extends SimpleWindow<MetodologiasController> {

  private static final long serialVersionUID = 1L;

  public MetodologiasWindow(final WindowOwner parent) {
    super(parent, new MetodologiasController());
    getModelObject().setError("");
    getModelObject().obtenerEmpresas();
    getModelObject().obtenerMetodologias();
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
    @SuppressWarnings({"unchecked", "rawtypes"})
    final Binding<Metodologia, Selector<Metodologia>, ListBuilder<Metodologia>> propiedadMetodologia =
        selectorMetodologia.bindItems(new ObservableProperty(getModelObject(), "metodologias"));
    propiedadMetodologia.setAdapter(new PropertyAdapter(Metodologia.class, "nombre"));

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
    final Table<MetodologiaResultado> table = new Table<MetodologiaResultado>(mainPanel, MetodologiaResultado.class);
    table.setNumberVisibleRows(4);
    table.setWidth(450);

    table.bindItemsToProperty("metodologiasResultado");

    this.describeResultsGridEmpresa(table);
  }

  protected void describeResultsGridEmpresa(final Table<MetodologiaResultado> table) {
    new Column<MetodologiaResultado>(table).setTitle("Empresa").setFixedSize(100).bindContentsToProperty("nombreEmpresa");
  }

}
