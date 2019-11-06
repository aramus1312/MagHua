package pakunek1;

import pakunek1.MainFrame2;
//import pakunek1.FirstPanel.CheckBoxModelListener;
//import pakunek1.FirstPanel.CheckBoxModelListener2;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

//import tableDemo2.Testtable;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;

public class FirstPanel extends JPanel{
	
	
	
	JTable table1, table2Main, tableTmp;
	String query;
	DefaultTableModel defmodel, model1;
	
	
	
	Object[] columnNames = {"BOM code", "Name", "Description","Dodaj"};
	Object[] columnNames2 = {"BOM code", "Name", "Description","Usun"};
    Object[][] data = {};
    
//    ArrayList<String> listA  = new ArrayList<>();
//	ArrayList<String> listB  = new ArrayList<>();
    ArrayList<String> listA  = MainFrame2.listA;
   	ArrayList<String> listB  = MainFrame2.listB;
    
    private static final int BOOLEAN_COLUMN = 3;
    //public static int rowCountTab2 = 0;
	 
    String myUrl = "jdbc:mysql://127.0.0.1:3306/hua_mag?useSSL=false";
    
    
    
	
	//---------------------------JTable table, DefaultTableModel model ------------------------
	public  JTable setTableView1(JTable table, DefaultTableModel model){
		
				
		this.table1 = table;
		this.model1 = model;
		//table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//DefaultTableModel model = new DefaultTableModel(data, columnNames);
		table1 = new JTable(model){

            private static final long serialVersionUID = 1L;
            
            /*@Override
            public Class getColumnClass(int column) {
            return getValueAt(0, column).getClass();
            }*/
            TableColumn columnTable;
            
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                    	
                        return String.class;
                    case 1:
                    	
                        return String.class;
                    case 2:
                    	
                        return String.class;
                    case 3:
                    	//table1.getColumnModel().getColumn(3).setPreferredWidth(30);
                    	return Boolean.class;
                    default:
                        return Boolean.class;
                }
            }
           
          //Implement table cell tool tips.           
            public String getToolTipText(MouseEvent e) {
                String tip = null;
                java.awt.Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);
                int colIndex = columnAtPoint(p);
                
                if(colIndex == 2) 
                	{

		                try {
		                    tip = getValueAt(rowIndex, colIndex).toString();
		                } catch (RuntimeException e1) {
		                    //catch null pointer exception if mouse is over an empty line
		                }		
		                
                	}
                return tip;
            }
            
        };
     
		
		JTableHeader header1 = table1.getTableHeader(); // header ustawia kolor w nazwach kolumn
	    header1.setBackground(Color.blue);
	    header1.setForeground(Color.yellow);
		return table1;
		
	}
	
	
	//----------------------------------------------------------------------------------------------------------	
	//---------------------- wczytywanie i pobieranie danych z mysql ----------------
