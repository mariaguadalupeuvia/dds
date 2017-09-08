package ar.org.utn.ddstpanual.ui;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

@SuppressWarnings({"serial", "unchecked", "rawtypes"})
public class MenuPrincipalWindow extends SimpleWindow {

  public MenuPrincipalWindow(final WindowOwner parent) {
    super(parent, new Object());
  }

  @Override
  protected void addActions(final Panel actionsPanel) {
	  actionsPanel.setLayout(new VerticalLayout());

	  
    final Panel actions = new Panel(actionsPanel);
    actions.setLayout(new ColumnLayout(2));

    final Button cargarExcel = new Button(actions);
    cargarExcel.setCaption("Cargar Datos");
    cargarExcel.onClick(this::cargarExcel);

    final Button consultarEmpresa = new Button(actions);
    consultarEmpresa.setCaption("Consultar Empresa");
    consultarEmpresa.onClick(this::consultaIndicadores);

    final Button abmIndicador = new Button(actions);
    abmIndicador.setCaption("ABM Indicadores");
    abmIndicador.onClick(this::abmIndicadores);

    final Button ejecutarIndicador = new Button(actions);
    ejecutarIndicador.setCaption("Ejecutar Indicador");
    ejecutarIndicador.onClick(this::ejecutarIndicador);

    final Button abmMetodologia = new Button(actions);
    abmMetodologia.setCaption("ABM Metodologias");
    abmMetodologia.onClick(this::abmMetodologias);

    final Button ejecutarMetodologias = new Button(actions);
    ejecutarMetodologias.setCaption("Ejecutar Metodologia");
    ejecutarMetodologias.onClick(this::ejecutarMetodologia);
    
    Graficos.graficarBorde(actionsPanel, 360);
  }

  @Override
  protected void createFormPanel(final Panel mainPanel) {
    this.setTitle("Donde Invierto");
	  Graficos.graficarDolares(mainPanel);
	  Graficos.graficarEspacio(mainPanel, 360);
	 
  }

  // ********************************************************
  // ** Acciones
  // ********************************************************
  public void ejecutarIndicador() {
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

  public void abmMetodologias() {
    new AbmMetodologiasWindow(this).open();
  }

  public void ejecutarMetodologia() {
    new MetodologiasWindow(this).open();
  }

}
