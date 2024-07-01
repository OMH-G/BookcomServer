package com.controller.dao;

import com.controller.pojo.Order;
import com.controller.utils.DBConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao{

    // Create a new order record
    public void addOrder(Order order) {
        String sql = "INSERT INTO orders (buyerId, totalAmount, orderStatus, orderDate, deliveryDate, shippingAddress, paymentMethod, paymentStatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, order.getBuyerId());
            stmt.setBigDecimal(2, order.getTotalAmount());
            stmt.setString(3, order.getOrderStatus());
            stmt.setTimestamp(4, order.getOrderDate());
            stmt.setTimestamp(5, order.getDeliveryDate());
            stmt.setString(6, order.getShippingAddress());
            stmt.setString(7, order.getPaymentMethod());
            stmt.setString(8, order.getPaymentStatus());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve an order by its ID
    public Order getOrderById(int orderId) {
        String sql = "SELECT * FROM orders WHERE orderId = ?";
        Order order = null;

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, orderId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    order = new Order();
                    order.setOrderId(rs.getInt("orderId"));
                    order.setBuyerId(rs.getInt("buyerId"));
                    order.setTotalAmount(rs.getBigDecimal("totalAmount"));
                    order.setOrderStatus(rs.getString("orderStatus"));
                    order.setOrderDate(rs.getTimestamp("orderDate"));
                    order.setDeliveryDate(rs.getTimestamp("deliveryDate"));
                    order.setShippingAddress(rs.getString("shippingAddress"));
                    order.setPaymentMethod(rs.getString("paymentMethod"));
                    order.setPaymentStatus(rs.getString("paymentStatus"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    // Retrieve all orders
    public List<Order> getAllOrders() {
        String sql = "SELECT * FROM orders";
        List<Order> orders = new ArrayList<>();

        try (Connection conn = DBConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("orderId"));
                order.setBuyerId(rs.getInt("buyerId"));
                order.setTotalAmount(rs.getBigDecimal("totalAmount"));
                order.setOrderStatus(rs.getString("orderStatus"));
                order.setOrderDate(rs.getTimestamp("orderDate"));
                order.setDeliveryDate(rs.getTimestamp("deliveryDate"));
                order.setShippingAddress(rs.getString("shippingAddress"));
                order.setPaymentMethod(rs.getString("paymentMethod"));
                order.setPaymentStatus(rs.getString("paymentStatus"));

                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    // Update an order record
    public void updateOrder(Order order) {
        String sql = "UPDATE orders SET buyerId = ?, totalAmount = ?, orderStatus = ?, orderDate = ?, deliveryDate = ?, shippingAddress = ?, paymentMethod = ?, paymentStatus = ? WHERE orderId = ?";

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, order.getBuyerId());
            stmt.setBigDecimal(2, order.getTotalAmount());
            stmt.setString(3, order.getOrderStatus());
            stmt.setTimestamp(4, order.getOrderDate());
            stmt.setTimestamp(5, order.getDeliveryDate());
            stmt.setString(6, order.getShippingAddress());
            stmt.setString(7, order.getPaymentMethod());
            stmt.setString(8, order.getPaymentStatus());
            stmt.setInt(9, order.getOrderId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete an order record
    public void deleteOrder(int orderId) {
        String sql = "DELETE FROM orders WHERE orderId = ?";

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, orderId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
