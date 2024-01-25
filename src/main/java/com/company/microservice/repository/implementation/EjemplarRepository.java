// EjemplarRepository.java
package com.company.microservice.repository.implementation;

import com.company.microservice.data.DatabaseConfig;
import com.company.microservice.model.Autor;
import com.company.microservice.model.Ejemplar;
import com.company.microservice.repository.interfaces.IEjemplarRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EjemplarRepository implements IEjemplarRepository {
    private final Connection connection;

    public EjemplarRepository(DatabaseConfig databaseConfig) throws SQLException {
        this.connection = databaseConfig.getConnection();
    }

    @Override
    public List<Ejemplar> getAllEjemplares() throws SQLException {
        List<Ejemplar> ejemplares = new ArrayList<>();
        try (CallableStatement callStmt = connection.prepareCall("{call sp_GetAllEjemplares()}")) {
            ResultSet resultSet = callStmt.executeQuery();
            while (resultSet.next()) {
                Ejemplar ejemplar = new Ejemplar();
                ejemplar.setEjemId(resultSet.getInt("EJEM_ID"));
                ejemplar.setEjemId(resultSet.getInt("LIB_ID"));
                ejemplar.setEjemId(resultSet.getInt("EJEM_ESTADO"));
                ejemplares.add(ejemplar);
            }
        }
        return ejemplares;    }

    /*@Override
    public Ejemplar getEjemplarById(int id) throws SQLException {
        // Implementación de getEjemplarById
    }

    @Override
    public void insertEjemplar(Ejemplar ejemplar) throws SQLException {
        // Implementación de insertEjemplar
    }

    @Override
    public void updateEjemplar(Ejemplar ejemplar) throws SQLException {
        // Implementación de updateEjemplar
    }

    @Override
    public void deleteEjemplar(int id) throws SQLException {
        // Implementación de deleteEjemplar
    }*/

    // Otros métodos específicos para Ejemplar, si es necesario
}