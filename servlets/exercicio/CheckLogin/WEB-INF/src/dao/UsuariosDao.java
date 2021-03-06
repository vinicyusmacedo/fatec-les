package dao;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import models.Usuario;
import interfaces.UsuariosDaoInterface;
import utils.DBConnection;

public class UsuariosDao implements UsuariosDaoInterface {
    private Connection conn;

    public UsuariosDao () {
        conn = DBConnection.getConnection();
    }

    @Override
    public Usuario search (String username, String password) {
        Usuario foundUser = null;

        String sql = "SELECT * FROM USUARIOS WHERE USUARIO = ? AND PASSWORD = ? LIMIT 1";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                foundUser = new Usuario();
                foundUser.setUsuario(
                    rs.getString("USUARIO")
                );
                foundUser.setNome(
                    rs.getString("NOME")
                );
                foundUser.setEmail(
                    rs.getString("EMAIL")
                );
                foundUser.setTelefone(
                    rs.getString("TELEFONE")
                );
                foundUser.setPerfil(
                    rs.getString("PERFIL")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundUser;
    }
}