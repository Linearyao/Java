import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Score {
	
	String dburl = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF8";
	private StringBuilder sb;//���StringBuilderƴ�����ݿ�select�����ַ���
	String insertstr;
	
	public Score() throws Exception {
		sb = new StringBuilder();
		// private JTextField
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException cne) {
			cne.printStackTrace();
		}
		String sql = "SELECT * FROM scoretable WHERE score ORDER BY score asc";// �ɼ����ս�������

		try (Connection conn = DriverManager.getConnection(dburl, "", "");
				// ����dburl��ʾJDBC URL��user��ʾ���ݿ��û�����password��ʾ����
				java.sql.Statement stmt = conn.createStatement();
				ResultSet rst = stmt.executeQuery(sql)) {

			while (rst.next()) {
				sb.append(rst.getString(1)).append("      ").append(rst.getDouble(2)).append("\n");
//				System.out.println(rst.getString(1) + "\t" + rst.getDouble(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert() {
		String insertsql = "INSERT INTO scoretable (name, score) values "
				+ "(\""+ HuaRongRoad.gamer +"\", \"" + HuaRongRoad.usetime + "\");";
		try(Connection conn1 = DriverManager.getConnection(dburl,"","");
				java.sql.Statement stmt1 = conn1.createStatement()){
			stmt1.execute(insertsql);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public String getResulut() {
		return sb.toString();
	}
}
