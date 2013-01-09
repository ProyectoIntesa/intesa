package edu.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;

public class MensajeConfirmacion extends PopupPanel {

	private Label tituloVentana;
	private Label mensaje;
	private Label pie;
	private FlexTable panel;

	private Button aceptar;
	private Button cancelar;
	private boolean acepta;
	private Constantes constante = GWT.create(Constantes.class);

	public MensajeConfirmacion(String mensaje) {
		super(false);
		setStyleName("fondoPopup");
		acepta = false;
		tituloVentana = new Label();
		tituloVentana.setStyleName("labelTitulo");

		this.mensaje = new Label(mensaje);
		this.mensaje.setStyleName("gwt-LabelFormulario");
		pie = new Label();
		pie.setStyleName("labelTitulo");

		aceptar = new Button(constante.aceptar());
		aceptar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				acepta = true;
				salir();
			}
		});
		cancelar = new Button(constante.cancelar());
		cancelar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				salir();
			}
		});
	
		panel = new FlexTable();
		panel.setSize("600px", "300px");
		panel.setWidget(0, 0, tituloVentana);
		panel.getFlexCellFormatter().setColSpan(0, 0, 3);
		panel.setWidget(1, 0, this.mensaje);
		panel.getFlexCellFormatter().setColSpan(1, 0, 3);
		panel.setWidget(2, 0, pie);
		panel.getFlexCellFormatter().setColSpan(2, 0, 3);
		panel.setWidget(3, 1, aceptar);
		panel.setWidget(3, 2, cancelar);
	
		setWidget(panel);
	}

	protected void salir() {
		this.hide();
	}
	
	public boolean acepta(){
		return this.acepta;
	}
}
