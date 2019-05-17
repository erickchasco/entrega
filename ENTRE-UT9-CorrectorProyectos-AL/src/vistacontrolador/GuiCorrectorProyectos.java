
package vistacontrolador;

import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.CorrectorProyectos;

/*
*@author: Erick Chasco
*/

public class GuiCorrectorProyectos extends Application
	{

		private MenuItem itemLeer;
		private MenuItem itemGuardar;
		private MenuItem itemSalir;

		private TextField txtAlumno;
		private Button btnVerProyecto;

		private RadioButton rbtAprobados;
		private RadioButton rbtOrdenados;
		private Button btnMostrar;

		private TextArea areaTexto;

		private Button btnClear;
		private Button btnSalir;

		private CorrectorProyectos corrector; // el modelo

		@Override
		public void start(Stage stage)
		{

			corrector = new CorrectorProyectos();
			BorderPane root = crearGui();
			Scene scene = new Scene(root, 800, 600);
			stage.setScene(scene);
			stage.setTitle("- Corrector de proyectos -");
			scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			stage.show();
		}

		private BorderPane crearGui()
		{

			BorderPane panel = new BorderPane();
			MenuBar barraMenu = crearBarraMenu();
			panel.setTop(barraMenu);
			VBox panelPrincipal = crearPanelPrincipal();
			panel.setCenter(panelPrincipal);
			HBox panelBotones = crearPanelBotones();
			panel.setBottom(panelBotones);
			return panel;
		}

		private MenuBar crearBarraMenu()
		{

			MenuBar barraMenu = new MenuBar();
			barraMenu.getStyleClass().add("menu-bar");
			Menu menu = new Menu("Archivo");
			menu.getStyleClass().add("menu");
			itemLeer = new MenuItem("Leer de fichero");
			itemLeer.setAccelerator(KeyCombination.keyCombination("CTRL+L"));
			itemLeer.setOnAction(event -> leerDeFichero());
			// a completar
			itemGuardar = new MenuItem("Guardar en fichero");
			itemGuardar.setAccelerator(KeyCombination.keyCombination("CTRL+G"));
			itemGuardar.setDisable(true);
			itemGuardar.setOnAction(event -> salvarEnFichero());
			itemSalir = new MenuItem("Salir");
			itemSalir.setAccelerator(KeyCombination.keyCombination("CTRL+S"));
			itemSalir.setOnAction(event -> salir());
			menu.getItems().addAll(itemLeer, itemGuardar);
			menu.getItems().addAll(new SeparatorMenuItem(), itemSalir);
			barraMenu.getMenus().add(menu);
			return barraMenu;
		}

		private VBox crearPanelPrincipal()
		{

			VBox panel = new VBox();
			panel.setPadding(new Insets(5));
			panel.setSpacing(10);
			Label lblEntrada = new Label("Panel de entrada");
			// a completar
			lblEntrada.getStyleClass().add("titulo-panel");
			lblEntrada.setMaxWidth(Integer.MAX_VALUE);
			VBox.setVgrow(lblEntrada, Priority.ALWAYS);
			Label lblOpciones = new Label("Panel de opciones");
			lblOpciones.getStyleClass().add("titulo-panel");
			lblOpciones.setMaxWidth(Integer.MAX_VALUE);
			VBox.setVgrow(lblOpciones, Priority.ALWAYS);
			areaTexto = new TextArea();
			areaTexto.getStyleClass().add("text-area");
			areaTexto.setMaxSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
			VBox.setVgrow(areaTexto, Priority.ALWAYS);
			panel.getChildren().addAll(lblEntrada, crearPanelEntrada(), lblOpciones, crearPanelOpciones(), areaTexto);
			return panel;
		}

		private HBox crearPanelEntrada()
		{

			HBox panel = new HBox();
			panel.setPadding(new Insets(5));
			// a completar
			panel.setSpacing(10);
			Label lblAlumno = new Label("Alumno");
			lblAlumno.getStyleClass().add("label");
			txtAlumno = new TextField();
			txtAlumno.setPrefColumnCount(30);
			btnVerProyecto = new Button("Ver proyecto");
			btnVerProyecto.getStyleClass().add("button");
			btnVerProyecto.setPrefWidth(120);
			panel.getChildren().addAll(lblAlumno, txtAlumno, btnVerProyecto);
			return panel;
		}

		private HBox crearPanelOpciones()
		{

			HBox panel = new HBox();
			panel.setPadding(new Insets(5));
			// a completar
			panel.setSpacing(50);
			panel.setAlignment(Pos.CENTER);
			rbtAprobados = new RadioButton("Mostrar aprobados");
			rbtAprobados.setSelected(true);
			rbtOrdenados = new RadioButton("Mostrar ordenados");
			btnMostrar = new Button("Mostrar");
			btnMostrar.getStyleClass().add("button");
			panel.getChildren().addAll(rbtAprobados, rbtOrdenados, btnMostrar);
			return panel;
		}

		private HBox crearPanelBotones()
		{

			HBox panel = new HBox();
			panel.setPadding(new Insets(5));
			// a completar
			panel.setSpacing(10);
			panel.setAlignment(Pos.CENTER_RIGHT);
			btnClear = new Button("Clear");
			btnClear.getStyleClass().add("button");
			btnClear.setPrefWidth(90);
			btnClear.setOnAction(event -> clear());
			btnSalir = new Button("Salir");
			btnSalir.getStyleClass().add("button");
			btnSalir.setPrefWidth(90);
			btnSalir.setOnAction(event -> salir());
			panel.getChildren().addAll(btnClear, btnSalir);
			return panel;
		}

		private void salvarEnFichero()
		{
			// a completar
			try
			{
				corrector.guardarOrdenadosPorNota();
				areaTexto.setText("Guardados en fichero de texto los proyectos ordenados");
				itemLeer.setDisable(false);
				itemGuardar.setDisable(true);
			} catch (IOException e)
			{
				areaTexto.setText("No hay ningún fichero leído");
			}

		}

		private void leerDeFichero()
		{
			// a completar
			corrector.leerDatosProyectos();
			areaTexto.setText("Leido fichero de texto\n\n" + corrector.getErrores()
					+ "\n\nYa están guardados en memoria los datos de los proyectos");

			itemLeer.setDisable(true);
			itemGuardar.setDisable(false);

		}

		private void verProyecto()
		{
			// a completar

		}

		private void mostrar()
		{
			clear();
			// a completar

		}

		private void cogerFoco()
		{
			txtAlumno.requestFocus();
			txtAlumno.selectAll();
		}

		private void salir()
		{
			System.exit(0);
		}

		private void clear()
		{
			areaTexto.clear();
			cogerFoco();
		}

		public static void main(String[] args)
		{
			launch(args);
		}
	}
