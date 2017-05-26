package ar.org.utn.ddstpanual.ui;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import java.awt.Color;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.service.impl.IndicadorServiceImpl;


@SuppressWarnings("serial")
public class AbmIndicadoresWindow extends SimpleWindow<IndicadorServiceImpl> {

  public AbmIndicadoresWindow(WindowOwner parent) {
    super(parent, new IndicadorServiceImpl());
    this.getModelObject().setFormula("");
    this.getModelObject().setNombre("");
    try {
      getModelObject().obtenerIndicadores();
    } catch (ServiceException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  protected void addActions(Panel actionsPanel) {
    new Button(actionsPanel).setCaption("Guardar").onClick(() -> {
      try {
        getModelObject().guardarIndicador();
      } catch (ServiceException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }).setAsDefault().disableOnError();

  }

  @Override
  protected void createFormPanel(Panel mainPanel) {
    Panel inputFormPanel = new Panel(mainPanel);
    inputFormPanel.setLayout(new ColumnLayout(2));

    new Label(inputFormPanel).setText("Nombre").setForeground(Color.BLUE);
    new TextBox(inputFormPanel).setWidth(150).bindValueToProperty("nombre");
    new Label(inputFormPanel).setText("Formula").setForeground(Color.BLUE);
    new TextBox(inputFormPanel).setWidth(150).bindValueToProperty("formula");

  }

  @Override
  protected void createMainTemplate(Panel mainPanel) {
    this.setTitle("Alta de Indicador");

    Label error = new Label(mainPanel);
    error.setForeground(Color.RED).bindValueToProperty("error");

    super.createMainTemplate(mainPanel);

    this.createResultsGridIndicadores(mainPanel);

  }

  protected void createResultsGridIndicadores(Panel mainPanel) {
    Table<Indicador> table = new Table<Indicador>(mainPanel, Indicador.class);
    table.setNumberVisibleRows(4);
    table.setWidth(450);

    table.bindItemsToProperty("indicadores");

    this.describeResultsGridIndicador(table);
  }

  protected void describeResultsGridIndicador(Table<Indicador> table) {
    new Column<Indicador>(table) //
        .setTitle("Nombre").setFixedSize(100).bindContentsToProperty("nombre");

    new Column<Indicador>(table) //
        .setTitle("Formula").setFixedSize(150).bindContentsToProperty("formula");

  }

}
