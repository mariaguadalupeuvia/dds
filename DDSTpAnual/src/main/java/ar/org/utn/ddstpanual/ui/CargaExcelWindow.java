package ar.org.utn.ddstpanual.ui;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.FileSelector;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import java.awt.Color;

import ar.org.utn.ddspanual.controller.CargaExcelController;

@SuppressWarnings("serial")
public class CargaExcelWindow extends SimpleWindow<CargaExcelController> {

  public CargaExcelWindow(WindowOwner parent) {
    super(parent, new CargaExcelController());
  }

  @Override
  protected void createMainTemplate(Panel mainPanel) {
    this.setTitle("Carga de excel");

    Label error = new Label(mainPanel);
    error.setForeground(Color.RED).bindValueToProperty("error");

    super.createMainTemplate(mainPanel);

    this.createMenuActions(mainPanel);
  }

  private void createMenuActions(Panel mainPanel) {
    Panel actions = new Panel(mainPanel);
    actions.setLayout(new HorizontalLayout());

    Button crearIndicador = new Button(actions);
    crearIndicador.setCaption("Crear Indicador");
    crearIndicador.onClick(this::crearIndicador);

    Button abmIndicador = new Button(actions);
    abmIndicador.setCaption("ABM Indicadores");
    abmIndicador.onClick(this::abmIndicadores);

    Button consultaEmpresa = new Button(actions);
    consultaEmpresa.setCaption("Consulta Empresas");
    consultaEmpresa.onClick(this::consultaEmpresas);
  }

  @Override
  protected void addActions(Panel actionsPanel) {

    new Button(actionsPanel).setCaption("Guardar").onClick(() -> {
      getModelObject().guardarArchivo();
    }).setAsDefault().disableOnError();

  }

  @Override
  protected void createFormPanel(Panel mainPanel) {
    Panel inputFormPanel = new Panel(mainPanel);
    inputFormPanel.setLayout(new ColumnLayout(2));

    TextBox txtRuta = new TextBox(inputFormPanel);
    txtRuta.bindValueToProperty("rutaArchivo");
    txtRuta.setWidth(200);

    FileSelector fileselector =
        (FileSelector) new FileSelector(inputFormPanel).setCaption("Examinar");
    fileselector.bindValueToProperty("rutaArchivo");
    fileselector.extensions("*.xlsx");
  }

  // ********************************************************
  // ** Acciones
  // ********************************************************

  public void crearIndicador() {
    new IndicadoresWindow(this).open();
  }

  public void abmIndicadores() {
    new AbmIndicadoresWindow(this).open();
  }

  public void consultaEmpresas() {
    new ConsultaEmpresasWindow(this).open();
  }


}
