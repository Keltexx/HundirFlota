package tablero;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;

public class Juego {

	/**
	 * Implementa el juego 'Hundir la flota' mediante una interfaz gráfica (GUI)
	 */

	/** Parametros por defecto de una partida */
	public static final int NUMFILAS=8, NUMCOLUMNAS=8, NUMBARCOS=6;

	private GuiTablero guiTablero = null;			// El juego se encarga de crear y modificar la interfaz gráfica
	private Partida partida = null;                 // Objeto con los datos de la partida en juego
	
	/** Atributos de la partida guardados en el juego para simplificar su implementación */
	private int quedan = NUMBARCOS, disparos = 0;

	/**
	 * Programa principal. Crea y lanza un nuevo juego
	 * @param args
	 */
	public static void main(String[] args) {
		Juego juego = new Juego();
		juego.ejecuta();
	} // end main

	/**
	 * Lanza una nueva hebra que crea la primera partida y dibuja la interfaz grafica: tablero
	 */
	private void ejecuta() {
		// Instancia la primera partida
		partida = new Partida(NUMFILAS, NUMCOLUMNAS, NUMBARCOS);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				guiTablero = new GuiTablero(NUMFILAS, NUMCOLUMNAS);
				guiTablero.dibujaTablero();
			}
		});
	} // end ejecuta

	/******************************************************************************************/
	/*********************  CLASE INTERNA GuiTablero   ****************************************/
	/******************************************************************************************/
	private class GuiTablero {

		private int numFilas, numColumnas;

		private JFrame frame = null;        // Tablero de juego
		private JLabel estado = null;       // Texto en el panel de estado
		private JButton buttons[][] = null; // Botones asociados a las casillas de la partida

		/**
         * Constructor de una tablero dadas sus dimensiones
         */
		GuiTablero(int numFilas, int numColumnas) {
			this.numFilas = numFilas;
			this.numColumnas = numColumnas;
			frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		}

		/**
		 * Dibuja el tablero de juego y crea la partida inicial
		 */
		public void dibujaTablero() {
			anyadeMenu();
			anyadeGrid(numFilas, numColumnas);		
			anyadePanelEstado("Intentos: " + disparos + "    Barcos restantes: " + quedan);		
			frame.setSize(300, 300);
			frame.setVisible(true);	
		} // end dibujaTablero

		/**
		 * Anyade el menu de opciones del juego y le asocia un escuchador
		 */
		private void anyadeMenu() {	//TODO
			//Creacion de opciones del menu
            JMenuBar menu = new JMenuBar();	
            JMenu opciones = new JMenu("Opciones");
            JMenuItem solucion = new JMenuItem("Mostrar solucion");
            JMenuItem nueva = new JMenuItem("Nueva partida");
            JMenuItem salir = new JMenuItem("Salir");
            
            //Llamada al escuchador con cada opcion del menu
            MenuListener escuchador = new MenuListener();
            solucion.addActionListener(escuchador);
            nueva.addActionListener(escuchador);
            salir.addActionListener(escuchador);
            
            //Añade las opciones a la ventana
            opciones.add(solucion);
            opciones.add(nueva);
            opciones.add(salir);
            menu.add(opciones);
            frame.add(menu, BorderLayout.NORTH);
		} // end anyadeMenu

		/**
		 * Anyade el panel con las casillas del mar y sus etiquetas.
		 * Cada casilla sera un boton con su correspondiente escuchador
		 * @param nf	numero de filas
		 * @param nc	numero de columnas
		 */
		private void anyadeGrid(int nf, int nc) {	//TODO
			JPanel cuadr = new JPanel();
			GridLayout experimentLayout = new GridLayout(nf+1,nc+2);
			JLabel letraLabel;
			JLabel numeroLabel;
			cuadr.setLayout(experimentLayout);
			
			for(int i=0; i<nf+1; i++){	//Recorre las filas de la matriz
				for(int j=0; j<nc+2; j++){	//Recorre las columnas de la matriz
					if((i==0 && j==0)||(i==0 && j==nc+1))	//Añade espacio en blanco en la primera y ultima casilla de la primera fila
						cuadr.add(numeroLabel = new JLabel("   "));
					else if(i==0 && j >=1)	//Numera las columnas en la primera fila
						cuadr.add(numeroLabel = new JLabel("   "+j));
					else if(j==0 || j==nc+1)	//Numera mediante letras las filas al principio y al final
						cuadr.add(numeroLabel = new JLabel("   "+Character.toString((char)('A'+i-1))));
					else
						cuadr.add(new JButton());
				}
				
			}
			frame.add(cuadr);
		} // end anyadeGrid


		/**
		 * Anyade el panel de estado al tablero
		 * @param cadena	cadena inicial del panel de estado
		 */
		private void anyadePanelEstado(String cadena) {	
			JPanel panelEstado = new JPanel();
			estado = new JLabel(cadena);
			panelEstado.add(estado);
			// El panel de estado queda en la posición SOUTH del frame
			frame.getContentPane().add(panelEstado, BorderLayout.SOUTH);
		} // end anyadePanel Estado

		/**
		 * Cambia la cadena mostrada en el panel de estado
		 * @param cadenaEstado	nuevo estado
		 */
		public void cambiaEstado(String cadenaEstado) {
			estado.setText(cadenaEstado);
		} // end cambiaEstado

		/**
		 * Muestra la solucion de la partida y marca la partida como finalizada
		 */
		public void muestraSolucion() {	//TODO
//			for (int i = 0; i < numFilas; i++) {
//				for (int j = 0; j < numColumnas; j++) {
//					buttons[i][j].pintaBoton();
//				}
//			}
            // POR IMPLEMENTAR
		} // end muestraSolucion


		/**
		 * Pinta un barco como hundido en el tablero
		 * @param cadenaBarco	cadena con los datos del barco codifificados como
		 *                      "filaInicial#columnaInicial#orientacion#tamanyo"
		 */
		public void pintaBarcoHundido(String cadenaBarco) {	//TODO
//			String[] parts = cadenaBarco.split("#");
//			String filaInicial = parts[0];
//			String columnaInicial = parts[1];     
//			String orientacion = parts[2];     
//			String tamanyo = parts[3];  

			
			
		} // end pintaBarcoHundido

		/**
		 * Pinta un botón de un color dado
		 * @param b			boton a pintar
		 * @param color		color a usar
		 */
		public void pintaBoton(JButton b, Color color) {
			b.setBackground(color);
			// El siguiente código solo es necesario en Mac OS X
			b.setOpaque(true);
			b.setBorderPainted(false);
		} // end pintaBoton

		/**
		 * Limpia las casillas del tablero pintándolas del gris por defecto
		 */
		public void limpiaTablero() {
			for (int i = 0; i < numFilas; i++) {
				for (int j = 0; j < numColumnas; j++) {
					buttons[i][j].setBackground(null);
					buttons[i][j].setOpaque(true);
					buttons[i][j].setBorderPainted(true);
				}
			}
		} // end limpiaTablero

		/**
		 * 	Destruye y libera la memoria de todos los componentes del frame
		 */
		public void liberaRecursos() {
			frame.dispose();
		} // end liberaRecursos


	} // end class GuiTablero

	/******************************************************************************************/
	/*********************  CLASE INTERNA MenuListener ****************************************/
	/******************************************************************************************/

	/**
	 * Clase interna que escucha el menu de Opciones del tablero
	 * 
	 */
	private class MenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {	//TODO
			switch(e.getActionCommand()){
			case "Salir": 
				guiTablero.liberaRecursos();
				break;
			case "Mostrar solucion":
				System.out.println("Esta es la solucion");
				guiTablero.muestraSolucion();
				break;
			case "Nueva partida":
				System.out.println("Vuelve a empezar la partida");
				guiTablero.limpiaTablero();
				break;				
			default:
				break;
			} //end switch
			
		} //end actionPerformed
	} // end class MenuListener



	/******************************************************************************************/
	/*********************  CLASE INTERNA ButtonListener **************************************/
	/******************************************************************************************/
	/**
	 * Clase interna que escucha cada uno de los botones del tablero
	 * Para poder identificar el boton que ha generado el evento se pueden usar las propiedades
	 * de los componentes, apoyandose en los metodos putClientProperty y getClientProperty
	 */
	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {	//TODO
			Object boton;
			switch(e.getActionCommand()){
//				case "":
//					Partida partida = new Partida();
//					if (partida.pruebaCasilla(f, c)==-1) {	//AGUA	
//						pintarBoton
//					}
//					else if (partida.pruebaCasilla(f, c)==-2) {	//TOCADO
//						
//					}
//					
//					else if (partida.pruebaCasilla(f, c)==-3) {	//HUNDIDO
//						
//					}
//					break;
//				case ":":
//					break;
//				
			} //end switch
			
        } // end actionPerformed

	} // end class ButtonListener

	

} // end class Juego

