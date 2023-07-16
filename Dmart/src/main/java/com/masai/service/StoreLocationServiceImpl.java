package com.masai.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.masai.entity.StoreLocation;

public class StoreLocationServiceImpl implements StoreLocationService {
    private Connection connection;

    public StoreLocationServiceImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public StoreLocation getStoreLocationById(Long id) {
        String query = "SELECT * FROM store_locations WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapStoreLocation(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<StoreLocation> getAllStoreLocations() {
        String query = "SELECT * FROM store_locations";
        List<StoreLocation> storeLocations = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                storeLocations.add(mapStoreLocation(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return storeLocations;
    }

    private StoreLocation mapStoreLocation(ResultSet resultSet) throws SQLException {
        StoreLocation storeLocation = new StoreLocation();
        storeLocation.setId(resultSet.getLong("id"));
        storeLocation.setName(resultSet.getString("name"));
        storeLocation.setAddress(resultSet.getString("address"));
        return storeLocation;
    }

	public StoreLocation findById(long long1) {
		// TODO Auto-generated method stub
		return null;
	}
}