//	public void mysqlConnection2(JTable table, String query, DefaultTableModel model) 
//	{
//		
//		this.tableTmp = table;
//		this.query = query;
//		this.defmodel = model;
//		
//		try
//	    {
//	      // create our mysql database connection
//	     
//	      	     
//	      Connection conn = DriverManager.getConnection(myUrl, "root", "password");	      
//	      //String query = "SELECT * FROM parts";	      // create the java statement
//	      Statement st = conn.createStatement();
//	      ResultSet rs = st.executeQuery(query);	
//	      System.out.println(query);
//	      
//	      //DefaultTableModel DefTableMode = (DefaultTableModel)tableTmp.getModel();
//	      
//	      model.setRowCount(0);
//	      while (rs.next())
//	      {
//	    	
//	        String bom_code = rs.getString("bom_code");
//	        String name_part = rs.getString("name_part");
//	        String desciption = rs.getString("desciption");
//	       
//	        model.addRow(new Object[] {bom_code, name_part ,desciption, false});
//	      }
//	      st.close();
//	    }
//	    catch (Exception e)
//	    {
//	      System.err.println("Got an exception! ");
//	      System.err.println(e.getMessage());
//	    }
//		
//	}
	
	
// ----------- ------  SQLITE CONECTION - do oblslugi SQLITE !!! -------------------------------\\
	@SuppressWarnings("null")
	public void mysqlConnection2(JTable table, String query, DefaultTableModel model) 
	{
		
		this.tableTmp = table;
		this.query = query;
		this.defmodel = model;
		
		Connection conn = null;
		try
	    {     
	     
          String url = "jdbc:sqlite:C://HUA_MAG//DBhua_mag//hua_mag.db";	
		  //String url = "jdbc:sqlite:C:\\HUA_MAG\\DBhua_mag\\hua_mag.db";	
          conn = DriverManager.getConnection(url);
		  System.out.println("Connection to SQLite has been established.");
          
	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);	
	      System.out.println(query);
	      
	      //DefaultTableModel DefTableMode = (DefaultTableModel)tableTmp.getModel();
	      
	      model.setRowCount(0);
	      while (rs.next())
	      {
	    	
	        String bom_code = rs.getString("bom_code");
	        String name_part = rs.getString("name_part");
	        String desciption = rs.getString("desciption");
	       
	        model.addRow(new Object[] {bom_code, name_part ,desciption, false});
	      }
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! in FirstPanel.mysqlConnection2() ");
	      System.err.println(e.getMessage());
	    }
		
	}
	
	//----------------------------------------------------------------------------------------\\\
	
	
	
			
	//JTable table1Main = MainFrame.table_1;
	
	// --------------------  Listener do obslugi Table_1 --------------------------------------
	public class CheckBoxModelListener implements TableModelListener {
		
		
        public void tableChanged(TableModelEvent e) {
            int row = e.getFirstRow();
            int column = e.getColumn();
            if (column == BOOLEAN_COLUMN) {
                TableModel model = (TableModel) e.getSource();
                String columnName = model.getColumnName(column);
                Boolean checked = (Boolean) model.getValueAt(row, column);
                
                if (checked) {
                    
                    TableModel tabmodel = MainFrame2.table_1.getModel();
    				int indexs[] = MainFrame2.table_1.getSelectedRows();
    				
    				Object[] rowTab1 = new Object[10];
    				Object[] rowTab2 = new Object[2];
    				
    				table2Main = MainFrame2.table_2;   				
    				
    				DefaultTableModel tab2model = (DefaultTableModel)table2Main.getModel();
    				
    				//List listA = new ArrayList();
    				
    				
    				for(int i = 0; i <indexs.length; i++)
    				{
    					rowTab1[0] = tabmodel.getValueAt(indexs[i], 0);
    					String str = (String) rowTab1[0];
    					listA.add(str); // dodaje boom code do listy z tabeli 1
    					rowTab1[1] = tabmodel.getValueAt(indexs[i], 1);
    					rowTab1[2] = tabmodel.getValueAt(indexs[i], 2);
    					rowTab1[3] = tabmodel.getValueAt(indexs[i], 3);      					
    					
    					boolean ans = listB.contains(str);
    					
    					
    					if(ans) {
    						
    						//JOptionPane.showMessageDialog(frame, "Ju¿ doda³eœ t¹ czêœæ");
    						int result = JOptionPane.showConfirmDialog(MainFrame2.frame,"Czêœæ zosta³a ju¿ wczeœniej dodana. Dodaæ kolejn¹ szt. ?", "Info",
    					               JOptionPane.YES_NO_OPTION,
    					               JOptionPane.QUESTION_MESSAGE);
    					            if(result == JOptionPane.YES_OPTION){
    					            	tab2model.addRow(rowTab1);
    					            	
    					            	rowTab2[0] = tab2model.getValueAt(indexs[i], 0);
    		        					String strB = (String) rowTab2[0];
    		        					listB.add(strB); // dodaje boom code do listy z tabeli 2    		        					
    					            	
    					            	int indextab2 = table2Main.getRowCount();
    					            	
    					            	
    					            	tabmodel.setValueAt(false, indexs[i], 3);
    		        					tab2model.setValueAt(false, indextab2 - 1, 3);
    		        					
    		        					MainFrame2.rowCountTab2 = table2Main.getRowCount();
    		        					//rowCountTab2 = 1122;
    					            }else if (result == JOptionPane.NO_OPTION){
    					            	tabmodel.setValueAt(false, indexs[i], 3);
    					            	
    					            	
    					            	
    					            }else {
    					            	tabmodel.setValueAt(false, indexs[i], 3);
    					            	
    					            	
    					            }
    						
    					}else {
    						tab2model.addRow(rowTab1);
        					
    						int indextab2 = table2Main.getRowCount();
    						
    						rowTab2[0] = tab2model.getValueAt(indextab2 - 1, 0);
        					String strB = (String) rowTab2[0];
        					listB.add(strB); // dodaje boom code do listy z tabeli 2
        					
        					tabmodel.setValueAt(false, indexs[i], 3);
        					//tab2model.setValueAt(false, indexs[i], 3);
        					
        					tab2model.setValueAt(false, indextab2 - 1, 3);
        					
        					MainFrame2.rowCountTab2 = table2Main.getRowCount();
        					//rowCountTab2 = 1122;
    					}
    					
    					
    					
    				}
                } else {
                    System.out.println(columnName + ": " + false);
                }
                
                
            }
        }
    }
	
	
	public void celarTable1(JTable table)
	{
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
	}
	
	
	
	
// --------------------  Listener do obslugi Table_2 --------------------------------------
	public class CheckBoxModelListener2 implements TableModelListener {
		
		

        public void tableChanged(TableModelEvent e) {
            int row = e.getFirstRow();
            int column = e.getColumn();
            if (column == BOOLEAN_COLUMN) {
                TableModel model = (TableModel) e.getSource();
                String columnName = model.getColumnName(column);
                Boolean checked = (Boolean) model.getValueAt(row, column);
                
                
                
                if (checked) {	                	
                	
    				int ind = MainFrame2.table_2.getSelectedRow();	
    				
    				DefaultTableModel tab2model = (DefaultTableModel)MainFrame2.table_2.getModel();	    				
    				String bomcode = (String)tab2model.getValueAt(ind, 0);    				
    				listB.forEach(name ->{
    						
    			            System.out.print(name + ", ");
    			        });
    				
    				tab2model.removeRow(ind);
    				
    				listB.remove(bomcode);
    				
    				
    				MainFrame2.rowCountTab2 = MainFrame2.table_2.getRowCount();
    				//rowCountTab2 = 1122;
                }
                
            }
        }
    }

	
	
	
	
	
	
	//--------------------------------------------
//	public void paintComponent(Graphics g){
//        g.setColor(Color.BLACK);
//        g.setFont(new Font("Verdana",Font.BOLD,16));
//        //g.drawString("Hello there", 20, 20);
//    }//--------------------------------------
//	
	
	
	
	
	

}// end firstPanel class
