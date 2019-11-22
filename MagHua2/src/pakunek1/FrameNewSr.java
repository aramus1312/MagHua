package pakunek1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

import org.apache.log4j.chainsaw.Main;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdesktop.swingx.autocomplete.AutoCompleteComboBoxEditor;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.SwingConstants;
public class FrameNewSr extends JFrame {

public static ArrayList<String> listBoom  = new ArrayList<>();	
	
	private JFrame frame;

	private JPanel contentPane;
	private JTextField textFieldNrN;
	public int ind2;
	
	String myUrl = "jdbc:mysql://127.0.0.1:3306/hua_mag?useSSL=false";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameNewSr frame = new FrameNewSr();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameNewSr() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 399, 356);
		//frame.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);		
		
		JLabel lblNewLabel = new JLabel("Nr Lokalizacji");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(47, 23, 87, 29);
		contentPane.add(lblNewLabel);
		
		textFieldNrN = new JTextField();
		textFieldNrN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				int counter = 0;
				// ----------------- tylko liczby ------------------------------------------------
				String str = textFieldNrN.getText();
				char c = e.getKeyChar();
			    if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) 
			      {
			        
			        e.consume();
			      }
			     
			    
			    // ---------------  ustawia ze textfield przyjmuje tylko 5 znakow -----------------------
			    if(str == null || str.length() == 0)
				      {
				    	  str = "98";
				    	  System.out.println("jest null");
				      }
			    int number = Integer.parseInt(str);
			    while(number > 0) {
			    	  number = number / 10;
			    	  counter = counter + 1; 
					}
				if(counter >= 5)
					    {
					    	e.consume();
					    }				   
				
			}
			
		});
		
		
		
		textFieldNrN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldNrN.setBounds(167, 29, 86, 29);
		contentPane.add(textFieldNrN);
		textFieldNrN.setColumns(5);
		
		JLabel lblSystem = new JLabel("System");
		lblSystem.setHorizontalAlignment(SwingConstants.LEFT);
		lblSystem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSystem.setBounds(47, 124, 87, 29);
		contentPane.add(lblSystem);
		
		JComboBox comboBoxSystemType = new JComboBox();
		comboBoxSystemType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxSystemType.setModel(new DefaultComboBoxModel(new String[] {"", "GSM", "LTE", "UMTS"}));
		comboBoxSystemType.setBounds(167, 124, 86, 29);
		contentPane.add(comboBoxSystemType);
		
		JLabel lblWystawiajcy = new JLabel("Wystawiaj\u0105cy");
		lblWystawiajcy.setHorizontalAlignment(SwingConstants.LEFT);
		lblWystawiajcy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblWystawiajcy.setBounds(47, 176, 87, 29);
		contentPane.add(lblWystawiajcy);
		
		JLabel lblOdbierajcy = new JLabel("Odbieraj\u0105cy");
		lblOdbierajcy.setHorizontalAlignment(SwingConstants.LEFT);
		lblOdbierajcy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOdbierajcy.setBounds(47, 227, 87, 29);
		contentPane.add(lblOdbierajcy);
		
		
		JComboBox comboBoxRecipientBy = new JComboBox();
		comboBoxRecipientBy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxRecipientBy.setModel(new DefaultComboBoxModel(new String[] {""}));
		comboBoxRecipientBy.setBounds(167, 227, 142, 29);
		contentPane.add(comboBoxRecipientBy);		
		
		AutoCompleteDecorator.decorate(comboBoxRecipientBy);		
		try
	    {
	      // conn dla MYSQL	        
	      //Connection conn = DriverManager.getConnection(myUrl, "root", "password");	 
		  String url = "jdbc:sqlite:C://HUA_MAG//DBhua_mag//hua_mag.db";	  			
  	      Connection conn = DriverManager.getConnection(url);
  		  System.out.println("Connection to SQLite has been established.");
			
	      String query = " select nazwisko, imie from recipient_by ";	      // create the java statement
	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);	      
	      	    
	      while (rs.next())
	      {
	    	
	    	  comboBoxRecipientBy.addItem(rs.getString(1) + " " + rs.getString(2) ); // 1 - nazwisko, 2 - imie
	      }
	      st.close();
	      rs.close();
	      conn.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception in FrameNewSr.comboBoxRecipientBy ");
	      System.err.println(e.getMessage());
	    }
		
		
		
		JComboBox comboBoxOrderedBy = new JComboBox();
		comboBoxOrderedBy.setModel(new DefaultComboBoxModel(new String[] {""}));
		comboBoxOrderedBy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxOrderedBy.setBounds(167, 176, 142, 29);
		contentPane.add(comboBoxOrderedBy);
		
		  
		AutoCompleteDecorator.decorate(comboBoxOrderedBy);		

		try
	    {
	      // conn dla MYSQL	        
	      //Connection conn = DriverManager.getConnection(myUrl, "root", "password");	   
			
		  String url = "jdbc:sqlite:C://HUA_MAG//DBhua_mag//hua_mag.db";	  			
          Connection conn = DriverManager.getConnection(url);
		  System.out.println("Connection to SQLite has been established.");
  			
	      String query = " select nazwisko, imie from ordered_by";	      // create the java statement
	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);	      
	      
	    
	      while (rs.next())
	      {
	    	
	    	  comboBoxOrderedBy.addItem(rs.getString(1) + " " + rs.getString(2) ); // 1 - nazwisko, 2 - imie
	      }
	      st.close();
	      rs.close();
	      conn.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! in FrameNewSr.comboBoxOrderedBy ");
	      System.err.println(e.getMessage());
	    }
		
		JComboBox comboBoxMno = new JComboBox();
		comboBoxMno.setModel(new DefaultComboBoxModel(new String[] {"", "Orange", "T-Mobile"}));
		comboBoxMno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxMno.setBounds(167, 73, 115, 29);
		contentPane.add(comboBoxMno);
		
		
		JButton btnSave = new JButton("Zapisz");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				String nrN, systemType, recipientBy, orderedBy, mnoName;
				int idrecipientBy, idOrderedBy, idOrders, phoneOrdered, phoneRecipient, nrNInt;
				
				System.out.println("@@@@@@@@@@@@@@@@@@@");
				ArrayList<String> listalll = new ArrayList<String>();
			    int j = MainFrame2.rowCountTab2;
				
				
				//if(nrN == "" || systemType == "" || recipientBy == "" || orderedBy == "")
				if(comboBoxRecipientBy.getSelectedItem() == null || comboBoxRecipientBy.getSelectedItem().equals("") ||comboBoxOrderedBy.getSelectedItem() == null || comboBoxOrderedBy.getSelectedItem().equals("")  || textFieldNrN.getText().equals("") || comboBoxSystemType.getSelectedItem().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Nie wype³nio wszystkich pól z informacjami o zamówieniu");
				}
				else {
					
					try
					    {
						  nrN = textFieldNrN.getText();				
						  systemType = comboBoxSystemType.getSelectedItem().toString();
						  recipientBy = comboBoxRecipientBy.getSelectedItem().toString(); //!!!!! DODAC JESLI PSUTO TO KOMUNIKAT ZE PUSTO
						  orderedBy = comboBoxOrderedBy.getSelectedItem().toString();	
						  mnoName = comboBoxMno.getSelectedItem().toString();
						 
						  nrNInt = Integer.parseInt(nrN);
							
					      // create our mysql database connection	        
					      //Connection conn = DriverManager.getConnection(myUrl, "root", "password");	 							
						
						  String url = "jdbc:sqlite:C://HUA_MAG//DBhua_mag//hua_mag.db";	  			
				  	      Connection conn = DriverManager.getConnection(url);
				  		  System.out.println("Connection to SQLite has been established.");						  
				  		  // query dla MYSQL
//					      String query = " select id_recipient_by, phone_nr from recipient_by where concat(nazwisko, ' ', imie ) = '"+ recipientBy +"' ";	 
//					      String query2 = " select id_ordered_by, phone_nr from ordered_by where concat(nazwisko, ' ', imie ) = '"+ orderedBy +"' ";
				  		  
				  		  String query = " select id_recipient_by, phone_nr from recipient_by where nazwisko || ' ' || imie  = '"+ recipientBy +"' ";	 
					      String query2 = " select id_ordered_by, phone_nr from ordered_by where nazwisko || ' ' || imie  = '"+ orderedBy +"' ";
					    
					      Statement st = conn.createStatement();
					      ResultSet rs = st.executeQuery(query);
					      rs.next();	     
					    	  
					      idrecipientBy = Integer.parseInt(rs.getString(1));
					      phoneRecipient = Integer.parseInt(rs.getString(2));
					      System.out.println("TEL recipient: " + phoneRecipient);
					      st.close();			     
					      rs.close();
					   	  
					   	  Statement st2 = conn.createStatement();
					   	  ResultSet rs2 = st2.executeQuery(query2);
					    	 
				    	  rs2.next();
				    	  idOrderedBy = Integer.parseInt(rs2.getString(1));
				    	  phoneOrdered = Integer.parseInt(rs2.getString(2));
				    	  System.out.println("TEL ordered: " + phoneOrdered);
				    	  st2.close();			     
					      rs2.close();	  
					     
					   // insert into orders
					      nrNInt = Integer.parseInt(nrN);
					      String queryInsert = " INSERT INTO orders (site_nr, id_ordered_by, id_recipient_by, status_order) VALUES ('"+ nrNInt +"', '"+ idOrderedBy +"', '"+ idrecipientBy +"', 'open') ";
					      Statement stInsert = conn.createStatement();
					      stInsert.execute(queryInsert);
					      stInsert.close();
					      
					      // -------------- pobiera ID ordes dla ktorego zrobilo insert 
					      String queryGetIdOrder = " SELECT id_orders from orders WHERE site_nr = '"+ nrNInt +"' AND id_ordered_by = '"+ idOrderedBy +"' AND id_recipient_by = '"+ idrecipientBy +"' AND status_order = 'open' ";
					      Statement stGetIdOrder = conn.createStatement();
					      ResultSet rsGetIdOrder = stGetIdOrder.executeQuery(queryGetIdOrder);
					      System.out.println("WWWWWWWWWW " + idOrderedBy + " + " + phoneOrdered);
					      rsGetIdOrder.next();
					      idOrders = Integer.parseInt(rsGetIdOrder.getString(1));
					      System.out.println("WWWWWWWWWW " + idOrderedBy + " + " + phoneOrdered);
					      stGetIdOrder.close();
					      rsGetIdOrder.close();
					      
					      System.out.println("ID ORDERS: " + idOrders);
					      
					      //-----------------------  pobieranie bomcode z listy 
					     
					      Statement stItemOrders = conn.createStatement();
					      for(int i=0; i < j; i++) 
					      {	
								//DefaultTableModel modelBom = new DefaultTableModel();
								//tab2model = (DefaultTableModel)table_2.getModel();	    				
								String bomcode = (String)MainFrame2.tab2model.getValueAt(i, 0);
								System.out.println(bomcode);
								//listBoom.add(bomcode);
								
								listalll.add(bomcode);
								
								String InsertItemOrders = "insert into item_orders(id_orders, bom_code)	values  ('"+ idOrders +"', '"+ bomcode +"')";
							    
							    stItemOrders.execute(InsertItemOrders);
								
									
					      }
					      stItemOrders.close();
					      
					  	//---------------------------------- zapis do pliku excel 	TemplateNewSR -----------------
							FileInputStream fisNewSr;			
							
							//------------------------ start try -------------------------------------------
							try {
								
								fisNewSr = new FileInputStream(new File("C:\\HUA_MAG\\TemplateNewSR.xlsm"));
								XSSFWorkbook workbookNewSr = new XSSFWorkbook (fisNewSr);			
								workbookNewSr.setForceFormulaRecalculation(true);
								XSSFSheet sheetNewSr = workbookNewSr.getSheetAt(0);
								
								FormulaEvaluator evaluator = workbookNewSr.getCreationHelper().createFormulaEvaluator();
								
								XSSFRow rowH11 = sheetNewSr.getRow(10);					
								XSSFCell cellH11 = rowH11.getCell(7); // 	cell H11									
								cellH11.setCellValue(orderedBy);
								
								XSSFRow rowH13 = sheetNewSr.getRow(12);					
								XSSFCell cellH13 = rowH13.getCell(7); // 	cell H13									
								cellH13.setCellValue(recipientBy);
								
								XSSFRow rowH7 = sheetNewSr.getRow(6);					
								XSSFCell cellH7 = rowH7.getCell(7); // 	cell H7									
								cellH7.setCellValue(systemType);
								
								XSSFRow rowI8 = sheetNewSr.getRow(7);					
								XSSFCell cellI8 = rowI8.getCell(8); // 	cell I8									
								cellI8.setCellValue(nrN);
								
								XSSFRow rowJ8 = sheetNewSr.getRow(8);					
								XSSFCell cellJ8 = rowJ8.getCell(8); // 	cell J8									
								cellJ8.setCellValue(nrN);
								
								XSSFRow rowH12 = sheetNewSr.getRow(11);					
								XSSFCell cellH12 = rowH12.getCell(7); // 	cell H12									
								cellH12.setCellValue(phoneOrdered);
								
								XSSFRow rowH14 = sheetNewSr.getRow(13);					
								XSSFCell cellH14 = rowH14.getCell(7); // 	cell H14									
								cellH14.setCellValue(phoneRecipient);
								
								
								if(MainFrame2.rowCountTab2 >= 1)
								{
									String partsAll;
									String partFinall = "";
									String firstPart = listalll.get(0);
									for(int i=0; i < j; i++)
									{
										
										String part = listalll.get(i);
										partsAll =  part + " + ";
										partFinall = partFinall + partsAll;
									}
									String partFinal2 = partFinall.substring(0, partFinall.length() - 3);
									XSSFRow rowH10 = sheetNewSr.getRow(9);					
									XSSFCell cellH10 = rowH10.getCell(7); // 	cell H10										
									cellH10.setCellValue(partFinal2);
								}
								
								
								XSSFFormulaEvaluator.evaluateAllFormulaCells(workbookNewSr); // recalculating all formulas						
								
								Cell excelValue;
								excelValue = workbookNewSr.getSheetAt(0).getRow(12).getCell(3);   //D13					get value from c11 - and insert to  orders -titl_orders	
								String excelString = excelValue.getRichStringCellValue().toString();						
								//JOptionPane.showMessageDialog(null, excelString);
								
								
								fisNewSr.close();
								
								// update do tabeli orders kolumny z tytulem zamowienia
								Statement stTitleOrders = conn.createStatement();
								String InsertTitleOrders = "update orders set title_order = '"+ excelString +"' WHERE id_orders =  '"+ idOrders +"' ";   //SELECT id_orders from orders WHERE site_nr = '"+ nrNInt +"'
							    
								stTitleOrders.execute(InsertTitleOrders);
								stTitleOrders.close();
								
								
								// Write the output to a file
								try
								{
							        FileOutputStream fileOut = new FileOutputStream("C:\\HUA_MAG\\TemplateNewSR.xlsm");
							        workbookNewSr.write(fileOut);
							        workbookNewSr.close();
							        fileOut.close();
								}catch (IOException e)
									{
									// conn dla MYSQL
									//Connection connDelete = DriverManager.getConnection(myUrl, "root", "password");	   // lepiej byloby gdyby sprawdzalo najpierw czy plik otwarty i nie pozzwalalo zrovic insert ale nie moglem zrobic   
									//String url = "jdbc:sqlite:C://HUA_MAG//DBhua_mag//hua_mag.db";	  			
						  	        Connection connDelete = DriverManager.getConnection(url);
						  			
									
									String queryDelete = "delete from orders where id_orders = '"+ idOrders +"' ";
									Statement stDelete = connDelete.createStatement();
									stDelete.executeUpdate(queryDelete);
									System.out.println("catch "+idOrders);
									stDelete.close();
									connDelete.close();
									System.out.println("Delete in SQLite OK. (FrameNewSR)");
									// TODO Auto-generated catch block
									e.printStackTrace();
									JOptionPane.showMessageDialog(frame, "Otwarty plik TemplateNewSR.xlsx. Zamknij ¿eby mo¿na by³o zapisaæ zmiany");
									
									
									
									}
								
								
									
								conn.close();	
					
		
								////********************otwiera plik*****************************###***********
						File filehistory = new File("C:\\HUA_MAG\\TemplateNewSR.xlsm");
						Desktop dt = Desktop.getDesktop();
						try {
							dt.open(filehistory);
							
						} catch (IOException e3) {
							// TODO Auto-generated catch block
							e3.printStackTrace();
						}
							
								
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							//------------------------ end 	try-catch blok ------------------------------------------
							
							//--------------------------- koniec zapisu do pliku excel ------------------------
							
					      
					      
					    }
				    catch (Exception e)
				    {
				      System.err.println("Got an exception! in FrameNewSr.btnSave ");
				      System.err.println(e.getMessage());
				    }
					
			
					
				}
				
					
			//MainFrame mainF = new MainFrame();
			//mainF.clearTable2();
			// clear all rows in table2 
			DefaultTableModel def = (DefaultTableModel) MainFrame2.table_2.getModel();
			def.setRowCount(0);
			MainFrame2.listA.clear();
			MainFrame2.listB.clear();
			
			// refresh data in tableSpOrders
			SecondPanel sP = new SecondPanel();
			sP.mysqlConnection(MainFrame2.tableSpOrders, MainFrame2.modelTabSpOrder);
				
			setVisible(false);
			dispose();	// zamyka frame
			
			
			
				
			} 	
				
		});
		
		
		
		btnSave.setBounds(137, 283, 102, 23);
		contentPane.add(btnSave);
		
		JLabel lblOperator = new JLabel("Operator");
		lblOperator.setHorizontalAlignment(SwingConstants.LEFT);
		lblOperator.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOperator.setBounds(47, 67, 87, 29);
		contentPane.add(lblOperator);
		
		
		
		
		//--------------------------
			
		
		
		
	}
}
