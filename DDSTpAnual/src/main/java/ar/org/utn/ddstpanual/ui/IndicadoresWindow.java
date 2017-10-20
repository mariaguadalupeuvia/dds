package ar.org.utn.ddstpanual.ui;

import java.awt.Color;
import java.util.List;

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

import ar.org.utn.ddstpanual.controllerOld.IndicadoresController;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.FormulaIndicador;
import ar.org.utn.ddstpanual.model.Periodo;

public class IndicadoresWindow extends SimpleWindow<IndicadoresController> {

	private static final long serialVersionUID = 1L;
	private List<FormulaIndicador> ejecutarIndicador;

	public IndicadoresWindow(final WindowOwner parent) {
		super(parent, new IndicadoresController());
		getModelObject().obtenerIndicadores();
		getModelObject().obtenerEmpresas();
		getModelObject().obtenerPeriodos();
	}

	@Override
	protected void addActions(final Panel actionsPanel) {
		new Button(actionsPanel).setCaption("Ejecutar").onClick(() -> {
			// getModelObject().listarIndicadores();
		}).setAsDefault().disableOnError();

		new Button(actionsPanel).setCaption("Limpiar").onClick(() -> {
			getModelObject().limpiar();
		});
	}

	@Override
	protected void createFormPanel(final Panel mainPanel) {
		final Panel inputFormPanel = new Panel(mainPanel);
		inputFormPanel.setLayout(new ColumnLayout(2));

		new Label(inputFormPanel).setText("Empresa").setForeground(Color.BLUE);
		final Selector<Empresa> selectorEmpresa = new Selector<Empresa>(inputFormPanel).allowNull(false);
		selectorEmpresa.bindValueToProperty("empresaCheckbox");
		selectorEmpresa.setWidth(150);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		final Binding<Empresa, Selector<Empresa>, ListBuilder<Empresa>> propiedadEmpresa = selectorEmpresa
				.bindItems(new ObservableProperty(getModelObject(), "empresas"));
		propiedadEmpresa.setAdapter(new PropertyAdapter(Empresa.class, "nombre"));

		new Label(inputFormPanel).setText("Periodo").setForeground(Color.BLUE);
		final Selector<Periodo> selectorPeriodo = new Selector<Periodo>(inputFormPanel).allowNull(false);
		selectorPeriodo.bindValueToProperty("periodoCheckbox");
		selectorPeriodo.setWidth(150);
		final Binding<Periodo, Selector<Periodo>, ListBuilder<Periodo>> propiedadPeriodo = selectorPeriodo
				.bindItems(new ObservableProperty<Periodo>(getModelObject(), "periodos"));
		propiedadPeriodo.setAdapter(new PropertyAdapter(Periodo.class, "fecha"));

	}

	@Override
	protected void createMainTemplate(final Panel mainPanel) {
		this.setTitle("Indicadores");

		final Label error = new Label(mainPanel);
		error.setForeground(Color.RED).bindValueToProperty("error");

		super.createMainTemplate(mainPanel);

		this.createResultsGridIndicadores(mainPanel);
	}

	protected void createResultsGridIndicadores(final Panel mainPanel) {
		final Table<FormulaIndicador> table = new Table<FormulaIndicador>(mainPanel, FormulaIndicador.class);
		table.setNumberVisibleRows(6);
		table.setWidth(450);
		table.bindItemsToProperty("indicadoresEvaluados");
		this.describeResultsGridIndicador(table);
	}

	protected void describeResultsGridIndicador(final Table<FormulaIndicador> table) {
		new Column<FormulaIndicador>(table).setTitle("Periodo").setFixedSize(100).bindContentsToProperty("fecha");
		new Column<FormulaIndicador>(table).setTitle("Indicador").setFixedSize(150).bindContentsToProperty("nombre");
		new Column<FormulaIndicador>(table).setTitle("Valor").setFixedSize(150).bindContentsToProperty("valor");
	}

	public List<FormulaIndicador> getEjecutarIndicador() {
		return ejecutarIndicador;
	}

	public void setEjecutarIndicador(final List<FormulaIndicador> ejecutarIndicador) {
		this.ejecutarIndicador = ejecutarIndicador;
	}

}
