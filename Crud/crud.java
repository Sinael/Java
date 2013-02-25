import java.sql.*;
import java.util.Scanner;



public class crud{

	
  public static void main(String[] args) {
	  
	  
	  wypisywarka main = new wypisywarka();
	  Connection con = null;
	  String url = "jdbc:postgresql://localhost/crud";
	  Scanner myScanner = new Scanner (System.in);
		String funkcja = myScanner.nextLine();
		Scanner whileselect = new Scanner (System.in);
		int l3;
 
  try{
  con = DriverManager.getConnection(url, "name", "pass");
  
  switch (funkcja.toLowerCase())
  {
  case "delete":
	  try
	  {
		  Scanner Slowa = new Scanner (System.in);
		  Statement st = con.createStatement();
		  String sql = "DELETE FROM"+" "+(Slowa.nextLine());	
		  int delete = st.executeUpdate(sql);
		  if(delete !=0)
		  {
			  main.main("Row is deleted");
			  
		  }
		  else
		  {
			  main.main("Row is not deleted.");
		  }
	  }
	  catch (SQLException s)
	  {
		  main.main("SQL statement is not executed!");
	  }
	  break;
  case "insert":
  { 
	  main.main("Menu INSERT 1-kontynuu�acja: 0 - powr�t");
	  l3 = whileselect.nextInt();
	  while (l3 == 1)
	  {
	  try {
		  Scanner id_pracownik = new Scanner (System.in);
		  Scanner imie = new Scanner (System.in);
		  Scanner nazwisko = new Scanner (System.in);
		  Statement st = con.createStatement();
		  String sql="INSERT INTO pracownik VALUES('"+(id_pracownik.nextLine())+"','"+(imie.nextLine())+"','"+(nazwisko.nextLine())+"')";
		  int delete = st.executeUpdate(sql);
		  if(delete !=0)
		  {
			  main.main("1");
			  
		  }
		  else
		  {
			main.main("0");
		  }
	  		}	
		  catch (Exception s)
	  		{
	  			main.main("Z�e dane");	  
	  		}
	  		l3 = whileselect.nextInt();
		  if (l3==0)
		  {
			  crud.main(args);
		  }
	  	}
  }
  break;
  case "update":
  	{ 
  		main.main("Menu UPDATE 1-kontynuu�acja: 0 - powr�t");
		  l3 = whileselect.nextInt();
		  while (l3 == 1)
		  {
  		 try {
  			Scanner idscanner = new Scanner (System.in);
  			main.main("podaj id");
  			String id = idscanner.nextLine();
  			Scanner imiescanner = new Scanner (System.in);
  			main.main("podaj imie");
  			String imie = imiescanner.nextLine();
  			Scanner nazwiskoscanner = new Scanner (System.in);
  			main.main("podaj nazwisko");
  			String nazwisko = nazwiskoscanner.nextLine();
  			Statement st = con.createStatement();	
  			String sql="UPDATE pracownik SET imie='"+imie+"', nazwisko='"+nazwisko+"' WHERE id_pracownik ='"+id+"'";
  				//String sql="UPDATE pracownik SET imie ='debil', nazwisko = 'partacz' WHERE id_pracownik='12345'";
  			  int update = st.executeUpdate(sql);
  			  if(update !=0)
  			  {
  				  main.main("1");
  				  
  			  }
  			  else
  			  {
  				  main.main("0");
  			  }
  		  }
  			  
  		
  		  catch (Exception s)
  		  {
  			  main.main("B��d zapytania sql");
  		  }
  		  l3 = whileselect.nextInt();
  		  if (l3==0)
  		  {
  			  crud.main(args);
  		  }
   		  }
		  }
  	break;
  	
  case "select":	
	  try {
		  main.main("Menu SELECT 1-kontynuu�acja: 0 - powr�t");
		  Scanner switch2scanner = new Scanner (System.in);
		  l3 = whileselect.nextInt();
		  while (l3 == 1)
		  {
			main.main("Podaj nazwe kolumny");
			String kolumna = switch2scanner.nextLine();
			switch (kolumna.toLowerCase())
		  {
		  	case "imie":
		  	{
		  		String sql="SELECT imie FROM pracownik";
		  		Statement stmt = con.createStatement();
		  		ResultSet rset = stmt
		  				.executeQuery(sql);
		  		while (rset.next())
		  			System.out.println(rset.getString(1));
		  		break;
		  	
		  }
		  case "nazwisko":
		  {
				String sql="SELECT nazwisko FROM pracownik";
				Statement stmt = con.createStatement();
				ResultSet rset = stmt
						.executeQuery(sql);
				while (rset.next())
					System.out.println(rset.getString(1));
				break;
		  }
		  case "id":
		  {
			  String sql="SELECT id_pracownik FROM pracownik";
			  Statement stmt = con.createStatement();
			    ResultSet rset = stmt
			        .executeQuery(sql);
			    while (rset.next())
			      System.out.println(rset.getString(1));
			    break;
		  }

		 }
			l3 = whileselect.nextInt();
			if(l3 ==0)
			{
				crud.main(args);
			}
		  }
	  }
		  
			  
	
	  catch (Exception s)
	  {
	  }
	  break;
	 
  }
  }
  catch (SQLException s)
  {
	  main.main("B��d po��czenia z baz�");
  }
  }
}

	  
 
 
