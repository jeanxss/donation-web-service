package model.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DonationImage;
import model.SocialGroupArticle;

/**
 * socialGroup article 관리를 위해 데이터베이스 작업을 전담하는 DAO 클래스
 * DONATION_ARTICLE, SOCIALGROUP_ARTICLE, DONATION_IMAGE 테이블에
 * article 정보를 추가, 수정, 삭제, 검색 수행 
 */
public class SocialGroupDAO {
    private JDBCUtil jdbcUtil = null; // JDBCUtil 참조 변수 선언
    int generatedKey = 0;
    
    public SocialGroupDAO() { // 생성자
        jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성 및 활용
    }

    /**
     * article 테이블에 새로운 article 생성
     */
    public int create(SocialGroupArticle article) throws SQLException {
        try {
            //DONATION_ARTICLE
            String sql1 = "INSERT INTO DONATION_ARTICLE VALUES (seq_donation_article_id.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?, ?, ?, ?)";     
            Object[] param1 = new Object[] {
                                article.getTitle(),
                                article.getCategory(), 
                                article.getDeadline(),
                                article.getBankName(), 
                                article.getAccHolder(),
                                article.getAccNum(), 
                                article.getIdCheck(),
                                article.getDueDate(), 
                                article.getUsePlan(),
                                article.getOtherText(), 
                                article.getUpdateDate(),
                                article.getReceiptCheck(), 
                                article.getTotalAmount(), 
                                article.getUserId()
                                };             
            jdbcUtil.setSqlAndParameters(sql1, param1);   // JDBCUtil 에 insert문과 매개 변수 설정
            String key[] = {"article_id"}; // DONATION_ARTICLE PK
            int result = jdbcUtil.executeUpdate(key);
            //System.out.println("Doantion_Article table " + result + "개 등록 성공");
            
            //SOCIALGROUP_ARTICLE
            ResultSet rs = jdbcUtil.getGeneratedKeys(); // 생성된 PK 값을 포함한 result set 객체 반환
            //int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1); // PK 값을 읽음
                String sql2 = "INSERT INTO SOCIALGROUP_ARTICLE VALUES (?, ?, ?, ?, ?, ?)";
                Object[] param2 = new Object[] {
                                    generatedKey, 
                                    article.getAge(), 
                                    article.getGender(), 
                                    article.getType(),
                                    article.getArea(), 
                                    article.getSituation()
                                    };
                jdbcUtil.setSqlAndParameters(sql2, param2); // JDBCUtil 에 insert문과 매개 변수 설정
                result = jdbcUtil.executeUpdate(); // insert 문 실행
                //System.out.println("SocialGroup_Article table " + result + "개 등록 성공");
            
            //DONATION_IMAGE
//            String sql3 = "INSERT INTO DONATION_IMAGE VALUES (?, ?, ?)";
//            Object[] param3 = new Object[] {
//                                generatedKey,
//                                image.getFileOrder(),
//                                image.getFilename()
//                                };
//            jdbcUtil.setSqlAndParameters(sql3, param3); // JDBCUtil 에 insert문과 매개 변수 설정
//            result = jdbcUtil.executeUpdate(); // insert 문 실행
            //System.out.println("DONATION_IMAGE table " + result + "개 등록 성공");
        }
            return generatedKey; // article ID 반환
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {     
            jdbcUtil.commit();
            jdbcUtil.close();   // resource 반환
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
    
    public int create_image(DonationImage image) throws SQLException {
        try {
            //DONATION_IMAGE
            String sql3 = "INSERT INTO DONATION_IMAGE VALUES (?, ?, ?)";
            Object[] param3 = new Object[] {
                                generatedKey,
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
    
    
    /**
     * 주어진 article ID에 해당하는 article 정보를 데이터베이스에서 찾아
     * article 도메인 클래스에 저장하여 반환
     */
    public SocialGroupArticle findSocialGroupArticleByArticleId(int articleId) throws SQLException {
        try {
            String sql = "SELECT title, category, TO_CHAR(deadline, 'YYYY-MM-DD') \"deadline\", "
                    + "bank_name, acc_holder, acc_num, id_check, TO_CHAR(due_date, 'YYYY-MM-DD') \"due_date\", "
                    + "use_plan, other_text, create_date, update_date, receipt_check, user_id, total_amount, "
                    + "age, gender, type, area, situation "
                    + "FROM donation_article d JOIN socialgroup_article s ON d.article_id = s.article_id "
                    + "WHERE s.article_id=? ";
            jdbcUtil.setSqlAndParameters(sql, new Object[] { articleId });
            ResultSet rs = jdbcUtil.executeQuery();
            if (rs.next()) {
                SocialGroupArticle article = new SocialGroupArticle(
                              articleId,
                              rs.getString("title"), 
                              rs.getString("category"), 
                              rs.getString("deadline"), 
                              rs.getString("bank_name"), 
                              rs.getString("acc_holder"), 
                              rs.getString("acc_num"),
                              rs.getString("id_check"),
                              rs.getString("due_date"),
                              rs.getString("use_plan"), 
                              rs.getString("other_text"),
                              rs.getDate("create_date"), 
                              rs.getDate("update_date"), 
                              rs.getString("receipt_check"),
                              rs.getInt("total_amount"),
                              rs.getString("user_id"), 
                              rs.getInt("age"), 
                              rs.getString("gender"),
                              rs.getString("type"), 
                              rs.getString("area"),
                              rs.getString("situation")
                              );
              //이미지 추가
              StringBuilder sb = new StringBuilder();
              sb.append("SELECT img_order, img_link ");
              sb.append("FROM donation_image ");
              sb.append("WHERE article_id = ?");
              sb.append("ORDER BY img_order ");
              String sql2 = sb.toString();
              jdbcUtil.setSqlAndParameters(sql2, new Object[] {articleId});
              
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
      } catch (Exception ex) {
          jdbcUtil.rollback();
          ex.printStackTrace();
      } finally {
          jdbcUtil.commit();
          jdbcUtil.close(); // resource 반환
      }
      return null;
  }
    
    /**
     * 기존의 article 정보를 수정
     */
    public int update(SocialGroupArticle article) throws SQLException {
        try {
            //DONATINO_ARTICLE
            String sql1 = "UPDATE DONATION_ARTICLE "
                        + "SET title=?, due_date=?, use_plan=?, other_text=?, update_date=SYSDATE, receipt_check=? "
                        + "WHERE article_id=? ";
            Object[] param1 = new Object[] {
                                article.getTitle(), 
                                article.getDueDate(), 
                                article.getUsePlan(),
                                article.getOtherText(), 
                                article.getReceiptCheck(), 
                                article.getArticleId()
                                };
            jdbcUtil.setSqlAndParameters(sql1, param1);
            int result = jdbcUtil.executeUpdate();
            //System.out.println("DONATION_ARTICLE table " + result + "개 수정 성공");

            //SOCIALGROUP_ARTICLE
            String sql2 = "UPDATE SOCIALGROUP_ARTICLE "
                        + "SET age=?, gender=?, type=?, area=?, situation=? "
                        + "where article_id=? ";
            Object[] param2 = new Object[] {
                                article.getAge(), 
                                article.getGender(),
                                article.getType(),
                                article.getArea(), 
                                article.getSituation(), 
                                article.getArticleId()
                                };
            jdbcUtil.setSqlAndParameters(sql2, param2);
            result = jdbcUtil.executeUpdate();
            //System.out.println("SOCIALGROUP_ARTICLE table " + result + "개 수정 성공");
            
            return 1;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();   // resource 반환
        }
        return 0;
    }
    
     /**
      * article ID에 해당하는 article을 삭제
      */
     public int remove(int articleId) throws SQLException {
         try {
             String sql1 = "DELETE FROM DONATION_ARTICLE WHERE article_id=?";
             jdbcUtil.setSqlAndParameters(sql1, new Object[] { articleId });
             int result = jdbcUtil.executeUpdate(); // delete 문 실행
             System.out.println("DONATION_ARTICLE table " + result + "개 삭제 성공");
             
             /*
              * String sql2 = "DELETE FROM SOCIALGROUP_ARTICLE WHERE article_id=?";
              * jdbcUtil.setSqlAndParameters(sql2, new Object[] { articleId }); int result2 =
              * jdbcUtil.executeUpdate(); // delete 문 실행
              * System.out.println("SOCIALGROUP_ARTICLE table " + result2 + "개 삭제 성공");
              * 
              * String sql3 = "DELETE FROM DONATION_IMAGE WHERE article_id=?";
              * jdbcUtil.setSqlAndParameters(sql3, new Object[] { articleId }); int result3 =
              * jdbcUtil.executeUpdate(); // delete 문 실행
              * System.out.println("DONATION_IMAGE table " + result3 + "개 삭제 성공");
              */
             
             return 1;
         } catch (Exception ex) {
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