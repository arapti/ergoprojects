package com.eco.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {

	public static void main(String[] args) {
		Connection conn = null;

		try {

			String url = "jdbc:mysql://192.168.191.41:3306/df_prod";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, "phb_df_user", "p@n0$pdf");
			System.out.println("Database connection established");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();

		} finally {

			if (conn != null) {

				try {
					// 1st query--we retrieve the uri of the form
					Statement stmt = conn.createStatement();

					// ResultSet rs =
					// stmt.executeQuery("SELECT _URI FROM _form_info where FORM_ID='ecoelastika_form'");
					ResultSet rs = stmt
							.executeQuery("SELECT UNROOTED_FILE_PATH, _SUB_AURI FROM `_form_info_manifest_ref`"
									+ " a INNER JOIN `_form_info_manifest_bin` b ON a._DOM_AURI=b._URI "
									+ "where a._TOP_LEVEL_AURI='md5:9e91898a6c4d200c08bf5fc5d7224df6'");

					while (rs.next()) {
						String filename = rs.getString("UNROOTED_FILE_PATH");
						String uri = rs.getString("_SUB_AURI");

						System.out.println(filename + " " + uri + "\n");

						// we define the name of the file to be uploaded from
						// the select query above
						File update = new File("C:/Users/arapti/Documents/"
								+ filename);
						InputStream fin = new FileInputStream(update);
						String query = "UPDATE _form_info_manifest_blb SET VALUE=? WHERE _URI=?";
						PreparedStatement preparedStmt = conn
								.prepareStatement(query);
						preparedStmt.setString(2, uri);
						preparedStmt.setBinaryStream(1, fin, update.length());
						System.out.println(preparedStmt);
						// execute the java preparedstatement
						preparedStmt.executeUpdate();
						preparedStmt.close();
						fin.close();

					}
					// 2nd query
					/*
					 * String querySetLimit =
					 * "SET GLOBAL max_allowed_packet=104857600;"; // 10 MB
					 * Statement stSetLimit = conn.createStatement();
					 * stSetLimit.execute(querySetLimit);
					 */
					conn.close();

					System.out.println("Database connection terminated");

				} catch (SQLException e) { /* ignore close errors */
					System.out.println(e.toString());
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}

	}

}
