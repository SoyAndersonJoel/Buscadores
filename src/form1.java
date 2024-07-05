import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class form1 {
    public JPanel mainPanel;
    private JButton buscarCedula;
    private JLabel nombreTF;
    private JTextField ingresar;
    private JButton buscarNombre;
    private JLabel resNombre;
    private JLabel resCedula;
    private JLabel resB1;
    private JLabel resB2;
    private JLabel resPromedio;

    public form1() {

        buscarCedula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String url="jdbc:mysql://localhost:3306/Prueba";
                String user="root";
                String password="123456";


                try(Connection connection = DriverManager.getConnection(url,user,password)){
                    System.out.println("conectado");


                    String query="SELECT * FROM estudiantes WHERE cedula ='"+ingresar.getText()+"'";


                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);
                    while(resultSet.next()){
                        System.out.println(resultSet.getString("nombre"));
                        resNombre.setText(resultSet.getString("nombre"));
                        resCedula.setText(resultSet.getString("cedula"));
                        resB1.setText(resultSet.getString("b1"));
                        resB2.setText(resultSet.getString("b2"));
                        double b1 = Double.parseDouble(resultSet.getString("b1"));
                        double b2 = Double.parseDouble(resultSet.getString("b2"));
                        double promedio = Double.parseDouble(resultSet.getString("promedio"));
                        resPromedio.setText(String.valueOf(promedio));

                        ingresar.setText("");

                    }


                }catch(SQLException e1){
                    System.out.println(e1);
                }


            }
        });
        buscarNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url="jdbc:mysql://localhost:3306/Prueba";
                String user="root";
                String password="123456";
                try(Connection connection = DriverManager.getConnection(url,user,password)){
                    System.out.println("conectado");

                    String query="SELECT * FROM estudiantes WHERE nombre ='"+ingresar.getText()+"'";

                    Statement stat = connection.createStatement();
                    ResultSet resul = stat.executeQuery(query);
                    while(resul.next()){
                        System.out.println(resul.getString("nombre"));
                        resNombre.setText(resul.getString("nombre"));
                        resCedula.setText(resul.getString("cedula"));
                        resB1.setText(resul.getString("b1"));
                        resB2.setText(resul.getString("b2"));
                        double b1 = Double.parseDouble(resul.getString("b1"));
                        double b2 = Double.parseDouble(resul.getString("b2"));
                        double promedio =(b1 + b2)/2;
                        resPromedio.setText(String.valueOf(promedio));

                        ingresar.setText("");

                    }


                }catch(SQLException e1){
                    System.out.println(e1);
                }


            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("form1");
        frame.setContentPane(new form1().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

