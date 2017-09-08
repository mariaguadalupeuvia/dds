package ar.org.utn.ddstpanual.ui;

import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;

public class Graficos {
	public static Panel graficarBorde(Panel panelMain, Integer width)
	{
		Panel unPanel = new Panel(panelMain);
		unPanel.setLayout(new VerticalLayout());
		
		new Label(unPanel).setWidth(width);
		new Label(unPanel);
		new Label(unPanel).setBackground(Paleta.colorMedioAlto()).setFontSize(3);
		
		return unPanel;
	}
	public static Panel graficarSeparador(Panel panelMain, Integer width)
	{
		Panel unPanel = new Panel(panelMain);
		unPanel.setLayout(new VerticalLayout());
		
		new Label(unPanel).setWidth(width);
		new Label(unPanel).setBackground(Paleta.colorMedioBajo()).setFontSize(1);
		
		return unPanel;
	}
	
	public static Panel graficarEspacio(Panel panelMain, Integer width)
	{
		Panel unPanel = new Panel(panelMain);
		unPanel.setLayout(new VerticalLayout());
		
		new Label(unPanel).setWidth(width);
		new Label(unPanel).setBackground(Paleta.fuenteAlta()).setFontSize(1);
		
		return unPanel;
	}
	
	public static Panel graficarDolares(Panel panelMain)
	{
		Panel unPanel = new Panel(panelMain);
		unPanel.setLayout(new HorizontalLayout());
		
		new Label(unPanel).setText("  |     U$D     |  ").setBackground(Paleta.colorBajo()).setForeground(Paleta.fuenteAlta());
		new Label(unPanel).setText("  |     U$D     |  ").setBackground(Paleta.colorMedioBajo());
		new Label(unPanel).setText("  |     U$D     |  ").setBackground(Paleta.colorMedio()).setForeground(Paleta.fuenteAlta());
		new Label(unPanel).setText("  |     U$D     |  ").setBackground(Paleta.colorMedioAlto());
		new Label(unPanel).setText("  |     U$D     |  ").setBackground(Paleta.colorAlto()).setForeground(Paleta.fuenteAlta());
		
		return unPanel;
	}
}
