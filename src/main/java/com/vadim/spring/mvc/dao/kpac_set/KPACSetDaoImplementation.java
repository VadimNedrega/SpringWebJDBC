package com.vadim.spring.mvc.dao.kpac_set;

import com.vadim.spring.mvc.dao.kpac.KPACDaoImplementation;
import com.vadim.spring.mvc.entity.KPACSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class KPACSetDaoImplementation implements KPACSetDao {

    @Autowired
    private DataSource dataSource;
    @Autowired
    KPACDaoImplementation kpacDaoImplementation;

    @Override
    public void save(KPACSet kpacSet) {
        String query = "insert into k_pac_example.k_pac_set (Title) values (?)";
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, kpacSet.getTitle());
            int out = ps.executeUpdate();
            if (out != 0) {
                System.out.println("KPACSet saved with id=" + kpacSet.getId());
            } else System.out.println("KPACSet save failed with id=" + kpacSet.getId());
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
        String query = "delete from k_pac_example.k_pac_set where id=?";
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
    public List<KPACSet> getAll() {
        String query = "select id, Title from k_pac_example.k_pac_set";
        return getKpacsSet(query);
    }

    @Override
    public KPACSet get(int id) {
        String query = "select Title from k_pac_example.k_pac_set where id = ?";
        KPACSet kpacSet = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                kpacSet = new KPACSet();
                kpacSet.setId(id);
                kpacSet.setTitle(rs.getString("Title"));
                System.out.println("KPACSet Found::" + kpacSet);
            } else {
                System.out.println("No KPACSet found with id=" + id);
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
        return kpacSet;
    }


    public List<KPACSet> sortBy(String column) {
        String query = String.format("select id, Title from k_pac_example.k_pac_set ORDER BY %s ASC", column);
        return getKpacsSet(query);
    }

    public List<KPACSet> filterBy(String column, String filter) {
        String query = String.format("select id, Title from k_pac_example.k_pac_set WHERE %s LIKE '%s'", column, filter);
        return getKpacsSet(query);
    }

    private List<KPACSet> getKpacsSet(String query) {
        List<KPACSet> kpacSetList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                KPACSet kpacSet = new KPACSet();
                kpacSet.setId(rs.getInt("id"));
                kpacSet.setTitle(rs.getString("Title"));
                kpacSetList.add(kpacSet);
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
        return kpacSetList;
    }
}
