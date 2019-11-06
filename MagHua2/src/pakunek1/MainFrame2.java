package pakunek1;
import pakunek1.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
//import org.apache.poi.hsmf.examples.Msg2txt;
//import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
//import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
//import com.graphbuilder.curve.Point;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Panel;
import javax.swing.border.TitledBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.Button;
import javax.swing.JComboBox;

public class MainFrame2 implements MouseListener{

	private JPanel contentPane;
	public static JFrame frame = new JFrame();
    JTabbedPane tabbedPane = new JTabbedPane();
    FirstPanel fp = new FirstPanel();
    SecondPanel sp = new SecondPanel();
    public  JTextField textField;
    public JButton button;
   
    public static int idOrders, nrSite;
    public static int rowCountTab2 = 0;
    public static String dateStartOrder;
    
    String queryTabOrders2;
    
    public static JTable table_1, table_2, tableSpOrders, tableSpPartStatus;	
	public static JScrollPane scrollPane_2;
	public static DefaultTableModel tab2model;
	
	public static ArrayList<String> listA  = new ArrayList<>();
	public static ArrayList<String> listB  = new ArrayList<>();   
	
    static Object[][] data = {};
    
    FirstPanel firstPanel = new FirstPanel();	    
    static Object[] columnNames = {"BOM code", "Name", "Description","Dodaj"};
	Object[] columnNames2 = {"BOM code", "Name", "Description","Usun"};
	public static DefaultTableModel modelTab1 = new DefaultTableModel(data, columnNames) {
    	
    	boolean[] canEdit = new boolean[]{
                false, false, false, true
        
        };
//	public static DefaultTableModel modelTab1 = new DefaultTableModel(data, columnNames) {
//    	
//    	boolean[] canEdit = new boolean[]{
//                false, false, false, true
//        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit[columnIndex];
        }
        
    };
    
	DefaultTableModel modelTab2 = new DefaultTableModel(data, columnNames2) {
		boolean[] canEdit = new boolean[]{
                false, false, false, true
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit[columnIndex];
        }
	};
	
	
	SecondPanel secondPanel = new SecondPanel();
	static Object[] columnNameSpOrder = {"ID", "NrN", "Tytul_zamowienia","Wystawiajacy", "Odbierajacy", "Data", "Status"};
	Object[] columnNameSpPartStatus = {"BOM code", "Name", "Description","SN", "Stan"};
	public static DefaultTableModel modelTabSpOrder = new DefaultTableModel(data, columnNameSpOrder) {
		 public boolean isCellEditable(int rowIndex, int mColIndex) {
		        return false;
		      }
	};
	DefaultTableModel modelTabPartStatus = new DefaultTableModel(data, columnNameSpPartStatus);
	
	static JPanel panel;
	static JLabel lblNewLabel;
	static JLabel lblInformacjeZamwienia;
	static JLabel lblNrN;
	static JLabel lblTytu;
	static JLabel lblWystawiajcy;
	static JLabel lblOdbierajcy;
	static JLabel label_1;
	static JLabel label_2;
	static JLabel lblNewLabel_2;
	static JLabel lblNewLabel_1;
	static JLabel lblNewLabel_3;
	static JLabel label;
	static JLabel lblNewLabel_4;
	static JButton btnNewButton;
	static JComboBox comboBox;
	
	int ind;
	static JTextField textField_1;
	static JLabel lblDataWystawienia;
	static JLabel lblData;
	
	String myUrl = "jdbc:mysql://127.0.0.1:3306/hua_mag?useSSL=false";
	
	String phoneNr, company;
	
	/**
	 * Create the frame.
	 */
	public MainFrame2() {
		tabbedPane.add("Wystaw new SR", fp);
		fp.setLayout(null);
		
		JLabel lblSzukaj = new JLabel("szukaj:");
		lblSzukaj.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSzukaj.setBounds(333, 41, 43, 16);
		fp.add(lblSzukaj);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(55, 373, 731, 166);
		fp.add(scrollPane_2);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				String wyszukiwanie = textField.getText();
				String query = "SELECT * FROM parts where name_part like '%"+wyszukiwanie+"%' OR bom_code like '%"+wyszukiwanie+"%' OR desciption like '%"+wyszukiwanie+"%'";
				//mysqlConnection(table, query, DefTableMode = (DefaultTableModel)table.getModel());
				firstPanel.mysqlConnection2(table_1, query, modelTab1);
				
			}
		});
		textField.setColumns(10);
		textField.setBounds(410, 38, 97, 22);
		fp.add(textField);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(55, 116, 731, 166);
		fp.add(scrollPane_1);
		
		
		JScrollPane scrollPaneSpOrders = new JScrollPane();
		scrollPaneSpOrders.setBounds(46, 62, 876, 166);
		sp.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sp.add(scrollPaneSpOrders);
		
		JScrollPane scrollPaneSpPartStatus = new JScrollPane();
		scrollPaneSpPartStatus.setBounds(46, 527, 725, 175);
		sp.add(scrollPaneSpPartStatus);
			
		
		/// --------- --------  tworzenie tabeli1 (z czesciami) w firstpanel ------------------------\\\\\\		
		
		table_1 = firstPanel.setTableView1(table_1, modelTab1);		
						
		scrollPane_1.setViewportView(table_1);		
		String queryTab1 = "SELECT * FROM parts";
		//wczytywanie danych do tabeli1
		firstPanel.mysqlConnection2(table_1, queryTab1, modelTab1);
		table_1.getModel().addTableModelListener(firstPanel.new CheckBoxModelListener()); // listener do obslugi koluny dodaj
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_1.getColumnModel().getColumn(0).setPreferredWidth(80);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(135);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(448);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(50);
		
		/// ------  koniec tab1 -------------------------------\\\\\\\
	
		
		/// --------- --------  tworzenie tabeli2 w firstpanel ------------------------\\\\\\		
		createTable2();		
		/// ------  koniec tab2 -------------------------------\\\\\\\
		
		/// --------- --------  tworzenie TabOrders w SecondPanel ------------------------\\\\\\
				//table_2 = new JTable(modelTab2);
		tableSpOrders = secondPanel.setTableView(tableSpOrders, modelTabSpOrder);
		tableSpOrders.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//table_2.getModel().addTableModelListener(firstPanel.new CheckBoxModelListener2());		
		scrollPaneSpOrders.setViewportView(tableSpOrders);	
		// set data for tableSpOrders 
		secondPanel.mysqlConnection(tableSpOrders, modelTabSpOrder);
		
		tableSpOrders.addMouseListener(new MouseAdapter() {
			
			 public void mousePressed(MouseEvent mouseEvent) {
			        JTable table =(JTable) mouseEvent.getSource();
			        java.awt.Point point = mouseEvent.getPoint();
			        int row = table.rowAtPoint(point);
			        int col = table.columnAtPoint(point);
			        
			        Object idOrder =  table.getValueAt(row, 0);
			        Object NrN =  table.getValueAt(row, 1);
			        Object title =  table.getValueAt(row, 2);
			        Object ordered =  table.getValueAt(row, 3);
			        Object recipent =  table.getValueAt(row, 4);
			        Object data = table.getValueAt(row, 5);
			        
			        idOrders = (int) table.getValueAt(row, 0);
			        nrSite = (int) NrN;
			        dateStartOrder = (String) data;
			        
			        if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
			            // your valueChanged overridden method 			        	
			        	
			        	String queryitemsOrder = "select item_orders.bom_code, parts.name_part, parts.desciption \r\n" + 
				    			"from item_orders\r\n" + 
				    			"INNER JOIN parts\r\n" + 
				    			"ON item_orders.bom_code = parts.bom_code\r\n" + 
				    			"where item_orders.id_orders = '" +idOrder+ "' ";
			        	
//			        	String queryLabel = "select id_orders, site_nr, title_order, concat(ordered_by.imie, ' ', ordered_by.nazwisko)  as Wystawiajacy, \r\n" + 
//			    				"		concat(recipient_by.imie, ' ', recipient_by.nazwisko) as Odbierajacy , status_order, date_start\r\n" + 
//			    				"from orders\r\n" + 
//			    				"INNER JOIN ordered_by\r\n" + 
//			    				"ON orders.id_ordered_by = ordered_by.id_ordered_by\r\n" + 
//			    				"INNER JOIN recipient_by\r\n" + 
//			    				"ON orders.id_recipient_by = recipient_by.id_recipient_by" + 
//			    				"where orders.id_orders = '16' OR orders.id_orders = '26' ";
				    	
				    	secondPanel.mysqlConnectionItemOrder(tableSpPartStatus, queryitemsOrder, modelTabPartStatus);
				   
				    	makeVisible(NrN, title, ordered, recipent, data);
			        }
			 }
			
			
			
		});
		
		
				/// ------  koniec tabOrders -------------------------------\\\\\\\
		
		
		
		
	
	////   -----------  tworzenie tabeli w secondPanel tabPartStatus ----------------  \\\\\
		tableSpPartStatus = secondPanel.setTableViewItemOrder(tableSpPartStatus, modelTabPartStatus);
		//tableSpPartStatus.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPaneSpPartStatus.setViewportView(tableSpPartStatus);
		tableSpPartStatus.addMouseListener(new MouseAdapter() {
				
			public void mouseReleased(MouseEvent mouseEvent) {
		        JTable table =(JTable) mouseEvent.getSource();
		        java.awt.Point point = mouseEvent.getPoint();
		        
		        
		        if (mouseEvent.getClickCount() == 1 && table.getSelectedRow() != -1) {
		            // your valueChanged overridden method 
		        	int col = table.columnAtPoint(point);
			        int row = table.rowAtPoint(point);
			        Object status =  table.getValueAt(row, 4);
			       
		        }
		        secondPanel.checkStatus(tableSpPartStatus, 0);
		        
			}
		});
		
