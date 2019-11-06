package pakunek1;

import pakunek1.MainFrame2;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mysql.jdbc.PreparedStatement;

public class SecondPanel extends JPanel{
	
	JTable  tableOrder;
	DefaultTableModel defModel;
	private JTable tableTmp;
	private String query;
	int colCount = 0;
	
	public static int goodPartCount = 0;
	public static int faultPartCount = 0;
	public static int updateCount = 0;
	public static int openTemplateSr = 0;
	public static int countBlank = 0;
	
    
	String myUrl = "jdbc:mysql://127.0.0.1:3306/hua_mag?useSSL=false";
	private JTable tablePart;
	private DefaultTableModel defModelPart;
   
    public JTable setTableView(JTable table, DefaultTableModel model) {
    	
    	
    	
    	this.tableOrder = table;
		this.defModel = model;
		//this.colCount = colCout; 
		
		
			//DefaultTableModel model = new DefaultTableModel(data, columnNames);
			tableOrder = new JTable(model){
	
	            private static final long serialVersionUID = 1L;
	            
	            TableColumn columnTable;
	            
	            @Override
	            public Class getColumnClass(int column) {
	                switch (column) {
	                    case 0:
	                    	columnTable = tableOrder.getColumnModel().getColumn(0);
	                    	columnTable.setPreferredWidth(30);
	                        return Integer.class;
	                    case 1:
	                    	columnTable = tableOrder.getColumnModel().getColumn(1);
	                    	columnTable.setPreferredWidth(60);
	                        return Integer.class;
	                    case 2:
	                    	columnTable = tableOrder.getColumnModel().getColumn(2);
	                    	columnTable.setPreferredWidth(300);
	                        return String.class;
	                    case 3:
	                    	columnTable = tableOrder.getColumnModel().getColumn(3);
	                    	columnTable.setPreferredWidth(140);
	                    	return String.class;
	                    case 4:
	                    	columnTable = tableOrder.getColumnModel().getColumn(4);
	                    	columnTable.setPreferredWidth(140);
	                    	return String.class;
	                    case 5:
	                    	columnTable = tableOrder.getColumnModel().getColumn(5);
	                    	columnTable.setPreferredWidth(140);
	                    	return String.class;    
	                    case 6:
	                    	columnTable = tableOrder.getColumnModel().getColumn(6);
	                    	columnTable.setPreferredWidth(40);
	                    	return String.class;    
	                    default:
	                        return Boolean.class;
	                }
	            }
	        };
	       
       
		JTableHeader header1 = tableOrder.getTableHeader(); // header ustawia kolor w nazwach kolumn
	    header1.setBackground(Color.blue);
	    header1.setForeground(Color.yellow);
		
		return tableOrder;
    	
    }
    
    
    
    
    public JTable setTableViewItemOrder(JTable table, DefaultTableModel model ) {
    	
    	
    	
    	this.tablePart = table;
		this.defModelPart = model;
		//this.colCount = colCout; 
		
		JComboBox comboBox = new JComboBox();
		
		comboBox.addItem("Good");
		comboBox.addItem("Faulty");
		
		
				
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				comboBox.setBackground(Color.CYAN);
				
				
				
			}
		});
				
			//DefaultTableModel model = new DefaultTableModel(data, columnNames);
		tablePart = new JTable(model){
	
	            private static final long serialVersionUID = 1L;
	            
	            TableColumn columnTable;
	         
	            
	            @Override
	            public Class getColumnClass(int column) {
	                switch (column) {	                   
	                    case 0:
	                    	columnTable = tablePart.getColumnModel().getColumn(0);
	                    	columnTable.setPreferredWidth(80);
	                        return String.class;
	                    case 1:
	                    	columnTable = tablePart.getColumnModel().getColumn(1);
	                    	columnTable.setPreferredWidth(100);
	                    	return String.class;
	                    case 2:
	                    	columnTable = tablePart.getColumnModel().getColumn(2);
	                    	columnTable.setPreferredWidth(330);
	                    	return String.class;
	                    case 3:
	                    	columnTable = tablePart.getColumnModel().getColumn(3);
	                    	columnTable.setPreferredWidth(140);
	                    	return String.class;  	 
	                    case 4:
	                    	columnTable = tablePart.getColumnModel().getColumn(4);
	                    	columnTable.setPreferredWidth(70);
	                    	columnTable.setCellEditor(new DefaultCellEditor(comboBox));
	                    	
	                    	return String.class;  	   
	                    default:
	                        return String.class;
	                }
	            }
	        };
	    	
	        tablePart.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	
		JTableHeader header1 = tablePart.getTableHeader(); // header ustawia kolor w nazwach kolumn
	    header1.setBackground(Color.blue);
	    header1.setForeground(Color.yellow);
		
		return tablePart;
    	
    }
    
    
    
    
    
    
    
  //---------------------- wczytywanie i pobieranie danych z mysql ----------------
  	public void mysqlConnection(JTable table, DefaultTableModel model) 
  	{
  		
  		this.tableTmp = table;  		 
  		this.defModel = model;
  		
  		///  query dla MYSQL 
//		queryTabOrders2 = "select id_orders, site_nr, title_order, concat(ordered_by.imie, ' ', ordered_by.nazwisko)  as Wystawiajacy, \r\n" + 
//				"		concat(recipient_by.imie, ' ', recipient_by.nazwisko) as Odbierajacy , status_order, date_start\r\n" + 
//				"from orders\r\n" + 
//				"INNER JOIN ordered_by\r\n" + 
//				"ON orders.id_ordered_by = ordered_by.id_ordered_by\r\n" + 
//				"INNER JOIN recipient_by\r\n" + 
//				"ON orders.id_recipient_by = recipient_by.id_recipient_by";
		
		// query dla SQLITE			
  		this.query = "select id_orders, site_nr, title_order, ordered_by.imie || ' ' || ordered_by.nazwisko as Wystawiajacy, \r\n" + 
				"		recipient_by.imie || ' ' || recipient_by.nazwisko as Odbierajacy, date_start , status_order\r\n" + 
				"from orders\r\n" + 
				"INNER JOIN ordered_by\r\n" + 
				"ON orders.id_ordered_by = ordered_by.id_ordered_by\r\n" + 
				"INNER JOIN recipient_by\r\n" + 
				"ON orders.id_recipient_by = recipient_by.id_recipient_by WHERE orders.status_order =  'open' ";
  		
  		//Connection conn = null;
  		try
  	    {
  			
  			
  			
  	    	String url = "jdbc:sqlite:C://HUA_MAG//DBhua_mag//hua_mag.db";	  			
  	        Connection conn = DriverManager.getConnection(url);
  			System.out.println("Connection to SQLite has been established.");	
  			
  			// connection dla MYSQL
	  	    //Connection conn = DriverManager.getConnection(myUrl, "root", "password");	
	  	    
	  	   
	  	    Statement st = conn.createStatement();
	  	    ResultSet rs = st.executeQuery(query);	
	  	     
  	      
	  	    //DefaultTableModel DefTableMode = (DefaultTableModel)tableTmp.getModel();
	  	      
	  	    model.setRowCount(0);
	  	    while (rs.next())
	  	    {
	  	    	//"ID", "NrN", "Tytul_zamowienia","Wystawiajacy", "Odbierajacy", "status"
	  	    	Integer ID = rs.getInt("id_orders");
	  	    	Integer NrN = rs.getInt("site_nr");
	  	        String Tytul_zamowienia = rs.getString("title_order");
	  	        String Wystawiajacy = rs.getString("Wystawiajacy");
	  	        String Odbierajacy = rs.getString("Odbierajacy");
	  	        String status = rs.getString("status_order");
	  	        String date = rs.getString("date_start");
	  	       
	  	        model.addRow(new Object[] {ID, NrN ,Tytul_zamowienia, Wystawiajacy, Odbierajacy, date, status});
	  	    }
	  	    
	  	    st.close();
	  	    conn.close();
  	    }catch (Exception e)  		
	  	    {
		  	      System.err.println("Got an exception! in SecondPanel.mysqlConnection() ");
		  	      System.err.println(e.getMessage());
	  	    }
  		
  	}
   
  	
    //---------------------- wczytywanie i pobieranie danych z mysql ----------------
  	public void mysqlConnectionItemOrder(JTable table, String query, DefaultTableModel model) 
  	{
  		
  		this.tableTmp = table;
  		this.query = query;
  		this.defModel = model;
  		
  		try
  	    {
  	      // create our mysql database connection
  	     
  	      	// conn dla MYSQL     
  	      //Connection conn = DriverManager.getConnection(myUrl, "root", "password");	
  	      
  	      String url = "jdbc:sqlite:C://HUA_MAG//DBhua_mag//hua_mag.db";	  			
	      Connection conn = DriverManager.getConnection(url);
	      System.out.println("Connection to SQLite has been established.");
  	      
  	      
  	      //String query = "SELECT * FROM parts";	      // create the java statement
  	      Statement st = conn.createStatement();
  	      ResultSet rs = st.executeQuery(query);	
  	    
  	      
  	      //DefaultTableModel DefTableMode = (DefaultTableModel)tableTmp.getModel();
  	      
  	      model.setRowCount(0);
  	      while (rs.next())
  	      {
  	    	//"ID", "NrN", "Tytul_zamowienia","Wystawiajacy", "Odbierajacy", "status"
  	    	String ID = rs.getString("bom_code");
  	    	String NrN = rs.getString("name_part");
  	        String Tytul_zamowienia = rs.getString("desciption");
  	       
  	       
  	       
  	        model.addRow(new Object[] {ID, NrN ,Tytul_zamowienia});
  	      }
  	      st.close();
  	    }
  	    catch (Exception e)
  	    {
  	      System.err.println("Got an exception in SecondPanel.mysqlConnectionItemOrder ");
  	      System.err.println(e.getMessage());
  	    }
  		
  	}
    
  	public void checkStatus(JTable table, int row) {
  		
  		String valueStatus = (String) table.getValueAt(row, 4);
  		
  		
  	}
  	
  	
  	public void makeVisible() {
  		
  	goodPartCount = 0;
  	faultPartCount = 0;
  		
  	}

