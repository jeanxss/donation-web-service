package model.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;
import model.AnimalArticle;
import model.DonationImage;

public class AnimalDAO {
	private JDBCUtil jdbcUtil = null;
	
	public AnimalDAO() {jdbcUtil = new JDBCUtil();}

	public int create(AnimalArticle animal) throws SQLException {			
		try {		
			String insert1 = "INSERT INTO DONATION_ARTICLE VALUES (seq_donation_article_id.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?, ?, ?, ?)";
			Object[] param1 = new Object[] {animal.getTitle(), animal.getCategory(), animal.getDeadline(), animal.getBankName(),
					animal.getAccHolder(), animal.getAccNum(), animal.getIdCheck(), animal.getDueDate(), animal.getUsePlan(), animal.getOtherText(),
					animal.getUpdateDate(), animal.getReceiptCheck(), animal.getTotalAmount(), animal.getUserId()};
			System.out.println("AnimalDAO: "+animal);
			jdbcUtil.setSqlAndParameters(insert1, param1);
			String key[]= {"article_id"}; // DONATION_ARTICLE PK
			jdbcUtil.executeUpdate(key);
			ResultSet rs = jdbcUtil.getGeneratedKeys(); // 생성된 PK 값을 포함한 result set 객체 반환
			int generatedKey = 0;
			if(rs.next()) {
				 generatedKey = rs.getInt(1);     //  PK 값을 읽음
				 String insert2 = "INSERT INTO ANIMAL_ARTICLE VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				 Object[] param2 = new Object[] {generatedKey, animal.getName(), animal.getArea(), animal.getType(), 
                         							animal.getAge(), animal.getWeight(), animal.getGender(), 
                         							animal.getNeutering(), animal.getCurrentStatus(),animal.getHealthStatus(),
                         							animal.getPersonality()};
				 jdbcUtil.setSqlAndParameters(insert2, param2);
				 jdbcUtil.executeUpdate();
			}
			return generatedKey;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;			
	}
	
	public AnimalArticle findAnimalArticleByArticleId(int article_id) throws SQLException{	
		try {
			String sql1 = "SELECT title, category, TO_CHAR(deadline, 'YYYY-MM-DD') \"deadline\", "
					+ "bank_name, acc_holder, acc_num, TO_CHAR(due_date, 'YYYY-MM-DD') \"due_date\", "
					+ "use_plan, other_text, create_date, update_date, receipt_check, user_id, total_amount, "
					+ "name, area, type, age, weight, gender, neutering, current_status, health_status, personality "
					+"FROM animal_article a JOIN donation_article d ON a.article_id = d.article_id "
					+"WHERE a.article_id=? ";
			jdbcUtil.setSqlAndParameters(sql1, new Object[] {article_id});
			ResultSet rs = jdbcUtil.executeQuery();
			if(rs.next()) {
				AnimalArticle article = new AnimalArticle(
												article_id,
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
												rs.getString("name"), 
												rs.getString("area"),
												rs.getString("type"), 
												rs.getString("age"),
												rs.getString("weight"), 
												rs.getString("gender"), 
												rs.getString("neutering"),
												rs.getString("current_status"), 
												rs.getString("health_status"),
												rs.getString("personality")
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
	
	public int update(AnimalArticle article) throws SQLException{
		try {
			String update1 = "UPDATE DONATION_ARTICLE "
							+ "SET title=?, due_date=?, use_plan=?, other_text=?, update_date = SYSDATE, receipt_check=? "
							+ "WHERE article_id=? ";
			Object[] param1 = new Object[] {article.getTitle(), article.getDueDate(), article.getUsePlan(),
					article.getOtherText(), article.getReceiptCheck(), article.getArticleId()};
			jdbcUtil.setSqlAndParameters(update1, param1);
			jdbcUtil.executeUpdate();
			
			String update2 = "UPDATE ANIMAL_ARTICLE "
							+ "SET name=?, area=?, type=?, age=?, weight=?, gender=?, neutering=?, current_status=?, health_status=?, personality=? "
							+ "WHERE article_id=? ";
			Object[] param2 = new Object[] {article.getName(), article.getArea(), article.getType(), 
					article.getAge(), article.getWeight(), article.getGender(), article.getNeutering(),
					article.getCurrentStatus(), article.getHealthStatus(), article.getPersonality(), article.getArticleId()};
			jdbcUtil.setSqlAndParameters(update2, param2);
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
	
	public int remove(int articleId) throws SQLException {	
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("DELETE FROM DONATION_ARTICLE WHERE article_id=? ");
			String sql = sb.toString();
			jdbcUtil.setSqlAndParameters(sql, new Object[] { articleId });
            int result = jdbcUtil.executeUpdate(); // delete 문 실행
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
	
	
	public int createImage(DonationImage image) throws SQLException {
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
            //System.out.println("DONATION_IMAGE table " + result + "개 등록 성공");
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
	
	public int update_image(DonationImage image) throws SQLException{
		try {
			String update1 = "UPDATE DONATION_IMAGE "
							+ "SET img_link = ? "
							+ "WHERE article_id=? ";
			Object[] param1 = new Object[] {image.getFileName(), image.getArticleId()};
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
