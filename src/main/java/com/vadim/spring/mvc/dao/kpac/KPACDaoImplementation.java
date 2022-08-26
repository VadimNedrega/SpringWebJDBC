package com.vadim.spring.mvc.dao.kpac;

import com.vadim.spring.mvc.entity.KPAC;
import com.vadim.spring.mvc.entity.KPACSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class KPACDaoImplementation implements KPACDao {

    @Autowired
    private DataSource dataSource;

    @Override
    public void save(KPAC kpac) {
        String query = "insert into k_pac_example.k_pac (Title, Description, Creation_Date, K_PAC_SET_ID) values (?,?,?,?)";
        String query1 = "insert into k_pac_example.k_pac (Title, Description, Creation_Date) values (?,?,?)";
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(query1);
            ps.setString(1, kpac.getTitle());
            ps.setString(2, kpac.getDescription());
            ps.setString(3, String.valueOf(new Date(System.currentTimeMillis())));
            if (kpac.getKpacSet() != null) {
                ps = con.prepareStatement(query);
                ps.setString(1, kpac.getTitle());
                ps.setString(2, kpac.getDescription());
                ps.setString(3, String.valueOf(new Date(System.currentTimeMillis())));
                ps.setInt(4, kpac.getKpacSet());
            }
            int out = ps.executeUpdate();
            if (out != 0) {
                System.out.println("KPAC saved with id=" + kpac.getId());
            } else System.out.println("KPAC save failed with id=" + kpac.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert ps != null;
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int id) {
        String query = "delete from k_pac_example.k_pac where id=?";
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            int out = ps.executeUpdate();
            if (out != 0) {
                System.out.println("KPAC deleted with id=" + id);
            } else System.out.println("No KPAC found with id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert ps != null;
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<KPAC> getAll() {
        String query = "select id, Title, Description, Creation_Date, K_PAC_SET_ID, K_PAC_SET_ID from k_pac_example.k_pac";
        return getKpacs(query);
    }

    public List<KPAC> getAllBySetId(int setId) {
        String query = String.format("select id, Title, Description, Creation_Date, K_PAC_SET_ID from k_pac_example.k_pac" +
                " where K_PAC_SET_ID=%d", setId);
        return getKpacs(query);
    }

    @Override
    public KPAC get(int id) {
        String query = "select Title, Description, Creation_Date, K_PAC_SET_ID from k_pac_example.k_pac where id = ?";
        KPAC kpac = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                kpac = new KPAC();
                kpac.setId(id);
                kpac.setTitle(rs.getString("Title"));
                kpac.setDescription(rs.getString("Description"));
                kpac.setCreationDate(rs.getString("Creation_Date"));
                kpac.setKpacSet(rs.getInt("K_PAC_SET_ID"));
                System.out.println("KPAC Found::" + kpac);
            } else {
                System.out.println("No KPAC found with id=" + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert rs != null;
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return kpac;
    }

    @Override
    public void setSet(int kpacId, KPACSet kpacSet) {
        KPAC kpac = get(kpacId);
        kpac.setKpacSet(kpacSet.getId());
    }

    public List<KPAC> sortBy(String column) {
        String query = String.format("select id, Title, Description, Creation_Date, K_PAC_SET_ID from k_pac_example.k_pac ORDER BY %s ASC", column);
        return getKpacs(query);
    }

    public List<KPAC> filterBy(String column, String filter) {
        String query = String.format("select id, Title, Description, Creation_Date, K_PAC_SET_ID from k_pac_example.k_pac WHERE %s LIKE '%s'", column, filter);
        return getKpacs(query);
    }

    private List<KPAC> getKpacs(String query) {
        List<KPAC> kpacList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                KPAC kpac = new KPAC();
                kpac.setId(rs.getInt("id"));
                kpac.setTitle(rs.getString("Title"));
                kpac.setDescription(rs.getString("Description"));
                kpac.setCreationDate(rs.getString("Creation_Date"));
                kpac.setKpacSet(rs.getInt("K_PAC_SET_ID"));
                kpacList.add(kpac);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert rs != null;
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return kpacList;
    }

}