//    public void paintComponent(Graphics g){
//        g.setColor(Color.BLACK);
//        g.setFont(new Font("Verdana",Font.BOLD,16));
//        g.drawString("Hello there again!", 20, 20);
//    }
  	int idOrders, rowCount, columnCount, nrN;
  	String iCareSR, dateStartOrder, returnedPerson, phonePerson, companyPerson;
  	
  	public void returnPart(int idO, int nrSite, String dateStartOrder, int rowC, int colC, String iCare, String person, String phone, String company) {
  		
  		this.idOrders = idO;
  		this.nrN = nrSite;
  		this.dateStartOrder = dateStartOrder;
  		this.rowCount = rowC;
  		this.columnCount = colC;
  		this.iCareSR = iCare;
  		this.returnedPerson = person;
  		this.phonePerson = phone;
  		this.companyPerson = company;
  		
  		openTemplateSr = 0;
//  		Object[][] tableValue = new Object[rowCount+1][colCount+1];
//		
//		for(int i =0; i<rowCount -1; i++) {
//			for(int i =0; i<rowCount -1; i++)
//				
//				tableValue[rowCount][colCount] = tableSpPartStatus.getValueAt(rowCount, colCount);
//			}
//			
//		}
//		
//		System.out.println(rowCount);
//		System.out.println(colCount);
  	
  		
  		FileInputStream fisNewSr;
  		
  		
		try {
			fisNewSr = new FileInputStream(new File("C:\\HUA_MAG\\TemplateZwrotCzesci.xlsm"));
			XSSFWorkbook workbookNewSr = new XSSFWorkbook (fisNewSr);			
			workbookNewSr.setForceFormulaRecalculation(true);
			XSSFSheet sheetNewSr = workbookNewSr.getSheetAt(0);
			
			FormulaEvaluator evaluator = workbookNewSr.getCreationHelper().createFormulaEvaluator();
			// ------ czysci komorki w arkuszu  ----------------
			for(int row2 =0; row2<8; row2++) {
				int licznik =0;
				 while(licznik<15){
					 XSSFRow rowBlankFault = sheetNewSr.getRow(2+row2);	
					 XSSFRow rowBlankGood = sheetNewSr.getRow(13+row2);
					 
					 XSSFCell cellBlankFault = rowBlankFault.getCell(0+licznik); // 	komórka A3	### col A -0, row 3 -2
					 //cellBlankFault.setCellValue(null);
					 cellBlankFault.setCellType(CellType.BLANK);
					 
					 XSSFCell cellBlankGood = rowBlankGood.getCell(0+licznik); // 	komórka A3	### col A -0, row 3 -2
					// cellBlankGood.setCellValue("");
					 cellBlankGood.setCellType(CellType.BLANK);
				
					  licznik++;
				 	}
				}
			
//			
//			String bomCode = "";
//			String namePart = "";
//			String description = "";
//			String partSN = "";
//			String stan = ""; 
			
			String bomCode = null;
			String namePart = null;
			String description = null;
			String partSN = null;
			String stan = null; 
			
			countBlank = 0;
			
						
			// -------------  petla ktora leci po calej tabeli i wychwytuje puste komorki jesli wystapi pusta wartosc ----------------
			// i dodaje +1 do countBlank, pozniej sprawdza jesli countBlank > 0 tzn ze w tabeli sa puste pola i wyrzuca komunikat
			for(int r2 =0; r2<rowCount; r2++) 
			{
				
				bomCode = (String) MainFrame2.tableSpPartStatus.getValueAt(r2, 0);					
				namePart = (String) MainFrame2.tableSpPartStatus.getValueAt(r2, 1);					
				description = (String) MainFrame2.tableSpPartStatus.getValueAt(r2, 2);					
				partSN = (String) MainFrame2.tableSpPartStatus.getValueAt(r2, 3);				
				stan = (String) MainFrame2.tableSpPartStatus.getValueAt(r2, 4);
				
				if(bomCode == null || bomCode.equals("") || namePart == null || namePart.equals("") || description == null || description.equals("") || partSN == null || partSN.equals("") || stan == null || stan.equals(""))
				{
					countBlank =  countBlank +1;					
				}
			}
			// -----------------------							--------------------------\\
			
			
			
			if(countBlank > 0)
			{
				JOptionPane.showMessageDialog(null, "Nie wype³nio wszystkich w tabeli odnoœnie informacji o zwracanych czêœciach");
				
				
			}else {
			// start else	
			
			for(int r =0; r<rowCount; r++) {
								
					String currentDate, returnedPerson;
					
					SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd"); //"yyyy-MM-dd 'at' HH:mm:ss z"
					Date date = new Date(System.currentTimeMillis());
					currentDate = formatter.format(date);					
					
					bomCode = (String) MainFrame2.tableSpPartStatus.getValueAt(r, 0);					
					namePart = (String) MainFrame2.tableSpPartStatus.getValueAt(r, 1);					
					description = (String) MainFrame2.tableSpPartStatus.getValueAt(r, 2);					
					partSN = (String) MainFrame2.tableSpPartStatus.getValueAt(r, 3);					
					stan = (String) MainFrame2.tableSpPartStatus.getValueAt(r, 4);
					
					
				///  -------------------------tworzy layout panelu do wpisuwania info opisu uszkodzenia czesci-----------------------------------------  \\\						
					String text;						
					String[] options = {"OK"};
					JPanel panel = new JPanel();
					JLabel lbl = new JLabel("Opis uszkodzenia czêœci: " + bomCode + " " + namePart);				
					JTextField txt = new JTextField(40);
					panel.add(lbl);
					panel.add(txt);
					
					txt.setForeground(Color.BLACK);
					txt.setBackground(Color.RED);
					txt.setFont(new Font("Tahoma", Font.PLAIN, 14));				
					txt.addKeyListener(new KeyAdapter() {
						@Override
						public void keyReleased(KeyEvent arg0) {
							
							if(txt.getText().equals("")) {
								txt.setBackground(Color.RED);
								
							}else {
								txt.setBackground(Color.WHITE);
								
							}
							
						}
					});
				/// -------------------------------- -------------------   \\\		
						
						
						updateCount = 0;
						if(stan.equals("Faulty")) {
							
						// -------------- wywoluje optionDialog do wpisywania opisu -----------------  \\						
						    do {
						    	
						    	JOptionPane.showOptionDialog(null, panel, "The Title", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options , options[0]);//			    
						    	text = txt.getText();
						    	
						    	
						    }while(text.equals(""));
							
						 //----------------------------------------------------------------------------- \\				/
													
							XSSFRow rowA3 = sheetNewSr.getRow(2+faultPartCount);					
							XSSFCell cellA3 = rowA3.getCell(0); // 	komórka A3	### col A -0, row 3 -2
							cellA3.setCellValue(iCareSR);
							
							XSSFRow rowB3 = sheetNewSr.getRow(2+faultPartCount);					
							XSSFCell cellB3 = rowB3.getCell(1); // 	komórka B3	
							cellB3.setCellValue(nrN);
						
							
							XSSFRow rowC3 = sheetNewSr.getRow(2+faultPartCount);					
							XSSFCell cellC3 = rowC3.getCell(2); // 	komórka C3	
							cellC3.setCellValue(bomCode);
							
							XSSFRow rowD3 = sheetNewSr.getRow(2+faultPartCount);					
							XSSFCell cellD3 = rowD3.getCell(3); // 	komórka D3	
							cellD3.setCellValue(description);
							
							XSSFRow rowE3 = sheetNewSr.getRow(2+faultPartCount);					
							XSSFCell cellE3 = rowE3.getCell(4); // 	komórka E3	
							cellE3.setCellValue(partSN);
							
							XSSFRow rowF3 = sheetNewSr.getRow(2+faultPartCount);					
							XSSFCell cellF3 = rowF3.getCell(5); // 	komórka F3	
							cellF3.setCellValue(this.dateStartOrder);
							
							XSSFRow rowG3 = sheetNewSr.getRow(2+faultPartCount);					
							XSSFCell cellG3 = rowG3.getCell(6); // 	komórka G3	
							cellG3.setCellValue(currentDate);						
							
							
							XSSFRow rowH3 = sheetNewSr.getRow(2+faultPartCount);					
							XSSFCell cellH3 = rowH3.getCell(7); // 	komórka H3	
							cellH3.setCellValue("Yes");
							XSSFRow rowI3 = sheetNewSr.getRow(2+faultPartCount);					
							XSSFCell cellI3 = rowH3.getCell(8); // 	komórka I3	
							cellI3.setCellValue("Yes");
							XSSFRow rowJ3 = sheetNewSr.getRow(2+faultPartCount);					
							XSSFCell cellJ3 = rowJ3.getCell(9); // 	komórka J3	
							cellJ3.setCellValue("Yes");
							
							XSSFRow rowK3 = sheetNewSr.getRow(2+faultPartCount);					
							XSSFCell cellK3 = rowK3.getCell(10); // 	komórka K3	
							cellK3.setCellValue("LODZ");
							
							
							XSSFRow rowL3 = sheetNewSr.getRow(2+faultPartCount);					
							XSSFCell cellL3 = rowL3.getCell(11); // 	komórka L3	
							cellL3.setCellValue(this.returnedPerson);							
							XSSFRow rowM3 = sheetNewSr.getRow(2+faultPartCount);					
							XSSFCell cellM3 = rowM3.getCell(12); // 	komórka M3	
							cellM3.setCellValue(this.phonePerson);							
							XSSFRow rowN3 = sheetNewSr.getRow(2+faultPartCount);					
							XSSFCell cellN3 = rowN3.getCell(13); // 	komórka N3	
							cellN3.setCellValue(this.companyPerson);
							
							XSSFRow rowO3 = sheetNewSr.getRow(2+faultPartCount);					
							XSSFCell cellO3 = rowO3.getCell(14); // 	komórka L3	
							cellO3.setCellValue(text);	
	
							
							// sprawdza czy plik jest otwarty jesli tak nie pozwala zrobic inserta do bazy
							try
							{
								
						        FileOutputStream fileOut = new FileOutputStream("C:\\HUA_MAG\\TemplateZwrotCzesci.xlsm");					        
						        fileOut.close();
						        
						      //-------- insert do bazy danych -------------------------
								String queryInsertReturnedPartFault = " INSERT INTO returned_orders (id_orders, bom_code, name_part, desciption_ordered, sn_part_ordered, state_part, bom_code_returned, desciption_returned, sn_part_returned) VALUES ('"+ idOrders +"', '"+ bomCode +"', '"+ namePart +"', '"+ description +"', '"+ partSN +"', 'faulty', '"+ bomCode +"', '"+ description +"', '"+ partSN +"' ) ";
								// query for MYSQL
								//String queryUpdateOrders = "UPDATE orders SET sr_code = "+iCareSR+", status_order = 'close', date_stop = now() WHERE id_orders = "+idOrders+"";
								String queryUpdateOrders = "UPDATE orders SET sr_code = "+iCareSR+", status_order = 'close', date_stop = CURRENT_TIMESTAMP WHERE id_orders = "+idOrders+" ";
								InsertToReturned_orders(queryInsertReturnedPartFault, queryUpdateOrders);
								
								updateCount ++; // licznik do warunku w funkcji  InsertToReturned_orders() je¿eli wiekszy od 0 to nie robi update poniewa¿ juz raz wykonalo i nie trzeba wiecej
								// ---------------------------------------------------- \\
						        
							}catch (IOException e)
								{
								
									// TODO Auto-generated catch block
									e.printStackTrace();
									//JOptionPane.showMessageDialog(MainFrame.frame, "Otwarty plik TemplateZwrotCzesci.xlsm. Zamknij plik");
								
								
								
								}
							
							
							
							faultPartCount++;
							
						}else 
							{
							
	//						String bomCode = (String) MainFrame.tableSpPartStatus.getValueAt(r, 0);
	//						String namePart = (String) MainFrame.tableSpPartStatus.getValueAt(r, 1);
	//						String description = (String) MainFrame.tableSpPartStatus.getValueAt(r, 2);
	//						String partSN = (String) MainFrame.tableSpPartStatus.getValueAt(r, 3);
							
							XSSFRow rowA14 = sheetNewSr.getRow(13+goodPartCount);					
							XSSFCell cellA14 = rowA14.getCell(0); // 	komórka A14	
							cellA14.setCellValue(iCareSR);
							
							XSSFRow rowB14 = sheetNewSr.getRow(13+goodPartCount);					
							XSSFCell cell14 = rowB14.getCell(1); // 	komórka B14	
							cell14.setCellValue(nrN);
							
							XSSFRow rowC14 = sheetNewSr.getRow(13+goodPartCount);					
							XSSFCell cellC14 = rowC14.getCell(2); // 	komórka C14	
							cellC14.setCellValue(bomCode);
							
							XSSFRow rowD14 = sheetNewSr.getRow(13+goodPartCount);					
							XSSFCell cellD14 = rowD14.getCell(3); // 	komórka D14	
							cellD14.setCellValue(description);
							
							XSSFRow rowE14 = sheetNewSr.getRow(13+goodPartCount);					
							XSSFCell cellE14 = rowE14.getCell(4); // 	komórka E14	
							cellE14.setCellValue(partSN);
							
							XSSFRow rowF14 = sheetNewSr.getRow(13+goodPartCount);					
							XSSFCell cellF14 = rowF14.getCell(5); // 	komórka F14
							cellF14.setCellValue(this.dateStartOrder);
							
							XSSFRow rowG14 = sheetNewSr.getRow(13+goodPartCount);					
							XSSFCell cellG14 = rowG14.getCell(6); // 	komórka G14	
							cellG14.setCellValue(currentDate);
							
							XSSFRow rowH14 = sheetNewSr.getRow(13+goodPartCount);					
							XSSFCell cellH14 = rowH14.getCell(7); // 	komórka H14	
							cellH14.setCellValue("Yes");
							XSSFRow rowI14 = sheetNewSr.getRow(13+goodPartCount);					
							XSSFCell cellI14 = rowI14.getCell(8); // 	komórka I14	
							cellI14.setCellValue("Yes");
							XSSFRow rowJ14 = sheetNewSr.getRow(13+goodPartCount);					
							XSSFCell cellJ14 = rowJ14.getCell(9); // 	komórka J14	
							cellJ14.setCellValue("Yes");
							
							XSSFRow rowK14 = sheetNewSr.getRow(13+goodPartCount);					
							XSSFCell cellK14 = rowK14.getCell(10); // 	komórka K14
							cellK14.setCellValue("LODZ");
							
							
							
							XSSFRow rowL14 = sheetNewSr.getRow(13+goodPartCount);					
							XSSFCell cellL14 = rowL14.getCell(11); // 	komórka L14	
							cellL14.setCellValue(this.returnedPerson);							
							
							XSSFRow rowM14 = sheetNewSr.getRow(13+goodPartCount);					
							XSSFCell cellM14 = rowM14.getCell(12); // 	komórka M14	
							cellM14.setCellValue(this.phonePerson);
							
							XSSFRow rowN14 = sheetNewSr.getRow(13+goodPartCount);					
							XSSFCell cellN14 = rowN14.getCell(13); // 	komórka N14	
							cellN14.setCellValue(this.companyPerson);
							
							
							// sprawdza czy plik jest otwarty jesli tak nie pozwala zrobic inserta do bazy
							try
							{
						        FileOutputStream fileOut = new FileOutputStream("C:\\HUA_MAG\\TemplateZwrotCzesci.xlsm");					        
						        fileOut.close();
						        
						      //-------- insert do bazy danych -------------------------
								String queryInsertReturnedPartFault2 = " INSERT INTO returned_orders (id_orders, bom_code, name_part, desciption_ordered, sn_part_ordered, state_part, bom_code_returned, desciption_returned, sn_part_returned) VALUES ('"+ idOrders +"', '"+ bomCode +"', '"+ namePart +"', '"+ description +"', '"+ partSN +"', 'good', '"+ bomCode +"', '"+ description +"', '"+ partSN +"' ) ";
								// for mysql
								//String queryUpdateOrders = "UPDATE orders SET sr_code = "+iCareSR+", status_order = 'close', date_stop = now() WHERE id_orders = "+idOrders+"";
								String queryUpdateOrders = "UPDATE orders SET sr_code = "+iCareSR+", status_order = 'close', date_stop = CURRENT_TIMESTAMP WHERE id_orders = "+idOrders+"";
								InsertToReturned_orders(queryInsertReturnedPartFault2, queryUpdateOrders);
								
								updateCount ++;
								// ---------------------------------------------------- \\
						        
							}catch (IOException e)
								{
								
									// TODO Auto-generated catch block
									e.printStackTrace();
									//JOptionPane.showMessageDialog(MainFrame.frame, "Otwarty plik TemplateZwrotCzesci.xlsm. Zamknij plik");
															
								}
							
							
							
							goodPartCount++;
							}
						
						
					//}
					  	
					  	
					openTemplateSr = 1;		
					
					 
	//				} // ------ end else - sprawdzanie czy wypelniono informacje w tabeli odnoscnie zwracanych czesci
					
					
					
				}// -- end for
			goodPartCount = 0;
		  	faultPartCount = 0;	
		  	
		  	
		  	
			}// end else
				
		  	if(openTemplateSr == 1) {
				// zapis danych do pliku excel
				try
				{
			        FileOutputStream fileOut = new FileOutputStream("C:\\HUA_MAG\\TemplateZwrotCzesci.xlsm");
			        workbookNewSr.write(fileOut);
			        workbookNewSr.close();
			        fileOut.close();
				}catch (IOException e)
					{					
						// TODO Auto-generated catch block
						e.printStackTrace();
						JOptionPane.showMessageDialog(MainFrame2.frame, "Otwarty plik TemplateNewSR.xlsx. Zamknij ¿eby mo¿na by³o zapisaæ zmiany");
					
					}
				
				
				// clear all data in tableSpPartStatus
				DefaultTableModel def = (DefaultTableModel) MainFrame2.tableSpPartStatus.getModel();
				def.setRowCount(0);
				// refresh data in tableSpOrders
			    mysqlConnection(MainFrame2.tableSpOrders, MainFrame2.modelTabSpOrder);
			    	   
				makeInVisible();
			    
			////********************otwiera plik*****************************###***********
				File filehistory = new File("C:\\HUA_MAG\\TemplateZwrotCzesci.xlsm");
				Desktop dt = Desktop.getDesktop();
				try {
					dt.open(filehistory);
					
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}			
				workbookNewSr.close();
				fisNewSr.close();
				
		  	}
			
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
  	
  	
  	
  	public void makeInVisible() 
	{
		
//		lblNewLabel_3.setText(nrN.toString());
//	    label.setText(title.toString());
//        label_2.setText(ordered.toString());
//        label_1.setText(recipent.toString());
//        lblData.setText(data.toString());		
		MainFrame2.panel.setVisible(false);			
		MainFrame2.lblInformacjeZamwienia.setVisible(false);
		MainFrame2.lblNrN.setVisible(false);
		MainFrame2.lblTytu.setVisible(false);
		MainFrame2.lblWystawiajcy.setVisible(false);
		MainFrame2.lblOdbierajcy.setVisible(false);
		MainFrame2.label_1.setVisible(false);
		MainFrame2.label_2.setVisible(false);
		MainFrame2.lblNewLabel_2.setVisible(false);
		MainFrame2.lblNewLabel_1.setVisible(false);
		MainFrame2.lblNewLabel_3.setVisible(false);
		MainFrame2.label.setVisible(false);
		MainFrame2.lblDataWystawienia.setVisible(false);
		MainFrame2.lblData.setVisible(false);	
		MainFrame2.textField_1.setVisible(false);
		MainFrame2.lblNewLabel_4.setVisible(false);		
		MainFrame2.comboBox.setVisible(false);			
		MainFrame2.btnNewButton.setVisible(false);
		System.out.println("INVISIBLE 2222 @@@@!!!!");
		
	}
  	
  	
  	public void InsertToReturned_orders(String queryInsert, String queryUpdate)
  	{
  		Connection conn;
		try {
			//conn dla MYSQL
			//conn = DriverManager.getConnection(myUrl, "root", "password");
			
			String url = "jdbc:sqlite:C://HUA_MAG//DBhua_mag//hua_mag.db";	  			
  	        Connection conn2 = DriverManager.getConnection(url);
  			System.out.println("Connection to SQLite has been established.");
		    
		    // insert into returned_orders
		    
		    Statement stInsert = conn2.createStatement();
		    Statement stUpdate = conn2.createStatement();
		    //PreparedStatement stUpdate = (PreparedStatement) conn.prepareStatement(queryUpdate);
		    stInsert.execute(queryInsert);
		    if(updateCount == 0) {
		    	//stUpdate.setInt(1, 1313);
		    	//stUpdate.setInt(2, 10);
		    	stUpdate.executeUpdate(queryUpdate);
		    	
		    	stUpdate.close();
		    }
		    
		    //int updateCount = st.executeUpdate(queryUpdate);
		    //st.executeQuery(queryUpdate);
		    
		    stInsert.close();
		    
		    
		    conn2.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Got exeption in SecondPanel.InsertToReturned_orders()");
			System.err.println(e.getMessage());
		}	      
  	}

}


	
