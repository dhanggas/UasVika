/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.uasvika;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vika
 */
public class BayaranKontrol {
     String sql = "insert into tb_pembayaran(tot_harga,jum_bayar,jum_pajak, jum_diskon, jum_kembali) values (?,?,?,?,?)";

    public List<Bayaran> ambilseumaData() throws SQLException {
        List<Bayaran> daftarBayaran = new ArrayList<>();
        Connection koneksi = Koneksi.getConnection();
        Statement st = koneksi.createStatement();
        ResultSet hasil = st.executeQuery("select * from tb_pembayaran");

        while (hasil.next()) {
            Bayaran m = new Bayaran();
            m.setHarga(hasil.getInt(1));
            m.setBayar(hasil.getInt(2));
            m.setPajak(hasil.getInt(3));
            m.setDiskon(hasil.getInt(4));
            m.setKembali(hasil.getInt(5));
            daftarBayaran.add(m);
        }

        return daftarBayaran;
    }

    public void simpanData(Bayaran m) throws SQLException {
        Connection koneksi = Koneksi.getConnection();
        PreparedStatement ps = koneksi.prepareStatement(sql);

        ps.setInt(1, m.getHarga());
        ps.setInt(2, m.getBayar());
        ps.setInt(3, m.getPajak());
        ps.setInt(4, m.getDiskon());
        ps.setInt(5, m.getKembali());
        ps.executeUpdate();
        ps.close();
        koneksi.close();
    }
}