//		String queryitemsOrder = "select item_orders.bom_code, parts.name_part, parts.desciption \r\n" + 
//    			"from item_orders\r\n" + 
//    			"INNER JOIN parts\r\n" + 
//    			"ON item_orders.bom_code = parts.bom_code\r\n" + 
//    			"where item_orders.id_orders = '26'";
//    	
//    	secondPanel.mysqlConnectionItemOrder(tableSpPartStatus, queryitemsOrder, modelTabPartStatus);
						
						///    	       ----------- koniec taPartStatus -----------------    \\\
	
		button = new JButton("Wystaw");
		button.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{				
				int j = rowCountTab2;		
				
				if(j != 0) 
				{
					// ---------------------  otwiera newFrame polami o nrN i osobami pobierajacymi czesci
					
					tab2model = (DefaultTableModel)table_2.getModel();	
					
//					FrameOrderData newFrame = new FrameOrderData();
//					newFrame.SecondFrame();
					
					new FrameNewSr().setVisible(true);
					
					
					
					rowCountTab2 = getValue();
					
				}else
					{
						JOptionPane.showMessageDialog(null, "brak czesci do zamowienia");
					}
				
			}

		});
		button.setBounds(835, 149, 97, 42);
		fp.add(button);
		
		
		lblNewLabel = new JLabel("Lista cz\u0119\u015Bci, kt\u00F3re chesz pobra\u0107 z magazynu");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(233, 337, 295, 25);
		fp.add(lblNewLabel);
		
		
		tabbedPane.add("Zwrot czêœci", sp);
		sp.setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(46, 302, 434, 183);
		sp.add(panel);		
		panel.setLayout(null);
		panel.setVisible(false);
		
		lblInformacjeZamwienia = new JLabel("Informacje o zam\u00F3wieniu:");
		lblInformacjeZamwienia.setBounds(100, 11, 172, 23);
		panel.add(lblInformacjeZamwienia);
		lblInformacjeZamwienia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInformacjeZamwienia.setVisible(false);
		
		lblNrN = new JLabel("Nr N!");
		lblNrN.setBounds(10, 74, 51, 23);
		panel.add(lblNrN);
		lblNrN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNrN.setVisible(false);
		
		lblTytu = new JLabel("Tytu\u0142");
		lblTytu.setBounds(10, 94, 51, 23);
		panel.add(lblTytu);
		lblTytu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTytu.setVisible(false);
		
		lblWystawiajcy = new JLabel("Wystawiaj\u0105cy");
		lblWystawiajcy.setBounds(10, 124, 102, 23);
		panel.add(lblWystawiajcy);
		lblWystawiajcy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblWystawiajcy.setVisible(false);
		
		lblOdbierajcy = new JLabel("Odbieraj\u0105cy");
		lblOdbierajcy.setBounds(10, 141, 102, 23);
		panel.add(lblOdbierajcy);
		lblOdbierajcy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOdbierajcy.setVisible(false);
		
		lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(132, 78, 56, 14);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setVisible(false);
		
		label = new JLabel("New label");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(132, 98, 303, 14);
		panel.add(label);
		label.setVisible(false);
		
		label_1 = new JLabel("New label");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(132, 145, 127, 14);
		panel.add(label_1);
		label_1.setVisible(false);
		
		label_2 = new JLabel("New label");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_2.setBounds(132, 128, 127, 14);
		panel.add(label_2);
		label_2.setVisible(false);
		
		lblDataWystawienia = new JLabel("Data wystawienia");
		lblDataWystawienia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataWystawienia.setBounds(10, 45, 105, 14);
		panel.add(lblDataWystawienia);
		lblDataWystawienia.setVisible(false);
		
		lblData = new JLabel("data");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblData.setBounds(132, 45, 181, 14);
		panel.add(lblData);
		lblData.setVisible(false);
		
				
		lblNewLabel_2 = new JLabel("Podaj iCare SR");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(669, 462, 102, 23);
		sp.add(lblNewLabel_2);
		lblNewLabel_2.setVisible(false);
		
		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			
			public void keyTyped(KeyEvent e) {
				
				int counter = 0;
				// ----------------- tylko liczby ------------------------------------------------
				String str = textField_1.getText();
				char c = e.getKeyChar();
			    if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) 
			      {
			        
			        e.consume();
			      }
			}
			
			public void keyReleased(KeyEvent arg0) {
				
				
				if(textField_1.getText().equals("")) {
					textField_1.setBackground(Color.RED);
					
				}else {
					textField_1.setBackground(Color.WHITE);
					
				}
				
			}
		});
		
		textField_1.setForeground(Color.BLACK);
		textField_1.setBackground(Color.RED);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_1.setBounds(669, 496, 102, 20);
		sp.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setVisible(false);
		
		lblNewLabel_1 = new JLabel("Wype\u0142nij informacjie w tabeli odno\u015Bnie zwracanch cz\u0119\u015Bci");
		lblNewLabel_1.setBounds(46, 496, 422, 23);
		sp.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		
		comboBox = new JComboBox();
		comboBox.setBounds(488, 496, 171, 22);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {""}));
		sp.add(comboBox);
		comboBox.setVisible(false);
		
		AutoCompleteDecorator.decorate(comboBox);		
		try
	    {
	     	    // connection dla MYSQL   
	      //Connection conn = DriverManager.getConnection(myUrl, "root", "password");	  
	      
			// connection dla SQLite
	      String url = "jdbc:sqlite:C://HUA_MAG//DBhua_mag//hua_mag.db";	  			
	      Connection conn = DriverManager.getConnection(url);
	      System.out.println("Connection to SQLite has been established.");	
	      
	      String query = " select nazwisko, imie from recipient_by ";	      // create the java statement
	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);	      
	      	    
	      while (rs.next())
	      {
	    	
	    	  comboBox.addItem(rs.getString(1) + " " + rs.getString(2) ); // 1 - nazwisko, 2 - imie
	      }
	      st.close();
	      rs.close();
	      conn.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		
		
		btnNewButton = new JButton("Zapisz");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// !!!!!!!!!! dodac warunki przed zeby przed wcisneiciem button zapisz sprawdzalo czy uzytkowni wypelnil pola i nie ma pustych !!!!!!!!!!!!!!!
							
				int rowCount = tableSpPartStatus.getRowCount();
				int colCount = tableSpPartStatus.getColumnCount();
				String iCareSr = null;
				iCareSr = textField_1.getText();				
				String returnedPerson = null;
				returnedPerson = comboBox.getSelectedItem().toString();
				
				//if(returnedPerson == null || iCareSr == null)
				if(returnedPerson.equals("") || iCareSr.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Nie wpisano numeru SR albo nie wybrano osoby zwracaj¹cej");
					
				} else {
					
					returnedPerson = comboBox.getSelectedItem().toString();
					// create our mysql database connection	        
				    Connection conn;
					try {
						// conn dla MYSQL
						//conn = DriverManager.getConnection(myUrl, "root", "password");
						
						String url = "jdbc:sqlite:C://HUA_MAG//DBhua_mag//hua_mag.db";	  			
			  	        Connection conn2 = DriverManager.getConnection(url);
			  			System.out.println("Connection to SQLite has been established.");	
						//query for MYSQL
						//String query = " select phone_nr, company from recipient_by where concat(nazwisko, ' ', imie ) = '"+ returnedPerson +"' ";
			  			String query = " select phone_nr, company from recipient_by where nazwisko || ' ' || imie  = '"+ returnedPerson +"' ";
					    Statement st = conn2.createStatement();
					    ResultSet rs = st.executeQuery(query);
					    rs.next();
					    
					    phoneNr = rs.getString(1);
					    company = rs.getString(2);
					    
					    st.close();			     
					    rs.close();
					   
					    conn2.close();
					    
					    
					   
					    
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.err.println(e.getMessage());
					}	      
				   
					
					secondPanel.returnPart(idOrders, nrSite, dateStartOrder, rowCount, colCount, iCareSr, returnedPerson, phoneNr, company);
					
				}
					
					
				
				
			}
		});
		btnNewButton.setBounds(825, 593, 121, 63);
		sp.add(btnNewButton);
		btnNewButton.setVisible(false);
		
	
		
		lblNewLabel_4 = new JLabel("Osoba zwracaj\u0105ca cz\u0119sci");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(488, 465, 171, 16);
		sp.add(lblNewLabel_4);
		lblNewLabel_4.setVisible(false);
		lblNewLabel_1.setVisible(false);
		
		
        frame.getContentPane().add(tabbedPane);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1012, 803);
        // frame.pack();
        //frame.setLocationByPlatform(true);
        frame.setVisible(true);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MainFrame2();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} // -------------- end main(String[] args)
	
	
	public int getValue() 
	{
		return this.rowCountTab2;
	}

	
	public void makeVisible(Object nrN, Object title, Object ordered, Object recipent, Object data) 
	{
		
		lblNewLabel_3.setText(nrN.toString());
	    label.setText(title.toString());
        label_2.setText(ordered.toString());
        label_1.setText(recipent.toString());
        lblData.setText(data.toString());
		
		panel.setVisible(true);		
		lblInformacjeZamwienia.setVisible(true);
		lblNrN.setVisible(true);
		lblTytu.setVisible(true);
		lblWystawiajcy.setVisible(true);
		lblOdbierajcy.setVisible(true);
		label_1.setVisible(true);
		label_2.setVisible(true);
		lblNewLabel_2.setVisible(true);
		lblNewLabel_1.setVisible(true);
		lblNewLabel_3.setVisible(true);
		label.setVisible(true);
		lblDataWystawienia.setVisible(true);
		lblData.setVisible(true);	
		textField_1.setVisible(true);
		textField_1.setText("");
		lblNewLabel_4.setVisible(true);		
		comboBox.setVisible(true);	
		//comboBox.setSelectedItem("");
		btnNewButton.setVisible(true);
		
	}
	
	
	public void createTable2()
	{
		table_2 = firstPanel.setTableView1(table_2, modelTab2);	
		scrollPane_2.setViewportView(table_2);	
		table_2.getModel().addTableModelListener(firstPanel.new CheckBoxModelListener2());
		
		table_2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_2.getColumnModel().getColumn(0).setPreferredWidth(80);
		table_2.getColumnModel().getColumn(1).setPreferredWidth(135);
		table_2.getColumnModel().getColumn(2).setPreferredWidth(448);
		table_2.getColumnModel().getColumn(3).setPreferredWidth(50);
		
	}
	
	
	
	
	public void clearTable2()
	{
		DefaultTableModel model = (DefaultTableModel) table_2.getModel();
		model.setRowCount(0);
		System.out.println("FUNKCJA START");
		
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}// ---  end MainFrame class





