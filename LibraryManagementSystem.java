import java.sql.*;
import java.util.Scanner;

public class LibraryManagementSystem{
    static final String DB_URL="jdbc:mysql://localhost:3306/library_db";
    static final String USER="root";
    static final String PASS="sqlpassword";

    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        int choice;
        while (true) { 
            System.out.println("\n=====Library Management System=======");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book by ID");
            System.out.println("4. Delete Book");
            System.out.println("5. Exit");
            System.out.println("Enter the choice: ");

            choice=sc.nextInt();

            switch(choice)
            {
                case 1:
                {
                    addBook(sc);
                    break;
                }
                case 2:
                {
                    viewBook();
                    break;
                }
                case 3:
                {
                    searchBook(sc);
                    break;
                }
                case 4:
                {
                    deleteBook(sc);
                    break;
                }
                case 5:
                {
                    System.out.println("Thank for using the Service");
                    sc.close();
                    return;
                }
                default:
                    System.out.println("Invalid choice, try again!!");
            }
        }
    }

    public static void addBook(Scanner sc)
    {
        try(Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
            ){
                System.out.println("Enter Book Title:");
                String title=sc.nextLine();
                System.out.println("Enter author name:");
                String author=sc.nextLine();
                System.out.println("Enter year:");
                int year=sc.nextInt();
                String query="INSERT INTO books (title, author, year) VALUES (?, ?, ?)";
                PreparedStatement pstmt=conn.prepareStatement(query);
                pstmt.setString(1,title);
                pstmt.setString(2, author);
                pstmt.setInt(3, year);
                pstmt.executeUpdate();

                System.out.println("Successfully added BOOK!!!");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void viewBook()
    {
        try(Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stmt=conn.createStatement()){
                
                ResultSet rs=stmt.executeQuery("SELECT * FROM books");
                
                System.out.println("\n All Books:");
                while(rs.next())
                {
                    System.out.printf("ID: %d | Title: %s | Author: %s | Year: %d%n", 
                    rs.getInt("id"), rs.getString("title"),
                    rs.getString("author"), rs.getInt("year"));
                }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void searchBook(Scanner sc) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             ) {

            System.out.print("Enter book ID: ");
            int id = sc.nextInt();

            String query = "SELECT * FROM books WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.printf("✅ Found: ID: %d | Title: %s | Author: %s | Year: %d%n",
                        rs.getInt("id"), rs.getString("title"),
                        rs.getString("author"), rs.getInt("year"));
            } else {
                System.out.println("❌ No book found with that ID.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteBook(Scanner sc) 
    {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             ) {

            System.out.print("Enter book ID to delete: ");
            int id = sc.nextInt();

            String query = "DELETE FROM books WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Book deleted successfully!");
            } else {
                System.out.println("❌ No book found with that ID.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}