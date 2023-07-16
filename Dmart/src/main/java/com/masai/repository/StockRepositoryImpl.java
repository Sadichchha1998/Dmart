package com.masai.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.masai.entity.StockItem;
import com.masai.entity.StoreLocation;
import com.masai.service.StoreLocationServiceImpl;

public class StockRepositoryImpl implements StockRepository {
    private Connection connection;

    public StockRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public StockItem save(StockItem stockItem) {
        String query = "INSERT INTO stock_items (name, quantity, store_location_id) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, stockItem.getName());
            preparedStatement.setInt(2, stockItem.getQuantity());
            preparedStatement.setLong(3, stockItem.getStoreLocation().getId());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                stockItem.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("Failed to insert stock item, no ID obtained.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stockItem;
    }

    @Override
    public StockItem update(StockItem stockItem) {
        String query = "UPDATE stock_items SET name = ?, quantity = ?, store_location_id = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, stockItem.getName());
            preparedStatement.setInt(2, stockItem.getQuantity());
            preparedStatement.setLong(3, stockItem.getStoreLocation().getId());
            preparedStatement.setLong(4, stockItem.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stockItem;
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM stock_items WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public StockItem findById(Long id) {
        String query = "SELECT * FROM stock_items WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapStockItem(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<StockItem> findAll() {
        String query = "SELECT * FROM stock_items";
        List<StockItem> stockItems = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                stockItems.add(mapStockItem(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stockItems;
    }

    @Override
    public List<StockItem> findByStoreLocation(Long storeLocationId) {
        String query = "SELECT * FROM stock_items WHERE store_location_id = ?";
        List<StockItem> stockItems = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, storeLocationId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    stockItems.add(mapStockItem(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stockItems;
    }

    private StockItem mapStockItem(ResultSet resultSet) throws SQLException {
        StockItem stockItem = new StockItem();
        stockItem.setId(resultSet.getLong("id"));
        stockItem.setName(resultSet.getString("name"));
        stockItem.setQuantity(resultSet.getInt("quantity"));

        // Assuming you have a StoreLocationRepository instance available to fetch StoreLocation by ID
        StoreLocationServiceImpl storeLocationRepository = new StoreLocationServiceImpl(connection);
        StoreLocation storeLocation = storeLocationRepository.findById(resultSet.getLong("store_location_id"));
        stockItem.setStoreLocation(storeLocation);

        return stockItem;
    }
}
