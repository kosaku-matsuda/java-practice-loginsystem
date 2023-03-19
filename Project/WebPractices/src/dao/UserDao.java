package dao;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import model.User;

/**
 * ユーザテーブル用のDao
 * @author takano
 *
 */
public class UserDao {


	public String hashPass(String password) {
		try {

			//ハッシュを生成したい元の文字列
			String source = password;
			//ハッシュ生成前にバイト配列に置き換える際のCharset
			Charset charset = StandardCharsets.UTF_8;
			//ハッシュアルゴリズム
			String algorithm = "MD5";

			//ハッシュ生成処理
			byte[] bytes = MessageDigest.getInstance(algorithm).digest(source.getBytes(charset));
			String result = DatatypeConverter.printHexBinary(bytes);
//			//標準出力
			System.out.println(result);

			return result;

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * ログインIDとパスワードに紐づくユーザ情報を返す
	 * @param loginId
	 * @param password
	 * @return
	 */
	public User findByLoginInfo(String loginId, String password) {
		Connection conn = null;
		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// SELECT文を準備
			String sql = "SELECT * FROM user WHERE login_id = ? and password = ?";

			// SELECTを実行し、結果表を取得
			PreparedStatement pStmt = conn.prepareStatement(sql);
			String hash = hashPass(password);
			pStmt.setString(1, loginId);
			pStmt.setString(2, hash);
			ResultSet rs = pStmt.executeQuery();


			// 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
			if (!rs.next()) {
				return null;
			}

			// 必要なデータのみインスタンスのフィールドに追加
			String loginIdData = rs.getString("login_id");
			String nameData = rs.getString("name");
			return new User(loginIdData, nameData);
//			ビーンズのコンストラクタに値を渡す
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
	}



	/**
	 * idからユーザ情報を取得
	 * @param id
	 * @return
	 */
	public User findById(String id) {
		Connection conn = null;
		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// SELECT文を準備
			String sql = "SELECT * FROM user WHERE id = ?";

			// SELECTを実行し、結果表を取得
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, id);
			ResultSet rs = pStmt.executeQuery();


			// 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
			if (!rs.next()) {
				return null;
			}

			// 必要なデータのみインスタンスのフィールドに追加
			int idData = rs.getInt("id");
			String loginIdData = rs.getString("login_id");
			String nameData = rs.getString("name");
			Date birthDate = rs.getDate("birth_date");
			String createDate = rs.getString("create_date");
			String updateDate = rs.getString("update_date");


			return new User(idData,loginIdData, nameData, birthDate, createDate, updateDate);

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
	}
	/**
	 * 全てのユーザ情報を取得する
	 * @return
	 */
	public List<User> findAll() {
		Connection conn = null;
		List<User> userList = new ArrayList<User>();

		try {
			// データベースへ接続
			conn = DBManager.getConnection();

			// SELECT文を準備
			// TODO: 未実装：管理者以外を取得するようSQLを変更する
			String sql = "SELECT * FROM user WHERE login_id !='admin'";

			// SELECTを実行し、結果表を取得
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			// 結果表に格納されたレコードの内容を
			// Userインスタンスに設定し、ArrayListインスタンスに追加
			while (rs.next()) {

				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");
				String name = rs.getString("name");
				Date birthDate = rs.getDate("birth_date");
				String password = rs.getString("password");
				String createDate = rs.getString("create_date");
				String updateDate = rs.getString("update_date");
				User user = new User(id, loginId, name, birthDate, password, createDate, updateDate);

				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return userList;
	}

//	ユーザ一覧を検索
	public List<User> UserSearch(String loginId, String name, String birthStart, String birthEnd) {
		Connection conn = null;
		List<User> userList = new ArrayList<User>();
		try {
			// データベースへ接続
			conn = DBManager.getConnection();
			// SELECT文を準備
			String sql = "SELECT * FROM user WHERE login_id !='admin'";

			if(!loginId.equals("")) {
				sql += " AND login_id = '" + loginId + "'";
			}

			if(!name.equals("")) {
				sql += "AND name LIKE '%" + name + "%'";
			}

			if(!birthStart.equals("")) {
				sql += " AND birth_date >= '" + birthStart + "'";
			}

			if(!birthEnd.equals("")) {
				sql += " AND birth_date <= '" + birthEnd + "'";
			}

			// SELECTを実行し、結果表を取得
			// SELECTを実行し、結果表を取得
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);


			while (rs.next()) {
				String loginIdData = rs.getString("login_id");
				String nameData = rs.getString("name");
				Date birthData = rs.getDate("birth_date");
				User user = new User(loginIdData, nameData, birthData);
				userList.add(user);
			}



		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return userList;
	}
	/**
	 * ユーザ情報を新規登録する
	 * @return
	 */
	public void signUp(String login_id, String name, String birth_date, String password) {
		Connection conn = null;
		try {
			conn = DBManager.getConnection();
			String sql = "INSERT INTO user(login_id,name,birth_date,password,create_date,update_date) VALUES(?,?,?,?,now(),now())";
			//    		(login_id, name, birth_date, password, create_date, update_date)
			PreparedStatement pStmt = conn.prepareStatement(sql);

//			Calendar cl = Calendar.getInstance();
//			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
//			String date = sdf.format(cl.getTime());
			String hash = hashPass(password);
			pStmt.setString(1, login_id);
			pStmt.setString(2, name);
			pStmt.setString(3, birth_date);
			pStmt.setString(4, hash);
//			pStmt.setString(5, date);
//			pStmt.setString(6, date);
			pStmt.executeUpdate();

//			★戻り値がvoidなのはサインアップメソッドは情報を登録する、受け取るだけなので
//			returnで何も返さないから！




		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();

				}
			}
		}

	}
	//    アップデート
	public void update(String name, String birth_date, String password,String id) {
		Connection conn = null;
		try {
			conn = DBManager.getConnection();
			String sql = "UPDATE user SET name = ?, birth_date = ?, password = ?,update_date = NOW() WHERE id = ?";
			//    		(login_id, name, birth_date, password, create_date, update_date)
			PreparedStatement pStmt = conn.prepareStatement(sql);
			String hash = hashPass(password);
			pStmt.setString(1, name);
			pStmt.setString(2, birth_date);
			pStmt.setString(3, hash);
			pStmt.setString(4, id);
			pStmt.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();

				}
			}

		}
	}

//	更新もサインアップと一緒で情報を再登録するだけで戻り値を何も返さないため
//	voidでreturnはいらない。

	//	デリートメソッド
	public void delete(String id) {
		Connection conn = null;
		try {
			conn = DBManager.getConnection();
			String sql = "DELETE FROM user WHERE id = ?";
			//    		(login_id, name, birth_date, password, create_date, update_date)
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, id);
			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();

				}
			}

		}
	}

	public User findByLogId(String login_id) {
		Connection conn = null;
		try {
			conn = DBManager.getConnection();

			String sql = "SELECT * FROM user WHERE login_id = ?";

			// SELECTを実行し、結果表を取得
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, login_id);
			ResultSet rs = pStmt.executeQuery();

			if (!rs.next()) {
				return null;
			}
//			？イフ文の読み方
//			rs.の中？ゲットでuserビーンズから持ってきている
//			下の変数の動き？

			int idData = rs.getInt("id");
			String loginIdData = rs.getString("login_id");
			String nameData = rs.getString("name");
			Date birthDate = rs.getDate("birth_date");
			String createDate = rs.getString("create_date");
			String updateDate = rs.getString("update_date");


			return new User(idData,loginIdData, nameData, birthDate, createDate, updateDate);


		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
	}
	}
}

