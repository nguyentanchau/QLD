/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import INTER.ILopHocDAO;
import JDBC.DBConnect;
import MODEL.LopHoc;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anonymous
 */
public class LopHocDAO implements ILopHocDAO {

    @Override
    public ArrayList<LopHoc> getAll() {
        ArrayList<LopHoc> list = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.cnn.prepareStatement("select * from Lop");
                rs = ps.executeQuery();
                list = new ArrayList<LopHoc>();
                while (rs.next()) {
                    LopHoc lh = new LopHoc();
                    lh.setMalop(rs.getString(1));
                    lh.setTenlop(rs.getString(2));
                    lh.setMakhoa(rs.getString(3));
                    list.add(lh);
                }
            } catch (SQLException ex) {
                Logger.getLogger(LopHocDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close(ps, rs);
            }
        }
        return list;
    }

    @Override
    public ArrayList<LopHoc> findByIDKhoa(String maKhoa) {
        ArrayList<LopHoc> list = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.cnn.prepareStatement("select * from Lop where MaKhoa = ?");
               ps.setString(1, maKhoa);
                rs = ps.executeQuery();
                list = new ArrayList<LopHoc>();
                while (rs.next()) {
                    LopHoc lh = new LopHoc();
                    lh.setMalop(rs.getString(1));
                    lh.setTenlop(rs.getString(2));
                    lh.setMakhoa(rs.getString(3));
                    list.add(lh);
                }

            } catch (SQLException ex) {
                Logger.getLogger(LopHocDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close(ps, rs);
            }
        }
        return list;
    }

    @Override
    public LopHoc addNew(LopHoc lh) {
        PreparedStatement ps = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.cnn.prepareStatement("insert into Lop(MaLop,TenLop,MaKhoa) values(?,?,?)");
                ps.setString(1, lh.getMalop());
                ps.setString(2, lh.getTenlop());
                ps.setString(3, lh.getMakhoa());
                int row = ps.executeUpdate();
                if (row < 1) {
                    lh = null;
                }
            } catch (SQLException ex) {
                Logger.getLogger(LopHocDAO.class.getName()).log(Level.SEVERE, null, ex);
                lh = null;
            } finally {
                DBConnect.close(ps);
            }
        }
        return lh;
    }

    @Override
    public LopHoc updateByID(LopHoc lh) {
        PreparedStatement ps = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.cnn.prepareStatement("update Lop set TenLop = ?,MaKhoa=? where MaLop = ?");
               
                ps.setString(1, lh.getTenlop());
                ps.setString(2, lh.getMakhoa());
                 ps.setString(3, lh.getMalop());
                int row = ps.executeUpdate();
                if (row < 1) {
                    lh = null;
                }
            } catch (SQLException ex) {
                Logger.getLogger(LopHocDAO.class.getName()).log(Level.SEVERE, null, ex);
                lh = null;
            } finally {
                DBConnect.close();
            }
        }
        return lh;
    }
    public void deleteLopHoc(String LopID)throws SQLException, ClassNotFoundException{
    PreparedStatement ps = null;
        if (DBConnect.open()) {
            ps = DBConnect.cnn.prepareStatement("delete from Lop where MaLop= ?");
            ps.setString(1, LopID);
            ps.executeUpdate();
            DBConnect.close();
        }
    }

    @Override
    public ArrayList<LopHoc> checkID(String malop) {
      ArrayList<LopHoc> list = null;
      PreparedStatement psCheck = null;
      ResultSet rs = null;
        if (DBConnect.open()) {
            try {
                psCheck = DBConnect.cnn.prepareStatement("select * from Lop where MaLop = ?");
                psCheck.setString(1, malop);
                rs = psCheck.executeQuery();
                list = new ArrayList<LopHoc>();
                while (rs.next()) {
                    LopHoc lh = new LopHoc();
                    lh.setMalop(rs.getString(1));
                    list.add(lh);
                }
            } catch (SQLException ex) {
                Logger.getLogger(LopHocDAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
            DBConnect.close(psCheck, rs);
            }
            
        }
        return list;
    }
    
}
