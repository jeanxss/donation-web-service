package model.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.AnimalArticle;
import model.DisasterArticle;
import model.DonationImage;

public class DisasterDAO {
	// DisasterDao

	private JDBCUtil jdbcUtil = null;

	public DisasterDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
	}

	public int create(DisasterArticle disaster) throws SQLException {
		try {
			String insert1 = "INSERT INTO DONATION_ARTICLE VALUES (seq_donation_article_id.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?, ?, ?, ?)";
			Object[] param1 = new Object[] { disaster.getTitle(), disaster.getCategory(), disaster.getDeadline(),
					disaster.getBankName(), disaster.getAccHolder(), disaster.getAccNum(), disaster.getIdCheck(),
					disaster.getDueDate(), disaster.getUsePlan(), disaster.getOtherText(), disaster.getUpdateDate(),
					disaster.getReceiptCheck(), disaster.getTotalAmount(), disaster.getUserId() };
			System.out.println("DisasterDao" + disaster);
			jdbcUtil.setSqlAndParameters(insert1, param1);
			String key[] = { "article_id" };
			jdbcUtil.executeUpdate(key);
			ResultSet rs = jdbcUtil.getGeneratedKeys();
			int generatedKey = 0;
			if (rs.next()) {
				generatedKey = rs.getInt(1);
				String insert2 = "INSERT INTO DISASTER_ARTICLE VALUES (?, ?, ?, ?, ?, ?)";
				Object[] param2 = new Object[] {generatedKey, disaster.getType(), disaster.getName(), disaster.getArea(),
						disaster.getDamageAmount(), disaster.getSituation()};
				jdbcUtil.setSqlAndParameters(insert2, param2);
				jdbcUtil.executeUpdate();
			}
			return generatedKey;
		} catch (Exception e) {
			// TODO: handle exception
			jdbcUtil.rollback();
			e.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}
	
	public int getMaxOrder(int articleId) throws SQLException{
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT MAX(img_order) AS \"order\" ");
			sql.append("FROM donation_image ");
			sql.append("WHERE article_id = ?");
			jdbcUtil.setSqlAndParameters(sql.toString(), new Object[] { articleId });
			ResultSet rs = jdbcUtil.executeQuery();
			if(rs.next()) {
				int maxOrder = rs.getInt("order");
				System.out.println("maxOrder: "+ maxOrder);
				return maxOrder;
			}
		} catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {     
            jdbcUtil.commit();
            jdbcUtil.close();   // resource 반환
        }       
        return 0;
	}	
	
	public int create_image(DonationImage image) throws SQLException {
        try {
            //DONATION_IMAGE
            String sql3 = "INSERT INTO DONATION_IMAGE VALUES (?, ?, ?)";
            System.out.println(image.getArticleId());
            Object[] param3 = new Object[] {
            					image.getArticleId(),
                                image.getFileOrder(),
                                image.getFileName()
                                };
            jdbcUtil.setSqlAndParameters(sql3, param3); // JDBCUtil 에 insert문과 매개 변수 설정
            int result = jdbcUtil.executeUpdate(); // insert 문 실행
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {     
            jdbcUtil.commit();
            jdbcUtil.close();   // resource 반환
        }       
        return 0;
    }
	
	public DisasterArticle findDisasterArticleByArticleId(int article_id) throws SQLException{	
		try {
			StringBuilder sql1 = new StringBuilder();
			sql1.append("SELECT title, category, TO_CHAR(deadline, 'YYYY-MM-DD') \"deadline\", "
					+ "bank_name, acc_holder, acc_num, TO_CHAR(due_date, 'YYYY-MM-DD') \"due_date\", "
					+ "use_plan, other_text, create_date, update_date, receipt_check, user_id, total_amount, "
					+ "type, name, area, damage_amount, situation ");
			sql1.append("FROM disaster_article dis JOIN donation_article d ON dis.article_id = d.article_id ");
			sql1.append("WHERE dis.article_id=? ");
			jdbcUtil.setSqlAndParameters(sql1.toString(), new Object[] {article_id});
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				DisasterArticle article = new DisasterArticle(article_id,
										rs.getString("title"),
										rs.getString("category"),
										rs.getString("deadline"),
										rs.getString("bank_name"),
										rs.getString("acc_holder"), 
										rs.getString("acc_num"),
										rs.getString("due_date"),
										rs.getString("use_plan"),
										rs.getString("other_text"), 
										rs.getDate("create_date"),
										rs.getDate("update_date"), 
										rs.getString("receipt_check"),
										rs.getString("user_id"),
										rs.getInt("total_amount"),
										rs.getString("type"), 
										rs.getString("name"),
										rs.getString("area"),
										rs.getInt("damage_amount"),
										rs.getString("situation")
									);
				//이미지 추가
				StringBuilder sb = new StringBuilder();
				sb.append("SELECT img_order, img_link ");
				sb.append("FROM donation_image ");
				sb.append("WHERE article_id = ?");
				sb.append("ORDER BY img_order ");
				String sql2 = sb.toString();
				jdbcUtil.setSqlAndParameters(sql2, new Object[] {article_id});
				
				List<DonationImage> images = new ArrayList<DonationImage>();
				
				rs = jdbcUtil.executeQuery();	
				while(rs.next()) {
					DonationImage image = new DonationImage(
							rs.getInt("img_order"), 
							rs.getString("img_link")
							);
					images.add(image);
				}
				article.setImageList(images);
				return article;
			}
		}catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}	
		return null;
	}

	public int update(DisasterArticle article) throws SQLException {
		try {
			String update1 = "UPDATE DONATION_ARTICLE "
					+ "SET title=?, due_date=?, use_plan=?, other_text=?, update_date = SYSDATE, receipt_check=? "
					+ "WHERE article_id=? ";
			Object[] param1 = new Object[] {article.getTitle(), article.getDueDate(), article.getUsePlan(),
					article.getOtherText(), article.getReceiptCheck(), article.getArticleId()};
			jdbcUtil.setSqlAndParameters(update1, param1);
			jdbcUtil.executeUpdate();

			StringBuilder update2 = new StringBuilder();
			update2.append("UPDATE DISASTER_ARTICLE ");
			update2.append("SET type = ?, name = ?, area = ?, damage_amount = ?, situation = ? ");
			update2.append("WHERE article_id=? ");
			String updateSql = update2.toString();
			Object[] param2 = new Object[] { article.getType(), article.getName(), article.getArea(),
					article.getDamageAmount(), article.getSituation(), article.getArticleId() };
			jdbcUtil.setSqlAndParameters(updateSql, param2);
			jdbcUtil.executeUpdate();
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			jdbcUtil.rollback();
			e.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}
	
	public int remove(int articleId) throws SQLException {	
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("DELETE FROM DONATION_ARTICLE WHERE article_id=? ");
			String sql = sb.toString();
			jdbcUtil.setSqlAndParameters(sql, new Object[] { articleId });
            int result = jdbcUtil.executeUpdate(); // delete 문 실행
            System.out.println("DONATION_ARTICLE table " + result + "개 삭제 성공");
            return result;
		}catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close(); // resource 반환
        }
		return 0;
	}
	
	public int update_image(DonationImage image) throws SQLException{
		try {
			String update1 = "UPDATE DONATION_IMAGE "
							+ "SET img_order = ?, img_link = ? "
							+ "WHERE article_id=? ";
			Object[] param1 = new Object[] {image.getFileOrder(), image.getFileName(), image.getArticleId()};
			jdbcUtil.setSqlAndParameters(update1, param1);
			jdbcUtil.executeUpdate();
			
			return 1;
		}catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}
		return 0;
	}
}