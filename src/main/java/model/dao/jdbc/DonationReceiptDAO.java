package model.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DonationImage;
import model.DonationReceipt;
import model.ReceiptImage;

public class DonationReceiptDAO {
	private JDBCUtil jdbcUtil = null;
	int generatedKey = 0;
	
	public DonationReceiptDAO() {
		jdbcUtil = new JDBCUtil();
	}

	//	후원 인증 작성 시, DonationReceipt table에 튜플 삽입, 후원 인증에 대한 DonationReceipt 객체 입력
	// 후원 인증하면 donationArticle의 receiptCheck의 값도 Y로 변해야하므로 articleId로 update도 실행
	public int create(DonationReceipt receipt) throws SQLException {
		try {
			String insert = "INSERT INTO DONATION_RECEIPT VALUES (seq_receipt_id.nextval, ?, SYSDATE, NULL, ?)";
			int articleId = receipt.getArticleId();
			Object[] param1 = new Object[] {receipt.getContent(), articleId};
			
			jdbcUtil.setSqlAndParameters(insert, param1);	// JDBCUtil 에 insert문과 매개 변수 설정
			// jdbcUtil.executeUpdate();
			
			String key[] = {"receipt_id"}; // DONATION_ARTICLE PK
	        int result = jdbcUtil.executeUpdate(key);
	        //System.out.println("Doantion_Article table " + result + "개 등록 성공");
	            
	        //SOCIALGROUP_ARTICLE
	        ResultSet rs = jdbcUtil.getGeneratedKeys(); // 생성된 PK 값을 포함한 result set 객체 반환
//	        generatedKey = 0;
	        if (rs.next()) {
	                generatedKey = rs.getInt(1);
	                
	                String update = "UPDATE DONATION_ARTICLE SET receipt_check = 'Y' WHERE article_id = ?";
	    			Object[] param2 = new Object[] {articleId};
	    			
	    			jdbcUtil.setSqlAndParameters(update, param2);	// JDBCUtil 에 insert문과 매개 변수 설정
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
	
	public int create_image(ReceiptImage receiptImage) throws SQLException {
        try {
            //DONATION_IMAGE
            String sql3 = "INSERT INTO RECEIPT_IMAGE VALUES (?, ?, ?)";
            Object[] param3 = new Object[] {
            					receiptImage.getReceiptId(),
                                receiptImage.getImgOrder(),
                                receiptImage.getImgLink()
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
	
	//	receiptId로 후원 인증글 찾기, receiptId로 질의(입력)
	public DonationReceipt findByReceiptId(int receiptId) {
		try {
			String query = "SELECT receipt_id, content, create_date, update_date, article_id "
					+ "FROM DONATION_RECEIPT "
					+ "WHERE receipt_id = ?";
	    	
	    	Object[] param = new Object[] {receiptId};
	    	jdbcUtil.setSqlAndParameters(query, param);
	    	
			ResultSet rs = jdbcUtil.executeQuery();
			DonationReceipt receipt = new DonationReceipt();
			
			if (rs.next()) {
				receipt.setReceiptId(rs.getInt("receipt_id"));
				receipt.setContent(rs.getString("content"));
				receipt.setCreateDate(rs.getDate("create_date"));
				receipt.setUpdateDate(rs.getDate("update_date"));
				receipt.setArticleId(rs.getInt("article_id"));
			}
			return receipt;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		} return null;
	}
	
	//	articleId로 후원 인증글 찾기, articleId로 질의(입력)
	public DonationReceipt findByArticleId(int articleId) {
		try {
			String query = "SELECT receipt_id, content, create_date, update_date, article_id "
					+ "FROM DONATION_RECEIPT "
					+ "WHERE article_id = ?";
	    	
	    	Object[] param = new Object[] {articleId};
	    	jdbcUtil.setSqlAndParameters(query, param);
	    	
			ResultSet rs = jdbcUtil.executeQuery();
			DonationReceipt receipt = new DonationReceipt();
			
			if (rs.next()) {
				receipt.setReceiptId(rs.getInt("receipt_id"));
				receipt.setContent(rs.getString("content"));
				receipt.setCreateDate(rs.getDate("create_date"));
				receipt.setUpdateDate(rs.getDate("update_date"));
				receipt.setArticleId(rs.getInt("article_id"));
			}
			return receipt;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		} return null;
	}
	
	//	후원 인증글 수정, id나 createDate는 수정되지 않고 content, updateDate만 수정, 수정한 DonationReceipt 객체 입력
	public int update(DonationReceipt receipt) throws SQLException {
		try {
			String update = "UPDATE DONATION_RECEIPT SET content = ?, update_date = sysdate WHERE receipt_id = ?";		
			Object[] param = new Object[] {receipt.getContent(), receipt.getReceiptId()};
			
			jdbcUtil.setSqlAndParameters(update, param);	// JDBCUtil 에 insert문과 매개 변수 설정
			jdbcUtil.executeUpdate();
		
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 1;			
	}
	
	//	후원 인증글 삭제, donation_article이 삭제되면 같이 삭제되므로 article_id로 삭제(입력)
	public int remove(int articleId, int receiptId) throws SQLException {
		try {
			String remove = "DELETE FROM DONATION_RECEIPT WHERE article_id = ?";		
			Object[] param = new Object[] {articleId};
			
			jdbcUtil.setSqlAndParameters(remove, param);	// JDBCUtil 에 insert문과 매개 변수 설정
			jdbcUtil.executeUpdate();
			
			String remove2 = "DELETE FROM RECEIPT_IMAGE WHERE receipt_id = ?";		
			Object[] param2 = new Object[] {receiptId};
			
			jdbcUtil.setSqlAndParameters(remove2, param2);	// JDBCUtil 에 insert문과 매개 변수 설정
			jdbcUtil.executeUpdate();
			
			String update = "UPDATE DONATION_ARTICLE SET receipt_check = 'N' WHERE article_id = ?";		
			Object[] param3 = new Object[] {articleId};
			
			jdbcUtil.setSqlAndParameters(update, param3);	// JDBCUtil 에 insert문과 매개 변수 설정
			jdbcUtil.executeUpdate();
			
//			String remove2 = "DELETE FROM DONATION_IMAGE WHERE article_id=?";
//            jdbcUtil.setSqlAndParameters(remove2, new Object[] { articleId });
//            jdbcUtil.executeUpdate(); // delete 문 실행
			
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 1;			
	}
	
	public int getMaxOrder(int receiptId) throws SQLException{
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT MAX(img_order) AS \"order\" ");
			sql.append("FROM receipt_image ");
			sql.append("WHERE receipt_id = ?");
			jdbcUtil.setSqlAndParameters(sql.toString(), new Object[] { receiptId });
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
	
	public int update_image(ReceiptImage image) throws SQLException{
		try {
			String update1 = "UPDATE RECEIPT_IMAGE "
							+ "SET img_link = ? "
							+ "WHERE receipt_id=? ";
			Object[] param1 = new Object[] {image.getImgLink(), image.getReceiptId()};
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
	
	
	public List<ReceiptImage> findImageByReceiptId(int receiptId) {
		try {
			String query = "SELECT receipt_id, img_order, img_link "
					+ "FROM RECEIPT_IMAGE "
					+ "WHERE receipt_id = ?";
	    	
	    	Object[] param = new Object[] {receiptId};
	    	jdbcUtil.setSqlAndParameters(query, param);
	    	
			ResultSet rs = jdbcUtil.executeQuery();
			List<ReceiptImage> receiptImgList = new ArrayList<>();
			
			while (rs.next()) {
				ReceiptImage receiptImg = new ReceiptImage();
				receiptImg.setReceiptId(rs.getInt("receipt_id"));
				receiptImg.setImgOrder(rs.getInt("img_order"));
				receiptImg.setImgLink(rs.getString("img_link"));
				
				receiptImgList.add(receiptImg);
			}
			return receiptImgList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		} return null;
	}

}
