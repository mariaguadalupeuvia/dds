package ar.org.utn.ddstpanual.ui;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

@SuppressWarnings({"serial", "unchecked", "rawtypes"})
public class MenuPrincipalWindow extends SimpleWindow {

  public MenuPrincipalWindow(WindowOwner parent) {
    super(parent, new Object());
  }

  @Override
  protected void addActions(Panel actionsPanel) {
    Panel actions = new Panel(actionsPanel);
    actions.setLayout(new ColumnLayout(2));

    Button cargarExcel = new Button(actions);
    cargarExcel.setCaption("Cargar Datos");
    cargarExcel.onClick(this::cargarExcel);

    Button consultarEmpresa = new Button(actions);
    consultarEmpresa.setCaption("Consultar Empresa");
    consultarEmpresa.onClick(this::consultaIndicadores);

    Button crearIndicador = new Button(actions);
    crearIndicador.setCaption("Crear Indicador");
    crearIndicador.onClick(this::crearIndicador);

    Button abmIndicador = new Button(actions);
    abmIndicador.setCaption("ABM Indicadores");
    abmIndicador.onClick(this::abmIndicadores);
  }

  @Override
  protected void createFormPanel(Panel mainPanel) {
    this.setTitle("Donde Invierto");

    Label lblAcciones = new Label(mainPanel);
    lblAcciones.setText("Bienvenido!");
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

  public void consultaIndicadores() {
    new ConsultaEmpresasWindow(this).open();
  }

  public void cargarExcel() {
    new CargaExcelWindow(this).open();
  }

}
