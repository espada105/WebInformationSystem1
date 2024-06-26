package sec02.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
    private DataSource dataFactory;
    
    public MemberDAO() {
        try {
            Context ctx = new InitialContext();
            Context envContext = (Context) ctx.lookup("java:/comp/env");
            dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<MemberVO> listMembers(MemberVO memberVO) {
        List<MemberVO> membersList = new ArrayList<>();
        String _name = memberVO.getName();
        String query = "SELECT * FROM t_member";
        if (_name != null && !_name.isEmpty()) {
            query += " WHERE name=?";
        }
        
        try (Connection con = dataFactory.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
             
            if (_name != null && !_name.isEmpty()) {
                pstmt.setString(1, _name);
            }
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String pwd = rs.getString("pwd");
                String name = rs.getString("name");
                String email = rs.getString("email");
                Date joinDate = rs.getDate("joinDate");
                MemberVO vo = new MemberVO(id, pwd, name, email, joinDate);
                membersList.add(vo);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return membersList;
    }
}
